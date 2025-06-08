package file.service.dto.user;

import java.io.Serializable;

public class UserLoginResponseDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String token;

    public UserLoginResponseDTO() { }

    public UserLoginResponseDTO(String token)
    {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
