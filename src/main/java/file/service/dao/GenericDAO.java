package file.service.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T>
{
    public Optional<T> findById(long id);

    public List<T> findAll();

    public void create(T entity);

    public T update(T entity);

    public void delete(long id);
}
