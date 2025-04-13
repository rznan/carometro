package com.rznan.lab.engsw.carometro.aluno;

import com.rznan.lab.engsw.carometro.interfaces.IService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService implements IService<AlunoDto, Long> {

    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public List<AlunoDto> getAll() {
        // transforma a lista de aluno do dto em uma lista de dtos de aluno
        return alunoRepository.findAll(Sort.by("nome").ascending()).stream().map(AlunoDto::new).toList();
    }

    @Override
    public List<AlunoDto> getAllById(List<Long> longs) {
        List<Aluno> alunos = new ArrayList<>();
        for(Long l : longs) {
            alunos.add(alunoRepository.getReferenceById(l));
        }
        // transforma a lista de aluno do dto em uma lista de dtos de aluno
        return alunos.stream().map(AlunoDto::new).toList();
    }

    @Override
    public AlunoDto getById(Long aLong) {
        Aluno aluno = alunoRepository.findById(aLong).orElse(null);
        if (aluno == null) {
            return null;
        }
        return new AlunoDto(aluno);
    }

    @Override
    @Transactional
    // TODO: Usar os campos de ID do curso e da faculdade no dto para inserí-los no aluno
    public AlunoDto save(AlunoDto alunoDto) {
        if(alunoDto == null) {
            return null;
        }
        Aluno a = new Aluno(alunoDto);
        return new AlunoDto(alunoRepository.save(a));
    }

    @Override
    @Transactional
    // TODO: Criar dtos das outras entidades para utilizá-las aqui
    public AlunoDto update(AlunoDto alunoDto) {
        return null;
    }

    @Override
    @Transactional
    public void delete(Long aLong) {
        alunoRepository.deleteById(aLong);
    }
}
