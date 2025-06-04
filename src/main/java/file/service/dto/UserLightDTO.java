package file.service.dto;

public class UserLightDTO
{
    private static final long serialVersionUID = 1L;

    /* For DTO the fields are also private, because it adheres to JavaBeans
     ** standard and leads to more maintainable code */
    private Long id;
    private String username;

    public UserLightDTO() { }

    public UserLightDTO(String username)
    {
        this.username = username;
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
    public void setUsername(String username) {
        this.username = username;
    }
}
