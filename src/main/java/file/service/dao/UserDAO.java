package file.service.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import file.service.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Stateless // the @Stateless annotation is recommended for DAO
// @Stateless is transactional by default, so we don't need to apply the @Transactional
// annotation to the methods
public class UserDAO extends AbstractCrudDAO<UserEntity, Long>
{
    public UserDAO()
    {
        super(UserEntity.class);
    }

    // Additional method, besides the tone inherited from AbstractGenericDAO<User>
    public Optional<UserEntity> findByUsername(String username) {
        try
        {
            TypedQuery<UserEntity> query = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.username = :username", UserEntity.class);
            query.setParameter("username", username);

            return Optional.of(query.getSingleResult());
        }
        catch (NoResultException execp) { return Optional.empty(); }
    }

    public List<UserEntity> findAllByUsernameStartingWith(String namePrefix)
    {
        // This is the JPQL query.
        // 'u' refers to an alias for the User entity.
        // 'username' refers to the field within the User entity.
        // ':searchPrefix' is a named parameter.
//        TypedQuery<UserEntity> query = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.username LIKE :prefix", UserEntity.class);
        TypedQuery<UserEntity> query = entityManager.createQuery("SELECT u FROM UserEntity u WHERE LOWER(u.username) LIKE LOWER(:prefix)", UserEntity.class);

        // Set the parameter for the query.
        // The '%' wildcard is standard in JPQL's LIKE operator, just like SQL.
        query.setParameter("prefix", namePrefix + "%");

        return query.getResultList();
    }
}
