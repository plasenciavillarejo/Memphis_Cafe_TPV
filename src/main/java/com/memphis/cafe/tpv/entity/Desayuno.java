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
@Table(schema = "Memphis_Cafe", name = "Desayunos")
public class Desayuno implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotEmpty
	@Column(name = "nombre")
	private String nombre;

	@NotEmpty
	@Column(name = "precio_media")
	private String precio_media;

	@NotEmpty
	@Column(name = "precio_entera")
	private String precio_entera;

	public String getPrecio_media() {
		return precio_media;
	}

	public void setPrecio_media(String precio_media) {
		this.precio_media = precio_media;
	}

	public String getPrecio_entera() {
		return precio_entera;
	}

	public void setPrecio_entera(String precio_entera) {
		this.precio_entera = precio_entera;
	}

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

	
	private static final long serialVersionUID = -1394643322511769136L;

	
}
