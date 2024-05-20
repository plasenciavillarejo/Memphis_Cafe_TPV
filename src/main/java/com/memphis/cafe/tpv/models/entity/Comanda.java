package com.memphis.cafe.tpv.models.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(schema = "Memphis_Cafe", name = "Comandas")
public class Comanda implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idComanda")
	private long idComanda;
	
	/* Al problema que tengo de Hibernate throws MultipleBagFetchException - cannot simultaneously fetch multiple bags
	tengo la siguiente solución -> Sí utilio List<?> en vez de Set<?> es agregar @Fetch(value = FetchMode.SUBSELECT) */
	
	@OneToMany(mappedBy = "comanda", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@Fetch(value =FetchMode.SUBSELECT)
	private Set<ListaComidaAlmacenada> listaComidaAlmacenada;

	@OneToMany(mappedBy = "comanda", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ListaBebidaAlmacenada> listaBebidaAlmacenada;

	public long getIdComanda() {
		return idComanda;
	}

	public void setIdComanda(long idComanda) {
		this.idComanda = idComanda;
	}

	public Set<ListaComidaAlmacenada> getListaComidaAlmacenada() {
		return listaComidaAlmacenada;
	}

	public void setListaComidaAlmacenada(Set<ListaComidaAlmacenada> listaComidaAlmacenada) {
		this.listaComidaAlmacenada = listaComidaAlmacenada;
	}

	public Set<ListaBebidaAlmacenada> getListaBebidaAlmacenada() {
		return listaBebidaAlmacenada;
	}

	public void setListaBebidaAlmacenada(Set<ListaBebidaAlmacenada> listaBebidaAlmacenada) {
		this.listaBebidaAlmacenada = listaBebidaAlmacenada;
	}
	
	private static final long serialVersionUID = 717537185073576803L;	
	
}
