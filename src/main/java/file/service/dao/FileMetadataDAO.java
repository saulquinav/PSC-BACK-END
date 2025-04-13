package file.service.dao;

import file.service.entity.FileMetadata;

import file.service.entity.FilePermission;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Stateless // not sure if the @Stateless annotation is recommended
public class FileMetadataDAO implements GenericDAO<FileMetadata>
{
    @PersistenceContext
    private EntityManager entityManager;  // every DAO has an EntityManager instance inside it

    @Override
    public Optional<FileMetadata> findById(long id)
    {
        FileMetadata fileMetadata = entityManager.find(FileMetadata.class, id);
        return Optional.ofNullable(fileMetadata);
    }

    @Override
    public List<FileMetadata> findAll()
    {
        return entityManager.createQuery("SELECT p FROM FilePermission p", FileMetadata.class)
                            .getResultList();
    }

    @Override
    @Transactional // is it necessary?
    public void create(FileMetadata fileMetadata)
    {
        entityManager.persist(fileMetadata);
    }

    @Override
    @Transactional // is it necessary?
    public FileMetadata update(FileMetadata fileMetadata)
    {
        FileMetadata foundFileMetadata = entityManager.find(FileMetadata.class, fileMetadata.getId());

        if (foundFileMetadata == null)
            return null; // Or throw an exception
        else
            return entityManager.merge(fileMetadata);
    }

    @Override
    @Transactional // is it necessary?
    public void delete(long id)
    {
        FileMetadata foundFileMetadata = entityManager.find(FileMetadata.class, id);
        if (foundFileMetadata != null)
            entityManager.remove(foundFileMetadata);
    }
}
