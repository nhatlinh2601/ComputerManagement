-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th1 21, 2023 lúc 04:38 PM
-- Phiên bản máy phục vụ: 10.4.27-MariaDB
-- Phiên bản PHP: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `computers_sales_management`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ct_hoadon`
--

CREATE TABLE `ct_hoadon` (
  `mahd` varchar(10) NOT NULL,
  `masp` varchar(10) NOT NULL,
  `dongia` float NOT NULL,
  `soluong` int(11) NOT NULL,
  `donvitinh` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `ct_hoadon`
--

INSERT INTO `ct_hoadon` (`mahd`, `masp`, `dongia`, `soluong`, `donvitinh`) VALUES
('QHK158', 'MOS5931', 450000, 1, 'Cái'),
('QHK158', 'PWAN071', 1029000, 2, 'Cái'),
('QHK563', '1151-c2', 3399000, 2, 'Bộ'),
('QHK563', '2010-v21', 2490000, 1, 'Cái');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `mahd` varchar(10) NOT NULL,
  `manv` varchar(10) NOT NULL,
  `ngaylap` varchar(50) NOT NULL,
  `tongtien` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `hoadon`
--

INSERT INTO `hoadon` (`mahd`, `manv`, `ngaylap`, `tongtien`) VALUES
('QHK158', 'P03', 'Wed Jan 18 15:23:48 ICT 2023', 2508000),
('QHK563', 'P02', 'Wed Jan 18 15:23:48 ICT 2023', 9288000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `makh` int(11) NOT NULL,
  `mahd` varchar(10) NOT NULL,
  `tenkh` varchar(20) NOT NULL,
  `diachi` varchar(50) DEFAULT NULL,
  `sodt` varchar(20) NOT NULL,
  `ngaysinh` date DEFAULT NULL,
  `gioitinh` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`makh`, `mahd`, `tenkh`, `diachi`, `sodt`, `ngaysinh`, `gioitinh`) VALUES
(39, 'QHK563', 'Hoài Thương', 'Hưng Nguyên', '0369224698', '2004-02-16', 'Nữ'),
(40, 'QHK158', 'John', 'UK', '0698452314', '2000-06-23', 'Nam');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `linhkienmt`
--

CREATE TABLE `linhkienmt` (
  `masp` varchar(10) NOT NULL,
  `tensp` varchar(30) NOT NULL,
  `mota` varchar(50) DEFAULT NULL,
  `gia` float NOT NULL,
  `nsx` varchar(50) NOT NULL,
  `soluong` int(11) NOT NULL,
  `trangthai` varchar(30) DEFAULT NULL,
  `donvitinh` varchar(20) NOT NULL,
  `ngaysx` date NOT NULL,
  `sodk` char(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `linhkienmt`
--

INSERT INTO `linhkienmt` (`masp`, `tensp`, `mota`, `gia`, `nsx`, `soluong`, `trangthai`, `donvitinh`, `ngaysx`, `sodk`) VALUES
('1052-c1', 'Ổ cứng SSD Kingston A400', 'SA400S37/240G', 655, 'Kingston', 5, 'New', 'Ổ', '2021-01-12', 'USko-005'),
('1151-c1', 'CPU AMD Ryzen 5 3600', 'Socket AMD AM4', 2459, 'AMD', 8, 'Like New', 'Bộ', '2020-01-16', 'VN-0002'),
('1151-c2', 'CPU Intel Core i5-9400F', 'Socket Intel LGA 1200', 3399, 'Intel', 10, 'New', 'Bộ', '2010-01-16', 'VN-0001'),
('1302967', 'Thùng máy/ Case Sama S1', 'USB/ AUDIO/ NÚT POWER/ RESET', 1509, 'SAMA', 3, 'New', 'Cái', '2022-01-20', 'PIO514'),
('2009-kc3', 'Card màn hình NVIDIA Quadro', 'P620 2GB GDDR5', 295, 'Leadtek', 2, 'New', 'Cái', '2022-01-20', 'OIN552'),
('2010-v21', 'Card màn hình ASUS PH-GT1030', '2GB GDDR5, 64-bit, DVI+HDMI', 249, 'Asus', 5, 'New', 'Cái', '2018-01-12', 'PH-GT1030'),
('CSLA046', 'Vỏ Case LIAN-LI Odyssey', 'Full Tower/Màu Đen', 800, 'Lian Li', 1, 'New', 'Cái', '2020-01-23', 'DYK566'),
('DWAS003', 'Ổ Quang DVD Rewrite Asus', 'SDRW-08D2S-U Ext USB', 1350, 'Asus', 2, 'New', 'Cái', '2018-01-31', 'OPF962'),
('MBAS670', 'Mainboard ASUS ROG MAXIMUS', 'Intel Z690, Socket 1700, ATX, 4 khe RAM DDR5', 999, 'FORMULA', 7, 'New', 'Cái', '2018-04-13', 'UK009'),
('MBAS694', 'Mainboard Asus TUF GAMING', 'B660M-PLUS WIFI D4', 7999, 'Asus', 2, 'New', 'Cái', '2022-01-20', 'UK007'),
('MOS5931', 'Chuột Gaming Logitech G402', 'Chuột Gaming', 450, 'Logitech', 4, 'New', 'Cái', '2016-08-25', 'MOU003'),
('PWAI001', 'Nguồn máy tính AIGO VK550', 'VK550', 159, 'AIGO', 5, 'New', 'Cái', '2019-07-17', 'LHP369'),
('PWAN071', 'Nguồn ANTEC CUPRUM STRIKE', '80 Plus Bronze/Màu Đen', 1029, 'ANTEC', 12, 'New', 'Cái', '2020-04-24', 'LPH362'),
('RAKT322', 'Ram Desktop Fury Beast', 'KF552C40BBAK2-32', 590, 'KINGSTON', 6, 'New', 'Cái', '2022-01-20', 'HSK569');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `manv` varchar(10) NOT NULL,
  `tennv` varchar(30) NOT NULL,
  `cmnd` varchar(12) NOT NULL,
  `sodt` varchar(20) NOT NULL,
  `gioitinh` varchar(20) NOT NULL,
  `diachi` varchar(50) DEFAULT NULL,
  `ngaysinh` date NOT NULL DEFAULT current_timestamp(),
  `calamviec` varchar(30) NOT NULL,
  `username` char(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`manv`, `tennv`, `cmnd`, `sodt`, `gioitinh`, `diachi`, `ngaysinh`, `calamviec`, `username`) VALUES
('P01', 'Nhật Linh', '040304005668', '0346242297', 'Nữ', 'Nghệ An', '2004-01-26', 'Ca 4 (Fulltime)', 'nhanvien'),
('P02', 'Phạm Quốc Hùng', '040304005992', '0368453621', 'Nam', 'Hà Nội', '2001-08-09', 'Ca 3 (17h00-21h00)', 'nhanvien'),
('P03', 'Nguyễn Mai Anh', '040506005992', '0392453643', 'Nữ', 'HCM', '1999-04-14', 'Ca 1 (7h30-11h30)', 'nhanvien');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `username` char(20) NOT NULL,
  `password` char(20) NOT NULL,
  `loaiTK` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`username`, `password`, `loaiTK`) VALUES
('admin', '123456', 'manager'),
('nhanvien', '123456', 'employee');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `ct_hoadon`
--
ALTER TABLE `ct_hoadon`
  ADD PRIMARY KEY (`mahd`,`masp`),
  ADD KEY `masp` (`masp`);

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`mahd`),
  ADD KEY `manv` (`manv`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`makh`,`mahd`),
  ADD UNIQUE KEY `makh` (`makh`),
  ADD KEY `mahd` (`mahd`);

--
-- Chỉ mục cho bảng `linhkienmt`
--
ALTER TABLE `linhkienmt`
  ADD PRIMARY KEY (`masp`),
  ADD UNIQUE KEY `tensp` (`tensp`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`manv`),
  ADD KEY `username` (`username`);

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `makh` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `ct_hoadon`
--
ALTER TABLE `ct_hoadon`
  ADD CONSTRAINT `ct_hoadon_ibfk_1` FOREIGN KEY (`masp`) REFERENCES `linhkienmt` (`masp`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ct_hoadon_ibfk_2` FOREIGN KEY (`mahd`) REFERENCES `hoadon` (`mahd`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `hoadon_ibfk_2` FOREIGN KEY (`manv`) REFERENCES `nhanvien` (`manv`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD CONSTRAINT `khachhang_ibfk_1` FOREIGN KEY (`mahd`) REFERENCES `hoadon` (`mahd`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD CONSTRAINT `nhanvien_ibfk_1` FOREIGN KEY (`username`) REFERENCES `taikhoan` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
