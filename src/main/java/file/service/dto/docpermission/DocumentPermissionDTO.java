package file.service.dto.docpermission;

import file.service.entity.DocumentPermissionType;

import java.io.Serializable;
import java.util.Objects;

public class DocumentPermissionDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    // The components of the composite primary key are exposed directly
    private Long userId;
    private Long documentId;

    private String name;
    private DocumentPermissionType permissionType;

    public DocumentPermissionDTO(Long userId,
                                 Long documentId,
                                 String name,
                                 DocumentPermissionType permissionType)
    {
        this.userId = userId;
        this.documentId = documentId;
        this.name = name;
        this.permissionType = permissionType;
    }

    public DocumentPermissionDTO(String name)
    { this.name = name; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getDocumentId() { return documentId; }
    public void setDocumentId(Long documentId) { this.documentId = documentId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public DocumentPermissionType getPermissionType() { return permissionType; }
    public void setPermissionType(DocumentPermissionType permissionType) { this.permissionType = permissionType; }

    // --- equals(), hashCode() for good practice ---
    // These should be based on the natural key (userId, productId) if they uniquely identify the DTO.
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentPermissionDTO that = (DocumentPermissionDTO) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(documentId, that.documentId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(userId, documentId);
    }
}
