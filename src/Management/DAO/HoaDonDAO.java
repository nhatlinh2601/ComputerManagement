package Management.DAO;

import Management.DTO.*;

import java.sql.*;
import java.util.ArrayList;

public class HoaDonDAO {

    private PreparedStatement pstmt;

    public HoaDon layThongTinByMaHD(String maHD) {
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "select * from hoadon where mahd=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maHD);
            ResultSet rs = pstmt.executeQuery();
            HoaDon hoaDon = new HoaDon();
            if (rs.next()) {
                hoaDon.setMa(rs.getString("mahd"));
                hoaDon.setNhanVien(rs.getString("manv"));
                hoaDon.setTongTien(rs.getFloat("tongtien"));
                hoaDon.setNgayLap(rs.getDate("ngaylap"));
            }

            return hoaDon;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<HoaDon> getMaHD() throws Exception {
        ArrayList<HoaDon> hoaDonList = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "select mahd from hoadon";
            ResultSet rs = pstmt.executeQuery(sql);
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMa(rs.getString("mahd"));
                hoaDonList.add(hoaDon);
            }

            return hoaDonList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<SanPhamLK> loadCbxTenSP() {

        ArrayList<SanPhamLK> sanPhamLKList = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "select tensp from linhkienmt";
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                SanPhamLK sanPhamLK = new SanPhamLK();
                sanPhamLK.setTenSP(rs.getString("tensp"));
                sanPhamLKList.add(sanPhamLK);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) try {
                conn.close();
            } catch (SQLException ignore) {
            }
        }
        return sanPhamLKList;
    }

    public boolean addHoaDon(HoaDon hoaDon) throws SQLException {
        Double tongtien = 1230.5;
        String sql;
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            sql = "insert into hoadon(mahd,manv,ngaylap,tongtien) values(?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, hoaDon.getMa());
            pstmt.setString(2, hoaDon.getNhanVien());
            pstmt.setDate(3, (Date) hoaDon.getNgayLap());
            pstmt.setDouble(4, hoaDon.getTongTien());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return pstmt.executeUpdate() > 0;
    }


    public boolean addKhachHang(KhachHang khachHang) throws SQLException {
        String sql;
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            sql = "insert into khachhang(mahd,tenkh,diachi,sodt,ngaysinh,gioitinh) values(?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, khachHang.getMaHD());
            pstmt.setString(2, khachHang.getTenKH());
            pstmt.setString(3, khachHang.getDiaChi());
            pstmt.setString(4, khachHang.getSoDT());
            pstmt.setDate(5, khachHang.getNgaySinh());
            pstmt.setString(6, khachHang.getGioiTinh());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return pstmt.executeUpdate() > 0;


    }

    public ArrayList<NhanVien> loadCbxTenNV() {

        ArrayList<NhanVien> nhanVienList = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "select tennv from nhanvien";
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setTenNV(rs.getString("tennv"));
                nhanVienList.add(nhanVien);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return nhanVienList;
    }

    public String getMaNVbyTen(String tenNV) {
        String maNV = null;
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "select manv from nhanvien where tennv=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tenNV);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                maNV = rs.getString("manv");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return maNV;
    }

    public String getTenNVbyMa(String maNV) {
        String tenNV = null;
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "select tennv from nhanvien where manv=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maNV);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                tenNV = rs.getString("tennv");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) try {
                conn.close();
            } catch (SQLException ignore) {
            }
        }
        return tenNV;
    }

    public String getTenKHbyMaHD(String maHD) {
        String tenKH = null;
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "select tenkh from khachhang where mahd=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maHD);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                tenKH = rs.getString("tenkh");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return tenKH;
    }


}

