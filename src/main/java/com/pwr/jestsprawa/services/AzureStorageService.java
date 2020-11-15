package com.pwr.jestsprawa.services;

import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.pwr.jestsprawa.exceptions.UploadFileException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AzureStorageService {

    private final CloudBlobContainer cloudBlobContainer;

    public URI uploadFile(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        String fileName = FilenameUtils.getBaseName(file.getOriginalFilename());
        try {
            String newFileName = createFileName(fileName, extension);
            CloudBlockBlob blob = cloudBlobContainer.getBlockBlobReference(newFileName);
            while (blob.exists()) {
                blob = cloudBlobContainer.getBlockBlobReference(createFileName(fileName, extension));
            }
            blob.upload(file.getInputStream(), -1);
            return blob.getUri();
        } catch (URISyntaxException | StorageException | IOException e) {
            throw new UploadFileException();
        }
    }

    private String createFileName(String fileName, String extension) {
        return String.join(".", fileName, UUID.randomUUID().toString(), extension);
    }

}
