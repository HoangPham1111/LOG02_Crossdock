package vn.crossdock.domain;
import jakarta.persistence.*;
@Entity @Table(name="chuyen_nhap")
public class ChuyenNhap {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="id") public Long id;
    @Column(name="code") public String code;
    @Column(name="vehicle_id") public Long vehicleId;
    @Column(name="planned_at") public java.time.OffsetDateTime plannedAt;
    @Column(name="status") public String status;
    @Column(name="created_by") public Long createdBy;
    @Column(name="unloaded_at") public java.time.OffsetDateTime unloadedAt;
    @Column(name="note") public String note;

    // Read-only association; scalar FK is the persisted source.
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="vehicle_id",insertable=false,updatable=false) public Xe vehicleRef;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="created_by",insertable=false,updatable=false) public NguoiDung createdByRef;
}
