package vn.crossdock.domain;
import jakarta.persistence.*;
@Entity @Table(name="thung_hang")
public class ThungHang {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="id") public Long id;
    @Column(name="code") public String code;
    @Column(name="source_code") public String sourceCode;
    @Column(name="lot_id") public Long lotId;
    @Column(name="part_id") public Long partId;
    @Column(name="detail_id") public Long detailId;
    @Column(name="supply_id") public Long supplyId;
    @Column(name="expected_qty") public Integer expectedQty;
    @Column(name="quantity") public Integer quantity;
    @Column(name="gross_kg") public java.math.BigDecimal grossKg;
    @Column(name="status") public String status;
    @Column(name="staging_id") public Long stagingId;
    @Column(name="inspected_by") public Long inspectedBy;
    @Column(name="inspected_at") public java.time.OffsetDateTime inspectedAt;
    @Column(name="staged_at") public java.time.OffsetDateTime stagedAt;
    @Column(name="note") public String note;

    // Read-only association; scalar FK is the persisted source.
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="lot_id",insertable=false,updatable=false) public LoNhap lotRef;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="part_id",insertable=false,updatable=false) public LinhKien partRef;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="detail_id",insertable=false,updatable=false) public ChiTietYeuCau detailRef;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="supply_id",insertable=false,updatable=false) public XacNhanCungCap supplyRef;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="staging_id",insertable=false,updatable=false) public KhuCho stagingRef;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="inspected_by",insertable=false,updatable=false) public NguoiDung inspectedByRef;
}
