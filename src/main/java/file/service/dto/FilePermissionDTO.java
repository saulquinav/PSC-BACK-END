package file.service.dto;



import file.service.entity.FileMetadata;
import file.service.entity.FilePermisionType;
import file.service.entity.User;

import java.io.Serializable;


public class FilePermissionDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    private long id;
    private User user;
    private FileMetadata fileMetadata;
    private FilePermisionType permissionType;

    // No-arguments constructor
    public FilePermissionDTO() { }

    public FilePermissionDTO(User user, FileMetadata fileMetadata, FilePermisionType permissionType)
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

    public FileMetadata getFileMetadata() {
        return fileMetadata;
    }
    public void setFileMetadata(FileMetadata fileMetadata) {
        this.fileMetadata = fileMetadata;
    }

    public FilePermisionType getPermissionType() {
        return permissionType;
    }
    public void setPermissionType(FilePermisionType permissionType) {
        this.permissionType = permissionType;
    }
}
