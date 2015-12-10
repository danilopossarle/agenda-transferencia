package agendaTransferencias.utils.calculadora;

import static org.joda.time.Days.daysBetween;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import agendaTransferencias.model.domain.Transferencia;

/**
* Calculadora responsável por realizar o cálculo de taxas para transferências do tipo C
* Se tornará um bean para ser injetado onde for necessário na aplicação
*
* @author danilo.possarle
* @created Dec 9, 2015
*/
@Component
public class CalculadoraTaxaC implements CalculadoraTaxa {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public BigDecimal calcularTaxa(Transferencia transferencia) {
        int intervaloEmDias = daysBetween(transferencia.getDataCadastro(), transferencia.getDataTransferencia()).getDays();
        if (intervaloEmDias <= 5) {
            return new BigDecimal("8.3");
        }
        if (intervaloEmDias <= 10) {
            return new BigDecimal("7.4");
        }
        if (intervaloEmDias <= 15) {
            return new BigDecimal("6.7");
        }
        if (intervaloEmDias <= 20) {
            return new BigDecimal("5.4");
        }
        if (intervaloEmDias <= 25) {
            return new BigDecimal("4.3");
        }
        if (intervaloEmDias <= 30) {
            return new BigDecimal("2.1");
        }
        return new BigDecimal("1.2");
    }
}
