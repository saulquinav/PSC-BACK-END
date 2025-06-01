package file.service.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

public class AbstractGenericDAO<T> implements GenericDAO<T>
{
    @PersistenceContext
    protected EntityManager entityManager;

    private final Class<T> entityClass;

    public AbstractGenericDAO(Class<T> entityClass)
    {
        this.entityClass = entityClass;
    }

    @Override
    public Optional<T> findById(Long id)
    {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    @Override
    public List<T> findAll()
    {
        String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e";
        return entityManager.createQuery(jpql, entityClass).getResultList();
    }

    @Override
    public void create(T entity)
    {
        entityManager.persist(entity);
    }

    @Override
    public T update(T entity)
    {
        return entityManager.merge(entity);
    }

    @Override
    public void delete(Long id)
    {
        findById(id).ifPresent(entity -> entityManager.remove(entity));
    }
}
