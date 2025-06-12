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

    public InventoryLogEntity() { }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public InventoryAction getAction() { return action; }
    public void setAction(InventoryAction action) { this.action = action; }

    public Integer getQuantityChange() { return quantityChange; }
    public void setQuantityChange(Integer quantityChange) { this.quantityChange = quantityChange; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}
