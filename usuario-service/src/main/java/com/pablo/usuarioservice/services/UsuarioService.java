package com.pablo.usuarioservice.services;

import com.pablo.usuarioservice.entities.Usuario;
import com.pablo.usuarioservice.feignclients.CarroFeignClient;
import com.pablo.usuarioservice.feignclients.MotoFeignClient;
import com.pablo.usuarioservice.models.Carro;
import com.pablo.usuarioservice.models.Moto;
import com.pablo.usuarioservice.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CarroFeignClient carroFeignClient;

    @Autowired
    private MotoFeignClient motoFeignClient;


    public List<Carro> getCarros(int usuarioId){
        List<Carro> carros = restTemplate.getForObject("http://localhost:8002/carros/usuario/" + usuarioId, List.class);
        return carros;
    }

    public List<Moto> getMotos(int usuarioId){
        List<Moto> motos = restTemplate.getForObject("http://localhost:8003/motos/usuario/" + usuarioId, List.class);
        return motos;
    }
    
    public List<Usuario> getAll(){
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(int id){
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario){
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return nuevoUsuario;
    }
    
    public Carro saveCarro(int usuarioId, Carro carro) {
        carro.setUsuarioId(usuarioId);
        Carro nuevoCarro = carroFeignClient.save(carro);
        return nuevoCarro;
    }

    public List<Carro> getCarrosByUserId(int usuarioId) {
       List<Carro> carros = carroFeignClient.getCarros(usuarioId);
       return carros;
    }

    public List<Moto> getMotosByUserId(int usuarioId) {
        List<Moto> motos = motoFeignClient.getMotos(usuarioId);
        return motos;
    }

    public Map<String, Object> getTodos(int usuarioId){
        Map<String, Object> resultado = new HashMap<>();
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);

        if(usuario == null){
            resultado.put("Mensaje","El usuario no existe");
            return resultado;
        }

        List<Carro> carros = this.getCarrosByUserId(usuarioId);
        List<Moto> motos = this.getMotosByUserId(usuarioId);

        if(carros.isEmpty()){
            resultado.put("Carros","No posee Carros");
        }else{
            resultado.put("Carros",carros);
        }

        if(motos.isEmpty()){
            resultado.put("Motos","No posee Carros");
        }else{
            resultado.put("Motos",motos);
        }

        return resultado;
    }

}
