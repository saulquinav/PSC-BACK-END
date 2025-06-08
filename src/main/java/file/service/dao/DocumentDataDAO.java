package file.service.dao;

import file.service.entity.DocumentDataEntity;

public class DocumentDataDAO extends AbstractCrudDAO<DocumentDataEntity, Long>
{
    public DocumentDataDAO() { super(DocumentDataEntity.class); }
}
