package com.medeiros.apiNumerosAleatorios.impl;

import com.medeiros.apiNumerosAleatorios.entities.Loteria;
import com.medeiros.apiNumerosAleatorios.repositories.LoteriaRepository;
import com.medeiros.apiNumerosAleatorios.services.LoteriaService;

public class LoteriaServiceImpl extends LoteriaService {

	private final LoteriaRepository loteriaRepository;

	public LoteriaServiceImpl(LoteriaRepository loteriaRepository) {
		this.loteriaRepository = loteriaRepository;
	}

	
	@Override
	public Loteria saveLoteria(Loteria loteria) {
		return loteriaRepository.save(loteria);
	}

}
