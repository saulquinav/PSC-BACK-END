package file.service.converters.document;

import file.service.converters.GenericConverter;
import file.service.dto.document.DocumentUpdateInfoDTO;
import file.service.entity.DocumentEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DocumentUpdateInfoConverter extends GenericConverter<DocumentEntity, DocumentUpdateInfoDTO>
{
    @Override
    public DocumentUpdateInfoDTO convertToNewDTO(DocumentEntity entity)
    {
        return new DocumentUpdateInfoDTO(entity.getId(), entity.getName());
    }

    @Override
    public DocumentEntity convertToNewEntity(DocumentUpdateInfoDTO dto)
    {
        DocumentEntity entity = new DocumentEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());

        return entity;
    }
}
