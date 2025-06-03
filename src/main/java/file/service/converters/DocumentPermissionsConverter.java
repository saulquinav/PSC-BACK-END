package file.service.converters;

import file.service.dto.DocumentPermissionDTO;
import file.service.entity.DocumentPermissionEntity;
import file.service.entity.DocumentPermissionId;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DocumentPermissionsConverter extends GenericConverter<DocumentPermissionEntity, DocumentPermissionDTO>
{
    @Override
    public DocumentPermissionDTO convertToDTO(DocumentPermissionEntity entity)
    {
        DocumentPermissionDTO dto = new DocumentPermissionDTO(entity.getName());
        dto.setUserId(entity.getId().getUserId());
        dto.setDocumentId(entity.getId().getDocumentId());

        return dto;
    }

    @Override
    public DocumentPermissionEntity convertToEntityWithoutId(DocumentPermissionDTO dto)
    {
        DocumentPermissionEntity entity = new DocumentPermissionEntity(dto.getName());
        // should try to get the 'user' field?

        return entity;
    }

    @Override
    public DocumentPermissionEntity convertToEntity(DocumentPermissionDTO dto)
    {
        DocumentPermissionEntity entity = convertToEntityWithoutId(dto);

        // also copy the ID
        DocumentPermissionId id = new DocumentPermissionId(dto.getUserId(), dto.getDocumentId());
        entity.setId(id);

        return entity;
    }
}
