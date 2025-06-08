package file.service.converters.user;

import file.service.converters.GenericConverter;
import file.service.dto.user.UserPasswordUpdateDTO;
import file.service.entity.UserEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserPasswordUpdateConverter extends GenericConverter<UserEntity, UserPasswordUpdateDTO>
{
    @Override
    public UserPasswordUpdateDTO convertToNewDTO(UserEntity entity)
    {
        return new UserPasswordUpdateDTO(entity.getId(), entity.getUsername());
    }

    @Override
    public UserEntity convertToNewEntity(UserPasswordUpdateDTO dto)
    {
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setUsername(dto.getNewPassword());

        return entity;
    }
}
