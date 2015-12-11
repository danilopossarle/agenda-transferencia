package agendaTransferencias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller para a página inicial da aplicação.
 * 
 * @author danilo.possarle
 * @created Dec 9, 2015
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "home";
    }

}
