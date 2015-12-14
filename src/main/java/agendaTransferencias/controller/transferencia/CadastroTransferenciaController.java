package agendaTransferencias.controller.transferencia;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import agendaTransferencias.model.domain.Transferencia;
import agendaTransferencias.service.transferencia.TransferenciaService;

/**
 * Controller para a página de cadastro de transferências.
 * 
 * @author danilo.possarle
 * @created Dec 12, 2015
 */
@Controller
public class CadastroTransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @Autowired
    private TransferenciaValidator transferenciaValidator;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(this.transferenciaValidator);
    }

    /**
     * Cria página de cadastro de transferência
     * 
     * @param model {@link Model}
     * @return {@link ModelAndView}
     */
    @RequestMapping(value = "cadastroTransferencia")
    public ModelAndView cadastroTransferencia(Model model) {
        if (!model.containsAttribute("transferencia")) {
            model.addAttribute("transferencia", new Transferencia());
        }
        return new ModelAndView("cadastroTransferencia", model.asMap());
    }

    /**
     * Realiza o cadastro da transferência informada
     * 
     * @param transferencia {@link Transferencia}
     * @param bindingResult {@link BindingResult}
     * @return {@link ModelAndView}
     */
    @RequestMapping(value = "realizarCadastroTransferencia", method = RequestMethod.POST)
    public ModelAndView realizarCadastroTransferencia(@Valid Transferencia transferencia, BindingResult bindingResult) {
        Model model = new ExtendedModelMap();

        if (bindingResult.hasErrors()) {
            model.addAttribute("transferencia", transferencia);
        } else {
            try {
                this.transferenciaService.save(transferencia);
                model.addAttribute("msgSuccess", "Cadastro realizado com sucesso");
                model.addAttribute("transferencia", new Transferencia());
            } catch (Exception e) {
                model.addAttribute("msgError", "Não foi possível realizar o cadastro");
                model.addAttribute("transferencia", transferencia);
            }
        }
        return this.cadastroTransferencia(model);
    }
}
