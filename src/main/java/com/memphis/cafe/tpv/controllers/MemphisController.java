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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.memphis.cafe.constantes.sesion.ConstantesSesion;
import com.memphis.cafe.tpv.entity.Combinado;
import com.memphis.cafe.tpv.entity.ListaBebidaAlmacenada;
import com.memphis.cafe.tpv.entity.Lotes;
import com.memphis.cafe.tpv.service.ICafeService;
import com.memphis.cafe.tpv.service.ICarneService;
import com.memphis.cafe.tpv.service.ICombinadoService;
import com.memphis.cafe.tpv.service.IDesayunosService;
import com.memphis.cafe.tpv.service.IListaBebidaAlmacenadaService;
import com.memphis.cafe.tpv.service.ILoteService;
import com.memphis.cafe.tpv.service.IPescadoService;
import com.memphis.cafe.tpv.service.IRacionService;
import com.memphis.cafe.tpv.service.IRefrescoService;
import com.memphis.cafe.tpv.service.IReposteriaService;
import com.memphis.cafe.tpv.utilidades.Utilidades;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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
	
	private static String VALORPAGINAACTUAL = "";
	
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
	
	@Autowired
	private IRefrescoService refrescoService;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private IListaBebidaAlmacenadaService bebidaAlmacenadaService;
	
	
	@GetMapping({ "/inicio", "/" })
	public String inicio(Model model) {

		session = request.getSession();

		// Primero busco en la tabla para verificar que no hay nada en la cuenta
		// Procedo almacenar el producto en la tabla
		List<ListaBebidaAlmacenada> bebidaAlmacenada = bebidaAlmacenadaService.listaBebidaAlmacenada();
		
		for(ListaBebidaAlmacenada lista: bebidaAlmacenada) {
			ConstantesSesion.LISTACONSTANTEPRECIOBEBIDA.add(lista.getPrecio());
			ConstantesSesion.LISTACONSTANTENOMBREBEBIDA.add(lista.getNombreBebida());
			session.setAttribute(ConstantesSesion.CONSTANTEPRECIOBEBIDA,ConstantesSesion.LISTACONSTANTEPRECIOBEBIDA);
			session.setAttribute(ConstantesSesion.CONSTANTENOMBREBEBIDA,ConstantesSesion.LISTACONSTANTENOMBREBEBIDA);
		}
		
		Map<Integer, String> localizarNombre = new HashMap<>();
		localizarNombre = utilidades.logosIniciales();

		model.addAttribute("logosPrincipales", localizarNombre.values());
		logAplicacion.info("Mostrando la carta para el Cafe Bar - Memphis");

		// Metemos la constante de sesión
		if (session.getAttribute(ConstantesSesion.CONSTANTECPRECIOCOMIDA) == null
				&& session.getAttribute(ConstantesSesion.CONSTANTEPRECIOBEBIDA) == null) {
			// Cuando se accede por primera vez a la página inicial no debe de existir
			// ningún listado de productos
			session.setAttribute(ConstantesSesion.CONSTANTECPRECIOCOMIDA, null);
			session.setAttribute(ConstantesSesion.CONSTANTEPRECIOBEBIDA, null);
		} else if (session.getAttribute(ConstantesSesion.CONSTANTECPRECIOCOMIDA) != null) {
			model.addAttribute(ConstantesSesion.CONSTANTECPRECIOCOMIDA, ConstantesSesion.LISTACONSTANTEPRECIOCOMIDA);
		} else if (session.getAttribute(ConstantesSesion.CONSTANTEPRECIOBEBIDA) != null || !bebidaAlmacenada.isEmpty()) {
			
			model.addAttribute(ConstantesSesion.CONSTANTEPRECIOBEBIDA, ConstantesSesion.LISTACONSTANTEPRECIOBEBIDA);
			model.addAttribute(ConstantesSesion.CONSTANTENOMBREBEBIDA, ConstantesSesion.LISTACONSTANTENOMBREBEBIDA);
		}

		return INICIO;
	}

	@GetMapping(value = "/redireccionComidas")
	public String redireccioneComidas(Model model, @RequestParam(value = "valorBoton", required = false) String valorBoton) {
		logAplicacion.info("Entrando por la redireccionComidas");
		
		if(valorBoton.equalsIgnoreCase("Café")) {
			
			// Primero busco en la tabla para verificar que no hay nada en la cuenta
			// Procedo almacenar el producto en la tabla
			List<ListaBebidaAlmacenada> bebidaAlmacenada = bebidaAlmacenadaService.listaBebidaAlmacenada();
			
			if (bebidaAlmacenada.isEmpty() && session.getAttribute(ConstantesSesion.CONSTANTECPRECIOCOMIDA) == null
					&& session.getAttribute(ConstantesSesion.CONSTANTEPRECIOBEBIDA) == null) {
				// Cuando se accede por primera vez a la página inicial no debe de existir
				// ningún listado de productos
				session.getAttribute(ConstantesSesion.CONSTANTECPRECIOCOMIDA);
				session.getAttribute(ConstantesSesion.CONSTANTEPRECIOBEBIDA);
			} else if (session.getAttribute(ConstantesSesion.CONSTANTECPRECIOCOMIDA) != null) {
				model.addAttribute(ConstantesSesion.CONSTANTECPRECIOCOMIDA, ConstantesSesion.LISTACONSTANTEPRECIOCOMIDA);
			} else if (session.getAttribute(ConstantesSesion.CONSTANTEPRECIOBEBIDA) != null || !bebidaAlmacenada.isEmpty()) {
				model.addAttribute(ConstantesSesion.CONSTANTEPRECIOBEBIDA, ConstantesSesion.LISTACONSTANTEPRECIOBEBIDA);
				model.addAttribute(ConstantesSesion.CONSTANTENOMBREBEBIDA, ConstantesSesion.LISTACONSTANTENOMBREBEBIDA);
			}
 			model.addAttribute("listaCafes", cafeService.listaCafes());
 			VALORPAGINAACTUAL = PAGINACAFE;
			return PAGINACAFE;
		} else if(valorBoton.equalsIgnoreCase("Desayunos")) {
			model.addAttribute("listaDesayunos", desayunoService.listaDesayunos());
			VALORPAGINAACTUAL = PAGINADESAYUNOS;
			return PAGINADESAYUNOS;
		} else if(valorBoton.equalsIgnoreCase("Bebidas")) {
			// La pagina bebidas se incluyen los botones dentro de la vista, no se llama a ninguna tabla en BBDD.
			VALORPAGINAACTUAL = PAGINABEBIDAS;
			return PAGINABEBIDAS;
		} else if(valorBoton.equalsIgnoreCase("Comidas")) {
			// La pagina comidas se incluyen los botones dentro de la vista, no se llama a ninguna tabla en BBDD.
			VALORPAGINAACTUAL = PAGINACOMIDAS;
			return PAGINACOMIDAS;
		} else if(valorBoton.equalsIgnoreCase("Raciones")) {
			model.addAttribute("listaRaciones", racionService.listaRaciones());
			VALORPAGINAACTUAL = PAGINARACIONES;
			return PAGINARACIONES;
		} else if(valorBoton.equalsIgnoreCase("Carnes")) {
			model.addAttribute("listaCarnes", carneService.listaCarnes());
			VALORPAGINAACTUAL = PAGINACARNES;
			return PAGINACARNES;
		} else if(valorBoton.equalsIgnoreCase("Pescados")) {
			model.addAttribute("listaPescados", pescadoService.listaPescado());
			VALORPAGINAACTUAL = PAGINAPESCADOS;
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
			VALORPAGINAACTUAL = PAGINACOMBINADOS;
			return PAGINACOMBINADOS;
		} else if(valorBoton.equalsIgnoreCase("Reposteria")) {
			model.addAttribute("listaReposteria", reposteriaService.listaReposteria());
			VALORPAGINAACTUAL = PAGINAREPOSTERIA;
			return PAGINAREPOSTERIA;
		} 
		
		return null;
	}
	
	
	@GetMapping(value = "/bebidas")
	public String listaBebidas(Model model, @RequestParam(value = "valorBoton", required = false) String valorBoton) {
		String salidaPagina = "";
		
		if(valorBoton.equalsIgnoreCase("Refrescos")) {
 			model.addAttribute("listaRefrescos", refrescoService.listaRefrescos());
 			salidaPagina = "";
		}
		return salidaPagina;
	}
	
	@GetMapping(value = "/cafe/{nombreCafe}")
	public String aniadirProductoCafe(Model model, @PathVariable(name = "nombreCafe") String nombreCafe) {
		
		session = request.getSession();
		
		
		model.addAttribute("listaCafes", cafeService.listaCafes());
		String precioCafe = cafeService.precioCafe(nombreCafe);
		
		// Procedo almacenar el producto en la tabla
		List<ListaBebidaAlmacenada> bebidaAlmacenada = bebidaAlmacenadaService.listaBebidaAlmacenada();
		
		
		if (bebidaAlmacenada.isEmpty()) {
			ListaBebidaAlmacenada b = new ListaBebidaAlmacenada();
			b.setPrecio(precioCafe);
			b.setNombreBebida(nombreCafe);
			bebidaAlmacenadaService.guardarBebida(b);
			
			// Almaceno el precio de la bebida
			ConstantesSesion.LISTACONSTANTEPRECIOBEBIDA.add(b.getPrecio());
			// ALmaceno el nombre de la bebida
			ConstantesSesion.LISTACONSTANTENOMBREBEBIDA.add(b.getNombreBebida());
			
			// Si ya hay algo en sesión se procede a verificar que es lo que se ha recibido
			// y si existe en la tabla de bebidas existentes.
		} else {
			for (ListaBebidaAlmacenada b : bebidaAlmacenada) {
				String resultadoString = "";
				if (b.getNombreBebida().equalsIgnoreCase(nombreCafe)) {
					// Sumo el nuevo valor
					double resultado = Double.parseDouble(precioCafe.replace(',', '.')) + Double.parseDouble(b.getPrecio().replace(',', '.'));
					
					// Función para redondear decimales
					resultadoString = String.valueOf(utilidades.redondearDecimales(resultado)).replace('.', ',');
					
					b.setPrecio(resultadoString);
					bebidaAlmacenadaService.guardarBebida(b);
				}
				break;
			}
		}
		
		// Empezamos a guardar todo en sesión
		if(!bebidaAlmacenada.isEmpty()) {
			utilidades.borrarObjetosListaBebidas(request);
		}
		
		for(ListaBebidaAlmacenada bebida: bebidaAlmacenada) {
			// Almaceno el precio de la bebida
			ConstantesSesion.LISTACONSTANTEPRECIOBEBIDA.add(bebida.getPrecio());
			// ALmaceno el nombre de la bebida
			ConstantesSesion.LISTACONSTANTENOMBREBEBIDA.add(bebida.getNombreBebida());
		}
		
		// Lo metemos en sesión
		session.setAttribute(ConstantesSesion.CONSTANTEPRECIOBEBIDA, ConstantesSesion.LISTACONSTANTEPRECIOBEBIDA);
		session.setAttribute(ConstantesSesion.CONSTANTENOMBREBEBIDA, ConstantesSesion.LISTACONSTANTENOMBREBEBIDA);
		
		// Pasamos los atributos a la vista
		model.addAttribute(ConstantesSesion.CONSTANTEPRECIOBEBIDA, ConstantesSesion.LISTACONSTANTEPRECIOBEBIDA);
		model.addAttribute(ConstantesSesion.CONSTANTENOMBREBEBIDA, ConstantesSesion.LISTACONSTANTENOMBREBEBIDA);
		
		if(session.getAttribute(ConstantesSesion.CONSTANTECPRECIOCOMIDA) == null) {
			model.addAttribute(ConstantesSesion.CONSTANTECPRECIOCOMIDA, null);
		}
		return VALORPAGINAACTUAL;
	}
	
	
	
	// Almacenar la sesión dentro de un Jquery
	@GetMapping("/limpiarObjetosSesion")
	public String obtenerSesionAtributos(Model model) {
		// Procedemos a borrar los atributos que existe en la sesión
		logAplicacion.info("Se procede a borrar los atributos que hay en sesión.");
		
		List<ListaBebidaAlmacenada> bebidaAlmacenada = bebidaAlmacenadaService.listaBebidaAlmacenada();
		
		for(ListaBebidaAlmacenada l: bebidaAlmacenada) {
			bebidaAlmacenadaService.borrarBebida(l.getId());
		}
		
		ConstantesSesion.LISTACONSTANTEPRECIOBEBIDA = new ArrayList<>();
		ConstantesSesion.LISTACONSTANTEPRECIOCOMIDA = new ArrayList<>();
		
		ConstantesSesion.LISTACONSTANTENOMBREBEBIDA = new ArrayList<>();
		ConstantesSesion.LISTACONSTANTENOMBRECOMIDA = new ArrayList<>();
		
		session.removeAttribute(ConstantesSesion.CONSTANTECPRECIOCOMIDA);
		session.removeAttribute(ConstantesSesion.CONSTANTEPRECIOBEBIDA);
		
		session.removeAttribute(ConstantesSesion.CONSTANTENOMBREBEBIDA);
		session.removeAttribute(ConstantesSesion.CONSTANTENOMBRECOMIDA);

		if (session.getAttribute(ConstantesSesion.CONSTANTECPRECIOCOMIDA) == null
				&& session.getAttribute(ConstantesSesion.CONSTANTEPRECIOBEBIDA) == null) {
			logAplicacion.info("Se ha borrado los atributos de sesión correctamente");
		}
		
		
		
		return VALORPAGINAACTUAL;
	}
	
	@GetMapping("/validarObjetosActuales/{borrarPrecio}")
	public String validarObjetosDeSesion(@PathVariable("borrarPrecio") String borrarPrecio, Model model) {

		if(ConstantesSesion.LISTACONSTANTEPRECIOBEBIDA != null) { 
			for(String bebidas: ConstantesSesion.LISTACONSTANTEPRECIOBEBIDA) {
				if(borrarPrecio.trim().equalsIgnoreCase(bebidas.trim())) {
					ConstantesSesion.LISTACONSTANTEPRECIOBEBIDA.remove(bebidas);
					break;
				}
			}
			// Validamos que si no hay más objetos en sesión se elimine todo
			if(ConstantesSesion.LISTACONSTANTEPRECIOBEBIDA.isEmpty()) {
				ConstantesSesion.LISTACONSTANTEPRECIOBEBIDA = new ArrayList<>();
				session.removeAttribute(ConstantesSesion.CONSTANTEPRECIOBEBIDA);
				// Con esto borramos el card de Bebida
				model.addAttribute(ConstantesSesion.CONSTANTEPRECIOBEBIDA, null);
			}else {
				model.addAttribute(ConstantesSesion.CONSTANTEPRECIOBEBIDA, ConstantesSesion.LISTACONSTANTEPRECIOBEBIDA);
			}
			
		} else if (ConstantesSesion.LISTACONSTANTEPRECIOCOMIDA.isEmpty()) {
			ConstantesSesion.LISTACONSTANTEPRECIOCOMIDA = new ArrayList<>();
			session.removeAttribute(ConstantesSesion.CONSTANTECPRECIOCOMIDA);
			// Con esto borramos el card de Comida
			model.addAttribute(ConstantesSesion.CONSTANTEPRECIOBEBIDA, null);
		}

		return VALORPAGINAACTUAL;
	}

	// Se encarga de que cuando exista un producto al pulsar en (+) sume su valor
	@GetMapping("/sumarObjetosActuales/{sumarPrecio}/{nombreCafe}")
	public String sumarPrecioEnSesion(@PathVariable("sumarPrecio") String sumarPrecio,
			@PathVariable("nombreCafe") String nombreCafe, Model model) {
		
	String resultadoString = "";
	String buscarPrecioBBDD = cafeService.precioCafeAumentaCantidad(sumarPrecio.trim());
	
		String valorFijo = sumarPrecio;
		if (ConstantesSesion.LISTACONSTANTEPRECIOBEBIDA != null) {
			for (String bebidas : ConstantesSesion.LISTACONSTANTEPRECIOBEBIDA) {
					// Elimino el valor antiguo
					ConstantesSesion.LISTACONSTANTEPRECIOBEBIDA.remove(bebidas);
					// Sumo el nuevo valor
					double resultado = Double.parseDouble(sumarPrecio.replace(',', '.')) + Double.parseDouble(buscarPrecioBBDD.replace(',', '.'));
					resultadoString = String.valueOf(resultado).replace('.', ',');
					ConstantesSesion.LISTACONSTANTEPRECIOBEBIDA.add(resultadoString);
					break;
			}
			
		}
		model.addAttribute("valorModificado", resultadoString);
		return VALORPAGINAACTUAL;
	}
	
	
	
}
