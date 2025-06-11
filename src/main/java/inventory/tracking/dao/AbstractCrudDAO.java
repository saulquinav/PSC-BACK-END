package inventory.tracking.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

public class AbstractCrudDAO<E, ID> implements CrudDAO<E, ID>
{
    @PersistenceContext
    protected EntityManager entityManager;

    private final Class<E> entityClass;
//    private final Class<ID> idClass;

    public AbstractCrudDAO(Class<E> entityClass)
    {
        this.entityClass = entityClass;
    }

    @Override
    public Optional<E> findById(ID id)
    {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    @Override
    public List<E> findAll()
    {
        String query = "SELECT e FROM " + entityClass.getSimpleName() + " e";
        return entityManager.createQuery(query, entityClass).getResultList();
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
    public void delete(ID id)
    {
        findById(id).ifPresent(entity -> entityManager.remove(entity));
    }
}
