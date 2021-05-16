package com.medeiros.apiNumerosAleatorios.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.medeiros.apiNumerosAleatorios.dto.LoteriaDTO;
import com.medeiros.apiNumerosAleatorios.entities.Loteria;
import com.medeiros.apiNumerosAleatorios.impl.LoteriaServiceImpl;
import com.medeiros.apiNumerosAleatorios.services.LoteriaService;

@RestController
@RequestMapping(value = "/loteria")
public class LoteriaResource {

	@Autowired
	private LoteriaServiceImpl loteriaServiceImpl;
	private LoteriaDTO loteriaDto;
	
	
	@GetMapping(value = "")
	public ResponseEntity<List<Loteria>> findAll() {		
		
		List<Loteria> obj = loteriaServiceImpl.findAll();
		
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping(value = "/{email}")
	public ResponseEntity<Loteria> findByEmail(@PathVariable String email) {		
		
		Loteria obj = loteriaServiceImpl.findByEmail(email);
		
		return ResponseEntity.ok().body(obj);
	}
	
	
	@PostMapping
	public ResponseEntity<Loteria> sorteio(@Valid @RequestBody LoteriaDTO objDTO) {
		Loteria objLoteria = loteriaServiceImpl.sorteio(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{email}").buildAndExpand(objLoteria).toUri();
		return ResponseEntity.created(uri).body(objLoteria);
	}

//	@PostMapping(value = "/buscarEmail")
//	public ResponseEntity<Loteria> listarPorEmail(@RequestBody Loteria loteria) {
//		loteria = loteriaService.findByEmail(loteria);
//		if(loteria != null) {
//			URI uri = ServletUriComponentsBuilder
//					.fromCurrentRequest()
//					.path("/buscarEmail")
//					.buildAndExpand(loteria.getId())
//					.toUri();
//			return ResponseEntity.created(uri).body(loteria);
//		}else
//			return ResponseEntity.noContent().build();
//
//
//
//	}

//	@DeleteMapping(value = "/{id}")
//	public ResponseEntity<Void> delete(@PathVariable Long id) {
//		loteriaService.delete(id);
//		return ResponseEntity.noContent().build();
//	}

}