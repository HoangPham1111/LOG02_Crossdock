package vn.crossdock;
import org.springframework.web.bind.annotation.*;import org.springframework.http.*;import java.util.*;import org.slf4j.*;
@RestControllerAdvice(assignableTypes=ApiController.class) public class Errors {
 final Logger log=LoggerFactory.getLogger(Errors.class);
 @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)ResponseEntity<?> forbidden(Exception e){return ResponseEntity.status(403).body(Map.of("ok",false,"message",e.getMessage()));}
 @ExceptionHandler(IllegalArgumentException.class)ResponseEntity<?> validation(Exception e){return ResponseEntity.badRequest().body(Map.of("ok",false,"message",Objects.toString(e.getMessage(),"Dữ liệu không hợp lệ")));}
 @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)ResponseEntity<?> conflict(Exception e){return ResponseEntity.status(409).body(Map.of("ok",false,"message","Mã bị trùng hoặc dữ liệu vi phạm ràng buộc. Hãy làm mới và kiểm tra lại."));}
 @ExceptionHandler(Exception.class)ResponseEntity<?> other(Exception e){log.error("Operation failed",e);return ResponseEntity.status(500).body(Map.of("ok",false,"message","Không thể hoàn tất thao tác. Kiểm tra dữ liệu và nhật ký ứng dụng."));}
}
