package file.service.dto.document;

import file.service.dto.permission.DocumentPermissionDTO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class DocumentCreationDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String name;
    private byte[] data; // the document BLOB

    private Set<DocumentPermissionDTO> documentPermissions = new HashSet<DocumentPermissionDTO>();

//    public DocumentCreationDTO(String name, byte[] data)
//    {
//        this.name = name;
//        this.data = data;
//    }

    public DocumentCreationDTO(String name,
                               byte[] data,
                               Set<DocumentPermissionDTO> documentPermissions)
    {
        this.name = name;
        this.data = data;
        this.documentPermissions = documentPermissions;
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

    public Set<DocumentPermissionDTO> getDocumentPermissions()
    { return documentPermissions; }
    public void setDocumentPermissions(Set<DocumentPermissionDTO> documentPermissions)
    { this.documentPermissions = documentPermissions; }
}
