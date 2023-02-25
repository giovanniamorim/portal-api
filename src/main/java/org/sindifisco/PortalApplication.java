package org.sindifisco;

import org.sindifisco.config.FileStorageConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import org.sindifisco.config.property.ApiProperty;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableConfigurationProperties({ApiProperty.class, FileStorageConfig.class})
public class PortalApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PortalApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(PortalApplication.class, args);
	}



}
