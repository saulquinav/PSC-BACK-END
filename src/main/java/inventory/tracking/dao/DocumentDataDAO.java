package inventory.tracking.dao;

import inventory.tracking.entity.DocumentDataEntity;

public class DocumentDataDAO extends AbstractCrudDAO<DocumentDataEntity, Long>
{
    public DocumentDataDAO() { super(DocumentDataEntity.class); }
}
