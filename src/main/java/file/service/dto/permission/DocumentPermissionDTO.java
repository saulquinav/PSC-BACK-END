package file.service.dto.permission;

import file.service.entity.DocumentEntity;
import file.service.entity.DocumentPermissionType;
import file.service.entity.UserEntity;

import java.io.Serializable;
import java.util.Objects;

public class DocumentPermissionDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    // The components of the composite primary key are exposed directly
    private Long userId;
    private Long documentId;

    private String name;
    private UserEntity userEntity;
    private DocumentEntity fileMetadata;
    private DocumentPermissionType permissionType;
    private String filePassword;

    public DocumentPermissionDTO() { }

    public DocumentPermissionDTO(String name,
                                 UserEntity userEntity,
                                 DocumentEntity fileMetadata,
                                 DocumentPermissionType permissionType,
                                 String filePassword)
    {
        this.name = name;
        this.userEntity = userEntity;
        this.fileMetadata = fileMetadata;
        this.permissionType = permissionType;
        this.filePassword = filePassword;
    }

    public DocumentPermissionDTO(Long userId, Long documentId,
                                 String name,
                                 UserEntity userEntity,
                                 DocumentEntity fileMetadata,
                                 DocumentPermissionType permissionType,
                                 String filePassword)
    {
        this.userId = userId;
        this.documentId = documentId;

        this.name = name;
        this.userEntity = userEntity;
        this.fileMetadata = fileMetadata;
        this.permissionType = permissionType;
        this.filePassword = filePassword;
    }

    public DocumentPermissionDTO(String name)
    { this.name = name; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getDocumentId() { return documentId; }
    public void setDocumentId(Long documentId) { this.documentId = documentId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public UserEntity getUser() { return userEntity; }
    public void setUser(UserEntity userEntity) { this.userEntity = userEntity; }

    public DocumentPermissionType getPermissionType() { return permissionType; }
    public void setPermissionType(DocumentPermissionType permissionType) { this.permissionType = permissionType; }

    public DocumentEntity getFileMetadata() { return fileMetadata; }
    public void setFileMetadata(DocumentEntity fileMetadata) { this.fileMetadata = fileMetadata; }

    public String getFilePassword() { return filePassword; }
    public void setFilePassword(String filePassword) { this.filePassword = filePassword; }

    // --- equals(), hashCode() for good practice ---
    // These should be based on the natural key (userId, productId) if they uniquely identify the DTO.
    @Override
    public boolean equals(Object o) {
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
