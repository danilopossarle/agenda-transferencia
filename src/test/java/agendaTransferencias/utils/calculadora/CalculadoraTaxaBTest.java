package agendaTransferencias.utils.calculadora;

import static agendaTransferencias.utils.calculadora.CalculadoraTaxaUtils.arredondaTaxa;
import static java.math.BigDecimal.TEN;

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
 * Teste unitário para a calculadora de transferências do tipo B
 * 
 * @author danilo.possarle
 * @created Dec 10, 2015
 */
@RunWith(MockitoJUnitRunner.class)
public class CalculadoraTaxaBTest {

    @Mock
    private Transferencia transferencia;

    @InjectMocks
    private CalculadoraTaxaB calculadoraTaxaB;

    @Test
    public void calculaTaxaIntervaloMenorQue30() {
        this.mockIntervalor(20);
        Assert.assertEquals(arredondaTaxa(TEN), this.calculadoraTaxaB.calcularTaxa(this.transferencia));
    }

    @Test
    public void calculaTaxaIntervaloIgualA30() {
        this.mockIntervalor(30);
        Assert.assertEquals(arredondaTaxa(TEN), this.calculadoraTaxaB.calcularTaxa(this.transferencia));
    }

    @Test
    public void calculaTaxaIntervaloMaiorQue30() {
        this.mockIntervalor(40);
        Assert.assertEquals(arredondaTaxa(new BigDecimal("8")), this.calculadoraTaxaB.calcularTaxa(this.transferencia));
    }

    /**
     * Define as datas de cadastro e transferência, sendo que a data de cadastro será data atual do sistema e a data de transferência será
     * data atual do sistema acrescida da quantidade de dias informada.
     * 
     * @param diasIntervalo quantidade de dias entre a data de cadastro e a data que a transferência será realizada
     */
    private void mockIntervalor(int diasIntervalo) {
        DateTime dataCadastro = new DateTime();
        DateTime dataTransferencia = dataCadastro.plusDays(diasIntervalo);
        Mockito.when(this.transferencia.getDataCadastro()).thenReturn(dataCadastro);
        Mockito.when(this.transferencia.getDataTransferencia()).thenReturn(dataTransferencia);
    }
}
