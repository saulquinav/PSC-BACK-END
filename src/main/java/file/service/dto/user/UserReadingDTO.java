package file.service.dto.user;

import java.io.Serializable;

public class UserReadingDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;

    public UserReadingDTO() { }

    public UserReadingDTO(Long id, String username)
    {
        this.id = id;
        this.username = username;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}
