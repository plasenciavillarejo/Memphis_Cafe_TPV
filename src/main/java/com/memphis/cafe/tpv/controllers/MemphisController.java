package com.memphis.cafe.tpv.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("memphisCafe")
public class MemphisController {

	private Logger logAplicacion = LoggerFactory.getLogger(this.getClass());

	@GetMapping({ "/inicio", "/" })
	public String inicio() {
		logAplicacion.info("Mostrando la carta para el Cafe Bar - Memphis");
		return "inicio";
	}

}
