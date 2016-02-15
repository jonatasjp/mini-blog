package br.com.miniblog.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.miniblog.dao.ArtigoDAO;
import br.com.miniblog.models.Artigo;

@Controller
@Transactional
@RequestMapping("/gerenciador")
public class ArtigoController {

	@Autowired
	private ArtigoDAO artigoDAO;
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(@Valid Artigo artigo, BindingResult bindingResult, RedirectAttributes redirectAttributes){
		
		
		if(bindingResult.hasErrors()){
			return form(artigo);
		}
		
		if(artigo.getId() == null || artigo.getId() == 0){
			artigoDAO.save(artigo);
			redirectAttributes.addFlashAttribute("sucesso", "Artigo inserido com sucesso");
		}else{
			artigoDAO.atualizar(artigo);
			redirectAttributes.addFlashAttribute("sucesso", "Artigo atualizado com sucesso");
		}
		
		return new ModelAndView("redirect:gerenciador");
		
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView editar(@PathVariable("id") Long id){
		
		Artigo artigo = artigoDAO.findById(id);
		return form(artigo);
	
	}
	
	@RequestMapping("/remove/{id}")
	public ModelAndView remover(@PathVariable("id") Long id){
		
		Artigo artigo = new Artigo();
		artigo.setId(id);
		artigoDAO.excluir(artigo);
		
		return list();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("artigo/list");
		modelAndView.addObject("artigos", artigoDAO.findAll());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/form")
	public ModelAndView form(Artigo artigo) {
		ModelAndView modelAndView = new ModelAndView("artigo/form");
		modelAndView.addObject("artigo", artigo);
		return modelAndView;
	}
}
