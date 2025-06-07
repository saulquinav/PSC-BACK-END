package file.service.converters.document;

import file.service.converters.GenericConverter;
import file.service.dto.document.DocumentCreationDTO;
import file.service.entity.DocumentEntity;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class DocumentCreationConverter extends GenericConverter<DocumentEntity, DocumentCreationDTO>
{
    @Override
    public DocumentCreationDTO convertToNewDTO(DocumentEntity entity)
    {
         return new DocumentCreationDTO(entity.getName(), entity.getData());
    }

    @Override
    public DocumentEntity convertToNewEntity(DocumentCreationDTO dto)
    {
        DocumentEntity entity = new DocumentEntity();
        entity.setName(dto.getName());
        entity.setData(dto.getData());

        return entity;
    }
}
