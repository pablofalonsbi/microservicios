package com.pablo.motoservice.repositories;

import com.pablo.motoservice.entities.Moto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MotoRepository extends JpaRepository<Moto,Integer> {
    List<Moto> findByUsuarioId(int usuarioId);
}
