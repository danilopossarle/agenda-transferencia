package agendaTransferencias.model.dao;

import java.util.List;

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
import agendaTransferencias.model.domain.Persistable;

/**
 * Teste genérico para {@link DAO}s 
 *
 * @param <D> que extende {@link DAO}
 * @param <BO> que extende {@link Persistable}
 * @author danilo.possarle
 * @created Dec 16, 2015 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AgendaTransferenciasApplication.class)
@WebAppConfiguration
public abstract class GenericDAOTest<D extends DAO, BO extends Persistable> {

	@Autowired
	protected D dao;
	
	@Test
    @Transactional
    @Rollback(true)
    public void save() {
		List<BO> entidadesCadastradas;
        this.verificaBancoZerado();
		
        BO entidade = this.criaEntidade();
        this.dao.save(entidade);
        entidadesCadastradas = this.dao.findAll();
         
        Assert.assertEquals(1, entidadesCadastradas.size());
        
        BO entidadeCadastrada = entidadesCadastradas.get(0);
		this.assertEquals(entidade, entidadeCadastrada);
    }
	
	@Test
	@Transactional
	@Rollback(true)
	public void remove() {
		List<BO> entidadesCadastradas;
		this.verificaBancoZerado();
		
        BO entidade = this.criaEntidade();
        this.dao.save(entidade);
        entidadesCadastradas = this.dao.findAll();
        Assert.assertEquals(1, entidadesCadastradas.size());
        
        this.dao.remove(entidade);
        this.verificaBancoZerado();
	}

	@Test
	@Transactional
	@Rollback(true)
	public void findAll() {
		this.verificaBancoZerado();
		
		this.dao.save(this.criaEntidade());
		this.dao.save(this.criaEntidade());
		this.dao.save(this.criaEntidade());
		this.dao.save(this.criaEntidade());
		
		Assert.assertEquals(4, this.dao.findAll().size());
	}
	
	protected void verificaBancoZerado() {
		List<BO> entidadesCadastradas = this.dao.findAll();
        Assert.assertEquals(0, entidadesCadastradas.size());
	}
	
	protected abstract BO criaEntidade();
	
	protected abstract void assertEquals(BO entidade, BO entidadeCadastrada);
	
}
