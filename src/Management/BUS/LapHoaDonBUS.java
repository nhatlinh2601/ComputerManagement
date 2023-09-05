package Management.BUS;

import Management.DAO.ChiTietHDDAO;
import Management.DAO.DatabaseHelper;
import Management.DAO.HoaDonDAO;
import Management.DAO.SanPhamLKDAO;
import Management.DTO.ChiTietHD;
import Management.DTO.HoaDon;
import Management.DTO.KhachHang;
import Management.GUI.FrmLapHoaDon;
import Management.GUI.FrmXuatHD;
import Management.TEST.RandomStringExmple;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class LapHoaDonBUS {

    private ChiTietHDDAO chiTietHDDAO=new ChiTietHDDAO();
    private HoaDonDAO hoaDonDAO=new HoaDonDAO();
    private SanPhamLKDAO sanPhamLKDAO=new SanPhamLKDAO();

    public void xuLyThemSPVaoGio( String tenSP,Integer sl, String maSP) {

        if (sl + chiTietHDDAO.soLuongSPDaBan(maSP) > chiTietHDDAO.soLuongNhap(maSP)) {
            JOptionPane.showMessageDialog(null, "Số Lượng SP Cần Mua Vượt Giới Hạn Trong Kho!");
        } else {
            int rowCount = FrmLapHoaDon.tbModel.getRowCount();

            if (rowCount > 0) {
                int count = 0;
                for (int i = 0; i < rowCount; i++) {

                    String value = (String) FrmLapHoaDon.tbModel.getValueAt(i, 1);
                    String s = (String) FrmLapHoaDon.tbModel.getValueAt(i, 5);

                    if (value.toString().equals(tenSP)) {
                        if (sl + chiTietHDDAO.soLuongSPDaBan(maSP) + Integer.parseInt(s) > chiTietHDDAO.soLuongNhap(maSP)) {
                            JOptionPane.showMessageDialog(null, "Số Lượng SP Cần Mua Vượt Giới Hạn Trong Kho!");
                        } else {
                            FrmLapHoaDon.tbModel.removeRow(i);
                            loadDataSPvaoHoaDon(tenSP, Integer.parseInt(s) + (int) sl);
                            String tongTien = String.valueOf(tinhTienHD());
                            FrmLapHoaDon.tfTongTienSP.setText(tongTien);
                            return;
                        }

                    } else {
                        count++;
                    }
                    if (count == rowCount) loadDataSPvaoHoaDon(tenSP, sl);
                    String tongTien = String.valueOf(tinhTienHD());
                    FrmLapHoaDon.tfTongTienSP.setText(tongTien);
                }
            } else loadDataSPvaoHoaDon(tenSP, sl);
            String tongTien = String.valueOf(tinhTienHD());
            FrmLapHoaDon.tfTongTienSP.setText(tongTien);

        }
    }

    public void xuLyTienThoi() {
        Double tienThoi = 0.0;
        StringBuilder errors = new StringBuilder();
        if (FrmLapHoaDon.tfTienNhan.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Tiền nhận rỗng");
        } else {
            Double tienNhan = Double.valueOf(FrmLapHoaDon.tfTienNhan.getText());
            Double tongTien = Double.valueOf(FrmLapHoaDon.tfTongTienSP.getText());
            if (tienNhan < tongTien) {
                errors.append("Số tiền không đủ!!");
            }
            if (errors.isEmpty()) {
                tienThoi = tienNhan - tongTien;
            } else {
                JOptionPane.showMessageDialog(null, errors.toString());
            }
            FrmLapHoaDon.tfTienThoi.setText(String.valueOf(tienThoi));
        }
    }

    public void loadDataSPvaoHoaDon(String tenSP, int soLuong) {
        try {
            Connection conn = DatabaseHelper.getConnection();
            String sql = "select * from linhkienmt where tensp=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tenSP);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Float tongTien = soLuong * rs.getFloat("gia");
                String[] row = new String[]{
                        rs.getString("masp"), rs.getString("tensp"), rs.getString("nsx"),
                        rs.getString("donvitinh"), String.valueOf(rs.getFloat("gia")),
                        String.valueOf(soLuong), String.valueOf(tongTien)
                };
                FrmLapHoaDon.tbModel.addRow(row);
            }
            FrmLapHoaDon.tbModel.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void xuLyXoa() {
        if ( FrmLapHoaDon.tbHoaDon.getRowCount() <= 0) {
            JOptionPane.showMessageDialog(null, "Đơn hàng đang trống.");
        } else {
            if ( FrmLapHoaDon.tbHoaDon.getSelectedRow() != -1) {

                if (JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa sản phẩm?") == JOptionPane.YES_OPTION) {
                    FrmLapHoaDon.tbModel.removeRow( FrmLapHoaDon.tbHoaDon.getSelectedRow());
                    FrmLapHoaDon.tbModel.fireTableDataChanged();
                }
            } else
                JOptionPane.showMessageDialog(null, "Dữ liệu không được để trống.Chọn dòng sản phẩm muốn xóa!!");

        }
        String tongTien = String.valueOf(tinhTienHD());
        FrmLapHoaDon.tfTongTienSP.setText(tongTien);
    }

    public void xuLyThemKHHoaDon(HoaDon hoaDon,KhachHang khachHang) throws Exception {

        StringBuilder errors = new StringBuilder();
        java.sql.Date ngaySinhsql2 = null;
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        String ngaySinhtxt2 = dateFormat2.format(hoaDon.getNgayLap().getDate());
        java.util.Date ngaySinh2 = dateFormat2.parse(ngaySinhtxt2);
        ngaySinhsql2 = new java.sql.Date(ngaySinh2.getTime());

        java.sql.Date ngaySinhsql = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String ngaySinhtxt = dateFormat.format(khachHang.getNgaySinh().getDate());
        java.util.Date ngaySinh = dateFormat.parse(ngaySinhtxt);
        ngaySinhsql = new java.sql.Date(ngaySinh.getTime());
        StringBuilder errorss = new StringBuilder();

        if (hoaDon.getMa().equalsIgnoreCase("")) {
            errorss.append("Mã Hóa đơn không được để trống\n");
        }
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
            if (FrmLapHoaDon.tbHoaDon.getRowCount() > 0) {
                if (hoaDonDAO.addHoaDon(hoaDon)) {
                    if (hoaDonDAO.addKhachHang(khachHang)) {
                        JOptionPane.showMessageDialog(null, "Thêm HĐ thành công");
                        int row = (FrmLapHoaDon.tbHoaDon.getRowCount());
                        if (FrmLapHoaDon.tbHoaDon.getRowCount() > 0) {
                            for (int i = 0; i < row; i++) {
                                String maSP = FrmLapHoaDon.tbModel.getValueAt(i, 0).toString();
//                                String maHD = tfIDHoaDon.getText();
                                String dvt = FrmLapHoaDon.tbModel.getValueAt(i, 3).toString();
                                Float donGia = Float.parseFloat(FrmLapHoaDon.tbModel.getValueAt(i, 4).toString());
                                int soLuong = Integer.parseInt(FrmLapHoaDon.tbModel.getValueAt(i, 5).toString());
                                ChiTietHD chiTietHD = new ChiTietHD(hoaDon.getMa(), maSP, dvt, donGia, soLuong);
                                chiTietHDDAO.insertCTHD(chiTietHD);
                            }
                        }
                        xuLyXuatHD(hoaDon, khachHang);
                        xoaRong();
                    } else JOptionPane.showMessageDialog(null, "Thêm HĐ thất bại");
                } else JOptionPane.showMessageDialog(null, "Thêm HD thất bại");
            } else JOptionPane.showMessageDialog(null, "Giỏ hàng đang trống, thêm sp trước khi lưu");
        } else JOptionPane.showMessageDialog(null, errorss.toString());

    }

    public void xuLyXuatHD(HoaDon hoaDon,KhachHang khachHang) throws Exception {

        FrmXuatHD frmXuatHD=new FrmXuatHD();
        frmXuatHD.setVisible(true);
        java.sql.Date ngaySinhsql = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String ngaySinhtxt = dateFormat.format(FrmLapHoaDon.birthday.getDate());
        java.util.Date ngaySinh = dateFormat.parse(ngaySinhtxt);
        ngaySinhsql = new java.sql.Date(ngaySinh.getTime());
        java.sql.Date ngaySinhsql2 = null;
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        String ngaySinhtxt2 = dateFormat2.format(FrmLapHoaDon.dchooseNgaylap.getDate());
        java.util.Date ngaySinh2 = dateFormat2.parse(ngaySinhtxt2);
        ngaySinhsql2 = new java.sql.Date(ngaySinh2.getTime());

        String nguoiBan=FrmLapHoaDon.modelNV.getSelectedItem().toString();
        FrmXuatHD.lblLoaiHD1.setText(hoaDon.getMa());
        FrmXuatHD.lbNgayLap.setText(String.valueOf(ngaySinhsql2));
        FrmXuatHD.lblTenKH1.setText(khachHang.getTenKH());
        FrmXuatHD.lblGTinh1.setText(khachHang.getGioiTinh());
        FrmXuatHD.lblsdtkh1.setText(khachHang.getSoDT());
        FrmXuatHD.lblNamSinh1.setText(String.valueOf(ngaySinhsql));
        FrmXuatHD.lblNguoiBan.setText(nguoiBan);
        FrmXuatHD.lblNguoiMuaHang1.setText(khachHang.getTenKH());
        int row = FrmLapHoaDon.tbHoaDon.getRowCount();
        int slg=0;
        Float tongTien= (float) 0;
        int j=1;
        if (FrmLapHoaDon.tbHoaDon.getRowCount() > 0) {
            for (int i = 0; i < row; i++) {
                String maSP = FrmLapHoaDon.tbModel.getValueAt(i, 0).toString();
                String dvt = FrmLapHoaDon.tbModel.getValueAt(i, 3).toString();
                Float donGia = Float.parseFloat(FrmLapHoaDon.tbModel.getValueAt(i, 4).toString());
                int soLuong = Integer.parseInt(FrmLapHoaDon.tbModel.getValueAt(i, 5).toString());
                Float thanhTien= Float.parseFloat( FrmLapHoaDon.tbModel.getValueAt(i,6).toString());
                String tensp=sanPhamLKDAO.getTenByMa(maSP);
                String [] rows=new String[]{
                        String.valueOf(j++),tensp,dvt, String.valueOf(donGia), String.valueOf(soLuong),
                        String.valueOf(thanhTien)
                };
                FrmXuatHD.tableModel.addRow(rows);
                FrmXuatHD.tableModel.fireTableDataChanged();
                slg+=soLuong;
                tongTien+=thanhTien;
            }
        }
        FrmXuatHD.lblSL.setText(String.valueOf(slg));
        FrmXuatHD.lblTongTThuoc.setText(String.valueOf(tongTien));
        FrmXuatHD.lblTongT.setText(String.valueOf(tongTien));


    }

    public Double tinhTienHD() {
        Double tongTien = 0.0;
        int row =  FrmLapHoaDon.tbHoaDon.getRowCount();
        for (int i = 0; i < row; i++) {
            tongTien += Double.parseDouble((String)  FrmLapHoaDon.tbHoaDon.getValueAt(i, 6));
        }
        return tongTien;
    }
    public boolean checkTrungMaHD() throws Exception {
        String maHD =  FrmLapHoaDon.tfIDHoaDon.getText().trim();
        boolean flag = false;
        for (HoaDon hoaDon : hoaDonDAO.getMaHD()) {
            if (hoaDon.getMa().equalsIgnoreCase(maHD)) {
                flag = true;
            }
        }
        return flag;
    }

    public void xoaRong() {

        RandomStringExmple rand=new RandomStringExmple();
        FrmLapHoaDon.tfIDHoaDon.setText(rand.randomAlphaNumeric(6));
        FrmLapHoaDon.tfTenKH.setText("");
        FrmLapHoaDon.tfDiaChi.setText("");
        FrmLapHoaDon.tfSDT.setText("");
        FrmLapHoaDon.tfTongTienSP.setText("0");
        FrmLapHoaDon.tfTienNhan.setText("0");
        FrmLapHoaDon.tfTienThoi.setText("0");
        FrmLapHoaDon.tbModel.setRowCount(0);


    }

}
