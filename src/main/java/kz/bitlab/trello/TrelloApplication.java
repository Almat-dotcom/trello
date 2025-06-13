package kz.bitlab.trello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan("kz.bitlab.trello.*")
public class TrelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrelloApplication.class, args);
	}

}
