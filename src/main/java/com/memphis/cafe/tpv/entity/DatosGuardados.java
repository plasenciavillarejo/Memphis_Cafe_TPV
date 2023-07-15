package com.memphis.cafe.tpv.entity;

import java.io.Serializable;
import java.util.List;

/* Clase encargada de almacenar los json cuando se procede a cobrar la comanda para la insercción de los datos en BBDD. 
  Se a tenido que crear este objeto para poder enviar los datos desde jquery hacia el back-end  de la siguiente forma:
	data: JSON.stringify(
			{
				bebidaAlmacenada: listaBebida,
				comidaAlmacenada: listaComida,
				cuenta: cuenta
			}
		)
*/
public class DatosGuardados implements Serializable{
	
	private static final long serialVersionUID = 5308426494188013784L;

	private List<String> bebidaAlmacenada;
	
	private List<String> comidaAlmacenada;

	private String cuenta;
	
	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public List<String> getBebidaAlmacenada() {
		return bebidaAlmacenada;
	}

	public void setBebidaAlmacenada(List<String> bebidaAlmacenada) {
		this.bebidaAlmacenada = bebidaAlmacenada;
	}

	public List<String> getComidaAlmacenada() {
		return comidaAlmacenada;
	}

	public void setComidaAlmacenada(List<String> comidaAlmacenada) {
		this.comidaAlmacenada = comidaAlmacenada;
	}
	
	
	
	
	
}
