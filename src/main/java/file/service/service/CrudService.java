package file.service.service;

import file.service.converters.GenericConverter;
import file.service.dao.CrudDAO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/* Type parameters:
** E - entity
** ID - the id of the Entity
** D - DTO  */
public abstract class CrudService<E, ID, D>
{
    protected abstract CrudDAO<E, ID> getDao();
    protected abstract GenericConverter<E, D> getConverter();

    public Optional<D> findById(ID id)
    {
        // Use the DAO to find the requested user
        Optional<E> optionalEntity = getDao().findById(id);

        // If there is a (non-null) value inside the Optional
        if (optionalEntity.isPresent())
        {
            // Get the User value from inside the Optional
            E entity = optionalEntity .get();

            // Convert the value to DTO
            D dto = getConverter().convertToDTO(entity);

            // Return the user converted to DTO, wrapped inside an Optional
            return Optional.of(dto);
        }
        else
            // If nothing was found, then just return an Optional with no value
            return Optional.empty();
    }

    public List<D> findAll()
    {
        return getDao().findAll().stream()
                .map(entity -> getConverter().convertToDTO(entity))
                .collect(Collectors.toList());
    }

    public void create(D dto)
    {
        E entity = getConverter().convertToEntityWithoutId(dto);

        getDao().create(entity);
    }

    public void update(D dto)
    {
        E entity = getConverter().convertToEntity(dto);

        getDao().update(entity);
    }

    public void delete(ID id)
    {
        getDao().delete(id);
    }
}
