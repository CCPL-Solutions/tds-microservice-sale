package co.com.viveres.susy.microservicesale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = {"co.com.viveres.susy"})
public class MicroserviceSaleApplication {
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceSaleApplication.class, args);
	}

}
