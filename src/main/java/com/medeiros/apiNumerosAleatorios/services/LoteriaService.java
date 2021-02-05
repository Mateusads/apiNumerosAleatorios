package com.medeiros.apiNumerosAleatorios.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medeiros.apiNumerosAleatorios.entities.Loteria;
import com.medeiros.apiNumerosAleatorios.repositories.LoteriaRepository;

@Service
public class LoteriaService {

	@Autowired
	private LoteriaRepository loteriaRepository;
	private List<Loteria> entity = new ArrayList<>();

	public List<Loteria> findAll() {
		return loteriaRepository.findAll();
	}

	public Loteria sorteio(Loteria obj) {
		entity = loteriaRepository.findAll();
		int i = 0, numeroAleatorio = SortearNumero();
		
		if(verificarEmail(obj) == true) {
			obj.addNumero(numeroAleatorio);
			return loteriaRepository.save(obj);
			
		}else
			obj.addNumero(SortearNumero());
			return loteriaRepository.save(obj);
			
			
//
//		for (Loteria loteria : entity) {
//			loteria = entity.get(i);
//			System.out.println(loteria.getEmail());
//			System.out.println(obj.getEmail());
//			i++;
//			if (loteria.getEmail().equals(obj.getEmail())) {
//				if (loteria.getNumeroAleatorio().equals(numeroAleatorio)) {
//					System.out.println(loteria.getNumeroAleatorio() + "AQUIXXXXXXX" + numeroAleatorio);
//					return loteria;
//				}
//				System.out.println(loteria.getNumeroAleatorio() + "AQUIXXXXXXX" + numeroAleatorio);
//				loteria.addNumero(numeroAleatorio);
//				return loteriaRepository.save(loteria);
//			}
//		}



	}

	public Integer SortearNumero() {
		Random gerador = new Random();
//		Integer numeroAleatorio = gerador.nextInt((999999 - 111111) + 1) + 111111;
		Integer numeroAleatorio = gerador.nextInt((3 - 1) + 3);
		return numeroAleatorio;

	}

	public boolean verificarEmail(Loteria obj) {
		int i = 0;
		for (Loteria loteria : entity) {
			loteria = entity.get(i);
			System.out.println(loteria.getEmail());
			System.out.println(obj.getEmail());
			i++;
			if (loteria.getEmail().equals(obj.getEmail())) {
				return true;
			}
		}
		return false;

	}

}
