package agendaTransferencias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller para direcionar para página inicial da aplicação.
 * 
 * @author danilo.possarle
 * @created Dec 9, 2015
 */
@Controller
public class HomeController {

	/**
	 * @return página inicial
	 */
	@RequestMapping("/")
    public String home() {
        return "home";
    }
	
}
