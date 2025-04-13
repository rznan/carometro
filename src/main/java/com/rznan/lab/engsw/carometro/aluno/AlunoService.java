package com.rznan.lab.engsw.carometro.aluno;

import com.rznan.lab.engsw.carometro.aluno.dtos.DadosCadastroAluno;
import com.rznan.lab.engsw.carometro.interfaces.IService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService implements IService<Aluno, DadosCadastroAluno, Long> {

    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public List<Aluno> getAll() {
        return alunoRepository.findAll(Sort.by("nome").ascending());
    }

    @Override
    public List<Aluno> getAllById(List<Long> longs) {
        List<Aluno> alunos = new ArrayList<>();
        for(Long l : longs) {
            alunos.add(alunoRepository.getReferenceById(l));
        }
        return alunos;
    }

    @Override
    public Aluno getById(Long aLong) {
        return alunoRepository.findById(aLong).orElse(null);
    }

    @Override
    @Transactional
    // TODO: Receber long com id do curso e faculdade para incluir no aluno
    public void save(DadosCadastroAluno dadosCadastroAluno) {
        if(dadosCadastroAluno == null) {
            return;
        }
        Aluno a = new Aluno(dadosCadastroAluno);
        alunoRepository.save(a);
    }

    @Override
    @Transactional
    public void delete(Long aLong) {
        alunoRepository.deleteById(aLong);
    }
}
