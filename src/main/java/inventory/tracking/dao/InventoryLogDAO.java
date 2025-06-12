package inventory.tracking.dao;

import inventory.tracking.entity.InventoryLogEntity;
import jakarta.ejb.Stateless;

@Stateless // the @Stateless annotation is recommended for DAO
// @Stateless is transactional by default, so we don't need to apply the @Transactional
// annotation to the methods
public class InventoryLogDAO extends AbstractCrudDAO<InventoryLogEntity, Long>
{
    public InventoryLogDAO() { super(InventoryLogEntity.class); }
}
