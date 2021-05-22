package com.medeiros.apiNumerosAleatorios.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@Accessors
public class ApostadorResponseDTO {

    @JsonIgnore
    private Long id;

    @Email
    private String email;

    private Long numero;

}
