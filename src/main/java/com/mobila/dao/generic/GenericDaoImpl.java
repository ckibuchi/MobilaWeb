package com.mobila.dao.generic;

/**
 * Created by ckibuchi on 8/19/2016.
 */

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * JPA implementation of the GenericRepository.
 *
 * @author Mikebavon
 *
 * @param <T>
 *            The persistent type
 * @param <ID>
 *            The primary key type
 */
/*@ManagedBean*/
public class GenericDaoImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {

    private final Class<T> persistentClass;

   // public Logger log = Logger.getLogger(getClass());


    @PersistenceContext(unitName="primary")
    protected EntityManager em;



    @Override
    public void setEm(EntityManager em){
        this.em = em;
    }

    @Override
    public EntityManager getEm(){
        return this.em;
    }

    @SuppressWarnings("unchecked")
    public GenericDaoImpl() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public GenericDaoImpl(final Class<T> persistentClass) {
        super();
        this.persistentClass = persistentClass;
    }

    /**
     * @see GenericDAO#getEntityClass()
     */
    @Override
    public Class<T> getEntityClass() {
        return persistentClass;
    }

    /**
     * @see GenericDAO#save(Object)
     * #save(java.lang.Object)
     */
    @Override
    public T save(T entity) throws Exception{
        entity = em.merge(entity);
        return entity;
    }

    /**
     * @see GenericDAO#delete(Object)
     */
    @Override
    public void delete(T entity) throws Exception{
        em.remove(em.contains(entity) ? entity : em.merge(entity));
        //	em.delete(entity);
    }

    /**
     * @see GenericDAO#deleteById(Object)
     */
    @Override
    public void deleteById(final ID id) throws Exception{
        T entity = em.find(persistentClass, id);
        if(entity != null) em.remove(entity);
    }

    @Override
    public void delete(ID ids []) throws Exception{
        int size = ids.length;

        for(int idx = 0; idx < size; idx++){
            T entity = em.find(persistentClass, ids[idx]);
            if(entity != null) em.remove(entity);
        }

    }

    /**
     * @see GenericDAO#deleteBatchById(Object)
     */
    @Override
    public void deleteBatchById(ID ids []) throws Exception{
        int size = ids.length;

        for(int idx = 0; idx < size; idx++){
            T entity = em.find(persistentClass, ids[idx]);
            if(entity != null) em.remove(entity);
        }

    }

    /**
     * @see GenericDAO#findById(Serializable)
     */
    @Override
    public T findById(final ID id) {
        final T result = em.find(persistentClass, id);
        return result;
    }



    /* (non-Javadoc)
     * @seecom.qis.Mobila.dao.generic.GenericDAO#getReference(java.io.Serializable)
     */
    @Override
    public T getReference(final ID id){
        return em.getReference(persistentClass, id);
    }




    /**
     * @see GenericDAO#findAll()
     */
    @Override
    public List<T> findAll() {
        return findByCriteria();
    }

    /**
     * @see GenericDAO#countAll()
     */
    @Override
    public int countAll() {
        return countByCriteria();
    }

    /**
     * @see GenericDAO#countByExample(Object)
     */
    @Override
    public int countByExample(final T exampleInstance) {
        Session session = (Session) em.getDelegate();
        Criteria crit = session.createCriteria(getEntityClass());
        crit.setProjection(Projections.rowCount());
        crit.add(Example.create(exampleInstance));

        return (Integer) crit.list().get(0);
    }

