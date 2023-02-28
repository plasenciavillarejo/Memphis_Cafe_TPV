package com.memphis.cafe.tpv.utilidades;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

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
		logos.put(11, "Infusiones");
		return logos;
	}
	
}
