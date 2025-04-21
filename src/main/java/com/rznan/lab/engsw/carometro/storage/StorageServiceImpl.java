package com.rznan.lab.engsw.carometro.storage;


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
public class StorageServiceImpl implements IStorageService {
    @Value("${upload.dir}")
    private String uploadDir;
    @Override
    public String uploadFile(MultipartFile file) throws IOException {

        String nomeArquivo = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path pasta = Paths.get(uploadDir);
        Files.createDirectories(pasta);
        Path destino = pasta.resolve(nomeArquivo);
        file.transferTo(destino.toFile());
        return "/uploads/" + nomeArquivo;
    }

    @Override
    public void deleteFile(String path) throws IOException {
        if (path != null && path.startsWith("/uploads/")) {
            String nomeArquivo = path.replace("/uploads/", "");
            Path arquivo = Paths.get(uploadDir).resolve(nomeArquivo);
            Files.deleteIfExists(arquivo);
        }
    }
}
