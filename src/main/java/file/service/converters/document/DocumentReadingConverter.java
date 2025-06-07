package file.service.converters.document;

import file.service.converters.GenericConverter;
import file.service.dto.document.DocumentReadingDTO;
import file.service.entity.DocumentEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DocumentReadingConverter extends GenericConverter<DocumentEntity, DocumentReadingDTO>
{

    @Override
    public DocumentReadingDTO convertToNewDTO(DocumentEntity entity)
    {
        return new DocumentReadingDTO(entity.getId(), entity.getName(), entity.getData());
    }

    @Override
    public DocumentEntity convertToNewEntity(DocumentReadingDTO dto)
    {
        DocumentEntity entity = new DocumentEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setData(dto.getData());

        return entity;
    }
}
