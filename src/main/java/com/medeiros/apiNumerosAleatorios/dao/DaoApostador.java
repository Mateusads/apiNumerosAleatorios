package com.medeiros.apiNumerosAleatorios.DAO;

import com.medeiros.apiNumerosAleatorios.entities.Apostador;

public interface DaoApostador {

    public void createAposta(Apostador apostador);

    public void buscarPorEmail(String email);

    public void alterarNumeroAposta(Apostador apostador);

    public void deleteApostador(Apostador apostador);
}
