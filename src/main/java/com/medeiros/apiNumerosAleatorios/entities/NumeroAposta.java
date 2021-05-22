package com.medeiros.apiNumerosAleatorios.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class NumeroAposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "numeroAposta")
    Long numeroAposta;

    @ManyToOne
    @JoinColumn(name = "id_apostador")
    private Apostador apostador;
}
