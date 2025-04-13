package file.service.dto;

import file.service.entity.FilePermission;
import file.service.entity.User;

import java.util.Set;

public class FileMetadataDTO
{
    private Long id;
    private Boolean isEdited;
    private String name;
    private Set<User> users;
    private Set<FilePermission> filePermissions;

    public FileMetadataDTO() { }

    public FileMetadataDTO(boolean isEdited, String name, Set<User> users, Set<FilePermission> filePermissions)
    {
        this.isEdited = isEdited;
        this.name = name;
        this.users = users;
        this.filePermissions = filePermissions;
    }

    public Long getId() { return id; }
    public void setId(long id)
    {
        this.id = id;
    }

    public Boolean getIsEdited() { return isEdited; }
    public void setIsEdited(boolean edited)
    {
        isEdited = edited;
    }

    public String getName() { return name; }
    public void setName(String name)
    {
        this.name = name;
    }

    public Set<User> getUsers() { return users; }
    public void setUsers(Set<User> users)
    {
        this.users = users;
    }

    public Set<FilePermission> getFilePermissions() { return filePermissions; }
    public void setFilePermissions(Set<FilePermission> filePermissions)
    {
        this.filePermissions = filePermissions;
    }
}
