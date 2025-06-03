package file.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    /* For DTO the fields are also private, because it adheres to JavaBeans
    ** standard and leads to more maintainable code */
    private Long id;
    private String username;
    private String password;
    private Set<DocumentPermissionDTO> documentPermissions = new HashSet<DocumentPermissionDTO>();

    public UserDTO() { }

    public UserDTO(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public UserDTO(String username, String password, Set<DocumentPermissionDTO> documentPermissions)
    {
        this.username = username;
        this.password = password;
        this.documentPermissions = documentPermissions;
    }

    public Long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Set<DocumentPermissionDTO> getDocumentPermissions() { return documentPermissions; }
    public void setDocumentPermissions(Set<DocumentPermissionDTO> documentPermissions)
    { this.documentPermissions = documentPermissions; }
}
