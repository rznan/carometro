package com.rznan.lab.engsw.carometro.aluno;

import com.rznan.lab.engsw.carometro.aluno.dtos.*;
import com.rznan.lab.engsw.carometro.curso.Curso;
import com.rznan.lab.engsw.carometro.curso.CursoServiceImpl;
import com.rznan.lab.engsw.carometro.storage.IStorageService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @Lazy
    @Autowired
    private IStorageService storageService;

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
    public AlunoDto save(CreateAlunoDto dto,MultipartFile imagem) throws Exception {


        if (dto == null) return null;
        Aluno aluno = new Aluno(dto);
        if (imagem != null && !imagem.isEmpty()) {
            aluno.setImagemPerfil(storageService.uploadFile(imagem)); // DA PRA MELHORAR ?
        }
        addCursoToAluno(dto.idCurso(), aluno);

        return new AlunoDto(alunoRepository.save(aluno));
    }

    @Override
    @Transactional
    public void update(UpdateAlunoDto dto,MultipartFile imagem) throws Exception {
        if (dto == null) return;

        Aluno aluno = alunoRepository.findById(dto.id()).orElse(null);
        if (aluno == null) {
            System.err.println("Aluno não encontrado. ID: " + dto.id());
            return;
        }

        addCursoToAluno(dto.idCurso(), aluno);
        aluno.update(dto);

        //DELETA A IMAGEM ANTIGA E SALVA A NOVA
        if (imagem != null && !imagem.isEmpty()) {  // Verifica se o usuário enviou uma nova imagem
            String urlImagem = storageService.uploadFile(imagem);
            if (aluno.getImagemPerfil() != null && !aluno.getImagemPerfil().isBlank()) {// Se o aluno já tem uma imagem antiga, exclui ela
                storageService.deleteFile(aluno.getImagemPerfil());
            }
            aluno.setImagemPerfil(urlImagem);
        }

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
        try { // te, jeito melhor de fazer esse delete ? e esse try catch ?
            storageService.deleteFile(alunoRepository.findById(id).get().getImagemPerfil());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        alunoRepository.deleteById(id);
    }
}
