package com.rznan.lab.engsw.carometro.aluno;

import com.rznan.lab.engsw.carometro.aluno.dtos.*;

import java.util.List;

public interface IAlunoService {

    List<AlunoDto> getAll();

    List<AlunoDto> getAllById(List<Long> ids);

    DetailsAlunoDto getById(Long id);

    AlunoDto save(CreateAlunoDto dadosCadastroAluno);

    void update(UpdateAlunoDto updateAlunoDto);

    void delete(Long id);
}
