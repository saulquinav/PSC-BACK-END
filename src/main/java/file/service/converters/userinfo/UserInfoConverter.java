package file.service.converters.userinfo;

import file.service.converters.GenericConverter;
import file.service.dto.userinfo.UserInfoDTO;
import file.service.entity.UserInfoEntity;

public class UserInfoConverter extends GenericConverter<UserInfoEntity, UserInfoDTO>
{
    @Override
    public UserInfoDTO convertToNewDTO(UserInfoEntity entity)
    {
        return new UserInfoDTO(entity.getFirstname(), entity.getSurname());
    }

    @Override
    public UserInfoEntity convertToNewEntity(UserInfoDTO dto)
    {
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setFirstname(dto.getFirstname());
        userInfoEntity.setSurname(dto.getSurname());

        return userInfoEntity;
    }
}
