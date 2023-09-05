-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th2 25, 2023 lúc 04:49 PM
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
('1W6CI5', 'MBGI675', 5999, 1, 'Cái'),
('1W6CI5', 'MELO150', 2419, 1, 'Cái'),
('1W6CI5', 'PWRM014', 1719, 1, 'Cái'),
('1W6CI5', 'VGGI546', 9099, 1, 'Cái'),
('2J211V', 'KBDA049', 599, 1, 'Cái'),
('2J211V', 'MBAS600', 2699, 1, 'Cái'),
('2J211V', 'VGGI546', 9099, 1, 'Cái'),
('3A485Y', 'VGLE053', 25999, 1, 'Cái'),
('3J9J4K', 'VGLE053', 25999, 1, 'Cái'),
('44MC84', 'PWFP075', 2990, 2, 'Cái'),
('44MC84', 'RALX002', 689, 2, 'Cái'),
('4SBPN3', 'VGIN130', 49999, 1, 'Cái'),
('4X15QO', 'RAAV178', 1199, 2, 'Cái'),
('78DLSI', 'VGLE053', 25999, 1, 'Cái'),
('7TSOVF', 'CPUI480', 3099, 1, 'Cái'),
('7TSOVF', 'PWLI002', 2999, 1, 'Cái'),
('7YARSP', 'VGLE053', 25999, 1, 'Cái'),
('8JSH5C', 'CPUI462', 8999, 1, 'Cái'),
('8JSH5C', 'KBED209', 1149, 1, 'Cái'),
('ABFHB0', 'CPUI480', 3099, 1, 'Cái'),
('ABFHB0', 'VGGI546', 9099, 1, 'Cái'),
('AJIVY3', 'HDWD411', 6199, 2, 'Cái'),
('AJIVY3', 'MOU202', 2390, 1, 'Cái'),
('B8UBMI', 'CPUI462', 8999, 1, 'Cái'),
('B8UBMI', 'MOU202', 2390, 1, 'Cái'),
('D17VBV', 'MBMS455', 2699, 1, 'Cái'),
('D17VBV', 'MEDA046', 279, 1, 'Cái'),
('D17VBV', 'VGLE053', 25999, 1, 'Cái'),
('DWX8CZ', 'CPUI296', 4099, 1, 'Cái'),
('DWX8CZ', 'RAAD279', 1499, 1, 'Cái'),
('E0ESWQ', 'CPUA257', 3099, 1, 'Cái'),
('EWYBJH', 'MEDA046', 279, 1, 'Cái'),
('EWYBJH', 'PWCM150', 1799, 1, 'Cái'),
('F6N5QZ', 'KBED209', 1149, 1, 'Cái'),
('F6N5QZ', 'RACO359', 1549, 4, 'Cái'),
('GTRTEF', 'RALX002', 689, 1, 'Cái'),
('HDM389', 'MBSM002', 10999, 1, 'Cái'),
('HDM389', 'VGIN130', 49999, 1, 'Cái'),
('HHLL2L', 'HDLX011', 1299, 2, 'Cái'),
('HHLL2L', 'MBGI675', 5999, 2, 'Cái'),
('HHLL2L', 'PWCM150', 1799, 2, 'Cái'),
('HHLL2L', 'RAAV178', 1199, 2, 'Cái'),
('HJLCLV', 'MBGI675', 5999, 1, 'Cái'),
('HJLCLV', 'MOU202', 2390, 1, 'Cái'),
('IPRXX9', 'VGLE053', 25999, 1, 'Cái'),
('JI1MAL', 'MBAR354', 3599, 1, 'Cái'),
('JI1MAL', 'VGCL039', 8599, 1, 'Cái'),
('JNZT64', 'MBSM002', 10999, 1, 'Cái'),
('JNZT64', 'MERZ116', 1149, 1, 'Cái'),
('N31ZJQ', 'MBGI675', 5999, 1, 'Cái'),
('N31ZJQ', 'PWFP075', 2990, 2, 'Cái'),
('N31ZJQ', 'RAAV178', 1199, 1, 'Cái'),
('N31ZJQ', 'VGGI546', 9099, 1, 'Cái'),
('P83HXE', 'MELO150', 2419, 1, 'Cái'),
('P83HXE', 'PWFP075', 2990, 1, 'Cái'),
('PQEJEO', 'VGLE053', 25999, 1, 'Cái'),
('QGTZG4', 'VGGI546', 9099, 1, 'Cái'),
('QGTZG4', 'VGLE053', 25999, 1, 'Cái'),
('QZC46R', 'MBAR354', 3599, 1, 'Cái'),
('QZC46R', 'VGAS561', 6599, 1, 'Cái'),
('R9GG4Y', 'MBAS600', 2699, 1, 'Cái'),
('R9GG4Y', 'MOU202', 2390, 1, 'Cái'),
('R9GG4Y', 'RAKT332', 8699, 2, 'Cái'),
('RYV8IY', 'MBAR354', 3599, 1, 'Cái'),
('RYV8IY', 'RAAD279', 1499, 1, 'Cái'),
('S0BIIH', 'RAKT332', 8699, 2, 'Cái'),
('S2H55G', 'MOU202', 2390, 1, 'Cái'),
('SKO218', 'MBAR354', 3599, 1, 'Cái'),
('SKO218', 'MBSM002', 10999, 1, 'Cái'),
('SKO218', 'RAKT332', 8699, 1, 'Cái'),
('UGGDBX', 'MBMS455', 2699, 1, 'Cái'),
('UGGDBX', 'VGAS561', 6599, 1, 'Cái'),
('UGGDBX', 'VGCL039', 8599, 1, 'Cái'),
('VX9X3S', 'KBED209', 1149, 1, 'Cái'),
('VX9X3S', 'MBMS455', 2699, 1, 'Cái'),
('WWPC5Z', 'VGIN130', 49999, 1, 'Cái'),
('XD5BQG', 'HDSE326', 2499, 2, 'Cái'),
('XD5BQG', 'PWFP075', 2990, 1, 'Cái'),
('XD5BQG', 'PWLI002', 2999, 1, 'Cái'),
('XEO9P2', 'PWLI002', 2999, 1, 'Cái'),
('Y3X0YV', 'VGLE053', 25999, 1, 'Cái'),
('Y8DZRT', 'RAAV178', 1199, 1, 'Cái'),
('YS213W', 'MBSM002', 10999, 1, 'Cái'),
('YS213W', 'RAAV178', 1199, 1, 'Cái'),
('YS213W', 'RALX002', 689, 1, 'Cái'),
('YWR6WL', 'CPUI393', 6999, 4, 'Cái'),
('YWR6WL', 'KBED209', 1149, 1, 'Cái'),
('YWR6WL', 'VGAS561', 6599, 2, 'Cái');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `mahd` varchar(10) NOT NULL,
  `manv` varchar(10) NOT NULL,
  `ngaylap` date NOT NULL,
  `tongtien` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `hoadon`
