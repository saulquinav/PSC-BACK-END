package file.service.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "file_permission")
public class FilePermission
{
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id_fk")
    private User user;

    @ManyToOne
    @JoinColumn(name = "file_metadata_id_fk")
    private FileMetadata fileMetadata;

    @Column(name = "permission", nullable = false)
    @Enumerated(EnumType.STRING)
    private FilePermisionType permissionType;

    @Column(name = "file_password", unique = false, nullable = false)
    private String filePassword;

    // No-argument contructor
    public FilePermission() { }

    public FilePermission(User user, FileMetadata fileMetadata, FilePermisionType permissionType, String filePassword)
    {
        this.user = user;
        this.fileMetadata = fileMetadata;
        this.permissionType = permissionType;
        this.filePassword = filePassword;
    }

    public FilePermission(User user, FileMetadata fileMetadata, FilePermisionType permissionType)
    {
        this.user = user;
        this.fileMetadata = fileMetadata;
        this.permissionType = permissionType;
    }

    public Long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public FilePermisionType getPermissionType() {
        return permissionType;
    }
    public void setPermissionType(FilePermisionType permission) {
        this.permissionType = permission;
    }

    public FileMetadata getFileMetadata() {
        return fileMetadata;
    }
    public void setFileMetadata(FileMetadata fileMetadata) {
        this.fileMetadata = fileMetadata;
    }

    public String getFilePassword() {
        return filePassword;
    }
    public void setFilePassword(String filePassword) {
        this.filePassword = filePassword;
    }
}
