package agendaTransferencias.utils.calculadora;

import java.math.BigDecimal;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import agendaTransferencias.model.domain.Transferencia;

/**
 * Teste unitário para a calculadora de transferências do tipo C
 * 
 * @author danilo.possarle
 * @created Dec 10, 2015
 */
@RunWith(MockitoJUnitRunner.class)
public class CalculadoraTaxaCTest {

	@Mock
	private Transferencia transferencia;
	
	@InjectMocks
	private CalculadoraTaxaC calculadoraTaxaC;
	
	@Test
	public void calculaTaxaIntervaloMenorQue5() {
		this.mockIntervalo(3);
		Assert.assertEquals(new BigDecimal("8.3"), this.calculadoraTaxaC.calcularTaxa(this.transferencia));
	}
	
	@Test
	public void calculaTaxaIntervaloIgualA5() {
		this.mockIntervalo(5);
		Assert.assertEquals(new BigDecimal("8.3"), this.calculadoraTaxaC.calcularTaxa(this.transferencia));
	}
	
	@Test
	public void calculaTaxaIntervaloEntre5e10() {
		this.mockIntervalo(8);
		Assert.assertEquals(new BigDecimal("7.4"), this.calculadoraTaxaC.calcularTaxa(this.transferencia));
	}
	
	@Test
	public void calculaTaxaIntervaloIgualA10() {
		this.mockIntervalo(10);
		Assert.assertEquals(new BigDecimal("7.4"), this.calculadoraTaxaC.calcularTaxa(this.transferencia));
	}
	
	@Test
	public void calculaTaxaIntervaloEntre10e15() {
		this.mockIntervalo(13);
		Assert.assertEquals(new BigDecimal("6.7"), this.calculadoraTaxaC.calcularTaxa(this.transferencia));
	}
	
	@Test
	public void calculaTaxaIntervaloIgualA15() {
		this.mockIntervalo(15);
		Assert.assertEquals(new BigDecimal("6.7"), this.calculadoraTaxaC.calcularTaxa(this.transferencia));
	}
	
	@Test
	public void calculaTaxaIntervaloEntre15e20() {
		this.mockIntervalo(18);
		Assert.assertEquals(new BigDecimal("5.4"), this.calculadoraTaxaC.calcularTaxa(this.transferencia));
	}
	
	@Test
	public void calculaTaxaIntervaloIgualA20() {
		this.mockIntervalo(20);
		Assert.assertEquals(new BigDecimal("5.4"), this.calculadoraTaxaC.calcularTaxa(this.transferencia));
	}
	
	@Test
	public void calculaTaxaIntervaloEntre20e25() {
		this.mockIntervalo(23);
		Assert.assertEquals(new BigDecimal("4.3"), this.calculadoraTaxaC.calcularTaxa(this.transferencia));
	}
	
	@Test
	public void calculaTaxaIntervaloIgualA25() {
		this.mockIntervalo(25);
		Assert.assertEquals(new BigDecimal("4.3"), this.calculadoraTaxaC.calcularTaxa(this.transferencia));
	}
	
	@Test
	public void calculaTaxaIntervaloEntre25e30() {
		this.mockIntervalo(28);
		Assert.assertEquals(new BigDecimal("2.1"), this.calculadoraTaxaC.calcularTaxa(this.transferencia));
	}
	
	@Test
	public void calculaTaxaIntervaloIgualA30() {
		this.mockIntervalo(30);
		Assert.assertEquals(new BigDecimal("2.1"), this.calculadoraTaxaC.calcularTaxa(this.transferencia));
	}
	
	@Test
	public void calculaTaxaIntervaloMaiorQue30() {
		this.mockIntervalo(40);
		Assert.assertEquals(new BigDecimal("1.2"), this.calculadoraTaxaC.calcularTaxa(this.transferencia));
	}
	
	/**
	 * Define as datas de cadastro e transferência, sendo que a data de cadastro será data atual do sistema
	 * e a data de transferência será data atual do sistema acrescida da quantidade de dias informada. 
	 * 
	 * @param diasIntervalo quantidade de dias entre a data de cadastro e a data que a transferência será realizada
	 */
	private void mockIntervalo(int diasIntervalo) {
		DateTime dataCadastro = new DateTime();
		DateTime dataTransferencia = dataCadastro.plusDays(diasIntervalo);
		Mockito.when(this.transferencia.getDataCadastro()).thenReturn(dataCadastro);
		Mockito.when(this.transferencia.getDataTransferencia()).thenReturn(dataTransferencia);
	}
	
}
