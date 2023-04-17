package com.memphis.cafe.tpv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.*"})
public class MemphisCafeTpvApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemphisCafeTpvApplication.class, args);
	}

}
