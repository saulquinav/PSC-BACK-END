package file.service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "file_version")
public class FileVersion
{
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Column(name = "editing_user_id_fk", unique = true, nullable = false)
    private long editingUserId;

    @Column(unique = false, nullable = false)
    private String name;

    @Column(name = "is_edited", unique = false, nullable = false)
    private Boolean isEdited;

    @Lob
    @Column(length=100000)
    private byte[] data; // the document BLOB

    public FileVersion() { }

    public FileVersion(long editingUserId,
                       String name,
                       Boolean isEdited,
                       byte[] data)
    {
        this.editingUserId = editingUserId;
        this.name = name;
        this.isEdited = isEdited;
        this.data = data;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public long getEditingUserId() {
        return editingUserId;
    }
    public void setEditingUserId(long editingUserId) {
        this.editingUserId = editingUserId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsEdited() {
        return isEdited;
    }
    public void setIsEdited(Boolean edited) {
        isEdited = edited;
    }

    public byte[] getData() {
        return data;
    }
    public void setData(byte[] data) {
        this.data = data;
    }
}
