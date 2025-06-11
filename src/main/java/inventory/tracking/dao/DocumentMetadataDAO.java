package inventory.tracking.dao;

import jakarta.ejb.Stateless;

@Stateless // the @Stateless annotation is recommended for DAO
// @Stateless is transactional by default, so we don't need to apply the @Transactional
// annotation to the methods
public class DocumentMetadataDAO extends AbstractCrudDAO<DocumentMetadataEntity, Long>
{
    public DocumentMetadataDAO() { super(DocumentMetadataEntity.class); }
}
