package file.service.service;

import file.service.converters.document.DocumentCreationConverter;
import file.service.converters.GenericConverter;
import file.service.converters.document.DocumentReadingConverter;
import file.service.converters.document.DocumentUpdateDataCoverter;
import file.service.converters.document.DocumentUpdateInfoConverter;
import file.service.dao.CrudDAO;
import file.service.dao.DocumentDAO;
import file.service.dto.document.DocumentCreationDTO;
import file.service.dto.document.DocumentReadingDTO;
import file.service.dto.document.DocumentUpdateInfoDTO;
import file.service.entity.DocumentEntity;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// We use @Stateless 'Bean' when this bean doesn't hold any client-specific information between method calls.
@Stateless
public class DocumentService
{
    @Inject
    private DocumentDAO documentDAO;

    @Inject
    private DocumentCreationConverter converter;

    @Inject
    private DocumentReadingConverter documentReadingConverter;

    @Inject
    private DocumentUpdateInfoConverter documentUpdateInfoConverter;

    @Inject
    private DocumentUpdateDataCoverter documentUpdateDataCoverter;

    public Optional<DocumentReadingDTO> findById(Long id)
    {
        // Use the DAO to find the requested user
        Optional<DocumentEntity> optionalEntity = documentDAO.findById(id);

        // If there is a (non-null) value inside the Optional
        if (optionalEntity.isPresent())
        {
            // Get the User value from inside the Optional
            DocumentEntity entity = optionalEntity .get();

            // Convert the value to DTO
            DocumentReadingDTO dto = documentReadingConverter.convertToNewDTO(entity);

            // Return the user converted to DTO, wrapped inside an Optional
            return Optional.of(dto);
        }
        else
            // If nothing was found, then just return an Optional with no value
            return Optional.empty();
    }

    public List<DocumentReadingDTO> findAll()
    {
        return documentDAO.findAll().stream()
                .map(entity -> documentReadingConverter.convertToNewDTO(entity))
                .collect(Collectors.toList());
    }

    public void create(DocumentCreationDTO dto)
    {
        DocumentEntity entity = converter.convertToNewEntity(dto);

        documentDAO.create(entity);
    }

    public void update(DocumentUpdateInfoDTO dto)
    {
        Optional<DocumentEntity> foundEntity = documentDAO.findById(dto.getId());

        if (foundEntity.isPresent())
        {
            DocumentEntity entity = documentUpdateInfoConverter.convertToNewEntity(dto);
            documentDAO.update(entity);
        }
    }

    public void delete(Long id)
    {
        documentDAO.delete(id);
    }
}
