package ru.nokisev.skladio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = "ru.nokisev.skladio")
@EnableJpaRepositories("ru.nokisev.skladio.repositories")  // ← Важно!
@EntityScan("ru.nokisev.skladio.models")
public class SkladioApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkladioApplication.class, args);
	}

}
