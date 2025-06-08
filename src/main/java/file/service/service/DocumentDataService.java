package file.service.service;

import file.service.converters.docdata.DocumentDataConverter;
import file.service.dao.DocumentDataDAO;
import file.service.dto.docdata.DocumentDataDTO;
import file.service.entity.DocumentDataEntity;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class DocumentDataService
{
    @Inject
    private DocumentDataDAO dao;

    @Inject
    private DocumentDataConverter converter;

    public Optional<DocumentDataDTO> findById(Long id)
    {
//        // Use the DAO to find the requested user
//        Optional<DocumentDataEntity> optionalEntity = dao.findById(id);
//
//        // If there is a (non-null) value inside the Optional
//        if (optionalEntity.isPresent())
//        {
//            // Get the User value from inside the Optional
//            DocumentDataEntity entity = optionalEntity.get();
//
//            // Convert the value to DTO
//            DocumentDataDTO dto = converter.convertToDTO(entity);
//
//            // Return the user converted to DTO, wrapped inside an Optional
//            return Optional.of(dto);
//        }
//        else
//            // If nothing was found, then just return an Optional with no value
//            return Optional.empty();

        return ServiceUtility.<DocumentDataEntity, Long, DocumentDataDTO>findById(id, dao, converter);
    }

    public List<DocumentDataDTO> findAll()
    {
//        return dao.findAll().stream()
//                .map(entity -> converter.convertToDTO(entity))
//                .collect(Collectors.toList());

        return ServiceUtility.<DocumentDataEntity, Long, DocumentDataDTO>findAll(dao, converter);
    }

    public void create(DocumentDataDTO dto)
    {
//        DocumentDataEntity entity = converter.convertToEntity(dto);
//        dao.create(entity);

        ServiceUtility.<DocumentDataEntity, Long, DocumentDataDTO>create(dto, dao, converter);
    }

    public void update(DocumentDataDTO dto)
    {
//        Optional<DocumentDataEntity> foundEntity = dao.findById(dto.getId());
//
//        if (foundEntity.isPresent())
//        {
//            DocumentDataEntity entity = converter.convertToEntity(dto);
//            dao.update(entity);
//        }

        ServiceUtility.<DocumentDataEntity, Long, DocumentDataDTO>update(dto, dao, converter);
    }

    public void delete(Long id)
    {
        dao.delete(id);
    }
}
