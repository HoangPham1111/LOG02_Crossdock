package vn.crossdock.domain;
import jakarta.persistence.*;
@Entity @Table(name="chi_tiet_giao")
public class ChiTietGiao {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="id") public Long id;
    @Column(name="result_id") public Long resultId;
    @Column(name="allocation_id") public Long allocationId;
    @Column(name="status") public String status;
    @Column(name="late") public Boolean late;
    @Column(name="note") public String note;

    // Read-only association; scalar FK is the persisted source.
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="result_id",insertable=false,updatable=false) public KetQuaGiaoHang resultRef;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="allocation_id",insertable=false,updatable=false) public PhanBoThung allocationRef;
}
