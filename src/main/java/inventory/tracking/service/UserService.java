package inventory.tracking.service;

import inventory.tracking.auth.JwtUtil;
import inventory.tracking.converters.user.UserRegisterConverter;
import inventory.tracking.converters.user.UserReadingConverter;
import inventory.tracking.converters.user.UserPasswordUpdateConverter;
import inventory.tracking.dao.UserDAO;
import inventory.tracking.dto.user.*;
import inventory.tracking.entity.UserEntity;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;


// We use @Stateless 'Bean' when this bean doesn't hold any client-specific information between method calls.
@Stateless
public class UserService
{
    @Inject // or @EJB - either will work
    private UserDAO userDAO; // a service always has one or more DAO instances

    @Inject
    private UserRegisterConverter userRegisterConverter;

    @Inject
    private UserReadingConverter userReadingConverter;

    @Inject
    private UserPasswordUpdateConverter userPasswordUpdateConverter;

    public Optional<UserReadingDTO> findById(Long id)
    {
        return GenericServiceUtility.<UserEntity, Long, UserReadingDTO>findById(id, userDAO, userReadingConverter);
        // this also works, where explicit type arguments can be inferred
//        return GenericServiceUtility.findById(id, userDAO, userReadingConverter);
    }

    public List<UserReadingDTO> findAll()
    {
        return GenericServiceUtility.<UserEntity, Long, UserReadingDTO>findAll(userDAO, userReadingConverter);
    }

    // This method is analogous witht the usual 'create()' method of a Service
    public void register(UserRegisterDTO dto)
    {
        // TO-DO: check if user already exists
        String hashedPassword = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
        dto.setPassword(hashedPassword); // update the password with the encrypted password
        GenericServiceUtility.<UserEntity, Long, UserRegisterDTO>create(dto, userDAO, userRegisterConverter);
    }

    public void updatePassword(UserPasswordUpdateDTO dto)
    {
        Optional<UserEntity> foundEntity = userDAO.findById(dto.getId());

        if (foundEntity.isPresent())
        {
            UserEntity entity = foundEntity.get();
            entity.setPassword(dto.getNewPassword());
            userDAO.update(entity);
        }
    }

    public void update(UserUpdateDTO dto)
    {
        Optional<UserEntity> foundEntity = userDAO.findById(dto.getId());

        if (foundEntity.isPresent())
        {
            UserEntity entity = foundEntity.get();
            entity.setUsername(dto.getUsername());
            entity.setPassword(dto.getPassword());
            userDAO.update(entity);
        }
    }

    public void delete(Long id)
    {
        userDAO.delete(id);
    }

    public Optional<UserReadingDTO> findByUsername(String username)
    {
        Optional<UserEntity> foundEntity = userDAO.findByUsername(username);

        if (foundEntity.isPresent())
        {
            UserReadingDTO dto = userReadingConverter.convertToDTO(foundEntity.get());
            return Optional.of(dto);
        }
        else
            return Optional.empty();
    }

    public String login(UserLoginDTO userLoginDTO)
    {
        if (userLoginDTO.getUsername() == null ||
            userLoginDTO.getUsername().trim().isEmpty() ||
            userLoginDTO.getPassword() == null ||
            userLoginDTO.getPassword().trim().isEmpty())
        {
            return null;
        }

        Optional<UserEntity> userOpt = userDAO.findByUsername(userLoginDTO.getUsername());

        if (userOpt.isPresent())
        {
            UserEntity user = userOpt.get();

            if (BCrypt.checkpw(userLoginDTO.getPassword(), user.getPassword())) {
                return JwtUtil.generateToken(user.getUsername(), user.getRole());
            }
        }

        // Else, just return null
        return null;
    }
}
