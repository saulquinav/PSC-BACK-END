package file.service.service;

import file.service.converters.UserConverter;
import file.service.dao.UserDAO;
import file.service.dto.UserDTO;
import file.service.dto.UserLoginDTO;
import file.service.entity.UserEntity;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.Optional;

// We use @Stateless 'Bean' when this bean doesn't hold any client-specific information between method calls.
@Stateless
public class UserService extends CrudService<UserEntity, Long, UserDTO>
{
//    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    @Inject // or @EJB - either will work
    private UserDAO userDAO; // a service always has one or more DAO instances

    @Inject
    private UserConverter userConverter;

    @PostConstruct
    private void init()
    {
        setDao(userDAO);
        setConverter(userConverter);
    }

    public Optional<UserEntity> findByUsername(String username)
    {
        return userDAO.findByUsername(username);
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
