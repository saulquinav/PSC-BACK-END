package inventory.tracking.dto.inventoryitem;

import inventory.tracking.entity.InventoryAction;

public class InventoryItemDTO
{
    private Long id;
    private String name;
    private InventoryAction itemType;
    private String brand;
    private String model;
    private Integer quantity;

    public InventoryItemDTO() { }

    public InventoryItemDTO(String name,
                            InventoryAction itemType,
                            String brand,
                            String model,
                            Integer quantity)
    {
        this.name = name;
        this.itemType = itemType;
        this.brand = brand;
        this.model = model;
        this.quantity = quantity;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public InventoryAction getItemType() { return itemType; }
    public void setItemType(InventoryAction itemType) { this.itemType = itemType; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
