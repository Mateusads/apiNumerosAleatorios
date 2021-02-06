package com.medeiros.apiNumerosAleatorios.test.service;

import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.medeiros.apiNumerosAleatorios.entities.Loteria;
import com.medeiros.apiNumerosAleatorios.impl.LoteriaServiceImpl;
import com.medeiros.apiNumerosAleatorios.repositories.LoteriaRepository;
import com.medeiros.apiNumerosAleatorios.services.LoteriaService;

@SpringBootTest
public class LoteriaServiceTest {
	
	@MockBean
	private LoteriaRepository loteriaRepository;	
	
	private LoteriaService loteriaService;
	
	private Loteria loteria;
	
	private static final String EMAIL = "Mateus";
	private static final Long NUMERO_ALEATORIO = (long) 123456;

	
	@BeforeEach
	public void setUp() throws Exception{
		
		loteriaService = new LoteriaServiceImpl(loteriaRepository);
		loteria = new Loteria();
		loteria.setEmail(EMAIL);
		loteria.addNumero(NUMERO_ALEATORIO);		
	}
	
	@Test
	public void save_loteria_repository() throws Exception{
		loteriaService.saveLoteria(loteria);
		
	}	


}
