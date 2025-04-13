package file.service.service;

import file.service.dao.FileMetadataDAO;
import file.service.dao.FilePermissionDAO;
import file.service.dto.FileMetadataDTO;
import file.service.dto.FilePermissionDTO;
import file.service.entity.FileMetadata;
import file.service.entity.FilePermission;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.Optional;

// @Stateless annotation is optional, services can run without it too.
// We use @Stateless 'Bean' when this bean doesn't hold any client-specific information between method calls.
// But there are other annotations too: @Stateful, @Singleton, etc.
@Stateless
public class FileMatadataService
{
    @Inject
    private FileMetadataDAO fileMetadataDAO; // a service always has one or more DAO instances

    public Optional<FileMetadataDTO> findById(Long id)
    {
        // Use the DAO to find the requested user
        Optional<FileMetadata> optionalFileMetadata = fileMetadataDAO.findById(id);

        // If there is a (non-null) value inside the Optional
        if (optionalFileMetadata.isPresent())
        {
            // Get the User value from inside the Optional
            FileMetadata fileMetadata = optionalFileMetadata.get();

            // Convert the value to DTO
            FileMetadataDTO fileMetadataDTO = convertToDTO(fileMetadata);

            // Return the filePermission converted to DTO, wrapped inside an Optional
            return Optional.of(fileMetadataDTO);
        }
        else
            // If nothing was found, then just return an Optional with no value
            return Optional.empty();
    }

    public void create(FileMetadataDTO fileMetadataDTO)
    {
        FileMetadata fileMetadata = convertToEntity(fileMetadataDTO);

        fileMetadataDAO.create(fileMetadata);
    }

    public void update(FileMetadataDTO fileMatadataDTO)
    {
        FileMetadata fileMetadata = convertToEntity(fileMatadataDTO);

        fileMetadataDAO.update(fileMetadata);
    }

    public void delete(Long id)
    {
        fileMetadataDAO.delete(id);
    }

    // Method that converts from Entity to DTO
    private FileMetadataDTO convertToDTO(FileMetadata fileMetadata)
    {
        return new FileMetadataDTO(fileMetadata.getIsEdited(),
                                    fileMetadata.getName(),
                                    fileMetadata.getUsers(),
                                    fileMetadata.getFilePermissions());
    }

    // Method that converts from DTO to Entity
    private FileMetadata convertToEntity(FileMetadataDTO fileMetadataDTO)
    {
        return new FileMetadata(fileMetadataDTO.getIsEdited(),
                                fileMetadataDTO.getName(),
                                fileMetadataDTO.getUsers(),
                                fileMetadataDTO.getFilePermissions());
    }
}
