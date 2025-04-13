package file.service.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "file_metadata")
public class FileMetadata
{
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "is_edited", unique = false, nullable = false)
    private Boolean isEdited;

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "fileMetadatas")
    private Set<User> users;

    @OneToMany(mappedBy = "file")
    private Set<FilePermission> filePermissions;

    // No-arguments constructor
    public FileMetadata() { }

    public FileMetadata(boolean isEdited, String name, Set<User> users, Set<FilePermission> filePermissions)
    {
        this.isEdited = isEdited;
        this.name = name;
        this.users = users;
        this.filePermissions = filePermissions;
    }

    public Long getId() { return id;}
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
    public void setName(String name) {
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
