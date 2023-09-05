package Management.BUS;

import Management.DAO.DatabaseHelper;
import Management.DAO.NhanVienDAO;
import Management.DTO.NhanVien;
import Management.DTO.TaiKhoan;
import Management.GUI.FrmNhanVien;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class NhanVienBUS {

    private NhanVienDAO nhanVienDAO=new NhanVienDAO();
    public void xuLySapXep() {

        if (FrmNhanVien.rdoTen.isSelected() == false && FrmNhanVien.rdoMa.isSelected() == false && FrmNhanVien.rdoCaLamViec.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn thuộc tính cần sắp xếp!");
        } else {
            if (FrmNhanVien.rdoTen.isSelected()) {
                FrmNhanVien.NVmodel.setRowCount(0);
                sortNhanVienByTen();
            }
            if (FrmNhanVien.rdoCaLamViec.isSelected()) {
                FrmNhanVien.NVmodel.setRowCount(0);
                sortNhanVienByCaLamViec();
            }
            if (FrmNhanVien.rdoMa.isSelected()) {
                FrmNhanVien.NVmodel.setRowCount(0);
                sortNhanVienByMa();
            }
        }

    }

    public void xuLyUpdateNV(NhanVien nhanVien) throws SQLException, ParseException {
        int row = FrmNhanVien.tblNhanVien.getSelectedRow();
        try {
            if (row != -1) {

                java.sql.Date ngaySinhsql = null;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String ngaySinhtxt = dateFormat.format(nhanVien.getNgaySinh().getDate());
                java.util.Date ngaySinh = dateFormat.parse(ngaySinhtxt);
                ngaySinhsql = new java.sql.Date(ngaySinh.getTime());
                StringBuilder errorss = new StringBuilder();
                if (nhanVien.getTenNV().equalsIgnoreCase("")) {
                    errorss.append("Họ tên không được để trống\n");
                } else if (nhanVien.getTenNV().length() > 40) {
                    errorss.append("Họ tên không hợp lệ!!\n");
                }
                if (nhanVien.getDiaChi().equalsIgnoreCase("")) {
                    errorss.append("Địa chỉ không được để trống\n");
                }
                if (nhanVien.getGioiTinh().equalsIgnoreCase("")) {
                    errorss.append("Giới tính không được để trống\n");
                }
                if (nhanVien.getSoDT().equalsIgnoreCase("")) {
                    errorss.append("SDT không được để trống\n");
                } else if (!nhanVien.getSoDT().matches("^0[0-9]{9}$")) {
                    errorss.append("SDT không hợp lệ!\n");
                }
                if (nhanVien.getCMND().equalsIgnoreCase("")) {
                    errorss.append("CMND không được để trống\n");
                } else if (!nhanVien.getCMND().matches("^[0-9]{12}$")) {
                    errorss.append("CMND không hợp lệ(CMND có 12 số)!\n");
                }
                if (nhanVien.getCaLamViec().equals("")) {
                    errorss.append("Ca làm việc không được để trống!!");
                }
                int years = Integer.parseInt(ngaySinhtxt.substring(0, 4));

                if (LocalDate.now().getYear() - years > 120 || LocalDate.now().getYear() - years < 18) {
                    errorss.append("Ngày sinh không hợp lệ!\n");
                }

                if (errorss.isEmpty()) {
                    if (nhanVienDAO.updateNhanVien(nhanVien)) {
                        JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                    } else JOptionPane.showMessageDialog(null, "Cập nhật thất bại");
                } else JOptionPane.showMessageDialog(null, errorss.toString());
            } else JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng để cập nhật !!");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (HeadlessException e) {
            throw new RuntimeException(e);
        }
    }

    public void xuLyThemNV(NhanVien nhanVien) throws SQLException {
        try {
            java.sql.Date ngaySinhsql = null;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String ngaySinhtxt = dateFormat.format(nhanVien.getNgaySinh().getDate());
            java.util.Date ngaySinh = dateFormat.parse(ngaySinhtxt);
            ngaySinhsql = new java.sql.Date(ngaySinh.getTime());
            StringBuilder errorss = new StringBuilder();
            if (nhanVien.getTenNV().equalsIgnoreCase("")) {
                errorss.append("Họ tên không được để trống\n");
            } else if (nhanVien.getTenNV().length() > 40) {
                errorss.append("Họ tên không hợp lệ!!\n");
            }
            if (nhanVien.getDiaChi().equalsIgnoreCase("")) {
                errorss.append("Địa chỉ không được để trống\n");
            }
            if (nhanVien.getGioiTinh().equalsIgnoreCase("")) {
                errorss.append("Giới tính không được để trống\n");
            }
            if (nhanVien.getSoDT().equalsIgnoreCase("")) {
                errorss.append("SDT không được để trống\n");
            } else if (!nhanVien.getSoDT().matches("^0[0-9]{9}$")) {
                errorss.append("SDT không hợp lệ!\n");
            }
            if (nhanVien.getCMND().equalsIgnoreCase("")) {
                errorss.append("CMND không được để trống\n");
            } else if (!nhanVien.getCMND().matches("^[0-9]{12}$")) {
                errorss.append("CMND không hợp lệ(CMND có 12 số)!\n");
            }
            if (nhanVien.getCaLamViec().equals("")) {
                errorss.append("Ca làm việc không được để trống!!");
            }
            int years = Integer.parseInt(ngaySinhtxt.substring(0, 4));

            if (LocalDate.now().getYear() - years > 120 || LocalDate.now().getYear() - years < 18) {
                errorss.append("Ngày sinh không hợp lệ!\n");
            }

            if (errorss.isEmpty()) {
                if (!checkTrungMaNV(nhanVien.getMaNV())) {
                    if (nhanVienDAO.themNhanVien(nhanVien)) {
                        JOptionPane.showMessageDialog(null, "Thêm thành công");
                    } else JOptionPane.showMessageDialog(null, "Thêm thất bại");
                } else JOptionPane.showMessageDialog(null, "NV đã tồn tại!!");
            } else JOptionPane.showMessageDialog(null, errorss.toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkTrungMaNV(String maNV) throws Exception {
        boolean flag = false;
        for (NhanVien nhanVien : nhanVienDAO.getMaNV()) {
            if (nhanVien.getMaNV().equalsIgnoreCase(maNV)) {
                flag = true;
            }
        }
        return flag;
    }

    public void loadDataNhanVienTable() {
        try {
            Connection conn = DatabaseHelper.getConnection();
            String sql = "select * from nhanvien";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            FrmNhanVien.NVmodel.setRowCount(0);
            while (rs.next()) {
                String[] row = new String[]{String.valueOf(rs.getString("manv")), rs.getString("tennv"),
                        rs.getString("cmnd"), rs.getString("sodt"), rs.getString("gioitinh"),
                        rs.getString("diachi"), String.valueOf(rs.getDate("ngaysinh")),
                        rs.getString("calamviec"), rs.getString("username")};
                FrmNhanVien.NVmodel.addRow(row);
            }
            FrmNhanVien.NVmodel.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sortNhanVienByMa() {
        try {
            Connection conn = DatabaseHelper.getConnection();
            String sql = "select * from nhanvien order by manv";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            FrmNhanVien.NVmodel.setRowCount(0);
            while (rs.next()) {
                String[] row = new String[]{String.valueOf(rs.getString("manv")), rs.getString("tennv"),
                        rs.getString("cmnd"), rs.getString("sodt"), rs.getString("gioitinh"),
                        rs.getString("diachi"), String.valueOf(rs.getDate("ngaysinh")),
                        rs.getString("calamviec"), rs.getString("username")};
                FrmNhanVien.NVmodel.addRow(row);
            }
            FrmNhanVien.NVmodel.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sortNhanVienByTen() {
        try {
            Connection conn = DatabaseHelper.getConnection();
            String sql = "select * from nhanvien order by tennv";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            FrmNhanVien.NVmodel.setRowCount(0);
            while (rs.next()) {
                String[] row = new String[]{String.valueOf(rs.getString("manv")), rs.getString("tennv"),
                        rs.getString("cmnd"), rs.getString("sodt"), rs.getString("gioitinh"),
                        rs.getString("diachi"), String.valueOf(rs.getDate("ngaysinh")),
                        rs.getString("calamviec"), rs.getString("username")};
                FrmNhanVien.NVmodel.addRow(row);
            }
            FrmNhanVien.NVmodel.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sortNhanVienByCaLamViec() {
        try {
            Connection conn = DatabaseHelper.getConnection();
            String sql = "select * from nhanvien order by calamviec";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            FrmNhanVien.NVmodel.setRowCount(0);
            while (rs.next()) {
                String[] row = new String[]{String.valueOf(rs.getString("manv")), rs.getString("tennv"),
                        rs.getString("cmnd"), rs.getString("sodt"), rs.getString("gioitinh"),
                        rs.getString("diachi"), String.valueOf(rs.getDate("ngaysinh")),
                        rs.getString("calamviec"), rs.getString("username")};
                FrmNhanVien.NVmodel.addRow(row);
            }
            FrmNhanVien.NVmodel.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void xuLyXoaNV( String maNV) throws SQLException {
        int row = FrmNhanVien.tblNhanVien.getSelectedRow();
        if (row != -1) {
            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa?") == JOptionPane.YES_OPTION) {
                if (nhanVienDAO.xoaNhanVien(maNV)) {
                    JOptionPane.showMessageDialog(null, "Xóa thành công!");
                    xoaRong();
                    loadDataNhanVienTable();
                } else JOptionPane.showMessageDialog(null, "Xóa thất bại!");
            }
        } else JOptionPane.showMessageDialog(null, "Dữ liệu không được để trống. Vui lòng chọn dòng để xóa!!");
    }

    public void xoaRong() {
        FrmNhanVien.tfId.setText("");
        FrmNhanVien.tfName.setText("");
        FrmNhanVien.tfPhone.setText("");
        FrmNhanVien.tfAddress.setText("");
        FrmNhanVien.tfCMND.setText("");

    }



    public void xuLyTimKiemNV() {

        StringBuilder errors = new StringBuilder();
        if (FrmNhanVien.btnTimKiemBySDT.isSelected() == false
                && FrmNhanVien.btnTimKiemByMaNV.isSelected() == false
                && FrmNhanVien.btnTimKiemByName.isSelected() == false
                && FrmNhanVien.btnTimKiemByCMND.isSelected() == false
                && FrmNhanVien.tfNhapTimKiem.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin và chọn loại tìm kiếm !",
                    "Thông báo", JOptionPane.ERROR_MESSAGE, new ImageIcon("Image/warning-icon.png"));
        } else if (FrmNhanVien.tfNhapTimKiem.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin tìm kiếm !",
                    "Thông báo", JOptionPane.ERROR_MESSAGE, new ImageIcon("Image/warning-icon.png"));
        } else if (FrmNhanVien.btnTimKiemBySDT.isSelected() == false
                && FrmNhanVien.btnTimKiemByMaNV.isSelected() == false
                && FrmNhanVien.btnTimKiemByName.isSelected() == false
                && FrmNhanVien.btnTimKiemByCMND.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn loại tìm kiếm !",
                    "Thông báo", JOptionPane.ERROR_MESSAGE, new ImageIcon("Image/warning-icon.png"));
        } else {
            if (FrmNhanVien.btnTimKiemByMaNV.isSelected()) {
                getTimKiemNhanVienByMaNV(FrmNhanVien.tfNhapTimKiem.getText());
            } else if (FrmNhanVien.btnTimKiemByName.isSelected()) {
                getTimKiemNhanVienByTen(FrmNhanVien.tfNhapTimKiem.getText());
            } else if (FrmNhanVien.btnTimKiemBySDT.isSelected()) {
                getTimKiemNhanVienBySoDT(FrmNhanVien.tfNhapTimKiem.getText());
            } else if (FrmNhanVien.btnTimKiemByCMND.isSelected()) {
                getTimKiemNhanVienByCMND(FrmNhanVien.tfNhapTimKiem.getText());
            }
        }
    }

    public void getTimKiemNhanVienByTen(String ten) {
        ArrayList<NhanVien> list = null;
        try {
            list = (ArrayList<NhanVien>) nhanVienDAO.findByTenNV(ten);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        FrmNhanVien.NVmodel = (DefaultTableModel) FrmNhanVien.tblNhanVien.getModel();
        FrmNhanVien.NVmodel.setRowCount(0);
        for (NhanVien nhanVien : list) {
            FrmNhanVien.NVmodel.addRow(nhanVien.toVector());
        }
        if( FrmNhanVien.NVmodel.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Nhân viên không tồn tại!");
        }
    }

    public void getTimKiemNhanVienByMaNV(String maNV) {
        ArrayList<NhanVien> list = null;
        try {
            list = (ArrayList<NhanVien>) nhanVienDAO.findByMaNV(maNV);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        FrmNhanVien.NVmodel = (DefaultTableModel) FrmNhanVien.tblNhanVien.getModel();
        FrmNhanVien.NVmodel.setRowCount(0);
        for (NhanVien nhanVien : list) {
            FrmNhanVien.NVmodel.addRow(nhanVien.toVector());
        }
        if( FrmNhanVien.NVmodel.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Nhân viên không tồn tại!");
        }
    }

    public void getTimKiemNhanVienBySoDT(String soDT) {
        ArrayList<NhanVien> list = null;
        try {
            list = (ArrayList<NhanVien>) nhanVienDAO.findBySDT(soDT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        FrmNhanVien.NVmodel = (DefaultTableModel) FrmNhanVien.tblNhanVien.getModel();
        FrmNhanVien.NVmodel.setRowCount(0);
        for (NhanVien nhanVien : list) {
            FrmNhanVien.NVmodel.addRow(nhanVien.toVector());
        }
    }

    public void getTimKiemNhanVienByCMND(String cmnd) {
        ArrayList<NhanVien> list = null;
        try {
            list = (ArrayList<NhanVien>) nhanVienDAO.findByCMND(cmnd);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        FrmNhanVien.NVmodel = (DefaultTableModel) FrmNhanVien.tblNhanVien.getModel();
        FrmNhanVien.NVmodel.setRowCount(0);
        for (NhanVien nhanVien : list) {
            FrmNhanVien.NVmodel.addRow(nhanVien.toVector());
        }
        if( FrmNhanVien.NVmodel.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Nhân viên không tồn tại!");
        }
    }

    public void loadCbxUsername() {
        FrmNhanVien.cbxUsername.removeAllItems();
        try {
            for (TaiKhoan taiKhoan : nhanVienDAO.loadCbxUsername()) {
                String username = taiKhoan.getUsername();
                FrmNhanVien.cbxModel.addElement(username);
                FrmNhanVien.cbxUsername.setModel(FrmNhanVien.cbxModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if( FrmNhanVien.NVmodel.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Nhân viên không tồn tại!");
        }

    }


}
