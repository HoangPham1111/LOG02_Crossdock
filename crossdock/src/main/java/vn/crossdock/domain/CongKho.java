package vn.crossdock.domain;
import jakarta.persistence.*;
@Entity @Table(name="cong_kho")
public class CongKho {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="id") public Long id;
    @Column(name="code") public String code;
    @Column(name="name") public String name;
    @Column(name="kind") public String kind;
    @Column(name="active") public Boolean active;
}
