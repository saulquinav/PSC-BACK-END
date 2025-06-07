package file.service.dto.user;

import file.service.entity.IdCarrier;

import java.io.Serializable;

public class UserUpdateDTO implements Serializable, IdCarrier<Long>
{
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String password;

    public UserUpdateDTO(Long id, String username)
    {
        this.id = id;
        this.username = username;
    }

    @Override
    public Long getId() { return id; }
    @Override
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
