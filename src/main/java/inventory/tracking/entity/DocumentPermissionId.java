package inventory.tracking.entity;

import jakarta.persistence.Embeddable;

import java.util.Objects;

/* This class represents a composite key for the DocumentPermissionEntity class used to
** represent a 'join table' (pivot table) that joins the UserEntity and DocumentEntity into
** a many-to-many relationship.
** This class represents the ID of the DocumentPermissionEntity intermediate class.
** This class does not need to be included in the 'persistence.xml' file because JPA will
** automatically discover any @Embeddable or @EmbeddedId classes used by the already existing
** entities. */
@Embeddable
public class DocumentPermissionId
{
    private Long userId;
    private Long documentId;

    public DocumentPermissionId() { }

    public DocumentPermissionId(Long userId, Long documentId)
    {
        this.userId = userId;
        this.documentId = documentId;
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getDocumentId() { return documentId; }
    public void setDocumentId(Long documentId) { this.documentId = documentId; }

    // For composite keys, we must make sure equals() and hashCode() are well implemented
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentPermissionId that = (DocumentPermissionId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(documentId, that.documentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, documentId);
    }
}
