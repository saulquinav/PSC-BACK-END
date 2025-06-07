package file.service.converters.user;

import file.service.converters.GenericConverter;
import file.service.dto.user.UserUpdateDTO;
import file.service.entity.UserEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserUpdateConverter extends GenericConverter<UserEntity, UserUpdateDTO>
{
    @Override
    public UserUpdateDTO convertToNewDTO(UserEntity entity)
    {
        return new UserUpdateDTO(entity.getId(), entity.getUsername());
    }

    @Override
    public UserEntity convertToNewEntity(UserUpdateDTO dto)
    {
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());

        return entity;
    }
}
