package com.rznan.lab.engsw.carometro.curso;

import com.rznan.lab.engsw.carometro.aluno.Aluno;
import com.rznan.lab.engsw.carometro.aluno.AlunoServiceImpl;
import com.rznan.lab.engsw.carometro.curso.dtos.CreateCursoDto;
import com.rznan.lab.engsw.carometro.curso.dtos.CursoDto;
import com.rznan.lab.engsw.carometro.curso.dtos.DetailsCursoDto;
import com.rznan.lab.engsw.carometro.curso.dtos.UpdateCursoDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Lazy
@Service
public class CursoServiceImpl implements ICursoService{

    @Autowired
    private CursoRepository repository;

    @Lazy
    @Autowired
    private AlunoServiceImpl alunoService;

    @Override
    public List<CursoDto> getAll() {
        return repository.findAll().stream().map(CursoDto::new).toList();
    }

    @Override
    public List<CursoDto> getAllById(List<Long> ids) {
        return getCursosByIds(ids).stream().map(CursoDto::new).toList();
    }

    public List<Curso> getCursosByIds(List<Long> ids) {
        ArrayList<Curso> cursos = new ArrayList<>();
        for(Long id : ids) {
            Curso curso = repository.findById(id).orElse(null);
            if(curso != null) {
                cursos.add(curso);
                continue;
            }
            System.err.println("Curso não encontrado. ID: " + id);
        }
        return cursos;
    }

    @Override
    public DetailsCursoDto getById(Long id) {
        Curso c = getCursoById(id);
        if (c == null) {
            return null;
        }
        return new DetailsCursoDto(c);
    }

    public Curso getCursoById(Long id) {
        return repository.findById(id).orElse(null);
    }


    @Override
    @Transactional
    public CursoDto save(CreateCursoDto dto) {
        if (dto == null) {
            return null;
        }
        Curso curso = new Curso(dto);

        List<Aluno> alunos = alunoService.getAlunosByIds(dto.alunosId());
        curso.setAlunos(alunos);
        // TODO: pegar faculdade

        Curso saved = repository.save(curso);

        return new CursoDto(saved);
    }

    @Override
    @Transactional
    public void update(UpdateCursoDto dto) {
        if(dto == null) {
            return;
        }
        Curso curso = repository.getReferenceById(dto.id());
        curso.update(dto);

        List<Aluno> alunos = alunoService.getAlunosByIds(dto.alunosId());
        curso.setAlunos(alunos);
        // TODO: pegar faculdade

        repository.save(curso);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
