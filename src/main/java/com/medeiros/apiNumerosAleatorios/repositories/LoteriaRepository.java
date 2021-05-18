package com.medeiros.apiNumerosAleatorios.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medeiros.apiNumerosAleatorios.dto.LoteriaDTO;
import com.medeiros.apiNumerosAleatorios.entities.Loteria;


	@Repository
	public interface LoteriaRepository extends JpaRepository<Loteria, Long> {


	}


