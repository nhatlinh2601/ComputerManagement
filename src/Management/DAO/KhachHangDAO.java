package Management.DAO;

import Management.DTO.HoaDon;
import Management.DTO.KhachHang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class KhachHangDAO {

    private ArrayList<KhachHang> customerList;
    private PreparedStatement pstmt;


    public KhachHang layThongTinKhachHang(String maKH){
        try{
            Connection conn=DatabaseHelper.getConnection();
            String sql="select * from khachhang where makh=?";
            pstmt=conn.prepareStatement(sql);

            pstmt.setString(1,maKH);
            ResultSet rs=pstmt.executeQuery();
            KhachHang khachHang=new KhachHang();
            if (rs.next()){

                khachHang.setMaKH(maKH);
                khachHang.setTenKH(rs.getString("tenkh"));
                khachHang.setDiaChi(rs.getString("diachi"));
                khachHang.setSoDT(rs.getString("sodt"));
                khachHang.setNgaySinh(rs.getDate("ngaysinh"));
                khachHang.setGioiTinh(rs.getString("gioitinh"));
            }

            return khachHang;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateKhachHang(KhachHang khachHang) throws SQLException {

        try {

            Connection conn=DatabaseHelper.getConnection();
            String sql="update khachHang set tenkh=?,diachi=?,sodt=?,ngaysinh=?,gioitinh=? where makh=?";
            pstmt=conn.prepareStatement(sql);

            pstmt.setString(6,khachHang.getMaKH());
            pstmt.setString(1,khachHang.getTenKH());
            pstmt.setString(2,khachHang.getDiaChi());
            pstmt.setString(3,khachHang.getSoDT());
            pstmt.setDate(4,khachHang.getNgaySinh());
            pstmt.setString(5,khachHang.getGioiTinh());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pstmt.executeUpdate()>0;
    }
    public boolean xoaKhachHang(String maKH) throws SQLException {
        try {
            Connection conn=DatabaseHelper.getConnection();
            String sql="delete from khachHang where makh=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,maKH);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pstmt.executeUpdate()>0;
    }

    public ArrayList<KhachHang> findByMaKH(String maKH){
        try {
            Connection conn=DatabaseHelper.getConnection();
            String sql="select * from khachhang where makh like ?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,"%" + maKH + "%");
            ResultSet rs=pstmt.executeQuery();
            customerList=new ArrayList<>();
            while (rs.next()){
                KhachHang khachHang=new KhachHang();
                khachHang.setMaKH(rs.getString("makh"));
                khachHang.setSoDT(rs.getString("sodt"));
                khachHang.setTenKH(rs.getString("tenkh"));
                khachHang.setNgaySinh(rs.getDate("ngaysinh"));
                khachHang.setGioiTinh(rs.getString("gioitinh"));
                khachHang.setDiaChi(rs.getString("diachi"));
                customerList.add(khachHang);
            }
            return customerList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<KhachHang> findByTenKH(String tenKHlike){
        try {
            Connection conn=DatabaseHelper.getConnection();
            String sql="select * from khachhang where tenkh like ?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,"%" + tenKHlike + "%");
            ResultSet rs=pstmt.executeQuery();
            customerList=new ArrayList<>();
            while (rs.next()){
                KhachHang khachHang=new KhachHang();
                khachHang.setMaKH(rs.getString("makh"));
                khachHang.setSoDT(rs.getString("sodt"));
                khachHang.setTenKH(rs.getString("tenkh"));
                khachHang.setNgaySinh(rs.getDate("ngaysinh"));
                khachHang.setGioiTinh(rs.getString("gioitinh"));
                khachHang.setDiaChi(rs.getString("diachi"));

                customerList.add(khachHang);
            }
            return customerList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<KhachHang> findBySDT(String soDT){
        try {
            Connection conn=DatabaseHelper.getConnection();
            String sql="select * from khachhang where sodt like ?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,"%" + soDT + "%");
            ResultSet rs=pstmt.executeQuery();
            customerList=new ArrayList<>();
            while (rs.next()){
                KhachHang khachHang=new KhachHang();
                khachHang.setMaKH(rs.getString("makh"));
                khachHang.setSoDT(rs.getString("sodt"));
                khachHang.setTenKH(rs.getString("tenkh"));
                khachHang.setNgaySinh(rs.getDate("ngaysinh"));
                khachHang.setGioiTinh(rs.getString("gioitinh"));
                khachHang.setDiaChi(rs.getString("diachi"));
                customerList.add(khachHang);
            }
            return customerList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getMaHDbyMaKH(String maKH){
        String maHD = null;
        try {
            Connection conn=DatabaseHelper.getConnection();
            String sql="select mahd from khachhang where makh=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(maKH));
            ResultSet rs= pstmt.executeQuery();
            if (rs.next()){
                maHD=rs.getString("mahd");
            }
            return maHD;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public String getTenKHbyMaHD(String maHD){
        String tenKH = null;
        try {
            Connection conn=DatabaseHelper.getConnection();
            String sql="select tenkh from khachhang where mahd=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maHD);
            ResultSet rs= pstmt.executeQuery();
            if (rs.next()){
                tenKH=rs.getString("tenkh");
            }
            return tenKH;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
