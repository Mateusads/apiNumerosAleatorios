package com.medeiros.apiNumerosAleatorios.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.medeiros.apiNumerosAleatorios.entities.Loteria;
import com.medeiros.apiNumerosAleatorios.services.LoteriaService;
import java.util.HashSet;
import java.util.Set;

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
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<Loteria>> findById(@PathVariable int id) {
		Optional<Loteria> obj = loteriaService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Loteria> sorteio(@RequestBody Loteria obj){
		obj = loteriaService.sorteio(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}


}
