package file.service.converters.docmetadata;

import file.service.converters.GenericConverter;
import file.service.dto.docmetadata.DocumentMetadataDTO;
import file.service.entity.DocumentMetadataEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DocumentMetadataConverter extends GenericConverter<DocumentMetadataEntity, DocumentMetadataDTO>
{

    @Override
    public DocumentMetadataDTO convertToDTO(DocumentMetadataEntity entity)
    {
        return new DocumentMetadataDTO(entity.getId(), entity.getName());
    }

    @Override
    public DocumentMetadataEntity convertToEntity(DocumentMetadataDTO dto)
    {
        DocumentMetadataEntity entity = new DocumentMetadataEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());

        return entity;
    }
}
