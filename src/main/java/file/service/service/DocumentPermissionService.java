package file.service.service;

import file.service.converters.DocumentPermissionsConverter;
import file.service.converters.GenericConverter;
import file.service.dao.CrudDAO;
import file.service.dao.DocumentPermissionDAO;
import file.service.dto.permission.DocumentPermissionDTO;
import file.service.entity.DocumentPermissionEntity;
import file.service.entity.DocumentPermissionId;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

// We use @Stateless 'Bean' when this bean doesn't hold any client-specific information between method calls.
@Stateless
public class DocumentPermissionService extends CrudService<DocumentPermissionEntity, DocumentPermissionId, DocumentPermissionDTO>
{
    @Inject
    private DocumentPermissionDAO documentPermissionDAO;

    @Inject
    private DocumentPermissionsConverter documentPermissionsConverter;

    @Override
    protected CrudDAO<DocumentPermissionEntity, DocumentPermissionId> getDao() { return documentPermissionDAO; }

    @Override
    protected GenericConverter<DocumentPermissionEntity, DocumentPermissionDTO> getConverter() { return documentPermissionsConverter; }
}
