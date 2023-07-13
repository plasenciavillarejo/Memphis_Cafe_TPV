package com.memphis.cafe.tpv.constantes.sesion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ConstantesSesion {

	public static List<String> LISTACONSTANTEPRECIOBEBIDA = new ArrayList<>();
	public static List<String> LISTACONSTANTEPRECIOCOMIDA = new ArrayList<>();
	
	public static List<String> LISTACONSTANTENOMBREBEBIDA = new ArrayList<>();
	public static List<String> LISTACONSTANTENOMBRECOMIDA = new ArrayList<>();
	
	
	public static String CONSTANTEPRECIOBEBIDA = "constanteBebida";
	public static String CONSTANTECPRECIOCOMIDA = "constanteComida";
	
	public static String CONSTANTENOMBREBEBIDA = "constanteNombreBebida";
	public static String CONSTANTENOMBRECOMIDA = "constanteNombreComida";
	
	
	public void aniadirConstantesBebida (String bebida){
		List<String> listaBebida = new ArrayList<>();
		listaBebida.add(bebida);
	}
	
}
