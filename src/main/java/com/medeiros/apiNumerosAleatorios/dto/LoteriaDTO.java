package com.medeiros.apiNumerosAleatorios.dto;

import javax.validation.constraints.Email;
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
	private Loteria loteria;

	@Email
	private String email;

	@JsonIgnore
	private Long numero;

	public LoteriaDTO(String email, Long numero) {
		this.email = email;
		this.numero = numero;

	}

	public Loteria transformaParaObjeto(LoteriaDTO objDTO) {

		return new Loteria(this.email, numero);
	}

	public void setNumero(Long numero) {
		loteria.addNumero(numero);

	}

}
