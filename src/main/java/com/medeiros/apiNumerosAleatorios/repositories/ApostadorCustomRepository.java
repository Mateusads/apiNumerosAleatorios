package com.medeiros.apiNumerosAleatorios.repositories;

import com.medeiros.apiNumerosAleatorios.entities.Apostador;

import java.util.List;

public interface ApostadorCustomRepository {

    List<Apostador> findByEmail(String email);
}
