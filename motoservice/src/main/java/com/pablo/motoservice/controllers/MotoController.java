package com.pablo.motoservice.controllers;

import com.pablo.motoservice.entities.Moto;
import com.pablo.motoservice.services.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motos")
public class MotoController {
    @Autowired
    private MotoService motoService;

    @GetMapping
    public ResponseEntity<List<Moto>> listarMotos(){
        List<Moto> motos = motoService.getAll();
        if (motos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Moto> obtenerMoto(@PathVariable("id")int id){
        Moto moto = motoService.getMotoById(id);
        if (moto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(moto);
    }

    @PostMapping("/moto")
    public ResponseEntity<Moto> guardarMoto(@RequestBody Moto moto){
        Moto nuevaMoto = motoService.save(moto);       
        return ResponseEntity.ok(nuevaMoto);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Moto>> listarMotosPorUsuarioId(@PathVariable("usuarioId")int usuarioId){
        List<Moto> motos = motoService.byUsuarioId(usuarioId);
        if (motos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motos);
    }

}