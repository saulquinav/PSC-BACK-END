package file.service.converters;

import file.service.entity.UserEntity;

public class UserLightConverter extends GenericConverter<UserEntity, UserLightDTO>
{

    @Override
    public UserLightDTO fromEntityToCreateDTO(UserEntity entity)
    {
        UserLightDTO dto = new UserLightDTO();

        dto.setId(entity.getId());

        return dto;
    }

    @Override
    public UserEntity convertToEntityWithoutId(UserLightDTO dto)
    {
        return new UserEntity(dto.getUsername());
    }

    @Override
    public UserEntity convertToEntity(UserLightDTO dto)
    {
        UserEntity entity = new UserEntity(dto.getUsername());
        entity.setId(dto.getId());

        return entity;
    }
}
