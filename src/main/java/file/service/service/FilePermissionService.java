package file.service.service;

import file.service.dao.FilePermissionDAO;
import file.service.dto.FilePermissionDTO;
import file.service.dto.UserDTO;
import file.service.entity.FilePermission;

import file.service.entity.User;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.Optional;

// @Stateless annotation is optional, services can run without it too.
// We use @Stateless 'Bean' when this bean doesn't hold any client-specific information between method calls.
// But there are other annotations too: @Stateful, @Singleton, etc.
@Stateless
public class FilePermissionService
{
    @Inject
    private FilePermissionDAO filePermissionDAO; // a service always has one or more DAO instances

    public Optional<FilePermissionDTO> findById(Long id)
    {
        // Use the DAO to find the requested user
        Optional<FilePermission> optionalFilePerm = filePermissionDAO.findById(id);

        // If there is a (non-null) value inside the Optional
        if (optionalFilePerm.isPresent())
        {
            // Get the User value from inside the Optional
            FilePermission filePermission = optionalFilePerm.get();

            // Convert the value to DTO
            FilePermissionDTO filePermissionDTO = convertToDTO(filePermission);

            // Return the filePermission converted to DTO, wrapped inside an Optional
            return Optional.of(filePermissionDTO);
        }
        else
            // If nothing was found, then just return an Optional with no value
            return Optional.empty();
    }

    public void create(FilePermissionDTO filePermissionDTO)
    {
        FilePermission filePermission = convertToEntity(filePermissionDTO);

        filePermissionDAO.create(filePermission);
    }

    public void update(FilePermissionDTO filePermissionDTO)
    {
        FilePermission filePermission = convertToEntity(filePermissionDTO);

        filePermissionDAO.update(filePermission);
    }

    public void delete(Long id)
    {
        filePermissionDAO.delete(id);
    }

    // Method that converts from Entity to DTO
    private FilePermissionDTO convertToDTO(FilePermission filePermission)
    {
        return new FilePermissionDTO(filePermission.getUser(),
                                        filePermission.getFileMetadata(),
                                        filePermission.getPermissionType());
    }

    // Method that converts from DTO to Entity
    private FilePermission convertToEntity(FilePermissionDTO filePermissionDTO)
    {
        return new FilePermission(filePermissionDTO.getUser(),
                                    filePermissionDTO.getFileMetadata(),
                                    filePermissionDTO.getPermissionType());
    }
}
