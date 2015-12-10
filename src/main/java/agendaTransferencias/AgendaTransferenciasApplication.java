package agendaTransferencias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Classe para inicialização da aplicação
 * 
 * @author danilo.possarle
 * @created Dec 9, 2015
 */
@SpringBootApplication
public class AgendaTransferenciasApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgendaTransferenciasApplication.class, args);
    }
}
