package agendaTransferencias.model.dao;

import static agendaTransferencias.utils.TipoTransferencia.A;
import static agendaTransferencias.utils.TipoTransferencia.B;
import static agendaTransferencias.utils.TipoTransferencia.C;
import static agendaTransferencias.utils.builder.TransferenciaBuilder.umaTransferencia;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import agendaTransferencias.model.domain.Transferencia;

/**
 * Teste unitário para {@link TransferenciaDAO}
 * 
 * @author danilo.possarle
 * @created Dec 16, 2015
 */
public class TransferenciaDAOTest extends GenericDAOTest<TransferenciaDAO, Transferencia>{
	
	private static final String CONTA_1 = "65432-1";

    private static final String CONTA_2 = "12345-6";
    
    private static final String CONTA_3 = "78912-3";

    private static final DateTime DATA_1 = new DateTime().withTimeAtStartOfDay();

    private static final DateTime DATA_2 = DATA_1.plusDays(10);
    
    private static final DateTime DATA_3 = DATA_1.plusDays(15);

	@Test
	@Transactional
	@Rollback(true)
	public void findByContaDestino() {
		this.verificaBancoZerado();

		this.dao.save(umaTransferencia().comContaDestino(CONTA_1).build());
		this.dao.save(umaTransferencia().comContaDestino(CONTA_1).build());
		this.dao.save(umaTransferencia().comContaDestino(CONTA_1).build());
		this.dao.save(umaTransferencia().comContaDestino(CONTA_2).build());
		
		Assert.assertEquals(1, this.dao.findBy(null, null, null, CONTA_2, null, null).size());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void findByContaOrigem() {
		this.verificaBancoZerado();
		

		this.dao.save(umaTransferencia().comContaOrigem(CONTA_2).build());
		this.dao.save(umaTransferencia().comContaOrigem(CONTA_2).build());
		this.dao.save(umaTransferencia().comContaOrigem(CONTA_3).build());
		this.dao.save(umaTransferencia().comContaOrigem(CONTA_1).build());
		
		Assert.assertEquals(1, this.dao.findBy(null, null, CONTA_1, null, null, null).size());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void findByDataCadastro() {
		this.verificaBancoZerado();

		this.dao.save(umaTransferencia().comDataCadastro(DATA_1).build());
		this.dao.save(umaTransferencia().comDataCadastro(DATA_1).build());
		this.dao.save(umaTransferencia().comDataCadastro(DATA_1).build());
		this.dao.save(umaTransferencia().comDataCadastro(DATA_2).build());

		Assert.assertEquals(3,  this.dao.findBy(null, DATA_1, null, null, null, null).size());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void findByDataTransferencia() {
		this.verificaBancoZerado();
		
		this.dao.save(umaTransferencia().comDataTransferencia(DATA_2).build());
		this.dao.save(umaTransferencia().comDataTransferencia(DATA_3).build());
		this.dao.save(umaTransferencia().comDataTransferencia(DATA_3).build());
		this.dao.save(umaTransferencia().comDataTransferencia(DATA_3).build());
		
		Assert.assertEquals(3,  this.dao.findBy(DATA_3, null, null, null, null, null).size());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void findByValor() {
		this.verificaBancoZerado();
		
		this.dao.save(umaTransferencia().comValor(ONE).build());
		this.dao.save(umaTransferencia().comValor(ZERO).build());
		this.dao.save(umaTransferencia().comValor(ZERO).build());
		this.dao.save(umaTransferencia().comValor(ZERO).build());
		
		Assert.assertEquals(1,  this.dao.findBy(null, null, null, null, ONE, null).size());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void findByTipo() {
		this.verificaBancoZerado();
		
		this.dao.save(umaTransferencia().comTipo(A).build());
		this.dao.save(umaTransferencia().comTipo(A).build());
		this.dao.save(umaTransferencia().comTipo(B).build());
		this.dao.save(umaTransferencia().comTipo(C).build());
		
		Assert.assertEquals(2,  this.dao.findBy(null, null, null, null, null, A).size());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Transferencia criaEntidade() {
		return umaTransferencia().build();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void assertEquals(Transferencia entidade, Transferencia entidadeCadastrada){
		Assert.assertEquals(entidade.getContaDestino(), entidadeCadastrada.getContaDestino());
		Assert.assertEquals(entidade.getContaOrigem(), entidadeCadastrada.getContaOrigem());
		Assert.assertEquals(entidade.getDataCadastro(), entidadeCadastrada.getDataCadastro());
		Assert.assertEquals(entidade.getDataTransferencia(), entidadeCadastrada.getDataTransferencia());
		Assert.assertEquals(entidade.getTaxa(), entidadeCadastrada.getTaxa());
		Assert.assertEquals(entidade.getValor(), entidadeCadastrada.getValor());
		Assert.assertEquals(entidade.getTipo(), entidadeCadastrada.getTipo());
	}
	
}
