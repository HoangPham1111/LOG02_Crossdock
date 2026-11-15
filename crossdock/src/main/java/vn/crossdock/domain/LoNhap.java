package vn.crossdock.domain;
import jakarta.persistence.*;
@Entity @Table(name="lo_nhap")
public class LoNhap {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="id") public Long id;
    @Column(name="code") public String code;
    @Column(name="inbound_id") public Long inboundId;
    @Column(name="supplier_id") public Long supplierId;

    // Read-only association; scalar FK is the persisted source.
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="inbound_id",insertable=false,updatable=false) public ChuyenNhap inboundRef;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="supplier_id",insertable=false,updatable=false) public NhaCungCap supplierRef;
}
