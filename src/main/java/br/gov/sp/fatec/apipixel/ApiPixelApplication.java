package br.gov.sp.fatec.apipixel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin("*")
public class ApiPixelApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPixelApplication.class, args);
	}

}
