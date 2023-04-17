package com.memphis.cafe.constantes.sesion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ConstantesSesion {

	public static List<String> LISTACONSTANTEBEBIDA = new ArrayList<>();
	public static List<String> LISTACONSTANTECOMIDA = new ArrayList<>();
	
	public static String CONSTANTEBEBIDA = "constanteBebida";
	public static String CONSTANTECOMIDA = "constanteComida";
	
	public void aniadirConstantesBebida (String bebida){
		List<String> listaBebida = new ArrayList<>();
		listaBebida.add(bebida);
	}
	
}
