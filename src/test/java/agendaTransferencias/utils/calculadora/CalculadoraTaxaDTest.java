package agendaTransferencias.utils.calculadora;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import agendaTransferencias.model.domain.Transferencia;

/**
 * Teste unitário para a calculadora de transferências do tipo D
 * 
 * @author danilo.possarle
 * @created Dec 10, 2015
 */
@RunWith(MockitoJUnitRunner.class)
public class CalculadoraTaxaDTest {

	@Mock
	private Transferencia transferencia;
	
	@Mock
	private CalculadoraTaxaA calculadoraTaxaA;
	
	@Mock
	private CalculadoraTaxaB calculadoraTaxaB;
	
	@Mock
	private CalculadoraTaxaC calculadoraTaxaC;
	
	@InjectMocks
	private CalculadoraTaxaD calculadoraTaxaD;
	
	@Before
	public void setUp() {
		Mockito.when(this.calculadoraTaxaA.calcularTaxa(this.transferencia)).thenReturn(ONE);
		Mockito.when(this.calculadoraTaxaB.calcularTaxa(this.transferencia)).thenReturn(ZERO);
		Mockito.when(this.calculadoraTaxaC.calcularTaxa(this.transferencia)).thenReturn(TEN);
	}
	
	@Test
	public void calculaTaxaValorMenorQue25000() {
		this.mockValor(20000);
		Assert.assertEquals(ONE, this.calculadoraTaxaD.calcularTaxa(this.transferencia));
	}
	
	@Test
	public void calculaTaxaValorIgualA25000() {
		this.mockValor(25000);
		Assert.assertEquals(ONE, this.calculadoraTaxaD.calcularTaxa(this.transferencia));
	}
	
	@Test
	public void calculaTaxaValorMenorQue120000() {
		this.mockValor(111000);
		Assert.assertEquals(ZERO, this.calculadoraTaxaD.calcularTaxa(this.transferencia));
	}
	
	@Test
	public void calculaTaxaValorIgualA120000() {
		this.mockValor(120000);
		Assert.assertEquals(ZERO, this.calculadoraTaxaD.calcularTaxa(this.transferencia));
	}
	
	@Test
	public void calculaTaxaValorMaiorQue120000() {
		this.mockValor(125000);
		Assert.assertEquals(TEN, this.calculadoraTaxaD.calcularTaxa(this.transferencia));
	}
	
	/**
	 * Define o valor da transferência
	 * 
	 * @param valor valor da transferência
	 */
	private void mockValor(int valor) {
		Mockito.when(this.transferencia.getValor()).thenReturn(new BigDecimal(valor));
	}
	
}
