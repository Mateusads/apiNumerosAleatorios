package com.medeiros.apiNumerosAleatorios.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.medeiros.apiNumerosAleatorios.dto.LoteriaDTO;
import com.medeiros.apiNumerosAleatorios.entities.Loteria;

@Service
public interface LoteriaService {
	
	
	List<Loteria> findAll();
	
	Loteria sorteio(LoteriaDTO objDTO);

	Loteria findByEmail(String email);
	
}