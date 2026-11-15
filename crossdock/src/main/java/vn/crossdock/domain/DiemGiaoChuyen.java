package vn.crossdock.domain;
import jakarta.persistence.*;
@Entity @Table(name="diem_giao_chuyen")
public class DiemGiaoChuyen {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="id") public Long id;
    @Column(name="trip_id") public Long tripId;
    @Column(name="location_id") public Long locationId;
    @Column(name="sequence_no") public Integer sequenceNo;
    @Column(name="address_snapshot") public String addressSnapshot;
    @Column(name="status") public String status;

    // Read-only association; scalar FK is the persisted source.
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="trip_id",insertable=false,updatable=false) public ChuyenXuat tripRef;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="location_id",insertable=false,updatable=false) public DiaDiemGiao locationRef;
}
