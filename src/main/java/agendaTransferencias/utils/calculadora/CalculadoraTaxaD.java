package agendaTransferencias.utils.calculadora;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import agendaTransferencias.model.domain.Transferencia;

/**
* Calculadora responsável por realizar o cálculo de taxas para transferências do tipo D
* Se tornará um bean para ser injetado onde for necessário na aplicação
*
* @author danilo.possarle
* @created Dec 9, 2015
*/
@Component
public class CalculadoraTaxaD implements CalculadoraTaxa {
	
	private CalculadoraTaxaA calculadoraTaxaA;

	private CalculadoraTaxaB calculadoraTaxaB;
	
	private CalculadoraTaxaC calculadoraTaxaC;
	
	/**
	 * Construtor.
	 * Quando invocado para a criação do bean, realizará a injeção das dependencias que essa factory possui.
	 * 
	 * @param calculadoraTaxaA {@link CalculadoraTaxaA}
	 * @param calculadoraTaxaB {@link CalculadoraTaxaB}
	 * @param calculadoraTaxaC {@link CalculadoraTaxaC}
	 */
	@Autowired
	public CalculadoraTaxaD(CalculadoraTaxaA calculadoraTaxaA, CalculadoraTaxaB calculadoraTaxaB,
			CalculadoraTaxaC calculadoraTaxaC) {
		this.calculadoraTaxaA = calculadoraTaxaA;
		this.calculadoraTaxaB = calculadoraTaxaB;
		this.calculadoraTaxaC = calculadoraTaxaC;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public BigDecimal calcularTaxa(Transferencia transferencia) {
        if (transferencia.getValor().compareTo(new BigDecimal("25000")) <= 0) {
            return this.calculadoraTaxaA.calcularTaxa(transferencia);
        }
        if (transferencia.getValor().compareTo(new BigDecimal("120000")) <= 0) {
            return this.calculadoraTaxaB.calcularTaxa(transferencia);
        }
        return this.calculadoraTaxaC.calcularTaxa(transferencia);
    }
}
