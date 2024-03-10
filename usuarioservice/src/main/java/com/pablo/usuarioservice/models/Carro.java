package com.pablo.usuarioservice.models;

import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Carro {
    private String marca;
    private String modelo;
    private int usuarioId;

    public Carro() {
    }
}