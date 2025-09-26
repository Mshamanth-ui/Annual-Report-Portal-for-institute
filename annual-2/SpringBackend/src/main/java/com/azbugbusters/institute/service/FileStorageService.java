package com.azbugbusters.institute.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileStorageService {
    Path getRootLocation();
    void init() throws IOException;
    String store(MultipartFile file) throws IOException;
    List<String> list() throws IOException;
    Resource loadAsResource(String filename) throws IOException;
}


