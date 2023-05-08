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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.memphis.cafe.tpv.entity.ListaBebidaAlmacenada;
import com.memphis.cafe.tpv.entity.ListaComidaAlmacenada;
import com.memphis.cafe.tpv.service.IDesayunosService;
import com.memphis.cafe.tpv.service.IListaComidaAlmacenadaService;
import com.memphis.cafe.tpv.utilidades.Utilidades;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/Memphis_Cafe")
@SessionAttributes({"listaProductos", "paginaActual", "comidaAlmacenada"})
public class DesayunosController {

	@Autowired
	private IListaComidaAlmacenadaService comidaAlmacenadaService;
	
	@Autowired
	private IDesayunosService desayunoService;
	
	@Autowired
	private Utilidades utilidades;
	
	@GetMapping(value = "/desayuno")
	//@ResponseBody 
	public String aniadirDesayno(@ModelAttribute("listaProductos") List<ListaBebidaAlmacenada> bebidaAlmacenada, 
			@ModelAttribute("paginaActual") String VALORPAGINAACTUAL,
			@ModelAttribute("comidaAlmacenada") List<ListaComidaAlmacenada> comidaAlmacenada,
			@RequestParam(value ="checked", required = false) boolean checked,
			Model model, HttpServletRequest request) {
		
		String nombre = request.getParameter("nombreDesayuno");
		
		String precioDesayuno = "";
		ListaComidaAlmacenada desayunoSolicitado = new ListaComidaAlmacenada();
		
		if(!checked) {
			precioDesayuno = desayunoService.precioDesayunoMedia(nombre);
		}else {
			precioDesayuno = desayunoService.precioDesayunoEntera(nombre);
		}
		
		
		if (comidaAlmacenada.isEmpty()) {
			ListaComidaAlmacenada b = new ListaComidaAlmacenada();
			b.setPrecio(precioDesayuno);
			b.setNombreComida(nombre);
			comidaAlmacenadaService.guardarComida(b);
			comidaAlmacenada = comidaAlmacenadaService.listaComidaAlmacenada();
			
			desayunoSolicitado = b;
			
			// Si ya hay algo en sesión se procede a verificar que es lo que se ha recibido
			// y si existe en la tabla de bebidas existentes.
		} else {
			boolean encontrado = false;
			for (ListaComidaAlmacenada b: comidaAlmacenada) {
				String resultadoString = "";
				if (b.getNombreComida().equalsIgnoreCase(nombre)) {
					// Sumo el nuevo valor
					double resultado = Double.parseDouble(precioDesayuno.replace(',', '.')) + Double.parseDouble(b.getPrecio().replace(',', '.'));
					
					// Función para redondear decimales
					resultadoString = String.valueOf(utilidades.redondearDecimales(resultado)).replace('.', ',');
					
					b.setPrecio(resultadoString);
					comidaAlmacenadaService.guardarComida(b);
					desayunoSolicitado = b;
					encontrado = true;
					break;
				}	
			}
			if(!encontrado) {
				ListaComidaAlmacenada aniadirComida = new ListaComidaAlmacenada();
				aniadirComida.setPrecio(precioDesayuno);
				aniadirComida.setNombreComida(nombre);
				comidaAlmacenadaService.guardarComida(aniadirComida);
				desayunoSolicitado = aniadirComida;
			}
		}
		// Empezamos a guardar todo en sesión
			
		if (!comidaAlmacenada.isEmpty()) {
			comidaAlmacenada = comidaAlmacenadaService.listaComidaAlmacenada();
			model.addAttribute("comidaAlmacenada", comidaAlmacenada);
			model.addAttribute("listaDesayunos", desayunoService.listaDesayunos());
		}
		return VALORPAGINAACTUAL;
	}
	

	
}
