package com.rznan.lab.engsw.carometro.aluno;

import com.rznan.lab.engsw.carometro.aluno.dtos.*;

import java.util.List;

public interface IAlunoService {

    List<AlunoDto> getAll();

    List<AlunoDto> getAllById(List<Long> ids);

    DetailsAlunoDto getById(Long id);

    AlunoDto save(CreateAlunoDto dadosCadastroAluno) throws Exception;

    void update(UpdateAlunoDto updateAlunoDto) throws Exception;

    void delete(Long id);
}
