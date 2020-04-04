package pt.jogodaforca.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pt.jogodaforca.dao.CategoriaDAO;
import pt.jogodaforca.model.Categoria;

/**
 * Classe implementa controller
 * @author Walyson 
 * @version 1.0
 * @since   2019-12-05
 */
@Controller
public class CategoriaController {

	@Autowired
	private CategoriaDAO categoriaDao;

	/**
	 * Carrega página de categoria com os atributos carregados com a lista 
	 * 
	 * @param model ajuda adicionar atributos a view
	 * 
	 * @return categoria view que será renderizada para usuário
	 */
	@RequestMapping("/categoriaController")
	@Cacheable(value="categoriaHome")
	public ModelAndView categoriaController() {
		Categoria categoria = new Categoria();
		ModelAndView modelAndView = new ModelAndView("categoria");
		modelAndView.addObject("categoria", categoria);
		modelAndView.addObject("categorias", categoriaDao.listar());
		
		return modelAndView;
	}
	

	/**
	 * Redireciona para palavra após selecionada a opção
	 * 
	 * @param categoria_id pega o id da opção de categoria selecionada para guardar informação
	 * @param session região de memória compartilhada por todas as requisições feitas pelo mesmo usuário
	 * 
	 * @return modelAndView retorna para controller 
	 */
	@RequestMapping(value = "/categoriaRedirect/", method = RequestMethod.GET)
	public ModelAndView categoriaRedirect(@RequestParam Long id, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("redirect:/palavraController");
		session.setAttribute("categoria_id", id);
		return modelAndView;
	}

}
