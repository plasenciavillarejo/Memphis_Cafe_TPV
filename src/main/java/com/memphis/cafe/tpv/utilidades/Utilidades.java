package com.memphis.cafe.tpv.utilidades;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.memphis.cafe.constantes.sesion.ConstantesSesion;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Component
public class Utilidades {

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
		resultadoString = String.valueOf(resultado).replace('.', ',');
		return resultadoString;
	}
	
	public String suma(String precioCafe, String buscarPrecioBBDD ) {
		String resultadoString = "";
		double resultado = Double.parseDouble(precioCafe.replace(',', '.')) + Double.parseDouble(buscarPrecioBBDD.replace(',', '.'));
		resultadoString = String.valueOf(redondearDecimales(resultado)).replace('.', ',');
		return resultadoString;
	}
	
}
