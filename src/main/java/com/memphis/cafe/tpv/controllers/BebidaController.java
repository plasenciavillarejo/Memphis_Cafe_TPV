package com.memphis.cafe.tpv.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.memphis.cafe.tpv.entity.ListaBebidaAlmacenada;
import com.memphis.cafe.tpv.entity.ListaComidaAlmacenada;
import com.memphis.cafe.tpv.service.ICervezasBarrilService;
import com.memphis.cafe.tpv.service.ICervezasService;
import com.memphis.cafe.tpv.service.IListaBebidaAlmacenadaService;
import com.memphis.cafe.tpv.service.IRefrescoService;
import com.memphis.cafe.tpv.utilidades.Utilidades;

@Controller
@RequestMapping(value = "/Memphis_Cafe")
@SessionAttributes({"listaProductos", "paginaActual", "comidaAlmacenada"})
public class BebidaController {

	@Autowired
	private IRefrescoService refrescoService;
	
	@Autowired
	private Utilidades utilidades;
	
	@Autowired
	private IListaBebidaAlmacenadaService bebidaAlmacenadaService;
	
	@Autowired
	private ICervezasService cervezaService;
	
	@Autowired
	private ICervezasBarrilService cervezaBarrilService;
	
	private static final String PAGINAREFRESCOS = "/bebida/refrescos/paginaRefrescos";
	private static final String PAGINACERVEZAS = "/bebida/cervezas/paginaCervezas";
	private static final String PAGINACERVEZASBARRIL = "/bebida/barril/paginaCervezasBarril";
	
	@GetMapping(value = "/refrescos")
	public String listadoRefrescos(@ModelAttribute("listaProductos") List<ListaBebidaAlmacenada> bebidaAlmacenada, 
			@ModelAttribute("paginaActual") String VALORPAGINAACTUAL,
			@ModelAttribute("comidaAlmacenada") List<ListaComidaAlmacenada> comidaAlmacenada,Model model) {
		
		VALORPAGINAACTUAL = PAGINAREFRESCOS;
		model.addAttribute("paginaActual", VALORPAGINAACTUAL);
		model.addAttribute("listaRefrescos", refrescoService.listaRefrescos());
		return PAGINAREFRESCOS;
	}
	
	@GetMapping(value = "/aniadirRefresco/{nombreRefresco}")
	public String aniadirRefresco(@ModelAttribute("listaProductos") List<ListaBebidaAlmacenada> bebidaAlmacenada, 
			@ModelAttribute("paginaActual") String VALORPAGINAACTUAL,
			@PathVariable(value= "nombreRefresco") String nombreRefresco, Model model) {

		model.addAttribute("listaRefrescos", refrescoService.listaRefrescos());
		String precioRefresco = refrescoService.precioRefresco(nombreRefresco);		
		
		if (bebidaAlmacenada.isEmpty()) {
			ListaBebidaAlmacenada b = new ListaBebidaAlmacenada();
			b.setPrecio(precioRefresco);
			b.setNombreBebida(nombreRefresco);
			b.setNombreTabla("Refrescos");
			b.setTotal(1);
			bebidaAlmacenadaService.guardarBebida(b);
			
			bebidaAlmacenada = bebidaAlmacenadaService.listaBebidaAlmacenada();
		} else {
			// Si ya hay algo en sesión se procede a verificar que es lo que se ha recibido y si existe en la tabla de bebidas existentes.
			boolean encontrado = false;
			for (ListaBebidaAlmacenada b: bebidaAlmacenada) {
				String resultadoString = "";
				if (b.getNombreBebida().equalsIgnoreCase(nombreRefresco)) {
					
					// Sumo el nuevo valor
					double resultado = Double.parseDouble(precioRefresco.replace(',', '.')) + Double.parseDouble(b.getPrecio().replace(',', '.'));
					
					// Función para redondear decimales
					resultadoString = String.valueOf(utilidades.redondearDecimales(resultado)).replace('.', ',');
					
					b.setPrecio(resultadoString);
					
					// Aumentamos en 1 la cantidad de producto
					int totalIncrementado = utilidades.aumentarProductos(b.getTotal());
					b.setTotal(totalIncrementado);
					
					bebidaAlmacenadaService.guardarBebida(b);
					
					encontrado = true;
					break;
				}	
			}
			if(!encontrado) {
				ListaBebidaAlmacenada aniadirBebida = new ListaBebidaAlmacenada();
				aniadirBebida.setPrecio(precioRefresco);
				aniadirBebida.setNombreBebida(nombreRefresco);
				aniadirBebida.setNombreTabla("Refrescos");
				// Aumentamos en 1 la cantidad de producto
				aniadirBebida.setTotal(1);
				bebidaAlmacenadaService.guardarBebida(aniadirBebida);
			}
		}
		// Empezamos a guardar todo en sesión
		if (!bebidaAlmacenada.isEmpty()) {
			bebidaAlmacenada = bebidaAlmacenadaService.listaBebidaAlmacenada();
			model.addAttribute("listaProductos", bebidaAlmacenada);
		}
		// Le indicamos la tabla de el producto para poder distinguir a la hora de añadir (+) o eliminar (-) un producto.
		model.addAttribute("productoBebida", "Refrescos");
		
		return VALORPAGINAACTUAL;
	}
	

