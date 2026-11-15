package vn.crossdock.domain;
import jakarta.persistence.*;
@Entity @Table(name="don_hang")
public class DonHang {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="id") public Long id;
    @Column(name="code") public String code;
    @Column(name="customer_id") public Long customerId;
    @Column(name="created_by") public Long createdBy;
    @Column(name="status") public String status;
    @Column(name="created_at") public java.time.OffsetDateTime createdAt;
    @Column(name="note") public String note;

    // Read-only association; scalar FK is the persisted source.
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="customer_id",insertable=false,updatable=false) public KhachHang customerRef;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="created_by",insertable=false,updatable=false) public NguoiDung createdByRef;
}
