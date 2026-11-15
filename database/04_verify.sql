-- Read-only consistency checks; each anomaly query should return zero rows.
SELECT table_name FROM information_schema.tables WHERE table_schema='public' AND table_type='BASE TABLE' ORDER BY table_name;
SELECT box_id, count(*) FROM phan_bo_thung WHERE status IN ('ALLOCATED','LOADED','SHIPPED','RETURN_PENDING') GROUP BY box_id HAVING count(*)>1;
SELECT gate_id,count(*) FROM luot_phuc_vu WHERE status='IN_GATE' GROUP BY gate_id HAVING count(*)>1;
SELECT t.code,sum(b.gross_kg) kg,x.capacity_kg FROM chuyen_xuat t JOIN xe x ON x.id=t.vehicle_id JOIN diem_giao_chuyen s ON s.trip_id=t.id JOIN phan_bo_thung a ON a.stop_id=s.id JOIN thung_hang b ON b.id=a.box_id WHERE a.status<>'CANCELLED' GROUP BY t.id,t.code,x.capacity_kg HAVING sum(b.gross_kg)>least(x.capacity_kg,18000);
SELECT k.code,k.capacity,count(b.id) used FROM khu_cho k JOIN thung_hang b ON b.staging_id=k.id GROUP BY k.id,k.code,k.capacity HAVING count(b.id)>k.capacity;
SELECT t.code,l.code FROM chuyen_xuat t JOIN diem_giao_chuyen s ON s.trip_id=t.id JOIN dia_diem_giao l ON l.id=s.location_id WHERE t.region<>l.region;
