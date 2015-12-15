package agendaTransferencias.utils.calculadora;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

import agendaTransferencias.model.domain.Transferencia;

/**
 * Calculadora responsável por realizar o cálculo de taxas para transferências do tipo A Se tornará um bean para ser injetado onde for
 * necessário na aplicação
 * 
 * @author danilo.possarle
 * @created Dec 9, 2015
 */
@Component
public class CalculadoraTaxaA implements CalculadoraTaxa {

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal calcularTaxa(Transferencia transferencia) {
        BigDecimal percentualCobrado = new BigDecimal("0.03");
        return new BigDecimal("2").add(percentualCobrado.multiply(transferencia.getValor())).setScale(2, RoundingMode.HALF_EVEN);
    }
}
