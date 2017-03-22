package com.project.nwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.project.nwt.IRepository.IUserRepository;
import com.project.nwt.model.Department;
import com.project.nwt.model.Role;
import com.project.nwt.model.User;


@SpringBootApplication
@EnableAutoConfiguration
public class EscUsersApplication {
	
	private static final Logger log = LoggerFactory.getLogger(EscUsersApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EscUsersApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(IUserRepository repository) {
		return (args) -> {
			// testing method save()
			//repository.save(new User("Neko", "Nekic2",1, "sifra", "nnekic","ddgrgdrg",//new Department("1", "AiE")
		    //		"nnekic@etf.unsa.ba", new Date(), "123456", "fhhdfh",1));//new Role("1", "Administrator")

			// testing method findAll()
			log.info("Svi korisnici:");
			log.info("-------------------------------");
			for (User u : repository.findAll()) {
				log.info(u.toString());
			}
			log.info("");

			// testing method findOne
			User user = repository.findOne(1L);
			log.info("Korisnik sa id-em:");
			log.info("--------------------------------");
			log.info(user.toString());
			log.info("");

		};
	}
}
