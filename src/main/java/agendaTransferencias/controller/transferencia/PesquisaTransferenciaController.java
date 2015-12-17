package agendaTransferencias.controller.transferencia;

import static agendaTransferencias.utils.TipoTransferenciaUtils.getTipoTransferenciaChoices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import agendaTransferencias.model.domain.Transferencia;
import agendaTransferencias.service.transferencia.TransferenciaService;
import agendaTransferencias.utils.model.PesquisaTransferenciaModel;

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
     * @param pesquisaModel {@link PesquisaTransferenciaModel}
     * @return {@link ModelAndView}
     */
    @RequestMapping(value = "realizarPesquisaTransferencia", method = RequestMethod.POST)
    public ModelAndView realizarPesquisa(Model model, @ModelAttribute("pesquisaModel") PesquisaTransferenciaModel pesquisaModel) {
        List<Transferencia> transferencias = this.transferenciaService.findTransferenciasBy(pesquisaModel);
		model.addAttribute("transferencias", transferencias);
		if(transferencias.isEmpty()){
			model.addAttribute("msgError", "Não foi encontrada nenhuma transferência para os parâmetros informados.");
		}
        return this.criaPaginaPesquisa(model);

    }

    /**
     * Método responsável por construir um {@link PesquisaTransferenciaModel} e adicionar no {@link Model} da página
     * 
     * @param model {@link Model} da página
     * @return página de pesquisa de transferências
     */
    @RequestMapping("transferencias")
    public ModelAndView criaPaginaPesquisa(Model model) {
        if (!model.containsAttribute("pesquisaModel")) {
            model.addAttribute("pesquisaModel", new PesquisaTransferenciaModel());
        }
        if (!model.containsAttribute("tiposTransferencia")) {
            model.addAttribute("tiposTransferencia", getTipoTransferenciaChoices());
        }
        if (!model.containsAttribute("transferencias")) {
            model.addAttribute("transferencias", this.transferenciaService.findAll());
        }
        return new ModelAndView("consultaTransferencias", model.asMap());
    }

    /**
     * Método responsável por remover uma tranferência da base de dados.
     * 
     * @param id id da transferência que deve ser removida.
     * @return página de pesquisa de transferências após a remoção.
     */
    @RequestMapping(value = "excluirTransferencia", method = RequestMethod.GET)
    public ModelAndView excluirTransferencia(@ModelAttribute("id") Long id) {
        this.transferenciaService.remove(id);
        return this.criaPaginaPesquisa(new ExtendedModelMap());
    }

}
