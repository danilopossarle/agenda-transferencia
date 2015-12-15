package agendaTransferencias.model.dao;

import java.io.Serializable;
import java.util.List;

import agendaTransferencias.model.domain.Persistable;

/**
 * Interface para implementação dos DAOs necessários para a aplicação
 * 
 * @param <BO> do tipo {@link Persistable}
 * @author danilo.possarle
 * @created Dec 9, 2015
 */
public interface DAO<BO extends Persistable> {
    /**
     * Encontra todos os registros da base
     * 
     * @return {@link List} com todos os registros da base
     */
    List<BO> findAll();

    /**
     * Encontra o registro da base identificado por aquele id
     * 
     * @param id o id
     * @return o registro da base identificado por aquele id
     */
    BO findById(Serializable id);

    /**
     * Salva a entidade informada
     * 
     * @param entity entidade que deve ser salva
     */
    void save(BO entity);
    
    /**
     * Remove a entidade informada da base
     * 
     * @param entity entidade que deve ser removida
     */
    void remove(BO entity);
    

    /**
     * Remove a entidade informada da base
     * 
     * @param id o id da entidade a ser removida
     */
    void remove(Serializable id);

    /**
     * Recupera a classe do BO
     * 
     * @return a classe BO
     */
    Class<BO> getPersistableClass();
}
