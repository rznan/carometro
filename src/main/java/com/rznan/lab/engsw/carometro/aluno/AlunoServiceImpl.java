package com.rznan.lab.engsw.carometro.aluno;

import com.rznan.lab.engsw.carometro.aluno.dtos.AlunoDto;
import com.rznan.lab.engsw.carometro.aluno.dtos.CreateAlunoDto;
import com.rznan.lab.engsw.carometro.aluno.dtos.DetailsAlunoDto;
import com.rznan.lab.engsw.carometro.aluno.dtos.UpdateAlunoDto;
import com.rznan.lab.engsw.carometro.curso.Curso;
import com.rznan.lab.engsw.carometro.curso.CursoController;
import com.rznan.lab.engsw.carometro.curso.CursoServiceImpl;
import com.rznan.lab.engsw.carometro.storage.IImageStorageService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(CursoController.class);
    @Autowired
    private AlunoRepository alunoRepository;
    @Lazy
    @Autowired
    private CursoServiceImpl cursoService;
    @Lazy
    @Autowired
    private IImageStorageService storageService;

    @Override
    public List<AlunoDto> getAll() {
        return alunoRepository.findAll(Sort.by("nome").ascending()).stream().map(AlunoDto::new).toList();
    }

    @Override
    public List<AlunoDto> getAllById(List<Long> ids) {
        return getAlunosByIds(ids).stream().map(AlunoDto::new).toList();
    }

    @Override
    public DetailsAlunoDto getById(Long id) {
        Aluno aluno = getAlunoById(id);
        return aluno != null ? new DetailsAlunoDto(aluno) : null;
    }

    public Aluno getAlunoById(Long id) {
        return alunoRepository.findById(id).orElse(null);
    }

    public List<Aluno> getAlunosByIds(List<Long> ids) {
        List<Aluno> alunos = new ArrayList<>();
        for (Long id : ids) {
            alunoRepository
                    .findById(id)
                    .ifPresentOrElse(alunos::add, () -> logger.warn("Aluno não encontrado. ID: {}", id));
        }
        return alunos;
    }

    @Override
    @Transactional
    public AlunoDto save(CreateAlunoDto dto, MultipartFile imagem) throws Exception {
        if (dto == null) return null;
        Aluno aluno = new Aluno(dto);
        processImagem(imagem, aluno);
        addCursoToAluno(dto.idCurso(), aluno);
        return new AlunoDto(alunoRepository.save(aluno));
    }

    @Override
    @Transactional
    public void update(UpdateAlunoDto dto, MultipartFile imagem) throws Exception {
        if (dto == null) return;
        Aluno aluno = alunoRepository
                .findById(dto.id())
                .orElseThrow(() -> new Exception("Não foi possível encontrar o Aluno. ID: " + dto.id()));
        addCursoToAluno(dto.idCurso(), aluno);
        aluno.update(dto);
        processImagem(imagem, aluno);

        alunoRepository.save(aluno);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        alunoRepository.findById(id).ifPresent(a -> deleteImagemSilently(a.getImagemPerfil()));
        alunoRepository.deleteById(id);
    }

    private void addCursoToAluno(Long idCurso, Aluno aluno) throws Exception {
        Curso curso = cursoService.getCursoById(idCurso);
        if (curso == null) {
            throw new Exception("Não foi possível encontrar o curso. ID: " + idCurso);
        }
        aluno.setCurso(curso);
    }

    private void deleteImagemSilently(String path) {
        if (path == null) return;
        try {
            storageService.delete(path);
        } catch (IOException e) {
            logger.info("Não foi possível deletar a imagem: {}", path, e);
        }
    }

    private void processImagem(MultipartFile imagem, Aluno aluno) {
        if (imagem != null && !imagem.isEmpty()) {
            try {
                if (aluno.getImagemPerfil() != null) {
                    storageService.delete(aluno.getImagemPerfil());
                }
                String url = storageService.save(imagem);
                aluno.setImagemPerfil(url);
            } catch (IOException e) {
                logger.warn("Falha ao processar imagem do aluno id={}", aluno.getId(), e);
            }
        }
    }
}
