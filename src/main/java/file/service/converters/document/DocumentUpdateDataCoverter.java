package file.service.converters.document;

import file.service.converters.GenericConverter;
import file.service.dto.document.DocumentUpdateDataDTO;
import file.service.entity.DocumentEntity;

public class DocumentUpdateDataCoverter extends GenericConverter<DocumentEntity, DocumentUpdateDataDTO>
{
    @Override
    public DocumentUpdateDataDTO convertToNewDTO(DocumentEntity entity)
    {
        return new DocumentUpdateDataDTO(entity.getId(), entity.getData());
    }

    @Override
    public DocumentEntity convertToNewEntity(DocumentUpdateDataDTO dto)
    {
        DocumentEntity entity = new DocumentEntity();
        entity.setId(dto.getId());
        entity.setData(dto.getData());

        return entity;
    }
}
