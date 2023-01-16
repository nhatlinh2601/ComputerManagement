package Management.DAO;

import Management.DTO.KhachHang;
import Management.DTO.NhanVien;
import Management.DTO.TaiKhoan;

import java.sql.*;
import java.util.ArrayList;

public class NhanVienDAO {
    private PreparedStatement pstmt;
    private ArrayList<NhanVien> nhanVienList;

   public ArrayList<NhanVien> getMaNV() throws Exception {
       ArrayList<NhanVien> nhanVienList=new ArrayList<>();
       try {
           Connection conn=DatabaseHelper.getConnection();
           String sql="select manv from nhanvien";
           ResultSet rs= pstmt.executeQuery(sql);
           while (rs.next()){
               NhanVien nhanVien=new NhanVien();
               nhanVien.setMaNV(rs.getString("manv"));
               nhanVienList.add(nhanVien);
           }
           return nhanVienList;
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
   }

    public boolean themNhanVien(NhanVien nhanVien) throws SQLException {
        try {

            Connection conn=DatabaseHelper.getConnection();
            String sql="insert into nhanvien(manv,tennv,cmnd,sodt,gioitinh,diachi,ngaysinh,calamviec,username) values(?,?,?,?,?,?,?,?,?)";
            pstmt= conn.prepareStatement(sql);
            pstmt.setString(1, nhanVien.getMaNV());
            pstmt.setString(2, nhanVien.getTenNV());
            pstmt.setString(3, nhanVien.getCMND());
            pstmt.setString(4, nhanVien.getSoDT());
            pstmt.setString(5, nhanVien.getGioiTinh());
            pstmt.setString(6, nhanVien.getDiaChi());
            pstmt.setDate(7, (Date) nhanVien.getNgaySinh());
            pstmt.setString(8, nhanVien.getCaLamViec());
            pstmt.setString(9,nhanVien.getUsername());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return pstmt.executeUpdate()>0;
    }

    public NhanVien layThongTinNhanVien(String maNV){
        try{
            Connection conn=DatabaseHelper.getConnection();
            String sql="select * from nhanvien where manv=?";
            pstmt=conn.prepareStatement(sql);

            pstmt.setString(1,maNV);
            ResultSet rs=pstmt.executeQuery();
            NhanVien nhanVien=new NhanVien();
            if (rs.next()){

                nhanVien.setMaNV(maNV);
                nhanVien.setTenNV(rs.getString("tennv"));
                nhanVien.setDiaChi(rs.getString("diachi"));
                nhanVien.setSoDT(rs.getString("sodt"));
                nhanVien.setNgaySinh(rs.getDate("ngaysinh"));
                nhanVien.setGioiTinh(rs.getString("gioitinh"));
                nhanVien.setCMND(rs.getString("cmnd"));
                nhanVien.setCaLamViec(rs.getString("calamviec"));
                nhanVien.setUsername(rs.getString("username"));
            }

            return nhanVien;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateNhanVien(NhanVien nhanVien) throws SQLException {

        try {

            Connection conn=DatabaseHelper.getConnection();
            String sql="update nhanvien set tennv=?,cmnd=?,diachi=?,sodt=?,ngaysinh=?,gioitinh=?,calamviec=? where manv=?";
            pstmt=conn.prepareStatement(sql);

            pstmt.setString(8,nhanVien.getMaNV());
            pstmt.setString(1,nhanVien.getTenNV());
            pstmt.setString(2,nhanVien.getCMND());
            pstmt.setString(3,nhanVien.getDiaChi());
            pstmt.setString(4,nhanVien.getSoDT());
            pstmt.setDate(5, (Date) nhanVien.getNgaySinh());
            pstmt.setString(6,nhanVien.getGioiTinh());
            pstmt.setString(7,nhanVien.getCaLamViec());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pstmt.executeUpdate()>0;
    }

    public boolean xoaNhanVien(String maNV) throws SQLException {
        try {
            Connection conn=DatabaseHelper.getConnection();
            String sql="delete from nhanvien where manv=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,maNV);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pstmt.executeUpdate()>0;
    }

    public ArrayList<NhanVien> findByMaNV(String maNV){
        try {
            Connection conn=DatabaseHelper.getConnection();
            String sql="select * from nhanvien where manv like ?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,"%" + maNV + "%");
            ResultSet rs=pstmt.executeQuery();
            nhanVienList=new ArrayList<>();
            while (rs.next()){
                NhanVien nhanVien=new NhanVien();
                nhanVien.setMaNV(rs.getString("manv"));
                nhanVien.setTenNV(rs.getString("tennv"));
                nhanVien.setDiaChi(rs.getString("diachi"));
                nhanVien.setSoDT(rs.getString("sodt"));
                nhanVien.setNgaySinh(rs.getDate("ngaysinh"));
                nhanVien.setGioiTinh(rs.getString("gioitinh"));
                nhanVien.setCMND(rs.getString("cmnd"));
                nhanVien.setCaLamViec(rs.getString("calamviec"));
                nhanVien.setUsername(rs.getString("username"));
                nhanVienList.add(nhanVien);
            }
            return nhanVienList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<NhanVien> findByTenNV(String tenNV){
        try {
            Connection conn=DatabaseHelper.getConnection();
            String sql="select * from nhanvien where tennv like ?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,"%" + tenNV + "%");
            ResultSet rs=pstmt.executeQuery();
            nhanVienList=new ArrayList<>();
            while (rs.next()){
                NhanVien nhanVien=new NhanVien();
                nhanVien.setMaNV(rs.getString("manv"));
                nhanVien.setTenNV(rs.getString("tennv"));
                nhanVien.setDiaChi(rs.getString("diachi"));
                nhanVien.setSoDT(rs.getString("sodt"));
                nhanVien.setNgaySinh(rs.getDate("ngaysinh"));
                nhanVien.setGioiTinh(rs.getString("gioitinh"));
                nhanVien.setCMND(rs.getString("cmnd"));
                nhanVien.setCaLamViec(rs.getString("calamviec"));
                nhanVien.setUsername(rs.getString("username"));
                nhanVienList.add(nhanVien);
            }
            return nhanVienList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<NhanVien> findBySDT(String soDT){
        try {
            Connection conn=DatabaseHelper.getConnection();
            String sql="select * from nhanvien where sodt like ?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,"%" + soDT + "%");
            ResultSet rs=pstmt.executeQuery();
            nhanVienList=new ArrayList<>();
            while (rs.next()){
                NhanVien nhanVien=new NhanVien();
                nhanVien.setMaNV(rs.getString("manv"));
                nhanVien.setTenNV(rs.getString("tennv"));
                nhanVien.setDiaChi(rs.getString("diachi"));
                nhanVien.setSoDT(rs.getString("sodt"));
                nhanVien.setNgaySinh(rs.getDate("ngaysinh"));
                nhanVien.setGioiTinh(rs.getString("gioitinh"));
                nhanVien.setCMND(rs.getString("cmnd"));
                nhanVien.setCaLamViec(rs.getString("calamviec"));
                nhanVien.setUsername(rs.getString("username"));
                nhanVienList.add(nhanVien);
            }
            return nhanVienList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<NhanVien> findByCMND(String cmnd){
        try {
            Connection conn=DatabaseHelper.getConnection();
            String sql="select * from nhanvien where cmnd like ?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,"%" + cmnd + "%");
            ResultSet rs=pstmt.executeQuery();
            nhanVienList=new ArrayList<>();
            while (rs.next()){
                NhanVien nhanVien=new NhanVien();
                nhanVien.setMaNV(rs.getString("manv"));
                nhanVien.setTenNV(rs.getString("tennv"));
                nhanVien.setDiaChi(rs.getString("diachi"));
                nhanVien.setSoDT(rs.getString("sodt"));
                nhanVien.setNgaySinh(rs.getDate("ngaysinh"));
                nhanVien.setGioiTinh(rs.getString("gioitinh"));
                nhanVien.setCMND(rs.getString("cmnd"));
                nhanVien.setCaLamViec(rs.getString("calamviec"));
                nhanVien.setUsername(rs.getString("username"));
                nhanVienList.add(nhanVien);
            }
            return nhanVienList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<TaiKhoan> loadCbxUsername(){

        ArrayList<TaiKhoan> taiKhoanList=new ArrayList<>();
        try {
            Connection conn = DatabaseHelper.getConnection();
            String sql = "select username from taikhoan";
            pstmt = conn.prepareStatement(sql);
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()){
                TaiKhoan taiKhoan=new TaiKhoan();
                taiKhoan.setUsername(rs.getString("username"));
                taiKhoanList.add(taiKhoan);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return taiKhoanList;

    }




}
