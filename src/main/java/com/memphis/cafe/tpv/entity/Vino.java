package com.memphis.cafe.tpv.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(schema = "Memphis_Cafe", name = "Vinos")
public class Vino {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotEmpty
	@Column(name = "nombre")
	private String nombre;

	@NotEmpty
	@Column(name = "precio_copa")
	private String precioCopa;

	@NotEmpty
	@Column(name = "precio_botella")
	private String precioBotella;

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

	public String getPrecioCopa() {
		return precioCopa;
	}

	public void setPrecioCopa(String precioCopa) {
		this.precioCopa = precioCopa;
	}

	public String getPrecioBotella() {
		return precioBotella;
	}

	public void setPrecioBotella(String precioBotella) {
		this.precioBotella = precioBotella;
	}

}
