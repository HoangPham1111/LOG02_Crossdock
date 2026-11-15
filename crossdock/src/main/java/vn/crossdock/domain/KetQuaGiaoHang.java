package vn.crossdock.domain;
import jakarta.persistence.*;
@Entity @Table(name="ket_qua_giao_hang")
public class KetQuaGiaoHang {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="id") public Long id;
    @Column(name="stop_id") public Long stopId;
    @Column(name="delivered_at") public java.time.OffsetDateTime deliveredAt;
    @Column(name="recorded_by") public Long recordedBy;
    @Column(name="note") public String note;
    @Column(name="created_at") public java.time.OffsetDateTime createdAt;

    // Read-only association; scalar FK is the persisted source.
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="stop_id",insertable=false,updatable=false) public DiemGiaoChuyen stopRef;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="recorded_by",insertable=false,updatable=false) public NguoiDung recordedByRef;
}
