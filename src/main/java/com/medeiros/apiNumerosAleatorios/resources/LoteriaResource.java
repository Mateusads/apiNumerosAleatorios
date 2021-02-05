package com.medeiros.apiNumerosAleatorios.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.medeiros.apiNumerosAleatorios.entities.Loteria;
import com.medeiros.apiNumerosAleatorios.services.LoteriaService;

@RestController
@RequestMapping(value = "/loteria")
public class LoteriaResource {
	
	@Autowired
	private LoteriaService loteriaService;

	@GetMapping
	public ResponseEntity<List<Loteria>> findall() {
		List<Loteria> list = loteriaService.findAll();

		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<Loteria> sorteio(@RequestBody Loteria obj){
		obj = loteriaService.sorteio(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}


}
