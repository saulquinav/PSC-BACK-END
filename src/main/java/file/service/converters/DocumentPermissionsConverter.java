package file.service.converters;

import file.service.dto.DocumentPermissionDTO;
import file.service.entity.DocumentPermissionEntity;
import file.service.entity.DocumentPermissionId;

public class DocumentPermissionsConverter extends GenericConverter<DocumentPermissionEntity, DocumentPermissionDTO>
{
    @Override
    public DocumentPermissionDTO convertToDTO(DocumentPermissionEntity documentPermissionEntity)
    {
        DocumentPermissionDTO documentPermissionDTO = new DocumentPermissionDTO(documentPermissionEntity.getName());
        documentPermissionDTO.setUserId(documentPermissionEntity.getId().getUserId());
        documentPermissionDTO.setDocumentId(documentPermissionEntity.getId().getDocumentId());

        return documentPermissionDTO;
    }

    @Override
    public DocumentPermissionEntity convertToEntityWithoutId(DocumentPermissionDTO documentPermissionDTO)
    {
        DocumentPermissionEntity documentPermissionEntity = new DocumentPermissionEntity(documentPermissionDTO.getName());
        // should try to get the 'user' field?

        return documentPermissionEntity;
    }

    @Override
    public DocumentPermissionEntity convertToEntity(DocumentPermissionDTO documentPermissionDTO)
    {
        DocumentPermissionEntity permissionEntity = convertToEntityWithoutId(documentPermissionDTO);

        DocumentPermissionId documentPermissionId = new DocumentPermissionId(documentPermissionDTO.getUserId(),
                                                                            documentPermissionDTO.getDocumentId());
        // also copy the ID
        permissionEntity.setId(documentPermissionId);

        return permissionEntity;
    }
}
