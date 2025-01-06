package com.jitu.lead_management.controller;

import java.io.File;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jitu.lead_management.entity.Image;
import com.jitu.lead_management.exception.LeadManagementException;
import com.jitu.lead_management.exception.UnknownErrorException;
import com.jitu.lead_management.model.UploadImageResponse;
import com.jitu.lead_management.service.ImageService;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

    // API to upload a file
    @PostMapping("/upload")
    public UploadImageResponse uploadFile(@RequestParam("image") MultipartFile file) {
        try {
            return imageService.storeImage(file);
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    // API to retrieve file metadata
    @GetMapping
    public Image getFileMetadata(@RequestParam int id) {
        try {
            return imageService.getImageMetadata(id);
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    // API to download a file
    @GetMapping("/download/{imageName}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String imageName) {
        try {
            // Locate the file
            File file = imageService.getImage(imageName);
            Resource resource = new UrlResource(file.toURI());

            // Return the image with proper Content-Type
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(file.toPath())) // Dynamically set MIME
                                                                                             // type (e.g., image/jpeg)
                    .body(resource);
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

}
