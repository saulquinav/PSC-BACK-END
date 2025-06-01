package file.service.service;

import file.service.converters.GenericConverter;
import file.service.dao.GenericDAO;
import file.service.dto.UserDTO;
import file.service.entity.UserEntity;

import java.util.List;
import java.util.Optional;

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
            D dto = userConverter.convertToDTO(userEntity);

            // Return the user converted to DTO, wrapped inside an Optional
            return Optional.of(userDTO);
        }
        else
            // If nothing was found, then just return an Optional with no value
            return Optional.empty();
    }

    public Optional<D> findByUsername(String username)
    {
        // code omitted
    }

    public List<D> findAll()
    {
        // code omitted
    }

    public void create(D dto)
    {
        // code omitted
    }

    public void update(D dto)
    {
        // code omitted
    }

    public void delete(Long id)
    {
        // code omitted
    }
}
