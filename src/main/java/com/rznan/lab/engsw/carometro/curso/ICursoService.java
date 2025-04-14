package com.rznan.lab.engsw.carometro.curso;


import com.rznan.lab.engsw.carometro.curso.dtos.*;

import java.util.List;

public interface ICursoService {

    List<CursoDto> getAll();

    List<CursoDto> getAllById(List<Long> ids);

    DetailsCursoDto getById(Long id);

    CursoDto save(CreateCursoDto dadosCadastroCurso);

    void update(UpdateCursoDto updateCursoDto);

    void delete(Long id);
}
