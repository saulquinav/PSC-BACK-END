package file.service.converters;

import file.service.dto.userinfo.UserInfoDTO;
import file.service.entity.UserInfoEntity;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserInfoConverter extends GenericConverter<UserInfoEntity, UserInfoDTO>
{
    @Override
    public UserInfoDTO fromEntityToCreateDTO(UserInfoEntity entity)
    {
        UserInfoDTO dto = new UserInfoDTO(entity.getFirstname(), entity.getSurname());

        return dto;
    }

    @Override
    public UserInfoEntity convertToEntityWithoutId(UserInfoDTO dto)
    {
        UserInfoEntity entity = new UserInfoEntity(dto.getFirstname(), dto.getSurname());

        return entity;
    }

    @Override
    public UserInfoEntity convertToEntity(UserInfoDTO dto)
    {
        UserInfoEntity entity = convertToEntityWithoutId(dto);
        entity.setId(dto.getId());

        return entity;
    }
}
