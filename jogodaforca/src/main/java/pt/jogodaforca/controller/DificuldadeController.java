package pt.jogodaforca.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pt.jogodaforca.dao.DificuldadeDAO;
import pt.jogodaforca.model.Dificuldade;

/**
 * Classe implementa controller
 * @author Walyson 
 * @version 1.0
 * @since   2019-12-05
 */
@Controller
public class DificuldadeController {

	@Autowired
	private DificuldadeDAO dificuldadeDao;

	/**
	 * Carrega página de dificuldade com os atributos carregados com a lista 
	 * 
	 * @param model ajuda adicionar atributos a view
	 * 
	 * @return dificuldade view que será renderizada para usuário
	 */
	@RequestMapping("/")
	@Cacheable(value="dificuldadeHome")
	public ModelAndView dificuldadeController() {
		Dificuldade dificuldade = new Dificuldade();
		ModelAndView modelAndView = new ModelAndView("dificuldade");
		modelAndView.addObject("dificuldade", dificuldade);
		modelAndView.addObject("dificuldades", dificuldadeDao.listar());
		
		return modelAndView;
	}
	
	/**
	 * Redireciona para categoria após selecionada a opção
	 * 
	 * @param dificuldade_id pega o id da opção de dificuldade selecionada para guardar informação
	 * @param session região de memória compartilhada por todas as requisições feitas pelo mesmo usuário
	 * 
	 * @return modelAndView retorna para controller 
	 */

	@RequestMapping(value = "/dificuldadeRedirect/", method = RequestMethod.GET)
	public ModelAndView dificuldadeRedirect(@RequestParam Long id, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("redirect:/categoriaController");
		session.setAttribute("dificuldade_id", id);
		return modelAndView;
	}

}
