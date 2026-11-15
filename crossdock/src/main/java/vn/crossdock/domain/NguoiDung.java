package vn.crossdock.domain;
import jakarta.persistence.*;
@Entity @Table(name="nguoi_dung")
public class NguoiDung {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="id") public Long id;
    @Column(name="code") public String code;
    @Column(name="name") public String name;
    @Column(name="email") public String email;
    @Column(name="password") public String password;
    @Column(name="role") public String role;
    @Column(name="active") public Boolean active;
    @Column(name="created_at") public java.time.OffsetDateTime createdAt;
}
