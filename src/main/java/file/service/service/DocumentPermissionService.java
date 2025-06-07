package file.service.service;

import file.service.converters.docpermission.DocumentPermissionConverter;
import file.service.dao.DocumentPermissionDAO;
import file.service.dto.docpermission.DocumentPermissionDTO;
import file.service.entity.DocumentEntity;
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
    private DocumentPermissionDAO documentPermissionDAO;

    @Inject
    private DocumentPermissionConverter documentPermissionsConverter;

    public Optional<DocumentPermissionDTO> findById(DocumentPermissionId id)
    {
        // Use the DAO to find the requested user
        Optional<DocumentPermissionEntity> optionalEntity = documentPermissionDAO.findById(id);

        // If there is a (non-null) value inside the Optional
        if (optionalEntity.isPresent())
        {
            // Get the User value from inside the Optional
            E entity = optionalEntity .get();

            // Convert the value to DTO
            DR dto = getReadingConverter().convertToNewDTO(entity);

            // Return the user converted to DTO, wrapped inside an Optional
            return Optional.of(dto);
        }
        else
            // If nothing was found, then just return an Optional with no value
            return Optional.empty();
    }

    public List<DR> findAll()
    {
        return documentPermissionDAO.findAll().stream()
                .map(entity -> getReadingConverter().convertToNewDTO(entity))
                .collect(Collectors.toList());
    }

    public void create(DC dto)
    {
        E entity = getCreationConverter().convertToNewEntity(dto);

        getDao().create(entity);
    }

    public void update(DU dto)
    {
        Optional<E> foundEntity = documentPermissionDAO.findById(dto.getId());

        if (foundEntity.isPresent())
        {
            E entity = getUpdateConverter().convertToNewEntity(dto);
            documentPermissionDAO.update(entity);
        }
    }

    public void delete(ID id)
    {
        documentPermissionDAO.delete(id);
    }
}
