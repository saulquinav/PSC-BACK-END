package file.service.converters.user;

import file.service.converters.GenericConverter;
import file.service.dto.user.UserReadingDTO;
import file.service.entity.UserEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserReadingConverter extends GenericConverter<UserEntity, UserReadingDTO>
{
    @Override
    public UserReadingDTO convertToNewDTO(UserEntity entity)
    {
        return new UserReadingDTO(entity.getId(), entity.getUsername());
    }

    @Override
    public UserEntity convertToNewEntity(UserReadingDTO dto)
    {
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());

        return entity;
    }
}
