package com.medeiros.apiNumerosAleatorios.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.medeiros.apiNumerosAleatorios.dto.LoteriaDTO;
import com.medeiros.apiNumerosAleatorios.entities.Loteria;
import com.medeiros.apiNumerosAleatorios.repositories.LoteriaRepository;
import com.medeiros.apiNumerosAleatorios.services.LoteriaService;
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
		String string1, string2;
		int i = 0;
		entityLoteria = findAll();

		for (Loteria loteria : entityLoteria) {

			string1 = String.valueOf(entityLoteria.get(i).getEmail().trim());
			string2 = email.trim();

			i++;
			System.out.println(string1 + "  " + string2);

			if (string1.equalsIgnoreCase(string2)) {
				return entityLoteria.get(i - 1);
			}
		}
		throw new ResourceNotFoundException("Email não localizado ");

	}

	@Override
	public Loteria sorteio(LoteriaDTO objDTO) {
		Loteria obj = objDTO.transformaParaObjeto(objDTO);
		entityLoteria = loteriaRepository.findAll();
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

	}

	public boolean verificarEmail(Loteria obj) {
		int i = 0;
		for (Loteria loteria : entityLoteria) {
			loteria = entityLoteria.get(i);
			String string1, string2;
			string1 = String.valueOf(loteria.getEmail()).trim();
			string2 = String.valueOf(obj.getEmail()).trim();
			i++;

			if (string1.equalsIgnoreCase(string2)) {
				loteriaUpdate = loteria;
				return true;
			}
		}
		return false;

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
