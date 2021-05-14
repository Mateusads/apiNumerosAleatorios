package com.medeiros.apiNumerosAleatorios.dto;

import java.util.Set;

import javax.persistence.Id;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.medeiros.apiNumerosAleatorios.entities.Loteria;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors
public class LoteriaDTO {

	@Autowired
	@JsonIgnore
	Loteria loteria;
	
	String email;	
	
	@JsonIgnore
	Long numero;

	public LoteriaDTO(String email, Long numero) {
		Loteria loteria = new Loteria();			
		
	}
	
	public Loteria transformaParaObjeto(LoteriaDTO objDTO){
		
	    return new Loteria(objDTO.email, numero);
	}

	public void setNumero(Long numero) {
		loteria.addNumero(numero);
	
	}
	

}
