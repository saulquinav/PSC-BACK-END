package file.service.converters.user;

import file.service.converters.GenericConverter;
import file.service.dto.user.UserUpdateDTO;
import file.service.entity.UserEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserUpdateConverter extends GenericConverter<UserEntity, UserUpdateDTO>
{
    @Override
    public UserUpdateDTO convertToDTO(UserEntity entity)
    {
        return new UserUpdateDTO(entity.getId(), entity.getUsername(), entity.getPassword());
    }

    @Override
    public UserEntity convertToEntityWithoutId(UserUpdateDTO dto)
    {
        UserEntity entity = new UserEntity();
        entity.setUsername(dto.getPassword());

        return entity;
    }

    @Override
    public UserEntity convertToEntity(UserUpdateDTO dto)
    {
        UserEntity entity = convertToEntityWithoutId(dto);
        entity.setId(dto.getId()); // also set the ID from the DTO

        return entity;
    }
}
