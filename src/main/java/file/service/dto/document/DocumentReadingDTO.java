package file.service.dto.document;

import file.service.dto.permission.DocumentPermissionDTO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class DocumentReadingDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private byte[] data; // the document BLOB
    private Set<DocumentPermissionDTO> documentPermissions = new HashSet<DocumentPermissionDTO>();

    public DocumentReadingDTO(Long id,
                                String name,
                               byte[] data,
                               Set<DocumentPermissionDTO> documentPermissions)
    {
        this.id = id;
        this.name = name;
        this.data = data;
        this.documentPermissions = documentPermissions;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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

    public Set<DocumentPermissionDTO> getDocumentPermissions()
    { return documentPermissions; }
    public void setDocumentPermissions(Set<DocumentPermissionDTO> documentPermissions)
    { this.documentPermissions = documentPermissions; }
}
