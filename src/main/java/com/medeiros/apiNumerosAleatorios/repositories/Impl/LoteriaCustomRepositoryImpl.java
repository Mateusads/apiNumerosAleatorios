package com.medeiros.apiNumerosAleatorios.repositories.Impl;

import com.medeiros.apiNumerosAleatorios.entities.Loteria;
import com.medeiros.apiNumerosAleatorios.repositories.LoteriaCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class LoteriaCustomRepositoryImpl implements LoteriaCustomRepository {

    @Autowired
    private EntityManager entityManager;


    @Override
    public List<Loteria> findByEmail(String email) {


        String sql = " Select u from Loteria u where u.email = :varEmail";
        TypedQuery<Loteria> queryLoteria = entityManager.
                createQuery(sql, Loteria.class).setParameter("varEmail", email);

        return queryLoteria.getResultList();
    }
}
