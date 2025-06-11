package inventory.tracking.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "document_permissions")
public class DocumentPermissionEntity
{
    // Define the composite primary key using @EmbeddedId
    @EmbeddedId
    private DocumentPermissionId id;

    private String name;

    // Many-to-one relationship with UserEntity
    // @MapsId("userId") maps the 'userId' part of the composite key to the User's primary key.
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId") // Maps the userId from UserProductId to the User's ID
    @JoinColumn(name = "user_id_fk", nullable = false) // Foreign key column in document_permissions table
    private UserEntity user;

    // Many-to-one relationship with DocumentEntity
    // @MapsId("productId") maps the 'productId' part of the composite key to the Product's primary key.
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("documentId") // Here 'documentId' refers to the field of the composite key DocuemntPermissionId
    @JoinColumn(name = "document_id_fk", nullable = false) // Foreign key column in document_permissions table
    private DocumentMetadataEntity document;

    @Column(name = "permission_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private DocumentPermissionType permissionType;

    @Column(name = "document_password", unique = false, nullable = false)
    private String documentPassword;

    // No-argument constructor
    public DocumentPermissionEntity() { }

    public DocumentPermissionEntity(String name)
    {
        this.name = name;
    }

    public DocumentPermissionId getId() { return id; }
    public void setId(DocumentPermissionId id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public UserEntity getUser() {
        return user;
    }
    public void setUser(UserEntity userEntity) {
        this.user = userEntity;
    }

    public DocumentMetadataEntity getDocument() { return document; }
    public void setDocument(DocumentMetadataEntity fileMetadata) { this.document = fileMetadata; }

    public DocumentPermissionType getPermissionType() { return permissionType; }
    public void setPermissionType(DocumentPermissionType permissionType) { this.permissionType = permissionType; }

    public String getDocumentPassword() { return documentPassword; }
    public void setDocumentPassword(String filePassword) { this.documentPassword = filePassword; }
}
