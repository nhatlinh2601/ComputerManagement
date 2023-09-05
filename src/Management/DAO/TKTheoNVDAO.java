package Management.DAO;

import Management.DTO.NhanVien;
import Management.DTO.TKTheoNV;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class TKTheoNVDAO {

    private PreparedStatement pstmt;

    public ArrayList<NhanVien> loadCbxTenNV() {
        ArrayList<NhanVien> nhanViens = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "select tennv from nhanvien";
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setTenNV(rs.getString("tennv"));
                nhanViens.add(nhanVien);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) try {
                conn.close();
            } catch (SQLException ignore) {
            }
        }
        return nhanViens;
    }

    public ArrayList<TKTheoNV> thongKeNVtheoMaNV_ngayLap(String maNV, Date ngayLap) {
        ArrayList<TKTheoNV> tkTheoNVS = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "SELECT hoadon.mahd,nhanvien.tennv,nhanvien.calamviec,hoadon.ngaylap,hoadon.tongtien," +
                    "sum(ct_hoadon.soluong) FROM hoadon JOIN nhanvien ON hoadon.manv=nhanvien.manv JOIN ct_hoadon " +
                    "ON hoadon.mahd=ct_hoadon.mahd WHERE nhanvien.manv=? and ngaylap=? GROUP BY mahd ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maNV);
            pstmt.setDate(2, (java.sql.Date) ngayLap);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                TKTheoNV tkTheoNV = new TKTheoNV();
                tkTheoNV.setMaHD(rs.getString("mahd"));
                tkTheoNV.setNgayLap(String.valueOf(rs.getDate("ngaylap")));
                tkTheoNV.setTongTien(rs.getFloat("tongtien"));
                tkTheoNV.setCaLamViec(rs.getString("calamviec"));
                tkTheoNV.setSoLuong(rs.getInt("sum(ct_hoadon.soluong)"));
                tkTheoNV.setTenNV(rs.getString("tennv"));
                tkTheoNVS.add(tkTheoNV);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) try {
                conn.close();
            } catch (SQLException ignore) {
            }
        }
        return tkTheoNVS;

    }

    public ArrayList<TKTheoNV> thongKeNVtheongayLap(Date ngayLap) {
        ArrayList<TKTheoNV> tkTheoNVS = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "SELECT hoadon.mahd,nhanvien.tennv,nhanvien.calamviec,hoadon.ngaylap,hoadon.tongtien," +
                    "sum(ct_hoadon.soluong) FROM hoadon JOIN nhanvien ON hoadon.manv=nhanvien.manv JOIN ct_hoadon " +
                    "ON hoadon.mahd=ct_hoadon.mahd where ngaylap=? GROUP BY mahd ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setDate(1, (java.sql.Date) ngayLap);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                TKTheoNV tkTheoNV = new TKTheoNV();
                tkTheoNV.setMaHD(rs.getString("mahd"));
                tkTheoNV.setNgayLap(String.valueOf(rs.getDate("ngaylap")));
                tkTheoNV.setTongTien(rs.getFloat("tongtien"));
                tkTheoNV.setCaLamViec(rs.getString("calamviec"));
                tkTheoNV.setSoLuong(rs.getInt("sum(ct_hoadon.soluong)"));
                tkTheoNV.setTenNV(rs.getString("tennv"));
                tkTheoNVS.add(tkTheoNV);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) try {
                conn.close();
            } catch (SQLException ignore) {
            }
        }
        return tkTheoNVS;

    }


}
