package inventory.tracking.converters.docdata;

import inventory.tracking.converters.GenericConverter;
import inventory.tracking.dto.docdata.DocumentDataDTO;
import inventory.tracking.entity.DocumentDataEntity;

public class DocumentDataConverter extends GenericConverter<DocumentDataEntity, DocumentDataDTO>
{
    @Override
    public DocumentDataDTO convertToDTO(DocumentDataEntity entity)
    {
        return new DocumentDataDTO(entity.getId(), entity.getData());
    }

    @Override
    public DocumentDataEntity convertToEntityWithoutId(DocumentDataDTO dto)
    {
        DocumentDataEntity entity = new DocumentDataEntity();
        entity.setData(dto.getData());

        return entity;
    }

    @Override
    public DocumentDataEntity convertToEntity(DocumentDataDTO dto)
    {
        DocumentDataEntity entity = convertToEntityWithoutId(dto);
        entity.setId(dto.getId()); // also set the ID from the DTO

        return entity;
    }
}
