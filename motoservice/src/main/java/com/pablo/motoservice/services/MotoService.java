package com.pablo.motoservice.services;

import com.pablo.motoservice.entities.Moto;
import com.pablo.motoservice.repositories.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {

    @Autowired
    private MotoRepository motoRepository;

    public List<Moto> getAll(){
        return motoRepository.findAll();
    }

    public Moto getMotoById(int id){
        return motoRepository.findById(id).orElse(null);
    }

    public Moto save(Moto moto){
        Moto nuevoCarro = motoRepository.save(moto);
        return nuevoCarro;
    }

    public List<Moto> byUsuarioId(int usuarioId){
        return motoRepository.findByUsuarioId(usuarioId);
    }

}
