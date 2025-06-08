package file.service.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "document_datas")
public class DocumentDataEntity
{
    @Id
    /* No need for @GeneratedValue() here, because this Entity is in a one-to-one
    ** relationship and its ID is shared from DocumentMetadataEntity using @MapsId.
    ** @MapsId tells JPA: “use the ID from the associated entity (documentMetadata)
    ** as the primary key.”
    ** So the @Id field is not independently generated — it’s just a copy of
    ** documentMetadata.id.  */
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Lob
    @Column(name = "data", length = 100000)
    private byte[] data; // the document BLOB

    // One-to-one relationship with DocumentMedata
    @OneToOne
    @JoinColumn(name ="document_id_fk") // 'document_id_fk' is a column of this table ('document_datas')
    @MapsId
    private DocumentMetadataEntity documentMetadata;

    public Long getId() { return id; }
    public void setId(long id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }
    public void setData(byte[] data) {
        this.data = data;
    }
}
