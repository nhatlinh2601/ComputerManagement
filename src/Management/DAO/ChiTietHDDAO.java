package Management.DAO;

import Management.DTO.ChiTietHD;
import Management.DTO.NhanVien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChiTietHDDAO {

    private PreparedStatement pstmt;

    public List<ChiTietHD> getChiTietHD(String maHDlike) {
        List<ChiTietHD> list = new ArrayList<ChiTietHD>();
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "select * from ct_hoadon where mahd like ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maHDlike);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String maHD = rs.getString("mahd");
                String maSP = rs.getString("masp");
                String dvt = rs.getString("donvitinh");
                float donGia = rs.getFloat("dongia");
                int soLuong = rs.getInt("soluong");

                ChiTietHD ct = new ChiTietHD(maHD, maSP, dvt, donGia, soLuong);
                list.add(ct);

            }

        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (conn != null) try {
                conn.close();
            } catch (SQLException ignore) {
            }
        }
        return list;
    }

    public String getTenSPbyMa(String maSP) {
        String tenSP = null;
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "select tensp from linhkienmt where masp=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maSP);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                tenSP = rs.getString("tensp");
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) try {
                conn.close();
            } catch (SQLException ignore) {
            }
        }
        return tenSP;
    }

    public String getMaSPbyTen(String tenSP) {
        String maSP = null;
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "select masp from linhkienmt where tensp=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tenSP);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                maSP = rs.getString("masp");
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) try {
                conn.close();
            } catch (SQLException ignore) {
            }
        }
        return maSP;
    }

    public boolean insertCTHD(ChiTietHD chiTietHD) throws SQLException {
        Connection conn = null;
        try {

            conn = DatabaseHelper.getConnection();
            String sql = "insert into ct_hoadon(mahd,masp,dongia,soluong,donvitinh) values(?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, chiTietHD.getMaHD());
            pstmt.setString(2, chiTietHD.getMaSP());
            pstmt.setFloat(3, chiTietHD.getDonGia());
            pstmt.setFloat(4, chiTietHD.getSoLuong());
            pstmt.setString(5, chiTietHD.getDonViTinh());


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return pstmt.executeUpdate() > 0;
    }

    public int soLuongSPDaBan(String maSP) {
        int soLuongSP = 0;
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            PreparedStatement stmt = null;
            String sql = "select SUM(SoLuong) from ct_hoadon where masp = ? group by masp";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, maSP);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                soLuongSP = rs.getInt(1);
            }

        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (conn != null) try {
                conn.close();
            } catch (SQLException ignore) {
            }
        }
        return soLuongSP;
    }

    public int soLuongNhap(String maSP) {
        int soLuongNhap = 0;
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            PreparedStatement stmt = null;
            String sql = "select SoLuongNhap from linhkienmt where masp = ?";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, maSP);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                soLuongNhap = rs.getInt("soluongnhap");
            }


        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (conn != null) try {
                conn.close();
            } catch (SQLException ignore) {
            }
        }
        return soLuongNhap;
    }


}

