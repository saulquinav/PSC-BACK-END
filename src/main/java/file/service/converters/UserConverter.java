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
    public UserDTO convertToDTO(UserEntity userEntity)
    {
        UserDTO userDTO = new UserDTO(userEntity.getUsername(), userEntity.getPassword());

        userDTO.setId(userEntity.getId());

        Set<DocumentPermissionDTO> dtoPermissions = documentPermissionsConverter.convertAllToDTO(userEntity.getFilePermissions());

        userDTO.setDocumentPermissions(dtoPermissions);
        return userDTO;
    }

    // Method that converts from DTO to Entity
    @Override
    public UserEntity convertToEntityWithoutId(UserDTO userDTO)
    {
        UserEntity userEntity = new UserEntity(userDTO.getUsername(), userDTO.getPassword());

        Set<DocumentPermissionEntity> documentPermissionEntity = documentPermissionsConverter.convertAllToEntityWithoutId(userDTO.getDocumentPermissions());

        userEntity.setFilePermissions(documentPermissionEntity);

        return userEntity;
    }

    // Method that converts from DTO to Entity
    @Override
    public UserEntity convertToEntity(UserDTO userDTO)
    {
        UserEntity userEntity = convertToEntityWithoutId(userDTO);

        // Also copy the ID
        userEntity.setId(userDTO.getId());

        return userEntity;
    }
}
