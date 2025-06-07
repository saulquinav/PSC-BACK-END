package file.service.service;

import file.service.converters.GenericConverter;
import file.service.converters.UserConverter;
import file.service.converters.user.UserCreationConverter;
import file.service.converters.user.UserReadingConverter;
import file.service.converters.user.UserUpdateConverter;
import file.service.dao.CrudDAO;
import file.service.dao.UserDAO;
import file.service.dto.user.UserCreationDTO;
import file.service.dto.user.UserLoginDTO;
import file.service.dto.user.UserReadingDTO;
import file.service.dto.user.UserUpdateDTO;
import file.service.entity.UserEntity;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.Optional;

// We use @Stateless 'Bean' when this bean doesn't hold any client-specific information between method calls.
@Stateless
public class UserService extends CrudService<UserEntity, Long, UserCreationDTO, UserReadingDTO, UserUpdateDTO>
{
    @Inject // or @EJB - either will work
    private UserDAO userDAO; // a service always has one or more DAO instances

    @Inject
    private UserCreationConverter userConverter;

    @Inject
    private UserReadingConverter userReadingConverter;

    @Inject
    private UserUpdateConverter userUpdateConverter;

    @Override
    protected CrudDAO<UserEntity, Long> getDao() { return userDAO; }

    @Override
    protected GenericConverter<UserEntity, UserDTO> getConverter() { return userConverter; }

    public Optional<UserEntity> findByUsername(String username)
    {
        return userDAO.findByUsername(username);
    }

    public Optional<UserEntity> authenticate(UserLoginDTO userLoginDTO)
    {
        if (userLoginDTO.getUsername() == null ||
                userLoginDTO.getUsername().trim().isEmpty() ||
                userLoginDTO.getPassword() == null ||
                userLoginDTO.getPassword().trim().isEmpty())
        {
            return Optional.empty();
        }

        Optional<UserEntity> userOpt = userDAO.findByUsername(userLoginDTO.getUsername());

        if (userOpt.isPresent())
        {
            // In a real application, we should compare hashed passwords here:
            // if (BCrypt.checkpw(password, user.getPassword())) {
            //    return user;
            // }

            UserEntity userEntity = userOpt.get();

            // But for this example, we're doing a plain text comparison (NOT SECURE FOR PRODUCTION)
            if (userEntity.getPassword().equals(userLoginDTO.getPassword()))
                return userOpt;
        }
        return Optional.empty();
    }
}
