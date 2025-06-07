package file.service.dto.document;

import java.io.Serializable;

public class DocumentReadingDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private byte[] data; // the document BLOB
    public DocumentReadingDTO(Long id,
                                String name,
                               byte[] data)
    {
        this.id = id;
        this.name = name;
        this.data = data;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name)
    {
        this.name = name;
    }

    public byte[] getData() { return data; }
    public void setData(byte[] data)
    {
        this.data = data;
    }
}
