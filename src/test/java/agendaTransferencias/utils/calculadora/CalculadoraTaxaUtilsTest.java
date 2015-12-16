package agendaTransferencias.utils.calculadora;

import static agendaTransferencias.utils.calculadora.CalculadoraTaxaUtils.arredondaTaxa;
import static agendaTransferencias.utils.calculadora.CalculadoraTaxaUtils.calculaQuantidadeDias;
import static java.math.BigDecimal.ONE;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

/**
 * Classe utilitária para os testes unitários das calculadoras
 * 
 * @author danilo.possarle
 * @created Dec 16, 2015
 */
public class CalculadoraTaxaUtilsTest {

    @Test
    public void formataTaxa() {
        Assert.assertEquals(ONE.setScale(2, RoundingMode.HALF_EVEN), arredondaTaxa(ONE));
    }

    @Test
    public void formataTaxaArredondandoParaBaixo() {
        Assert.assertEquals(new BigDecimal("3.25"), arredondaTaxa(new BigDecimal("3.254")));
    }

    @Test
    public void formataTaxaArredondandoParaCima() {
        Assert.assertEquals(new BigDecimal("3.26"), arredondaTaxa(new BigDecimal("3.256")));
    }

    @Test
    public void calculaDias() {
        Assert.assertEquals(5, calculaQuantidadeDias(new DateTime(2015, 12, 16, 0, 0, 0), new DateTime(2015, 12, 21, 0, 0, 0)));
    }

}
