package vn.crossdock.domain;
import jakarta.persistence.*;
@Entity @Table(name="luot_phuc_vu")
public class LuotPhucVu {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="id") public Long id;
    @Column(name="gate_id") public Long gateId;
    @Column(name="inbound_id") public Long inboundId;
    @Column(name="outbound_id") public Long outboundId;
    @Column(name="planned_start") public java.time.OffsetDateTime plannedStart;
    @Column(name="planned_end") public java.time.OffsetDateTime plannedEnd;
    @Column(name="arrived_at") public java.time.OffsetDateTime arrivedAt;
    @Column(name="left_at") public java.time.OffsetDateTime leftAt;
    @Column(name="status") public String status;
    @Column(name="created_by") public Long createdBy;

    // Read-only association; scalar FK is the persisted source.
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="gate_id",insertable=false,updatable=false) public CongKho gateRef;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="inbound_id",insertable=false,updatable=false) public ChuyenNhap inboundRef;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="outbound_id",insertable=false,updatable=false) public ChuyenXuat outboundRef;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="created_by",insertable=false,updatable=false) public NguoiDung createdByRef;
}
