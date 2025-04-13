package file.service.dao;

import file.service.entity.FilePermission;
import file.service.entity.FileVersion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public class FileVersionDAO implements GenericDAO<FileVersion>
{
    @PersistenceContext
    private EntityManager entityManager;  // every DAO has an EntityManager instance inside it

    @Override
    public Optional<FileVersion> findById(Long id)
    {
        FileVersion filePermission = entityManager.find(FileVersion.class, id);
        return Optional.ofNullable(filePermission);
    }

    @Override
    public List<FileVersion> findAll()
    {
        return entityManager.createQuery("SELECT v FROM FileVersion v", FileVersion.class)
                            .getResultList();
    }

    @Override
    @Transactional // is it necessary?
    public void create(FileVersion fileVersion)
    {
        entityManager.persist(fileVersion);
    }

    @Override
    @Transactional // is it necessary?
    public FileVersion update(FileVersion fileVersion)
    {
        FileVersion foundFileVersion = entityManager.find(FileVersion.class, fileVersion.getId());

        if (foundFileVersion == null)
            return null; // Or throw an exception
        else
            return entityManager.merge(fileVersion);
    }

    @Override
    @Transactional // is it necessary?
    public void delete(Long id)
    {
        FileVersion foundFileVersion = entityManager.find(FileVersion.class, id);

        if (foundFileVersion != null)
            entityManager.remove(foundFileVersion);
    }
}
