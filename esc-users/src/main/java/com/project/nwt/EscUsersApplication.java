package com.project.nwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.nwt.IRepository.IUserRepository;
import com.project.nwt.model.Department;
import com.project.nwt.model.Role;
import com.project.nwt.model.User;

@EnableDiscoveryClient
@SpringBootApplication
public class EscUsersApplication {
	
	//private static final Logger log = LoggerFactory.getLogger(EscUsersApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EscUsersApplication.class, args);
	}
	
	
	@RestController
	class ServiceInstanceRestController {

	    @Autowired
	    private DiscoveryClient discoveryClient;

	    @RequestMapping("/service-instances/{applicationName}")
	    public List<ServiceInstance> serviceInstancesByApplicationName(
	            @PathVariable String applicationName) {
	        return this.discoveryClient.getInstances(applicationName);
	    }
	}
	
	@Bean
	public CommandLineRunner demo(ApplicationContext ctx) {
		return (args) -> {
			/*// testing method save()
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
			log.info("");*/
			
			
			
			System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

		};
	}
	
	
	@RefreshScope
	@RestController
	class MessageRestController {

	    @Value("${message:Hello default}")
	    private String message;

	    @RequestMapping("/message")
	    String getMessage() {
	        return this.message;
	    }
	}
}
