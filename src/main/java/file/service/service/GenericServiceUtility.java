package file.service.service;

import file.service.converters.GenericConverter;
import file.service.dao.CrudDAO;
import file.service.entity.IdOwner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/* Type parameters:
 ** E - entity
 ** ID - the id of the Entity
 ** D - DTO  */
public class GenericServiceUtility
{
    public static <E, ID, D> Optional<D> findById(ID id, CrudDAO<E, ID> dao, GenericConverter<E, D> converter)
    {
        // Use the DAO to find the requested user
        Optional<E> optionalEntity = dao.findById(id);

        // If there is a (non-null) value inside the Optional
        if (optionalEntity.isPresent())
        {
            // Get the User value from inside the Optional
            E entity = optionalEntity .get();

            // Convert the value to DTO
            D dto = converter.convertToDTO(entity);

            // Return the user converted to DTO, wrapped inside an Optional
            return Optional.of(dto);
        }
        else
            // If nothing was found, then just return an Optional with no value
            return Optional.empty();
    }

    public static <E, ID, D> List<D> findAll(CrudDAO<E, ID> dao, GenericConverter<E, D> converter)
    {
        return dao.findAll().stream()
                .map(entity -> converter.convertToDTO(entity))
                .collect(Collectors.toList());
    }

    public static <E, ID, D> void create(D dto, CrudDAO<E, ID> dao, GenericConverter<E, D> converter)
    {
        /* When creating a new Entity, the ID doesn't yet exist, so we convert from DTO
        ** to Entity without getting the ID from the DTO */
        E entity = converter.convertToEntityWithoutId(dto);

        dao.create(entity);
    }

    public static <E, ID, D extends IdOwner<ID>> void update(D dto, CrudDAO<E, ID> dao, GenericConverter<E, D> converter)
    {
        Optional<E> foundEntity = dao.findById(dto.getId());

        if (foundEntity.isPresent())
        {
            E entity = converter.convertToEntity(dto);
            dao.update(entity);
        }
    }

    public static <E, ID>void delete(ID id, CrudDAO<E, ID> dao)
    {
        dao.delete(id);
    }
}
