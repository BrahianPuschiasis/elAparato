package com.elaparato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class ElaparatoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElaparatoApplication.class, args);
	}

	@Component
	public class DataInitializer implements CommandLineRunner {

		@Autowired
		private JdbcTemplate jdbcTemplate;

		@Autowired
		private ResourceLoader resourceLoader;

		@Override
		public void run(String... args) throws Exception {
			Resource resource = resourceLoader.getResource("classpath:data.sql");
			List<String> lines = Files.readAllLines(Paths.get(resource.getURI()));

			String script = lines.stream().collect(Collectors.joining("\n"));
			String[] statements = script.split(";");

			for (String statement : statements) {
				if (!statement.trim().isEmpty()) {
					jdbcTemplate.execute(statement.trim());
				}
			}
		}
	}
}
