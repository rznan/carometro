package com.rznan.lab.engsw.carometro.faculdade.dtos;

import com.rznan.lab.engsw.carometro.faculdade.Faculdade;

import java.util.Date;

public record FaculdadeDto(
        Long id,
        String nome,
        Date inauguracao
) {

    public FaculdadeDto(Faculdade faculdade) {
            this(
                faculdade.getId(),
                faculdade.getNome(),
                faculdade.getInauguracao()
        );
    }
}