package file.service.service;

import file.service.converters.GenericConverter;
import file.service.converters.UserLightConverter;
import file.service.dao.CrudDAO;
import file.service.dao.UserDAO;
import file.service.entity.UserEntity;

import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

public class UserLightService extends CrudService<UserEntity, Long, UserLightDTO>
{
    @Inject // or @EJB - either will work
    private UserDAO userDAO; // a service always has one or more DAO instances

    @Inject
    private UserLightConverter converter;

    @Override
    protected CrudDAO<UserEntity, Long> getDao() { return userDAO; }

    @Override
    protected GenericConverter<UserEntity, UserLightDTO> getConverter() { return converter; }

    public Optional<UserLightDTO> getByUsername(String username)
    {
        Optional<UserEntity> optUserEntity = userDAO.findByUsername(username);

        if (optUserEntity.isPresent())
        {
            UserLightDTO dto = converter.fromEntityToCreateDTO(optUserEntity.get());
            return Optional.of(dto);
        }
        else
            return Optional.empty();
    }

    public List<UserLightDTO> getAllByUsernameStartingWith(String namePrefix)
    {
        List<UserEntity> entities = userDAO.findAllByUsernameStartingWith(namePrefix);

        List<UserLightDTO> dtos = converter.convertAllToDTO(entities);

        return dtos;
    }
}
