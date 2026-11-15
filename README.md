Hệ thống quản lý kho trung chuyển Cross-Docking
Báo cáo cuối kỳ môn Phân tích thiết kế hướng đối tượng (OOSD) – CNPM1.

Hệ thống hỗ trợ quản lý kho trung chuyển linh kiện ô tô tại Bình Dương, từ tiếp nhận nhu cầu giao hàng, ghi nhận nguồn cung, tiếp nhận và kiểm nhận thùng hàng đến phân loại, điều phối chuyến xuất và ghi nhận kết quả giao nhận. Mục tiêu là theo dõi xuyên suốt luồng hàng, hạn chế phân bổ trùng và kiểm soát tải trọng, cổng kho, sức chứa khu chờ.
Nội dung	Thông tin
Sinh viên thực hiện	Phạm Huy Hoàng
Mã số sinh viên	1250080062
Repository	BC_CUOIKI_OOSD_CNPM1
Phạm vi	Một kho trung chuyển, 3 cổng nhập, 3 cổng xuất
Vai trò sử dụng	Quản trị viên, nhân viên kho, nhân viên điều phối


1. Chức năng chính
Quản trị viên
Quản lý tài khoản và phân quyền; quản lý danh mục linh kiện, xe, cổng kho, khu chờ và tham số vận hành; theo dõi tổng quan, báo cáo và truy xuất dữ liệu. Người dùng được cập nhật hồ sơ cá nhân và mật khẩu trong phạm vi cho phép.
Nhân viên kho
Nhập và kiểm tra danh sách hàng dự kiến từ Excel; kiểm nhận hàng thực tế; phân loại và bố trí thùng vào khu chờ; xem hàng tại kho và in nhãn mã vạch; xếp đúng thùng đã phân bổ lên xe; xử lý hàng cách ly và tiếp nhận hàng hoàn thực tế.
Nhân viên điều phối
Quản lý khách hàng, địa điểm giao và nhà cung cấp; lập đơn hàng và yêu cầu giao; ghi nhận phản hồi nguồn cung; tạo chuyến nhập, bố trí lịch cổng; tạo chuyến xuất, phân bổ thùng và điểm giao; xác nhận xe xuất kho, tải phiếu PDF và ghi nhận kết quả giao nhận.

Khách hàng, nhà cung cấp và tài xế là thông tin nghiệp vụ, không có tài khoản đăng nhập riêng trong phạm vi đồ án.
2. Công nghệ sử dụng
Thành phần	Công nghệ
Ngôn ngữ	Java 21
Backend	Spring Boot 3.5.7, Spring MVC
Giao diện	Thymeleaf, HTML, CSS, JavaScript
Xác thực và phân quyền	Spring Security, session, BCrypt, CSRF
Truy cập dữ liệu	Spring Data JPA, JdbcTemplate
Cơ sở dữ liệu	PostgreSQL 17, PostGIS 3.5
Đọc Excel	Apache POI
Xuất PDF	Apache PDFBox
Tạo mã vạch	ZXing
Build và chạy	Maven, Docker Compose


Giao diện và backend được đóng gói trong cùng ứng dụng. Không cần cài Node.js hoặc chạy frontend riêng.
3. Tải mã nguồn và xác định thư mục chạy
git clone https://github.com/HoangPham1111/BC_CUOIKI_OOSD_CNPM1.git
cd BC_CUOIKI_OOSD_CNPM1
Nếu đã có thư mục trên máy, chỉ cần mở PowerShell tại thư mục đó. Có thể tải ZIP bằng nút Code trên GitHub và giải nén thay cho lệnh clone.

Repository có thể chứa cả báo cáo, slide và sơ đồ. Các lệnh cài đặt dưới đây phải chạy tại thư mục mã nguồn có đồng thời hai tệp pom.xml và compose.yaml, không nhất thiết là thư mục gốc repository.

