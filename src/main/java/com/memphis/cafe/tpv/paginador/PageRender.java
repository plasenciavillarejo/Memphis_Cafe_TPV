package com.memphis.cafe.tpv.paginador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

// Vamos a utilizar los genéricos que nos proporciona Java Clase<T>, de está forma se podrá reutilizar en cualquier controlador si necesiad de tener parámetros fijos.
public class PageRender<T> {

	private String url;
	
	private Page<T> page;

	private int totalPaginas;
	
	private int numeroElementosPorPagina;
	
	private int paginaActual; // El valor por defecto lo obtiene de el @RequestParam (defaultValue="0")
	
	private List<PageItem> paginas;
	
	public PageRender(String url, Page<T> page) {
		super();
		this.url = url;
		this.page = page;
		this.paginas = new ArrayList<>();
		
		// el tamaño por pagina se obtiene de 'page.getSize()' que se está inicializando en el controlador HistoricoVentasController.java donde indicamos 'new PageRequest(page,10), donde 10 es -> page.getSize();
		numeroElementosPorPagina = page.getSize();
		totalPaginas = page.getTotalPages();
		
		// Como en el controlador parte desde 0, y nosotros mostramos la vista desde el 1, debemos sumarle '1' al valor recibido desde el controlador
		paginaActual = page.getNumber() + 1;
		
		// Ahora vamos a proceder a realizar la lógica de la vista para el paginador desde / hasta.
		
		int desde, hasta;
		
		/* 1.- Si el TotalPage <= al numElemtoPorPagina entonces deberá de mostrar todo el paginador*/
		if(totalPaginas <= numeroElementosPorPagina) {
			desde = 1;
			hasta = totalPaginas;
		} else {
			// Rango Inicial
			if(paginaActual <= numeroElementosPorPagina/2) {
				desde = 1;
				hasta = numeroElementosPorPagina;
			// Rango Final
			} else if(paginaActual >= (totalPaginas - (numeroElementosPorPagina/2))) {
				desde = totalPaginas - numeroElementosPorPagina - 1;
				hasta = numeroElementosPorPagina + 1;
				if(hasta > numeroElementosPorPagina) {
					hasta = numeroElementosPorPagina;
				}
			// Rango Intermedio
			} else {
				desde = paginaActual - numeroElementosPorPagina / 2;
				hasta = numeroElementosPorPagina;
			}
		}	
		
		// Procedemos a recorrer para rellenar las páginas
		
		for(int i=0; i < hasta; i++) {
			paginas.add(new PageItem(desde + i,paginaActual == desde +i));
		}
		
		
	}

	
	/*  
	  ### Ini - Métodos booleanos para validar la página donde estamos ###
	  ##############################################################
	*/
	public boolean isFirst() {
		return page.isFirst();
	}
	
	public boolean isLast() {
		return page.isLast();
	}
	
	public boolean isHasNext() {
		return page.hasNext();
	}
	
	public boolean isHasPrevious() {
		return page.hasPrevious();
	}
	
	
	/*  
	  ### Fin - Métodos booleanos para validar la página donde estamos ###
	  ##############################################################
	*/
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Page<T> getPage() {
		return page;
	}

	public void setPage(Page<T> page) {
		this.page = page;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public void setTotalPaginas(int totalPaginas) {
		this.totalPaginas = totalPaginas;
	}

	public int getNumeroElementosPorPagina() {
		return numeroElementosPorPagina;
	}

	public void setNumeroElementosPorPagina(int numeroElementosPorPagina) {
		this.numeroElementosPorPagina = numeroElementosPorPagina;
	}

	public int getPaginaActual() {
		return paginaActual;
	}

	public void setPaginaActual(int paginaActual) {
		this.paginaActual = paginaActual;
	}

	public List<PageItem> getPaginas() {
		return paginas;
	}

	public void setPaginas(List<PageItem> paginas) {
		this.paginas = paginas;
	}
	
	
	
	
	
	
}
