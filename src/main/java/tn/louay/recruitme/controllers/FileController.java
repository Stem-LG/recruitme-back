package tn.louay.recruitme.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tn.louay.recruitme.dto.uploadFileResponseDTO;
import tn.louay.recruitme.entities.DBFile;
import tn.louay.recruitme.services.DBFileStorageService;

import java.util.Map;

@RestController
@RequestMapping("/file")
@CrossOrigin
public class FileController {

    @Autowired
    private DBFileStorageService dbFileStorageService;

    @PostMapping
    public uploadFileResponseDTO uploadFile(@RequestParam("file") MultipartFile file) {
        // Get current user ID from JWT token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        @SuppressWarnings("unchecked")
        Map<String, Object> claims = (Map<String, Object>) authentication.getDetails();
        int userId = (int) claims.get("id");

        // Store file with user ID
        DBFile dbFile = dbFileStorageService.storeFile(file, userId);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/file/")
                .path(dbFile.getId().toString())
                .toUriString();

        return new uploadFileResponseDTO(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @GetMapping("/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable int fileId) {

        System.out.println("fileId: " + fileId);

        // Load file from database
        DBFile dbFile = dbFileStorageService.getFile(fileId);

        System.out.println("dbFile: " + dbFile.getFileName());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }

}