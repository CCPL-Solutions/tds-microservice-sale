package co.com.viveres.susy.microservicesale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
@EntityScan(basePackages = {
		"co.com.viveres.susy.microservicesale.entity", 
		"co.com.viveres.susy.microservicecommons.entity"})
@EnableJpaRepositories(basePackages = {
    "co.com.viveres.susy.microservicesale.repository", 
	"co.com.viveres.susy.microservicecommons.repository"})
public class MicroserviceSaleApplication {
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceSaleApplication.class, args);
	}

}
