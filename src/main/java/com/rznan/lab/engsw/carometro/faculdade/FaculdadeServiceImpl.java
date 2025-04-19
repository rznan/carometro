package com.rznan.lab.engsw.carometro.faculdade;

import com.rznan.lab.engsw.carometro.curso.CursoServiceImpl;
import com.rznan.lab.engsw.carometro.faculdade.dtos.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Lazy
@Service
public class FaculdadeServiceImpl implements IFaculdadeService {

    @Autowired
    private FaculdadeRepository repository;

    @Lazy
    @Autowired
    private CursoServiceImpl cursoService;

    @Override
    public List<FaculdadeDto> getAll() {
        return repository.findAll().stream()
                .map(FaculdadeDto::new)
                .toList();
    }

    @Override
    public List<FaculdadeDto> getAllById(List<Long> ids) {
        return getFaculdadesByIds(ids).stream()
                .map(FaculdadeDto::new)
                .toList();
    }

    public List<Faculdade> getFaculdadesByIds(List<Long> ids) {
        List<Faculdade> faculdades = new ArrayList<>();
        for (Long id : ids) {
            repository.findById(id).ifPresentOrElse(
                    faculdades::add,
                    () -> System.err.println("Faculdade não encontrada. ID: " + id)
            );
        }
        return faculdades;
    }

    @Override
    public DetailsFaculdadeDto getById(Long id) {
        Faculdade faculdade = getFaculdadeById(id);
        return faculdade != null ? new DetailsFaculdadeDto(faculdade) : null;
    }

    public Faculdade getFaculdadeById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public FaculdadeDto save(CreateFaculdadeDto dto) {
        if (dto == null) return null;

        Faculdade faculdade = new Faculdade(dto);
        cursoService.getCursosByIds(dto.cursoIds()).forEach(faculdade::addCurso);

        return new FaculdadeDto(repository.save(faculdade));
    }

    @Override
    @Transactional
    public void update(UpdateFaculdadeDto dto) {
        if (dto == null) return;
        Faculdade faculdade = repository.getReferenceById(dto.id());
        faculdade.update(dto);

        faculdade.getCursos().forEach(c -> c.setFaculdade(null));
        faculdade.getCursos().clear();

        cursoService.getCursosByIds(dto.cursoIds()).forEach(faculdade::addCurso);
        repository.save(faculdade);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Faculdade faculdade = repository.getReferenceById(id);
        faculdade.getCursos().forEach(c -> c.setFaculdade(null));
        repository.deleteById(id);
    }
}
