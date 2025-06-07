package file.service.service;

import file.service.converters.GenericConverter;
import file.service.dao.CrudDAO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/* Type parameters:
** E - entity
** ID - the id of the Entity
** DC - DTO for Creation
** DD - DTO for Reading
** DU - DTO for Update */
public abstract class CrudService<E, ID, DC, DR, DU>
{
    protected abstract CrudDAO<E, ID> getDao();
    protected abstract GenericConverter<E, DC> getCreationConverter();
    protected abstract GenericConverter<E, DR> getReadingConverter();
    protected abstract GenericConverter<E, DU> getUpdateConverter();

    public Optional<DR> findById(ID id)
    {
        // Use the DAO to find the requested user
        Optional<E> optionalEntity = getDao().findById(id);

        // If there is a (non-null) value inside the Optional
        if (optionalEntity.isPresent())
        {
            // Get the User value from inside the Optional
            E entity = optionalEntity .get();

            // Convert the value to DTO
            DR dto = getReadingConverter().convertToDTO(entity);

            // Return the user converted to DTO, wrapped inside an Optional
            return Optional.of(dto);
        }
        else
            // If nothing was found, then just return an Optional with no value
            return Optional.empty();
    }

    public List<DR> findAll()
    {
        return getDao().findAll().stream()
                .map(entity -> getReadingConverter().convertToDTO(entity))
                .collect(Collectors.toList());
    }

    public void create(DC dto)
    {
        E entity = getCreationConverter().convertToEntity(dto);

        getDao().create(entity);
    }

    public void update(DU dto)
    {
//        Optional<E> entity = getDao().findById(id);

        E entity = getUpdateConverter().convertToEntity(dto);
        getDao().update(entity);
    }

    public void delete(ID id)
    {
        getDao().delete(id);
    }
}
