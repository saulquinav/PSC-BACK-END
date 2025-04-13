package file.service.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "user")
public class User
{
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String userName;

    @Column(name = "firstname", unique = false, nullable = false)
    private String firstName;

    @Column(name = "surname", unique = false, nullable = false)
    private String surName;

    @ManyToMany(mappedBy = "users")
    private Set<FileMetadata> fileMetadatas;

    @ManyToMany
    @JoinTable(name = "file_permission",
                joinColumns = @JoinColumn(name = "user_id_fk"),
                inverseJoinColumns = @JoinColumn(name = "file_metadata_id_fk"))
    private Set<FilePermission> filePermissions;

    // No-argument constructor
    public User() { }

    public User(String userName, String firstName, String surName)
    {
        this.userName = userName;
        this.firstName = firstName;
        this.surName = surName;
    }

    public Long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }
    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Set<FileMetadata> getFileMetadatas() {
        return fileMetadatas;
    }
    public void setFileMetadatas(Set<FileMetadata> fileMetadatas) {
        this.fileMetadatas = fileMetadatas;
    }

    public Set<FilePermission> getFilePermissions() {
        return filePermissions;
    }
    public void setFilePermissions(Set<FilePermission> filePermissions) {
        this.filePermissions = filePermissions;
    }
}
