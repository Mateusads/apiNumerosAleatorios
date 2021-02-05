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
	private Loteria loteriaUpdate = new Loteria();

	public List<Loteria> findAll() {
		return loteriaRepository.findAll();
	}

	public Loteria sorteio(Loteria obj) {
		entity = loteriaRepository.findAll();
		int i = 0; 
		Long numeroAleatorio;

		if (verificarEmail(obj) == true) {
			numeroAleatorio = verificaNumerosIguais(loteriaUpdate);
			loteriaUpdate.addNumero(numeroAleatorio);
			return loteriaRepository.save(loteriaUpdate);

		} else
			numeroAleatorio = sortearNumero();
		obj.addNumero(sortearNumero());
		return loteriaRepository.save(obj);

	}

	public Long sortearNumero() {
		Random gerador = new Random();
//		Integer numeroAleatorio = gerador.nextInt((999999 - 111111) + 1) + 111111;
		Long numeroAleatorio = (long) (Math.random()*10+5);
		return numeroAleatorio;

	}

	public boolean verificarEmail(Loteria obj) {
		int i = 0;
		for (Loteria loteria : entity) {
			loteria = entity.get(i);
			i++;
			if (loteria.getEmail().equals(obj.getEmail())) {
				loteriaUpdate = loteria;
				return true;
			}
		}
		return false;

	}

	public Long verificaNumerosIguais(Loteria loteriaUpdate) {

		ArrayList<Long> numerosAleatorios = new ArrayList<Long>();
		numerosAleatorios = loteriaUpdate.getNumeroAleatorio();
		int tamanho = numerosAleatorios.size();
		Long numeroSorteio = (long) 0;
		numeroSorteio = sortearNumero();
		boolean numeroRepitido = true;
		
		System.out.println("numeros aleatorios " + numerosAleatorios);
		
		System.out.println("tamanho " + tamanho );		
		


		while (numeroRepitido) {

			for (int i = 0; i < tamanho; i++) {				
				System.out.println("array numeros " + numerosAleatorios);
				System.out.println("dentro do for / numero sorteado " + numeroSorteio );
				if (numerosAleatorios.get(i).equals(numeroSorteio)) {
					System.out.println("dentro do if " );
					numeroSorteio = sortearNumero();
					i = -1;
					numeroRepitido = true;
				}else if(i == tamanho-1){
					System.out.println("dentro do elseIf ");					
					numeroRepitido = false;
				}
			}

		}

		return numeroSorteio;

	}
}
