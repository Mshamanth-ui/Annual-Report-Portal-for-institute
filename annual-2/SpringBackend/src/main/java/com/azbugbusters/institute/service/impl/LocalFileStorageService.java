package com.azbugbusters.institute.service.impl;

import com.azbugbusters.institute.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocalFileStorageService implements FileStorageService {

    private final Path rootLocation;

    public LocalFileStorageService(@Value("${app.storage.location:uploads}") String storageLocation) {
        this.rootLocation = Paths.get(storageLocation).toAbsolutePath().normalize();
    }

    @Override
    public Path getRootLocation() {
        return rootLocation;
    }

    @Override
    public void init() throws IOException {
        if (!Files.exists(rootLocation)) {
            Files.createDirectories(rootLocation);
        }
    }

    @Override
    public String store(MultipartFile file) throws IOException {
        init();
        String filename = file.getOriginalFilename();
        if (filename == null || filename.isBlank()) {
            throw new IOException("Invalid filename");
        }
        Path destination = rootLocation.resolve(Paths.get(filename)).normalize();
        if (!destination.startsWith(rootLocation)) {
            throw new IOException("Cannot store file outside root");
        }
        Files.copy(file.getInputStream(), destination);
        return filename;
    }

    @Override
    public List<String> list() throws IOException {
        init();
        try (var stream = Files.list(rootLocation)) {
            return stream
                .filter(Files::isRegularFile)
                .map(p -> p.getFileName().toString())
                .collect(Collectors.toList());
        }
    }

    @Override
    public Resource loadAsResource(String filename) throws IOException {
        Path file = rootLocation.resolve(filename).normalize();
        if (!file.startsWith(rootLocation) || !Files.exists(file)) {
            throw new IOException("File not found");
        }
        return new FileSystemResource(file);
    }
}


