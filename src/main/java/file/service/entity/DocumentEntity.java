package file.service.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "documents")
public class DocumentEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(unique = false, nullable = false)
    private String name;

    @Column(name = "editing_user_id_fk", unique = true, nullable = false)
    private Long editingUserId;

    @Column(name = "is_edited", unique = false, nullable = false)
    private Boolean isEdited;

    @Lob
    @Column(length=100000)
    private byte[] data; // the document BLOB

    // One-to-many relationship with DocumentPermissionEntity (the join table entity).
    // 'mappedBy' indicates that the 'document' field in DocumentPermissionEntity is the owning side.
    // CascadeType.ALL will propagate persist, merge, remove operations from DocumentEntity to DocumentPermissionEntity.
    // orphanRemoval = true ensures that if a DocumentPermissionEntity association is removed from this set,
    // the DocumentPermissionEntity entity itself is deleted from the database.
    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DocumentPermissionEntity> documentPermissions = new HashSet<DocumentPermissionEntity>();

    public Long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Long getEditingUserId() {
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

    public Set<DocumentPermissionEntity> getDocumentPermissions() { return documentPermissions; }
    public void setDocumentPermissions(Set<DocumentPermissionEntity> documentPermissions) { this.documentPermissions = documentPermissions; }
}
