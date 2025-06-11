package inventory.tracking.dto.docdata;

import inventory.tracking.entity.IdOwner;
import jakarta.json.bind.annotation.JsonbProperty;

import java.io.Serializable;

public class DocumentDataDTO implements Serializable, IdOwner<Long>
{
    private static final long serialVersionUID = 1L;

    private Long id;

    @JsonbProperty("data") // Optional
    private byte[] data; // the document BLOB

    public DocumentDataDTO() { }

    public DocumentDataDTO(byte[] data)
    {
        this.data = data;
    }

    public DocumentDataDTO(Long id, byte[] data)
    {
        this.id = id;
        this.data = data;
    }

    // These methods are the implementation of IdOwner<Long>
    @Override
    public Long getId() { return id; }
    @Override
    public void setId(Long id) { this.id = id; }

    public byte[] getData() { return data; }
    public void setData(byte[] data) { this.data = data; }
}
