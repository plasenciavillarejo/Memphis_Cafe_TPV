package com.memphis.cafe.tpv.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import com.memphis.cafe.tpv.entity.Combinado;
import com.memphis.cafe.tpv.entity.Lotes;
import com.memphis.cafe.tpv.service.ICafeService;
import com.memphis.cafe.tpv.service.ICarneService;
import com.memphis.cafe.tpv.service.ICombinadoService;
import com.memphis.cafe.tpv.service.IDesayunosService;
import com.memphis.cafe.tpv.service.ILoteService;
import com.memphis.cafe.tpv.service.IPescadoService;
import com.memphis.cafe.tpv.service.IRacionService;
import com.memphis.cafe.tpv.service.IReposteriaService;
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
	private static final String PAGINARACIONES = "/raciones/paginaRaciones";
	private static final String PAGINACARNES = "/carne/paginaCarne";
	private static final String PAGINAPESCADOS = "/pescado/paginaPescado";
	private static final String PAGINAPOSTRES = "postres";
	private static final String PAGINACOMBINADOS = "/combinado/paginaCombinado";
	private static final String PAGINAREPOSTERIA = "/reposteria/paginaReposteria";
	private static final String PAGINAINFUSIONES = "infusiones";
	
	private Logger logAplicacion = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ICafeService cafeService;
	
	@Autowired
	private IDesayunosService desayunoService;
	
	@Autowired
	private IRacionService racionService;
	
	@Autowired
	private Utilidades utilidades;
	
	@Autowired
	private ICarneService carneService;
	
	@Autowired
	private IPescadoService pescadoService;
	
	@Autowired
	private ICombinadoService combinadoService;
	
	@Autowired
	private ILoteService loteService;
	
	@Autowired
	private IReposteriaService reposteriaService;
	
	
	
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
		} else if(valorBoton.equalsIgnoreCase("Raciones")) {
			model.addAttribute("listaRaciones", racionService.listaRaciones());
			return PAGINARACIONES;
		} else if(valorBoton.equalsIgnoreCase("Carnes")) {
			model.addAttribute("listaCarnes", carneService.listaCarnes());
			return PAGINACARNES;
		} else if(valorBoton.equalsIgnoreCase("Pescados")) {
			model.addAttribute("listaPescados", pescadoService.listaPescado());
			return PAGINAPESCADOS;
		} else if(valorBoton.equalsIgnoreCase("Combinados")) {

			List<Combinado> listaCombinados = combinadoService.listaCombinadosConLotes();
			List<Lotes> listaLotes = loteService.listaLotes();
			List<String> listaCompleta = new ArrayList<>();
			
			for (Combinado c : listaCombinados) {
				listaCompleta.add(c.getNombre());
			}
			for (Lotes l : listaLotes) {
				listaCompleta.add(l.getNombre());
			}

			model.addAttribute("listaCombinados", listaCompleta);
			return PAGINACOMBINADOS;
		} else if(valorBoton.equalsIgnoreCase("Reposteria")) {
			model.addAttribute("listaReposteria", reposteriaService.listaReposteria());
			return PAGINAREPOSTERIA;
		} 
		
		return null;
	}
	
	
	
}
