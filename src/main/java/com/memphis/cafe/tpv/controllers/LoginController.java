package com.memphis.cafe.tpv.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/Memphis_Cafe")
public class LoginController {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	private static final String PAGINALOGIN = "/login/loginInicial";
	
	@GetMapping(value = "/login")
	public String loginInicial() {
		LOGGER.info("Redirigiendo a la pagina loginInicial");
		return PAGINALOGIN;
	}
	
}
