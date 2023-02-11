package org.sindifisco;

import org.sindifisco.config.FileStorageConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import org.sindifisco.config.property.ApiProperty;

@SpringBootApplication
@EnableConfigurationProperties({ApiProperty.class, FileStorageConfig.class})
public class PortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortalApplication.class, args);
	}

}
