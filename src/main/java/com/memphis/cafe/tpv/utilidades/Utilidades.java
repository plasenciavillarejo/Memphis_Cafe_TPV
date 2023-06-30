package com.memphis.cafe.tpv.utilidades;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.memphis.cafe.constantes.sesion.ConstantesSesion;
import com.memphis.cafe.tpv.service.ICafeService;
import com.memphis.cafe.tpv.service.ICervezasBarrilService;
import com.memphis.cafe.tpv.service.ICervezasService;
import com.memphis.cafe.tpv.service.IDesayunosService;
import com.memphis.cafe.tpv.service.IRefrescoService;
import com.memphis.cafe.tpv.service.IVinoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Component
public class Utilidades {

	@Autowired
	private IDesayunosService desayunoService;
	
	@Autowired
	private ICafeService cafeService;
	
	@Autowired
	private IRefrescoService refrescoService;
	
	@Autowired
	private ICervezasService cervezasService;
	
	@Autowired
	private ICervezasBarrilService cerverzasBarrilService;
	
	@Autowired
	private IVinoService vinoService;
	
	// Clase que carga el listado principal.
	public Map<Integer, String> logosIniciales() {
		Map<Integer, String> logos = new HashMap<>();
		logos.put(1, "Café");
		logos.put(2, "Desayunos");
		logos.put(3, "Bebidas");
		logos.put(4, "Comidas");
		logos.put(5, "Raciones");
		logos.put(6, "Carnes");
		logos.put(7, "Pescados");
		logos.put(8, "Postres");
		logos.put(9, "Combinados");
		logos.put(10, "Reposteria");
		return logos;
	}
	
	
	public void borrarObjetosListaBebidas(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ConstantesSesion.LISTACONSTANTEPRECIOBEBIDA = new ArrayList<>();
		ConstantesSesion.LISTACONSTANTEPRECIOCOMIDA = new ArrayList<>();
		ConstantesSesion.LISTACONSTANTENOMBREBEBIDA = new ArrayList<>();
		ConstantesSesion.LISTACONSTANTENOMBRECOMIDA = new ArrayList<>();
		session.removeAttribute(ConstantesSesion.CONSTANTEPRECIOBEBIDA);
		session.removeAttribute(ConstantesSesion.CONSTANTECPRECIOCOMIDA);
		session.removeAttribute(ConstantesSesion.CONSTANTENOMBREBEBIDA);
		session.removeAttribute(ConstantesSesion.CONSTANTENOMBRECOMIDA);
	}

	public String redondearDecimales(double precioProducto) {
		DecimalFormat formato = new DecimalFormat("#.##"); // Define el formato para tres decimales
		String numeroFormateado = formato.format(precioProducto); // Aplica el formato al número
		return numeroFormateado;
	}
	
	
	public String resta(String precioCafe, String buscarPrecioBBDD) {
		String resultadoString = "";
		double resultado = Double.parseDouble(precioCafe.replace(',', '.')) - Double.parseDouble(buscarPrecioBBDD.replace(',', '.'));
		resultadoString = String.valueOf(redondearDecimales(resultado)).replace('.', ',');
		return resultadoString;
	}
	
	public String suma(String precioCafe, String buscarPrecioBBDD ) {
		String resultadoString = "";
		double resultado = Double.parseDouble(precioCafe.replace(',', '.')) + Double.parseDouble(buscarPrecioBBDD.replace(',', '.'));
		resultadoString = String.valueOf(redondearDecimales(resultado)).replace('.', ',');
		return resultadoString;
	}
	
	// Función encargada de aumentar +1 en el valor
	public int aumentarProductos(int totalCantidad) {
		int count = ++totalCantidad;
		return count;
	}
	
	public int disminuirProductos(int totalCantidad) {
		int count = --totalCantidad;
		return count;
	}
	
	public String identificacionConsultas(String nombreProducto, String tablaIdentificacion, boolean checked) {
		String buscarPrecioBBDD = "";
		
		// Café
		if(tablaIdentificacion.equalsIgnoreCase("cafes_carajillos_infusiones")) {
			buscarPrecioBBDD =  cafeService.precioCafe(nombreProducto.trim());
		} else if(tablaIdentificacion.equalsIgnoreCase("Desayunos")) {
			if (!checked) {
				buscarPrecioBBDD = desayunoService.precioDesayunoMedia(nombreProducto);
			} else {
				buscarPrecioBBDD = desayunoService.precioDesayunoEntera(nombreProducto);
			}
		} else if(tablaIdentificacion.equalsIgnoreCase("Refrescos")) {
			buscarPrecioBBDD = refrescoService.precioRefresco(nombreProducto);
		} else if(tablaIdentificacion.equalsIgnoreCase("Cervezas")) {
			buscarPrecioBBDD = cervezasService.precioCervezas(nombreProducto);
		} else if(tablaIdentificacion.equalsIgnoreCase("Cervezas_Barril")) {
			buscarPrecioBBDD = cerverzasBarrilService.precioCervezasBarril(nombreProducto);
		} else if(tablaIdentificacion.equalsIgnoreCase("Vinos")) {
			if(checked) {
				buscarPrecioBBDD = vinoService.precioVino(nombreProducto);
			} else {
				buscarPrecioBBDD = vinoService.precioCopaVino(nombreProducto);
			}
		} else if(tablaIdentificacion.equalsIgnoreCase("7")) {
			
		} else if(tablaIdentificacion.equalsIgnoreCase("8")) {
			
		} else if(tablaIdentificacion.equalsIgnoreCase("9")) {
			
		} else if(tablaIdentificacion.equalsIgnoreCase("10")) {
			
		}
		
		return buscarPrecioBBDD;
	}
	
}
