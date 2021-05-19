package com.medeiros.apiNumerosAleatorios.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultRowSorter;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.medeiros.apiNumerosAleatorios.dto.LoteriaDTO;
import com.medeiros.apiNumerosAleatorios.entities.Loteria;
import com.medeiros.apiNumerosAleatorios.repositories.LoteriaRepository;
import com.medeiros.apiNumerosAleatorios.services.LoteriaService;
import com.medeiros.apiNumerosAleatorios.services.exceptions.ConstraintViolationExceptions;
import com.medeiros.apiNumerosAleatorios.services.exceptions.MethodArgumentNotValidExceptions;
import com.medeiros.apiNumerosAleatorios.services.exceptions.ResourceNotFoundException;

@Service
public class LoteriaServiceImpl implements LoteriaService {

	@Autowired
	private LoteriaRepository loteriaRepository;

	private List<Loteria> entityLoteria = new ArrayList<>();
	private static Loteria loteriaUpdate = new Loteria();

	@Override
	public List<Loteria> findAll() {

		return loteriaRepository.findAll();
	}


	@Override
	public Loteria findByEmail(String email) {

		entityLoteria = loteriaRepository.findByEmail(email);

		if(entityLoteria.isEmpty()){
			throw new ResourceNotFoundException("Email não localizado ");
		}
		return entityLoteria.get(0);

	}

	@Override
	public Loteria sorteio(LoteriaDTO objDTO){

		Loteria obj = objDTO.transformaParaObjeto(objDTO);

		try {
		entityLoteria = findAll();
		Long numeroAleatorio;
		if (verificarEmail(obj)) {
			numeroAleatorio = sorteioNumero(loteriaUpdate);
			loteriaUpdate.addNumero(numeroAleatorio);
			loteriaUpdate.setEmail(obj.getEmail());
			return loteriaRepository.save(loteriaUpdate);
		} else
			numeroAleatorio = sortearNumero();
			obj.addNumero(sortearNumero());
			return loteriaRepository.save(obj);

		} catch (Exception e) {
			throw new MethodArgumentNotValidExceptions(e);
		}

	}

	public boolean verificarEmail(Loteria obj) {

		entityLoteria = loteriaRepository.findByEmail(obj.getEmail());
		if (entityLoteria.contains(obj) ) {
			loteriaUpdate = entityLoteria.get(0);
			return true;
		}
		return false;

	}

	public Long sorteioNumero(Loteria loteriaUpdate) {

		Set<Long> numerosAleatorios = new HashSet<>();
		numerosAleatorios = loteriaUpdate.getNumeroAleatorio();
		Long numeroSorteio = (long) 0;
		numeroSorteio = sortearNumero();
		Boolean equals = verificarEquals(numerosAleatorios, numeroSorteio);

		if (!equals) {
			return numeroSorteio;
		} else
			return sorteioNumero(loteriaUpdate);
	}

	public Long sortearNumero() {
		return (Long) (long) (Math.random() * 99999 + 10000);
	}

	private static Boolean verificarEquals(Object varOne, Object varTwo) {

		String varOneType = varOne.getClass().getSimpleName();
		String varTwoType = varTwo.getClass().getSimpleName();
		Set<Long> numerosAleatorios = new HashSet<>();

		if (varOneType.equalsIgnoreCase("String") && varTwoType.equalsIgnoreCase("String")) {
			if (varOne.equals(varTwo)) {
				return true;
			} else
				return false;
		} else
			numerosAleatorios = (Set<Long>) varOne;
		if (numerosAleatorios.contains(varTwo)) {
			return true;
		}
		return false;

	}

}
