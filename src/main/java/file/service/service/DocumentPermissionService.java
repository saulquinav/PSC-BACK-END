package file.service.service;

import file.service.converters.docpermission.DocumentPermissionConverter;
import file.service.dao.DocumentPermissionDAO;
import file.service.dto.docpermission.DocumentPermissionDTO;
import file.service.entity.DocumentPermissionEntity;
import file.service.entity.DocumentPermissionId;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// We use @Stateless 'Bean' when this bean doesn't hold any client-specific information between method calls.
@Stateless
public class DocumentPermissionService
{
    @Inject
    private DocumentPermissionDAO dao;

    @Inject
    private DocumentPermissionConverter converter;

    public Optional<DocumentPermissionDTO> findById(DocumentPermissionId id)
    {
        // Use the DAO to find the requested user
        Optional<DocumentPermissionEntity> optionalEntity = dao.findById(id);

        // If there is a (non-null) value inside the Optional
        if (optionalEntity.isPresent())
        {
            // Get the User value from inside the Optional
            DocumentPermissionEntity entity = optionalEntity .get();

            // Convert the value to DTO
            DocumentPermissionDTO dto = converter.convertToNewDTO(entity);

            // Return the user converted to DTO, wrapped inside an Optional
            return Optional.of(dto);
        }
        else
            // If nothing was found, then just return an Optional with no value
            return Optional.empty();
    }

    public List<DocumentPermissionDTO> findAll()
    {
        return dao.findAll().stream()
                .map(entity -> converter.convertToNewDTO(entity))
                .collect(Collectors.toList());
    }

    public void create(DocumentPermissionDTO dto)
    {
        DocumentPermissionEntity entity = converter.convertToNewEntity(dto);

        dao.create(entity);
    }

    public void update(DocumentPermissionDTO dto)
    {
        DocumentPermissionId id = new DocumentPermissionId(dto.getUserId(), dto.getDocumentId());
        Optional<DocumentPermissionEntity> foundEntity = dao.findById(id);

        if (foundEntity.isPresent())
        {
            DocumentPermissionEntity entity = converter.convertToNewEntity(dto);
            dao.update(entity);
        }
    }

    public void delete(DocumentPermissionId id)
    {
        dao.delete(id);
    }
}
