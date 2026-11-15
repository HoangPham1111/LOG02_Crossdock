-- Seed độc lập: chỉ chạy MỘT LẦN trên schema trống. Mật khẩu demo: Demo@12345.
INSERT INTO nguoi_dung(id,code,name,email,password,role) VALUES
(1,'admin','Quản trị viên','admin@crossdock.local','$2a$10$DCppuOvnD.imokXn7BQyFuE.64ViZJiEHGEVfVk7TQkZfgd.4n3Lu','ADMIN'),
(2,'kho','Nguyễn Văn An','kho@crossdock.local','$2a$10$DCppuOvnD.imokXn7BQyFuE.64ViZJiEHGEVfVk7TQkZfgd.4n3Lu','KHO'),
(3,'dieuphoi','Trần Minh Anh','dieuphoi@crossdock.local','$2a$10$DCppuOvnD.imokXn7BQyFuE.64ViZJiEHGEVfVk7TQkZfgd.4n3Lu','DP');
INSERT INTO khach_hang(id,code,name,phone) VALUES
(1,'KH001','Nhà máy ô tô Bắc Việt','0901000001'),
(2,'KH002','Cơ khí Hà Nội','0901000002'),
(3,'KH003','Lắp ráp Miền Trung','0901000003'),
(4,'KH004','Ô tô Phương Nam','0901000004');
INSERT INTO dia_diem_giao(id,customer_id,code,name,address,region,latitude,longitude) VALUES
(1,1,'DD001','KCN A Hà Nội','Lô A1, KCN Quang Minh, Hà Nội','MB',21.15,105.76),
(2,2,'DD002','KCN B Hà Nội','Lô B2, KCN Thăng Long, Hà Nội','MB',21.11,105.78),
(3,3,'DD003','Nhà máy Đà Nẵng','Lô C3, KCN Hòa Khánh, Đà Nẵng','MT',16.07,108.15),
(4,4,'DD004','Xưởng Bình Dương','Lô D4, KCN Sóng Thần, Bình Dương','MN',10.9,106.75);
INSERT INTO linh_kien(id,code,name,unit) VALUES
(1,'LK001','Cụm phanh ABS','bộ'),
(2,'LK002','Trục truyền động','cái'),
(3,'LK003','Bộ dây điện','bộ'),
(4,'LK004','Cụm đèn pha','bộ');
INSERT INTO nha_cung_cap(id,code,name,phone) VALUES
(1,'NCC001','Linh kiện Đông Nam','0902000001'),
(2,'NCC002','Cơ khí Bình Dương','0902000002');
INSERT INTO xe(id,code,plate,region,capacity_kg) VALUES
(1,'MB-X01','61C-100.01','MB',18000),
(2,'MB-X02','61C-100.02','MB',18000),
(3,'MN-X01','61C-100.03','MN',18000),
(4,'MT-X01','61C-100.04','MT',18000),
(5,'MN-N01','61C-200.01','MN',18000),
(6,'MN-N02','61C-200.02','MN',18000),
(7,'MN-N03','61C-200.03','MN',18000),
(8,'MN-N04','61C-200.04','MN',18000);
INSERT INTO khu_cho(id,code,name,region,capacity) VALUES
(1,'KC-MB','Khu chờ miền Bắc','MB',50),
(2,'KC-MT','Khu chờ miền Trung','MT',40),
(3,'KC-MN','Khu chờ miền Nam','MN',30);
INSERT INTO cong_kho(id,code,name,kind) VALUES
(1,'IN01','Cổng nhập 1','IN'),
(2,'IN02','Cổng nhập 2','IN'),
(3,'IN03','Cổng nhập 3','IN'),
(4,'OUT01','Cổng xuất 1','OUT'),
(5,'OUT02','Cổng xuất 2','OUT'),
(6,'OUT03','Cổng xuất 3','OUT');
INSERT INTO don_hang(id,code,customer_id,created_by,note) VALUES
(1,'DH-001',1,3,'Ghép nguyên yêu cầu 8 tấn');
INSERT INTO yeu_cau_giao(id,code,order_id,location_id,earliest,latest) VALUES(1,'YC-001',1,1,current_date-2,current_date+7);
INSERT INTO chi_tiet_yeu_cau(id,requirement_id,part_id,quantity) VALUES
(1,1,1,80);
INSERT INTO xac_nhan_cung_cap(id,supplier_id,detail_id,quantity,promised_date,status,note,created_by) VALUES(1,1,1,80,current_date,'ACCEPTED','Đã liên hệ ngoài hệ thống',3);
INSERT INTO don_hang(id,code,customer_id,created_by,note) VALUES
(2,'DH-002',2,3,'Ghép nguyên yêu cầu 10 tấn');
INSERT INTO yeu_cau_giao(id,code,order_id,location_id,earliest,latest) VALUES(2,'YC-002',2,2,current_date-2,current_date+7);
INSERT INTO chi_tiet_yeu_cau(id,requirement_id,part_id,quantity) VALUES
(2,2,1,100);
INSERT INTO xac_nhan_cung_cap(id,supplier_id,detail_id,quantity,promised_date,status,note,created_by) VALUES(2,1,2,100,current_date,'ACCEPTED','Đã liên hệ ngoài hệ thống',3);
INSERT INTO don_hang(id,code,customer_id,created_by,note) VALUES
(3,'DH-003',1,3,'Thử chặn ghép nửa yêu cầu 12 tấn');
INSERT INTO yeu_cau_giao(id,code,order_id,location_id,earliest,latest) VALUES(3,'YC-003',3,1,current_date-2,current_date+7);
INSERT INTO chi_tiet_yeu_cau(id,requirement_id,part_id,quantity) VALUES
(3,3,1,120);
INSERT INTO xac_nhan_cung_cap(id,supplier_id,detail_id,quantity,promised_date,status,note,created_by) VALUES(3,1,3,120,current_date,'ACCEPTED','Đã liên hệ ngoài hệ thống',3);
INSERT INTO don_hang(id,code,customer_id,created_by,note) VALUES
(4,'DH-004',1,3,'Yêu cầu 25 tấn: 2 chuyến riêng');
INSERT INTO yeu_cau_giao(id,code,order_id,location_id,earliest,latest) VALUES(4,'YC-004',4,1,current_date-2,current_date+7);
INSERT INTO chi_tiet_yeu_cau(id,requirement_id,part_id,quantity) VALUES
(4,4,1,250);
INSERT INTO xac_nhan_cung_cap(id,supplier_id,detail_id,quantity,promised_date,status,note,created_by) VALUES(4,1,4,250,current_date,'ACCEPTED','Đã liên hệ ngoài hệ thống',3);
INSERT INTO don_hang(id,code,customer_id,created_by,note) VALUES
(5,'DH-005',4,3,'Hàng miền Nam');
INSERT INTO yeu_cau_giao(id,code,order_id,location_id,earliest,latest) VALUES(5,'YC-005',5,4,current_date-2,current_date+7);
INSERT INTO chi_tiet_yeu_cau(id,requirement_id,part_id,quantity) VALUES
(5,5,1,30);
INSERT INTO xac_nhan_cung_cap(id,supplier_id,detail_id,quantity,promised_date,status,note,created_by) VALUES(5,1,5,30,current_date,'ACCEPTED','Đã liên hệ ngoài hệ thống',3);
INSERT INTO don_hang(id,code,customer_id,created_by,note) VALUES
(6,'DH-006',3,3,'Hàng miền Trung');
INSERT INTO yeu_cau_giao(id,code,order_id,location_id,earliest,latest) VALUES(6,'YC-006',6,3,current_date-2,current_date+7);
INSERT INTO chi_tiet_yeu_cau(id,requirement_id,part_id,quantity) VALUES
(6,6,1,40);
INSERT INTO xac_nhan_cung_cap(id,supplier_id,detail_id,quantity,promised_date,status,note,created_by) VALUES(6,1,6,40,current_date,'ACCEPTED','Đã liên hệ ngoài hệ thống',3);
INSERT INTO don_hang(id,code,customer_id,created_by,note) VALUES
(7,'DH-007',4,3,'Demo Excel: 10 thùng phanh');
INSERT INTO yeu_cau_giao(id,code,order_id,location_id,earliest,latest) VALUES(7,'YC-007',7,4,current_date-2,current_date+7);
INSERT INTO chi_tiet_yeu_cau(id,requirement_id,part_id,quantity) VALUES
(7,7,1,100);
INSERT INTO xac_nhan_cung_cap(id,supplier_id,detail_id,quantity,promised_date,status,note,created_by) VALUES(7,1,7,100,current_date,'ACCEPTED','Đã liên hệ ngoài hệ thống',3);
INSERT INTO don_hang(id,code,customer_id,created_by,note) VALUES
(8,'DH-008',4,3,'Lịch sử giao hoàn tất');
INSERT INTO yeu_cau_giao(id,code,order_id,location_id,earliest,latest) VALUES(8,'YC-008',8,4,current_date-2,current_date+7);
INSERT INTO chi_tiet_yeu_cau(id,requirement_id,part_id,quantity) VALUES
(8,8,1,10);
INSERT INTO xac_nhan_cung_cap(id,supplier_id,detail_id,quantity,promised_date,status,note,created_by) VALUES(8,1,8,10,current_date,'ACCEPTED','Đã liên hệ ngoài hệ thống',3);
INSERT INTO chuyen_nhap(id,code,vehicle_id,planned_at,status,created_by,unloaded_at) VALUES(1,'CN-001',5,now()+interval '1 hour','LEFT',3,now());
INSERT INTO lo_nhap(id,code,inbound_id,supplier_id) VALUES
(1,'LO-001',1,1);
INSERT INTO chuyen_nhap(id,code,vehicle_id,planned_at,status,created_by,unloaded_at) VALUES(2,'CN-002',6,now()+interval '2 hour','EXPECTED',3,NULL);
INSERT INTO lo_nhap(id,code,inbound_id,supplier_id) VALUES
(2,'LO-002',2,1);
INSERT INTO chuyen_nhap(id,code,vehicle_id,planned_at,status,created_by,unloaded_at) VALUES(3,'CN-003',7,now()+interval '3 hour','EXPECTED',3,NULL);
INSERT INTO lo_nhap(id,code,inbound_id,supplier_id) VALUES
(3,'LO-003',3,1);
INSERT INTO chuyen_nhap(id,code,vehicle_id,planned_at,status,created_by,unloaded_at) VALUES(4,'CN-004',8,now()+interval '4 hour','EXPECTED',3,NULL);
INSERT INTO lo_nhap(id,code,inbound_id,supplier_id) VALUES
(4,'LO-004',4,1);
INSERT INTO chuyen_nhap(id,code,vehicle_id,planned_at,status,created_by,unloaded_at) VALUES(5,'CN-005',5,now()+interval '5 hour','EXPECTED',3,NULL);
INSERT INTO lo_nhap(id,code,inbound_id,supplier_id) VALUES
(5,'LO-005',5,1);
INSERT INTO chuyen_nhap(id,code,vehicle_id,planned_at,status,created_by,unloaded_at) VALUES(6,'CN-006',6,now()+interval '6 hour','EXPECTED',3,NULL);
INSERT INTO lo_nhap(id,code,inbound_id,supplier_id) VALUES
(6,'LO-006',6,1);
INSERT INTO chuyen_nhap(id,code,vehicle_id,planned_at,status,created_by,unloaded_at) VALUES(10,'CN-HIS-10',5,now()-interval '10 hours','LEFT',3,now()-interval '9 hours');
INSERT INTO lo_nhap(id,code,inbound_id,supplier_id) VALUES
(10,'LO-HIS-10',10,1);
INSERT INTO chuyen_nhap(id,code,vehicle_id,planned_at,status,created_by,unloaded_at) VALUES(11,'CN-HIS-11',5,now()-interval '11 hours','LEFT',3,now()-interval '10 hours');
INSERT INTO lo_nhap(id,code,inbound_id,supplier_id) VALUES
(11,'LO-HIS-11',11,1);
INSERT INTO chuyen_nhap(id,code,vehicle_id,planned_at,status,created_by,unloaded_at) VALUES(12,'CN-HIS-12',5,now()-interval '12 hours','LEFT',3,now()-interval '11 hours');
INSERT INTO lo_nhap(id,code,inbound_id,supplier_id) VALUES
(12,'LO-HIS-12',12,1);
INSERT INTO chuyen_nhap(id,code,vehicle_id,planned_at,status,created_by,unloaded_at) VALUES(13,'CN-HIS-13',5,now()-interval '13 hours','LEFT',3,now()-interval '12 hours');
INSERT INTO lo_nhap(id,code,inbound_id,supplier_id) VALUES
(13,'LO-HIS-13',13,1);
INSERT INTO chuyen_nhap(id,code,vehicle_id,planned_at,status,created_by,unloaded_at) VALUES(14,'CN-HIS-14',5,now()-interval '14 hours','LEFT',3,now()-interval '13 hours');
INSERT INTO lo_nhap(id,code,inbound_id,supplier_id) VALUES
(14,'LO-HIS-14',14,1);
INSERT INTO chuyen_nhap(id,code,vehicle_id,planned_at,status,created_by,unloaded_at) VALUES(15,'CN-HIS-15',5,now()-interval '15 hours','LEFT',3,now()-interval '14 hours');
INSERT INTO lo_nhap(id,code,inbound_id,supplier_id) VALUES
(15,'LO-HIS-15',15,1);
INSERT INTO chuyen_nhap(id,code,vehicle_id,planned_at,status,created_by,unloaded_at) VALUES(16,'CN-HIS-16',5,now()-interval '16 hours','LEFT',3,now()-interval '15 hours');
INSERT INTO lo_nhap(id,code,inbound_id,supplier_id) VALUES
(16,'LO-HIS-16',16,1);
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(1,'MB-0001','SRC-0001',1,1,1,1,10,10,1000,'STAGED',1,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(2,'MB-0002','SRC-0002',1,1,1,1,10,10,1000,'STAGED',1,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(3,'MB-0003','SRC-0003',1,1,1,1,10,10,1000,'STAGED',1,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(4,'MB-0004','SRC-0004',1,1,1,1,10,10,1000,'STAGED',1,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(5,'MB-0005','SRC-0005',1,1,1,1,10,10,1000,'STAGED',1,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(6,'MB-0006','SRC-0006',1,1,1,1,10,10,1000,'STAGED',1,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(7,'MB-0007','SRC-0007',1,1,1,1,10,10,1000,'STAGED',1,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(8,'MB-0008','SRC-0008',1,1,1,1,10,10,1000,'STAGED',1,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(9,'MB-0009','SRC-0009',10,1,2,2,10,10,1000,'STAGED',1,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(10,'MB-0010','SRC-0010',10,1,2,2,10,10,1000,'STAGED',1,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(11,'MB-0011','SRC-0011',10,1,2,2,10,10,1000,'STAGED',1,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(12,'MB-0012','SRC-0012',10,1,2,2,10,10,1000,'STAGED',1,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(13,'MB-0013','SRC-0013',10,1,2,2,10,10,1000,'STAGED',1,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(14,'MB-0014','SRC-0014',10,1,2,2,10,10,1000,'STAGED',1,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(15,'MB-0015','SRC-0015',10,1,2,2,10,10,1000,'STAGED',1,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(16,'MB-0016','SRC-0016',10,1,2,2,10,10,1000,'STAGED',1,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(17,'MB-0017','SRC-0017',10,1,2,2,10,10,1000,'STAGED',1,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(18,'MB-0018','SRC-0018',10,1,2,2,10,10,1000,'STAGED',1,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(19,'MB-0019','SRC-0019',11,1,3,3,10,10,1000,'STAGED',1,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(20,'MB-0020','SRC-0020',11,1,3,3,10,10,1000,'STAGED',1,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(21,'MB-0021','SRC-0021',11,1,3,3,10,10,1000,'STAGED',1,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(22,'MB-0022','SRC-0022',11,1,3,3,10,10,1000,'STAGED',1,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(23,'MB-0023','SRC-0023',11,1,3,3,10,10,1000,'STAGED',1,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(24,'MB-0024','SRC-0024',11,1,3,3,10,10,1000,'STAGED',1,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(25,'MB-0025','SRC-0025',11,1,3,3,10,10,1000,'STAGED',1,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(26,'MB-0026','SRC-0026',11,1,3,3,10,10,1000,'STAGED',1,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(27,'MB-0027','SRC-0027',11,1,3,3,10,10,1000,'STAGED',1,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(28,'MB-0028','SRC-0028',11,1,3,3,10,10,1000,'STAGED',1,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(29,'MB-0029','SRC-0029',11,1,3,3,10,10,1000,'STAGED',1,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(30,'MB-0030','SRC-0030',11,1,3,3,10,10,1000,'STAGED',1,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(31,'MB-0031','SRC-0031',12,1,4,4,50,50,5000,'STAGED',1,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(32,'MB-0032','SRC-0032',12,1,4,4,50,50,5000,'STAGED',1,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(33,'MB-0033','SRC-0033',12,1,4,4,50,50,5000,'STAGED',1,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(34,'MB-0034','SRC-0034',13,1,4,4,50,50,5000,'STAGED',1,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(35,'MB-0035','SRC-0035',13,1,4,4,50,50,5000,'STAGED',1,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(36,'MN-0036','SRC-0036',14,1,5,5,10,10,1000,'STAGED',3,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(37,'MN-0037','SRC-0037',14,1,5,5,10,10,1000,'STAGED',3,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(38,'MN-0038','SRC-0038',14,1,5,5,10,10,1000,'STAGED',3,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(39,'MT-0039','SRC-0039',15,1,6,6,10,10,1000,'STAGED',2,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(40,'MT-0040','SRC-0040',15,1,6,6,10,10,1000,'STAGED',2,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(41,'MT-0041','SRC-0041',15,1,6,6,10,10,1000,'STAGED',2,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,staging_id,inspected_by,inspected_at,staged_at) VALUES(42,'MT-0042','SRC-0042',15,1,6,6,10,10,1000,'STAGED',2,2,now()-interval '2 hours',now()-interval '1 hour');
INSERT INTO thung_hang(id,code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,inspected_by,inspected_at) VALUES(43,'MN-HIS-001','SRC-HIS',16,1,8,8,10,10,1000,'DELIVERED',2,now()-interval '2 days');
INSERT INTO chuyen_xuat(id,code,vehicle_id,region,driver,planned_at,status,created_by,dispatched_at,dispatched_by,underload_reason) VALUES(1,'CX-MN-HIS',3,'MN','Lê Văn Bình',now()-interval '1 day','DONE',3,now()-interval '1 day',3,'Giao gấp theo yêu cầu nhà máy');
INSERT INTO diem_giao_chuyen(id,trip_id,location_id,sequence_no,address_snapshot,status) VALUES(1,1,4,1,'Lô D4, KCN Sóng Thần, Bình Dương','DONE');
INSERT INTO phan_bo_thung(id,box_id,stop_id,status,allocated_by,loaded_by,loaded_at) VALUES(1,43,1,'DELIVERED',3,2,now()-interval '1 day');
INSERT INTO ket_qua_giao_hang(id,stop_id,delivered_at,recorded_by,note) VALUES(1,1,now()-interval '12 hours',3,'Khách hàng nhận đủ');
INSERT INTO chi_tiet_giao(id,result_id,allocation_id,status,late,note) VALUES(1,1,1,'DELIVERED',false,'Nguyên thùng');
INSERT INTO luot_phuc_vu(gate_id,inbound_id,planned_start,planned_end,arrived_at,left_at,status,created_by) VALUES(1,1,now()-interval '4 hours',now()-interval '3 hours',now()-interval '4 hours',now()-interval '3 hours','DONE',3);
INSERT INTO luot_phuc_vu(gate_id,outbound_id,planned_start,planned_end,arrived_at,left_at,status,created_by) VALUES(4,1,now()-interval '25 hours',now()-interval '24 hours',now()-interval '25 hours',now()-interval '24 hours','DONE',3);
UPDATE don_hang SET status='DONE' WHERE id=8; UPDATE yeu_cau_giao SET status='DONE' WHERE id=8;
INSERT INTO don_hang(id,code,customer_id,created_by,note) VALUES
(9,'DH-009',3,3,'Kiểm nhận và cách ly demo');
INSERT INTO yeu_cau_giao(id,code,order_id,location_id,earliest,latest) VALUES(9,'YC-009',9,3,current_date,current_date+5);
INSERT INTO chi_tiet_yeu_cau(id,requirement_id,part_id,quantity) VALUES
(9,9,1,100);
INSERT INTO xac_nhan_cung_cap(id,supplier_id,detail_id,quantity,promised_date,status,created_by) VALUES(9,1,9,100,current_date,'ACCEPTED',3);
INSERT INTO chuyen_nhap(id,code,vehicle_id,planned_at,status,created_by) VALUES(20,'CN-020',5,now(),'RECEIVING',3);
INSERT INTO lo_nhap(id,code,inbound_id,supplier_id) VALUES
(20,'LO-020',20,1);
INSERT INTO luot_phuc_vu(gate_id,inbound_id,planned_start,planned_end,arrived_at,status,created_by) VALUES(3,20,now()-interval '30 minutes',now()+interval '30 minutes',now()-interval '30 minutes','IN_GATE',3);
SELECT setval(pg_get_serial_sequence('thung_hang','id'),(SELECT max(id) FROM thung_hang));
INSERT INTO thung_hang(code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,note) VALUES('IN-DEMO-1','DEMO-1',20,1,9,9,10,NULL,250,'EXPECTED','');
INSERT INTO thung_hang(code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,note) VALUES('IN-DEMO-2','DEMO-2',20,1,9,9,10,NULL,250,'EXPECTED','');
INSERT INTO thung_hang(code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,note) VALUES('IN-DEMO-3','DEMO-3',20,1,9,9,10,10,250,'ACCEPTED','');
INSERT INTO thung_hang(code,source_code,lot_id,part_id,detail_id,supply_id,expected_qty,quantity,gross_kg,status,note) VALUES('IN-DEMO-4','DEMO-4',20,1,9,9,10,10,250,'QUARANTINE','Nhãn không rõ');
SELECT setval(pg_get_serial_sequence('nguoi_dung','id'),greatest(coalesce((SELECT max(id) FROM nguoi_dung),1),1));
SELECT setval(pg_get_serial_sequence('khach_hang','id'),greatest(coalesce((SELECT max(id) FROM khach_hang),1),1));
SELECT setval(pg_get_serial_sequence('dia_diem_giao','id'),greatest(coalesce((SELECT max(id) FROM dia_diem_giao),1),1));
SELECT setval(pg_get_serial_sequence('don_hang','id'),greatest(coalesce((SELECT max(id) FROM don_hang),1),1));
SELECT setval(pg_get_serial_sequence('yeu_cau_giao','id'),greatest(coalesce((SELECT max(id) FROM yeu_cau_giao),1),1));
SELECT setval(pg_get_serial_sequence('linh_kien','id'),greatest(coalesce((SELECT max(id) FROM linh_kien),1),1));
SELECT setval(pg_get_serial_sequence('chi_tiet_yeu_cau','id'),greatest(coalesce((SELECT max(id) FROM chi_tiet_yeu_cau),1),1));
SELECT setval(pg_get_serial_sequence('nha_cung_cap','id'),greatest(coalesce((SELECT max(id) FROM nha_cung_cap),1),1));
SELECT setval(pg_get_serial_sequence('xac_nhan_cung_cap','id'),greatest(coalesce((SELECT max(id) FROM xac_nhan_cung_cap),1),1));
SELECT setval(pg_get_serial_sequence('xe','id'),greatest(coalesce((SELECT max(id) FROM xe),1),1));
SELECT setval(pg_get_serial_sequence('chuyen_nhap','id'),greatest(coalesce((SELECT max(id) FROM chuyen_nhap),1),1));
SELECT setval(pg_get_serial_sequence('lo_nhap','id'),greatest(coalesce((SELECT max(id) FROM lo_nhap),1),1));
SELECT setval(pg_get_serial_sequence('khu_cho','id'),greatest(coalesce((SELECT max(id) FROM khu_cho),1),1));
SELECT setval(pg_get_serial_sequence('thung_hang','id'),greatest(coalesce((SELECT max(id) FROM thung_hang),1),1));
SELECT setval(pg_get_serial_sequence('cong_kho','id'),greatest(coalesce((SELECT max(id) FROM cong_kho),1),1));
SELECT setval(pg_get_serial_sequence('chuyen_xuat','id'),greatest(coalesce((SELECT max(id) FROM chuyen_xuat),1),1));
SELECT setval(pg_get_serial_sequence('luot_phuc_vu','id'),greatest(coalesce((SELECT max(id) FROM luot_phuc_vu),1),1));
SELECT setval(pg_get_serial_sequence('diem_giao_chuyen','id'),greatest(coalesce((SELECT max(id) FROM diem_giao_chuyen),1),1));
SELECT setval(pg_get_serial_sequence('phan_bo_thung','id'),greatest(coalesce((SELECT max(id) FROM phan_bo_thung),1),1));
SELECT setval(pg_get_serial_sequence('ket_qua_giao_hang','id'),greatest(coalesce((SELECT max(id) FROM ket_qua_giao_hang),1),1));
SELECT setval(pg_get_serial_sequence('chi_tiet_giao','id'),greatest(coalesce((SELECT max(id) FROM chi_tiet_giao),1),1));