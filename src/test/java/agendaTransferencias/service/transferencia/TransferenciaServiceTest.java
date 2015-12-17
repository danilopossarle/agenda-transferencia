package agendaTransferencias.service.transferencia;

import static agendaTransferencias.utils.builder.TransferenciaBuilder.umaTransferencia;
import static java.math.BigDecimal.ONE;
import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import agendaTransferencias.model.dao.TransferenciaDAO;
import agendaTransferencias.model.domain.Transferencia;
import agendaTransferencias.utils.calculadora.CalculadoraTaxaA;
import agendaTransferencias.utils.factory.CalculadoraTaxaFactory;
import agendaTransferencias.utils.model.PesquisaTransferenciaModel;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration
public class TransferenciaServiceTest {

    @Mock
    private TransferenciaDAO transferenciaDAO;

    @Mock
    private CalculadoraTaxaFactory calculadoraFactory;

    @Mock
    private CalculadoraTaxaA calculadoraTaxaA;

    @InjectMocks
    private TransferenciaService transferenciaService;

    private static Transferencia TRANSFERENCIA_AMANHA;

    private static Transferencia TRANSFERENCIA_2_DIAS;

    private static final PesquisaTransferenciaModel PESQUISA_MODEL = new PesquisaTransferenciaModel();

    private static final DateTime DATA_HOJE = new DateTime().withTimeAtStartOfDay();

    private static final DateTime DATA_AMANHA = DATA_HOJE.plusDays(1);

    private static final DateTime DATA_2_DIAS = DATA_HOJE.plusDays(2);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        TRANSFERENCIA_AMANHA = umaTransferencia().comDataTransferencia(DATA_AMANHA).build();
        TRANSFERENCIA_2_DIAS = umaTransferencia().comDataTransferencia(DATA_2_DIAS).build();
    }

    @Test
    public void findTransferenciasBy() {
        List<Transferencia> transferenciasMockadas = asList(TRANSFERENCIA_AMANHA, TRANSFERENCIA_2_DIAS);
        when(this.transferenciaDAO.findBy(null, null, null, null, null, null)).thenReturn(transferenciasMockadas);

        List<Transferencia> transferenciasFromServico = this.transferenciaService.findTransferenciasBy(PESQUISA_MODEL);
        Assert.assertEquals(transferenciasMockadas, transferenciasFromServico);

        for (Transferencia transferencia : transferenciasMockadas) {
            Assert.assertTrue(transferenciasFromServico.contains(transferencia));
        }

    }

    @Test
    public void calculaTaxa() {
        when(this.calculadoraFactory.calculadoraPara(TRANSFERENCIA_AMANHA.getTipo())).thenReturn(this.calculadoraTaxaA);
        when(this.calculadoraTaxaA.calcularTaxa(TRANSFERENCIA_AMANHA)).thenReturn(ONE);

        Assert.assertEquals(ONE, this.transferenciaService.calculaTaxa(TRANSFERENCIA_AMANHA));
    }

}
