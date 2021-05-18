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
		int i = 0;
		entityLoteria = findAll();

		for (Loteria loteria : entityLoteria) {
			if (verificarEquals(entityLoteria.get(i).getEmail().trim(), email.trim())) {
				return entityLoteria.get(i);
			}
			i++;
		}
		throw new ResourceNotFoundException("Email não localizado ");
	}

	@Override
	public Loteria sorteio(LoteriaDTO objDTO){
		Loteria obj = objDTO.transformaParaObjeto(objDTO);

		try {			
		entityLoteria = findAll();
		Long numeroAleatorio;
		if (verificarEmail(obj) == true) {
			numeroAleatorio = sorteioNumero(loteriaUpdate);
			loteriaUpdate.addNumero(numeroAleatorio);
			String string1 = obj.getEmail().trim();
			loteriaUpdate.setEmail(string1);
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
		int i = 0;

		System.out.println(obj.getEmail());
		if (obj.getEmail() != null) {

			for (Loteria loteria : entityLoteria) {
				loteria = entityLoteria.get(i);
				if (verificarEquals(loteria.getEmail().trim(), obj.getEmail().trim())) {
					loteriaUpdate = loteria;
					return true;
				}
				i++;
			}
			return false;

		}
		System.out.println("333 " + obj.getEmail());
		throw new ConstraintViolationExceptions("Email formato invalido ");
	}

	public Long sorteioNumero(Loteria loteriaUpdate) {

		Set<Long> numerosAleatorios = new HashSet<>();
		numerosAleatorios = loteriaUpdate.getNumeroAleatorio();
		Long numeroSorteio = (long) 0;
		numeroSorteio = sortearNumero();
		Boolean equals = verificarEquals(numerosAleatorios, numeroSorteio);

		if (equals != true) {
			return numeroSorteio;
		} else
			return sorteioNumero(loteriaUpdate);
	}

	public Long sortearNumero() {

		Long numeroAleatorio = (long) (Math.random() * 99999 + 10000);
		return numeroAleatorio;

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
