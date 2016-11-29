package com.mobila.dao.generic;

/**
 * Created by ckibuchi on 8/19/2016.
 */

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Generic Repository, providing basic CRUD operations
 *
 * @author Mikebavon
 *
 * @param <T> the entity type
 * @param <ID> the primary key type
 */
public interface GenericDAO<T, ID extends Serializable> {

    public void setEm(EntityManager em);

    public EntityManager getEm();

    /**
     * Get the Class of the entity.
     *
     * @return the class
     */
    Class<T> getEntityClass();

    /**
     * Find an entity by its primary key
     *
     * @param id the primary key
     * @return the entity
     */
    T findById(final ID id);

    /**
     * Find an entity by schemeId
     *
     * @param id the primary key
     * @return the entity
     */
    T findOneByScheme(Long schemeId);



    /**
     * Get reference of
     * the entity whose state may be lazily fetched.
     * @param id
     * @return
     */
    T getReference(final ID id);

    /**
     * Load all entities.
     *
     * @return the list of entities
     */
    List<T> findAll();


    /**
     * Load all entities tied to a scheme.
     *
     * @return the list of entities
     */
    List<T> findAllByScheme(Long schemeId);


    /**
     * Find entities based on an example.
     *
     * @param exampleInstance the example
     * @return the list of entities
     */
    List<T> findByExample(final T exampleInstance);

    /**
     * Find using a named query.
     *
     * @param queryName the name of the query
     * @param params the query parameters
     *
     * @return the list of entities
     */
    List<T> findByNamedQuery(final String queryName, Object... params);

    /**
     * Find using a named query.
     *
     * @param queryName the name of the query
     * @param params the query parameters
     *
     * @return the list of entities
     */
    List<T> findByNamedQuery(final String queryName, final Map<String, ? extends Object> params);

    /**
     * Count all entities.
     *
     * @return the number of entities
     */
    int countAll();

    /**
     * Count entities based on an example.
     *
     * @param exampleInstance the search criteria
     * @return the number of entities
     */
    int countByExample(final T exampleInstance);


    /**
     * save an entity. This can be either a INSERT or UPDATE in the database.
     *
     * @param entity the entity to save
     *
     * @return the saved entity
     */
    T save(final T entity) throws Exception;


    /**
     * delete an entities from the database.
     *
     * @param entity the entity to delete
     */
    void deleteAll() throws Exception;

    /**
     * delete an entity from the database.
     *
     * @param entity the entity to delete
     */
    void delete(final T entity) throws Exception;

    /**
     * delete an entity by its primary key
     *
     * @param id the primary key of the entity to delete
     */
    void deleteById(final ID id) throws Exception;

    /**
     * delete batch entities by their primary keys array
     *
     * @param ids [] the primary key of entities to delete
     */
    void deleteBatchById(ID ids[]) throws Exception;

    /**
     * delete batch entities by their primary keys array
     *
     * @param ids [] the primary key of entities to delete
     */
    void delete(ID ids[]) throws Exception;

    /**
     *
     * @param firstResult
     * @param maxResults
     * @param projectionList
     * @param criterion
     * @return
     */
    public List<T> findByCriteria(final int firstResult, final int maxResults, ProjectionList projectionList, final List<Criterion> criterion, final List<Order> orderBy);


    //final int maxResults, ProjectionList projectionListfirstResult, final List<Criterion> criterion, final List<Order> orderBy);

    public int countByCriteria(Criterion... criterion);

    public List<T> findByCriteria(final int firstResult,
                                  final int maxResults, final List<Criterion> criterions) ;

    public List<T> findByCriteria(int firstResult, int maxResults,
                                  Criterion... criterion);

    public List<T> findByCriteria(final int firstResult,
                                  final int maxResults, final List<Criterion> criterion, List<Order> order);


    public List<T> list(final int firstResult, final int maxResults);

    List<T> findByCriteria(int firstResult, int maxResults, ProjectionList projectionList,
                           final Map<String, String> aliases, List<Criterion> criterion, List<Order> orderBy);

    List<T> findByCriteria(Criterion... criterion);

    public int countByCriteria(List<Criterion> criterion);

    /**
     * Find using a named query.
     *
     * @param queryName the name of the query
     * @param params the query parameters
     *
     * @return the list of entities
     */
    List<T> findByNamedQueryAndNamedParams(final String queryName, final Map<String, ? extends Object> params);

    /**
     *
     * @param firstResult
     * @param maxResults
     * @param aliases
     * @param criterion
     * @return
     */
    List<T> findByCriteria(int firstResult, int maxResults,
                           Map<String, String> aliases, final Criterion... criterion);


}