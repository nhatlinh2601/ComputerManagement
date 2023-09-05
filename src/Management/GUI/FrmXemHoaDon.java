package Management.GUI;
import Management.DAO.*;
import Management.DTO.ChiTietHD;
import Management.DTO.HoaDon;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static Management.GUI.MainForm.frmXemHoaDon;

public class FrmXemHoaDon extends JPanel implements ActionListener, MouseListener {

    private JTextField tfNhapTimKiem;
    private JButton btnFindHD;
    private JButton btnNewFind;
    private JRadioButton btnTimKiemByMaHD;
    private JRadioButton btnTimKiemByNgayLap;
    private JRadioButton btnTimKiemBytenNV;
    private JButton btnClose;
    private JTextField tfNhanVien;
    private JTextField tfMaHD;
    private JTextField tfNgayLap;
    private JTextField tfKhachHang;
    private JTextField tfTongTien;
    private ChiTietHDDAO chiTietHDDAO = new ChiTietHDDAO();
    private XemHoaDonDAO xemHoaDonDAO = new XemHoaDonDAO();


    private String[] headerDanhSachSHD = new String[]{
            "STT", "Mã Hóa Đơn", "Ngày lập", "Tổng tiền", "Tên Khách Hàng", "Tên Nhân Viên"
    };

    private String[] headerChiTietHD = new String[]{
            "STT", "Tên sản phẩm", "Đơn vị tính", "Đơn giá", "Số lượng", "Thành tiền"
    };
    private DefaultTableModel modelChiTiet = new DefaultTableModel();


    private DefaultTableModel modelDanhSach = new DefaultTableModel();
    private JTable tbChiTietHD;
    private JTable tbDanhSachHD;
    private ArrayList<HoaDon> hoaDonList;
    private KhachHangDAO khachHangDAO = new KhachHangDAO();
    private HoaDonDAO hoaDonDAO = new HoaDonDAO();
    public static FrmThongTin frmThongTin = new FrmThongTin();
    private DefaultComboBoxModel sortModel = new DefaultComboBoxModel();
    private JButton btnSort;


    public FrmXemHoaDon() {
        initGUI();
        setVisible(true);
        action();
    }


