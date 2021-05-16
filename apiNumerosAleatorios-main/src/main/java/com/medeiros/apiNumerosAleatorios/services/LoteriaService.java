package com.medeiros.apiNumerosAleatorios.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.medeiros.apiNumerosAleatorios.dto.LoteriaDTO;
import com.medeiros.apiNumerosAleatorios.entities.Loteria;

@Service
public interface LoteriaService {
	
	
	List<Loteria> findAll();
	
	Loteria findByEmail(String email);
	
	Loteria sorteio(LoteriaDTO objDTO);
	
}