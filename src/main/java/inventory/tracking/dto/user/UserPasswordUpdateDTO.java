package inventory.tracking.dto.user;

import inventory.tracking.entity.IdOwner;

import java.io.Serializable;

public class UserPasswordUpdateDTO implements Serializable, IdOwner<Long>
{
    private static final long serialVersionUID = 1L;

    private Long id;
    private String oldPassword;
    private String newPassword;

    public UserPasswordUpdateDTO() { }

    public UserPasswordUpdateDTO(Long id, String username)
    {
        this.id = id;
        this.newPassword = username;
    }

    @Override
    public Long getId() { return id; }
    @Override
    public void setId(Long id) { this.id = id; }

    public String getOldPassword() { return oldPassword; }
    public void setOldPassword(String oldPassword) { this.oldPassword = oldPassword; }

    public String getNewPassword() { return newPassword; }
    public void setNewPassword(String newPassword) { this.newPassword = newPassword; }

}
