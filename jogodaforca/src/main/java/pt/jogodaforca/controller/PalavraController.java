package pt.jogodaforca.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pt.jogodaforca.conf.ResourceNotFoundException;
import pt.jogodaforca.dao.PalavraDAO;
import pt.jogodaforca.model.Palavra;

/**
 * Classe implementa controller
 * @author Walyson 
 * @version 1.0
 * @since   2019-12-05
 */
@SuppressWarnings("unchecked")
@Controller
public class PalavraController {

	private List<String> randomWordList;

	private Random randomGen;

	@Autowired
	private PalavraDAO palavraDao;


	/**
	 * Carrega página de palavra 
	 * 
	 * @param model ajuda adicionar atributos a view
	 * @param session região de memória compartilhada por todas as requisições feitas pelo mesmo usuário
	 * 
	 * @return palavra view que será renderizada para usuário
	 */
	@RequestMapping("/palavraController")
	@Cacheable(value="palavraHome")
	public ModelAndView palavraController() {
		Palavra palavra = new Palavra();
		ModelAndView modelAndView = new ModelAndView("palavra");
		modelAndView.addObject("palavra", palavra);
		return modelAndView;
	}

	/**
	 * Pega lista de palavras atribuidas na sessão
	 * 
	 * @param session região de memória compartilhada por todas as requisições feitas pelo mesmo usuário
	 * 
	 * @return palavras que estão na sessão na atual sessão
	 */
	@RequestMapping("/palavras")
	public @ResponseBody List<Palavra> getPalavraList(HttpSession session) {
		List<Palavra> palavras = (List<Palavra>) session.getAttribute("palavras");
		return palavras; 
	}

	/**
	 * Pegar um jogo existente que está sessão
	 * 
	 * @param id da palavra 
	 * 
	 * @param session região de memória compartilhada por todas as requisições feitas pelo mesmo usuário
	 * 
	 * @return palavra
	 */
	@RequestMapping(value = "/palavra/{id}", method = RequestMethod.GET)

	public @ResponseBody Palavra getPalavra(@PathVariable int id, HttpSession session) {
		Palavra palavra = getPalavraInSession(id, session);
		return palavra;
	}

	/**
	 * A cada letra digitada ele verifica se é compatível com a palavra sorteada
	 * 
	 * @param id da palavra 
	 * 
	 * @param letra digitada pelo usuário 
	 * 
	 * @param session região de memória compartilhada por todas as requisições feitas pelo mesmo usuário
	 * 
	 * @return palavra preenchida caso positivo
	 */
	@RequestMapping(value = "/palavra/{id}", method = RequestMethod.PUT)
	public @ResponseBody Palavra marcarPalavra(@PathVariable int id,
			@RequestParam(value = "letra", required = true) String letra, HttpSession session) {

		Palavra palavra = getPalavraInSession(id, session);

		if (palavra.isPlayable() && !letra.isEmpty()) {
			String letraSimples = String.valueOf(letra.toLowerCase().charAt(0));
			palavra.marcarPalavra(letraSimples);
		}

		return palavra;
	}

	/**
	 * Cria um novo jogo adicionando a palavra que foi sorteada para exibir  
	 * 
	 * @param session região de memória compartilhada por todas as requisições feitas pelo mesmo usuário
	 * 
	 * @return newPalavra 
	 * @throws IOException 
	 */
	@RequestMapping(value = "/palavra", method = RequestMethod.POST)
	public @ResponseBody Palavra createPalavra(HttpSession session) throws IOException {
		this.randomGen = new Random(System.nanoTime()); //

		Long dificuldade_id = (Long) session.getAttribute("dificuldade_id");
		Long categoria_id = (Long) session.getAttribute("categoria_id");
		initializeRandomWordList(dificuldade_id, categoria_id);

		List<Palavra> palavras = createOrGetPalavrasInSession(session);
		Long palavraIndex = Long.valueOf(palavras.size());
		
		Palavra newPalavra = null;

	
		if(randomWordList.size() > 0) {
	    newPalavra = new Palavra(palavraIndex, randomWordList.get(randomGen.nextInt(randomWordList.size())));
		palavras.add(newPalavra);
		
		}else{
			session.removeAttribute("palavras");
		}
		

		return newPalavra;
	}

	/**
	 * Restaura da sessão as palavras na sessão e faz a sicronização
	 * 
	 * @param session região de memória compartilhada por todas as requisições feitas pelo mesmo usuário
	 * 
	 * @return palavras da sessão
	 */
	public List<Palavra> createOrGetPalavrasInSession(HttpSession session) {
		List<Palavra> palavras = (List<Palavra>) session.getAttribute("palavras");

		if (palavras == null) {
			palavras = new ArrayList<Palavra>();
			session.setAttribute("palavras", palavras);
		}else{
			for (Palavra palavra : palavras) {
				this.randomWordList.remove(palavra.getDescricao());
			}
		}

		return palavras;
	}

	/**
	 * Procurando palavra na sessão mas manda 404 caso não encontre
	 * 
	 * @param id da palavra 
	 * 
	 * @param session região de memória compartilhada por todas as requisições feitas pelo mesmo usuário
	 * 
	 * @return id de palavras da sessão
	 */
	public Palavra getPalavraInSession(int id, HttpSession session) {
		List<Palavra> palavras = (List<Palavra>) session.getAttribute("palavras");

		if (palavras == null || id >= palavras.size() || palavras.get(id) == null) {
			throw new ResourceNotFoundException();
		}

		return palavras.get(id);
	}

	/**
	 * Instancia a randomWordList baseada nos parâmetros salvos nas session adiciona o resultado na lista
	 * 
	 * @param dificuldade_id passado pelo session.getAttribute("dificuldade_id");  
	 * 
	 * @param categoria_id passado pelo session.getAttribute("categoria_id"); 
	 * 
	 */
	private void initializeRandomWordList(Long dificuldade_id, Long categoria_id) {

		List<Palavra> palavras = palavraDao.listar(dificuldade_id, categoria_id);

		this.randomWordList = new ArrayList<String>();
		for (Palavra palavra : palavras) {
			this.randomWordList.add(palavra != null ? palavra.getDescricao() : null);
		}

	}

}
