package com.polex.dbperf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.polex.dbperf.repositories.mongo")
@EnableElasticsearchRepositories("com.polex.dbperf.repositories.elasticsearch")
@EnableJpaRepositories("com.polex.dbperf.repositories.postgres")
public class DbperfApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbperfApplication.class, args);
	}
}
