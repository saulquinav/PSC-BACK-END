package file.service.dto.docmetadata;

import file.service.entity.IdOwner;

import java.io.Serializable;

public class DocumentMetadataDTO implements Serializable, IdOwner<Long>
{
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    public DocumentMetadataDTO() { }

    public DocumentMetadataDTO(String name)
    {
        this.name = name;
    }

    public DocumentMetadataDTO(Long id, String name)
    {
        this.id = id;
        this.name = name;
    }

    // These methods are the implementation of IdOwner<Long>
    @Override
    public Long getId() { return id; }
    @Override
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name)
    {
        this.name = name;
    }
}
