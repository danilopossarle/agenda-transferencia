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
            return arredondaTaxa(new BigDecimal("0.083").multiply(transferencia.getValor()));
        }
        if (intervaloEmDias <= 10) {
            return arredondaTaxa(new BigDecimal("0.074").multiply(transferencia.getValor()));
        }
        if (intervaloEmDias <= 15) {
            return arredondaTaxa(new BigDecimal("0.067").multiply(transferencia.getValor()));
        }
        if (intervaloEmDias <= 20) {
            return arredondaTaxa(new BigDecimal("0.054").multiply(transferencia.getValor()));
        }
        if (intervaloEmDias <= 25) {
            return arredondaTaxa(new BigDecimal("0.043").multiply(transferencia.getValor()));
        }
        if (intervaloEmDias <= 30) {
            return arredondaTaxa(new BigDecimal("0.021").multiply(transferencia.getValor()));
        }
        return arredondaTaxa(new BigDecimal("0.012").multiply(transferencia.getValor()));
    }
}
