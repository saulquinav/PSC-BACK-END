package file.service.dto.document;

import java.io.Serializable;


public class DocumentCreationDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String name;
    private byte[] data; // the document BLOB

    public DocumentCreationDTO(String name, byte[] data)
    {
        this.name = name;
        this.data = data;
    }

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
