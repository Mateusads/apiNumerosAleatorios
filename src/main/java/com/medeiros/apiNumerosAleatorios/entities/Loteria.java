package com.medeiros.apiNumerosAleatorios.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "loteria")
public class Loteria implements Serializable{
	private static final long serialVersionUID = 1L;

	public Loteria() {
		super();
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(unique = true, name = "email")
	private String email;
	



	@ElementCollection(targetClass=Long.class)
	@Fetch(FetchMode.JOIN)
	@Column(name = "numeros")
	private Set<Long> numeroAleatorio = new HashSet<Long>();


	
		public Loteria(Long id, String email, Long numeroAleatorio) {
		super();
		this.id = id;
		this.email = email;
		this.numeroAleatorio.add(numeroAleatorio);
		
		
	}
		
	public void addNumero(Long numeroAleatorio) {
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
	
	public Set<Long> getNumeroAleatorio() {
		return numeroAleatorio;
	}
	
	public void setNumeroAleatorio(Set<Long> numeroAleatorio) {
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
