package com.memphis.cafe.tpv.paginador;

import java.io.Serializable;

public class PageItem implements Serializable{

	private static final long serialVersionUID = 5030954472651257055L;

	private int numero;
	
	private boolean actual;

	public PageItem(int numero, boolean actual) {
		super();
		this.numero = numero;
		this.actual = actual;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean isActual() {
		return actual;
	}

	public void setActual(boolean actual) {
		this.actual = actual;
	}
	
	
	
}
