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
        try {
            Connection con = DatabaseHelper.getConnection();
            String sql = "select * from ct_hoadon where mahd like ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
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
        }
        return list;
    }

    public String getTenSPbyMa(String maSP){
        String tenSP=null;
        try {
            Connection conn=DatabaseHelper.getConnection();
            String sql="select tensp from linhkienmt where masp=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,maSP);
            ResultSet rs= pstmt.executeQuery();
            if (rs.next()){
                tenSP=rs.getString("tensp");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return tenSP;
    }

    public boolean insertCTHD(ChiTietHD chiTietHD) throws SQLException {
        try {

            Connection conn = DatabaseHelper.getConnection();
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


}

