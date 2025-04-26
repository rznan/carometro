package com.rznan.lab.engsw.carometro.storage;


import com.rznan.lab.engsw.carometro.curso.CursoController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Lazy
public class ImageStorageServiceImpl implements IImageStorageService {

    @Value("${upload.dir}")
    private String uploadDir;

    @Override
    public String save(MultipartFile file) throws IOException {
        String nomeArquivo = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path pasta = Paths.get(uploadDir);
        Files.createDirectories(pasta);
        Path destino = pasta.resolve(nomeArquivo);
        file.transferTo(destino.toFile());
        return "/uploads/" + nomeArquivo;
    }

    @Override
    public void delete(String path) throws IOException {
        String nomeArquivo = path.replace("/uploads/", "");
        Path arquivo = Paths.get(uploadDir).resolve(nomeArquivo);
        Files.deleteIfExists(arquivo);
    }
}
