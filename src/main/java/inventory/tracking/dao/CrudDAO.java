package inventory.tracking.dao;

import java.util.List;
import java.util.Optional;

/* Type parameters:
** E - entity
** ID - the id of the entity (usually that type is Long or String, but could also
**      be a composite id) */
public interface CrudDAO<E, ID>
{
    public Optional<E> findById(ID id);

    public List<E> findAll();

    public void create(E entity);

    public E update(E entity);

    public void delete(ID id);
}
