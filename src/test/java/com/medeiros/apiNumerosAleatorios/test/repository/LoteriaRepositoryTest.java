package com.medeiros.apiNumerosAleatorios.test.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.medeiros.apiNumerosAleatorios.entities.Loteria;
import com.medeiros.apiNumerosAleatorios.repositories.LoteriaRepository;
import com.medeiros.apiNumerosAleatorios.services.LoteriaService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LoteriaRepositoryTest {

	@MockBean
	private LoteriaRepository loteriaRepository;
	
	@MockBean
	private LoteriaService loteriaService;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void createLoteriaData() {
		Long numero =  (long) 1234;
		Loteria loteria = new Loteria(null, "mateus.medeiros@gmail.com", numero);
		this.loteriaService.saveLoteria(loteria);
		assertThat(loteria.getEmail()).isEqualTo("mateus.medeiros@gmail.com");
		assertThat(String.valueOf(loteria.getNumeroAleatorio())).isEqualTo("[1234]");

	}
	
	
	@Test
	public void delete_loteria_repository() throws Exception{
		Loteria loteria = new Loteria(null, "mateus.medeiros@gmail.com", 1234L);
		this.loteriaService.saveLoteria(loteria);
		
		this.loteriaService.delete(loteria.getId());
		
		assertNull(loteria.getId());
		
		

	}
}