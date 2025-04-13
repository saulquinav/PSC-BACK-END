package file.service.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import java.io.Serializable;

public class FileVersionDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long editingUserId;
    private String name;
    private Boolean isEdited;
    private byte[] data; // the document BLOB

    // No-arguments constructor
    public FileVersionDTO() { }

    public FileVersionDTO(long editingUserId,
                          String name,
                          Boolean isEdited,
                          byte[] data)
    {
        this.editingUserId = editingUserId;
        this.name = name;
        this.isEdited = isEdited;
        this.data = data;
    }

    public Long getId() { return id; }
    public void setId(long id)
    {
        this.id = id;
    }

    public Long getEditingUserId() { return editingUserId; }
    public void setEditingUserId(long editingUserId)
    {
        this.editingUserId = editingUserId;
    }

    public String getName() { return name; }
    public void setName(String name)
    {
        this.name = name;
    }

    public Boolean getIsEdited() { return isEdited;}
    public void setIsEdited(Boolean edited)
    {
        isEdited = edited;
    }

    public byte[] getData() { return data; }
    public void setData(byte[] data)
    {
        this.data = data;
    }
}
