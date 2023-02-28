package com.memphis.cafe.tpv.controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.memphis.cafe.tpv.service.ICafeService;
import com.memphis.cafe.tpv.service.IDesayunosService;
import com.memphis.cafe.tpv.utilidades.Utilidades;

@Controller
@RequestMapping(value = "/Memphis_Cafe")
@SessionAttributes("memphisCafe")
public class MemphisController {

	private static final String INICIO = "inicio";
	
	// Paginas de retorno
	private static final String PAGINACAFE = "/cafe/paginaCafe";
	private static final String PAGINADESAYUNOS = "/desayuno/paginaDesayuno";
	private static final String PAGINABEBIDAS = "/bebida/paginaBebida";
	private static final String PAGINACOMIDAS = "/comida/paginaComida";
	private static final String PAGINARACIONES = "raciones";
	private static final String PAGINACARNES = "carnes";
	private static final String PAGINAPESCADOS = "pescados";
	private static final String PAGINAPOSTRES = "postres";
	private static final String PAGINACOMBINADOS = "combinados";
	private static final String PAGINAREPOSTERIA = "reposteria";
	private static final String PAGINAINFUSIONES = "infusiones";
	
	private Logger logAplicacion = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ICafeService cafeService;
	
	@Autowired
	private IDesayunosService desayunoService;
	
	
	@Autowired
	private Utilidades utilidades;
	
	@GetMapping({ "/inicio", "/" })
	public String inicio(Model model) {
		
		Map<Integer, String> localizarNombre = new HashMap<>();
		localizarNombre = utilidades.logosIniciales();
		
		model.addAttribute("logosPrincipales", localizarNombre.values());		
		logAplicacion.info("Mostrando la carta para el Cafe Bar - Memphis");
		
		return INICIO;
	}

	@GetMapping(value = "/redireccionComidas")
	public String redireccioneComidas(Model model, @RequestParam(value = "valorBoton", required = false) String valorBoton) {
		logAplicacion.info("Entrando por la redireccionComidas");
		
		if(valorBoton.equalsIgnoreCase("Café")) {
 			model.addAttribute("listaCafes", cafeService.listaCafes());
			return PAGINACAFE;
		} else if(valorBoton.equalsIgnoreCase("Desayunos")) {
			model.addAttribute("listaDesayunos", desayunoService.listaDesayunos());
			return PAGINADESAYUNOS;
		} else if(valorBoton.equalsIgnoreCase("Bebidas")) {
			// La pagina bebidas se incluyen los botones dentro de la vista, no se llama a ninguna tabla en BBDD.
			return PAGINABEBIDAS;
		} else if(valorBoton.equalsIgnoreCase("Comidas")) {
			// La pagina comidas se incluyen los botones dentro de la vista, no se llama a ninguna tabla en BBDD.
			return PAGINACOMIDAS;
		}
		
		
		return null;
	}
	
	
	
}
