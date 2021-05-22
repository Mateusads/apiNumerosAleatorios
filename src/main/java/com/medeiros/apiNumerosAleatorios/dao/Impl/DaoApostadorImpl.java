package com.medeiros.apiNumerosAleatorios.DAO.Impl;

import com.medeiros.apiNumerosAleatorios.DAO.DaoApostador;
import com.medeiros.apiNumerosAleatorios.entities.Apostador;
import com.medeiros.apiNumerosAleatorios.repositories.ApostadorRepository;

public class DaoApostadorImpl implements DaoApostador {

    private final ApostadorRepository ApostadorRepository;

    public DaoApostadorImpl(ApostadorRepository ApostadorRepository) {
        this.ApostadorRepository = ApostadorRepository;
    }

    @Override
    public void createAposta(Apostador apostador) {
        ApostadorRepository.save(apostador);
    }

    @Override
    public void buscarPorEmail(String email) {
        ApostadorRepository.findByEmail(email);
    }

    @Override
    public void alterarNumeroAposta(Apostador apostador) {

    }

    @Override
    public void deleteApostador(Apostador Apostador) {

    }
}
