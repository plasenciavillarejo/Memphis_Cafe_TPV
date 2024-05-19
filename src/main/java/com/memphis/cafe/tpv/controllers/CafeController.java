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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.memphis.cafe.tpv.models.entity.Comanda;
import com.memphis.cafe.tpv.models.entity.ListaBebidaAlmacenada;
import com.memphis.cafe.tpv.models.service.ICafeService;
import com.memphis.cafe.tpv.models.service.IListaBebidaAlmacenadaService;
import com.memphis.cafe.tpv.utilidades.Utilidades;

@Controller
@RequestMapping(value = "/Memphis_Cafe")
@SessionAttributes({"listaProductos", "paginaActual", "comidaAlmacenada","comanda"})
public class CafeController {

	private Logger logAplicacion = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ICafeService cafeService;

	@Autowired
	private Utilidades utilidades;

	@Autowired
	private IListaBebidaAlmacenadaService bebidaAlmacenadaService;
	
	
	@GetMapping(value = "/cafe/{nombreCafe}")
	public String aniadirProductoCafe(Model model,
			@ModelAttribute("listaProductos") List<ListaBebidaAlmacenada> bebidaAlmacenada,
			@ModelAttribute("paginaActual") String VALORPAGINAACTUAL,
			@ModelAttribute("comanda") Comanda comanda,
			@PathVariable(name = "nombreCafe") String nombreCafe) {

				model.addAttribute("listaCafes", cafeService.listaCafes());
				String precioCafe = cafeService.precioCafe(nombreCafe);		
				
				if (bebidaAlmacenada.isEmpty()) {
					ListaBebidaAlmacenada b = new ListaBebidaAlmacenada();
					b.setPrecio(precioCafe);
					b.setNombreBebida(nombreCafe);
					b.setNombreTabla("cafes_carajillos_infusiones");
					b.setTotal(1);
					bebidaAlmacenadaService.guardarBebida(b);
					
					bebidaAlmacenada = bebidaAlmacenadaService.listaBebidaAlmacenada();
					
				} else {
					// Si ya hay algo en sesión se procede a verificar que es lo que se ha recibido y si existe en la tabla de bebidas existentes.
					boolean encontrado = false;
					for (ListaBebidaAlmacenada b: bebidaAlmacenada) {
						String resultadoString = "";
						if (b.getNombreBebida().equalsIgnoreCase(nombreCafe)) {
							
							// Sumo el nuevo valor
							double resultado = Double.parseDouble(precioCafe.replace(',', '.')) + Double.parseDouble(b.getPrecio().replace(',', '.'));
							
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
						aniadirBebida.setPrecio(precioCafe);
						aniadirBebida.setNombreBebida(nombreCafe);
						aniadirBebida.setNombreTabla("cafes_carajillos_infusiones");
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
				model.addAttribute("productoBebida", "cafes_carajillos_infusiones");
				
				// Agregamos los datos de la comanda
				comanda.setListaBebidaAlmacenada(bebidaAlmacenada);
		return VALORPAGINAACTUAL;
	}		
	
}
