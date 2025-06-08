package file.service.service;

import file.service.converters.docmetadata.DocumentMetadataConverter;
import file.service.dao.DocumentMetadataDAO;
import file.service.dto.docmetadata.DocumentMetadataDTO;
import file.service.entity.DocumentMetadataEntity;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// We use @Stateless 'Bean' when this bean doesn't hold any client-specific information between method calls.
@Stateless
public class DocumentMetadataService
{
    @Inject
    private DocumentMetadataDAO documentDAO;

    @Inject
    DocumentMetadataConverter converter;

    public Optional<DocumentMetadataDTO> findById(Long id)
    {
        // Use the DAO to find the requested user
        Optional<DocumentMetadataEntity> optionalEntity = documentDAO.findById(id);

        // If there is a (non-null) value inside the Optional
        if (optionalEntity.isPresent())
        {
            // Get the User value from inside the Optional
            DocumentMetadataEntity entity = optionalEntity.get();

            // Convert the value to DTO
            DocumentMetadataDTO dto = converter.convertToNewDTO(entity);

            // Return the user converted to DTO, wrapped inside an Optional
            return Optional.of(dto);
        }
        else
            // If nothing was found, then just return an Optional with no value
            return Optional.empty();
    }

    public List<DocumentMetadataDTO> findAll()
    {
        return documentDAO.findAll().stream()
                .map(entity -> converter.convertToNewDTO(entity))
                .collect(Collectors.toList());
    }

    public void create(DocumentMetadataDTO dto)
    {
        DocumentMetadataEntity entity = converter.convertToNewEntity(dto);

        documentDAO.create(entity);
    }

    public void update(DocumentMetadataDTO dto)
    {
        Optional<DocumentMetadataEntity> foundEntity = documentDAO.findById(dto.getId());

        if (foundEntity.isPresent())
        {
            DocumentMetadataEntity entity = converter.convertToNewEntity(dto);
            documentDAO.update(entity);
        }
    }

    public void delete(Long id)
    {
        documentDAO.delete(id);
    }
}
