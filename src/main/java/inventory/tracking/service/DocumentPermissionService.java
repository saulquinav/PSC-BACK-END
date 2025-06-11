package inventory.tracking.service;

import inventory.tracking.converters.docpermission.DocumentPermissionConverter;
import inventory.tracking.dao.DocumentPermissionDAO;
import inventory.tracking.dto.docpermission.DocumentPermissionDTO;
import inventory.tracking.entity.DocumentPermissionEntity;
import inventory.tracking.entity.DocumentPermissionId;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

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
//        // Use the DAO to find the requested user
//        Optional<DocumentPermissionEntity> optionalEntity = dao.findById(id);
//
//        // If there is a (non-null) value inside the Optional
//        if (optionalEntity.isPresent())
//        {
//            // Get the User value from inside the Optional
//            DocumentPermissionEntity entity = optionalEntity .get();
//
//            // Convert the value to DTO
//            DocumentPermissionDTO dto = converter.convertToDTO(entity);
//
//            // Return the user converted to DTO, wrapped inside an Optional
//            return Optional.of(dto);
//        }
//        else
//            // If nothing was found, then just return an Optional with no value
//            return Optional.empty();

        return GenericServiceUtility.<DocumentPermissionEntity, DocumentPermissionId, DocumentPermissionDTO>findById(id, dao, converter);
    }

    public List<DocumentPermissionDTO> findAll()
    {
//        return dao.findAll().stream()
//                .map(entity -> converter.convertToDTO(entity))
//                .collect(Collectors.toList());
        return GenericServiceUtility.<DocumentPermissionEntity, DocumentPermissionId, DocumentPermissionDTO>findAll(dao, converter);
    }

    public void create(DocumentPermissionDTO dto)
    {
//        DocumentPermissionEntity entity = converter.convertToEntity(dto);
//        dao.create(entity);

        GenericServiceUtility.<DocumentPermissionEntity, DocumentPermissionId, DocumentPermissionDTO>create(dto, dao, converter);
    }

    public void update(DocumentPermissionDTO dto)
    {
        DocumentPermissionId id = new DocumentPermissionId(dto.getUserId(), dto.getDocumentId());
//        Optional<DocumentPermissionEntity> foundEntity = dao.findById(id);
//
//        if (foundEntity.isPresent())
//        {
//            DocumentPermissionEntity entity = converter.convertToEntity(dto);
//            dao.update(entity);
//        }

        GenericServiceUtility.<DocumentPermissionEntity, DocumentPermissionId, DocumentPermissionDTO>update(dto, dao, converter);
    }

    public void delete(DocumentPermissionId id)
    {
        dao.delete(id);
    }
}