Để tìm vị trí tệp trên Windows:
Get-ChildItem -Recurse -Filter compose.yaml
Sau đó dùng cd chuyển đến thư mục chứa tệp vừa tìm được. Trong bộ mã nguồn cung cấp ban đầu, thư mục này tên là crossdock.
4. Cài đặt và chạy bằng Docker
4.1. Chuẩn bị
Cài Docker Desktop, bật Linux containers và bảo đảm Docker đang chạy. Trên Windows, cần cấu hình WSL2 phù hợp với Docker Desktop. Lần chạy đầu cần Internet để tải image và thư viện.
docker --version
docker compose version
4.2. Khởi động
Tại thư mục chứa compose.yaml, chạy:
docker compose up --build -d
docker compose ps
docker compose logs -f app
Khi nhật ký xuất hiện Started Application, truy cập:

http://localhost:8080

Nhấn Ctrl+C để thoát chế độ xem nhật ký; thao tác này không dừng các container chạy nền.

Cấu hình mặc định đủ để chạy demo. Nếu cần đổi mật khẩu cơ sở dữ liệu, sao chép .env.example thành .env và sửa DB_PASSWORD trước lần khởi tạo đầu tiên. Mật khẩu cơ sở dữ liệu khác mật khẩu đăng nhập website. Đổi .env sau khi dữ liệu đã được tạo không tự đổi mật khẩu PostgreSQL trong volume.
4.3. Tài khoản demo
Tài khoản	Mật khẩu	Vai trò
admin	Demo@12345	Quản trị viên
kho	Demo@12345	Nhân viên kho
dieuphoi	Demo@12345	Nhân viên điều phối


Ứng dụng tự tạo bảng và nạp dữ liệu mẫu khi chức năng seed được bật và bảng người dùng chưa có dữ liệu. Không chạy thêm tệp seed thủ công sau khi ứng dụng đã nạp dữ liệu. Các tài khoản trên chỉ dùng cho môi trường học tập và demo.
4.4. Dừng và chạy lại
docker compose stop
docker compose start
Sau khi cập nhật mã nguồn, chạy lại:
docker compose up --build -d
Dữ liệu PostgreSQL được lưu trong volume pgdata, tham số vận hành được lưu trong appdata. Không dùng docker compose down -v nếu muốn giữ dữ liệu vì tùy chọn -v xóa các volume của project.
5. Chạy bằng Java và Maven
Cách này dành cho người muốn chạy hoặc chỉnh sửa mã nguồn trực tiếp. Cài JDK 21 và Maven 3.9 trở lên, sau đó kiểm tra:
java -version
mvn -version
Nếu đã chạy toàn bộ hệ thống bằng Docker, dừng container ứng dụng để tránh trùng cổng, rồi giữ cơ sở dữ liệu hoạt động:
docker compose stop app
docker compose up -d db
Cấu hình kết nối trong PowerShell bằng các giá trị khớp với cơ sở dữ liệu đã khởi tạo:
$env:DB_URL='jdbc:postgresql://localhost:5432/crossdock'
$env:DB_USER='crossdock'
$env:DB_PASSWORD='crossdock_demo_2026'
mvn spring-boot:run
Giá trị mật khẩu trên là cấu hình demo mặc định. Nếu đã đổi mật khẩu khi tạo cơ sở dữ liệu, thay bằng mật khẩu đã đặt. Ứng dụng đọc cấu hình tại src/main/resources/application.properties và cho phép ghi đè bằng biến môi trường.

Có thể đóng gói và chạy tệp JAR thay cho spring-boot:run:
mvn clean package
java -jar target/crossdock-1.0.0.jar
Mở http://localhost:8080 sau khi ứng dụng khởi động. Với IntelliJ IDEA, mở project Maven, chọn SDK 21 và chạy lớp vn.crossdock.Application với cùng cấu hình kết nối.

