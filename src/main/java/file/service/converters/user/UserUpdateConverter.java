package file.service.converters.user;

import file.service.converters.GenericConverter;
import file.service.dto.user.UserUpdateDTO;
import file.service.entity.UserEntity;

public class UserUpdateConverter extends GenericConverter<UserEntity, UserUpdateDTO>
{
    @Override
    public UserUpdateDTO convertToDTO(UserEntity entity)
    {
        return new UserUpdateDTO(entity.getId(), entity.getUsername());
    }

    @Override
    public UserEntity convertToEntity(UserUpdateDTO dto)
    {
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());

        return entity;
    }
}
