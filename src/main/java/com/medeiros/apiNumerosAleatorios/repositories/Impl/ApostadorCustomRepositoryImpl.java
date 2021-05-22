package com.medeiros.apiNumerosAleatorios.repositories.Impl;

import com.medeiros.apiNumerosAleatorios.entities.Apostador;
import com.medeiros.apiNumerosAleatorios.repositories.ApostadorCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ApostadorCustomRepositoryImpl implements ApostadorCustomRepository {

    @Autowired
    private EntityManager entityManager;


    @Override
    public List<Apostador> findByEmail(String email) {

        String sql = " Select l from Apostador l where l.email = :varEmail";
        TypedQuery<Apostador> queryApostador = entityManager.
                createQuery(sql, Apostador.class).setParameter("varEmail", email);
        return queryApostador.getResultList();
    }


}
