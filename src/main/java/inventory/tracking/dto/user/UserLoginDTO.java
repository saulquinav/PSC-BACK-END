package inventory.tracking.dto.user;

import java.io.Serializable;

public class UserLoginDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;

    public UserLoginDTO() { }

    public UserLoginDTO(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
