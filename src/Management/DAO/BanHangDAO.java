package Management.DAO;

import Management.DTO.SanPhamLK;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BanHangDAO {
    private PreparedStatement pstmt;
    private ResultSet rs;

    public ArrayList<SanPhamLK> loadSanPhamCPU() {
        ArrayList<SanPhamLK> sanPhamLKS = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "select tensp from linhkienmt where tensp like ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + "cpu" + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                SanPhamLK sanPhamLK = new SanPhamLK();
                sanPhamLK.setTenSP(rs.getString("tensp"));
                sanPhamLKS.add(sanPhamLK);
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
        return sanPhamLKS;
    }

    public ArrayList<SanPhamLK> loadSanPhamRAM() {
        ArrayList<SanPhamLK> sanPhamLKS = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "select tensp from linhkienmt where tensp like ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + "ram" + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                SanPhamLK sanPhamLK = new SanPhamLK();
                sanPhamLK.setTenSP(rs.getString("tensp"));
                sanPhamLKS.add(sanPhamLK);
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
        return sanPhamLKS;
    }

    public ArrayList<SanPhamLK> loadSanPhamOCung() {
        ArrayList<SanPhamLK> sanPhamLKS = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "select tensp from linhkienmt where tensp like ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + "Ổ cứng" + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                SanPhamLK sanPhamLK = new SanPhamLK();
                sanPhamLK.setTenSP(rs.getString("tensp"));
                sanPhamLKS.add(sanPhamLK);
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
        return sanPhamLKS;
    }

    public ArrayList<SanPhamLK> loadSanPhamBoNguon() {
        ArrayList<SanPhamLK> sanPhamLKS = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "select tensp from linhkienmt where tensp like ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + "nguồn" + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                SanPhamLK sanPhamLK = new SanPhamLK();
                sanPhamLK.setTenSP(rs.getString("tensp"));
                sanPhamLKS.add(sanPhamLK);
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
        return sanPhamLKS;
    }

    public ArrayList<SanPhamLK> loadSanPhamCard() {
        ArrayList<SanPhamLK> sanPhamLKS = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "select tensp from linhkienmt where tensp like ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + "Card" + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                SanPhamLK sanPhamLK = new SanPhamLK();
                sanPhamLK.setTenSP(rs.getString("tensp"));
                sanPhamLKS.add(sanPhamLK);
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
        return sanPhamLKS;
    }

    public ArrayList<SanPhamLK> loadSanPhamMainBoard() {
        ArrayList<SanPhamLK> sanPhamLKS = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "select tensp from linhkienmt where tensp like ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + "mainboard" + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                SanPhamLK sanPhamLK = new SanPhamLK();
                sanPhamLK.setTenSP(rs.getString("tensp"));
                sanPhamLKS.add(sanPhamLK);
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
        return sanPhamLKS;
    }

    public ArrayList<SanPhamLK> loadSanPhamBanPhim() {
        ArrayList<SanPhamLK> sanPhamLKS = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "select tensp from linhkienmt where tensp like ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + "phím" + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                SanPhamLK sanPhamLK = new SanPhamLK();
                sanPhamLK.setTenSP(rs.getString("tensp"));
                sanPhamLKS.add(sanPhamLK);
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
        return sanPhamLKS;
    }

    public ArrayList<SanPhamLK> loadSanPhamChuot() {
        ArrayList<SanPhamLK> sanPhamLKS = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "select tensp from linhkienmt where tensp like ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + "chuột" + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                SanPhamLK sanPhamLK = new SanPhamLK();
                sanPhamLK.setTenSP(rs.getString("tensp"));
                sanPhamLKS.add(sanPhamLK);
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
        return sanPhamLKS;
    }

    public ArrayList<SanPhamLK> loadSanPham() {
        ArrayList<SanPhamLK> sanPhamLKS = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "select tensp from linhkienmt";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                SanPhamLK sanPhamLK = new SanPhamLK();
                sanPhamLK.setTenSP(rs.getString("tensp"));
                sanPhamLKS.add(sanPhamLK);
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
        return sanPhamLKS;
    }

    public SanPhamLK layThongTinSanPham(String tenSP) {
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "select * from linhkienmt where tensp=?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, tenSP);
            ResultSet rs = pstmt.executeQuery();
            SanPhamLK sanPhamLK = new SanPhamLK();
            if (rs.next()) {
                sanPhamLK.setMaSP(rs.getString("masp"));
                sanPhamLK.setTenSP(rs.getString("tensp"));
                sanPhamLK.setNsx(rs.getString("nsx"));
                sanPhamLK.setMoTa(rs.getString("mota"));
                sanPhamLK.setNgaySX(rs.getDate("ngaysx"));
                sanPhamLK.setSoLuong(rs.getInt("soluongnhap"));
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
        } finally {
            if (conn != null) try {
                conn.close();
            } catch (SQLException ignore) {
            }
        }
    }

}
