package Management.GUI;

import Management.DAO.ChiTietHDDAO;
import Management.DAO.DatabaseHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MoTaSPDialog extends JDialog implements ActionListener {

    private DefaultComboBoxModel model = new DefaultComboBoxModel<>();
    private ChiTietHDDAO chiTietHDDAO = new ChiTietHDDAO();

    public static JTextField tfMaSP;
    public static JTextField tfMoTa;
    public static JTextField tfTenSP;
    public static JTextField tfNSX;
    public static JTextField tfGia;
    public static JTextField tfNgaySX;
    private Float TongTien = (float) 0;
    private JComboBox<Object> cbxSlg;
    private JButton btnThoat;
    private JButton btnLuu;


    public MoTaSPDialog() {

        initGUI();
        action();

    }

    public void initGUI() {
        this.setTitle("Sản phẩm");
        this.setSize(500, 800);
        this.setResizable(false);
        this.setLocationRelativeTo(null);


        Font fontText = new Font("Consolas", Font.BOLD, 25);


        JPanel pn2 = new JPanel(new GridLayout(3, 1));
        pn2.setBackground(new Color(211, 225, 237));
        JPanel pnMaSP = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnMaSP.setBackground(new Color(211, 225, 237));

        JLabel lbmaSP = new JLabel("MÃ SẢN PHẨM   :");
        lbmaSP.setFont(fontText);
        tfMaSP = new JTextField(30);
        tfMaSP.setFont(fontText);
        tfMaSP.setEditable(false);
        pnMaSP.add(lbmaSP);
        pnMaSP.add(tfMaSP);

        JPanel pnTenSP = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnTenSP.setBackground(new Color(211, 225, 237));

        JLabel lbTenSP = new JLabel("TÊN SẢN PHẨM  :");
        lbTenSP.setFont(fontText);
        tfTenSP = new JTextField(30);
        tfTenSP.setFont(fontText);
        tfTenSP.setEditable(false);
        pnTenSP.add(lbTenSP);
        pnTenSP.add(tfTenSP);

        JPanel pnMoTa = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnMoTa.setBackground(new Color(211, 225, 237));

        JLabel lbMoTa = new JLabel("MÔ TẢ CHI TIẾT:");
        lbMoTa.setFont(fontText);
        tfMoTa = new JTextField(30);
        tfMoTa.setFont(fontText);
        tfMoTa.setEditable(false);
        pnMoTa.add(lbMoTa);
        pnMoTa.add(tfMoTa);

        pn2.add(pnMaSP);
        pn2.add(pnTenSP);
        pn2.add(pnMoTa);

        JPanel pn3 = new JPanel(new GridLayout(3, 1));
        pn3.setBackground(new Color(211, 225, 237));

        JPanel pnGia = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnGia.setBackground(new Color(211, 225, 237));

        JLabel lbGia = new JLabel("GIÁ           :");
        lbGia.setFont(fontText);
        tfGia = new JTextField(30);
        tfGia.setFont(fontText);
        tfGia.setEditable(false);
        pnGia.add(lbGia);
        pnGia.add(tfGia);

        JPanel pnNSX = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnNSX.setBackground(new Color(211, 225, 237));

        JLabel lbNSX = new JLabel("NHÀ SẢN XUẤT  :");
        lbNSX.setFont(fontText);
        tfNSX = new JTextField(30);
        tfNSX.setFont(fontText);
        tfNSX.setEditable(false);
        pnNSX.add(lbNSX);
        pnNSX.add(tfNSX);

        JPanel pnNgaySX = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnNgaySX.setBackground(new Color(211, 225, 237));

        JLabel lbNgaySX = new JLabel("NGÀY SẢN XUẤT :");
        lbNgaySX.setFont(fontText);
        tfNgaySX = new JTextField(30);
        tfNgaySX.setFont(fontText);
        tfNgaySX.setEditable(false);
        pnNgaySX.add(lbNgaySX);
        pnNgaySX.add(tfNgaySX);

        pn3.add(pnGia);
        pn3.add(pnNSX);
        pn3.add(pnNgaySX);

        JPanel main = new JPanel(new GridLayout(2, 1));
        main.setBackground(new Color(211, 225, 237));
        main.add(pn2);
        main.add(pn3);

        JPanel pnSlg = new JPanel(new FlowLayout());

        JLabel lbSlg = new JLabel("SỐ LƯỢNG");
        lbSlg.setFont(fontText);
        cbxSlg = new JComboBox<>();
        cbxSlg.setFont(fontText);
        for (int i = 1; i < 100; i++) {
            model.addElement(i);
        }
        cbxSlg.setModel(model);
        btnThoat = new JButton("THOÁT");
        btnThoat.setFont(fontText);
        btnThoat.setForeground(Color.white);
        btnThoat.setBackground(new Color(135, 172, 203));
        btnLuu = new JButton("LƯU");
        btnLuu.setFont(fontText);
        btnLuu.setForeground(Color.white);
        btnLuu.setBackground(new Color(135, 172, 203));

        pnSlg.add(lbSlg);
        pnSlg.add(cbxSlg);
        pnSlg.add(btnLuu);
        pnSlg.add(btnThoat);


        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(main, BorderLayout.CENTER);
        getContentPane().add(pnSlg, BorderLayout.SOUTH);

    }

    public void action() {
        btnThoat.addActionListener(this);
        btnLuu.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj.equals(btnThoat)) {
            dispose();
        } else if (obj.equals(btnLuu)) {
            xuLyLuu();
        }
    }

    public void xuLyLuu() {
        String maSP = tfMaSP.getText();
        String tenSP = tfTenSP.getText();
        int soLuong = Integer.parseInt(model.getSelectedItem().toString());
        if (checkSlg(tenSP, soLuong)) {
            int rowCount = FrmBanHang.model.getRowCount();
            if (rowCount > 0) {
                int count = 0;
                for (int i = 0; i < rowCount; i++) {
                    String value = (String) FrmBanHang.model.getValueAt(i, 1);
                    String s = (String) FrmBanHang.model.getValueAt(i, 3);
                    if (value.toString().equals(tenSP)) {
                        if (soLuong + chiTietHDDAO.soLuongSPDaBan(maSP) + Integer.parseInt(s) > chiTietHDDAO.soLuongNhap(maSP)) {
                            JOptionPane.showMessageDialog(null, "Số Lượng SP Cần Mua Vượt Giới Hạn Trong Kho!");
                        } else {
                            FrmBanHang.model.removeRow(i);
                            loadDataSPvaoGioHang(tenSP, Integer.parseInt(s) + (int) soLuong);
                            FrmBanHang.tfTongTien.setText(String.valueOf(tinhTongTien()));
                            return;

                        }
                        dispose();
                    } else {
                        count++;
                    }
                    dispose();
                    if (count == rowCount) loadDataSPvaoGioHang(tenSP, soLuong);
                    FrmBanHang.tfTongTien.setText(String.valueOf(tinhTongTien()));
                    dispose();
                }
                dispose();
            } else loadDataSPvaoGioHang(tenSP, soLuong);
            FrmBanHang.tfTongTien.setText(String.valueOf(tinhTongTien()));
            dispose();
        } else JOptionPane.showMessageDialog(btnLuu, "Sản phẩm đã hết hàng!");

    }

    public boolean checkSlg(String tenSP, int soLuong) {
        String maSP = chiTietHDDAO.getMaSPbyTen(tenSP);
        int soLgSPDaBan = chiTietHDDAO.soLuongSPDaBan(maSP);
        int soLgSPNhap = chiTietHDDAO.soLuongNhap(maSP);
        if (soLuong + soLgSPDaBan <= soLgSPNhap) return true;
        else return false;
    }

    public void loadDataSPvaoGioHang(String tenSP, int soLuong) {

        try {
            Connection conn = DatabaseHelper.getConnection();
            String sql = "select * from linhkienmt where tensp=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tenSP);
            ResultSet rs = pstmt.executeQuery();
            int row = FrmBanHang.model.getRowCount() + 1;

            if (rs.next()) {
                Float tongTien = soLuong * rs.getFloat("gia");
                String[] rows = new String[]{
                        String.valueOf(row++), rs.getString("tensp"), String.valueOf(rs.getFloat("gia")),
                        String.valueOf(soLuong), String.valueOf(tongTien)
                };
                FrmBanHang.model.addRow(rows);

            }
            FrmBanHang.model.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Float tinhTongTien() {
        Float tongTien = 0.0F;
        int row = FrmBanHang.tbGioHang.getRowCount();
        for (int i = 0; i < row; i++) {
            tongTien += Float.parseFloat((String) FrmBanHang.tbGioHang.getValueAt(i, 4));
        }
        return tongTien;
    }


}