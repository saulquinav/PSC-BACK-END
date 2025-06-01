package file.service.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import file.service.entity.UserEntity;

import java.util.Optional;

@Stateless // the @Stateless annotation is recommended for DAO
// @Stateless is transactional by default, so we don't need to apply the @Transactional
// annotation to the methods
public class UserDAO extends AbstractGenericDAO<UserEntity>
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
        catch (NoResultException exc) { return Optional.empty(); }
    }
}
