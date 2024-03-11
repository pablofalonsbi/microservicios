package com.pablo.carroservice.repositories;

import com.pablo.carroservice.entities.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarroRepository extends JpaRepository<Carro,Integer> {
    List<Carro> findByUsuarioId(int usuarioId);
}
