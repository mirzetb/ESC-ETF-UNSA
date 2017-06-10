package com.project.nwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@EnableDiscoveryClient
@SpringBootApplication
@EnableAutoConfiguration
public class EscUsersApplication {
	
	

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
	
	
	
	/*@Bean
	public CommandLineRunner demo(ApplicationContext ctx) {
		return (args) -> {
						
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
	}*/
	
}
