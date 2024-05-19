package com.memphis.cafe.tpv.models.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(schema = "Memphis_Cafe", name = "Comandas")
public class Comanda implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idComanda")
	private long idComanda;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	private List<ListaComidaAlmacenada> listaComidaAlmacenada;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	private List<ListaBebidaAlmacenada> listaBebidaAlmacenada;

	public long getIdComanda() {
		return idComanda;
	}

	public void setIdComanda(long idComanda) {
		this.idComanda = idComanda;
	}

	public List<ListaComidaAlmacenada> getListaComidaAlmacenada() {
		return listaComidaAlmacenada;
	}

	public void setListaComidaAlmacenada(List<ListaComidaAlmacenada> listaComidaAlmacenada) {
		this.listaComidaAlmacenada = listaComidaAlmacenada;
	}

	public List<ListaBebidaAlmacenada> getListaBebidaAlmacenada() {
		return listaBebidaAlmacenada;
	}

	public void setListaBebidaAlmacenada(List<ListaBebidaAlmacenada> listaBebidaAlmacenada) {
		this.listaBebidaAlmacenada = listaBebidaAlmacenada;
	}
	
	private static final long serialVersionUID = 717537185073576803L;	
	
}
