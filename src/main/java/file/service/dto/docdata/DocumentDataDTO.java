package file.service.dto.docdata;

import java.io.Serializable;

public class DocumentDataDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Long id;
    private byte[] data; // the document BLOB

    public DocumentDataDTO(byte[] data)
    {
        this.data = data;
    }

    public DocumentDataDTO(Long id, byte[] data)
    {
        this.id = id;
        this.data = data;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public byte[] getData() { return data; }
    public void setData(byte[] data) { this.data = data; }
}