    public void initGUI() {
        Font fontIntro = new Font("Monospaced", Font.BOLD, 32);
        Font fontTbl = new Font("SF Mono", Font.PLAIN, 16);
        Font fontText = new Font("SF Mono", Font.BOLD, 18);
        Font fontTf = new Font("Lato", Font.PLAIN, 18);
        JPanel pnLbIntro = new JPanel();
        pnLbIntro.setBackground(new Color(135, 172, 203));
        JLabel lbIntro = new JLabel("THÔNG TIN HÓA ĐƠN", SwingConstants.CENTER);
        lbIntro.setFont(fontIntro);
        lbIntro.setForeground(Color.white);
        pnLbIntro.add(lbIntro);

        JPanel pnThongTinHoaDon = new JPanel(new GridLayout(5, 1, 10, 10));
        pnThongTinHoaDon.setBackground(new Color(211, 225, 237));

        JPanel pn1 = new JPanel(new FlowLayout());
        pn1.setBackground(new Color(211, 225, 237));
        JLabel lbMaHD = new JLabel("Mã Hóa Đơn:");
        lbMaHD.setFont(fontText);
        tfMaHD = new JTextField(30);
        tfMaHD.setFont(fontTf);
        tfMaHD.setEditable(false);
        pn1.add(lbMaHD);
        pn1.add(tfMaHD);

        JPanel pn2 = new JPanel(new FlowLayout());
        pn2.setBackground(new Color(211, 225, 237));
        JLabel lbNgayLap = new JLabel("Ngày Lập     :");
        lbNgayLap.setFont(fontText);
        tfNgayLap = new JTextField(30);
        tfNgayLap.setFont(fontTf);
        tfNgayLap.setEditable(false);
        pn2.add(lbNgayLap);
        pn2.add(tfNgayLap);

        JPanel pn3 = new JPanel(new FlowLayout());
        pn3.setBackground(new Color(211, 225, 237));
        JLabel lbNhanVien = new JLabel("Nhân Viên    :");
        lbNhanVien.setFont(fontText);
        tfNhanVien = new JTextField(30);
        tfNhanVien.setFont(fontTf);
        tfNhanVien.setEditable(false);
        pn3.add(lbNhanVien);
        pn3.add(tfNhanVien);

        JPanel pn4 = new JPanel(new FlowLayout());
        pn4.setBackground(new Color(211, 225, 237));
        JLabel lbKhachHang = new JLabel("Khách hàng :");
        lbKhachHang.setFont(fontText);
        tfKhachHang = new JTextField(30);
        tfKhachHang.setFont(fontTf);
        tfKhachHang.setEditable(false);
        pn4.add(lbKhachHang);
        pn4.add(tfKhachHang);

        JPanel pn5 = new JPanel(new FlowLayout());
        pn5.setBackground(new Color(211, 225, 237));
        JLabel lbTongTien = new JLabel("Tổng tiền     :");
        lbTongTien.setFont(fontText);
        tfTongTien = new JTextField(30);
        tfTongTien.setFont(fontTf);
        tfTongTien.setEditable(false);
        pn5.add(lbTongTien);
        pn5.add(tfTongTien);

        pnThongTinHoaDon.add(pn1);
        pnThongTinHoaDon.add(pn2);
        pnThongTinHoaDon.add(pn3);
        pnThongTinHoaDon.add(pn4);
        pnThongTinHoaDon.add(pn5);

        pnThongTinHoaDon.setBorder(BorderFactory.createTitledBorder("Thông tin hóa đơn"));

        tbChiTietHD = new JTable();
        tbChiTietHD.setFont(fontTbl);
        tbChiTietHD.setBackground(new Color(211, 225, 237));
        JScrollPane scrollPane = new JScrollPane(tbChiTietHD);
        scrollPane.setBackground(new Color(211, 225, 237));
        modelChiTiet.setColumnIdentifiers(headerChiTietHD);
        tbChiTietHD.setModel(modelChiTiet);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Thông tin chi tiết hóa đơn"));

        JPanel headerCenterpn = new JPanel(new GridLayout(1, 2, 10, 10));
        headerCenterpn.setBackground(new Color(211, 225, 237));
        headerCenterpn.add(pnThongTinHoaDon);
        headerCenterpn.add(scrollPane);
        headerCenterpn.setBorder(BorderFactory.createEtchedBorder());

        JPanel pnMainHeader = new JPanel(new BorderLayout(20, 20));
        pnMainHeader.setBackground(new Color(211, 225, 237));
        pnMainHeader.add(pnLbIntro, BorderLayout.NORTH);
        pnMainHeader.add(headerCenterpn, BorderLayout.CENTER);


        tbDanhSachHD = new JTable();
        tbDanhSachHD.setFont(fontTbl);
        tbDanhSachHD.setBackground(new Color(211, 225, 237));
        JScrollPane scp = new JScrollPane(tbDanhSachHD);
        scp.setBackground(new Color(211, 225, 237));
        scp.setBorder(BorderFactory.createTitledBorder("Danh sách hóa đơn"));
        modelDanhSach.setColumnIdentifiers(headerDanhSachSHD);
        tbDanhSachHD.setModel(modelDanhSach);
        loadDataDanhSachHoaDonTable();

        JPanel pnFooterTimKiem = new JPanel(new GridLayout(2, 1, 20, 20));
        pnFooterTimKiem.setBackground(new Color(211, 225, 237));
        pnFooterTimKiem.setBorder(BorderFactory.createTitledBorder("Tìm kiếm Hóa đơn"));


        JPanel nhapThongTinTimKiem = new JPanel(new FlowLayout());
        nhapThongTinTimKiem.setBackground(new Color(211, 225, 237));
        JLabel lbMaKH = new JLabel("Nhập thông tin tìm kiếm:");
        lbMaKH.setFont(fontText);
        tfNhapTimKiem = new JTextField(60);
        tfNhapTimKiem.setFont(fontTf);
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Image/Zoom-icon.png"));
        btnFindHD = new JButton("Tìm Kiếm", imageIcon);
        btnFindHD.setForeground(new Color(238, 238, 238));
        btnFindHD.setBackground(new Color(135, 172, 203));
        btnFindHD.setFont(fontText);
        btnNewFind = new JButton("Làm Mới");
        btnNewFind.setForeground(new Color(238, 238, 238));
        btnNewFind.setBackground(new Color(135, 172, 203));
        btnNewFind.setFont(fontText);
        btnClose = new JButton("Thoát");
        btnClose.setForeground(new Color(238, 238, 238));
        btnClose.setBackground(new Color(135, 172, 203));
        btnClose.setFont(fontText);


        nhapThongTinTimKiem.add(lbMaKH);
        nhapThongTinTimKiem.add(tfNhapTimKiem);
        nhapThongTinTimKiem.add(btnFindHD);
        nhapThongTinTimKiem.add(btnNewFind);
        nhapThongTinTimKiem.add(btnClose);


        JPanel timKiemBy = new JPanel(new FlowLayout());
        timKiemBy.setBackground(new Color(211, 225, 237));

        JLabel lbBy = new JLabel("Tim theo:");
        lbBy.setFont(fontText);
        btnTimKiemByMaHD = new JRadioButton("Mã Hóa Đơn");
        btnTimKiemByMaHD.setForeground(new Color(238, 238, 238));
        btnTimKiemByMaHD.setBackground(new Color(135, 172, 203));
        btnTimKiemByMaHD.setFont(fontText);
        btnTimKiemBytenNV = new JRadioButton("Tên Nhân viên");
        btnTimKiemBytenNV.setForeground(new Color(238, 238, 238));
        btnTimKiemBytenNV.setBackground(new Color(135, 172, 203));
        btnTimKiemBytenNV.setFont(fontText);
        btnTimKiemByNgayLap = new JRadioButton("Ngày ");
        btnTimKiemByNgayLap.setForeground(new Color(238, 238, 238));
        btnTimKiemByNgayLap.setBackground(new Color(135, 172, 203));
        btnTimKiemByNgayLap.setFont(fontText);


        ButtonGroup timKiem = new ButtonGroup();
        timKiem.add(btnTimKiemByMaHD);
        timKiem.add(btnTimKiemBytenNV);
        timKiem.add(btnTimKiemByNgayLap);

        timKiemBy.add(lbBy);
        timKiemBy.add(btnTimKiemByMaHD);
        timKiemBy.add(btnTimKiemBytenNV);
        timKiemBy.add(btnTimKiemByNgayLap);

        btnSort = new JButton("Sắp xếp");
        btnSort.setFont(fontText);
        btnSort.setForeground(new Color(238, 238, 238));
        btnSort.setBackground(new Color(135, 172, 203));

        JComboBox sort = new JComboBox<>();
        sort.setModel(sortModel);
        sort.setFont(fontText);
        sortModel.addElement("Mã hóa đơn");
        sortModel.addElement("Ngày lập");
        sortModel.addElement("Tổng tiền");

        timKiemBy.add(sort);
        timKiemBy.add(btnSort);

        pnFooterTimKiem.add(nhapThongTinTimKiem);
        pnFooterTimKiem.add(timKiemBy);

        JPanel footerCenterpn = new JPanel(new GridLayout(2, 1, 10, 10));
        footerCenterpn.setBackground(new Color(211, 225, 237));
        footerCenterpn.add(scp);
        footerCenterpn.add(pnFooterTimKiem);

        this.setLayout(new GridLayout(2, 1, 20, 20));
        this.add(pnMainHeader);
        this.add(footerCenterpn);
    }


