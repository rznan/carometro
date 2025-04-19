package com.rznan.lab.engsw.carometro.curso;

import com.rznan.lab.engsw.carometro.aluno.AlunoServiceImpl;
import com.rznan.lab.engsw.carometro.curso.dtos.*;
import com.rznan.lab.engsw.carometro.faculdade.Faculdade;
import com.rznan.lab.engsw.carometro.faculdade.FaculdadeServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Lazy
@Service
public class CursoServiceImpl implements ICursoService {

    @Autowired
    private CursoRepository repository;

    @Lazy
    @Autowired
    private AlunoServiceImpl alunoService;

    @Lazy
    @Autowired
    private FaculdadeServiceImpl faculdadeService;

    @Override
    public List<CursoDto> getAll() {
        return repository.findAll().stream()
                .map(CursoDto::new)
                .toList();
    }

    @Override
    public List<CursoDto> getAllById(List<Long> ids) {
        return getCursosByIds(ids).stream()
                .map(CursoDto::new)
                .toList();
    }

    public List<Curso> getCursosByIds(List<Long> ids) {
        if (ids == null) {
            return List.of();
        }
        List<Curso> cursos = new ArrayList<>();
        for (Long id : ids) {
            repository.findById(id).ifPresentOrElse(
                    cursos::add,
                    () -> System.err.println("Curso não encontrado. ID: " + id)
            );
        }
        return cursos;
    }

    @Override
    public DetailsCursoDto getById(Long id) {
        Curso curso = getCursoById(id);
        return curso != null ? new DetailsCursoDto(curso) : null;
    }

    public Curso getCursoById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public CursoDto save(CreateCursoDto dto) throws Exception {
        if (dto == null) return null;

        Curso curso = new Curso(dto);
        alunoService.getAlunosByIds(dto.alunosId()).forEach(curso::addAluno);
        curso.setFaculdade(resolveFaculdade(dto.faculdadeId()));

        return new CursoDto(repository.save(curso));
    }

    @Override
    @Transactional
    public void update(UpdateCursoDto dto) throws Exception {
        if (dto == null) return;

        Curso curso = repository.findById(dto.id()).orElse(null);
        if (curso == null) {
            System.err.println("Curso não encontrado. ID: " + dto.id());
            return;
        }

        curso.update(dto);
        curso.getAlunos().forEach(a -> a.setCurso(null));
        alunoService.getAlunosByIds(dto.alunosId()).forEach(curso::addAluno);
        curso.setFaculdade(resolveFaculdade(dto.faculdadeId()));

        repository.save(curso);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Curso curso = repository.getReferenceById(id);
        curso.getAlunos().forEach(a -> a.setCurso(null));
        repository.deleteById(id);
    }

    private Faculdade resolveFaculdade(Long faculdadeId) throws Exception {
        Faculdade faculdade = faculdadeService.getFaculdadeById(faculdadeId);
        if (faculdade == null) {
            throw new Exception("Faculdade inválida (ID: " + faculdadeId + ")");
        }
        return faculdade;
    }
}
