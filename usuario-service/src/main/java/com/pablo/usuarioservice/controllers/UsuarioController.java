package com.pablo.usuarioservice.controllers;

import com.pablo.usuarioservice.entities.Usuario;
import com.pablo.usuarioservice.models.Carro;
import com.pablo.usuarioservice.models.Moto;
import com.pablo.usuarioservice.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        List<Usuario> usuarios = usuarioService.getAll();
        if (usuarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id")int id){
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario){
        Usuario nuevoUsuario = usuarioService.save(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }
    
    //Rest Template
    
    @GetMapping("/carros/{usuarioId}")
    public ResponseEntity<List<Carro>> listarCarros(@PathVariable("usuarioId")int usuarioId){
    	Usuario usuario = usuarioService.getUsuarioById(usuarioId);
    	if(usuario == null) {
    		return ResponseEntity.notFound().build();
    	}
    	
    	List<Carro> carros = usuarioService.getCarros(usuarioId);
    	return ResponseEntity.ok(carros);
    }
    
    @GetMapping("/motos/{usuarioId}")
    public ResponseEntity<List<Moto>> listarMotos(@PathVariable("usuarioId")int usuarioId){
    	Usuario usuario = usuarioService.getUsuarioById(usuarioId);
    	if(usuario == null) {
    		return ResponseEntity.notFound().build();
    	}
    	
    	List<Moto> motos = usuarioService.getMotos(usuarioId);
    	return ResponseEntity.ok(motos);
    }

    @PostMapping("/carro/{usuarioId}")
    public ResponseEntity<Carro> guardarCarro(@PathVariable("usuarioId") int usuarioId, @RequestBody Carro carro){
        Carro nuevoCarro =usuarioService.saveCarro(usuarioId,carro);
        return ResponseEntity.ok(nuevoCarro);
    }

    @GetMapping("/todos/{usuarioId}")
    public ResponseEntity<Map<String,Object>> listarTodo(@PathVariable("usuarioId")int usuarioId){
        Map<String,Object> info = usuarioService.getTodos(usuarioId);
        return ResponseEntity.ok(info);
    }
    
}
