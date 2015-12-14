package agendaTransferencias.model.dao;

import static org.hibernate.criterion.Order.desc;
import static org.springframework.util.StringUtils.isEmpty;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import agendaTransferencias.model.domain.Transferencia;
import agendaTransferencias.utils.TipoTransferencia;

/**
 * Implementação de {@link DAO} para entidade de {@link Transferencia}
 * 
 * @author danilo.possarle
 * @created Dec 9, 2015
 */
@Repository
public class TransferenciaDAO extends GenericDAO<Transferencia> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<Transferencia> getPersistableClass() {
        return Transferencia.class;
    }

    /**
     * Procura transferências de acordo com os parâmetros informados.
     * 
     * @param dataTransferencia data da tranferência
     * @param dataCadastro data em que a transferência foi cadastrada no sistema
     * @param contaOrigem número da conta de origem
     * @param contaDestino número da conta de destino
     * @param valor valor
     * @param tipo {@link TipoTransferencia}
     * @return {@link List} de {@link Transferencia} de acordo com os parâmetros informados.
     */
	public List<Transferencia> findBy(DateTime dataTransferencia, DateTime dataCadastro, 
			String contaOrigem, String contaDestino, BigDecimal valor, TipoTransferencia tipo) {		
		Criteria criteria = this.createCriteria();
		
		if (dataTransferencia != null) {
			criteria.add(Restrictions.eq("dataTransferencia", dataTransferencia));
		}
		if (dataCadastro != null) {
			criteria.add(Restrictions.eq("dataCadastro", dataCadastro));
		}
		if (isEmpty(contaOrigem)) {
			criteria.add(Restrictions.eq("contaOrigem", contaOrigem));
		}
		if (isEmpty(contaDestino)) {
			criteria.add(Restrictions.eq("contaDestino", contaDestino));
		}
		if (valor != null) {
			criteria.add(Restrictions.eq("valor", valor));
		}
		if (tipo != null) {
			criteria.add(Restrictions.eq("tipo", tipo));
		}
		
		criteria.addOrder(desc("dataCadastro"));
		
		return criteria.list();
	}

}
