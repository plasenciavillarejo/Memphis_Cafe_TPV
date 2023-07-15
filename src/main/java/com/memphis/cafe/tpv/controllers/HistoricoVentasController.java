package com.memphis.cafe.tpv.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.memphis.cafe.tpv.entity.Historico;
import com.memphis.cafe.tpv.paginador.PageRender;
import com.memphis.cafe.tpv.service.IHistoricoService;
import com.memphis.cafe.tpv.utilidades.Utilidades;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/Memphis_Cafe")
@SessionAttributes({"paginaActual"})
public class HistoricoVentasController {

	@Autowired
	private IHistoricoService historicoService;
	
	@Autowired
	private Utilidades utilidades;
	
	private static String HISTORICOVENTAS = "/historicoVentas/historico";
	private static String REDIRECTHISTORICOVENTAS = "/Memphis_Cafe/historicoVentas";
	
	private String VALORPAGINAACTUAL = "";
	
	private String HISTORICO = "historico";
	
	@GetMapping(value="/historicoVentas")
	public String consultaHistorico(@RequestParam(name="pagina", defaultValue = "0") int page, Model model,
			Historico historico) {
		
		Pageable pageRequest = null;
		Page<Historico> listaConsutaHistorico = null;
		PageRender<Historico> paginador = null;
		
		busquedaPageableGenerica(page, pageRequest, listaConsutaHistorico, paginador,model);
		
		model.addAttribute("historico", historico);
		
		VALORPAGINAACTUAL = HISTORICO;
		model.addAttribute("paginaActual", VALORPAGINAACTUAL);
		
		return HISTORICOVENTAS;
	}
	
	/*
	@GetMapping(value="/historicoVentas")
	public String consultaHistorico(Historico historico, Model model) {
		model.addAttribute("historico", historico);
		return "prueba";
	}
	*/
	
	@PostMapping(value= "/buscarHistorico")
	public String busquedaHistorico(@Valid Historico historico,BindingResult result,RedirectAttributes flashAttributes, Model model,
			@RequestParam(name="pagina", defaultValue = "0") int page) {
		Pageable pageRequest = null;
		Page<Historico> listaConsutaHistorico = null;
		PageRender<Historico> paginador = null;
		if(result.hasErrors()) {
			busquedaPageableGenerica(page, pageRequest, listaConsutaHistorico, paginador,model);
			return HISTORICOVENTAS;
		}
		
		// Busqueda epécifia
		busquedaPageableEspecifica(historico, page, pageRequest, listaConsutaHistorico, paginador, model);
				
		return HISTORICOVENTAS;
	}
	
	
	
	// Función Genérica para el paginador
	public void busquedaPageableGenerica(int page, Pageable pageRequest,Page<Historico> listaConsutaHistorico,
			PageRender<Historico> paginador,Model model) {
		// PageRequest.of(page, 4) -> page='Número de Página', 10='Número de registros por página' 
		pageRequest = PageRequest.of(page, 10,Sort.by("dia").and(Sort.by("hora")).descending());
		
		listaConsutaHistorico = historicoService.findAllPaginable(pageRequest);
		
		// Llamamos a nuestro PageRender para realizar el paginador creado a mano
		paginador = new PageRender<>(REDIRECTHISTORICOVENTAS, listaConsutaHistorico);	
		
		model.addAttribute("pagina", paginador);
		model.addAttribute("listaHistorico", listaConsutaHistorico);
	}
	
	// Función para el paginador en busqueda específica
	public void busquedaPageableEspecifica(Historico historico,int page, Pageable pageRequest,Page<Historico> listaConsutaHistorico,
			PageRender<Historico> paginador,Model model) {
		
		List<Historico> busquedaPersonalizada = null;
		
		if(!historico.getMesero().isEmpty()) {
			busquedaPersonalizada = historicoService.findAllByName(historico.getMesero());
		} else if(!historico.getDia().isEmpty()) {
			busquedaPersonalizada = historicoService.findAllByDate(utilidades.formatoFecha(historico.getDia()));
		}
		
		pageRequest= PageRequest.of(page, 10,Sort.by("dia").and(Sort.by("hora")).descending());
		
		int startIndex = (int) pageRequest.getOffset();
		int endIndex = Math.min((startIndex + pageRequest.getPageSize()), busquedaPersonalizada.size());

		List<Historico> sublista = busquedaPersonalizada.subList(startIndex, endIndex);
		listaConsutaHistorico = new PageImpl<>(sublista,pageRequest,busquedaPersonalizada.size());
		
		// Llamamos a nuestro PageRender para realizar el paginador creado a mano
		paginador = new PageRender<>("/Memphis_Cafe/historicoVentas", listaConsutaHistorico);
		
		model.addAttribute("pagina", paginador);
		model.addAttribute("listaPersonalizada", listaConsutaHistorico);
	}
	
}
