package vn.crossdock.domain;
import jakarta.persistence.*;
@Entity @Table(name="khu_cho")
public class KhuCho {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="id") public Long id;
    @Column(name="code") public String code;
    @Column(name="name") public String name;
    @Column(name="region") public String region;
    @Column(name="capacity") public Integer capacity;
    @Column(name="active") public Boolean active;
}
