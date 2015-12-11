package agendaTransferencias.utils.factory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import agendaTransferencias.utils.TipoTransferencia;
import agendaTransferencias.utils.calculadora.CalculadoraTaxaA;
import agendaTransferencias.utils.calculadora.CalculadoraTaxaB;
import agendaTransferencias.utils.calculadora.CalculadoraTaxaC;
import agendaTransferencias.utils.calculadora.CalculadoraTaxaD;

/**
 * Teste unitário para a fábrica de calculadoras
 * 
 * @author danilo.possarle
 * @created Dec 10, 2015
 */
@RunWith(MockitoJUnitRunner.class)
public class CalculadoraTaxaFactoryTest {

    @Mock
    private CalculadoraTaxaA calculadoraTaxaA;

    @Mock
    private CalculadoraTaxaB calculadoraTaxaB;

    @Mock
    private CalculadoraTaxaC calculadoraTaxaC;

    @Mock
    private CalculadoraTaxaD calculadoraTaxaD;

    @InjectMocks
    private CalculadoraTaxaFactory calculadoraFactory;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void tipoTransferenciaA() {
        Assert.assertEquals(this.calculadoraTaxaA.getClass(), this.calculadoraFactory.calculadoraPara(TipoTransferencia.A).getClass());
    }

    @Test
    public void tipoTransferenciaB() {
        Assert.assertEquals(this.calculadoraTaxaB.getClass(), this.calculadoraFactory.calculadoraPara(TipoTransferencia.B).getClass());
    }

    @Test
    public void tipoTransferenciaC() {
        Assert.assertEquals(this.calculadoraTaxaC.getClass(), this.calculadoraFactory.calculadoraPara(TipoTransferencia.C).getClass());
    }

    @Test
    public void tipoTransferenciaD() {
        Assert.assertEquals(this.calculadoraTaxaD.getClass(), this.calculadoraFactory.calculadoraPara(TipoTransferencia.D).getClass());
    }

}
