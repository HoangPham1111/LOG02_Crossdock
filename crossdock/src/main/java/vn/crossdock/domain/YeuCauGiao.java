package vn.crossdock.domain;
import jakarta.persistence.*;
@Entity @Table(name="yeu_cau_giao")
public class YeuCauGiao {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="id") public Long id;
    @Column(name="code") public String code;
    @Column(name="order_id") public Long orderId;
    @Column(name="location_id") public Long locationId;
    @Column(name="earliest") public java.time.LocalDate earliest;
    @Column(name="latest") public java.time.LocalDate latest;
    @Column(name="early_consent") public String earlyConsent;
    @Column(name="status") public String status;

    // Read-only association; scalar FK is the persisted source.
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="order_id",insertable=false,updatable=false) public DonHang orderRef;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="location_id",insertable=false,updatable=false) public DiaDiemGiao locationRef;
}
