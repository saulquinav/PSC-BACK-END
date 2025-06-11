package inventory.tracking.dto.inventorylog;

import inventory.tracking.entity.InventoryAction;
import jakarta.persistence.*;

public class InventoryLogDTO
{
    private Long id;
    private InventoryAction action; // CREATE, UPDATE, DELETE
    private Integer quantityChange;
    private String note;

    public InventoryLogDTO() { }

    public InventoryLogDTO(Long id,
                           InventoryAction action,
                           Integer quantityChange,
                           String note)
    {
        this.id = id;
        this.action = action;
        this.quantityChange = quantityChange;
        this.note = note;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public InventoryAction getAction() { return action; }
    public void setAction(InventoryAction action) { this.action = action; }

    public Integer getQuantityChange() { return quantityChange; }
    public void setQuantityChange(Integer quantityChange) { this.quantityChange = quantityChange; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}
