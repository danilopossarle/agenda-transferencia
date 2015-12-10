package agendaTransferencias.utils.calculadora;

import static org.joda.time.Days.daysBetween;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import agendaTransferencias.model.domain.Transferencia;

/**
* Calculadora responsável por realizar o cálculo de taxas para transferências do tipo B
* Se tornará um bean para ser injetado onde for necessário na aplicação
*
* @author danilo.possarle
* @created Dec 9, 2015
*/
@Component
public class CalculadoraTaxaB implements CalculadoraTaxa {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public BigDecimal calcularTaxa(Transferencia transferencia) {
        int intervaloEmDias = daysBetween(transferencia.getDataCadastro(), transferencia.getDataTransferencia()).getDays();
        if (intervaloEmDias <= 30) {
            return BigDecimal.TEN;
        }
        return new BigDecimal("8");
    }
}
