package file.service.converters.user;

import file.service.converters.GenericConverter;
import file.service.dto.user.UserPasswordUpdateDTO;
import file.service.entity.UserEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserPasswordUpdateConverter extends GenericConverter<UserEntity, UserPasswordUpdateDTO>
{
    @Override
    public UserPasswordUpdateDTO convertToDTO(UserEntity entity)
    {
        return new UserPasswordUpdateDTO(entity.getId(), entity.getUsername());
    }

    @Override
    public UserEntity convertToEntityWithoutId(UserPasswordUpdateDTO dto)
    {
        UserEntity entity = new UserEntity();
        entity.setUsername(dto.getNewPassword());

        return entity;
    }

    @Override
    public UserEntity convertToEntity(UserPasswordUpdateDTO dto)
    {
        UserEntity entity = convertToEntityWithoutId(dto);
        entity.setId(dto.getId()); // also set the ID from the DTO

        return entity;
    }
}
