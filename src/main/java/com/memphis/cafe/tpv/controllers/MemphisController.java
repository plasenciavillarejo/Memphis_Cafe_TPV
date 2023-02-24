package com.memphis.cafe.tpv.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.memphis.cafe.tpv.entity.LogosIniciales;
import com.memphis.cafe.tpv.service.IBebidasService;

@Controller
@SessionAttributes("memphisCafe")
public class MemphisController {

	private Logger logAplicacion = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IBebidasService bebidasService;
	
	@GetMapping({ "/inicio", "/" })
	public String inicio(Model model) {
		
		List<LogosIniciales> localizarNombre = new ArrayList<>();
		localizarNombre = bebidasService.buscarLogosPrincipales();
		
		for(LogosIniciales logo : localizarNombre) {
			if(!logo.getNombre().isEmpty()) {
				model.addAttribute("logosPrincipales", logo.getNombre());
			}
		}
		
		logAplicacion.info("Mostrando la carta para el Cafe Bar - Memphis");
		return "inicio";
	}

}
