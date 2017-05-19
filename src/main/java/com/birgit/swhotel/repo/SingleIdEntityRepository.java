package com.birgit.swhotel.repo;


import com.birgit.swhotel.entity.SingleIdEntity;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;


public abstract class SingleIdEntityRepository<E extends SingleIdEntity> 
{
    
    @PersistenceContext(unitName="SwHotelPU")
    private EntityManager em;
    
    private Class<E> type;
    
    public SingleIdEntityRepository(Class<E> type) 
    {
        this.type = type;
    }
  
    public EntityManager getEntityManager() {
        return em;
    }

    public Class<E> getType() {
        return type;
    }

    @Transactional
    public E persist(E entity) {
        this.em.persist(entity);
        return entity;
    }
    
    @Transactional
    public E merge(E entity) {
        return this.em.merge(entity);
    }
    
    
    @Transactional
    public E delete(E entity)
    {
        entity = merge(entity);
        this.em.remove(entity);
        return entity;
    }
    
    public E getById(long id) 
    {
        return this.em.find(type, id);
    }
    
    public List<E> getAll() 
    {
        CriteriaBuilder criteriaBuilder = this.em.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(type);
        Root<E> rootEntry = criteriaQuery.from(type);
        CriteriaQuery<E> all = criteriaQuery.select(rootEntry);
        TypedQuery<E> allQuery = this.em.createQuery(all);

        List<E> resultList = allQuery.getResultList();
        if(resultList.isEmpty()) {
            return null;
        } else {
            return resultList;
        }  
    }
    
    public TypedQuery<E> createTypedQuery(String queryString) {
        TypedQuery<E> query = this.em.createQuery(queryString, this.type);
        return query;
    }
}
