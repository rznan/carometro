package com.rznan.lab.engsw.carometro.faculdade;

import com.rznan.lab.engsw.carometro.faculdade.dtos.*;

import java.util.List;

public interface IFaculdadeService {

    List<FaculdadeDto> getAll();

    List<FaculdadeDto> getAllById(List<Long> ids);

    DetailsFaculdadeDto getById(Long id);

    FaculdadeDto save(CreateFaculdadeDto dadosCadastroFaculdade);

    void update(UpdateFaculdadeDto updateFaculdadeDto);

    void delete(Long id);
}
