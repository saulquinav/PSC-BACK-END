package inventory.tracking.converters.inventoryitem;

import inventory.tracking.converters.GenericConverter;
import inventory.tracking.dto.inventoryitem.InventoryItemDTO;
import inventory.tracking.entity.InventoryItemEntity;

public class InventoryItemConverter extends GenericConverter<InventoryItemEntity, InventoryItemDTO>
{
    @Override
    public InventoryItemDTO convertToDTO(InventoryItemEntity entity)
    {
        return new InventoryItemDTO(entity.getName(),
                                    entity.getItemType(),
                                    entity.getBrand(),
                                    entity.getModel(),
                                    entity.getQuantity());

    }

    @Override
    public InventoryItemEntity convertToEntityWithoutId(InventoryItemDTO dto)
    {
        InventoryItemEntity entity = new InventoryItemEntity();
        entity.setName(dto.getName());
        entity.setItemType(dto.getItemType());
        entity.setBrand(dto.getBrand());
        entity.setModel(dto.getModel());
        entity.setQuantity(dto.getQuantity());

        return entity;
    }

    @Override
    public InventoryItemEntity convertToEntity(InventoryItemDTO dto)
    {
        InventoryItemEntity entity = convertToEntityWithoutId(dto);
        entity.setId(dto.getId()); // also set the ID

        return entity;
    }
}
