package agendaTransferencias.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import agendaTransferencias.model.domain.Persistable;

/**
 * {@link DAO} genérico
 * 
 * @param <BO> do tipo {@link Persistable}
 * @author danilo.possarle
 * @created Dec 9, 2015
 */
public abstract class GenericDAO<BO extends Persistable> implements DAO<BO> {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BO> findAll() {
        CriteriaBuilder critBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<BO> criteriaQuery = critBuilder.createQuery(this.getPersistableClass());
        Root<BO> variableRoot = criteriaQuery.from(this.getPersistableClass());
        criteriaQuery.select(variableRoot);
        return this.entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public BO findById(Serializable id) {
        Criteria criteria = this.createCriteria();
        criteria.add(Restrictions.eq("id", id));
        return (BO) criteria.uniqueResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(BO entity) {
        this.entityManager.persist(entity);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(BO entity) {
    	this.entityManager.remove(entity);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(Serializable id) {
    	this.remove(this.findById(id));
    }

    /**
     * Cria uma criteria.
     * 
     * @return {@link Criteria}
     */
    protected Criteria createCriteria() {
        Session session = this.entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(this.getPersistableClass());
        return criteria;
    }
}