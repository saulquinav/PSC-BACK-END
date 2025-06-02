package file.service.service;

import file.service.converters.UserInfoConverter;
import file.service.dao.UserInfoDAO;
import file.service.dto.UserInfoDTO;
import file.service.entity.UserInfoEntity;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;

public class UserInfoService extends CrudService<UserInfoEntity, Long, UserInfoDTO>
{
    @Inject
    public UserInfoDAO userInfoDAO;

    @Inject
    public UserInfoConverter userInfoConverter;

    @PostConstruct
    private void init()
    {
        setDao(userInfoDAO);
        setConverter(userInfoConverter);
    }
}
