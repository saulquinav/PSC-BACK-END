package file.service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_infos")
public class UserInfoEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "surname", nullable = false)
    private String surname;

    // One-to-one relationship with User
    // @JoinColumn specifies the foreign key column in the user_info table that references the users table.
    // nullable = false ensures that every UserInfo must be linked to a User.
    // unique = true ensures that only one UserInfo can point to a specific User, enforcing the one-to-one.
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = true)
    private UserEntity user;

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstName) {
        this.firstname = firstName;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surName) {
        this.surname = surName;
    }

    public UserEntity getUser() { return user; }

    public void setUser(UserEntity user) { this.user = user; }
}
