package com.jitu.lead_management.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import com.jitu.lead_management.entity.Image;
import com.jitu.lead_management.model.UploadImageResponse;

public interface ImageService {
    UploadImageResponse storeImage(MultipartFile file);

    Image getImageMetadata(int id);

    File getImage(String fileName);
}
