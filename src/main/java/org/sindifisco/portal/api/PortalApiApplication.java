package org.sindifisco.portal.api;

import org.sindifisco.portal.api.config.cors.SindifiscoApiProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(SindifiscoApiProperty.class)
public class PortalApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortalApiApplication.class, args);
	}

}
