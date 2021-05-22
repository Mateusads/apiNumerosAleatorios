package com.medeiros.apiNumerosAleatorios.repositories;

import com.medeiros.apiNumerosAleatorios.entities.Apostador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ApostadorRepository extends JpaRepository<Apostador, Long>, ApostadorCustomRepository {


}


