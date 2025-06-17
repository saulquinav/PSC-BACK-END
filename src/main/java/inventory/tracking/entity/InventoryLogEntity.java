package inventory.tracking.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "inventory_logs")
public class InventoryLogEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "action_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private InventoryAction action; // CREATE, UPDATE, DELETE

    @Column(name = "quantity_change", nullable = false)
    private Integer quantityChange;

    @Column(name = "note", nullable = false)
    private String note;

    /* Many-to-one relationship with InventoryItemEntity
    ** @JoinColumn specifies the foreign key (FK) column of this table/entity that points
    ** to the InventoryItem
    ** Optional: in the @JoinColumn we can add 'nullable = false' to enforce that a InventoryLogEntity
    * is always associated with a InventoryItemEntity */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_item_id_fk")
    private InventoryItemEntity inventoryItem;

    public InventoryLogEntity() { }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public InventoryAction getAction() { return action; }
    public void setAction(InventoryAction action) { this.action = action; }

    public Integer getQuantityChange() { return quantityChange; }
    public void setQuantityChange(Integer quantityChange) { this.quantityChange = quantityChange; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public InventoryItemEntity getInventoryItem() { return inventoryItem; }
    public void setInventoryItem(InventoryItemEntity inventoryItem) { this.inventoryItem = inventoryItem; }
}
