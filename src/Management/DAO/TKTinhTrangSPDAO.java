package Management.DAO;

import Management.DTO.TKTinhTrangSP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class TKTinhTrangSPDAO {
   private PreparedStatement pstmt;
   private ResultSet rs;

    public ArrayList<TKTinhTrangSP> slgSPSaBanTheoNgay(Date date){
        ArrayList<TKTinhTrangSP> slgSPdaBans=new ArrayList<>();
        Connection conn=null;
        try {
             conn=DatabaseHelper.getConnection();
            String sql="SELECT ct_hoadon.masp,linhkienmt.tensp,sum(ct_hoadon.soluong),linhkienmt.nsx,linhkienmt.gia " +
                    "from ct_hoadon JOIN linhkienmt ON ct_hoadon.masp=linhkienmt.masp JOIN hoadon ON hoadon.mahd=ct_hoadon.mahd " +
                    "WHERE hoadon.ngaylap= ? GROUP BY ct_hoadon.masp";
            pstmt= conn.prepareStatement(sql);
            pstmt.setDate(1, (java.sql.Date) date);
            rs= pstmt.executeQuery();
            while (rs.next()){
                TKTinhTrangSP tkTinhTrangSP=new TKTinhTrangSP();
                tkTinhTrangSP.setMaSP(rs.getString(1));
                tkTinhTrangSP.setTenSP(rs.getString(2));
                tkTinhTrangSP.setSoLuong(rs.getInt(3));
                tkTinhTrangSP.setNsx(rs.getString(4));
                tkTinhTrangSP.setDonGia(rs.getFloat(5));
                slgSPdaBans.add(tkTinhTrangSP);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
        }
        return slgSPdaBans;
    }


    public ArrayList<TKTinhTrangSP> slgSPSaBanTheoThang(int thang){
        ArrayList<TKTinhTrangSP> slgSPdaBans=new ArrayList<>();
        Connection conn=null;
        try {
             conn=DatabaseHelper.getConnection();
            String sql="SELECT ct_hoadon.masp,linhkienmt.tensp,sum(ct_hoadon.soluong),linhkienmt.nsx,linhkienmt.gia " +
                    "from ct_hoadon JOIN linhkienmt ON ct_hoadon.masp=linhkienmt.masp JOIN hoadon ON hoadon.mahd=ct_hoadon.mahd " +
                    "WHERE month(hoadon.ngaylap)= ? GROUP BY ct_hoadon.masp";
            pstmt= conn.prepareStatement(sql);
            pstmt.setInt(1, thang);
            rs= pstmt.executeQuery();
            while (rs.next()){
                TKTinhTrangSP tkTinhTrangSP=new TKTinhTrangSP();
                tkTinhTrangSP.setMaSP(rs.getString(1));
                tkTinhTrangSP.setTenSP(rs.getString(2));
                tkTinhTrangSP.setSoLuong(rs.getInt(3));
                tkTinhTrangSP.setNsx(rs.getString(4));
                tkTinhTrangSP.setDonGia(rs.getFloat(5));
                slgSPdaBans.add(tkTinhTrangSP);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
        }
        return slgSPdaBans;
    }
    public ArrayList<TKTinhTrangSP> spBanChay() {

        Connection conn = null;
        ArrayList<TKTinhTrangSP> spBanChay=new ArrayList<>();

        try {
            conn = DatabaseHelper.getConnection();
            String sql = "SELECT ct_hoadon.masp,linhkienmt.tensp,sum(ct_hoadon.soluong),linhkienmt.nsx,linhkienmt.gia " +
                    "FROM `ct_hoadon` JOIN linhkienmt ON ct_hoadon.masp=linhkienmt.masp" +
                    " GROUP BY masp ORDER BY sum(ct_hoadon.soluong) DESC";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                TKTinhTrangSP tkTinhTrangSP = null;
                tkTinhTrangSP = new TKTinhTrangSP();
                tkTinhTrangSP.setMaSP(rs.getString(1));
                tkTinhTrangSP.setTenSP(rs.getString(2));
                tkTinhTrangSP.setSoLuong(rs.getInt(3));
                tkTinhTrangSP.setNsx(rs.getString(4));
                tkTinhTrangSP.setDonGia(rs.getFloat(5));
                spBanChay.add(tkTinhTrangSP);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) try {
                conn.close();
            } catch (SQLException ignore) {
            }
        }
        return spBanChay;
    }

    public ArrayList<TKTinhTrangSP> slgSPSaBanTheoNam(int nam){
        ArrayList<TKTinhTrangSP> slgSPdaBans=new ArrayList<>();
        Connection conn=null;
        try {
             conn=DatabaseHelper.getConnection();
            String sql="SELECT ct_hoadon.masp,linhkienmt.tensp,sum(ct_hoadon.soluong),linhkienmt.nsx,linhkienmt.gia " +
                    "from ct_hoadon JOIN linhkienmt ON ct_hoadon.masp=linhkienmt.masp JOIN hoadon ON hoadon.mahd=ct_hoadon.mahd " +
                    "WHERE year(hoadon.ngaylap)= ? GROUP BY ct_hoadon.masp";
            pstmt= conn.prepareStatement(sql);
            pstmt.setInt(1, nam);
            rs= pstmt.executeQuery();
            while (rs.next()){
                TKTinhTrangSP tkTinhTrangSP=new TKTinhTrangSP();
                tkTinhTrangSP.setMaSP(rs.getString(1));
                tkTinhTrangSP.setTenSP(rs.getString(2));
                tkTinhTrangSP.setSoLuong(rs.getInt(3));
                tkTinhTrangSP.setNsx(rs.getString(4));
                tkTinhTrangSP.setDonGia(rs.getFloat(5));
                slgSPdaBans.add(tkTinhTrangSP);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
        }
        return slgSPdaBans;
    }


    public  int soLgDaBan(String maSP){
        int soLuong=0;
        Connection conn=null;
        try {
             conn=DatabaseHelper.getConnection();
            String sql="SELECT sum(ct_hoadon.soluong) FROM linhkienmt JOIN ct_hoadon ON linhkienmt.masp=ct_hoadon.masp" +
                    " WHERE linhkienmt.masp=? GROUP BY ct_hoadon.masp";
            pstmt= conn.prepareStatement(sql);
            pstmt.setString(1, maSP);
            rs= pstmt.executeQuery();
            if (rs.next()){
                soLuong=rs.getInt(1);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
        }
        return soLuong;
    }

    public  int soLgNhap(String maSP){
        int soLuong=0;
        Connection conn=null;
        try {
             conn=DatabaseHelper.getConnection();
            String sql="SELECT linhkienmt.soluongnhap from linhkienmt where linhkienmt.masp=?";
            pstmt= conn.prepareStatement(sql);
            pstmt.setString(1, maSP);
            rs= pstmt.executeQuery();
            if (rs.next()){
                soLuong=rs.getInt(1);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
        }
        return soLuong;
    }

    public ArrayList<TKTinhTrangSP> slgSPConTrongKho(){
        ArrayList<TKTinhTrangSP> slgSPdaBans=new ArrayList<>();
        Connection conn=null;
        try {
             conn=DatabaseHelper.getConnection();
            String sql="SELECT linhkienmt.masp,linhkienmt.tensp," +
                    "IFNULL(linhkienmt.soluongnhap-sum(ct_hoadon.soluong),linhkienmt.soluongnhap)," +
                    "linhkienmt.gia,linhkienmt.nsx " +
                    "FROM linhkienmt LEFT JOIN ct_hoadon ON linhkienmt.masp=ct_hoadon.masp GROUP BY linhkienmt.masp;";
            pstmt= conn.prepareStatement(sql);
            rs= pstmt.executeQuery();
            while (rs.next()){
                TKTinhTrangSP tkTinhTrangSP=new TKTinhTrangSP();
                tkTinhTrangSP.setMaSP(rs.getString(1));
                tkTinhTrangSP.setTenSP(rs.getString(2));
                tkTinhTrangSP.setSoLuong(rs.getInt(3));
                tkTinhTrangSP.setDonGia(rs.getFloat(4));
                tkTinhTrangSP.setNsx(rs.getString(5));
                slgSPdaBans.add(tkTinhTrangSP);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
        }
        return slgSPdaBans;
    }
}
