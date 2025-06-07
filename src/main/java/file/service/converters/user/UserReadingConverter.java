package file.service.converters.user;

import file.service.converters.GenericConverter;
import file.service.dto.user.UserReadingDTO;
import file.service.entity.UserEntity;

public class UserReadingConverter extends GenericConverter<UserEntity, UserReadingDTO>
{
    @Override
    public UserReadingDTO convertToDTO(UserEntity entity)
    {
        return new UserReadingDTO(entity.getId(), entity.getUsername());
    }

    @Override
    public UserEntity convertToEntity(UserReadingDTO dto)
    {
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());

        return entity;
    }
}
