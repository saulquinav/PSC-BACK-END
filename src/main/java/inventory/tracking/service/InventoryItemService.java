package inventory.tracking.service;

import inventory.tracking.converters.inventoryitem.InventoryItemConverter;
import inventory.tracking.dao.InventoryItemDAO;
import inventory.tracking.dto.inventoryitem.InventoryItemDTO;
import inventory.tracking.entity.InventoryItemEntity;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

// We use @Stateless 'Bean' when this bean doesn't hold any client-specific information between method calls.
@Stateless
public class InventoryItemService
{
    @Inject
    private InventoryItemDAO dao;

    @Inject
    private InventoryItemConverter converter;

    public Optional<InventoryItemDTO> findById(Long id)
    {
        return GenericServiceUtility.<InventoryItemEntity, Long, InventoryItemDTO>findById(id, dao, converter);
    }

    public List<InventoryItemDTO> findAll()
    {
        return GenericServiceUtility.<InventoryItemEntity, Long, InventoryItemDTO>findAll(dao, converter);
    }

    public void create(InventoryItemDTO dto)
    {
        GenericServiceUtility.<InventoryItemEntity, Long, InventoryItemDTO>create(dto, dao, converter);
    }

    public void update(InventoryItemDTO dto)
    {
        GenericServiceUtility.<InventoryItemEntity, Long, InventoryItemDTO>update(dto, dao, converter);
    }

    public void delete(Long id)
    {
        dao.delete(id);
    }
}
