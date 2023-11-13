package com.memphis.cafe.tpv.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping(value = "/Memphis_Cafe")
@SessionAttributes({"listaProductos", "paginaActual", "comidaAlmacenada"})
public class ComandaController {

	private static final Logger logAplicacion = LoggerFactory.getLogger(ComandaController.class);

	
	private static final String PAGINACOMANDAS = "/comandas/paginaComanda";
	private String VALORPAGINAACTUAL = "";
	
	@GetMapping(value = "/comandas")
	public String listarComanda(Model model,@ModelAttribute("paginaActual") String VALORPAGINAACTUAL) {
		
		VALORPAGINAACTUAL = PAGINACOMANDAS;
		model.addAttribute("paginaActual", VALORPAGINAACTUAL);
		
		return PAGINACOMANDAS;
	}
}