Nếu không sử dụng Docker, cài PostgreSQL 17 và tạo tài khoản, cơ sở dữ liệu bằng pgAdmin hoặc SQL Shell:
CREATE USER crossdock WITH PASSWORD 'crossdock_demo_2026';
CREATE DATABASE crossdock OWNER crossdock;
Chạy từng lệnh trên ngoài transaction. Nếu sử dụng PostGIS, kết nối vào database crossdock bằng tài khoản có quyền tạo extension và chạy CREATE EXTENSION IF NOT EXISTS postgis;. Sau đó chạy ứng dụng bằng Maven như trên.
6. Cấu trúc thư mục mã nguồn
crossdock/
├── src/main/java/vn/crossdock/     Xử lý nghiệp vụ, phân quyền và dữ liệu
├── src/main/resources/templates/  Giao diện Thymeleaf
├── src/main/resources/static/     CSS, JavaScript và tài nguyên giao diện
├── src/main/resources/db/         Schema và dữ liệu mẫu tự động
├── src/test/                      Mã kiểm thử
├── database/                      Các tệp SQL độc lập
├── demo-excel/                    Tệp Excel phục vụ demo
├── docs/                          Hướng dẫn cài đặt và nghiệp vụ
├── scripts/                       Script hỗ trợ
├── pom.xml                        Cấu hình Maven
├── Dockerfile                     Đóng gói ứng dụng
└── compose.yaml                   Chạy ứng dụng và cơ sở dữ liệu
Tên thư mục bao ngoài có thể khác tùy cách sắp xếp tài liệu trong repository; cấu trúc trên mô tả phần mã nguồn.
7. Quy trình sử dụng và các ràng buộc chính
Nhân viên điều phối tạo đơn hàng, yêu cầu giao và ghi nhận cam kết cung cấp. Sau đó tạo chuyến nhập, lô nhập và bố trí cổng. Nhân viên kho nhập danh sách dự kiến, kiểm nhận khi xe vào cổng, xác nhận hàng thực tế và bố trí thùng đạt vào khu chờ phù hợp. Điều phối phân bổ hàng đủ điều kiện vào chuyến xuất; kho xếp hàng khi xe đã vào cổng xuất; điều phối xác nhận xe rời kho và ghi nhận kết quả giao nhận.
- Mỗi thùng chứa một loại linh kiện và được xử lý nguyên thùng.
- Một thùng không được phân bổ đồng thời cho nhiều chuyến đang có hiệu lực.
- Chỉ hàng đạt, đã vào khu chờ và đủ điều kiện mới được phân bổ; phải đáp ứng đủ từng loại linh kiện của yêu cầu.
- Ghép nguyên yêu cầu cùng miền; yêu cầu vượt giới hạn 18 tấn được tách theo thùng nguyên thành các chuyến riêng.
- Tổng trọng lượng cả bì không vượt tải trọng xe và giới hạn 18.000 kg. Trọng lượng cả bì là trọng lượng toàn thùng, không nhân lại với số linh kiện.
- Không xếp vượt sức chứa khu chờ, không bố trí trùng lịch cổng hoặc xe; xe phải vào cổng xuất trước khi kho xác nhận xếp hàng.
- Kho xác nhận hàng thực tế; điều phối xác nhận xe rời kho. Hàng hoàn chỉ được đưa lại vào luồng xử lý sau khi kho tiếp nhận và kiểm tra.
Để xem hướng dẫn đầy đủ, mở các tài liệu trong docs/, đặc biệt 02_NGHIEP_VU_VA_PHAN_QUYEN.md và 03_NAM_KICH_BAN_DEMO.md.
8. Nhập dữ liệu Excel
Trong tài khoản nhân viên kho, mở Kho, chọn tab Tiếp nhận, chọn lô nhập và tải tệp .xlsx. Nhấn Kiểm tra tệp để xem lỗi trước khi chọn Nhập chính thức. Kiểm tra tệp không lưu dữ liệu; dữ liệu nhập chính thức vẫn là hàng dự kiến cho đến khi được kiểm nhận thực tế.

