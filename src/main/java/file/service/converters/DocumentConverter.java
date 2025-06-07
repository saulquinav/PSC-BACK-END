package file.service.converters;

import file.service.dto.document.DocumentCreationDTO;
import file.service.entity.DocumentEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DocumentConverter extends GenericConverter<DocumentEntity, DocumentCreationDTO>
{
    @Override
    public DocumentCreationDTO fromEntityToCreateDTO(DocumentEntity entity)
    {
        DocumentCreationDTO dto = new DocumentCreationDTO(entity.getName(), entity.getData());

        return dto;
    }

    @Override
    public DocumentEntity convertToEntityWithoutId(DocumentCreationDTO dto)
    {
        DocumentEntity entity = new DocumentEntity(dto.getName(), dto.getData());

        return entity;
    }

    @Override
    public DocumentEntity convertToEntity(DocumentCreationDTO dto)
    {
        DocumentEntity entity = convertToEntityWithoutId(dto);

        entity.setId(dto.getId());

        return entity;
    }
}
