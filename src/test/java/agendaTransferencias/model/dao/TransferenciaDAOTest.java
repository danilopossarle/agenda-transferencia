package agendaTransferencias.model.dao;

import static agendaTransferencias.utils.TipoTransferencia.A;
import static agendaTransferencias.utils.TipoTransferencia.B;
import static agendaTransferencias.utils.TipoTransferencia.C;
import static agendaTransferencias.utils.TipoTransferencia.D;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import agendaTransferencias.AgendaTransferenciasApplication;
import agendaTransferencias.model.domain.Transferencia;
import agendaTransferencias.utils.TipoTransferencia;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AgendaTransferenciasApplication.class)
@WebAppConfiguration
public class TransferenciaDAOTest {
	
	private static final String CONTA_1 = "65432-1";

    private static final String CONTA_2 = "12345-6";
    
    private static final String CONTA_3 = "78912-3";

    private static final DateTime DATA_1 = new DateTime().withTimeAtStartOfDay();

    private static final DateTime DATA_2 = DATA_1.plusDays(10);
    
    private static final DateTime DATA_3 = DATA_1.plusDays(15);

	@Autowired
	private TransferenciaDAO dao;
	
	@Test
    @Transactional
    @Rollback(true)
    public void salvarTransferencia() {
		List<Transferencia> transferenciasCadastradas;
        this.verificaBancoZerado();
		
        Transferencia transferencia = this.criaTransferencia(CONTA_1, CONTA_2, DATA_1, DATA_2, ONE, TEN, A);
        this.dao.save(transferencia);
        transferenciasCadastradas = this.dao.findAll();
         
        Assert.assertEquals(1, transferenciasCadastradas.size());
        
        Transferencia transferenciaCadastrada = transferenciasCadastradas.get(0);
		Assert.assertEquals(transferencia.getContaDestino(), transferenciaCadastrada.getContaDestino());
		Assert.assertEquals(transferencia.getContaOrigem(), transferenciaCadastrada.getContaOrigem());
		Assert.assertEquals(transferencia.getDataCadastro(), transferenciaCadastrada.getDataCadastro());
		Assert.assertEquals(transferencia.getDataTransferencia(), transferenciaCadastrada.getDataTransferencia());
		Assert.assertEquals(transferencia.getTaxa(), transferenciaCadastrada.getTaxa());
		Assert.assertEquals(transferencia.getValor(), transferenciaCadastrada.getValor());
		Assert.assertEquals(transferencia.getTipo(), transferenciaCadastrada.getTipo());
    }
	
	@Test
	@Transactional
	@Rollback(true)
	public void deletarTransferencia() {
		List<Transferencia> transferenciasCadastradas;
		this.verificaBancoZerado();
		
        Transferencia transferencia = this.criaTransferencia(CONTA_1, CONTA_2, DATA_1, DATA_2, ONE, TEN, A);
        this.dao.save(transferencia);
        transferenciasCadastradas = this.dao.findAll();
        Assert.assertEquals(1, transferenciasCadastradas.size());
        
        this.dao.remove(transferencia);
        this.verificaBancoZerado();
	}

