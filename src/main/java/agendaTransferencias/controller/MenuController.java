package agendaTransferencias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import agendaTransferencias.utils.model.PesquisaTranferenciaModel;

/**
 * Controller para o menu da aplicação.
 * 
 * @author danilo.possarle
 * @created Dec 9, 2015
 */
@Controller
public class MenuController {

	/**
	 * @return página inicial
	 */
	@RequestMapping("/")
    public String home() {
        return "home";
    }
	
	/**
	 * Método responsável por construir um {@link PesquisaTranferenciaModel} e adicionar no {@link Model} da página
	 * 
	 * @param model {@link Model} da página
	 * @return página de pesquisa de transferências
	 */
	@RequestMapping("/transferencias")
    public ModelAndView transferencias(Model model) {
		if (!model.containsAttribute("pesquisaModel")) {
			model.addAttribute("pesquisaModel", new PesquisaTranferenciaModel());
		}
		return new ModelAndView("consutlaTransferencias", model.asMap());
    }

}