--

INSERT INTO `hoadon` (`mahd`, `manv`, `ngaylap`, `tongtien`) VALUES
('1W6CI5', 'P03', '2023-10-27', 19236),
('2J211V', 'P02', '2023-05-15', 12397),
('3A485Y', 'P01', '2023-08-22', 25999),
('3J9J4K', 'P02', '2023-04-24', 25999),
('44MC84', 'P01', '2023-03-24', 7358),
('4SBPN3', 'P01', '2023-09-19', 49999),
('4X15QO', 'P06', '2023-01-14', 2398),
('78DLSI', 'P04', '2023-01-10', 25999),
('7TSOVF', 'P01', '2023-05-15', 6098),
('7YARSP', 'P01', '2023-06-22', 25999),
('8JSH5C', 'P03', '2023-06-12', 10148),
('ABFHB0', 'P03', '2023-04-11', 12198),
('AJIVY3', 'P02', '2023-04-11', 14788),
('B8UBMI', 'P06', '2023-01-17', 11389),
('D17VBV', 'P03', '2023-06-09', 28977),
('DWX8CZ', 'P02', '2023-09-15', 5598),
('E0ESWQ', 'P04', '2023-11-15', 6198),
('EWYBJH', 'P02', '2023-09-15', 2078),
('F6N5QZ', 'P06', '2023-02-20', 7345),
('GTRTEF', 'P02', '2023-02-14', 689),
('HDM389', 'P03', '2023-06-12', 60998),
('HHLL2L', 'P01', '2023-12-12', 20592),
('HJLCLV', 'P01', '2023-02-14', 8389),
('IPRXX9', 'P02', '2023-04-24', 25999),
('JI1MAL', 'P05', '2023-10-27', 12198),
('JNZT64', 'P01', '2023-07-20', 12148),
('N31ZJQ', 'P01', '2023-03-24', 15098),
('P83HXE', 'P05', '2023-09-15', 5409),
('PQEJEO', 'P01', '2023-07-18', 25999),
('QGTZG4', 'P05', '2023-09-15', 35098),
('QZC46R', 'P05', '2023-08-24', 10198),
('R9GG4Y', 'P06', '2023-02-16', 22487),
('RYV8IY', 'P01', '2023-02-20', 5098),
('S0BIIH', 'P04', '2023-02-14', 17398),
('S2H55G', 'P01', '2023-02-14', 2390),
('SKO218', 'P04', '2023-02-14', 23297),
('UGGDBX', 'P02', '2023-09-15', 17897),
('VX9X3S', 'P06', '2023-10-18', 3848),
('WWPC5Z', 'P01', '2023-10-25', 49999),
('XD5BQG', 'P04', '2023-02-17', 10987),
('XEO9P2', 'P04', '2023-10-10', 2999),
('Y3X0YV', 'P04', '2023-05-15', 25999),
('Y8DZRT', 'P01', '2023-02-17', 1199),
('YS213W', 'P01', '2023-03-24', 12887),
('YWR6WL', 'P01', '2023-03-16', 42343);

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
(64, 'HJLCLV', 'Mai Anh', 'Hưng Long', '0123456789', '2004-02-16', 'Nữ'),
(65, 'SKO218', 'Lê Linh Đan', 'Hà Nội', '0346242297', '1999-09-15', 'Nữ'),
(66, 'S0BIIH', 'Hồng Hạnh', 'Huế', '0258741369', '1998-11-26', 'Nữ'),
(67, 'GTRTEF', 'Nam', 'HCM', '0123654789', '2002-07-25', 'Nam'),
(68, 'S2H55G', 'John', 'UK', '0123456789', '2003-02-14', 'Nam'),
(69, 'R9GG4Y', 'Hải Huy', 'Phan Rang', '0789654123', '1995-02-15', 'Nam'),
(70, 'N31ZJQ', 'Hải Đăng', 'Nam Định', '0258741369', '2002-02-16', 'Nam'),
(71, '44MC84', 'Hồng Anh', 'Huế', '0145872369', '2002-12-17', 'Nữ'),
(72, 'YS213W', 'Quỳnh Nga', 'HCM', '0231456987', '2000-12-20', 'Nữ'),
(73, 'YWR6WL', 'LinDa', 'US', '0159741368', '1998-10-15', 'Nữ'),
(74, 'XD5BQG', 'Quang', 'DN', '0123456789', '2004-02-12', 'Nam'),
(75, 'Y8DZRT', 'Huy', 'HN', '0147852369', '2002-02-17', 'Nam'),
(76, 'F6N5QZ', 'Mai Lan', 'Hưng Lam', '0258741369', '2003-02-11', 'Nữ'),
(77, 'RYV8IY', 'Quỳnh Nga', 'Huế', '0901960958', '2004-02-12', 'Nữ'),
(78, 'B8UBMI', 'Tuấn Tú', 'HN', '0258963147', '2003-02-17', 'Nữ'),
(79, '4X15QO', 'Quỳnh Trang', 'HCM', '0147852369', '2003-02-17', 'Nữ'),
(80, 'AJIVY3', 'Khánh', 'An Giang', '0258961473', '2003-02-27', 'Nam'),
(81, 'ABFHB0', 'Xuân Thương', 'QN', '0789654123', '2004-02-27', 'Nữ'),
(82, '2J211V', 'Quỳnh', 'Huế', '0159742863', '1999-02-20', 'Nữ'),
(83, '7TSOVF', 'Minh', 'HN', '0269857413', '1998-02-20', 'Nữ'),
(84, '8JSH5C', 'Lan', '456HN', '0258741369', '1998-02-09', 'Nữ'),
(85, 'HDM389', 'Vũ', '02 HN', '0159741863', '1997-02-09', 'Nam'),
(86, 'JNZT64', 'Khánh', 'LA', '0258741369', '1996-02-09', 'Nam'),
(87, 'QZC46R', 'An', 'Thanh Hóa', '0874125639', '1995-02-09', 'Nam'),
(88, 'UGGDBX', 'Nhật Linh', 'Nghệ An', '0154789632', '2004-02-09', 'Nữ'),
(89, 'EWYBJH', 'Xuân Thương', 'HCM', '0159874236', '2004-02-11', 'Nữ'),
(90, 'P83HXE', 'Dũng', 'HN', '0147852369', '2003-02-11', 'Nữ'),
(91, 'DWX8CZ', 'Nga', 'GN', '0147852369', '2002-02-11', 'Nữ'),
(92, 'VX9X3S', 'Dũng', 'HCM', '0147852369', '2003-02-20', 'Nam'),
(93, 'JI1MAL', 'Nga', 'huế', '0258743694', '2002-02-20', 'Nữ'),
(94, '1W6CI5', 'Trang', 'HN', '0874125369', '2001-02-20', 'Nữ'),
(95, 'XEO9P2', 'Thương', 'HN', '0159687423', '2001-02-20', 'Nữ'),
(96, 'E0ESWQ', 'Đăng', 'HCM', '0159874236', '2001-02-20', 'Nam'),
(97, 'D17VBV', 'My', 'HN', '0159632478', '2002-02-20', 'Nữ'),
(98, 'QGTZG4', 'Mai', 'Quảng Bình', '0158742369', '2001-02-20', 'Nữ'),
(99, 'HHLL2L', 'Mai', 'HN', '0346242297', '2003-02-20', 'Nữ'),
(100, '78DLSI', 'Tuấn', 'HN', '0147852369', '2003-02-20', 'Nam'),
(101, 'IPRXX9', 'Mai Oanh', 'Bạc Liêu', '0258741369', '2003-02-17', 'Nữ'),
(102, '3J9J4K', 'Vinh', 'HN', '0258741369', '2003-02-17', 'Nam'),
(103, 'Y3X0YV', 'Quốc', 'Vinh', '0346242297', '2003-02-17', 'Nam'),
(105, '7YARSP', 'Viết Chú', 'HN', '0258741364', '1998-02-20', 'Nam'),
(106, 'PQEJEO', 'Nguyễn Lê', 'ĐN', '0258741364', '1998-02-20', 'Nam'),
(107, '3A485Y', 'Hà Ánh', 'Long An', '0258741364', '1998-02-20', 'Nữ'),
(108, '4SBPN3', 'Nguyễn Hà Ánh', 'An giang', '0258741364', '1998-02-20', 'Nữ'),
(109, 'WWPC5Z', 'Nguyễn Ánh', 'HN', '0258741364', '1998-02-20', 'Nữ');

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
  `soluongnhap` int(11) NOT NULL,
  `trangthai` varchar(30) DEFAULT NULL,
  `donvitinh` varchar(20) NOT NULL,
  `ngaysx` date NOT NULL,
  `sodk` char(20) NOT NULL,
  `gianhap` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `linhkienmt`
