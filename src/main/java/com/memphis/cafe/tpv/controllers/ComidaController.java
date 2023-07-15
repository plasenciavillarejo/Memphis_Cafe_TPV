package com.memphis.cafe.tpv.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.memphis.cafe.tpv.entity.ListaBebidaAlmacenada;
import com.memphis.cafe.tpv.entity.ListaComidaAlmacenada;
import com.memphis.cafe.tpv.service.IComidasService;
import com.memphis.cafe.tpv.service.IListaComidaAlmacenadaService;
import com.memphis.cafe.tpv.utilidades.Utilidades;

@Controller
@RequestMapping(value = "/Memphis_Cafe")
@SessionAttributes({ "listaProductos", "paginaActual", "comidaAlmacenada" })
public class ComidaController {

	// Paginas de retorno
	private static final String PAGINAROSCAS = "/comidas/roscas/paginaRoscas";
	private static final String PAGINAENSALADAS = "/comidas/ensaladas/paginaEnsaladas";
	private static final String PAGINAPIZZAS = "/comidas/pizzas/paginaPizzas";
	private static final String PAGINAPERRITOS = "/comidas/perritos/paginaPerritos";
	private static final String PAGINAHAMBURGUESAS = "/comidas/hamburguesas/paginaHamburguesas";
	private static final String PAGINABOCADILLOS = "/comidas/bocadillos/paginaBocadillos";
	private static final String PAGINAALPARGATAS = "/comidas/alpargatas/paginaAlpargatas";
	private static final String PAGINASANDWICH = "/comidas/sandwich/paginaSandwich";

	private Logger logAplicacion = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IComidasService comidaService;

	@Autowired
	private Utilidades utilidades;

	@Autowired
	private IListaComidaAlmacenadaService comidaAlmacenadaService;

	@GetMapping(value = "/comidas")
	public String comidas(@ModelAttribute("listaProductos") List<ListaBebidaAlmacenada> bebidaAlmacenada,
			@ModelAttribute("paginaActual") String VALORPAGINAACTUAL,
			@ModelAttribute("comidaAlmacenada") List<ListaComidaAlmacenada> comidaAlmacenada, Model model,
			@RequestParam(value = "valorBoton", required = false) String valorBoton) {
		logAplicacion.info("Entrando por la redireccionComidas");

		if (valorBoton.equalsIgnoreCase("Roscas")) {
//			model.addAttribute("listaCafes", comidaService.listaRoscas());
			model.addAttribute("listaRoscas", "1");
			VALORPAGINAACTUAL = PAGINAROSCAS;
			model.addAttribute("paginaActual", VALORPAGINAACTUAL);
			return PAGINAROSCAS;
		} else if (valorBoton.equalsIgnoreCase("Ensaladas")) {
			// Cargamos todos los desayunos
			model.addAttribute("listaEnsaladas", "2");
			VALORPAGINAACTUAL = PAGINAENSALADAS;
			model.addAttribute("paginaActual", VALORPAGINAACTUAL);
			return PAGINAENSALADAS;
		} else if (valorBoton.equalsIgnoreCase("Pizzas")) {
			VALORPAGINAACTUAL = PAGINAPIZZAS;
			model.addAttribute("paginaActual", VALORPAGINAACTUAL);
			return PAGINAPIZZAS;
		} else if (valorBoton.equalsIgnoreCase("Perritos")) {
			// La pagina comidas se incluyen los botones dentro de la vista, no se llama a
			// ninguna tabla en BBDD.
			VALORPAGINAACTUAL = PAGINAPERRITOS;
			model.addAttribute("paginaActual", VALORPAGINAACTUAL);
			return PAGINAPERRITOS;
		} else if (valorBoton.equalsIgnoreCase("Hamburguesas")) {
			model.addAttribute("listaHamburguesas", "5");
			VALORPAGINAACTUAL = PAGINAHAMBURGUESAS;
			model.addAttribute("paginaActual", VALORPAGINAACTUAL);
			return PAGINAHAMBURGUESAS;
		} else if (valorBoton.equalsIgnoreCase("Bocadillos")) {
			model.addAttribute("listaBocadillos", "6");
			VALORPAGINAACTUAL = PAGINABOCADILLOS;
			model.addAttribute("paginaActual", VALORPAGINAACTUAL);
			return PAGINABOCADILLOS;
		} else if (valorBoton.equalsIgnoreCase("Alpargatas")) {
			model.addAttribute("listaAlpargatas", "7");
			VALORPAGINAACTUAL = PAGINAALPARGATAS;
			model.addAttribute("paginaActual", VALORPAGINAACTUAL);
			return PAGINAALPARGATAS;
		} else if (valorBoton.equalsIgnoreCase("Sandwich")) {
			model.addAttribute("listaSandwich", "8");
			VALORPAGINAACTUAL = PAGINASANDWICH;
			model.addAttribute("paginaActual", VALORPAGINAACTUAL);
			return PAGINASANDWICH;
		}

		return null;
	}

}
