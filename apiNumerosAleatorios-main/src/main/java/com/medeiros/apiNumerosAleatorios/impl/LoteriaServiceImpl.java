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
	private Loteria loteriaUpdate = new Loteria();

	@Override
	public List<Loteria> findAll() {

		return loteriaRepository.findAll();
	}

	@Override
	public Loteria findByEmail(String email) {
		String string1, string2 = email;
		int i = 0;
		entityLoteria = findAll();
		

		for (Loteria loteria : entityLoteria) {

			string1 = String.valueOf(entityLoteria.get(i).getEmail().trim());

			i++;
			System.out.println(string1 + "  " + string2);
			if (string1.equalsIgnoreCase(string2)) {
				throw new ResourceNotFoundException("error");				
			} else
				return entityLoteria.get(i);

		}

		return null;

	}

	@Override
	public Loteria sorteio(LoteriaDTO objDTO) {
		Loteria obj = objDTO.transformaParaObjeto(objDTO);
		entityLoteria = loteriaRepository.findAll();
		int i = 0;
		Long numeroAleatorio;

		if (verificarEmail(obj) == true) {

			numeroAleatorio = sorteioNumero(loteriaUpdate);
			loteriaUpdate.addNumero(numeroAleatorio);
			String string1 = obj.getEmail();
			string1 = string1.trim();
			loteriaUpdate.setEmail(string1.trim());
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

		if (!numerosAleatorios.contains(numeroSorteio)) {
			return numeroSorteio;
		} else
			return sorteioNumero(loteriaUpdate);

	}

	public Long sortearNumero() {

		Long numeroAleatorio = (long) (Math.random() * 99999 + 10000);
		return numeroAleatorio;

	}

}
