package Management.BUS;

import Management.DAO.DatabaseHelper;
import Management.DAO.HoaDonDAO;
import Management.DAO.KhachHangDAO;
import Management.DTO.HoaDon;
import Management.DTO.KhachHang;
import Management.GUI.FrmKhachHang;
import Management.GUI.FrmNhanVien;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class KhachHangBUS {

    private KhachHangDAO khachHangDAO=new KhachHangDAO();
    private HoaDonDAO hoaDonDAO=new HoaDonDAO();

    public void xuLyUpdateKH(KhachHang khachHang) throws SQLException, ParseException {
        int row = FrmKhachHang.tbCustomer.getSelectedRow();
        try {
            if (row != -1) {

                java.sql.Date ngaySinhsql = null;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String ngaySinhtxt = dateFormat.format(khachHang.getNgaySinh().getDate());
                java.util.Date ngaySinh = dateFormat.parse(ngaySinhtxt);
                ngaySinhsql = new java.sql.Date(ngaySinh.getTime());

                StringBuilder errorss = new StringBuilder();
                if (khachHang.getTenKH().equalsIgnoreCase("")) {
                    errorss.append("Họ tên không được để trống\n");
                } else if (khachHang.getTenKH().length() > 40) {
                    errorss.append("Họ tên không hợp lệ!!\n");
                }
                if (khachHang.getDiaChi().equalsIgnoreCase("")) {
                    errorss.append("Địa chỉ không được để trống\n");
                }
                if (khachHang.getGioiTinh().equalsIgnoreCase("")) {
                    errorss.append("Giới tính không được để trống\n");
                }
                if (khachHang.getSoDT().equalsIgnoreCase("")) {
                    errorss.append("SDT không được để trống\n");
                } else if (!khachHang.getSoDT().matches("^0[0-9]{9}$")) {
                    errorss.append("SDT không hợp lệ!\n");
                }
                int years = Integer.parseInt(ngaySinhtxt.substring(0, 4));

                if (LocalDate.now().getYear() - years > 120 || LocalDate.now().getYear() - years < 18) {
                    errorss.append("Ngày sinh không hợp lệ!\n");
                }

                if (errorss.isEmpty()) {
                    if (khachHangDAO.updateKhachHang(khachHang)) {
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

    public void xoaRong() {
        FrmKhachHang.tfId.setText("");
        FrmKhachHang.tfName.setText("");
        FrmKhachHang.tfPhone.setText("");
        FrmKhachHang.tfAddress.setText("");
        FrmKhachHang.date.setDate(null);
    }

    public void timKiemKH() {

        StringBuilder errors = new StringBuilder();
        if ( FrmKhachHang.btnTimKiemBySDT.isSelected() == false
                &&  FrmKhachHang.btnTimKiemByMaKH.isSelected() == false
                &&  FrmKhachHang.btnTimKiemByName.isSelected() == false
                &&  FrmKhachHang.tfNhapTimKiem.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin và chọn loại tìm kiếm !", "Thông báo",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon("Image/warning-icon.png"));
        } else if ( FrmKhachHang.tfNhapTimKiem.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin tìm kiếm !", "Thông báo",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon("Image/warning-icon.png"));
        } else if ( FrmKhachHang.btnTimKiemBySDT.isSelected() == false
                &&  FrmKhachHang.btnTimKiemByMaKH.isSelected() == false
                &&  FrmKhachHang.btnTimKiemByName.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn loại tìm kiếm !", "Thông báo",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon("Image/warning-icon.png"));
        } else {
            if ( FrmKhachHang.btnTimKiemByMaKH.isSelected()) {
                getTimKiemKhachHangByMaKH( FrmKhachHang.tfNhapTimKiem.getText());
            } else if ( FrmKhachHang.btnTimKiemByName.isSelected()) {
                getTimKiemKhachHangByTen( FrmKhachHang.tfNhapTimKiem.getText());
            } else if ( FrmKhachHang.btnTimKiemBySDT.isSelected()) {
                getTimKiemKhachHangBySoDT( FrmKhachHang.tfNhapTimKiem.getText());
            }
        }
    }

    public void getTimKiemKhachHangByTen(String ten) {
        ArrayList<KhachHang> list = null;
        try {
            list = (ArrayList<KhachHang>) khachHangDAO.findByTenKH(ten);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        FrmKhachHang.customerTbModel = (DefaultTableModel)  FrmKhachHang.tbCustomer.getModel();
        FrmKhachHang.customerTbModel.setRowCount(0);
        for (KhachHang khachhang : list) {
            FrmKhachHang.customerTbModel.addRow(khachhang.toVector());
        }
        if( FrmKhachHang.customerTbModel.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Khách hàng không tồn tại!");
        }
    }

    public void getTimKiemKhachHangByMaKH(String maKH) {
        ArrayList<KhachHang> list = null;
        try {
            list = (ArrayList<KhachHang>) khachHangDAO.findByMaKH(maKH);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        FrmKhachHang.customerTbModel = (DefaultTableModel)  FrmKhachHang.tbCustomer.getModel();
        FrmKhachHang.customerTbModel.setRowCount(0);
        for (KhachHang khachhang : list) {
            FrmKhachHang.customerTbModel.addRow(khachhang.toVector());
        }
        if( FrmKhachHang.customerTbModel.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Khách hàng không tồn tại!");
        }
    }

    public void getTimKiemKhachHangBySoDT(String soDT) {
        ArrayList<KhachHang> list = null;
        try {
            list = (ArrayList<KhachHang>) khachHangDAO.findBySDT(soDT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        FrmKhachHang.customerTbModel = (DefaultTableModel)  FrmKhachHang.tbCustomer.getModel();
        FrmKhachHang.customerTbModel.setRowCount(0);
        for (KhachHang khachhang : list) {
            FrmKhachHang.customerTbModel.addRow(khachhang.toVector());
        }
        if( FrmKhachHang.customerTbModel.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Khách hàng không tồn tại!");
        }
    }

    public void loadHoaDonCuaKH() {
        int row =  FrmKhachHang.tbCustomer.getSelectedRow();
        String maKH =  FrmKhachHang.tbCustomer.getValueAt(row, 0).toString();
        String maHD = khachHangDAO.getMaHDbyMaKH(maKH);
        HoaDon hoaDon = hoaDonDAO.layThongTinByMaHD(maHD);
        String tenNV = hoaDonDAO.getTenNVbyMa(hoaDon.getNhanVien());

        String[] info = new String[]{
                hoaDon.getMa(), String.valueOf(hoaDon.getNgayLap()), String.valueOf(hoaDon.getTongTien()), tenNV
        };
        FrmKhachHang.hoaDonModel.setRowCount(0);
        FrmKhachHang.hoaDonModel.addRow(info);
        FrmKhachHang.hoaDonModel.fireTableDataChanged();
    }

    public void loadDataKhachHangTable() {
        try {
            Connection conn = DatabaseHelper.getConnection();
            String sql = "select * from khachhang";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            FrmKhachHang.customerTbModel.setRowCount(0);
            while (rs.next()) {
                String[] row = new String[]{
                        String.valueOf(rs.getString("makh")), rs.getString("tenkh"), rs.getString("diachi")
                        , rs.getString("sodt"), String.valueOf(rs.getDate("ngaysinh")), rs.getString("gioitinh")
                };
                FrmKhachHang.customerTbModel.addRow(row);
            }
            FrmKhachHang.customerTbModel.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void xuLySapXep() {
        if (FrmKhachHang.btnTimKiemByMaKH.isSelected() == false
                && FrmKhachHang.btnTimKiemByName.isSelected() == false
                && FrmKhachHang.btnTimKiemBySDT.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn thuộc tính cần sắp xếp!");
        } else {
            if (FrmKhachHang.btnTimKiemByName.isSelected()) {
                FrmKhachHang.customerTbModel.setRowCount(0);
                sortKhachHangByTenKH();
            }
            if (FrmKhachHang.btnTimKiemByMaKH.isSelected()) {
                FrmKhachHang.customerTbModel.setRowCount(0);
                sortKhachHangByMaKH();
            }
            if (FrmKhachHang.btnTimKiemBySDT.isSelected()) {
                FrmKhachHang.customerTbModel.setRowCount(0);
                sortKhachHangBySDT();
            }
        }
    }


    public void sortKhachHangByMaKH() {
        try {
            Connection conn = DatabaseHelper.getConnection();
            String sql = "select * from khachhang order by makh";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            FrmKhachHang.customerTbModel.setRowCount(0);
            while (rs.next()) {
                String[] row = new String[]{
                        String.valueOf(rs.getString("makh")), rs.getString("tenkh"),
                        rs.getString("diachi"), rs.getString("sodt"),
                        String.valueOf(rs.getDate("ngaysinh")), rs.getString("gioitinh"),
                };
                FrmKhachHang.customerTbModel.addRow(row);
            }
            FrmKhachHang.customerTbModel.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sortKhachHangByTenKH() {
        try {
            Connection conn = DatabaseHelper.getConnection();
            String sql = "select * from khachhang order by tenkh";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            FrmKhachHang.customerTbModel.setRowCount(0);
            while (rs.next()) {
                String[] row = new String[]{
                        String.valueOf(rs.getString("makh")), rs.getString("tenkh"),
                        rs.getString("diachi"), rs.getString("sodt"),
                        String.valueOf(rs.getDate("ngaysinh")), rs.getString("gioitinh"),
                };
                FrmKhachHang.customerTbModel.addRow(row);
            }
            FrmKhachHang.customerTbModel.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sortKhachHangBySDT() {
        try {
            Connection conn = DatabaseHelper.getConnection();
            String sql = "select * from khachhang order by sodt";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            FrmKhachHang.customerTbModel.setRowCount(0);
            while (rs.next()) {
                String[] row = new String[]{
                        String.valueOf(rs.getString("makh")), rs.getString("tenkh"),
                        rs.getString("diachi"), rs.getString("sodt"),
                        String.valueOf(rs.getDate("ngaysinh")), rs.getString("gioitinh"),
                };
                FrmKhachHang.customerTbModel.addRow(row);
            }
            FrmKhachHang.customerTbModel.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
