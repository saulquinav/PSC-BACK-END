package file.service.dto.document;

import java.io.Serializable;

public class DocumentUpdateInfoDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    public DocumentUpdateInfoDTO(Long id,
                                 String name)
    {
        this.id = id;
        this.name = name;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name)
    {
        this.name = name;
    }
}
