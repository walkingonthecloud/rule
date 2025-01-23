package com.wsgc.rule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories("com.wsgc.rule.repository")
public class RuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RuleApplication.class, args);
	}

}
