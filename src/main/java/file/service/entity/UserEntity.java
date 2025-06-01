package file.service.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", unique = true, nullable = false)
    private String password;

    // One-to-one relationship with UserInfo
    // CascadeType.ALL: Operations (persist, merge, remove) on User will cascade to UserInfo.
    // orphanRemoval = true: If a UserInfo is unlinked from a User, it will be removed from the database.
    // fetch = FetchType.LAZY: UserInfo will be loaded only when explicitly accessed.
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private UserInfoEntity userInfo;

    // One-to-many relationship with DocumentPermissionEntity (the join table entity).
    // 'mappedBy' indicates that the 'user' field in DocumentPermissionEntity is the owning side.
    // CascadeType.ALL will propagate persist, merge, remove operations from User to DocumentPermissionEntity.
    // orphanRemoval = true ensures that if a DocumentPermissionEntity association is removed from this set,
    // the DocumentPermissionEntity entity itself is deleted from the database.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DocumentPermissionEntity> documentPermissions = new HashSet<DocumentPermissionEntity>();

    // No-argument constructor
    public UserEntity() { }

    public UserEntity(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public UserEntity(String username, String password, Set<DocumentPermissionEntity> documentPermissions)
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
    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public UserInfoEntity getUserInfo() { return userInfo; }
    public void setUserInfo(UserInfoEntity userInfo) { this.userInfo = userInfo; }

    public Set<DocumentPermissionEntity> getFilePermissions() { return documentPermissions; }
    public void setFilePermissions(Set<DocumentPermissionEntity> documentPermissions)
    {
        this.documentPermissions = documentPermissions;
    }
}
