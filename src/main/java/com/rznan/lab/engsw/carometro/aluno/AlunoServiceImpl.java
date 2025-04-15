package com.rznan.lab.engsw.carometro.aluno;

import com.rznan.lab.engsw.carometro.aluno.dtos.AlunoDto;
import com.rznan.lab.engsw.carometro.aluno.dtos.DetailsAlunoDto;
import com.rznan.lab.engsw.carometro.aluno.dtos.UpdateAlunoDto;
import com.rznan.lab.engsw.carometro.aluno.dtos.CreateAlunoDto;
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
public class AlunoServiceImpl implements IAlunoService{

    @Autowired
    private AlunoRepository alunoRepository;

    @Lazy
    @Autowired
    //TODO: Criar uma interface interna para comunicação entre services
    private CursoServiceImpl cursoService;

    @Override
    public List<AlunoDto> getAll() {
        return alunoRepository.findAll(Sort.by("nome").ascending()).stream().map(AlunoDto::new).toList();
    }

    @Override
    public List<AlunoDto> getAllById(List<Long> longs) {
        List<Aluno> alunos = getAlunosByIds(longs);
        return alunos.stream().map(AlunoDto::new).toList();
    }

    public List<Aluno> getAlunosByIds(List<Long> longs) {
        List<Aluno> alunos = new ArrayList<>();
        for(Long l : longs) {
            Aluno aluno = alunoRepository.findById(l).orElse(null);
            if (aluno != null) {
                alunos.add(aluno);
                continue;
            }
            System.err.println("Aluno não encontrado. ID: " + l);
        }
        return alunos;
    }

    @Override
    public DetailsAlunoDto getById(Long aLong) {
        Aluno aluno = getAluno(aLong);
        if (aluno == null) {
            return null;
        }
        return new DetailsAlunoDto(aluno);
    }

    private Aluno getAluno(Long aLong) {
        return alunoRepository.findById(aLong).orElse(null);
    }

    @Override
    @Transactional
    public AlunoDto save(CreateAlunoDto dto) throws Exception {
        if(dto == null) {
            return null;
        }
        Aluno aluno = new Aluno(dto);
        addCursoToAluno(dto.idCurso(), aluno);
        Aluno saved = alunoRepository.save(aluno);
        return new AlunoDto(saved);
    }

    @Override
    @Transactional
    public void update(UpdateAlunoDto dto) throws Exception {
        if (dto == null) {
            return;
        }
        Aluno aluno = alunoRepository.getReferenceById(dto.id());

        addCursoToAluno(dto.idCurso(), aluno);

        aluno.update(dto);
        alunoRepository.save(aluno);
    }

    private void addCursoToAluno(Long idCurso, Aluno aluno) throws Exception {
        Curso curso = cursoService.getCursoById(idCurso);
        if (curso == null) {
            throw new Exception("Curso inválido");
        }
        aluno.setCurso(curso);
    }

    @Override
    @Transactional
    public void delete(Long aLong) {
        alunoRepository.deleteById(aLong);
    }
}
