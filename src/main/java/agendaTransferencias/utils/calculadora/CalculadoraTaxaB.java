package agendaTransferencias.utils.calculadora;

import static agendaTransferencias.utils.calculadora.CalculadoraTaxaUtils.arredondaTaxa;
import static agendaTransferencias.utils.calculadora.CalculadoraTaxaUtils.calculaQuantidadeDias;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import agendaTransferencias.model.domain.Transferencia;

/**
 * Calculadora responsável por realizar o cálculo de taxas para transferências do tipo B Se tornará um bean para ser injetado onde for
 * necessário na aplicação
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
        int intervaloEmDias = calculaQuantidadeDias(transferencia.getDataCadastro(), transferencia.getDataTransferencia());
        if (intervaloEmDias <= 30) {
            return arredondaTaxa(BigDecimal.TEN);
        }
        return arredondaTaxa(new BigDecimal("8"));
    }
}
