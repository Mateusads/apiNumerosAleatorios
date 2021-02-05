package com.medeiros.apiNumerosAleatorios.entities;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Loteria implements Serializable{
	private static final long serialVersionUID = 1L;

	public Loteria() {
		super();
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(unique = true)
	String email;
	@Column(unique = true)
	ArrayList<Integer> numeroAleatorio = new ArrayList<Integer>();


	
		public Loteria(Long id, String email, Integer numeroAleatorio) {
		super();
		this.id = id;
		this.email = email;
		this.numeroAleatorio.add(numeroAleatorio);
		
		
	}
		
	public void addNumero(Integer numeroAleatorio) {
		this.numeroAleatorio.add(numeroAleatorio);
	}
	
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	public ArrayList<Integer> getNumeroAleatorio() {
		return numeroAleatorio;
	}
	
	public void setNumeroAleatorio(ArrayList<Integer> numeroAleatorio) {
		this.numeroAleatorio = numeroAleatorio;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Loteria other = (Loteria) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	
	
	
	
	

}
