package Management.BUS;

import Management.DAO.HoaDonDAO;
import Management.DAO.TKTheoNVDAO;
import Management.DTO.HoaDon;
import Management.DTO.TKTheoNV;
import Management.GUI.FrmTKTheoNV;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TKTheoNVBUS {

    private TKTheoNVDAO tkTheoNVDAO=new TKTheoNVDAO();
    private HoaDonDAO hoaDonDAO=new HoaDonDAO();

    public void xuLyXem() {

        String tenNV = FrmTKTheoNV.cbxModel.getSelectedItem().toString();
        String maNV = hoaDonDAO.getMaNVbyTen(tenNV);
        java.sql.Date ngaySX = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String ngaySXtxt = dateFormat.format(FrmTKTheoNV.date.getDate());
        Date ngaySinh = null;
        int tongSlgSpDaBan = 0, tongSlgHD = 0;
        Float tongTienDaBan = (float) 0;
        try {
            ngaySinh = dateFormat.parse(ngaySXtxt);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
        ngaySX = new java.sql.Date(ngaySinh.getTime());
        int i = 1;
        FrmTKTheoNV.model.setRowCount(0);
        if (tenNV.equalsIgnoreCase("Tất cả")) {
            for (TKTheoNV tk : tkTheoNVDAO.thongKeNVtheongayLap(ngaySX)) {
                String row[] = new String[]{
                        String.valueOf(i++), tk.getMaHD(), tk.getTenNV(), tk.getCaLamViec(), tk.getNgayLap(),
                        String.valueOf(tk.getTongTien()), String.valueOf(tk.getSoLuong())
                };
                tongSlgSpDaBan += tk.getSoLuong();
                tongTienDaBan += tk.getTongTien();
                tongSlgHD += 1;
                FrmTKTheoNV.model.addRow(row);
            }
            FrmTKTheoNV.model.fireTableDataChanged();
            if(FrmTKTheoNV.model.getRowCount()==0){
                JOptionPane.showMessageDialog(null,"Hóa đơn trống!");
            }
        } else {
            for (TKTheoNV tk : tkTheoNVDAO.thongKeNVtheoMaNV_ngayLap(maNV, ngaySX)) {
                String row[] = new String[]{
                        String.valueOf(i++), tk.getMaHD(), tk.getTenNV(), tk.getCaLamViec(), tk.getNgayLap(),
                        String.valueOf(tk.getTongTien()), String.valueOf(tk.getSoLuong())
                };
                FrmTKTheoNV.model.addRow(row);
                tongSlgSpDaBan += tk.getSoLuong();
                tongTienDaBan += tk.getTongTien();
                tongSlgHD += 1;
            }
            FrmTKTheoNV.model.fireTableDataChanged();
            if(FrmTKTheoNV.model.getRowCount()==0){
                JOptionPane.showMessageDialog(null,"Hóa đơn trống!");
            }
        }
        FrmTKTheoNV.tfTongSlgHoaDon.setText(String.valueOf(tongSlgHD));
        FrmTKTheoNV.tfTongTienSPDaBan.setText(String.valueOf(tongTienDaBan));
        FrmTKTheoNV.tfTongSlgSPDaBan.setText(String.valueOf(tongSlgSpDaBan));
    }

}
