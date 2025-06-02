package file.service.service;

import file.service.converters.DocumentConverter;
import file.service.dao.DocumentDAO;
import file.service.dto.DocumentDTO;
import file.service.entity.DocumentEntity;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

// We use @Stateless 'Bean' when this bean doesn't hold any client-specific information between method calls.
@Stateless
public class DocumentService extends CrudService<DocumentEntity, Long, DocumentDTO>
{
    @Inject
    private DocumentDAO documentDAO;

    @Inject
    private DocumentConverter converter;

    @PostConstruct
    private void init()
    {
        setDao(documentDAO);
        setConverter(converter);
    }
}