	@GetMapping(value = "/cervezas")
	public String listadoCervezas(@ModelAttribute("listaProductos") List<ListaBebidaAlmacenada> bebidaAlmacenada, 
			@ModelAttribute("paginaActual") String VALORPAGINAACTUAL,
			Model model) {
		
		model.addAttribute("listaCervezas", cervezaService.listaCervezas());
		VALORPAGINAACTUAL = PAGINACERVEZAS;
		model.addAttribute("paginaActual", VALORPAGINAACTUAL);
		
		return PAGINACERVEZAS;
	}
	
	@GetMapping(value = "/aniadirCerveza/{nombreCerveza}")
	public String aniadirCerveza(@ModelAttribute("listaProductos") List<ListaBebidaAlmacenada> bebidaAlmacenada, 
			@ModelAttribute("paginaActual") String VALORPAGINAACTUAL,
			@PathVariable(value= "nombreCerveza") String nombreCerveza, Model model) {

		model.addAttribute("listaCervezas", cervezaService.listaCervezas());
		String precioCerveza = cervezaService.precioCervezas(nombreCerveza);		
		
		if (bebidaAlmacenada.isEmpty()) {
			ListaBebidaAlmacenada b = new ListaBebidaAlmacenada();
			b.setPrecio(precioCerveza);
			b.setNombreBebida(nombreCerveza);
			b.setNombreTabla("Cervezas");
			b.setTotal(1);
			bebidaAlmacenadaService.guardarBebida(b);
			
			bebidaAlmacenada = bebidaAlmacenadaService.listaBebidaAlmacenada();
		} else {
			// Si ya hay algo en sesión se procede a verificar que es lo que se ha recibido y si existe en la tabla de bebidas existentes.
			boolean encontrado = false;
			for (ListaBebidaAlmacenada b: bebidaAlmacenada) {
				String resultadoString = "";
				if (b.getNombreBebida().equalsIgnoreCase(nombreCerveza)) {
					
					// Sumo el nuevo valor
					double resultado = Double.parseDouble(precioCerveza.replace(',', '.')) + Double.parseDouble(b.getPrecio().replace(',', '.'));
					
					// Función para redondear decimales
					resultadoString = String.valueOf(utilidades.redondearDecimales(resultado)).replace('.', ',');
					
					b.setPrecio(resultadoString);
					
					// Aumentamos en 1 la cantidad de producto
					int totalIncrementado = utilidades.aumentarProductos(b.getTotal());
					b.setTotal(totalIncrementado);
					
					bebidaAlmacenadaService.guardarBebida(b);
					
					encontrado = true;
					break;
				}	
			}
			if(!encontrado) {
				ListaBebidaAlmacenada aniadirBebida = new ListaBebidaAlmacenada();
				aniadirBebida.setPrecio(precioCerveza);
				aniadirBebida.setNombreBebida(nombreCerveza);
				aniadirBebida.setNombreTabla("Cervezas");
				// Aumentamos en 1 la cantidad de producto
				aniadirBebida.setTotal(1);
				bebidaAlmacenadaService.guardarBebida(aniadirBebida);
			}
		}
		// Empezamos a guardar todo en sesión
		if (!bebidaAlmacenada.isEmpty()) {
			bebidaAlmacenada = bebidaAlmacenadaService.listaBebidaAlmacenada();
			model.addAttribute("listaProductos", bebidaAlmacenada);
		}
		// Le indicamos la tabla de el producto para poder distinguir a la hora de añadir (+) o eliminar (-) un producto.
		model.addAttribute("productoBebida", "Cervezas");
		
		return VALORPAGINAACTUAL;
	}
	
	
	@GetMapping(value = "/cervezasBarril")
	public String listadoCervezasBarril(@ModelAttribute("listaProductos") List<ListaBebidaAlmacenada> bebidaAlmacenada, 
			@ModelAttribute("paginaActual") String VALORPAGINAACTUAL,
			Model model) {
		
		model.addAttribute("listaCervezasBarril", cervezaBarrilService.listaCervezasBarril());
		VALORPAGINAACTUAL = PAGINACERVEZASBARRIL;
		model.addAttribute("paginaActual", VALORPAGINAACTUAL);
		
		return PAGINACERVEZASBARRIL;
	}
	
	
	@GetMapping(value = "/aniadirCervezaBarril/{nombreCervezaBarril}")
	public String aniadirCervezaBarril(@ModelAttribute("listaProductos") List<ListaBebidaAlmacenada> bebidaAlmacenada, 
			@ModelAttribute("paginaActual") String VALORPAGINAACTUAL,
			@PathVariable(value= "nombreCervezaBarril") String nombreCervezaBarril, Model model) {

		model.addAttribute("listaCervezasBarril", cervezaBarrilService.listaCervezasBarril());
		String precioCerveza = cervezaBarrilService.precioCervezasBarril(nombreCervezaBarril);		
		
		if (bebidaAlmacenada.isEmpty()) {
			ListaBebidaAlmacenada b = new ListaBebidaAlmacenada();
			b.setPrecio(precioCerveza);
			b.setNombreBebida(nombreCervezaBarril);
			b.setNombreTabla("Cervezas_Barril");
			b.setTotal(1);
			bebidaAlmacenadaService.guardarBebida(b);
			
			bebidaAlmacenada = bebidaAlmacenadaService.listaBebidaAlmacenada();
		} else {
			// Si ya hay algo en sesión se procede a verificar que es lo que se ha recibido y si existe en la tabla de bebidas existentes.
			boolean encontrado = false;
			for (ListaBebidaAlmacenada b: bebidaAlmacenada) {
				String resultadoString = "";
				if (b.getNombreBebida().equalsIgnoreCase(nombreCervezaBarril)) {
					
					// Sumo el nuevo valor
					double resultado = Double.parseDouble(precioCerveza.replace(',', '.')) + Double.parseDouble(b.getPrecio().replace(',', '.'));
					
					// Función para redondear decimales
					resultadoString = String.valueOf(utilidades.redondearDecimales(resultado)).replace('.', ',');
					
					b.setPrecio(resultadoString);
					
					// Aumentamos en 1 la cantidad de producto
					int totalIncrementado = utilidades.aumentarProductos(b.getTotal());
					b.setTotal(totalIncrementado);
					
					bebidaAlmacenadaService.guardarBebida(b);
					
					encontrado = true;
					break;
				}	
			}
			if(!encontrado) {
				ListaBebidaAlmacenada aniadirBebida = new ListaBebidaAlmacenada();
				aniadirBebida.setPrecio(precioCerveza);
				aniadirBebida.setNombreBebida(nombreCervezaBarril);
				aniadirBebida.setNombreTabla("Cervezas_Barril");
				// Aumentamos en 1 la cantidad de producto
				aniadirBebida.setTotal(1);
				bebidaAlmacenadaService.guardarBebida(aniadirBebida);
			}
		}
		// Empezamos a guardar todo en sesión
		if (!bebidaAlmacenada.isEmpty()) {
			bebidaAlmacenada = bebidaAlmacenadaService.listaBebidaAlmacenada();
			model.addAttribute("listaProductos", bebidaAlmacenada);
		}
		// Le indicamos la tabla de el producto para poder distinguir a la hora de añadir (+) o eliminar (-) un producto.
		model.addAttribute("productoBebida", "Cervezas_Barril");
		
		return VALORPAGINAACTUAL;
	}

	
}
