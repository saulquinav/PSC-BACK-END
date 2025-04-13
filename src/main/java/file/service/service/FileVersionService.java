package file.service.service;

import file.service.dao.FileVersionDAO;
import file.service.dto.FileVersionDTO;
import file.service.entity.FileVersion;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.Optional;

// @Stateless annotation is optional, services can run without it too.
// We use @Stateless 'Bean' when this bean doesn't hold any client-specific information between method calls.
// But there are other annotations too: @Stateful, @Singleton, etc.
@Stateless
public class FileVersionService
{
    @Inject
    FileVersionDAO fileVersionDAO; // a service always has one or more DAO instances

    public Optional<FileVersionDTO> findById(Long id)
    {
        // Use the DAO to find the requested user
        Optional<FileVersion> optionalFileVersion = fileVersionDAO.findById(id);

        // If there is a (non-null) value inside the Optional
        if (optionalFileVersion.isPresent())
        {
            // Get the User value from inside the Optional
            FileVersion fileVersion = optionalFileVersion.get();

            // Convert the value to DTO
            FileVersionDTO fileVersionDTO = convertToDTO(fileVersion);

            // Return the user converted to DTO, wrapped inside an Optional
            return Optional.of(fileVersionDTO);
        }
        else
            // If nothing was found, then just return an Optional with no value
            return Optional.empty();
    }

    public void create(FileVersionDTO fileVersionDTO)
    {
        FileVersion fileVersion = convertToEntity(fileVersionDTO);

        fileVersionDAO.create(fileVersion);
    }

    public void update(FileVersionDTO fileVersionDTO)
    {
        FileVersion fileVersion = convertToEntity(fileVersionDTO);

        fileVersionDAO.update(fileVersion);
    }

    public void delete(Long id)
    {
        fileVersionDAO.delete(id);
    }

    // Method that converts from Entity to DTO
    private FileVersionDTO convertToDTO(FileVersion fileVersion)
    {
        return new FileVersionDTO(fileVersion.getEditingUserId(),
                                    fileVersion.getName(),
                                    fileVersion.getIsEdited(),
                                    fileVersion.getData());
    }

    // Method that converts from DTO to Entity
    private FileVersion convertToEntity(FileVersionDTO fileVersionDTO)
    {
        return new FileVersion(fileVersionDTO.getEditingUserId(),
                                fileVersionDTO.getName(),
                                fileVersionDTO.getIsEdited(),
                                fileVersionDTO.getData());
    }
}
