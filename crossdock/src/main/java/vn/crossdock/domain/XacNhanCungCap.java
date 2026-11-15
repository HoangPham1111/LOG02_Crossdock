package vn.crossdock.domain;
import jakarta.persistence.*;
@Entity @Table(name="xac_nhan_cung_cap")
public class XacNhanCungCap {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="id") public Long id;
    @Column(name="supplier_id") public Long supplierId;
    @Column(name="detail_id") public Long detailId;
    @Column(name="quantity") public Integer quantity;
    @Column(name="promised_date") public java.time.LocalDate promisedDate;
    @Column(name="status") public String status;
    @Column(name="note") public String note;
    @Column(name="created_by") public Long createdBy;
    @Column(name="created_at") public java.time.OffsetDateTime createdAt;

    // Read-only association; scalar FK is the persisted source.
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="supplier_id",insertable=false,updatable=false) public NhaCungCap supplierRef;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="detail_id",insertable=false,updatable=false) public ChiTietYeuCau detailRef;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="created_by",insertable=false,updatable=false) public NguoiDung createdByRef;
}
