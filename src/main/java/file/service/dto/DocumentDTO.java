package file.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class DocumentDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long editingUserId;
    private String name;
    private Boolean isEdited;
    private byte[] data; // the document BLOB

    private Set<DocumentPermissionDTO> documentPermissions = new HashSet<DocumentPermissionDTO>();

    public DocumentDTO(long editingUserId,
                       String name,
                       Boolean isEdited,
                       byte[] data)
    {
        this.editingUserId = editingUserId;
        this.name = name;
        this.isEdited = isEdited;
        this.data = data;
    }

    public DocumentDTO(long editingUserId,
                       String name,
                       Boolean isEdited,
                       byte[] data,
                       Set<DocumentPermissionDTO> documentPermissions)
    {
        this.editingUserId = editingUserId;
        this.name = name;
        this.isEdited = isEdited;
        this.data = data;
        this.documentPermissions = documentPermissions;
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

    public Set<DocumentPermissionDTO> getDocumentPermissions() { return documentPermissions; }
    public void setDocumentPermissions(Set<DocumentPermissionDTO> documentPermissions)
    { this.documentPermissions = documentPermissions; }
}
