package com.rznan.lab.engsw.carometro.faculdade.dtos;

import com.rznan.lab.engsw.carometro.faculdade.Faculdade;

import java.time.LocalDate;

public record FaculdadeDto(
        Long id,
        String nome,
        LocalDate inauguracao
) {

    public FaculdadeDto(Faculdade faculdade) {
            this(
                faculdade.getId(),
                faculdade.getNome(),
                faculdade.getInauguracao()
        );
    }
}