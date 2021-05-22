package com.medeiros.apiNumerosAleatorios.services;

import com.medeiros.apiNumerosAleatorios.dto.ApostadorRequestDTO;
import com.medeiros.apiNumerosAleatorios.entities.Apostador;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApostadorService {


    List<Apostador> findAll();

    Apostador sorteio(ApostadorRequestDTO loteriaRequestDTO);

    List<Apostador> findByEmail(String email);

    Apostador delete(String email);

}