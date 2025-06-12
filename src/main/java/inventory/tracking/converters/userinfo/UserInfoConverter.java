package inventory.tracking.converters.userinfo;

import inventory.tracking.converters.GenericConverter;
import inventory.tracking.dto.userinfo.UserInfoDTO;
import inventory.tracking.entity.UserInfoEntity;

public class UserInfoConverter extends GenericConverter<UserInfoEntity, UserInfoDTO>
{
    @Override
    public UserInfoDTO convertToDTO(UserInfoEntity entity)
    {
        return new UserInfoDTO(entity.getFirstname(), entity.getSurname());
    }

    @Override
    public UserInfoEntity convertToEntityWithoutId(UserInfoDTO dto)
    {
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setId(dto.getId());
        userInfoEntity.setFirstname(dto.getFirstname());
        userInfoEntity.setSurname(dto.getSurname());

        return userInfoEntity;
    }

    @Override
    public UserInfoEntity convertToEntity(UserInfoDTO dto)
    {
        UserInfoEntity userInfoEntity = convertToEntityWithoutId(dto);
        userInfoEntity.setId(dto.getId()); // also set the ID from the DTO

        return userInfoEntity;
    }
}
