package inventory.tracking.converters.user;

import inventory.tracking.converters.GenericConverter;
import inventory.tracking.dto.user.UserReadingDTO;
import inventory.tracking.entity.UserEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserReadingConverter extends GenericConverter<UserEntity, UserReadingDTO>
{
    @Override
    public UserReadingDTO convertToDTO(UserEntity entity)
    {
        return new UserReadingDTO(entity.getId(), entity.getUsername(), entity.getPassword());
    }

    @Override
    public UserEntity convertToEntityWithoutId(UserReadingDTO dto)
    {
        UserEntity entity = new UserEntity();
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());

        return entity;
    }

    @Override
    public UserEntity convertToEntity(UserReadingDTO dto)
    {
        UserEntity entity = convertToEntityWithoutId(dto);
        entity.setId(dto.getId()); // also set the ID from the DTO

        return entity;
    }
}
