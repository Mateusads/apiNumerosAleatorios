package com.medeiros.apiNumerosAleatorios.resources;

import com.medeiros.apiNumerosAleatorios.dto.ApostadorRequestDTO;
import com.medeiros.apiNumerosAleatorios.dto.ApostadorResponseDTO;
import com.medeiros.apiNumerosAleatorios.entities.Apostador;

import java.util.ArrayList;
import java.util.List;


public class ApostadorMapper {

    public static ApostadorResponseDTO toDto(Apostador apostador) {
        ApostadorResponseDTO apostadorResponseDTO = new ApostadorResponseDTO();
        apostadorResponseDTO.setEmail(apostador.getEmail());
        apostadorResponseDTO.setNumero(apostador.getNumero());
        return apostadorResponseDTO;
    }

    public static List<ApostadorResponseDTO> toDtos(List<Apostador> sorteios) {
        List<ApostadorResponseDTO> listaApostadorDtos = new ArrayList<>();

        for (int i = 0; i < sorteios.size(); i++) {
            listaApostadorDtos.add(toDto(sorteios.get(i)));
        }

        return listaApostadorDtos;

    }

    public static Apostador toEntity(ApostadorRequestDTO apostadorRequestDTO) {

        var apostador = Apostador.builder()
                .email(apostadorRequestDTO.getEmail())
                .numero(apostadorRequestDTO.getNumero())
                .build();
        return apostador;


    }
}
