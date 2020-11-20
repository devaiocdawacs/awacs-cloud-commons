package com.aiocdawacs.files.pdf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.aiocdawacs.files.pdf.service.FileSystemStorageService;
import com.aiocdawacs.files.pdf.service.StorageProperties;
import com.aiocdawacs.files.pdf.service.StorageService;

@Import(StorageProperties.class)
@Configuration
public class FileStorageConfig {
	
	@Bean
	public StorageService getStorageService(StorageProperties properties) {
		return new FileSystemStorageService(properties);
	}
}