Tệp có hàng đầu tiên là tiêu đề, sử dụng các cột theo thứ tự:
ma_thung, ma_linh_kien, ten_linh_kien, so_luong, trong_luong_kg, ma_yeu_cau, ma_xac_nhan
ma_xac_nhan là ID số của phản hồi cung cấp đã chấp nhận, không phải mã nhà cung cấp. Tệp tối đa 5 MB và 2.000 dòng dữ liệu; không dùng công thức trong các ô dữ liệu. Dùng các tệp trong demo-excel/ theo docs/06_HUONG_DAN_EXCEL.md, không nhập đồng thời các bộ mẫu có mã thùng trùng nhau.
9. Kiểm thử
Kiểm thử tích hợp cần cơ sở dữ liệu riêng. Tại thư mục mã nguồn, chạy:
docker compose up -d db
docker compose exec db psql -U crossdock -d postgres -c "CREATE DATABASE crossdock_test OWNER crossdock;"
$env:RUN_DB_TESTS='true'
$env:DB_URL='jdbc:postgresql://localhost:5432/crossdock_test'
$env:DB_USER='crossdock'
$env:DB_PASSWORD='crossdock_demo_2026'
mvn test
Bỏ qua lệnh tạo database nếu crossdock_test đã tồn tại. Thay mật khẩu nếu đã thay cấu hình mặc định. Không dùng database đang demo để chạy kiểm thử. Xem kết quả tại target/surefire-reports.

Sau khi kiểm thử, xóa cấu hình môi trường dành riêng cho test trước khi chạy lại website:
Remove-Item Env:RUN_DB_TESTS -ErrorAction SilentlyContinue
Remove-Item Env:DB_URL -ErrorAction SilentlyContinue
Khi không đặt RUN_DB_TESTS=true, các kiểm thử tích hợp có điều kiện sẽ bị bỏ qua. Build thành công không đồng nghĩa toàn bộ kiểm thử tích hợp đã chạy.
10. Lỗi thường gặp
Hiện tượng	Cách kiểm tra và xử lý
Docker không kết nối được	Mở Docker Desktop, chờ engine hoạt động rồi chạy lại
Không tìm thấy tệp cấu hình Compose	Chuyển đến thư mục chứa compose.yaml
Cổng 8080 đã được sử dụng	Dừng ứng dụng đang chiếm cổng hoặc đổi mapping app thành 8081:8080, sau đó mở http://localhost:8081
Cổng 5432 đã được sử dụng	Dừng PostgreSQL khác hoặc đổi mapping db thành 127.0.0.1:5433:5432; Java chạy ngoài Docker phải đổi DB_URL sang cổng 5433
Sai mật khẩu PostgreSQL	Kiểm tra mật khẩu lúc khởi tạo volume; thay .env không tự đổi mật khẩu database cũ
Không đăng nhập được tài khoản demo	Kiểm tra seed và database đang kết nối; tài khoản mẫu không tự tạo lại nếu bảng người dùng đã có dữ liệu
Trang báo 403	Kiểm tra vai trò và phiên đăng nhập; không tắt CSRF để bỏ qua lỗi
Excel bị từ chối	Kiểm tra định dạng .xlsx, tiêu đề, mã tham chiếu và lỗi từng dòng
Maven không nhận Java 21	Kiểm tra java -version, mvn -version và cấu hình JAVA_HOME


Xem nhật ký để xác định lỗi khởi động:
docker compose logs --tail=100 app
docker compose logs --tail=100 db
11. Phạm vi và lưu ý
Đây là sản phẩm học tập, sử dụng dữ liệu giả lập. Hệ thống chưa tích hợp GPS thực tế, kế toán, thanh toán hoặc tối ưu tuyến đường tự động. Chức năng PDF phục vụ phiếu chuyến xuất; không mặc định bao gồm xuất tất cả báo cáo tổng hợp.

Chỉ công bố mã nguồn và dữ liệu phù hợp cho việc học tập. Không đưa mật khẩu thật, tệp .env, dữ liệu cá nhân thực tế hoặc tệp sao lưu cơ sở dữ liệu chứa thông tin nhạy cảm lên repository công khai. Các thông số và tài khoản demo cần được thay đổi nếu triển khai ngoài môi trường thực hành.
