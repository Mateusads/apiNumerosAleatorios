package com.medeiros.apiNumerosAleatorios.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.medeiros.apiNumerosAleatorios.entities.Loteria;
import com.medeiros.apiNumerosAleatorios.repositories.LoteriaRepository;


@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{	
	
	@Autowired
	private LoteriaRepository repository;
	
	@Override
	public void run(String... args) throws Exception {

	
	
	Loteria lot1 = new Loteria(null, "mateus@medeiros", 123123);
	Loteria lot2 = new Loteria(null, "medeiros@medeiros", 1223133);
	
	repository.saveAll(Arrays.asList(lot1, lot2));
	

	}
}
