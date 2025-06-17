package inventory.tracking.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "iventory_items")
public class InventoryItemEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "item_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    /* One-to-many relationship with DocumentPermissionEntity (the join table entity).
    ** 'mappedBy' indicates that the 'inventoryItem' field in InventoryLogEntity is the owning side.
    ** The 'inventoryItem' is of type InventoryItemEntity, so the owning side is this entity (InventoryLogEntity).
    ** CascadeType.ALL will propagate persist, merge, remove operations from InventoryItemEntity to InventoryLogEntity.
    ** orphanRemoval = true ensures that if a DocumentPermissionEntity association is removed from this set,
    ** the InventoryLogEntity entity itself is deleted from the database. */
    @OneToMany(mappedBy = "inventoryItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<InventoryLogEntity> inventoryLogs;

    public InventoryItemEntity() { }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public ItemType getItemType() { return itemType; }
    public void setItemType(ItemType itemType) { this.itemType = itemType; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Set<InventoryLogEntity> getInventoryLogs() { return inventoryLogs; }
    public void setInventoryLogs(Set<InventoryLogEntity> inventoryLogs) { this.inventoryLogs = inventoryLogs; }
}