--

INSERT INTO `linhkienmt` (`masp`, `tensp`, `mota`, `gia`, `nsx`, `soluongnhap`, `trangthai`, `donvitinh`, `ngaysx`, `sodk`, `gianhap`) VALUES
('CPUA257', 'CPU AMD Ryzen 5 5500', '3.6 GHz Upto 4.2GHz / 19MB / 6 Cores, 12 Threads', 3099, 'AMD Ryzen™', 10, 'New', 'Cái', '2019-02-11', 'FCLGA12', 2500),
('CPUI296', 'CPU Intel Core i5-9400', '2.9GHz turbo up to 4.1GHz, Socket Intel', 4099, 'Intel', 10, 'New', 'Cái', '2023-02-14', 'HUJ255', 3500),
('CPUI393', 'CPU Intel Core i7-10700', 'Sở hữu 8 nhân 16 luồng', 6999, 'Intel', 5, 'New', 'Cái', '2023-02-14', 'KOL112', 6200),
('CPUI462', 'CPU Intel Xeon W-1350P', '4.0GHz turbo up to 5.1GHz', 8999, 'Intel', 5, 'New', 'Cái', '2022-06-14', 'OKF263', 8000),
('CPUI480', 'CPU Intel Core i3-13100F', 'Socket Intel LGA 1700/Raptor Lake', 3099, 'Intel', 3, 'New', 'Cái', '2022-11-24', 'OFD741', 2500),
('HDLX011', 'Ổ cứng SSD Lexar NM620', 'PCIe 3.0x4 (Đoc 3300MB/s - Ghi 2400MB/s)', 1299, 'LEXAR', 4, 'New', 'Cái', '2022-12-03', 'DFC221', 900),
('HDSE326', 'Ổ cứng HDD Seagate SkyHawk', '4TB 3.5 inch 5400RPM SATA3 256MB', 2499, 'Seagate', 10, 'New', 'Cái', '2021-02-26', 'PLF115', 1900),
('HDWD411', 'Ổ cứng HDD WD Purple 8TB', '3.5 inch, 5640RPM, SATA, 128Mb Cache', 6199, 'Seagate', 4, 'New', 'Cái', '2023-02-14', 'JHF126', 5325),
('HDWD449', 'Ổ cứng HDD WD 8TB Red Plus', '5640RPM, SATA, 128MB Cache (WD80EFZZ)', 5499, 'Western', 6, 'New', 'Cái', '2022-02-17', 'NJK526', 4950),
('KBDA049', 'Bàn Phím cơ Dareu EK810', '(USB/Pink Led/Brown switch)', 599, 'Dareu', 15, 'New', 'Cái', '2023-02-14', 'FCLGA12', 425),
('KBED209', 'Bàn phím cơ game Edra EK387FL', 'RGB Polar night Red sw (USBC/PBT)', 1149, 'Edra', 10, 'New', 'Cái', '2023-02-14', 'FBGBF', 850),
('MBAR354', 'Mainboard ASROCK B760M Pro', 'Chipset: Intel B760 Socket: Intel', 3599, 'Intel', 5, 'New', 'Cái', '2023-02-14', 'DSCD', 2995),
('MBAS600', 'Mainboard ASUS PRIME B550M-A', 'AMD B550, Socket AM4,m- ATX, 4 khe RAM DRR4', 2699, 'Asus', 8, 'New', 'Cái', '2023-02-14', 'FDBF', 2100),
('MBGI675', 'Mainboard Gigabyte Z790', 'Hỗ trợ CPU Intel thế hệ thứ 12 và 13', 5999, 'Gigabyte', 7, 'New', 'Cái', '2023-02-14', 'JGMU', 5055),
('MBMS455', 'Mainboard MSI PRO B660M-A DDR4', 'Intel B660, Socket 1700, ATX, 4 khe RAM DDR4', 2699, 'MSI', 5, 'New', 'Cái', '2023-02-14', 'MJGMG', 2125),
('MBSM002', 'Mainboard Supermicro MBD-X10', '0 cổng SATA 3 (6Gbps) với Intel C612 controller', 10999, 'Supermicro', 10, 'New', 'Cái', '2023-02-14', 'MJMI,', 8555),
('MEDA046', 'Chuột không dây Dareu', 'Dareu', 279, 'Akko', 15, 'New', 'Cái', '2023-02-14', 'SCSV', 185),
('MELO150', 'Chuột game Không dây Logitech', 'Logitech G502 Lightspeed Wireless RGB', 2419, 'Logitech', 5, 'New', 'Cái', '2023-02-14', 'FCHMJ', 2000),
('MERZ116', 'Chuột Razer DeathAdder V2', 'HyperSpeed-Wireless Ergonomic_RZ01-04130100-R3A', 1149, 'Razer', 10, 'New', 'Cái', '2023-07-14', 'DSB', 859),
('MOU202', 'Bàn phím AKKO ACR Pro 68', 'AKKO CS Crystal', 2390, 'Akko', 5, 'New', 'Cái', '2023-02-14', 'MHJMJ', 1857),
('PWCM150', 'Nguồn máy tính Cooler Master', 'V2 230V 750 750W Plus Bronze', 1799, 'COOLERMASTER', 9, 'New', 'Cái', '2023-02-14', 'FCLGA12', 1435),
('PWFP075', 'Nguồn FSP Power Supply', 'model SDA2-850 850W ( 80 Plus Gold)', 2990, 'FSP', 10, 'New', 'Cái', '2023-02-20', 'MJMJUK', 2528),
('PWLI002', 'Nguồn máy tính Lian Li SP850', '80 Plus Gold/Full Modular/Màu Đen/kết nối PCIe 5.0', 2999, 'PWLI002', 3, 'New', 'Cái', '2023-02-14', 'HMGMJJ', 2455),
('PWRM014', 'Nguồn Raidmax RX-735AP-R', 'Raidmax RX-735AP-R 80 Plus Bronze', 1719, 'Raidmax', 4, 'New', 'Cái', '2023-02-14', 'DVFDB', 1425),
('RAAD279', 'Ram Desktop Adata XPG', '16GB (2x8GB) DDR4 3200Mhz', 1499, 'ADATA', 8, 'New', 'Cái', '2023-02-14', 'MGJMF', 1000),
('RAAV178', 'Ram Desktop AVEXIR 1SOE', '16GB/3200 (1x16GB) DDR4 3200MHz', 1199, 'AVEXIR', 10, 'New', 'Cái', '2022-02-25', 'BGFHN', 850),
('RACO359', 'Ram Desktop Corsair Vengeance', '16GB (2x8GB) DDR4 3200MHz', 1549, 'CORSAIR', 12, 'New', 'Cái', '2023-02-14', 'CGB122', 1325),
('RAKT332', 'Ram Desktop Kingston Fury', '64GB (2x32GB) - DDR5 5200MHz', 8699, 'KINGSTON', 5, 'New', 'Cái', '2022-02-11', 'NHFX45', 7505),
('RALX002', 'Ram Desktop Lexar', '8GB (1x8GB) DDR4 2666Mhz', 689, 'LEXAR', 4, 'New', 'Cái', '2023-02-14', 'BXF452', 475),
('VGAS561', 'Card màn hình Asus DUAL', 'Nhân GPU: RTX 2060 Số nhân Cuda: 1920', 6599, 'ASUS', 7, 'New', 'Cái', '2023-02-14', 'NGGH14', 5958),
('VGCL039', 'Card màn hình Colorful RTX', 'RTX 3060 NB DUO 12G V2 L-V', 8599, 'COLORFUL', 11, 'New', 'Cái', '2022-05-19', 'NHFX41', 7000),
('VGGI546', 'Card màn hình Gigabyte RTX', 'RTX 3060 GAMING OC 12GD-V2', 9099, 'GIGA', 10, 'New', 'Cái', '2022-11-10', 'FAS452', 8252),
('VGIN130', 'Card màn hình INNO3D RTX', 'RTX 4090 SUPRIM LIQUID X 24G', 49999, 'INNO3D', 9, 'New', 'Cái', '2022-05-18', 'BGFH14', 40000),
('VGLE053', 'Card màn hình NVIDIA RTX', 'NVIDIA Ampere GPU architecture\n6,144 NVIDIA®', 25999, 'Nvidia', 20, 'New', 'Cái', '2021-07-16', 'BHK236', 21555);

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
('P01', 'Nhật Linh Trần', '040304005668', '0346242297', 'Nữ', 'Nghệ An', '2004-01-26', 'Ca 4 (Fulltime)', 'admin'),
('P02', 'Phạm Quốc Hùng', '040304005992', '0368453621', 'Nam', 'Hà Nội', '2001-08-09', 'Ca 3 (17h00-21h00)', 'nhanvien'),
('P03', 'Nguyễn Mai Anh', '040506005992', '0392453643', 'Nữ', 'HCM', '1999-04-14', 'Ca 1 (7h30-11h30)', 'nhanvien'),
('P04', 'Hoàng Nhật Đăng', '024563987415', '0356242298', 'Nam', 'Vinh', '2002-09-19', 'Ca 4 (Fulltime)', 'admin'),
('P05', 'Võ Ngọc Phương Anh', '024563987218', '0356212345', 'Nữ', 'Nghệ An', '1998-07-14', 'Ca 2 (13h00-17h00)', 'nhanvien'),
('P06', 'Lê Nguyễn Trúc Quỳnh', '014785236932', '0258741369', 'Nữ', 'Vinh', '1999-04-14', 'Ca 3 (17h00-21h00)', 'nhanvien');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `username` char(20) NOT NULL,
  `password` char(20) NOT NULL,
  `loaiTK` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`username`, `password`, `loaiTK`) VALUES
('admin', '123456', 'manager'),
('hihi', '123', 'employee'),
('nhanvien', '123456', 'employee'),
('nhanvien123', '123', 'employee'),
('nhatdang', '123456', 'manager'),
('nhatlinh', '260104', 'manager'),
('nhatnhat', '123456', 'manager');

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
  MODIFY `makh` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=110;

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
