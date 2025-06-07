package file.service.service;

import file.service.converters.user.UserCreationConverter;
import file.service.converters.user.UserReadingConverter;
import file.service.converters.user.UserUpdateConverter;
import file.service.dao.UserDAO;
import file.service.dto.user.UserCreationDTO;
import file.service.dto.user.UserLoginDTO;
import file.service.dto.user.UserReadingDTO;
import file.service.dto.user.UserUpdateDTO;
import file.service.entity.UserEntity;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// We use @Stateless 'Bean' when this bean doesn't hold any client-specific information between method calls.
@Stateless
public class UserService
{
    @Inject // or @EJB - either will work
    private UserDAO userDAO; // a service always has one or more DAO instances

    @Inject
    private UserCreationConverter userCreationConverter;

    @Inject
    private UserReadingConverter userReadingConverter;

    @Inject
    private UserUpdateConverter userUpdateConverter;

    public Optional<UserReadingDTO> findById(Long id)
    {
        // Use the DAO to find the requested user
        Optional<UserEntity> optionalEntity = userDAO.findById(id);

        // If there is a (non-null) value inside the Optional
        if (optionalEntity.isPresent())
        {
            // Get the User value from inside the Optional
            UserEntity entity = optionalEntity .get();

            // Convert the value to DTO
            UserReadingDTO dto = userReadingConverter.convertToNewDTO(entity);

            // Return the user converted to DTO, wrapped inside an Optional
            return Optional.of(dto);
        }
        else
            // If nothing was found, then just return an Optional with no value
            return Optional.empty();
    }

    public List<UserReadingDTO> findAll()
    {
        return userDAO.findAll().stream()
                .map(entity -> userReadingConverter.convertToNewDTO(entity))
                .collect(Collectors.toList());
    }

    public void create(UserCreationDTO dto)
    {
        UserEntity entity = userCreationConverter.convertToNewEntity(dto);

        userDAO.create(entity);
    }

    public void update(UserUpdateDTO dto)
    {
        Optional<UserEntity> foundEntity = userDAO.findById(dto.getId());

        if (foundEntity.isPresent())
        {
            UserEntity entity = userUpdateConverter.convertToNewEntity(dto);
            userDAO.update(entity);
        }
    }

    public void delete(Long id)
    {
        userDAO.delete(id);
    }

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
