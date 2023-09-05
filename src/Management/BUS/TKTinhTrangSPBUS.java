package Management.BUS;

import Management.DAO.TKTinhTrangSPDAO;
import Management.DTO.TKTinhTrangSP;
import Management.GUI.FrmTKTinhTrangSP;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TKTinhTrangSPBUS {

    private TKTinhTrangSPDAO tkTinhTrangSPDAO=new TKTinhTrangSPDAO();

    public void xemSPDaBan(){
        if (FrmTKTinhTrangSP.rdoNgay.isSelected() || FrmTKTinhTrangSP.rdoThang.isSelected() || FrmTKTinhTrangSP.rdoNam.isSelected()) {
            if (FrmTKTinhTrangSP.rdoNgay.isSelected()) {

                java.sql.Date ngaySinhsql = null;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String ngaySinhtxt = dateFormat.format(FrmTKTinhTrangSP.date.getDate());
                Date ngaySinh = null;
                try {
                    ngaySinh = dateFormat.parse(ngaySinhtxt);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                ngaySinhsql = new java.sql.Date(ngaySinh.getTime());
                FrmTKTinhTrangSP.model.setRowCount(0);
                int i = 1, tongSlg = 0;
                for (TKTinhTrangSP sp : tkTinhTrangSPDAO.slgSPSaBanTheoNgay(ngaySinhsql)) {
                    String[] rows = new String[]{
                            String.valueOf(i++), sp.getMaSP(), sp.getTenSP(), String.valueOf(sp.getSoLuong()), sp.getNsx(),
                            String.valueOf(sp.getDonGia())
                    };
                    FrmTKTinhTrangSP.model.addRow(rows);
                    FrmTKTinhTrangSP.model.fireTableDataChanged();
                    tongSlg += sp.getSoLuong();
                }
                FrmTKTinhTrangSP.tfTongSlgSP.setText(String.valueOf(tongSlg));
                if( FrmTKTinhTrangSP.model.getRowCount()==0){
                    JOptionPane.showMessageDialog(null,"Danh sách sản phẩm trống");
                }
            }

            if (FrmTKTinhTrangSP.rdoThang.isSelected()) {

                java.sql.Date ngaySinhsql = null;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String ngaySinhtxt = dateFormat.format(FrmTKTinhTrangSP.date.getDate());
                Date ngaySinh = null;
                try {
                    ngaySinh = dateFormat.parse(ngaySinhtxt);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                ngaySinhsql = new java.sql.Date(ngaySinh.getTime());
                int thang = Integer.parseInt(ngaySinhsql.toString().substring(5, 7));
                FrmTKTinhTrangSP.model.setRowCount(0);
                int i = 1, tongSlg2 = 0;
                for (TKTinhTrangSP sp : tkTinhTrangSPDAO.slgSPSaBanTheoThang(thang)) {
                    String[] rows = new String[]{
                            String.valueOf(i++), sp.getMaSP(), sp.getTenSP(), String.valueOf(sp.getSoLuong()), sp.getNsx(),
                            String.valueOf(sp.getDonGia())
                    };
                    FrmTKTinhTrangSP.model.addRow(rows);
                    FrmTKTinhTrangSP.model.fireTableDataChanged();
                    tongSlg2 += sp.getSoLuong();
                }
                FrmTKTinhTrangSP.tfTongSlgSP.setText(String.valueOf(tongSlg2));
                if( FrmTKTinhTrangSP.model.getRowCount()==0){
                    JOptionPane.showMessageDialog(null,"Danh sách sản phẩm trống");
                }
            }

            if (FrmTKTinhTrangSP.rdoNam.isSelected()) {

                java.sql.Date ngaySinhsql = null;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String ngaySinhtxt = dateFormat.format(FrmTKTinhTrangSP.date.getDate());
                Date ngaySinh = null;
                try {
                    ngaySinh = dateFormat.parse(ngaySinhtxt);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                ngaySinhsql = new java.sql.Date(ngaySinh.getTime());
                int nam = Integer.parseInt(ngaySinhsql.toString().substring(0, 4));
                FrmTKTinhTrangSP.model.setRowCount(0);
                int i = 1, tongSlg3 = 0;
                for (TKTinhTrangSP sp : tkTinhTrangSPDAO.slgSPSaBanTheoNam(nam)) {
                    String[] rows = new String[]{
                            String.valueOf(i++), sp.getMaSP(), sp.getTenSP(), String.valueOf(sp.getSoLuong()), sp.getNsx(),
                            String.valueOf(sp.getDonGia())
                    };
                    FrmTKTinhTrangSP.model.addRow(rows);
                    FrmTKTinhTrangSP.model.fireTableDataChanged();
                    tongSlg3 += sp.getSoLuong();
                }
                FrmTKTinhTrangSP.tfTongSlgSP.setText(String.valueOf(tongSlg3));
                if( FrmTKTinhTrangSP.model.getRowCount()==0){
                    JOptionPane.showMessageDialog(null,"Danh sách sản phẩm trống");
                }
            }
        } else JOptionPane.showMessageDialog(null, "Lựa chọn thống kê theo ngày? tháng? năm? chưa được chọn!");

    }

    public void xemSPBanChay(){
        int sl = 0, i = 1;
        FrmTKTinhTrangSP.model.setRowCount(0);

        for (TKTinhTrangSP sp : tkTinhTrangSPDAO.spBanChay()) {
            String[] rows = new String[]{
                    String.valueOf(i++), sp.getMaSP(), sp.getTenSP(), String.valueOf(sp.getSoLuong()), sp.getNsx(),
                    String.valueOf(sp.getDonGia())
            };
            FrmTKTinhTrangSP.model.addRow(rows);
            FrmTKTinhTrangSP.model.fireTableDataChanged();
            sl += sp.getSoLuong();

        }
        FrmTKTinhTrangSP.tfTongSlgSP.setText(String.valueOf(sl));
        if( FrmTKTinhTrangSP.model.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Danh sách sản phẩm trống");
        }

    }

    public void xemSPConLai(){
        int i = 1, slg = 0;
        FrmTKTinhTrangSP.model.setRowCount(0);
        for (TKTinhTrangSP sp : tkTinhTrangSPDAO.slgSPConTrongKho()) {
            if (sp.getSoLuong() == 0) {

            } else {
                String[] rows = new String[]{
                        String.valueOf(i++), sp.getMaSP(), sp.getTenSP(),
                        String.valueOf(sp.getSoLuong()),
                        sp.getNsx(), String.valueOf(sp.getDonGia())
                };
                FrmTKTinhTrangSP.model.addRow(rows);
                FrmTKTinhTrangSP.model.fireTableDataChanged();
                slg += sp.getSoLuong();
            }

        }
        FrmTKTinhTrangSP.tfTongSlgSP.setText(String.valueOf(slg));
        if( FrmTKTinhTrangSP.model.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Danh sách sản phẩm trống");
        }
    }
}
