package com.rznan.lab.engsw.carometro.aluno;

import com.rznan.lab.engsw.carometro.aluno.dtos.*;
import com.rznan.lab.engsw.carometro.curso.Curso;
import com.rznan.lab.engsw.carometro.curso.CursoServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Lazy
@Service
public class AlunoServiceImpl implements IAlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Lazy
    @Autowired
    // TODO: tentar fazer uma interface para comunicação entre serviços
    private CursoServiceImpl cursoService;

    @Override
    public List<AlunoDto> getAll() {
        return alunoRepository.findAll(Sort.by("nome").ascending())
                .stream().map(AlunoDto::new).toList();
    }

    @Override
    public List<AlunoDto> getAllById(List<Long> ids) {
        return getAlunosByIds(ids).stream()
                .map(AlunoDto::new).toList();
    }

    public List<Aluno> getAlunosByIds(List<Long> ids) {
        List<Aluno> alunos = new ArrayList<>();
        for (Long id : ids) {
            alunoRepository.findById(id).ifPresentOrElse(
                    alunos::add,
                    () -> System.err.println("Aluno não encontrado. ID: " + id)
            );
        }
        return alunos;
    }

    @Override
    public DetailsAlunoDto getById(Long id) {
        Aluno aluno = getAlunoById(id);
        return aluno != null ? new DetailsAlunoDto(aluno) : null;
    }

    public Aluno getAlunoById(Long id) {
        return alunoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public AlunoDto save(CreateAlunoDto dto) throws Exception {
        if (dto == null) return null;

        Aluno aluno = new Aluno(dto);
        addCursoToAluno(dto.idCurso(), aluno);

        return new AlunoDto(alunoRepository.save(aluno));
    }

    @Override
    @Transactional
    public void update(UpdateAlunoDto dto) throws Exception {
        if (dto == null) return;

        Aluno aluno = alunoRepository.findById(dto.id()).orElse(null);
        if (aluno == null) {
            System.err.println("Aluno não encontrado. ID: " + dto.id());
            return;
        }

        addCursoToAluno(dto.idCurso(), aluno);
        aluno.update(dto);

        alunoRepository.save(aluno);
    }

    private void addCursoToAluno(Long idCurso, Aluno aluno) throws Exception {
        Curso curso = cursoService.getCursoById(idCurso);
        if (curso == null) {
            throw new Exception("Curso inválido. ID: " + idCurso);
        }
        aluno.setCurso(curso);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        alunoRepository.deleteById(id);
    }
}
