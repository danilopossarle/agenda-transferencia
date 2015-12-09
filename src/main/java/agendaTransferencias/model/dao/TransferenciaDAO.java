package agendaTransferencias.model.dao;

import agendaTransferencias.model.domain.Transferencia;

/**
 * Implementação de {@link DAO} para entidade de {@link Transferencia}
 * 
 * @author danilo.possarle
 * @created Dec 9, 2015
 */
public class TransferenciaDAO extends GenericDAO<Transferencia> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<Transferencia> getPersistableClass() {
        return Transferencia.class;
    }

}
