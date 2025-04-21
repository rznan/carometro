package com.rznan.lab.engsw.carometro.aluno;

import com.rznan.lab.engsw.carometro.aluno.dtos.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IAlunoService {

    List<AlunoDto> getAll();

    List<AlunoDto> getAllById(List<Long> ids);

    DetailsAlunoDto getById(Long id);

    AlunoDto save(CreateAlunoDto dadosCadastroAluno, MultipartFile imagemPerfil) throws Exception;

    void update(UpdateAlunoDto updateAlunoDto,MultipartFile imagemPerfil) throws Exception;

    void delete(Long id);
}
