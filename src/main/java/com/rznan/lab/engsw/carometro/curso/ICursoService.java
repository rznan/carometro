package com.rznan.lab.engsw.carometro.curso;

import com.rznan.lab.engsw.carometro.aluno.dtos.AlunoDto;
import com.rznan.lab.engsw.carometro.aluno.dtos.CreateAlunoDto;
import com.rznan.lab.engsw.carometro.aluno.dtos.DetailsAlunoDto;
import com.rznan.lab.engsw.carometro.aluno.dtos.UpdateAlunoDto;

import java.util.List;

public interface ICursoService {

    List<AlunoDto> getAll();

    List<AlunoDto> getAllById(List<Long> ids);

    DetailsAlunoDto getById(Long id);

    AlunoDto save(CreateAlunoDto dadosCadastroAluno);

    void update(UpdateAlunoDto updateAlunoDto);

    void delete(Long id);
}
