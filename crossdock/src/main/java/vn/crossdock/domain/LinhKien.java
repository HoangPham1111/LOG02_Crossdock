package vn.crossdock.domain;
import jakarta.persistence.*;
@Entity @Table(name="linh_kien")
public class LinhKien {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="id") public Long id;
    @Column(name="code") public String code;
    @Column(name="name") public String name;
    @Column(name="unit") public String unit;
    @Column(name="active") public Boolean active;
}
