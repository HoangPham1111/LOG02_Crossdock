package vn.crossdock.domain;
import jakarta.persistence.*;
@Entity @Table(name="khach_hang")
public class KhachHang {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="id") public Long id;
    @Column(name="code") public String code;
    @Column(name="name") public String name;
    @Column(name="phone") public String phone;
    @Column(name="active") public Boolean active;
}
