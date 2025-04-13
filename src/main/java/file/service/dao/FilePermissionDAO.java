package file.service.dao;

import file.service.entity.FilePermission;
import file.service.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public class FilePermissionDAO implements GenericDAO<FilePermission>
{
    @PersistenceContext
    private EntityManager entityManager;  // every DAO has an EntityManager instance inside it

    @Override
    public Optional<FilePermission> findById(long id)
    {
        FilePermission filePermission = entityManager.find(FilePermission.class, id);
        return Optional.ofNullable(filePermission);
    }

    @Override
    public List<FilePermission> findAll()
    {
        return entityManager.createQuery("SELECT u FROM User u", FilePermission.class)
                            .getResultList();
    }

    @Override
    @Transactional // is it necessary?
    public void create(FilePermission filePermission)
    {
        entityManager.persist(filePermission);
    }

    @Override
    @Transactional // is it necessary?
    public FilePermission update(FilePermission filePermission)
    {
        FilePermission foundFilePermission = entityManager.find(FilePermission.class, filePermission.getId());
        if (foundFilePermission == null)
            return null; // Or throw an exception
        else
            return entityManager.merge(filePermission);
    }

    @Override
    @Transactional // is it necessary?
    public void delete(long id)
    {
        FilePermission foundFilePermission = entityManager.find(FilePermission.class, id);
        if (foundFilePermission != null)
            entityManager.remove(foundFilePermission);
    }
}
