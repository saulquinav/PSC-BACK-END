package file.service.dao;

import file.service.entity.User;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;


@Stateless // not shure if the @Stateless annotation is recommended
public class UserDAO implements GenericDAO<User>
{
    @PersistenceContext
    private EntityManager entityManager;  // every DAO has an EntityManager instance inside it

    public Optional<User> findById(long id)
    {
        // Use the EntityManager to find the user by ID.
        // For the EntityManager to know in what type to return the found entity,
        // we pass the class type, User.class, of the searched entity.
        User foundUser = entityManager.find(User.class, id);

        // We could return the found entity as it is (as a User object), but that object
        // will be 'null' if nothing is found.
        // So we wrap the found object in an 'Optional', meaning that the returned value
        // could store a null value.
        // The Optional ise never 'null', but the value it holds might be 'null'.
        return Optional.ofNullable(foundUser);
    }


    public List<User> findAll()
    {
        return entityManager.createQuery("SELECT u FROM User u", User.class)
                            .getResultList();
    }

    @Override
    @Transactional // is it necessary?
    public void create(User user)
    {
        entityManager.persist(user);
    }

    @Override
    @Transactional // is it necessary?
    public User update(User user)
    {
        User foundUser = entityManager.find(User.class, user.getId());
        if (foundUser == null)
            return null; // Or throw an exception
        else
            return entityManager.merge(user);
    }

    @Override
    @Transactional // is it necessary?
    public void delete(long id)
    {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }
}
