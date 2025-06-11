package inventory.tracking.converters.docmetadata;

import inventory.tracking.converters.GenericConverter;
import inventory.tracking.dto.docmetadata.DocumentMetadataDTO;
import inventory.tracking.entity.DocumentMetadataEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DocumentMetadataConverter extends GenericConverter<DocumentMetadataEntity, DocumentMetadataDTO>
{
    @Override
    public DocumentMetadataDTO convertToDTO(DocumentMetadataEntity entity)
    {
        return new DocumentMetadataDTO(entity.getId(), entity.getName());
//        return new DocumentMetadataDTO(entity.getName());
    }

    @Override
    public DocumentMetadataEntity convertToEntityWithoutId(DocumentMetadataDTO dto)
    {
        DocumentMetadataEntity entity = new DocumentMetadataEntity();
        entity.setName(dto.getName());

        return entity;
    }

    @Override
    public DocumentMetadataEntity convertToEntity(DocumentMetadataDTO dto)
    {
        DocumentMetadataEntity entity = convertToEntityWithoutId(dto);
        entity.setId(dto.getId()); // also set the ID from the DTO

        return entity;
    }
}
