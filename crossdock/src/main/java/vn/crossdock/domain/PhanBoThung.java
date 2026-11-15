package vn.crossdock.domain;
import jakarta.persistence.*;
@Entity @Table(name="phan_bo_thung")
public class PhanBoThung {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="id") public Long id;
    @Column(name="box_id") public Long boxId;
    @Column(name="stop_id") public Long stopId;
    @Column(name="status") public String status;
    @Column(name="allocated_by") public Long allocatedBy;
    @Column(name="allocated_at") public java.time.OffsetDateTime allocatedAt;
    @Column(name="loaded_by") public Long loadedBy;
    @Column(name="loaded_at") public java.time.OffsetDateTime loadedAt;

    // Read-only association; scalar FK is the persisted source.
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="box_id",insertable=false,updatable=false) public ThungHang boxRef;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="stop_id",insertable=false,updatable=false) public DiemGiaoChuyen stopRef;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="allocated_by",insertable=false,updatable=false) public NguoiDung allocatedByRef;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="loaded_by",insertable=false,updatable=false) public NguoiDung loadedByRef;
}
