package agendaTransferencias.utils.calculadora;

import static java.math.BigDecimal.TEN;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import agendaTransferencias.model.domain.Transferencia;

/**
 * Teste unitário para a calculadora de transferências do tipo A
 * 
 * @author danilo.possarle
 * @created Dec 10, 2015
 */
@RunWith(MockitoJUnitRunner.class)
public class CalculadoraTaxaATest {

	@Mock
	private Transferencia transferencia;
	
	@InjectMocks
	private CalculadoraTaxaA calculadoraTaxaA;
	
	@Test
	public void calculaTaxa() {
		Mockito.when(this.transferencia.getValor()).thenReturn(TEN);
		Assert.assertEquals(new BigDecimal("2.30"), this.calculadoraTaxaA.calcularTaxa(this.transferencia));
	}
	
}
