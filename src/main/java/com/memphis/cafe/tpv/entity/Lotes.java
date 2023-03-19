package com.memphis.cafe.tpv.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(schema = "Memphis_Cafe", name = "Lotes")
public class Lotes implements Serializable {

	private static final long serialVersionUID = 3979383841102859728L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotEmpty
	@Column(name = "nombre")
	private String nombre;

	@NotEmpty
	@Column(name = "refrescos")
	private String refrescos;

	@NotEmpty
	@Column(name = "precio_uno")
	private String precioUno;

	@NotEmpty
	@Column(name = "precio_dos")
	private String precioDos;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRefrescos() {
		return refrescos;
	}

	public void setRefrescos(String refrescos) {
		this.refrescos = refrescos;
	}

	public String getPrecioUno() {
		return precioUno;
	}

	public void setPrecioUno(String precioUno) {
		this.precioUno = precioUno;
	}

	public String getPrecioDos() {
		return precioDos;
	}

	public void setPrecioDos(String precioDos) {
		this.precioDos = precioDos;
	}

}