    /**
     * @see GenericDAO#findByExample(Object)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> findByExample(final T exampleInstance) {
        Session session = (Session) em.getDelegate();
        Criteria crit = session.createCriteria(getEntityClass());
        final List<T> result = crit.list();
        return result;
    }

    /**
     * @see GenericDAO#findByNamedQuery(String, Object...)
     * #findByNamedQuery(java.lang.String, java.lang.Object[])
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> findByNamedQuery(final String name, Object... params) {
        javax.persistence.Query query = em.createNamedQuery(name);

        for (int i = 0; i < params.length; i++) {
            query.setParameter(i + 1, params[i]);
        }

        final List<T> result = query.getResultList();
        return result;
    }

    /**
     * @see GenericDAO#findByNamedQuery(String, Map)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> findByNamedQuery(final String name,
                                    final Map<String, ? extends Object> params) {
        javax.persistence.Query query = em.createNamedQuery(name);

        for (final Map.Entry<String, ? extends Object> param : params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }

        return query.getResultList();
    }

    /**
     * @see GenericDAO#findByCriteria(int, int, ProjectionList, List)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> findByCriteria(final int firstResult,
                                  final int maxResults, final ProjectionList projectionList, final List<Criterion> criterion, final List<Order> orderBy){

        Session session = (Session) em.getDelegate();
        Criteria crit = session.createCriteria(getEntityClass());

        if(projectionList != null)
            crit.setProjection(projectionList);

        if(criterion != null){
            for (final Criterion c : criterion) {
                crit.add(c);
            }
        }

        if(orderBy!=null){
            for (final Order order : orderBy) {
                crit.addOrder(order);
            }
        }


        if (firstResult > 0) {
            crit.setFirstResult(firstResult);
        }

        if (maxResults > 0) {
            crit.setMaxResults(maxResults);
        }

        final List<T> result = crit.list();

        return result;
    }

    /**
     * @see GenericDAO#findByCriteria(int, int, ProjectionList, List)
     */
/*    @Override
    @SuppressWarnings("unchecked")
    public List<T> findByCriteria(final int firstResult,
                                  final int maxResults, final ProjectionList projectionList, final List<Criterion> criterion, final Order...orderBy){

        Session session = (Session) em.getDelegate();
        Criteria crit = session.createCriteria(getEntityClass());

        if(projectionList != null)
            crit.setProjection(projectionList);

        for (final Criterion c : criterion) {
            crit.add(c);
        }

        for (final Order order : orderBy) {
            crit.addOrder(order);
        }

        if (firstResult > 0) {
            crit.setFirstResult(firstResult);
        }

        if (maxResults > 0) {
            crit.setMaxResults(maxResults);
        }

        final List<T> result = crit.list();

        return result;
    }*/


    /**
     * Use this inside subclasses as a convenience method.
     */
    @Override
    public List<T> findByCriteria(final Criterion... criterion) {
        return findByCriteria(-1, -1, criterion);
    }


