package file.service.converters;

import file.service.dto.DocumentDTO;
import file.service.entity.DocumentEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DocumentConverter extends GenericConverter<DocumentEntity, DocumentDTO>
{
    @Override
    public DocumentDTO convertToDTO(DocumentEntity entity)
    {
        DocumentDTO dto = new DocumentDTO(entity.getName(), entity.getData());

        return dto;
    }

    @Override
    public DocumentEntity convertToEntityWithoutId(DocumentDTO dto)
    {
        DocumentEntity entity = new DocumentEntity(dto.getName(), dto.getData());

        return entity;
    }

    @Override
    public DocumentEntity convertToEntity(DocumentDTO dto)
    {
        DocumentEntity entity = convertToEntityWithoutId(dto);

        entity.setId(dto.getId());

        return entity;
    }
}
