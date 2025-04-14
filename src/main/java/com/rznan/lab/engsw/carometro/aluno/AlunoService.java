package com.rznan.lab.engsw.carometro.aluno;

import com.rznan.lab.engsw.carometro.aluno.dtos.AlunoDto;
import com.rznan.lab.engsw.carometro.aluno.dtos.DetailsAlunoDto;
import com.rznan.lab.engsw.carometro.aluno.dtos.UpdateAlunoDto;
import com.rznan.lab.engsw.carometro.aluno.dtos.CreateAlunoDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService implements IAlunoService{

    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public List<AlunoDto> getAll() {
        return alunoRepository.findAll(Sort.by("nome").ascending()).stream().map(AlunoDto::new).toList();
    }

    @Override
    public List<AlunoDto> getAllById(List<Long> longs) {
        List<Aluno> alunos = new ArrayList<>();
        for(Long l : longs) {
            alunos.add(alunoRepository.getReferenceById(l));
        }
        return alunos.stream().map(AlunoDto::new).toList();
    }

    @Override
    public DetailsAlunoDto getById(Long aLong) {
        Aluno aluno = alunoRepository.findById(aLong).orElse(null);
        if (aluno == null) {
            return null;
        }
        return new DetailsAlunoDto(aluno);
    }

    @Override
    @Transactional
    public AlunoDto save(CreateAlunoDto dadosCadastroAluno) {
        if(dadosCadastroAluno == null) {
            return null;
        }
        Aluno saved = alunoRepository.save(new Aluno(dadosCadastroAluno));
        return new AlunoDto(saved);
    }

    @Override
    @Transactional
    public void update(UpdateAlunoDto updateAlunoDto) {
        if (updateAlunoDto == null) {
            return;
        }
        Aluno aluno = alunoRepository.getReferenceById(updateAlunoDto.id());
        aluno.update(updateAlunoDto);
    }

    @Override
    @Transactional
    public void delete(Long aLong) {
        alunoRepository.deleteById(aLong);
    }
}
