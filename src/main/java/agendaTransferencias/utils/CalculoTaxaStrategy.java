package agendaTransferencias.utils;

import java.math.BigDecimal;

import agendaTransferencias.model.domain.Transferencia;

/**
 * Interface para estratégia de cálculo para as taxas de transferência.
 * 
 * @author danilo.possarle
 * @created Dec 9, 2015
 */
public interface CalculoTaxaStrategy {

    /**
     * Realiza o cálculo da taxa para a transferência informada.
     * 
     * @param transferencia {@link Transferencia}
     * @return a taxa calculada
     */
    BigDecimal calcularTaxa(Transferencia transferencia);

}
