package file.service.dao;

import file.service.entity.DocumentEntity;
import jakarta.ejb.Stateless;

@Stateless // the @Stateless annotation is recommended for DAO
// @Stateless is transactional by default, so we don't need to apply the @Transactional
// annotation to the methods
public class DocumentDAO extends AbstractCrudDAO<DocumentEntity, Long>
{
    public DocumentDAO() { super(DocumentEntity.class); }
}
