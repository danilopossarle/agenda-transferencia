package agendaTransferencias.utils.calculadora;

import static agendaTransferencias.utils.calculadora.CalculadoraTaxaUtils.arredondaTaxa;

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
        this.mockTransferencia(3);
        Assert.assertEquals(arredondaTaxa(new BigDecimal("8.3")), this.calculadoraTaxaC.calcularTaxa(this.transferencia));
    }

    @Test
    public void calculaTaxaIntervaloIgualA5() {
        this.mockTransferencia(5);
        Assert.assertEquals(arredondaTaxa(new BigDecimal("8.3")), this.calculadoraTaxaC.calcularTaxa(this.transferencia));
    }

    @Test
    public void calculaTaxaIntervaloEntre5e10() {
        this.mockTransferencia(8);
        Assert.assertEquals(arredondaTaxa(new BigDecimal("7.4")), this.calculadoraTaxaC.calcularTaxa(this.transferencia));
    }

    @Test
    public void calculaTaxaIntervaloIgualA10() {
        this.mockTransferencia(10);
        Assert.assertEquals(arredondaTaxa(new BigDecimal("7.4")), this.calculadoraTaxaC.calcularTaxa(this.transferencia));
    }

    @Test
    public void calculaTaxaIntervaloEntre10e15() {
        this.mockTransferencia(13);
        Assert.assertEquals(arredondaTaxa(new BigDecimal("6.7")), this.calculadoraTaxaC.calcularTaxa(this.transferencia));
    }

    @Test
    public void calculaTaxaIntervaloIgualA15() {
        this.mockTransferencia(15);
        Assert.assertEquals(arredondaTaxa(new BigDecimal("6.7")), this.calculadoraTaxaC.calcularTaxa(this.transferencia));
    }

    @Test
    public void calculaTaxaIntervaloEntre15e20() {
        this.mockTransferencia(18);
        Assert.assertEquals(arredondaTaxa(new BigDecimal("5.4")), this.calculadoraTaxaC.calcularTaxa(this.transferencia));
    }

    @Test
    public void calculaTaxaIntervaloIgualA20() {
        this.mockTransferencia(20);
        Assert.assertEquals(arredondaTaxa(new BigDecimal("5.4")), this.calculadoraTaxaC.calcularTaxa(this.transferencia));
    }

    @Test
    public void calculaTaxaIntervaloEntre20e25() {
        this.mockTransferencia(23);
        Assert.assertEquals(arredondaTaxa(new BigDecimal("4.3")), this.calculadoraTaxaC.calcularTaxa(this.transferencia));
    }

    @Test
    public void calculaTaxaIntervaloIgualA25() {
        this.mockTransferencia(25);
        Assert.assertEquals(arredondaTaxa(new BigDecimal("4.3")), this.calculadoraTaxaC.calcularTaxa(this.transferencia));
    }

    @Test
    public void calculaTaxaIntervaloEntre25e30() {
        this.mockTransferencia(28);
        Assert.assertEquals(arredondaTaxa(new BigDecimal("2.1")), this.calculadoraTaxaC.calcularTaxa(this.transferencia));
    }

    @Test
    public void calculaTaxaIntervaloIgualA30() {
        this.mockTransferencia(30);
        Assert.assertEquals(arredondaTaxa(new BigDecimal("2.1")), this.calculadoraTaxaC.calcularTaxa(this.transferencia));
    }

    @Test
    public void calculaTaxaIntervaloMaiorQue30() {
        this.mockTransferencia(40);
        Assert.assertEquals(arredondaTaxa(new BigDecimal("1.2")), this.calculadoraTaxaC.calcularTaxa(this.transferencia));
    }

    /**
     * Define as datas de cadastro e transferência, sendo que a data de cadastro será data atual do sistema e a data de transferência será
     * data atual do sistema acrescida da quantidade de dias informada. Além disso define o valor da transferência como sendo
     * {@link BigDecimal.CEM}
     * 
     * @param diasIntervalo quantidade de dias entre a data de cadastro e a data que a transferência será realizada
     */
    private void mockTransferencia(int diasIntervalo) {
        DateTime dataCadastro = new DateTime();
        DateTime dataTransferencia = dataCadastro.plusDays(diasIntervalo);
        Mockito.when(this.transferencia.getDataCadastro()).thenReturn(dataCadastro);
        Mockito.when(this.transferencia.getDataTransferencia()).thenReturn(dataTransferencia);
        Mockito.when(this.transferencia.getValor()).thenReturn(new BigDecimal("100"));
    }

}
