package file.service.service;

import file.service.converters.DocumentPermissionsConverter;
import file.service.dao.DocumentPermissionDAO;
import file.service.dto.DocumentPermissionDTO;
import file.service.entity.DocumentPermissionEntity;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

// We use @Stateless 'Bean' when this bean doesn't hold any client-specific information between method calls.
@Stateless
public class DocumentPermissionService extends GenericService<DocumentPermissionEntity, DocumentPermissionDTO>
{
    @Inject
    private DocumentPermissionDAO documentPermissionDAO;

    @Inject
    private DocumentPermissionsConverter documentPermissionsConverter;

    @PostConstruct
    private void init()
    {
        setDao(documentPermissionDAO);
        setConverter(documentPermissionsConverter);
    }
}
