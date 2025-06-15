package inventory.tracking.entity;

import jakarta.persistence.*;


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

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;

    // One-to-one relationship with UserInfo
    // CascadeType.ALL: Operations (persist, merge, remove) on User will cascade to UserInfo.
    // orphanRemoval = true: If a UserInfo is unlinked from a User, it will be removed from the database.
    // fetch = FetchType.LAZY: UserInfo will be loaded only when explicitly accessed.
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private UserInfoEntity userInfo;

    // No-argument constructor
    public UserEntity() { }

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

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public UserInfoEntity getUserInfo() { return userInfo; }
    public void setUserInfo(UserInfoEntity userInfo) { this.userInfo = userInfo; }
}
