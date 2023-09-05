package Management.DAO;

import Management.DTO.HoaDon;
import Management.DTO.NhanVien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class XemHoaDonDAO {
    private PreparedStatement pstmt;
    private HoaDonDAO hoaDonDAO = new HoaDonDAO();


    public ArrayList<HoaDon> findByMaHD(String maHD) {
        List<HoaDon> hoaDonList;
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "select * from hoadon where mahd like ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + maHD + "%");
            ResultSet rs = pstmt.executeQuery();
            hoaDonList = new ArrayList<>();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMa(rs.getString("mahd"));
                hoaDon.setNgayLap(rs.getDate("ngaylap"));
                hoaDon.setTongTien(rs.getDouble("tongtien"));
                hoaDon.setNhanVien(hoaDonDAO.getTenNVbyMa(rs.getString("manv")));
                hoaDon.setKhachHang(hoaDonDAO.getTenKHbyMaHD(rs.getString("mahd")));
                hoaDonList.add(hoaDon);
            }

            return (ArrayList<HoaDon>) hoaDonList;
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
    }

    public ArrayList<HoaDon> findByTenNV(String tenNV) {
        List<HoaDon> hoaDonList;
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "select * from hoadon where manv like ?";
            pstmt = conn.prepareStatement(sql);
            String maNV = hoaDonDAO.getMaNVbyTen(tenNV);
            System.out.println(maNV);
            pstmt.setString(1, "%" + maNV + "%");
            ResultSet rs = pstmt.executeQuery();
            hoaDonList = new ArrayList<>();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMa(rs.getString("mahd"));
                hoaDon.setNgayLap(rs.getDate("ngaylap"));
                hoaDon.setTongTien(rs.getDouble("tongtien"));
                hoaDon.setNhanVien(hoaDonDAO.getTenNVbyMa(rs.getString("manv")));
                hoaDon.setKhachHang(hoaDonDAO.getTenKHbyMaHD(rs.getString("mahd")));
                hoaDonList.add(hoaDon);
            }

            return (ArrayList<HoaDon>) hoaDonList;
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
    }

    public ArrayList<HoaDon> findByNgay(String ngay) {
        List<HoaDon> hoaDonList;
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "select * from hoadon where ngaylap like ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + ngay + "%");
            ResultSet rs = pstmt.executeQuery();
            hoaDonList = new ArrayList<>();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMa(rs.getString("mahd"));
                hoaDon.setNgayLap(rs.getDate("ngaylap"));
                hoaDon.setTongTien(rs.getDouble("tongtien"));
                hoaDon.setNhanVien(hoaDonDAO.getTenNVbyMa(rs.getString("manv")));
                hoaDon.setKhachHang(hoaDonDAO.getTenKHbyMaHD(rs.getString("mahd")));
                hoaDonList.add(hoaDon);
            }

            return (ArrayList<HoaDon>) hoaDonList;
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
    }
}
