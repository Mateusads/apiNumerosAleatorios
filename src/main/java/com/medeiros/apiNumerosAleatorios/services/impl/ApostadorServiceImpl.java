package com.medeiros.apiNumerosAleatorios.services.impl;

import com.medeiros.apiNumerosAleatorios.dto.ApostadorRequestDTO;
import com.medeiros.apiNumerosAleatorios.entities.Apostador;
import com.medeiros.apiNumerosAleatorios.repositories.ApostadorRepository;
import com.medeiros.apiNumerosAleatorios.resources.ApostadorMapper;
import com.medeiros.apiNumerosAleatorios.services.ApostadorService;
import com.medeiros.apiNumerosAleatorios.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApostadorServiceImpl implements ApostadorService {

    private final ApostadorRepository apostadorRepository;


    public ApostadorServiceImpl(ApostadorRepository apostadorRepository) {
        this.apostadorRepository = apostadorRepository;

    }

    @Override
    public List<Apostador> findAll() {
        return apostadorRepository.findAll();
    }

    @Override
    public Apostador sorteio(ApostadorRequestDTO apostadorRequestDTO) {

        var apostador = ApostadorMapper.toEntity(apostadorRequestDTO);
        List<Long> numerosSorteados = new ArrayList<>();

        if (verificaEmail(apostador)) {
            apostador.setNumero(sortearNumero());
            return apostadorRepository.save(apostador);
        } else
            numerosSorteados = numerosJaSorteados(apostador);
        var numeroSorteio = sortearNumerosSemRepetir(numerosSorteados);
        apostador.setNumero(numeroSorteio);
        System.out.println("apostadorDTO numero2 " + apostador);
        return apostadorRepository.save(apostador);

    }

    @Override
    public List<Apostador> findByEmail(String email) {
        var entityApostador = apostadorRepository.findByEmail(email);

        if (entityApostador.isEmpty()) {
            throw new ResourceNotFoundException("Email não localizado ");
        }
        return entityApostador;
    }

    @Override
    public Apostador delete(String email) {
        List<Apostador> apostador = findByEmail(email);
        apostadorRepository.delete(apostador.get(0));
        return null;
    }

    private Long sortearNumero() {
        return (long) (Math.random() * 99999 + 10000);
    }

    private Long sortearNumerosSemRepetir(List<Long> numerosSorteados) {
        var numeroSorteio = sortearNumero();
        if (numerosSorteados.contains(numeroSorteio)) {
            return sortearNumerosSemRepetir(numerosSorteados);
        } else
            return numeroSorteio;
    }

    private List<Long> numerosJaSorteados(Apostador apostador) {
        var apostadorBusca = findByEmail(apostador.getEmail());
        List<Long> jaSorteados = new ArrayList<>();
        for (int i = 0; apostadorBusca.size() != i; i++) {
            jaSorteados.add(apostadorBusca.get(i).getNumero());
        }
        return jaSorteados;
    }

    private Boolean verificaEmail(Apostador apostador) {
        var apostadorBusca = apostadorRepository.findByEmail(apostador.getEmail());
        return (apostadorBusca.isEmpty());
    }

}
