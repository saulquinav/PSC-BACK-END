package file.service.dto;

import java.io.Serializable;

public class UserDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Long id;
    private String userName;
    private String firstName;
    private String surName;

    // No-argument constructor
    public UserDTO() { }

    public UserDTO(String userName, String firstName, String surName)
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
}
