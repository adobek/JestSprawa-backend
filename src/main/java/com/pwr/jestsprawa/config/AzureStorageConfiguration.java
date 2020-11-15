package com.pwr.jestsprawa.config;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

@Configuration
public class AzureStorageConfiguration {

    @Value("${azure.storage.connectionString}")
    private String connectionString;

    @Value("${azure.storage.containerName}")
    private String containerName;

    @Bean
    public CloudBlobContainer cloudBlobContainer() throws URISyntaxException, InvalidKeyException, StorageException {
        CloudStorageAccount cloudStorageAccount = CloudStorageAccount.parse(connectionString);
        CloudBlobClient cloudBlobClient = cloudStorageAccount.createCloudBlobClient();
        return cloudBlobClient.getContainerReference(containerName);
    }

}
