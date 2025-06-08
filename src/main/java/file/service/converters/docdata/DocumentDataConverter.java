package file.service.converters.docdata;

import file.service.converters.GenericConverter;
import file.service.dto.docdata.DocumentDataDTO;
import file.service.entity.DocumentDataEntity;

public class DocumentDataConverter extends GenericConverter<DocumentDataEntity, DocumentDataDTO>
{
    @Override
    public DocumentDataDTO convertToDTO(DocumentDataEntity entity)
    {
        return new DocumentDataDTO(entity.getId(), entity.getData());
    }

    @Override
    public DocumentDataEntity convertToEntity(DocumentDataDTO dto)
    {
        DocumentDataEntity entity = new DocumentDataEntity();
        entity.setId(dto.getId());
        entity.setData(dto.getData());

        return entity;
    }
}
