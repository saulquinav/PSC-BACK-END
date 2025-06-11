package inventory.tracking.dao;

import inventory.tracking.entity.InventoryItemEntity;
import jakarta.ejb.Stateless;

@Stateless // the @Stateless annotation is recommended for DAO
// @Stateless is transactional by default, so we don't need to apply the @Transactional
// annotation to the methods
public class InventoryItemDAO  extends AbstractCrudDAO<InventoryItemEntity, Long>
{
    public InventoryItemDAO() { super(InventoryItemEntity.class); }
}
