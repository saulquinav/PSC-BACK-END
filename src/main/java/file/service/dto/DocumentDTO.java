package file.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class DocumentDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private byte[] data; // the document BLOB

    private Set<DocumentPermissionDTO> documentPermissions = new HashSet<DocumentPermissionDTO>();

    public DocumentDTO() { }

    public DocumentDTO(String name, byte[] data)
    {
        this.name = name;
        this.data = data;
    }

    public DocumentDTO(String name,
                       byte[] data,
                       Set<DocumentPermissionDTO> documentPermissions)
    {
        this.name = name;
        this.data = data;
        this.documentPermissions = documentPermissions;
    }


    public Long getId() { return id; }
    public void setId(long id)
    {
        this.id = id;
    }

    public String getName() { return name; }
    public void setName(String name)
    {
        this.name = name;
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
