package vn.crossdock.domain;
import jakarta.persistence.*;
@Entity @Table(name="xe")
public class Xe {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="id") public Long id;
    @Column(name="code") public String code;
    @Column(name="plate") public String plate;
    @Column(name="region") public String region;
    @Column(name="capacity_kg") public java.math.BigDecimal capacityKg;
    @Column(name="active") public Boolean active;
}
