package com.rznan.lab.engsw.carometro.storage;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IImageStorageService {

    String save(MultipartFile file) throws IOException;
    void delete(String path) throws IOException;

}
