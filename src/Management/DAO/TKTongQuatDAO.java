package Management.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TKTongQuatDAO {

    public static PreparedStatement pstmt;
    public static ResultSet rs;

    public static Float tongTienBanDuoc(int thang, int nam) {
        Float tongTienBanDuoc = (float) 0;
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "SELECT sum(tongtien) from hoadon WHERE month(ngaylap)=? AND year(ngaylap)=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, thang);
            pstmt.setInt(2, nam);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                tongTienBanDuoc = rs.getFloat(1);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) try {
                conn.close();
            } catch (SQLException ignore) {
            }
        }
        return tongTienBanDuoc;

    }

    public static Float tongTienBanDuoc(int nam) {
        Float tongTienBanDuoc = (float) 0;
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "SELECT sum(tongtien) from hoadon WHERE year(ngaylap)=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, nam);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                tongTienBanDuoc = rs.getFloat(1);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) try {
                conn.close();
            } catch (SQLException ignore) {
            }
        }
        return tongTienBanDuoc;

    }

    public static Float tongTienNhap(int thang, int nam) {
        Float tongTienNhap = (float) 0;
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "SELECT sum(linhkienmt.gianhap) from hoadon JOIN ct_hoadon ON hoadon.mahd=ct_hoadon.mahd " +
                    "JOIN linhkienmt ON ct_hoadon.masp=linhkienmt.masp WHERE month(ngaylap)=? AND year(ngaylap)=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, thang);
            pstmt.setInt(2, nam);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                tongTienNhap = rs.getFloat(1);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) try {
                conn.close();
            } catch (SQLException ignore) {
            }
        }
        return tongTienNhap;

    }

    public static Float tongTienNhap(int nam) {
        Float tongTienNhap = (float) 0;
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "SELECT sum(linhkienmt.gianhap) from hoadon JOIN ct_hoadon ON hoadon.mahd=ct_hoadon.mahd " +
                    "JOIN linhkienmt ON ct_hoadon.masp=linhkienmt.masp WHERE year(ngaylap)=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, nam);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                tongTienNhap = rs.getFloat(1);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) try {
                conn.close();
            } catch (SQLException ignore) {
            }
        }
        return tongTienNhap;

    }
}
