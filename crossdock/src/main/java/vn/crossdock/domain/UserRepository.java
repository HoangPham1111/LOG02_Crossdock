package vn.crossdock.domain;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface UserRepository extends JpaRepository<NguoiDung,Long>{ Optional<NguoiDung> findByCode(String code); }
