package file.service.converters;

import file.service.dto.DocumentPermissionDTO;
import file.service.dto.UserDTO;
import file.service.entity.DocumentPermissionEntity;
import file.service.entity.UserEntity;

import java.util.Set;

public class UserConverter extends GenericConverter<UserEntity, UserDTO>
{
    private final static DocumentPermissionsConverter documentPermissionsConverter = new DocumentPermissionsConverter();

    // Method that converts from Entity to DTO by associating the fields one by one.
    // This method does not search for relationship-owned auxiliary elements in the database,
    // it just copies field to field.
    @Override
    public UserDTO convertToDTO(UserEntity entity)
    {
        UserDTO dto = new UserDTO(entity.getUsername(), entity.getPassword());

        dto.setId(entity.getId());

        Set<DocumentPermissionDTO> dtoPermissions = documentPermissionsConverter.convertAllToDTO(entity.getFilePermissions());

        dto.setDocumentPermissions(dtoPermissions);
        return dto;
    }

    // Method that converts from DTO to Entity
    @Override
    public UserEntity convertToEntityWithoutId(UserDTO dto)
    {
        UserEntity entity = new UserEntity(dto.getUsername(), dto.getPassword());

        Set<DocumentPermissionEntity> documentPermissionEntity = documentPermissionsConverter.convertAllToEntityWithoutId(dto.getDocumentPermissions());

        entity.setFilePermissions(documentPermissionEntity);

        return entity;
    }

    // Method that converts from DTO to Entity
    @Override
    public UserEntity convertToEntity(UserDTO dto)
    {
        UserEntity entity = convertToEntityWithoutId(dto);

        // Also copy the ID
        entity.setId(dto.getId());

        return entity;
    }
}
