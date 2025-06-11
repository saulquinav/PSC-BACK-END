package inventory.tracking.service;

import inventory.tracking.converters.userinfo.UserInfoConverter;
import inventory.tracking.dao.UserInfoDAO;
import inventory.tracking.dto.userinfo.UserInfoDTO;
import inventory.tracking.entity.UserInfoEntity;

import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

public class UserInfoService
{
    @Inject
    public UserInfoDAO dao;

    @Inject
    public UserInfoConverter converter;

    public Optional<UserInfoDTO> findById(Long id)
    {
        return GenericServiceUtility.<UserInfoEntity, Long, UserInfoDTO>findById(id, dao, converter);
    }

    public List<UserInfoDTO> findAll()
    {
        return GenericServiceUtility.<UserInfoEntity, Long, UserInfoDTO>findAll(dao, converter);
    }

    public void create(UserInfoDTO dto)
    {
        GenericServiceUtility.<UserInfoEntity, Long, UserInfoDTO>create(dto, dao, converter);
    }

    public void update(UserInfoDTO dto)
    {
        GenericServiceUtility.<UserInfoEntity, Long, UserInfoDTO>update(dto, dao, converter);
    }

    public void delete(Long id)
    {
        dao.delete(id);
    }
}
