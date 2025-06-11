package inventory.tracking.converters.docpermission;

import inventory.tracking.converters.GenericConverter;
import inventory.tracking.dto.docpermission.DocumentPermissionDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DocumentPermissionConverter extends GenericConverter<DocumentPermissionEntity, DocumentPermissionDTO>
{
    @Override
    public DocumentPermissionDTO convertToDTO(DocumentPermissionEntity entity)
    {
        DocumentPermissionId id = entity.getId();

        return new DocumentPermissionDTO(id.getUserId(),
                                        id.getDocumentId(),
                                        entity.getName(),
                                        entity.getPermissionType());
    }

    @Override
    public DocumentPermissionEntity convertToEntityWithoutId(DocumentPermissionDTO dto)
    {
        DocumentPermissionEntity entity = new DocumentPermissionEntity();
        entity.setName(dto.getName());
        entity.setPermissionType(dto.getPermissionType());

        return entity;
    }

    @Override
    public DocumentPermissionEntity convertToEntity(DocumentPermissionDTO dto)
    {
        DocumentPermissionEntity entity = convertToEntityWithoutId(dto);

        DocumentPermissionId documentPermissionId = new DocumentPermissionId(dto.getUserId(), dto.getDocumentId());
        entity.setId(documentPermissionId); // also set the ID from the DTO

        return entity;
    }
}
