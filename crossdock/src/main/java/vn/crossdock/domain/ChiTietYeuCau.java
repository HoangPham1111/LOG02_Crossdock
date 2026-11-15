package vn.crossdock.domain;
import jakarta.persistence.*;
@Entity @Table(name="chi_tiet_yeu_cau")
public class ChiTietYeuCau {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="id") public Long id;
    @Column(name="requirement_id") public Long requirementId;
    @Column(name="part_id") public Long partId;
    @Column(name="quantity") public Integer quantity;

    // Read-only association; scalar FK is the persisted source.
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="requirement_id",insertable=false,updatable=false) public YeuCauGiao requirementRef;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="part_id",insertable=false,updatable=false) public LinhKien partRef;
}
