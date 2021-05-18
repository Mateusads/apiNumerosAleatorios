package com.medeiros.apiNumerosAleatorios.repositories;

import com.medeiros.apiNumerosAleatorios.entities.Loteria;

import java.util.List;

public interface LoteriaCustomRepository {

    List<Loteria> findByEmail(String email);
}
