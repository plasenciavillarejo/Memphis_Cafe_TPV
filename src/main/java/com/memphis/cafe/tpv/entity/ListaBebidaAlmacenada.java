package com.memphis.cafe.tpv.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(schema = "Memphis_Cafe", name = "ListaBebidaAlmacenada")
public class ListaBebidaAlmacenada {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotEmpty
	@Column(name = "nombreBebida")
	private String nombreBebida;

	@Column(name = "precio")
	private String precio;

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreBebida() {
		return nombreBebida;
	}

	public void setNombreBebida(String nombreBebida) {
		this.nombreBebida = nombreBebida;
	}

}
