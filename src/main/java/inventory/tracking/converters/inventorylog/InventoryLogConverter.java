package inventory.tracking.converters.inventorylog;

import inventory.tracking.converters.GenericConverter;
import inventory.tracking.dto.inventorylog.InventoryLogDTO;
import inventory.tracking.entity.InventoryLogEntity;

public class InventoryLogConverter extends GenericConverter<InventoryLogEntity, InventoryLogDTO>
{
    @Override
    public InventoryLogDTO convertToDTO(InventoryLogEntity entity)
    {
        return new InventoryLogDTO(entity.getId(),
                                    entity.getAction(),
                                    entity.getQuantityChange(),
                                    entity.getNote());
    }

    @Override
    public InventoryLogEntity convertToEntityWithoutId(InventoryLogDTO dto)
    {
        InventoryLogEntity inventoryLogEntity = new InventoryLogEntity();
        inventoryLogEntity.setAction(dto.getAction());
        inventoryLogEntity.setQuantityChange(dto.getQuantityChange());
        inventoryLogEntity.setNote(dto.getNote());

        return inventoryLogEntity;
    }

    @Override
    public InventoryLogEntity convertToEntity(InventoryLogDTO dto)
    {
        InventoryLogEntity inventoryLogEntity = convertToEntityWithoutId(dto);
        inventoryLogEntity.setId(dto.getId()); // also set the id

        return inventoryLogEntity;
    }
}