    /**
     * Use this inside subclasses as a convenience method.
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> findByCriteria(final int firstResult,
                                  final int maxResults, final List<Criterion> criterions) {
        Session session = null;
        Criteria crit = null;

        try{

            session = (Session) em.getDelegate();
            crit = session.createCriteria(getEntityClass());

            if(criterions != null){
                for (final Criterion c : criterions) {
                    crit.add(c);
                }
            }
            if (firstResult > 0) {
                crit.setFirstResult(firstResult);
            }

            if (maxResults > 0) {
                crit.setMaxResults(maxResults);
            }

            return crit.list();

        }catch(Exception exp){
          //  log.error(exp.getMessage(), exp);
        }finally{
            try{
                //session.close();
            }catch(Exception exp){
              //  log.warn(exp.getMessage(), exp);
            }
        }

        return new ArrayList<T>();

    }


    /**
     * Use this inside subclasses as a convenience method.
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> findByCriteria(final int firstResult,
                                  final int maxResults, final List<Criterion> criterion, List<Order> orderBy) {
        Session session = (Session) em.getDelegate();
        Criteria crit = session.createCriteria(getEntityClass());
        if(criterion != null){
            for (final Criterion c : criterion) {
                crit.add(c);
            }
        }
        if (firstResult > 0) {
            crit.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            crit.setMaxResults(maxResults);
        }
        if(orderBy!=null){
            for (final Order order : orderBy) {
                crit.addOrder(order);
            }
        }


        final List<T> result = crit.list();

        return result;

    }


    @Override
    @SuppressWarnings("unchecked")
    public List<T> list(final int firstResult,final int maxResults){
        try{
            Query qry = em.createQuery("from "+getEntityClass().getName());
            qry.setFirstResult(firstResult);
            qry.setMaxResults(maxResults);
            return qry.getResultList();
        }catch(javax.persistence.NoResultException ex){
           // log.warn("Could not find any results with the query\"from "+getEntityClass().getName());
        }

        return new ArrayList<T>();

    }

    /**
     * Use this inside subclasses as a convenience method.
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> findByCriteria(int firstResult, int maxResults, ProjectionList projectionList,
                                  final Map<String, String> aliases, List<Criterion> criterion, List<Order> orderBy) {
        Session session = (Session) em.getDelegate();
        Criteria crit = session.createCriteria(getEntityClass());

        if(aliases != null) {
            for(Map.Entry<String, String> alias : aliases.entrySet()) {
                crit.createAlias(alias.getKey(), alias.getValue());
            }
        }

        if(criterion != null) {
            for (final Criterion c : criterion) {
                crit.add(c);
            }
        }

        if (firstResult > 0) {
            crit.setFirstResult(firstResult);
        }

        if (maxResults > 0) {
            crit.setMaxResults(maxResults);
        }

        if(orderBy != null) {
            for (final Order order : orderBy) {
                crit.addOrder(order);
            }
        }

        final List<T> result = crit.list();

        return result;

    }


    /**
     * Use this inside subclasses as a convenience method.
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> findByCriteria(final int firstResult,
                                  final int maxResults, final Criterion... criterion) {
        Session session = (Session) em.getDelegate();
        Criteria crit = session.createCriteria(getEntityClass());

        for (final Criterion c : criterion) {
            crit.add(c);
        }

        if (firstResult > 0) {
            crit.setFirstResult(firstResult);
        }

        if (maxResults > 0) {
            crit.setMaxResults(maxResults);
        }

        final List<T> result = crit.list();
        return result;
    }



    /**
     *
     * @param criterion
     * @return
     */
    @Override
    public int countByCriteria(List<Criterion> criterion) {
        Session session = (Session) em.getDelegate();
        Criteria crit = session.createCriteria(getEntityClass());
        crit.setProjection(Projections.rowCount());

        for (final Criterion c : criterion) {
            crit.add(c);
        }

        return ((Long) crit.list().get(0)).intValue();
    }
    /**
     *
     * @param criterion
     * @return
     */
    @Override
    public int countByCriteria(Criterion... criterion) {
        Session session = (Session) em.getDelegate();
        Criteria crit = session.createCriteria(getEntityClass());
        crit.setProjection(Projections.rowCount());

        for (final Criterion c : criterion) {
            crit.add(c);
        }

        return ((Long) crit.list().get(0)).intValue();
    }

    /* (non-Javadoc)
     * @seecom.qis.Mobila.dao.generic.GenericDAO#findByNamedQueryAndNamedParams(java.lang.String, java.util.Map)
     */
    @Override

    @SuppressWarnings("unchecked")
    public List<T> findByNamedQueryAndNamedParams(final String name,
                                                  final Map<String, ? extends Object> params) {
        javax.persistence.Query query = em.createNamedQuery(name);

        for (final Map.Entry<String, ? extends Object> param : params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }

        final List<T> result = query.getResultList();
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByCriteria(final int firstResult,
                                  final int maxResults, final Map<String, String> aliases, final Criterion... criterion) {
        Session session = (Session) em.getDelegate();
        Criteria crit = session.createCriteria(getEntityClass());

        for(Map.Entry<String, String> alias : aliases.entrySet()) {
            crit.createAlias(alias.getKey(), alias.getValue());
        }

        for (final Criterion c : criterion) {
            crit.add(c);
        }

        if (firstResult > 0) {
            crit.setFirstResult(firstResult);
        }

        if (maxResults > 0) {
            crit.setMaxResults(maxResults);
        }

        final List<T> result = crit.list();
        return result;
    }

    /* (non-Javadoc)
     * @seecom.qis.Mobila.dao.generic.GenericDAO#findBySchemeId(java.lang.Long)
     */
    @Override
    public T findOneByScheme(Long schemeId) {
        List<T> items = this.findAllByScheme(schemeId);
        return items.size() > 0 ? items.get(0) : null;
    }

    /* (non-Javadoc)
     * @seecom.qis.Mobila.dao.generic.GenericDAO#findAllByScheme(java.lang.Long)
     */
    @Override
    public List<T> findAllByScheme(Long schemeId) {
        return findByCriteria(Restrictions.eq("scheme.id", schemeId));
    }

    /* (non-Javadoc)
     * 
     */
    @Override
    public void deleteAll() throws Exception {
        for (T t : findAll()) {
            delete(t);
        }

    }


}