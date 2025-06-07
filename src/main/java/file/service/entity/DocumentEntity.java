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

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @Lob
    @Column(name = "data", length = 100000)
    private byte[] data; // the document BLOB

    // One-to-many relationship with DocumentPermissionEntity (the join table entity).
    // 'mappedBy' indicates that the 'document' field in DocumentPermissionEntity is the owning side.
    // CascadeType.ALL will propagate persist, merge, remove operations from DocumentEntity to DocumentPermissionEntity.
    // orphanRemoval = true ensures that if a DocumentPermissionEntity association is removed from this set,
    // the DocumentPermissionEntity entity itself is deleted from the database.
    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DocumentPermissionEntity> documentPermissions = new HashSet<DocumentPermissionEntity>();

    public DocumentEntity() { }

    public DocumentEntity(String name, byte[] data, Set<DocumentPermissionEntity> documentPermissions)
    {
        this.name = name;
        this.data = data;
        this.documentPermissions = documentPermissions;
    }

    public DocumentEntity(String name, byte[] data)
    {
        this.name = name;
        this.data = data;
    }

    public Long getId() { return id; }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
