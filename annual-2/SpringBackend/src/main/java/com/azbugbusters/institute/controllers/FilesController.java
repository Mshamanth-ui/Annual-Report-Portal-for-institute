package com.azbugbusters.institute.controllers;

import com.azbugbusters.institute.service.FileStorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping
public class FilesController {

    private final FileStorageService storageService;

    public FilesController(FileStorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/student/files")
    public String listFilesForStudent(Model model) throws IOException {
        model.addAttribute("files", storageService.list());
        return "modules/files-student";
    }

    @GetMapping("/files/download/{filename}")
    public ResponseEntity<Resource> download(@PathVariable String filename) throws IOException {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
            .body(file);
    }

    @GetMapping("/faculty/files")
    @PreAuthorize("hasRole('FACULTY') or hasRole('ADMIN')")
    public String listFilesForFaculty(Model model) throws IOException {
        model.addAttribute("files", storageService.list());
        return "modules/files-faculty";
    }

    @PostMapping("/faculty/files/upload")
    @PreAuthorize("hasRole('FACULTY') or hasRole('ADMIN')")
    public String upload(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        storageService.store(file);
        return "redirect:/faculty/files";
    }
}


