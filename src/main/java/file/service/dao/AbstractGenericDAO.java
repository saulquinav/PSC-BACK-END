package file.service.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

public class AbstractGenericDAO<E> implements GenericDAO<E>
{
    @PersistenceContext
    protected EntityManager entityManager;

    private final Class<E> entityClass;

    public AbstractGenericDAO(Class<E> entityClass)
    {
        this.entityClass = entityClass;
    }

    @Override
    public Optional<E> findById(Long id)
    {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    @Override
    public List<E> findAll()
    {
        String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e";
        return entityManager.createQuery(jpql, entityClass).getResultList();
    }

    @Override
    public void create(E entity)
    {
        entityManager.persist(entity);
    }

    @Override
    public E update(E entity)
    {
        return entityManager.merge(entity);
    }

    @Override
    public void delete(Long id)
    {
        findById(id).ifPresent(entity -> entityManager.remove(entity));
    }
}
