package com.example.spp;

import com.example.spp.controllers.WebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@Import({WebConfiguration.class })
@PropertySource({"classpath:application.properties"})
public class SppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SppApplication.class, args);
	}
}
