package file.service.service;

import file.service.entity.User;
import file.service.dto.UserDTO;
import file.service.dao.UserDAO;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.Optional;

// @Stateless annotation is optional, services can run without it too.
// We use @Stateless 'Bean' when this bean doesn't hold any client-specific information between method calls.
// But there are other annotations too: @Stateful, @Singleton, etc.
@Stateless
public class UserService
{
    @Inject
    private UserDAO userDAO; // a service always has one or more DAO instances

    public Optional<UserDTO> findById(Long id)
    {
        // Use the DAO to find the requested user
        Optional<User> optionalUser = userDAO.findById(id);

        // If there is a (non-null) value inside the Optional
        if (optionalUser.isPresent())
        {
            // Get the User value from inside the Optional
            User user = optionalUser.get();

            // Convert the value to DTO
            UserDTO userDTO = convertToDTO(user);

            // Return the user converted to DTO, wrapped inside an Optional
            return Optional.of(userDTO);
        }
        else
            // If nothing was found, then just return an Optional with no value
            return Optional.empty();
    }

    public void create(UserDTO userDTO)
    {
        User user = convertToEntity(userDTO);

        userDAO.create(user);
    }

    public void update(UserDTO userDTO)
    {
        User user = convertToEntity(userDTO);

        userDAO.update(user);
    }

    public void delete(Long id)
    {
        userDAO.delete(id);
    }

    // Method that converts from Entity to DTO
    private UserDTO convertToDTO(User user)
    {
        return new UserDTO(user.getUserName(), user.getFirstName(), user.getSurName());
    }

    // Method that converts from DTO to Entity
    private User convertToEntity(UserDTO userDTO)
    {
        return new User(userDTO.getUserName(), userDTO.getFirstName(), userDTO.getSurName());
    }
}
