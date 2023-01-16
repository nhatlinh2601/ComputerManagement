package Management.DAO;

import Management.DTO.NhanVien;
import Management.DTO.SanPhamLK;

import java.sql.*;
import java.util.ArrayList;

public class SanPhamLKDAO {

    private PreparedStatement pstmt;
    private ArrayList<SanPhamLK> sanPhamLKList;

    public ArrayList<SanPhamLK> getMaSP() throws Exception {
        ArrayList<SanPhamLK> sanPhamLKList=new ArrayList<>();
        try {
            Connection conn=DatabaseHelper.getConnection();
            String sql="select masp from linhkienmt";
            pstmt=conn.prepareStatement(sql);
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()){
                SanPhamLK sanPhamLK=new SanPhamLK();
                sanPhamLK.setMaSP(rs.getString("masp"));
                sanPhamLKList.add(sanPhamLK);
            }
            return sanPhamLKList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<SanPhamLK> getTenSP() throws Exception {
        ArrayList<SanPhamLK> sanPhamLKList=new ArrayList<>();
        try {
            Connection conn=DatabaseHelper.getConnection();
            String sql="select tensp from linhkienmt";
            pstmt=conn.prepareStatement(sql);
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()){
                SanPhamLK sanPhamLK=new SanPhamLK();
                sanPhamLK.setTenSP(rs.getString("tensp"));
                sanPhamLKList.add(sanPhamLK);
            }
            return sanPhamLKList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SanPhamLK layThongTinSanPham(String maSP){
        try{
            Connection conn=DatabaseHelper.getConnection();
            String sql="select * from linhkienmt where masp=?";
            pstmt=conn.prepareStatement(sql);

            pstmt.setString(1,maSP);
            ResultSet rs=pstmt.executeQuery();
            SanPhamLK sanPhamLK=new SanPhamLK();
            if (rs.next()){
                sanPhamLK.setMaSP(maSP);
                sanPhamLK.setTenSP(rs.getString("tensp"));
                sanPhamLK.setNsx(rs.getString("nsx"));
                sanPhamLK.setMoTa(rs.getString("mota"));
                sanPhamLK.setNgaySX(rs.getDate("ngaysx"));
                sanPhamLK.setSoLuong(rs.getInt("soluong"));
                sanPhamLK.setDonViTinh(rs.getString("donvitinh"));
                sanPhamLK.setTrangThai(rs.getString("trangthai"));
                sanPhamLK.setSoDK(rs.getString("sodk"));
                sanPhamLK.setPrice(rs.getFloat("gia"));
            }

            return sanPhamLK;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean themSanPhamLK(SanPhamLK sanPhamLK) throws SQLException {
        try {

            Connection conn=DatabaseHelper.getConnection();
            String sql="insert into linhkienmt(masp,tensp,mota,gia,nsx,soluong,trangthai,donvitinh,ngaysx,sodk) " +
                    "values(?,?,?,?,?,?,?,?,?,?)";
            pstmt= conn.prepareStatement(sql);
            pstmt.setString(1, sanPhamLK.getMaSP());
            pstmt.setString(2, sanPhamLK.getTenSP());
            pstmt.setString(3, sanPhamLK.getMoTa());
            pstmt.setString(4, String.valueOf(sanPhamLK.getPrice()));
            pstmt.setString(5, sanPhamLK.getNsx());
            pstmt.setString(6, String.valueOf(sanPhamLK.getSoLuong()));
            pstmt.setString(7, sanPhamLK.getTrangThai());
            pstmt.setString(8, sanPhamLK.getDonViTinh());
            pstmt.setDate(9, (Date) sanPhamLK.getNgaySX());
            pstmt.setString(10, sanPhamLK.getSoDK());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return pstmt.executeUpdate()>0;
    }
    public boolean updateSanPhamLK(SanPhamLK sanPhamLK) throws SQLException {

        try {

            Connection conn=DatabaseHelper.getConnection();
            String sql="update linhkienmt set tensp=?,mota=?,gia=?,nsx=?,soluong=?,trangthai=?," +
                    "donvitinh=?,ngaysx=?,sodk=? where masp=?";
            pstmt=conn.prepareStatement(sql);

            pstmt.setString(10, sanPhamLK.getMaSP());
            pstmt.setString(1, sanPhamLK.getTenSP());
            pstmt.setString(2, sanPhamLK.getMoTa());
            pstmt.setString(3, String.valueOf(sanPhamLK.getPrice()));
            pstmt.setString(4, sanPhamLK.getNsx());
            pstmt.setString(5, String.valueOf(sanPhamLK.getSoLuong()));
            pstmt.setString(6, sanPhamLK.getTrangThai());
            pstmt.setString(7, sanPhamLK.getDonViTinh());
            pstmt.setDate(8, (Date) sanPhamLK.getNgaySX());
            pstmt.setString(9, sanPhamLK.getSoDK());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pstmt.executeUpdate()>0;
    }

    public boolean xoaSanPhamLK(String maSP) throws SQLException {
        try {
            Connection conn=DatabaseHelper.getConnection();
            String sql="delete from linhkienmt where masp=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,maSP);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pstmt.executeUpdate()>0;
    }

    public ArrayList<SanPhamLK> findByMaSP(String maSP){
        try {
            Connection conn=DatabaseHelper.getConnection();
            String sql="select * from linhkienmt where masp like ?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,"%" + maSP + "%");
            ResultSet rs=pstmt.executeQuery();
            sanPhamLKList=new ArrayList<>();
            while (rs.next()){
                SanPhamLK sanPhamLK=new SanPhamLK();
                sanPhamLK.setMaSP(rs.getString("masp"));
                sanPhamLK.setTenSP(rs.getString("tensp"));
                sanPhamLK.setNsx(rs.getString("nsx"));
                sanPhamLK.setMoTa(rs.getString("mota"));
                sanPhamLK.setNgaySX(rs.getDate("ngaysx"));
                sanPhamLK.setSoLuong(rs.getInt("soluong"));
                sanPhamLK.setDonViTinh(rs.getString("donvitinh"));
                sanPhamLK.setTrangThai(rs.getString("trangthai"));
                sanPhamLK.setSoDK(rs.getString("sodk"));
                sanPhamLK.setPrice(rs.getFloat("gia"));
                sanPhamLKList.add(sanPhamLK);
            }
            return sanPhamLKList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<SanPhamLK> findByTenSP(String tenSP){
        try {
            Connection conn=DatabaseHelper.getConnection();
            String sql="select * from linhkienmt where tensp like ?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,"%" + tenSP + "%");
            ResultSet rs=pstmt.executeQuery();
            sanPhamLKList=new ArrayList<>();
            while (rs.next()){
                SanPhamLK sanPhamLK=new SanPhamLK();
                sanPhamLK.setMaSP(rs.getString("masp"));
                sanPhamLK.setTenSP(rs.getString("tensp"));
                sanPhamLK.setNsx(rs.getString("nsx"));
                sanPhamLK.setMoTa(rs.getString("mota"));
                sanPhamLK.setNgaySX(rs.getDate("ngaysx"));
                sanPhamLK.setSoLuong(rs.getInt("soluong"));
                sanPhamLK.setDonViTinh(rs.getString("donvitinh"));
                sanPhamLK.setTrangThai(rs.getString("trangthai"));
                sanPhamLK.setSoDK(rs.getString("sodk"));
                sanPhamLK.setPrice(rs.getFloat("gia"));
                sanPhamLKList.add(sanPhamLK);
            }
            return sanPhamLKList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<SanPhamLK> findByNSX(String nsx){
        try {
            Connection conn=DatabaseHelper.getConnection();
            String sql="select * from linhkienmt where nsx like ?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,"%" + nsx + "%");
            ResultSet rs=pstmt.executeQuery();
            sanPhamLKList=new ArrayList<>();
            while (rs.next()){
                SanPhamLK sanPhamLK=new SanPhamLK();
                sanPhamLK.setMaSP(rs.getString("masp"));
                sanPhamLK.setTenSP(rs.getString("tensp"));
                sanPhamLK.setNsx(rs.getString("nsx"));
                sanPhamLK.setMoTa(rs.getString("mota"));
                sanPhamLK.setNgaySX(rs.getDate("ngaysx"));
                sanPhamLK.setSoLuong(rs.getInt("soluong"));
                sanPhamLK.setDonViTinh(rs.getString("donvitinh"));
                sanPhamLK.setTrangThai(rs.getString("trangthai"));
                sanPhamLK.setSoDK(rs.getString("sodk"));
                sanPhamLK.setPrice(rs.getFloat("gia"));
                sanPhamLKList.add(sanPhamLK);
            }
            return sanPhamLKList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