	@Test
	@Transactional
	@Rollback(true)
	public void findAll() {
		this.verificaBancoZerado();
		
		this.dao.save(this.criaTransferencia(CONTA_1, CONTA_2, DATA_1, DATA_2, ONE, TEN, A));
		this.dao.save(this.criaTransferencia(CONTA_1, CONTA_2, DATA_1, DATA_2, ONE, TEN, B));
		this.dao.save(this.criaTransferencia(CONTA_1, CONTA_2, DATA_1, DATA_2, ONE, TEN, C));
		this.dao.save(this.criaTransferencia(CONTA_1, CONTA_2, DATA_1, DATA_2, ONE, TEN, D));
		
		Assert.assertEquals(4, this.dao.findAll().size());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void findByContaDestino() {
		this.verificaBancoZerado();
		
		this.dao.save(this.criaTransferencia(CONTA_1, CONTA_2, DATA_1, DATA_2, ONE, TEN, A));
		this.dao.save(this.criaTransferencia(CONTA_1, CONTA_2, DATA_1, DATA_2, ONE, TEN, A));
		this.dao.save(this.criaTransferencia(CONTA_1, CONTA_3, DATA_1, DATA_2, ONE, TEN, B));
		this.dao.save(this.criaTransferencia(CONTA_2, CONTA_1, DATA_1, DATA_2, ONE, TEN, C));
		
		Assert.assertEquals(1,  this.dao.findBy(null, null, null, CONTA_2, null, null).size());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void findByContaOrigem() {
		this.verificaBancoZerado();
		
		this.dao.save(this.criaTransferencia(CONTA_1, CONTA_2, DATA_1, DATA_2, ONE, TEN, A));
		this.dao.save(this.criaTransferencia(CONTA_1, CONTA_2, DATA_1, DATA_2, ONE, TEN, A));
		this.dao.save(this.criaTransferencia(CONTA_2, CONTA_3, DATA_1, DATA_2, ONE, TEN, B));
		this.dao.save(this.criaTransferencia(CONTA_2, CONTA_1, DATA_1, DATA_2, ONE, TEN, C));
		
		Assert.assertEquals(1,  this.dao.findBy(null, null, CONTA_1, null, null, null).size());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void findByDataCadastro() {
		this.verificaBancoZerado();
		
		this.dao.save(this.criaTransferencia(CONTA_1, CONTA_2, DATA_1, DATA_2, ONE, TEN, A));
		this.dao.save(this.criaTransferencia(CONTA_1, CONTA_2, DATA_3, DATA_2, ONE, TEN, A));
		this.dao.save(this.criaTransferencia(CONTA_2, CONTA_3, DATA_3, DATA_1, ONE, TEN, B));
		this.dao.save(this.criaTransferencia(CONTA_2, CONTA_1, DATA_3, DATA_2, ONE, TEN, C));
		
		Assert.assertEquals(3,  this.dao.findBy(null, DATA_3, null, null, null, null).size());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void findByDataTransferencia() {
		this.verificaBancoZerado();
		
		this.dao.save(this.criaTransferencia(CONTA_1, CONTA_2, DATA_1, DATA_2, ONE, TEN, A));
		this.dao.save(this.criaTransferencia(CONTA_1, CONTA_2, DATA_2, DATA_3, ONE, TEN, A));
		this.dao.save(this.criaTransferencia(CONTA_2, CONTA_3, DATA_1, DATA_3, ONE, TEN, B));
		this.dao.save(this.criaTransferencia(CONTA_2, CONTA_1, DATA_2, DATA_3, ONE, TEN, C));
		
		Assert.assertEquals(3,  this.dao.findBy(DATA_3, null, null, null, null, null).size());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void findByValor() {
		this.verificaBancoZerado();
		
		this.dao.save(this.criaTransferencia(CONTA_1, CONTA_2, DATA_1, DATA_2, TEN, ONE, A));
		this.dao.save(this.criaTransferencia(CONTA_1, CONTA_2, DATA_2, DATA_3, TEN, ZERO, A));
		this.dao.save(this.criaTransferencia(CONTA_2, CONTA_3, DATA_1, DATA_3, TEN, ZERO, B));
		this.dao.save(this.criaTransferencia(CONTA_2, CONTA_1, DATA_2, DATA_3, TEN, ZERO, C));
		
		Assert.assertEquals(1,  this.dao.findBy(null, null, null, null, ONE, null).size());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void findByTipo() {
		this.verificaBancoZerado();
		
		this.dao.save(this.criaTransferencia(CONTA_1, CONTA_2, DATA_1, DATA_2, ONE, TEN, A));
		this.dao.save(this.criaTransferencia(CONTA_1, CONTA_2, DATA_1, DATA_2, ONE, TEN, A));
		this.dao.save(this.criaTransferencia(CONTA_1, CONTA_2, DATA_1, DATA_2, ONE, TEN, B));
		this.dao.save(this.criaTransferencia(CONTA_1, CONTA_2, DATA_1, DATA_2, ONE, TEN, C));
		
		Assert.assertEquals(2,  this.dao.findBy(null, null, null, null, null, A).size());
	}

	private void verificaBancoZerado() {
		List<Transferencia> transferenciasCadastradas = this.dao.findAll();
        Assert.assertEquals(0, transferenciasCadastradas.size());
	}
	
	private Transferencia criaTransferencia(String contaDestino, String contaOrigem, DateTime dataCadastro, 
			DateTime dataTransferencia, BigDecimal taxa, BigDecimal valor, TipoTransferencia tipo) {
        Transferencia transferencia = new Transferencia();
        transferencia.setContaDestino(contaDestino);
        transferencia.setContaOrigem(contaOrigem);
        transferencia.setDataCadastro(dataCadastro);
        transferencia.setDataTransferencia(dataTransferencia);
        transferencia.setTaxa(taxa);
        transferencia.setValor(valor);
        transferencia.setTipo(tipo);
        return transferencia;
    }
	
}
