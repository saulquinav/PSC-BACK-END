package file.service.dto.userinfo;

import file.service.entity.IdOwner;

import java.io.Serializable;

public class UserInfoDTO implements Serializable, IdOwner<Long>
{
    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstname;
    private String surname;

    public UserInfoDTO() { }

    public UserInfoDTO(String firstname, String surname)
    {
        this.firstname = firstname;
        this.surname = surname;
    }

    // These methods are the implementation of IdOwner<Long>
    @Override
    public Long getId() { return id; }
    @Override
    public void setId(Long id) { this.id = id; }

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
}