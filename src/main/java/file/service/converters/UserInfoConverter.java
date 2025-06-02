package file.service.converters;

import file.service.dto.UserInfoDTO;
import file.service.entity.UserInfoEntity;
import jakarta.persistence.Column;

//@Column(name = "firstname", nullable = false)
//private String firstname;
//
//@Column(name = "surname", nullable = false)
//private String surname;

public class UserInfoConverter extends GenericConverter<UserInfoEntity, UserInfoDTO>
{
    @Override
    public UserInfoDTO convertToDTO(UserInfoEntity entity)
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
