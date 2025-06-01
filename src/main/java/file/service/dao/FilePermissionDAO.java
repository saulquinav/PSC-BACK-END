package file.service.dao;

import file.service.entity.DocumentPermissionEntity;

public class FilePermissionDAO extends AbstractGenericDAO<DocumentPermissionEntity>
{
    public FilePermissionDAO()
    {
        super(DocumentPermissionEntity.class);
    }

    // only find() and create() methods should exist for this DAO
}
