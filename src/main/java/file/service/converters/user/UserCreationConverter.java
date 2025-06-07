package file.service.converters.user;


import file.service.converters.GenericConverter;
import file.service.dto.user.UserCreationDTO;
import file.service.entity.UserEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserCreationConverter extends GenericConverter<UserEntity, UserCreationDTO>
{
    @Override
    public UserCreationDTO convertToNewDTO(UserEntity entity)
    {
        return new UserCreationDTO(entity.getUsername(), entity.getPassword());
    }

    @Override
    public UserEntity convertToNewEntity(UserCreationDTO dto)
    {
        UserEntity entity = new UserEntity();
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());

        return entity;
    }
}
