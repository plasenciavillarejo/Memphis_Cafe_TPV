package com.memphis.cafe.tpv.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.memphis.cafe.tpv.models.entity.Comanda;
import com.memphis.cafe.tpv.models.entity.Combinado;
import com.memphis.cafe.tpv.models.entity.DatosGuardados;
import com.memphis.cafe.tpv.models.entity.Historico;
import com.memphis.cafe.tpv.models.entity.ListaBebidaAlmacenada;
import com.memphis.cafe.tpv.models.entity.ListaComidaAlmacenada;
import com.memphis.cafe.tpv.models.entity.Lotes;
import com.memphis.cafe.tpv.models.service.ICafeService;
import com.memphis.cafe.tpv.models.service.ICarneService;
import com.memphis.cafe.tpv.models.service.IComandaService;
import com.memphis.cafe.tpv.models.service.ICombinadoService;
import com.memphis.cafe.tpv.models.service.IDesayunosService;
import com.memphis.cafe.tpv.models.service.IHistoricoService;
import com.memphis.cafe.tpv.models.service.IListaBebidaAlmacenadaService;
import com.memphis.cafe.tpv.models.service.IListaComidaAlmacenadaService;
import com.memphis.cafe.tpv.models.service.ILoteService;
import com.memphis.cafe.tpv.models.service.IPescadoService;
import com.memphis.cafe.tpv.models.service.IRacionService;
import com.memphis.cafe.tpv.models.service.IRefrescoService;
import com.memphis.cafe.tpv.models.service.IReposteriaService;
import com.memphis.cafe.tpv.utilidades.Utilidades;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/Memphis_Cafe")
@SessionAttributes({"listaProductos", "paginaActual", "comidaAlmacenada","comanda"})
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
	private static final String PAGINALISTADOMESAS = "/mesas/listadoMesas";
	
	private String VALORPAGINAACTUAL = "";
	
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
	private IListaBebidaAlmacenadaService bebidaAlmacenadaService;
	
	@Autowired
	private IListaComidaAlmacenadaService comidaAlmacenadaService;
	
	@Autowired
	private IHistoricoService historicoService;
	
	@Autowired
	private IComandaService comandaService;
	
	@GetMapping(value={"/inicio", "/"})
	public String inicio(Model model,
			HttpSession session,
			@RequestParam(value = "mesa", required = false) Integer mesa) {
		Map<Integer, String> localizarNombre = new HashMap<>();
		localizarNombre = utilidades.logosIniciales();
		model.addAttribute("logosPrincipales", localizarNombre.values());
		logAplicacion.info("Mostrando la carta para el Cafe Bar - Memphis");
		VALORPAGINAACTUAL = INICIO;
		
		// Se mete en sesión la listaBebida
		List<ListaBebidaAlmacenada> bebidaAlmacenada = bebidaAlmacenadaService.listaBebidaAlmacenada();
		model.addAttribute("listaProductos", bebidaAlmacenada);
		
		// Se mete en sesión la listComida
		List<ListaComidaAlmacenada> comidaAlmacenada = comidaAlmacenadaService.listaComidaAlmacenada();
		model.addAttribute("comidaAlmacenada", comidaAlmacenada);
		// Se mete en sesión las mesas
		model.addAttribute("listadoMesas", utilidades.listadoMesas());
		
		// Buscamos la comanda inicialmente para saber si ya la tenemos en sesión o no.
		Comanda comanda = null;
		if (mesa == null) {
		    comanda = new Comanda();
		    comanda.setIdComanda(0);
		    comanda.setListaBebidaAlmacenada(new HashSet<>());
		    comanda.setListaComidaAlmacenada(new HashSet<>());
		} else {
			comanda = comandaService.recuperarComanda(Long.valueOf(mesa));
			if(comanda.getListaComidaAlmacenada() == null) {
				comanda.setListaComidaAlmacenada(new HashSet<>());
			} 
			if(comanda.getListaBebidaAlmacenada()== null) {
				comanda.setListaBebidaAlmacenada(new HashSet<>());
			}
		}

		// Se mete en sesión la comanda con la mesa para asociar los productos a ella
		model.addAttribute("comanda", comanda);
		
		// Se mete en sesión la página actual
		model.addAttribute("paginaActual", VALORPAGINAACTUAL);
		return INICIO;
	}

	/**
	 * Función encargada de seleccionar la mesa para asociar la comandas
	 * @param model
	 * @param mesa
	 * @param session
	 * @return
	 */
	@GetMapping(value = "/mesaSeleccion")
	@ResponseBody
	public int mesaSeleccion(Model model,
			@RequestParam("mesa") int mesa,
			HttpSession session) {
		logAplicacion.info("Agregando en sesión la mesa: {}", mesa);
		model.addAttribute("mesaSeleccionada", mesa);
		
		// Se verifica si la comanda existe, en caso de que no exista, se crea.
		Comanda comanda = comandaService.recuperarComanda(Long.valueOf(mesa));
		if(comanda == null) {
			Comanda nuevaComanda = new Comanda();
			nuevaComanda.setIdComanda(mesa);
			try {
				comandaService.guardarComanda(nuevaComanda);
			} catch (Exception e) {
				logAplicacion.error("Error al almacenar la mesa a la comanda {}", e.getMessage(), e.getCause());
			}
		}
		// Agregamos la comanda a la sesión
		model.addAttribute("comanda", comanda);
		return mesa;
	}
	
	@GetMapping(value = "/redireccionComidas")
	public String redireccioneComidas(Model model, 
			@RequestParam(value = "valorBoton", required = false) String valorBoton) {
		logAplicacion.info("Entrando por la redireccionComidas");
		
		if(valorBoton.equalsIgnoreCase("Café")) {
 			model.addAttribute("listaCafes", cafeService.listaCafes());
 			VALORPAGINAACTUAL = PAGINACAFE;
 			model.addAttribute("paginaActual", VALORPAGINAACTUAL);
			return PAGINACAFE;
		} else if(valorBoton.equalsIgnoreCase("Desayunos")) {
			// Cargamos todos los desayunos
			model.addAttribute("listaDesayunos", desayunoService.listaDesayunos());
			VALORPAGINAACTUAL = PAGINADESAYUNOS;
 			model.addAttribute("paginaActual", VALORPAGINAACTUAL);
			return PAGINADESAYUNOS;
		} else if(valorBoton.equalsIgnoreCase("Bebidas")) {
			VALORPAGINAACTUAL = PAGINABEBIDAS;
			model.addAttribute("paginaActual", VALORPAGINAACTUAL);
			return PAGINABEBIDAS;
		} else if(valorBoton.equalsIgnoreCase("Comidas")) {
			// La pagina comidas se incluyen los botones dentro de la vista, no se llama a ninguna tabla en BBDD.
			VALORPAGINAACTUAL = PAGINACOMIDAS;
			model.addAttribute("paginaActual", VALORPAGINAACTUAL);
			return PAGINACOMIDAS;
		} else if(valorBoton.equalsIgnoreCase("Raciones")) {
			model.addAttribute("listaRaciones", racionService.listaRaciones());
			VALORPAGINAACTUAL = PAGINARACIONES;
			model.addAttribute("paginaActual", VALORPAGINAACTUAL);
			return PAGINARACIONES;
		} else if(valorBoton.equalsIgnoreCase("Carnes")) {
			model.addAttribute("listaCarnes", carneService.listaCarnes());
			VALORPAGINAACTUAL = PAGINACARNES;
			model.addAttribute("paginaActual", VALORPAGINAACTUAL);
			return PAGINACARNES;
		} else if(valorBoton.equalsIgnoreCase("Pescados")) {
			model.addAttribute("listaPescados", pescadoService.listaPescado());
			VALORPAGINAACTUAL = PAGINAPESCADOS;
			model.addAttribute("paginaActual", VALORPAGINAACTUAL);
			return PAGINAPESCADOS;
		} else if(valorBoton.equalsIgnoreCase("Combinados")) {

			List<Combinado> listaCombinados = combinadoService.listaCombinadosConLotes();
			List<Lotes> listaLotes = loteService.listaLotes();
			List<String> listaCompleta = new ArrayList<>();
			
			listaCombinados.forEach(list -> {
				listaCompleta.add(list.getNombre());
			});
			
			listaLotes.forEach(listLot -> {
				listaCompleta.add(listLot.getNombre());
			});
			
			model.addAttribute("listaCombinados", listaCompleta);
			VALORPAGINAACTUAL = PAGINACOMBINADOS;
			model.addAttribute("paginaActual", VALORPAGINAACTUAL);
			return PAGINACOMBINADOS;
		} else if(valorBoton.equalsIgnoreCase("Reposteria")) {
			model.addAttribute("listaReposteria", reposteriaService.listaReposteria());
			VALORPAGINAACTUAL = PAGINAREPOSTERIA;
			model.addAttribute("paginaActual", VALORPAGINAACTUAL);
			return PAGINAREPOSTERIA;
		} 
		
		return null;
	}
	
	// Almacenar la sesión dentro de un Jquery
	@GetMapping("/limpiarObjetosSesion/{elementosBebida}/{elementosComida}")
	@ResponseBody
	public String obtenerSesionAtributos(@ModelAttribute("listaProductos") List<ListaBebidaAlmacenada> bebidaAlmacenada,
			@ModelAttribute("comidaAlmacenada") List<ListaComidaAlmacenada> comidaAlmacenada, Model model,
			@PathVariable("elementosBebida") String elementosBebida,
			@PathVariable("elementosComida") String elementosComida ) {
		// Procedemos a borrar los atributos que existe en la sesión
		logAplicacion.info("Se procede a borrar los atributos que hay en sesión.");
		
		if(elementosBebida.equalsIgnoreCase("0")) {
			bebidaAlmacenada = new ArrayList<>();
			bebidaAlmacenadaService.borrarListaCompleta();
			model.addAttribute("listaProductos", bebidaAlmacenada);
		} 
		if(elementosComida.equalsIgnoreCase("0")) {
			comidaAlmacenada = new ArrayList<>();
			comidaAlmacenadaService.borrarListaCompletaComida();
			model.addAttribute("comidaAlmacenada", comidaAlmacenada);
		} else {
			bebidaAlmacenada = new ArrayList<>();
			comidaAlmacenada = new ArrayList<>();
			bebidaAlmacenadaService.borrarListaCompleta();
			comidaAlmacenadaService.borrarListaCompletaComida();
			model.addAttribute("listaProductos", bebidaAlmacenada);
			model.addAttribute("comidaAlmacenada", comidaAlmacenada);
		}
		return "OK";
	}
	
	// Almacenar la sesión dentro de un Jquery
	@GetMapping("/limpiarComandaEntera")
	@ResponseBody
	public String borrarComandaEntera(@ModelAttribute("listaProductos") List<ListaBebidaAlmacenada> bebidaAlmacenada,
			@ModelAttribute("comidaAlmacenada") List<ListaComidaAlmacenada> comidaAlmacenada, Model model) {
		// Procedemos a borrar los atributos que existe en la sesión
		logAplicacion.info("Se procede a borrar los atributos que hay en sesión.");
		bebidaAlmacenada = new ArrayList<>();
		comidaAlmacenada = new ArrayList<>();
		bebidaAlmacenadaService.borrarListaCompleta();
		comidaAlmacenadaService.borrarListaCompletaComida();
		model.addAttribute("listaProductos", bebidaAlmacenada);
		model.addAttribute("comidaAlmacenada", comidaAlmacenada);
		return "OK";
	}
	
	
	
	// Se encarga de que cuando exista un producto al pulsar en (+) sume su valor
	@GetMapping("/sumarPrecioComida/{nombreComida}/{precioComida}/{checked}/{tablaBBDD}")
	@ResponseBody
	public Map<String, Object> sumarPrecioEnSesion(@ModelAttribute("comidaAlmacenada") List<ListaComidaAlmacenada> comidaAlmacenada,
			@PathVariable("nombreComida") String nombreComida, 
			@PathVariable("precioComida") String precioComida,
			@PathVariable(value = "checked", required = false) boolean checked,
			@PathVariable(value = "tablaBBDD") String tablaBBDD,
			Model model) {

		String resultadoString = "";
		int totalIncrementado = -1;
		Map<String, Object> resultadoFinal = new HashMap<>();
		for (ListaComidaAlmacenada comida : comidaAlmacenada) {
			if (comida.getNombreComida().trim().equalsIgnoreCase(nombreComida)) {
				// Buscamos el precio que vale el café para restarlo al precio total que hay en
				// la cuenta.
				String buscarPrecioBBDD = "";

				buscarPrecioBBDD = utilidades.identificacionConsultas(nombreComida, tablaBBDD, checked);

				// Relizamos la resta
				resultadoString = utilidades.suma(precioComida, buscarPrecioBBDD);
				// Actualizo el objeto
				comida.setPrecio(resultadoString);
				totalIncrementado = utilidades.aumentarProductos(comida.getTotal());
				comida.setTotal(totalIncrementado);
				// Lo guardo nuevamente
				
				comidaAlmacenadaService.guardarComida(comida);
				resultadoFinal.put("aumentoPrecio", resultadoString);
				resultadoFinal.put("aumentoTotal", totalIncrementado);
			}
		}
		model.addAttribute("comidaAlmacenada", comidaAlmacenada);
		return resultadoFinal;
	}
	
	// Se encarga de que cuando exista un producto al pulsar en (-) restar su valor
	@GetMapping("/restarPrecioComida/{nombreComida}/{precioComida}/{checked}/{tablaBBDD}")
	@ResponseBody
	public Map<String, Object> restarPrecioEnSesion(@ModelAttribute("comidaAlmacenada") List<ListaComidaAlmacenada> comidaAlmacenada,
			@PathVariable("nombreComida") String nombreComida, 
			@PathVariable("precioComida") String precioComida,
			@PathVariable(value = "checked") boolean checked, 
			@PathVariable(value = "tablaBBDD") String tablaBBDD, Model model) {

		String resultadoString = "";
		int totalIncrementado = -1;
		Map<String, Object> resultadoFinal = new HashMap<>();
		for (ListaComidaAlmacenada comida : comidaAlmacenada) {
			if (comida.getNombreComida().trim().equalsIgnoreCase(nombreComida)) {
				// Buscamos el precio que vale el producto para restarlo al precio total que hay en la cuenta.
				String buscarPrecioBBDD = "";

				if (!tablaBBDD.equalsIgnoreCase("Desayunos")) {
					buscarPrecioBBDD = utilidades.identificacionConsultas(nombreComida, tablaBBDD, checked);

					// Relizamos la resta
					resultadoString = utilidades.resta(precioComida, buscarPrecioBBDD);
					// Actualizo el objeto
					comida.setPrecio(resultadoString);
					totalIncrementado = utilidades.aumentarProductos(comida.getTotal());
					comida.setTotal(totalIncrementado);
					
					if (!resultadoString.equalsIgnoreCase("0")) {
						comidaAlmacenadaService.guardarComida(comida);
						resultadoFinal.put("aumentoPrecio", resultadoString);
						resultadoFinal.put("aumentoTotal", totalIncrementado);
					} else {
						comidaAlmacenadaService.borrarComida(comida.getId());
						resultadoFinal.put("aumentoPrecio", 0);
						resultadoFinal.put("aumentoTotal", 0);
					}
					model.addAttribute("comidaAlmacenada", comidaAlmacenada);
				} else {
					// Cuando se trata de un desayuno se borra directamente.
					comidaAlmacenadaService.borrarComida(comida.getId());
					resultadoFinal.put("aumentoPrecio", 0);
					resultadoFinal.put("aumentoTotal", 0);
				}
				// Lo guardo nuevamente

			}
		}
		return resultadoFinal;
	}
	
	// Se encarga de que cuando exista un producto al pulsar en (+) sume su valor bebida
	@GetMapping("/sumarPrecioBebida/{nombreBebida}/{precioBebida}/{checked}/{tablaBBDD}")
	@ResponseBody
	public Map<String, Object> sumarPrecioBebidaEnSesion(@ModelAttribute("listaProductos") List<ListaBebidaAlmacenada> bebidaAlmacenada,
			@PathVariable("nombreBebida") String nombreBebida, 
			@PathVariable("precioBebida") String precioBebida,
			@PathVariable(value = "checked") boolean checked,
			@ModelAttribute("comanda") Comanda comanda,
			@PathVariable(value = "tablaBBDD") String tablaBBDD, Model model) {

		String resultadoString = "";
		int totalIncrementado = -1;
		Map<String, Object> resultadoFinal = new HashMap<>();
		for (ListaBebidaAlmacenada bebida : bebidaAlmacenada) {
			if (bebida.getNombreBebida().trim().equalsIgnoreCase(nombreBebida)) {
				// Buscamos el precio que vale el café para restarlo al precio total que hay en la cuenta.
				String buscarPrecioBBDD = utilidades.identificacionConsultas(nombreBebida,tablaBBDD, false);
				// Relizamos la resta
				resultadoString = utilidades.suma(precioBebida, buscarPrecioBBDD);
				// Actualizo el objeto
				bebida.setPrecio(resultadoString);
				totalIncrementado = utilidades.aumentarProductos(bebida.getTotal());
				bebida.setTotal(totalIncrementado);
				// Lo guardo nuevamente
				bebidaAlmacenadaService.guardarBebida(bebida);
				
				resultadoFinal.put("aumentoPrecio", resultadoString);
				resultadoFinal.put("aumentoTotal", totalIncrementado);
			}
		}
		model.addAttribute("listaProductos", bebidaAlmacenada);
		comanda.setListaBebidaAlmacenada(bebidaAlmacenada.stream().collect(Collectors.toSet()));
		return resultadoFinal;
	}
	
	// Se encarga de que cuando exista un producto al pulsar en (-) reste su valor bebida
	@GetMapping("/restarPrecioBebida/{nombreBebida}/{precioBebida}/{tablaBBDD}")
	@ResponseBody
	public Map<String, Object> restarPrecioBebidaEnSesion(@ModelAttribute("listaProductos") List<ListaBebidaAlmacenada> bebidaAlmacenada,
			@PathVariable("nombreBebida") String nombreBebida, 
			@PathVariable("precioBebida") String precioBebida, 
			@ModelAttribute("comanda") Comanda comanda,
			@PathVariable(value = "tablaBBDD") String tablaBBDD, Model model) {

		String resultadoString = "";
		int restarIncrementado = -1;
		Map<String, Object> resultadoFinal = new HashMap<>();
		for (ListaBebidaAlmacenada bebida : bebidaAlmacenada) {
			if (bebida.getNombreBebida().trim().equalsIgnoreCase(nombreBebida)) {
				// Cuando se trata de los vinos al tener un checked de 'Copa o Botella' mejor borrar todo de una vez.
				if(!tablaBBDD.equalsIgnoreCase("Vinos")) {
				// Buscamos el precio que vale el producto para restarlo al precio total que hay en la cuenta.
				String buscarPrecioBBDD = utilidades.identificacionConsultas(nombreBebida,tablaBBDD, false);

				// Relizamos la resta
				resultadoString = utilidades.resta(precioBebida, buscarPrecioBBDD);

				// Actualizo el objeto
				bebida.setPrecio(resultadoString);
				// Lo guardo nuevamente
				
				// Restamos en 1 la cantidad de producto
				restarIncrementado = utilidades.disminuirProductos(bebida.getTotal());
				bebida.setTotal(restarIncrementado);
				
				if (!resultadoString.equalsIgnoreCase("0")) {
					bebidaAlmacenadaService.guardarBebida(bebida);
					resultadoFinal.put("aumentoPrecio", resultadoString);
					resultadoFinal.put("aumentoTotal", restarIncrementado);
				} else {
					bebidaAlmacenadaService.borrarBebida(bebida.getId());
					resultadoFinal.put("aumentoPrecio", 0);
					resultadoFinal.put("aumentoTotal", 0);
				}
				}else {
					// Cuando se trata de un vino se borra directamente.
					bebidaAlmacenadaService.borrarBebida(bebida.getId());
					resultadoFinal.put("aumentoPrecio", 0);
					resultadoFinal.put("aumentoTotal", 0);
				}
			}
		}
		model.addAttribute("listaProductos", bebidaAlmacenada);
		comanda.setListaBebidaAlmacenada(bebidaAlmacenada.stream().collect(Collectors.toSet()));
		return resultadoFinal;
	}

	@PostMapping("/guardarHistorico")
	@ResponseBody
	public void guardarCuenta(@RequestBody DatosGuardados datos) {
		
		List<String> listaBebida = datos.getBebidaAlmacenada();
	    List<String> listaComida = datos.getComidaAlmacenada();
		
		logAplicacion.info("Se procede almacenar la cuenta");
		
		Historico historico = new Historico();
		
		Date date = new Date();
		SimpleDateFormat formatoDia = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
		
		String fechaDia = formatoDia.format(date);
		String hora = formatoHora.format(date);
		
		
		historico.setDia(fechaDia);
		historico.setHora(hora);
		
		
		// Creamos un objeto json para almacenar la lista en BBDD.
		ObjectMapper objectMapper = new ObjectMapper();
		
		String formatoJsonListaBebida = "";
		String formatoJsonListaComida = "";
		try {
			formatoJsonListaBebida = objectMapper.writeValueAsString(listaBebida).replace("[", "").replace("]", "").replace("\"", "");
			formatoJsonListaComida = objectMapper.writeValueAsString(listaComida).replace("[", "").replace("]", "").replace("\"", "");
		}catch (Exception e) {
			logAplicacion.error("Ha sucedido un problema a la hora de transformar la lista en un objeto json.", e.getMessage(),e);
		}
		
		// ***** NOTA: Debo cambiar algunos parámetros como la mesa o el mesero por los que se indique desde la aplicación de PDA utilizando los roles de spring security. *****
		historico.setListaBebidasHistorico(formatoJsonListaBebida);
		historico.setListaComidasHistorico(formatoJsonListaComida);
		historico.setMesa(0);
		historico.setMesero("Jose Plasencia");
		historico.setCuenta(datos.getCuenta());
		
		historicoService.guardarCuenta(historico);
		
		logAplicacion.info("Se ha almacenado correctamente la mesa con el id {}");
		
		/*
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
		return ResponseEntity.ok()
                .headers(headers)
                .body("Cobro realizado correctamente");
        */
	}
	
	
	
}
;