package file.service.service;

import file.service.converters.GenericConverter;
import file.service.dao.GenericDAO;
import file.service.dto.UserDTO;
import file.service.entity.DocumentPermissionEntity;
import file.service.entity.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/* Type parameters:
** E - entity
** D - DTO  */
public class GenericService<E, D>
{
    protected GenericDAO<E> dao;

    protected GenericConverter<E, D> converter;

    public Optional<D> findById(Long id)
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

    public List<D> findAll()
    {
        return dao.findAll().stream()
                .map(entity -> converter.convertToDTO(entity))
                .collect(Collectors.toList());
    }

    public void create(D dto)
    {
        E entity = converter.convertToEntityWithoutId(dto);

        dao.create(entity);
    }

    public void update(D dto)
    {
        E entity = converter.convertToEntity(dto);

        dao.update(entity);
    }

    public void delete(Long id)
    {
        dao.delete(id);
    }

    protected void setDao(GenericDAO<E> dao)
    {
        this.dao = dao;
    }

    protected void setConverter(GenericConverter<E, D> converter)
    {
        this.converter = converter;
    }
}
