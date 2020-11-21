package com.aiocdawacs.files.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aiocdawacs.files.service.FileSystemStorageService;
import com.aiocdawacs.files.service.StorageProperties;
import com.aiocdawacs.files.service.StorageService;

@EnableConfigurationProperties(StorageProperties.class)
@Configuration
public class FileStorageConfig {
	
	@Bean
	public StorageService getStorageService(StorageProperties properties) {
		return new FileSystemStorageService(properties);
	}
}
