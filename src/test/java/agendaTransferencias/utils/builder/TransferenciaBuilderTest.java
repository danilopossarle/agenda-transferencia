package agendaTransferencias.utils.builder;

import static agendaTransferencias.utils.TipoTransferencia.A;
import static agendaTransferencias.utils.TipoTransferencia.B;
import static agendaTransferencias.utils.builder.TransferenciaBuilder.umaTransferencia;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.ZERO;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import agendaTransferencias.model.domain.Transferencia;

/**
 * Teste unitário para {@link TransferenciaBuilder}
 * 
 * @author danilo.possarle
 * @created Dec 17, 2015
 */
public class TransferenciaBuilderTest {

    private static final String CONTA_2 = "65432-1";

    private static final String CONTA_1 = "12345-6";

    private final static DateTime DATA_1 = new DateTime().withTimeAtStartOfDay();

    private final static DateTime DATA_2 = DATA_1.plusDays(10);

    private final static DateTime DATA_3 = DATA_2.plusDays(10);

    @Test
    public void umaTransferenciaSemParametros() {
        Transferencia transferencia = umaTransferencia().build();

        Assert.assertEquals(CONTA_1, transferencia.getContaDestino());
        Assert.assertEquals(CONTA_2, transferencia.getContaOrigem());
        Assert.assertEquals(DATA_1, transferencia.getDataCadastro());
        Assert.assertEquals(DATA_2, transferencia.getDataTransferencia());
        Assert.assertEquals(ONE, transferencia.getTaxa());
        Assert.assertEquals(TEN, transferencia.getValor());
        Assert.assertEquals(A, transferencia.getTipo());
    }

    @Test
    public void umaTransferenciaComParametros() {
        Transferencia transferencia =
                umaTransferencia().comContaDestino(CONTA_2).comContaOrigem(CONTA_1).comDataCadastro(DATA_2).comDataTransferencia(DATA_3)
                        .comTaxa(ZERO).comValor(ONE).comTipo(B).build();

        Assert.assertEquals(CONTA_2, transferencia.getContaDestino());
        Assert.assertEquals(CONTA_1, transferencia.getContaOrigem());
        Assert.assertEquals(DATA_2, transferencia.getDataCadastro());
        Assert.assertEquals(DATA_3, transferencia.getDataTransferencia());
        Assert.assertEquals(ZERO, transferencia.getTaxa());
        Assert.assertEquals(ONE, transferencia.getValor());
        Assert.assertEquals(B, transferencia.getTipo());
    }

}
