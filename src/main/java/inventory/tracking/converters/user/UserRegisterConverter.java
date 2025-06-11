package inventory.tracking.converters.user;


import inventory.tracking.converters.GenericConverter;
import inventory.tracking.dto.user.UserRegisterDTO;
import inventory.tracking.entity.UserEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRegisterConverter extends GenericConverter<UserEntity, UserRegisterDTO>
{
    @Override
    public UserRegisterDTO convertToDTO(UserEntity entity)
    {
        return new UserRegisterDTO(entity.getUsername(), entity.getPassword());
    }

    @Override
    public UserEntity convertToEntityWithoutId(UserRegisterDTO dto)
    {
        UserEntity entity = new UserEntity();
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());

        return entity;
    }

    @Override
    public UserEntity convertToEntity(UserRegisterDTO dto)
    {
        // We return 'null' because the UserRegisterDTO is for user creation only, so it
        // never has an ID, because it hasn't been created yet
        return null;
    }
}
