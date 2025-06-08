package file.service.converters.docpermission;

import file.service.converters.GenericConverter;
import file.service.dto.docpermission.DocumentPermissionDTO;
import file.service.entity.DocumentPermissionEntity;
import file.service.entity.DocumentPermissionId;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DocumentPermissionConverter extends GenericConverter<DocumentPermissionEntity, DocumentPermissionDTO>
{
    @Override
    public DocumentPermissionDTO convertToNewDTO(DocumentPermissionEntity entity)
    {
        DocumentPermissionId id = entity.getId();

        return new DocumentPermissionDTO(id.getUserId(),
                                        id.getDocumentId(),
                                        entity.getName(),
                                        entity.getPermissionType());
    }

    @Override
    public DocumentPermissionEntity convertToNewEntity(DocumentPermissionDTO dto)
    {
        DocumentPermissionEntity entity = new DocumentPermissionEntity();

        DocumentPermissionId documentPermissionId = new DocumentPermissionId(dto.getUserId(), dto.getDocumentId());
        entity.setId(documentPermissionId);

        entity.setName(dto.getName());
        entity.setPermissionType(dto.getPermissionType());

        return entity;
    }
}
