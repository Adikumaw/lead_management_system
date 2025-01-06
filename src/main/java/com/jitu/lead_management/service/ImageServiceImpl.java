package com.jitu.lead_management.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jitu.lead_management.entity.Image;
import com.jitu.lead_management.exception.ErrorFetchingImageExtention;
import com.jitu.lead_management.exception.ErrorSavingImageException;
import com.jitu.lead_management.exception.ImageMetaDataNotFoundException;
import com.jitu.lead_management.exception.ImageNotFoundException;
import com.jitu.lead_management.exception.InvalidImageTypeException;
import com.jitu.lead_management.model.UploadImageResponse;
import com.jitu.lead_management.repository.ImageRepository;

@Service
public class ImageServiceImpl implements ImageService {

    @Value("${image.storage.path}")
    private String storagePath;
    @Value("${image.download.url}")
    private String downloadUrlPrefix;

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public UploadImageResponse storeImage(MultipartFile file) {
        // Validate the file type
        if (!isImageFile(file)) {
            throw new InvalidImageTypeException("Only image files are allowed.");
        }

        // Create storage directory if it doesn't exist
        File storageDir = new File(storagePath);
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }

        // Get the original file name
        String originalImageName = file.getOriginalFilename();
        // Extract file extension
        String imageExtension = getImageExtension(originalImageName);
        // Generate a unique file name
        String imageName = generateUniqueImageName(imageExtension);

        // Save the file locally
        String imagePath;
        try {
            imagePath = Paths.get(storagePath, imageName).toString();
            file.transferTo(new File(imagePath));
        } catch (Exception e) {
            throw new ErrorSavingImageException("An error occurred while saving the image.");
        }

        // Generate download URL
        String downloadUrl = downloadUrlPrefix + imageName;

        // Save metadata in the database
        Image image = new Image(imageName, imagePath, downloadUrl);
        imageRepository.save(image);

        return new UploadImageResponse(downloadUrl);
    }

    @Override
    public Image getImageMetadata(int id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new ImageMetaDataNotFoundException("Image metadata not found."));
    }

    @Override
    public File getImage(String imageName) {
        Path imagePath = Paths.get(storagePath, imageName);
        if (Files.exists(imagePath)) {
            return new File(imagePath.toString());
        }
        throw new ImageNotFoundException("Image not found.");
    }

    // ============================================================
    // Helper methods
    // ============================================================
    private boolean isImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null
                && (contentType.startsWith("image/") || contentType.equals("application/octet-stream"));
    }

    private String getImageExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf('.');
        if (lastIndex > 0) {
            return fileName.substring(lastIndex + 1).toLowerCase();
        }
        throw new ErrorFetchingImageExtention("Error fetching image extension.");
    }

    private String generateUniqueImageName(String fileExtension) {
        return java.util.UUID.randomUUID().toString() + "." + fileExtension;
    }

}
