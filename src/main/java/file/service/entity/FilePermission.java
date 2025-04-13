package file.service.entity;

import jakarta.persistence.*;

//CREATE TABLE User (
//        id BIGINT AUTO_INCREMENT PRIMARY KEY,
//        username VARCHAR(255) NOT NULL
//);
//
//CREATE TABLE File (
//        id BIGINT AUTO_INCREMENT PRIMARY KEY,
//        filename VARCHAR(255) NOT NULL
//);
//
//CREATE TABLE FilePermission (
//        id BIGINT AUTO_INCREMENT PRIMARY KEY,
//        user_id BIGINT,
//        file_id BIGINT,
//        permission_type VARCHAR(50),
//FOREIGN KEY (user_id) REFERENCES User(id),
//FOREIGN KEY (file_id) REFERENCES File(id)
//        );

@Entity
@Table(name = "file_permission")
public class FilePermission
{
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private long id;

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

    public long getId() {
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
