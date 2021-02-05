package com.medeiros.apiNumerosAleatorios.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.medeiros.apiNumerosAleatorios.entities.Loteria;
import com.medeiros.apiNumerosAleatorios.services.LoteriaService;

@SpringBootTest
public class LoteriaTest {
	
	@Autowired
	private LoteriaService service;
	
	private List<Loteria> emailLoteria = new ArrayList<>();
	private Optional<Loteria> loteria = Optional.ofNullable(new Loteria());
	
	@Test
	public void buscarEmailPorId() {
		loteria = service.findById(1);
		assertEquals("[123123]", String.valueOf(loteria.get().getNumeroAleatorio()));
		assertEquals("mateus@medeiros", loteria.get().getEmail());
		
		loteria = service.findById(2);
		assertEquals("[132131]", String.valueOf(loteria.get().getNumeroAleatorio()));
		assertEquals("medeiros@medeiros", loteria.get().getEmail());

	}
	
	
//	@BeforeEach
//	public void setUp() throws Exception{
//		
//		loteriaService = new LoteriaService();
//		client = new Client();
//		client.setNome(NOME);
//		client.setSobrenome(SOBRENOME);		
//		client.setCpf(CPF);
//		client.setEmail(EMAIL);
//		client.setDataNascimento(DATANASCIMENTO);
//		
//	}
//	
//	
//	@Test
//	public void save_client_repository() throws Exception{
//		
//		clientService.saveClient(client);
//		
//		verify(clientRepository).save(client);
//		
//	}
	

}
