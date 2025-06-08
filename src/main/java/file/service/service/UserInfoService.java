package file.service.service;

import file.service.converters.userinfo.UserInfoConverter;
import file.service.dao.UserInfoDAO;
import file.service.dto.userinfo.UserInfoDTO;
import file.service.entity.UserInfoEntity;

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
//        // Use the DAO to find the requested user
//        Optional<UserInfoEntity> optionalEntity = dao.findById(id);
//
//        // If there is a (non-null) value inside the Optional
//        if (optionalEntity.isPresent())
//        {
//            // Get the User value from inside the Optional
//            UserInfoEntity entity = optionalEntity .get();
//
//            // Convert the value to DTO
//            UserInfoDTO dto = converter.convertToDTO(entity);
//
//            // Return the user converted to DTO, wrapped inside an Optional
//            return Optional.of(dto);
//        }
//        else
//            // If nothing was found, then just return an Optional with no value
//            return Optional.empty();

        return GenericServiceUtility.<UserInfoEntity, Long, UserInfoDTO>findById(id, dao, converter);
    }

    public List<UserInfoDTO> findAll()
    {
//        return dao.findAll().stream()
//                .map(entity -> converter.convertToDTO(entity))
//                .collect(Collectors.toList());

        return GenericServiceUtility.<UserInfoEntity, Long, UserInfoDTO>findAll(dao, converter);
    }

    public void create(UserInfoDTO dto)
    {
//        UserInfoEntity entity = converter.convertToEntity(dto);
//        dao.create(entity);

        GenericServiceUtility.<UserInfoEntity, Long, UserInfoDTO>create(dto, dao, converter);
    }

    public void update(UserInfoDTO dto)
    {
//        Optional<UserInfoEntity> foundEntity =dao.findById(dto.getId());
//
//        if (foundEntity.isPresent())
//        {
//            UserInfoEntity entity = foundEntity.get();
//            entity.setFirstname(dto.getFirstname());
//            entity.setSurname(dto.getSurname());
//            dao.update(entity);
//        }

        GenericServiceUtility.<UserInfoEntity, Long, UserInfoDTO>update(dto, dao, converter);
    }

    public void delete(Long id)
    {
        dao.delete(id);
    }
}
