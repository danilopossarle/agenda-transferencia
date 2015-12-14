package agendaTransferencias.controller.transferencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import agendaTransferencias.service.transferencia.TransferenciaService;
import agendaTransferencias.utils.model.PesquisaTranferenciaModel;

/**
 * Controller para a página de pesquisa de transferências.
 * 
 * @author danilo.possarle
 * @created Dec 12, 2015
 */
@Controller
public class PesquisaTransferenciaController {
	
	@Autowired
	private TransferenciaService transferenciaService;

	/**
	 * Realiza a pesquisa com base no model informado.
	 * 
	 * @param model {@link Model}
	 * @param pesquisaModel {@link PesquisaTranferenciaModel}
	 * @return {@link ModelAndView}
	 */
	@RequestMapping(value="realizarPesquisaTransferencia", method = RequestMethod.POST)	
	public ModelAndView realizarPesquisa(Model model, @ModelAttribute("pesquisaModel") PesquisaTranferenciaModel pesquisaModel) {
		
		model.addAttribute("transferencias", this.transferenciaService.findTransferenciasBy(pesquisaModel));
		model.addAttribute("pesquisaModel", pesquisaModel);
		
		return new ModelAndView("consutlaTransferencias", model.asMap());
		
	}
	
}
