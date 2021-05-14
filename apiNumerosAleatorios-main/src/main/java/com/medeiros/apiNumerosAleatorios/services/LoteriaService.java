package com.medeiros.apiNumerosAleatorios.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.medeiros.apiNumerosAleatorios.dto.LoteriaDTO;
import com.medeiros.apiNumerosAleatorios.entities.Loteria;
import com.medeiros.apiNumerosAleatorios.repositories.LoteriaRepository;
import com.medeiros.apiNumerosAleatorios.services.exceptions.DataBaseException;
import com.medeiros.apiNumerosAleatorios.services.exceptions.ResourceNotFoundException;

@Service
public class LoteriaService {

	@Autowired
	private LoteriaRepository loteriaRepository;
	private List<Loteria> entity = new ArrayList<>();
	private Loteria loteriaUpdate = new Loteria();

	public List<Loteria> findAll() {
		return loteriaRepository.findAll();
	}

	public Optional<Loteria> findById(int i) {
		long id = i;
		Optional<Loteria> obj = loteriaRepository.findById(id);
		return Optional.of(obj.orElseThrow(() -> new ResourceNotFoundException(i)));

	}

	public Loteria findByEmail(String email) {

		int i = 0;
		entity = findAll();
		try {
			for (Loteria loteria : entity) {
				String string1, string2;
				string1 = String.valueOf(entity.get(i).getEmail().trim());
				string2 = String.valueOf(email.trim());
				i++;
				if (string1.equalsIgnoreCase(string2)) {
					return entity.get(i - 1);
				}
			}
		} catch (Exception e) {
			throw new DataBaseException(e.getMessage());
		}
		return null;

	}

	public Loteria sorteio(LoteriaDTO objDTO) {
		Loteria obj = objDTO.transformaParaObjeto(objDTO);
		entity = loteriaRepository.findAll();
		int i = 0;
		Long numeroAleatorio;

		if (verificarEmail(obj) == true) {
			numeroAleatorio = verificaNumerosIguais(loteriaUpdate);
			loteriaUpdate.addNumero(numeroAleatorio);
			String string1 = obj.getEmail();
			string1 = string1.trim();
			loteriaUpdate.setEmail(string1);
			return loteriaRepository.save(loteriaUpdate);
		} else
			numeroAleatorio = sortearNumero();
		obj.addNumero(sortearNumero());
		return loteriaRepository.save(obj);

	}

	public Long sortearNumero() {

		Long numeroAleatorio = (long) (Math.random() * 99999 + 10000);
		return numeroAleatorio;

	}

	public boolean verificarEmail(Loteria obj) {
		int i = 0;
		for (Loteria loteria : entity) {
			loteria = entity.get(i);
			String string1, string2;
			string1 = String.valueOf(entity.get(i).getEmail());
			string2 = String.valueOf(obj.getEmail());
			string1 = string1.trim();
			i++;

			if (string1.equalsIgnoreCase(string2)) {
				loteriaUpdate = loteria;
				return true;
			}
		}
		return false;

	}

	public Long verificaNumerosIguais(Loteria loteriaUpdate) {

		Set<Long> numerosAleatorios = new HashSet<>();
		numerosAleatorios = loteriaUpdate.getNumeroAleatorio();
		Long numeroSorteio = (long) 0;
		numeroSorteio = sortearNumero();

		if (!numerosAleatorios.contains(numeroSorteio)) {
			return numeroSorteio;
		} else
			return numeroSorteio;

	}

	public Loteria saveLoteria(Loteria loteria) {
		loteriaRepository.save(loteria);
		return null;
	}

	public void delete(Long id) {
		try {
			loteriaRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}

	}
}