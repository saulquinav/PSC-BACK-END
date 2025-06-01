package file.service.service;

import file.service.converters.DocumentPermissionsConverter;
import file.service.converters.UserConverter;
import file.service.dao.UserDAO;
import file.service.dto.UserDTO;
import file.service.dto.UserLoginDTO;
import file.service.entity.DocumentPermissionEntity;
import file.service.entity.UserEntity;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

// We use @Stateless 'Bean' when this bean doesn't hold any client-specific information between method calls.
@Stateless
public class UserService
{
//    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    @Inject // or @EJB - either will work
    private UserDAO userDAO; // a service always has one or more DAO instances

    private static final UserConverter userConverter = new UserConverter();
    private static final DocumentPermissionsConverter documentPermissionsConverter = new DocumentPermissionsConverter();

    public Optional<UserDTO> findById(Long id)
    {
        // Use the DAO to find the requested user
        Optional<UserEntity> optionalUser = userDAO.findById(id);

        // If there is a (non-null) value inside the Optional
        if (optionalUser.isPresent())
        {
            // Get the User value from inside the Optional
            UserEntity userEntity = optionalUser.get();

            // Convert the value to DTO
            UserDTO userDTO = userConverter.convertToDTO(userEntity);

            // Return the user converted to DTO, wrapped inside an Optional
            return Optional.of(userDTO);
        }
        else
            // If nothing was found, then just return an Optional with no value
            return Optional.empty();
    }

    public Optional<UserEntity> findByUsername(String username)
    {
        return userDAO.findByUsername(username);
    }

    public List<UserDTO> findAll()
    {
        return userDAO.findAll().stream()
                .map(userEntity -> userConverter.convertToDTO(userEntity))
                .collect(Collectors.toList());
    }

    public void create(UserDTO userDTO)
    {
        UserEntity userEntity = userConverter.convertToEntityWithoutId(userDTO);

        if (userDTO.getDocumentPermissions() != null)
        {
            Set<DocumentPermissionEntity> documentPermissionEntities = documentPermissionsConverter.convertAllToEntityWithoutId(userDTO.getDocumentPermissions());
            userEntity.setFilePermissions(documentPermissionEntities);
        }

        userDAO.create(userEntity);
    }

    public void update(UserDTO userDTO)
    {
        UserEntity userEntity = userConverter.convertToEntity(userDTO);

        userDAO.update(userEntity);
    }

    public void delete(Long id)
    {
        userDAO.delete(id);
    }

//    public Optional<User> authenticate(UserLoginDTO userLoginDTO)
//    {
//        Optional<User> userOpt = userDAO.findByUsername(userLoginDTO.getUsername());
//        if (userOpt.isPresent())
//        {
//            User user = userOpt.get();
//            // In production, use hashed password check
//            if (user.getPassword().equals(userLoginDTO.getPassword()))
//                return Optional.of(user);
//        }
//        return Optional.empty();
//    }

    public Optional<UserEntity> authenticate(UserLoginDTO userLoginDTO)
    {
        if (userLoginDTO.getUsername() == null ||
                userLoginDTO.getUsername().trim().isEmpty() ||
                userLoginDTO.getPassword() == null ||
                userLoginDTO.getPassword().trim().isEmpty())
        {
//            LOGGER.log(Level.WARNING, "Attempted authentication with null or empty username/password.");
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
//            else
//                LOGGER.log(Level.WARNING, "Authentication failed for user {0}: Incorrect password.", username);
        }
//        else
//            LOGGER.log(Level.WARNING, "Authentication failed: User {0} not found.", username);

        return Optional.empty();
    }
}