    public void action() {
        btnFindHD.addActionListener(this);
        btnSort.addActionListener(this);
        btnClose.addActionListener(this);
        tbDanhSachHD.addMouseListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj.equals(btnFindHD)) {
            timKiemHD();
        }
        if (obj.equals(btnClose)) {
            if (JOptionPane.showConfirmDialog(btnClose, "Bạn có chắc muốn thoát !") == JOptionPane.YES_OPTION) {
                MainForm.tpMain.remove(frmXemHoaDon);
                MainForm.tpMain.addTab("Giới Thiệu", null, frmThongTin);
                MainForm.tpMain.setSelectedComponent(frmThongTin);
            }
        }
        if (obj.equals(btnSort)) {
            xuLySapXep();
        }
    }

    public void xuLySapXep() {
        if (sortModel.getSelectedItem().equals("Tổng tiền")) {
            sortHoaDonByTongTien();
        }
        if (sortModel.getSelectedItem().equals("Ngày lập")) {
            sortHoaDonByNgayLap();
        }
        if (sortModel.getSelectedItem().equals("Mã hóa đơn")) {
            sortHoaDonByMaHD();
        }
    }


    public void sortHoaDonByTongTien() {
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "select * from hoadon order by tongtien";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            hoaDonList = new ArrayList<>();
            int i = 1;
            modelDanhSach.setRowCount(0);
            while (rs.next()) {
                String[] row = new String[]{
                        String.valueOf(i++), String.valueOf(rs.getString("mahd")),
                        String.valueOf(rs.getDate("ngaylap")), rs.getString("tongtien"),
                        khachHangDAO.getTenKHbyMaHD(rs.getString("mahd")),
                        hoaDonDAO.getTenNVbyMa(rs.getString("manv"))
                };
                modelDanhSach.addRow(row);
            }
            modelDanhSach.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) try {
                conn.close();
            } catch (SQLException ignore) {
            }
        }
    }

    public void sortHoaDonByMaHD() {
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "select * from hoadon order by mahd";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            hoaDonList = new ArrayList<>();
            int i = 1;
            modelDanhSach.setRowCount(0);
            while (rs.next()) {
                String[] row = new String[]{
                        String.valueOf(i++), String.valueOf(rs.getString("mahd")),
                        String.valueOf(rs.getDate("ngaylap")), rs.getString("tongtien"),
                        khachHangDAO.getTenKHbyMaHD(rs.getString("mahd")),
                        hoaDonDAO.getTenNVbyMa(rs.getString("manv"))
                };
                modelDanhSach.addRow(row);
            }
            modelDanhSach.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) try {
                conn.close();
            } catch (SQLException ignore) {
            }
        }
    }

    public void sortHoaDonByNgayLap() {
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "select * from hoadon order by ngaylap";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            hoaDonList = new ArrayList<>();
            int i = 1;
            modelDanhSach.setRowCount(0);
            while (rs.next()) {
                String[] row = new String[]{
                        String.valueOf(i++), String.valueOf(rs.getString("mahd")),
                        String.valueOf(rs.getDate("ngaylap")), rs.getString("tongtien"),
                        khachHangDAO.getTenKHbyMaHD(rs.getString("mahd")),
                        hoaDonDAO.getTenNVbyMa(rs.getString("manv"))
                };
                modelDanhSach.addRow(row);
            }
            modelDanhSach.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) try {
                conn.close();
            } catch (SQLException ignore) {
            }
        }
    }


    public void loadDataDanhSachHoaDonTable() {
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "select * from hoadon";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            hoaDonList = new ArrayList<>();
            modelDanhSach.setRowCount(0);

            int i = 1;
            while (rs.next()) {
                String[] row = new String[]{
                        String.valueOf(i++), rs.getString("mahd"), rs.getString("ngaylap"),
                        rs.getString("tongtien"), khachHangDAO.getTenKHbyMaHD(rs.getString("mahd")),
                        hoaDonDAO.getTenNVbyMa(rs.getString("manv"))
                };
                modelDanhSach.addRow(row);
            }
            modelDanhSach.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) try {
                conn.close();
            } catch (SQLException ignore) {
            }
        }
    }

    public void timKiemHD() {

        StringBuilder errors = new StringBuilder();
        if (btnTimKiemByMaHD.isSelected() == false
                && btnTimKiemBytenNV.isSelected() == false
                && btnTimKiemByNgayLap.isSelected() == false
                && tfNhapTimKiem.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin và chọn loại tìm kiếm !",
                    "Thông báo", JOptionPane.ERROR_MESSAGE, new ImageIcon("Image/warning-icon.png"));
        } else if (tfNhapTimKiem.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin tìm kiếm !",
                    "Thông báo", JOptionPane.ERROR_MESSAGE, new ImageIcon("Image/warning-icon.png"));
        } else if (btnTimKiemBytenNV.isSelected() == false
                && btnTimKiemByMaHD.isSelected() == false
                && btnTimKiemByNgayLap.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn loại tìm kiếm !",
                    "Thông báo", JOptionPane.ERROR_MESSAGE, new ImageIcon("Image/warning-icon.png"));
        } else {
            if (btnTimKiemByMaHD.isSelected()) {
                getTimKiemHoaDonByMaHD(tfNhapTimKiem.getText());
            } else if (btnTimKiemBytenNV.isSelected()) {
                getTimKiemHoaDonByTenNV(tfNhapTimKiem.getText());
            } else if (btnTimKiemByNgayLap.isSelected()) {
                getTimKiemHoaDonByNgayLap(tfNhapTimKiem.getText());
            }
        }
    }

    public void getTimKiemHoaDonByMaHD(String maHD) {
        ArrayList<HoaDon> list = null;
        try {
            list = (ArrayList<HoaDon>) xemHoaDonDAO.findByMaHD(maHD);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        modelDanhSach = (DefaultTableModel) tbDanhSachHD.getModel();
        modelDanhSach.setRowCount(0);
        for (HoaDon hoaDon : list) {
            modelDanhSach.addRow(hoaDon.toVector());
        }
        for (int i = 0; i < modelDanhSach.getRowCount(); i++) {
            modelDanhSach.setValueAt(i + 1, i, 0);
        }
        if(  modelDanhSach.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Hóa đơn không tồn tại!");
        }
    }

    public void getTimKiemHoaDonByTenNV(String tenNV) {
        ArrayList<HoaDon> list = null;
        try {
            list = (ArrayList<HoaDon>) xemHoaDonDAO.findByTenNV(tenNV);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        modelDanhSach = (DefaultTableModel) tbDanhSachHD.getModel();
        modelDanhSach.setRowCount(0);
        for (HoaDon hoaDon : list) {
            modelDanhSach.addRow(hoaDon.toVector());
        }
        for (int i = 0; i < modelDanhSach.getRowCount(); i++) {
            modelDanhSach.setValueAt(i + 1, i, 0);
        }
        if(  modelDanhSach.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Hóa đơn không tồn tại!");
        }
    }

    public void getTimKiemHoaDonByNgayLap(String ngay) {
        ArrayList<HoaDon> list = null;
        try {
            list = (ArrayList<HoaDon>) xemHoaDonDAO.findByNgay(ngay);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        modelDanhSach = (DefaultTableModel) tbDanhSachHD.getModel();
        modelDanhSach.setRowCount(0);
        for (HoaDon hoaDon : list) {
            modelDanhSach.addRow(hoaDon.toVector());
        }
        for (int i = 0; i < modelDanhSach.getRowCount(); i++) {
            modelDanhSach.setValueAt(i + 1, i, 0);
        }
        if(  modelDanhSach.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Hóa đơn không tồn tại!");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = tbDanhSachHD.getSelectedRow();
        String maHD = modelDanhSach.getValueAt(row, 1).toString();
        String ngayLap = modelDanhSach.getValueAt(row, 2).toString();
        String tongTien = modelDanhSach.getValueAt(row, 3).toString();
        String khachHang = modelDanhSach.getValueAt(row, 4).toString();
        String nhanVien = modelDanhSach.getValueAt(row, 5).toString();

        tfMaHD.setText(maHD);
        tfNgayLap.setText(ngayLap);
        tfTongTien.setText(tongTien);
        tfNhanVien.setText(nhanVien);
        tfKhachHang.setText(khachHang);

        List<ChiTietHD> list = chiTietHDDAO.getChiTietHD(maHD);
        int i = 1;
        modelChiTiet.setRowCount(0);
        for (ChiTietHD ct : list) {
            modelChiTiet.addRow(new Object[]{
                    String.valueOf(i++), chiTietHDDAO.getTenSPbyMa(ct.getMaSP()), ct.getDonViTinh(), String.valueOf(ct.getDonGia()),
                    String.valueOf(ct.getSoLuong()), String.valueOf((ct.getDonGia() * ct.getSoLuong()))
            });
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
