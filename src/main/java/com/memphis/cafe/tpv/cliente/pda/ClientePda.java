package com.memphis.cafe.tpv.cliente.pda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
/*
@Component
public class ClientePda {

	 private static final String BASE_URL = "http://localhost:8081"; // URL base del proyecto B
	 
	 @Autowired
	 private final RestTemplate restTemplate;

	 public ClientePda(RestTemplate restTemplate) {
		 this.restTemplate = restTemplate;
	 }
	
	    public void consumeResourceFromProjectB() {
	        String endpointUrl = BASE_URL + "/api/resource"; // URL del recurso en el proyecto B

	        // Configurar encabezados, si es necesario
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Authorization", "Bearer your-access-token");

	        // Realizar la llamada HTTP GET al recurso del proyecto B
	        ResponseEntity<String> response = restTemplate.exchange(endpointUrl, HttpMethod.GET,
	                new HttpEntity<>(headers), String.class);

	        // Procesar la respuesta recibida
	        if (response.getStatusCode().is2xxSuccessful()) {
	            String responseBody = response.getBody();
	            // Realizar las operaciones necesarias con la respuesta
	            System.out.println("Respuesta del proyecto cliente PDA: " + responseBody);
	        } else {
	            System.out.println("Error al consumir el recurso del proyecto cliente pda");
	        }
	    }
	 
}
*/