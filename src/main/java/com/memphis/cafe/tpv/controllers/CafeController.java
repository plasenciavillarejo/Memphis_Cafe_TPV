package com.memphis.cafe.tpv.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.memphis.cafe.tpv.entity.ListaBebidaAlmacenada;
import com.memphis.cafe.tpv.service.ICafeService;
import com.memphis.cafe.tpv.service.IListaBebidaAlmacenadaService;
import com.memphis.cafe.tpv.utilidades.Utilidades;

@Controller
@RequestMapping(value = "/Memphis_Cafe")
@SessionAttributes({"listaProductos", "paginaActual"})
public class CafeController {

	private Logger logAplicacion = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ICafeService cafeService;

	@Autowired
	private Utilidades utilidades;

	@Autowired
	private IListaBebidaAlmacenadaService bebidaAlmacenadaService;
	
	
	@GetMapping(value = "/cafe/{nombreCafe}")
	public String aniadirProductoCafe(@ModelAttribute("listaProductos") List<ListaBebidaAlmacenada> bebidaAlmacenada,
			@ModelAttribute("paginaActual") String VALORPAGINAACTUAL, Model model, @PathVariable(name = "nombreCafe") String nombreCafe) {
		
		model.addAttribute("listaCafes", cafeService.listaCafes());
		String precioCafe = cafeService.precioCafe(nombreCafe);		
				
		if (bebidaAlmacenada.isEmpty()) {
			ListaBebidaAlmacenada b = new ListaBebidaAlmacenada();
			b.setPrecio(precioCafe);
			b.setNombreBebida(nombreCafe);
			bebidaAlmacenadaService.guardarBebida(b);
			
			bebidaAlmacenada = bebidaAlmacenadaService.listaBebidaAlmacenada();
			
			// Si ya hay algo en sesión se procede a verificar que es lo que se ha recibido
			// y si existe en la tabla de bebidas existentes.
		} else {
			boolean encontrado = false;
			for (ListaBebidaAlmacenada b: bebidaAlmacenada) {
				String resultadoString = "";
				if (b.getNombreBebida().equalsIgnoreCase(nombreCafe)) {
					// Sumo el nuevo valor
					double resultado = Double.parseDouble(precioCafe.replace(',', '.')) + Double.parseDouble(b.getPrecio().replace(',', '.'));
					
					// Función para redondear decimales
					resultadoString = String.valueOf(utilidades.redondearDecimales(resultado)).replace('.', ',');
					
					b.setPrecio(resultadoString);
					bebidaAlmacenadaService.guardarBebida(b);
					encontrado = true;
					break;
				}	
			}
			if(!encontrado) {
				ListaBebidaAlmacenada aniadirBebida = new ListaBebidaAlmacenada();
				aniadirBebida.setPrecio(precioCafe);
				aniadirBebida.setNombreBebida(nombreCafe);
				bebidaAlmacenadaService.guardarBebida(aniadirBebida);
			}
		}

		// Empezamos a guardar todo en sesión
		if (!bebidaAlmacenada.isEmpty()) {
			bebidaAlmacenada = bebidaAlmacenadaService.listaBebidaAlmacenada();
			model.addAttribute("listaProductos", bebidaAlmacenada);
		}
		return VALORPAGINAACTUAL;
	}
	
	
	// Restamos un precio a un objeto ya en la lista.
		@GetMapping("/validarObjetosActuales/{nombreCafe}/{precioCafe}")
		@ResponseBody
		public String validarObjetosDeSesion(@ModelAttribute("listaProductos") List<ListaBebidaAlmacenada> bebidaAlmacenada,
				@PathVariable("nombreCafe") String nombreCafe, @PathVariable("precioCafe") String precioCafe, Model model) {

			String resultadoString = "";
			for(ListaBebidaAlmacenada bebida: bebidaAlmacenada) {
				if(bebida.getNombreBebida().trim().equalsIgnoreCase(nombreCafe)) {
					
					// Buscamos el precio que vale el café para restarlo al precio total que hay en la cuenta.
					String buscarPrecioBBDD = cafeService.precioCafe(nombreCafe.trim());
					
					// Relizamos la resta
					resultadoString = utilidades.resta(precioCafe, buscarPrecioBBDD);
					
					// Actualizo el objeto 
					bebida.setPrecio(resultadoString);
					// Lo guardo nuevamente
					
					
					if(!resultadoString.equalsIgnoreCase("0")) {
						bebidaAlmacenadaService.guardarBebida(bebida);
					} else {
						bebidaAlmacenadaService.borrarBebida(bebida.getId());
					}
				}
			}
			model.addAttribute("listaProductos", bebidaAlmacenada);
			return resultadoString;
		}

		// Se encarga de que cuando exista un producto al pulsar en (+) sume su valor
		@GetMapping("/sumarObjetosActuales/{nombreCafe}/{precioCafe}")
		@ResponseBody
		public String sumarPrecioEnSesion(@ModelAttribute("listaProductos") List<ListaBebidaAlmacenada> bebidaAlmacenada,
				@PathVariable("nombreCafe") String nombreCafe, @PathVariable("precioCafe") String precioCafe, Model model) {
			
			String resultadoString = "";
			for(ListaBebidaAlmacenada bebida: bebidaAlmacenada) {
				if(bebida.getNombreBebida().trim().equalsIgnoreCase(nombreCafe)) {
					// Buscamos el precio que vale el café para restarlo al precio total que hay en la cuenta.
					String buscarPrecioBBDD = cafeService.precioCafe(nombreCafe.trim());
					// Relizamos la resta
					resultadoString = utilidades.suma(precioCafe, buscarPrecioBBDD);
					// Actualizo el objeto 
					bebida.setPrecio(resultadoString);
					// Lo guardo nuevamente
					bebidaAlmacenadaService.guardarBebida(bebida);
				}
			}
			model.addAttribute("listaProductos", bebidaAlmacenada);
			return resultadoString;
		}
		
	
}
