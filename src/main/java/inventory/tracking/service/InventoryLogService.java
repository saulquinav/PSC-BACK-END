package inventory.tracking.service;

import inventory.tracking.converters.inventoryitem.InventoryItemConverter;
import inventory.tracking.converters.inventorylog.InventoryLogConverter;
import inventory.tracking.dao.InventoryItemDAO;
import inventory.tracking.dao.InventoryLogDAO;
import inventory.tracking.dto.inventoryitem.InventoryItemDTO;
import inventory.tracking.dto.inventorylog.InventoryLogDTO;
import inventory.tracking.entity.InventoryItemEntity;
import inventory.tracking.entity.InventoryLogEntity;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

// We use @Stateless 'Bean' when this bean doesn't hold any client-specific information between method calls.
@Stateless
public class InventoryLogService
{
    @Inject
    private InventoryLogDAO dao;

    @Inject
    private InventoryLogConverter converter;

    public Optional<InventoryLogDTO> findById(Long id)
    {
        return GenericServiceUtility.<InventoryLogEntity, Long, InventoryLogDTO>findById(id, dao, converter);
    }

    public List<InventoryLogDTO> findAll()
    {
        return GenericServiceUtility.<InventoryLogEntity, Long, InventoryLogDTO>findAll(dao, converter);
    }

    public void create(InventoryLogDTO dto)
    {
        GenericServiceUtility.<InventoryLogEntity, Long, InventoryLogDTO>create(dto, dao, converter);
    }

    public void update(InventoryLogDTO dto)
    {
        GenericServiceUtility.<InventoryLogEntity, Long, InventoryLogDTO>update(dto, dao, converter);
    }

    public void delete(Long id)
    {
        dao.delete(id);
    }
}
