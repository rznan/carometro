package com.rznan.lab.engsw.carometro.storage;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IStorageService {

    String uploadFile(MultipartFile file) throws Exception;
     void deleteFile (String path) throws IOException;

}
