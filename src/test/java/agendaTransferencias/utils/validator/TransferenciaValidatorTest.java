package agendaTransferencias.utils.validator;

import static agendaTransferencias.utils.TipoTransferencia.A;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;

import agendaTransferencias.model.domain.Transferencia;

/**
 * Teste unitário para a classe {@link TransferenciaValidator}
 * 
 * @author danilo.possarle
 * @created Dec 16, 2015
 */
public class TransferenciaValidatorTest {

    private static final String CONTA_ORIGEM = "65432-1";

    private static final String CONTA_DESTINO = "12345-6";

    private static final String CONTA_INVALIDA = "123456";

    private static final DateTime DATA_CADASTRO = new DateTime().withTimeAtStartOfDay();

    private static final DateTime DATA_TRANSFERENCIA = DATA_CADASTRO.plusDays(10);

    private static final DateTime DATA_TRANSFERENCIA_ANTERIOR = DATA_CADASTRO.minusDays(10);

    private TransferenciaValidator transferenciaValidator;

    @Before
    public void setUp() {
        this.transferenciaValidator = new TransferenciaValidator();
    }

    @Test
    public void supports() {
        Assert.assertTrue(this.transferenciaValidator.supports(Transferencia.class));
        Assert.assertFalse(this.transferenciaValidator.supports(Object.class));
    }

    @Test
    public void transferenciaValida() {
        Transferencia transferencia = this.criaTransferenciaValida();
        BindException errors = new BindException(transferencia, "transferencia");
        ValidationUtils.invokeValidator(this.transferenciaValidator, transferencia, errors);
        Assert.assertFalse(errors.hasErrors());
    }

    @Test
    public void erroContaOrigemInvalida() {
        Transferencia transferencia = this.criaTransferenciaValida();
        transferencia.setContaOrigem(CONTA_INVALIDA);
        BindException errors = new BindException(transferencia, "transferencia");
        ValidationUtils.invokeValidator(this.transferenciaValidator, transferencia, errors);
        Assert.assertTrue(errors.hasErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount("contaOrigem"));
        Assert.assertEquals("conta.mal.formatada", errors.getFieldError("contaOrigem").getCode());
    }

    @Test
    public void erroContaDestinoInvalida() {
        Transferencia transferencia = this.criaTransferenciaValida();
        transferencia.setContaDestino(CONTA_INVALIDA);
        BindException errors = new BindException(transferencia, "transferencia");
        ValidationUtils.invokeValidator(this.transferenciaValidator, transferencia, errors);
        Assert.assertTrue(errors.hasErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount("contaDestino"));
        Assert.assertEquals("conta.mal.formatada", errors.getFieldError("contaDestino").getCode());
    }

    @Test
    public void erroContasIguais() {
        Transferencia transferencia = this.criaTransferenciaValida();
        transferencia.setContaOrigem(CONTA_ORIGEM);
        transferencia.setContaDestino(CONTA_ORIGEM);
        BindException errors = new BindException(transferencia, "transferencia");
        ValidationUtils.invokeValidator(this.transferenciaValidator, transferencia, errors);
        Assert.assertTrue(errors.hasErrors());
        Assert.assertEquals(2, errors.getErrorCount());
        Assert.assertEquals(1, errors.getFieldErrorCount("contaOrigem"));
        Assert.assertEquals(1, errors.getFieldErrorCount("contaDestino"));
        Assert.assertEquals("contas.devem.ser.diferentes", errors.getFieldError("contaDestino").getCode());
        Assert.assertEquals("contas.devem.ser.diferentes", errors.getFieldError("contaOrigem").getCode());
    }

    @Test
    public void erroDatasIguais() {
        Transferencia transferencia = this.criaTransferenciaValida();
        transferencia.setDataTransferencia(DATA_CADASTRO);
        BindException errors = new BindException(transferencia, "transferencia");
        ValidationUtils.invokeValidator(this.transferenciaValidator, transferencia, errors);
        Assert.assertTrue(errors.hasErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount("dataTransferencia"));
        Assert.assertEquals("dataTransferencia.after.dataCadastro", errors.getFieldError("dataTransferencia").getCode());
    }

    @Test
    public void erroDataTransferenciaAnteriorDataCadastro() {
        Transferencia transferencia = this.criaTransferenciaValida();
        transferencia.setDataTransferencia(DATA_TRANSFERENCIA_ANTERIOR);
        BindException errors = new BindException(transferencia, "transferencia");
        ValidationUtils.invokeValidator(this.transferenciaValidator, transferencia, errors);
        Assert.assertTrue(errors.hasErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount("dataTransferencia"));
        Assert.assertEquals("dataTransferencia.after.dataCadastro", errors.getFieldError("dataTransferencia").getCode());
    }

    private Transferencia criaTransferenciaValida() {
        Transferencia transferencia = new Transferencia();
        transferencia.setContaDestino(CONTA_DESTINO);
        transferencia.setContaOrigem(CONTA_ORIGEM);
        transferencia.setDataCadastro(DATA_CADASTRO);
        transferencia.setDataTransferencia(DATA_TRANSFERENCIA);
        transferencia.setTaxa(ONE);
        transferencia.setValor(TEN);
        transferencia.setTipo(A);
        return transferencia;
    }

}
