package inventory.tracking.dao;

import inventory.tracking.entity.UserInfoEntity;
import jakarta.ejb.Stateless;

@Stateless // the @Stateless annotation is recommended for DAO
// @Stateless is transactional by default, so we don't need to apply the @Transactional
// annotation to the methods
public class UserInfoDAO extends AbstractCrudDAO<UserInfoEntity, Long>
{
    public UserInfoDAO() { super(UserInfoEntity.class); }
}
