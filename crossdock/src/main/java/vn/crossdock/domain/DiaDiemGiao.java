package vn.crossdock.domain;
import jakarta.persistence.*;
@Entity @Table(name="dia_diem_giao")
public class DiaDiemGiao {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="id") public Long id;
    @Column(name="customer_id") public Long customerId;
    @Column(name="code") public String code;
    @Column(name="name") public String name;
    @Column(name="address") public String address;
    @Column(name="region") public String region;
    @Column(name="latitude") public java.math.BigDecimal latitude;
    @Column(name="longitude") public java.math.BigDecimal longitude;
    @Column(name="active") public Boolean active;

    // Read-only association; scalar FK is the persisted source.
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="customer_id",insertable=false,updatable=false) public KhachHang customerRef;
}
