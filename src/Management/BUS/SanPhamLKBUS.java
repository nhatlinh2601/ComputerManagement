package Management.BUS;

import Management.DAO.DatabaseHelper;
import Management.DAO.SanPhamLKDAO;
import Management.DTO.SanPhamLK;
import Management.GUI.FrmKhachHang;
import Management.GUI.FrmSanPhamLK;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SanPhamLKBUS {

    private SanPhamLKDAO sanPhamLKDAO=new SanPhamLKDAO();

    public void xuLyThemSP(SanPhamLK sanPhamLK) {
        try {
            StringBuilder errorss = new StringBuilder();
            if (sanPhamLK.getMaSP().equalsIgnoreCase("")) {
                errorss.append("Mã sản phẩm không được để trống\n");
            }
            if (sanPhamLK.getTenSP().equalsIgnoreCase("")) {
                errorss.append("Tên sản phẩm không được để trống\n");
            }
            String gia= String.valueOf(sanPhamLK.getPrice());
            if (gia.equalsIgnoreCase("")) {
                errorss.append("Giá sản phẩm không được để trống\n");
            }
            String sluong= String.valueOf(sanPhamLK.getSoLuong());
            if (sluong.equalsIgnoreCase("")) {
                errorss.append("SL sản phẩm không được để trống\n");
            }
            if (errorss.isEmpty()) {
                if (!checkTrungMaSP(sanPhamLK.getMaSP()) && !checkTrungTenSP(sanPhamLK.getTenSP())) {
                    if (sanPhamLKDAO.themSanPhamLK(sanPhamLK)) {
                        JOptionPane.showMessageDialog(null, "Thêm thành công");
                    } else JOptionPane.showMessageDialog(null, "Thêm thất bại");
                } else JOptionPane.showMessageDialog(null, "Sản phẩm đã tồn tại");
            } else JOptionPane.showMessageDialog(null, errorss.toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void loadDataSanPhamTable() {
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "select * from linhkienmt";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            FrmSanPhamLK.spLKModel.setRowCount(0);
            while (rs.next()) {
                String[] row = new String[]{String.valueOf(rs.getString("masp")),
                        rs.getString("tensp"), rs.getString("mota"),
                        String.valueOf(rs.getFloat("gia")), String.valueOf(rs.getFloat("gianhap"))
                        , rs.getString("nsx"), String.valueOf(rs.getInt("soluongnhap")),
                        rs.getString("trangthai"), rs.getString("donvitinh"),
                        String.valueOf(rs.getDate("ngaysx")), rs.getString("sodk")};
                FrmSanPhamLK.spLKModel.addRow(row);
            }
            FrmSanPhamLK.spLKModel.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) try {
                conn.close();
            } catch (SQLException ignore) {
            }
        }
    }

    public void xuLyXoaSP(String maSP) throws SQLException {
        int row =  FrmSanPhamLK.tblSanPhamLK.getSelectedRow();
        if (row != -1) {
            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa?") == JOptionPane.YES_OPTION) {
                if (sanPhamLKDAO.xoaSanPhamLK(maSP)) {
                    JOptionPane.showMessageDialog(null, "Xóa thành công!");
                    xoaRong();
                } else JOptionPane.showMessageDialog(null, "Xóa thất bại!");
            }
        } else JOptionPane.showMessageDialog(null, "Dữ liệu không được để trống. Vui lòng chọn dòng để xóa!!");
    }

    public void xoaRong() {
        FrmSanPhamLK.tfId.setText("");
        FrmSanPhamLK.tfName.setText("");
        FrmSanPhamLK.tfNSX.setText("");
        FrmSanPhamLK.tfPrice.setText("");
        FrmSanPhamLK.tfDonViTinh.setText("");
        FrmSanPhamLK.tfMoTa.setText("");
        FrmSanPhamLK.tfSoDki.setText("");
        FrmSanPhamLK.tfTrangThai.setText("");
        FrmSanPhamLK.tfSoLuong.setText("");
        FrmSanPhamLK.tfGiaNhap.setText("");

    }

    public void xuLyUpdateSP(SanPhamLK sanPhamLK) {
        int row = FrmSanPhamLK.tblSanPhamLK.getSelectedRow();
        try {
            if (row != -1) {
                java.sql.Date ngaySX = null;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String ngaySXtxt = dateFormat.format(sanPhamLK.getNgaySX().getDate());
                java.util.Date ngaySinh = dateFormat.parse(ngaySXtxt);
                ngaySX = new java.sql.Date(ngaySinh.getTime());

                StringBuilder errorss = new StringBuilder();
                if (sanPhamLK.getMaSP().equalsIgnoreCase("")) {
                    errorss.append("Mã SP không được để trống\n");
                }
                if (sanPhamLK.getTenSP().equalsIgnoreCase("")) {
                    errorss.append("Tên SP không được để trống\n");
                }
                String gia= String.valueOf(sanPhamLK.getPrice());
                if (gia.equalsIgnoreCase("")) {
                    errorss.append("Giá SP không được để trống\n");
                }

                if (errorss.isEmpty()) {
                    if (sanPhamLKDAO.updateSanPhamLK(sanPhamLK)) {
                        JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                    } else JOptionPane.showMessageDialog(null, "Cập nhật thất bại");
                } else JOptionPane.showMessageDialog(null, errorss.toString());
            } else JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng để cập nhật !!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkTrungMaSP(String maSP) throws Exception {
        boolean flag = false;
        for (SanPhamLK sanPhamLK : sanPhamLKDAO.getMaSP()) {
            if (sanPhamLK.getMaSP().equalsIgnoreCase(maSP)) {
                flag = true;
            }
        }
        return flag;
    }

    public boolean checkTrungTenSP(String tenSP) throws Exception {
        boolean flag = false;
        for (SanPhamLK sanPhamLK : sanPhamLKDAO.getTenSP()) {
            if (sanPhamLK.getTenSP().equalsIgnoreCase(tenSP)) {
                flag = true;
            }
        }
        return flag;
    }

    public void timKiemSP(String text) {
        StringBuilder errors = new StringBuilder();
        if ( FrmSanPhamLK.btnTimKiemByMaSP.isSelected() == false
                &&  FrmSanPhamLK.btnTimKiemBytenSP.isSelected() == false
                &&  FrmSanPhamLK.btnTimKiemByNSX.isSelected() == false
                &&  FrmSanPhamLK.tfNhapTimKiem.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin và chọn loại tìm kiếm !",
                    "Thông báo", JOptionPane.ERROR_MESSAGE, new ImageIcon("Image/warning-icon.png"));
        } else if ( FrmSanPhamLK.tfNhapTimKiem.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin tìm kiếm !",
                    "Thông báo", JOptionPane.ERROR_MESSAGE, new ImageIcon("Image/warning-icon.png"));
        } else if ( FrmSanPhamLK.btnTimKiemByMaSP.isSelected() == false
                &&  FrmSanPhamLK.btnTimKiemBytenSP.isSelected() == false
                &&  FrmSanPhamLK.btnTimKiemByNSX.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn loại tìm kiếm !",
                    "Thông báo", JOptionPane.ERROR_MESSAGE, new ImageIcon("Image/warning-icon.png"));
        } else {
            if ( FrmSanPhamLK.btnTimKiemByMaSP.isSelected()) {
                getTimKiemNhanVienByMaSP( text);
            } else if ( FrmSanPhamLK.btnTimKiemBytenSP.isSelected()) {
                getTimKiemNhanVienByTenSP( text);
            } else if ( FrmSanPhamLK.btnTimKiemByNSX.isSelected()) {
                getTimKiemNhanVienByNSX( text);
            }
        }
    }

    public void getTimKiemNhanVienByMaSP(String maSP) {
        ArrayList<SanPhamLK> list = null;
        try {
            list = (ArrayList<SanPhamLK>) sanPhamLKDAO.findByMaSP(maSP);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        FrmSanPhamLK.spLKModel = (DefaultTableModel)  FrmSanPhamLK.tblSanPhamLK.getModel();
        FrmSanPhamLK.spLKModel.setRowCount(0);
        for (SanPhamLK sanPhamLK : list) {
            FrmSanPhamLK.spLKModel.addRow(sanPhamLK.toVector());
        }
        if(  FrmSanPhamLK.spLKModel.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Sản phẩm không tồn tại!");
        }
    }

    public void getTimKiemNhanVienByTenSP(String tenSP) {
        ArrayList<SanPhamLK> list = null;
        try {
            list = (ArrayList<SanPhamLK>) sanPhamLKDAO.findByTenSP(tenSP);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        FrmSanPhamLK.spLKModel = (DefaultTableModel)  FrmSanPhamLK.tblSanPhamLK.getModel();
        FrmSanPhamLK.spLKModel.setRowCount(0);
        for (SanPhamLK sanPhamLK : list) {
            FrmSanPhamLK.spLKModel.addRow(sanPhamLK.toVector());
        }
        if(  FrmSanPhamLK.spLKModel.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Sản phẩm không tồn tại!");
        }
    }

    public void getTimKiemNhanVienByNSX(String nsx) {
        ArrayList<SanPhamLK> list = null;
        try {
            list = (ArrayList<SanPhamLK>) sanPhamLKDAO.findByNSX(nsx);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        FrmSanPhamLK.spLKModel = (DefaultTableModel)  FrmSanPhamLK.tblSanPhamLK.getModel();
        FrmSanPhamLK.spLKModel.setRowCount(0);
        for (SanPhamLK sanPhamLK : list) {
            FrmSanPhamLK.spLKModel.addRow(sanPhamLK.toVector());
        }
        if(  FrmSanPhamLK.spLKModel.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Sản phẩm không tồn tại!");
        }
    }

    public void xuLySapXep() {
        if ( FrmSanPhamLK.sortModel.getSelectedItem().equals("Giá bán")) {
            sortSanPhamByGiaBan();
        }
        if ( FrmSanPhamLK.sortModel.getSelectedItem().equals("Giá nhập")) {
            sortSanPhamByGiaNhap();
        }
        if ( FrmSanPhamLK.sortModel.getSelectedItem().equals("Tên sản phẩm")) {
            sortSanPhamByTenSP();
        }
        if ( FrmSanPhamLK.sortModel.getSelectedItem().equals("Mã sản phẩm")) {
            sortSanPhamByMaSP();
        }
    }

    public void sortSanPhamByGiaNhap() {
        try {
            Connection conn = DatabaseHelper.getConnection();
            String sql = "select * from linhkienmt order by gianhap";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            FrmSanPhamLK.spLKModel.setRowCount(0);
            while (rs.next()) {
                String[] row = new String[]{String.valueOf(rs.getString("masp")),
                        rs.getString("tensp"), rs.getString("mota"),
                        String.valueOf(rs.getFloat("gia")), String.valueOf(rs.getFloat("gianhap")),
                        rs.getString("nsx"), String.valueOf(rs.getInt("soluongnhap")),
                        rs.getString("trangthai"), rs.getString("donvitinh"),
                        String.valueOf(rs.getDate("ngaysx")), rs.getString("sodk"),};
                FrmSanPhamLK.spLKModel.addRow(row);
            }
            FrmSanPhamLK.spLKModel.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sortSanPhamByTenSP() {
        try {
            Connection conn = DatabaseHelper.getConnection();
            String sql = "select * from linhkienmt order by tensp";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            FrmSanPhamLK.spLKModel.setRowCount(0);
            while (rs.next()) {
                String[] row = new String[]{String.valueOf(rs.getString("masp")),
                        rs.getString("tensp"), rs.getString("mota"),
                        String.valueOf(rs.getFloat("gia")), String.valueOf(rs.getFloat("gianhap")),
                        rs.getString("nsx"), String.valueOf(rs.getInt("soluongnhap")),
                        rs.getString("trangthai"), rs.getString("donvitinh"),
                        String.valueOf(rs.getDate("ngaysx")), rs.getString("sodk"),};
                FrmSanPhamLK.spLKModel.addRow(row);
            }
            FrmSanPhamLK.spLKModel.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sortSanPhamByMaSP() {
        try {
            Connection conn = DatabaseHelper.getConnection();
            String sql = "select * from linhkienmt order by masp";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            FrmSanPhamLK.spLKModel.setRowCount(0);
            while (rs.next()) {
                String[] row = new String[]{String.valueOf(rs.getString("masp")),
                        rs.getString("tensp"), rs.getString("mota"),
                        String.valueOf(rs.getFloat("gia")), String.valueOf(rs.getFloat("gianhap")),
                        rs.getString("nsx"), String.valueOf(rs.getInt("soluongnhap")),
                        rs.getString("trangthai"), rs.getString("donvitinh"),
                        String.valueOf(rs.getDate("ngaysx")), rs.getString("sodk"),};
                FrmSanPhamLK.spLKModel.addRow(row);
            }
            FrmSanPhamLK.spLKModel.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sortSanPhamByGiaBan() {
        try {
            Connection conn = DatabaseHelper.getConnection();
            String sql = "select * from linhkienmt order by gia";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            FrmSanPhamLK.spLKModel.setRowCount(0);
            while (rs.next()) {
                String[] row = new String[]{String.valueOf(rs.getString("masp")),
                        rs.getString("tensp"), rs.getString("mota"),
                        String.valueOf(rs.getFloat("gia")), String.valueOf(rs.getFloat("gianhap")),
                        rs.getString("nsx"), String.valueOf(rs.getInt("soluongnhap")),
                        rs.getString("trangthai"), rs.getString("donvitinh"),
                        String.valueOf(rs.getDate("ngaysx")), rs.getString("sodk"),};
                FrmSanPhamLK.spLKModel.addRow(row);
            }
            FrmSanPhamLK.spLKModel.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}
