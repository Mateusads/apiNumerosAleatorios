package com.medeiros.apiNumerosAleatorios.resources;


import com.medeiros.apiNumerosAleatorios.dto.ApostadorRequestDTO;
import com.medeiros.apiNumerosAleatorios.entities.Apostador;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ApostadorMapperTest {

    @Test
    void quandoTemUmaEntidadeRetornoDTO() {
        var sorteio = Apostador.builder()
                .email("mateus@test.com")
                .numero(21L)
                .build();
        var resultado = ApostadorMapper.toDto(sorteio);
        Assertions.assertThat(resultado.getEmail()).isEqualTo("mateus@test.com");
        Assertions.assertThat(resultado.getNumero()).isEqualTo(21L);
    }

    @Test
    void quantoTenhoUmDtoRetornoEntidade() {
        var sorteioDTO = new ApostadorRequestDTO();
        sorteioDTO.setEmail("teste@test.com");
        sorteioDTO.setNumero(555L);
        var sorteio = ApostadorMapper.toEntity(sorteioDTO);

        Assertions.assertThat(sorteio.getEmail()).isEqualTo("teste@test.com");
        Assertions.assertThat(sorteio.getNumero()).isEqualTo(555L);
    }

}