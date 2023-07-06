package com.memphis.cafe.tpv.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema = "Memphis_Cafe", name = "Historico")
public class Historico implements Serializable{

	private static final long serialVersionUID = 79643247244352877L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCuenta")
	private int idCuenta;
	
	@Column(name = "Mesa")
	private int mesa;
	
	@Column(name = "Bebidas")
	private String listaBebidasHistorico;
	
	@Column(name = "Comidas")
	private String listaComidasHistorico;
	
	@Column(name = "Dia")
	private String dia;
	
	@Column(name = "Hora")
	private String hora;
	
	@Column(name = "Mesero")
	private String mesero;

	public int getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}

	public int getMesa() {
		return mesa;
	}

	public void setMesa(int mesa) {
		this.mesa = mesa;
	}

	public String getListaBebidasHistorico() {
		return listaBebidasHistorico;
	}

	public void setListaBebidasHistorico(String listaBebidasHistorico) {
		this.listaBebidasHistorico = listaBebidasHistorico;
	}

	public String getListaComidasHistorico() {
		return listaComidasHistorico;
	}

	public void setListaComidasHistorico(String listaComidasHistorico) {
		this.listaComidasHistorico = listaComidasHistorico;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getMesero() {
		return mesero;
	}

	public void setMesero(String mesero) {
		this.mesero = mesero;
	}
	
	
	
}
