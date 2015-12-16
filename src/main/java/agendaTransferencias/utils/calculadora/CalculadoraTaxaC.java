package agendaTransferencias.utils.calculadora;

import static agendaTransferencias.utils.calculadora.CalculadoraTaxaUtils.arredondaTaxa;
import static agendaTransferencias.utils.calculadora.CalculadoraTaxaUtils.calculaQuantidadeDias;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import agendaTransferencias.model.domain.Transferencia;

/**
 * Calculadora responsável por realizar o cálculo de taxas para transferências do tipo C Se tornará um bean para ser injetado onde for
 * necessário na aplicação
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
        int intervaloEmDias = calculaQuantidadeDias(transferencia.getDataCadastro(), transferencia.getDataTransferencia());
        if (intervaloEmDias <= 5) {
            return arredondaTaxa(new BigDecimal("8.3"));
        }
        if (intervaloEmDias <= 10) {
            return arredondaTaxa(new BigDecimal("7.4"));
        }
        if (intervaloEmDias <= 15) {
            return arredondaTaxa(new BigDecimal("6.7"));
        }
        if (intervaloEmDias <= 20) {
            return arredondaTaxa(new BigDecimal("5.4"));
        }
        if (intervaloEmDias <= 25) {
            return arredondaTaxa(new BigDecimal("4.3"));
        }
        if (intervaloEmDias <= 30) {
            return arredondaTaxa(new BigDecimal("2.1"));
        }
        return arredondaTaxa(new BigDecimal("1.2"));
    }
}
