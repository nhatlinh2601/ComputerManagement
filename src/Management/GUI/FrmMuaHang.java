
package Management.GUI;

import Management.DAO.ChiTietHDDAO;
import Management.DAO.HoaDonDAO;
import Management.DAO.SanPhamLKDAO;
import Management.DTO.ChiTietHD;
import Management.DTO.HoaDon;
import Management.DTO.KhachHang;
import Management.DTO.NhanVien;
import Management.TEST.RandomStringExmple;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;


public class FrmMuaHang extends JDialog implements ActionListener {


    private RandomStringExmple rand = new RandomStringExmple();

    private DefaultComboBoxModel model = new DefaultComboBoxModel<>();
    private ChiTietHDDAO chiTietHDDAO = new ChiTietHDDAO();
    private SanPhamLKDAO sanPhamLKDAO = new SanPhamLKDAO();
    private JTextField tftenKH;
    private JTextField tfDiaChi;
    private JTextField tfSDT;
    private JTextField tfGioiTinh;

    private JTextField tfNhanVien;
    private JDateChooser birthday;
    private ButtonGroup gender;
    private JRadioButton rdoNam;
    private JRadioButton rdoNu;
    private JComboBox<Object> cbxNhanVien;
    private HoaDonDAO hoaDonDAO = new HoaDonDAO();
    private DefaultComboBoxModel modelNV = new DefaultComboBoxModel<>();
    public static JTextField tfTongTienSP;
    private JButton btnThanhToan;
    private JButton btnThoat;


    public FrmMuaHang() {

        initGUI();
        setVisible(true);
        action();

    }

    public void initGUI() {
        this.setTitle("Mua hàng");
        this.setSize(600, 550);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        Font fontIntro = new Font("Monospaced", Font.BOLD, 32);
        JPanel pnIntro = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lbIntro = new JLabel("ĐĂNG KÝ MUA HÀNG");
        lbIntro.setForeground(Color.white);
        lbIntro.setFont(fontIntro);
        pnIntro.add(lbIntro);
        pnIntro.setBackground(new Color(135, 172, 203));


        Font fontText = new Font("Consolas", Font.BOLD, 25);

        JPanel main = new JPanel(new GridLayout(7, 1, 10, 10));
        main.setBackground(new Color(211, 225, 237));


        JPanel pnTenKhachHang = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnTenKhachHang.setBackground(new Color(211, 225, 237));
        JLabel lbTenKH = new JLabel("TÊN KHÁCH HÀNG   :");
        lbTenKH.setFont(fontText);
        tftenKH = new JTextField(20);
        tftenKH.setFont(fontText);
        pnTenKhachHang.add(lbTenKH);
        pnTenKhachHang.add(tftenKH);

        JPanel pnDiaChi = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnDiaChi.setBackground(new Color(211, 225, 237));
        JLabel lbDiaChi = new JLabel("ĐỊA CHỈ          :");
        lbDiaChi.setFont(fontText);
        tfDiaChi = new JTextField(20);
        tfDiaChi.setFont(fontText);
        pnDiaChi.add(lbDiaChi);
        pnDiaChi.add(tfDiaChi);

        JPanel pnSDT = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnSDT.setBackground(new Color(211, 225, 237));
        JLabel lbSDT = new JLabel("SỐ ĐIỆN THOẠI    :");
        lbSDT.setFont(fontText);
        tfSDT = new JTextField(20);
        tfSDT.setFont(fontText);
        pnSDT.add(lbSDT);
        pnSDT.add(tfSDT);


        JPanel pnGioiTinh = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnGioiTinh.setBackground(new Color(211, 225, 237));

        JLabel lbGioiTinh = new JLabel("GIỚI TÍNH        :");
        lbGioiTinh.setFont(fontText);

        gender = new ButtonGroup();
        rdoNam = new JRadioButton("Nam");
        rdoNam.setFont(fontText);
        rdoNam.setBackground(new Color(135, 172, 203));
        rdoNam.setForeground(new Color(238, 238, 238));

        rdoNu = new JRadioButton("Nữ");
        rdoNu.setBackground(new Color(135, 172, 203));
        rdoNu.setForeground(new Color(238, 238, 238));
        rdoNu.setFont(fontText);
        gender.add(rdoNam);
        gender.add(rdoNu);

        pnGioiTinh.add(lbGioiTinh);
        pnGioiTinh.add(rdoNam);
        pnGioiTinh.add(rdoNu);

        JPanel pnNgaySinh = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnNgaySinh.setBackground(new Color(211, 225, 237));

        JLabel lbNgaySinh = new JLabel("NGÀY SINH        :");
        lbNgaySinh.setFont(fontText);

        birthday = new JDateChooser();
        birthday.setFont(fontText);
        birthday.setDate(new Date(System.currentTimeMillis()));
        birthday.setDateFormatString("dd-MM-yyyy");
        birthday.setPreferredSize(new Dimension(100, 30));

        pnNgaySinh.add(lbNgaySinh);
        pnNgaySinh.add(birthday);

        JPanel pnNhanVien = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnNhanVien.setBackground(new Color(211, 225, 237));
        JLabel lbNhanVien = new JLabel("NHÂN VIÊN TƯ VẤN :");
        lbNhanVien.setFont(fontText);

        cbxNhanVien = new JComboBox<>();
        cbxNhanVien.setFont(fontText);
        loadCbxTenNV();

        pnNhanVien.add(lbNhanVien);
        pnNhanVien.add(cbxNhanVien);

        JPanel pnTongTienSP = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnTongTienSP.setBackground(new Color(211, 225, 237));
        JLabel lbTongTienSP = new JLabel("TỔNG TIỀN SP     :");
        lbTongTienSP.setFont(fontText);
        tfTongTienSP = new JTextField(20);
        tfTongTienSP.setFont(fontText);
        tfTongTienSP.setEditable(false);
        pnTongTienSP.add(lbTongTienSP);
        pnTongTienSP.add(tfTongTienSP);


        JPanel pnSlg = new JPanel(new FlowLayout());

        btnThoat = new JButton("THOÁT");
        btnThoat.setFont(fontText);
        btnThoat.setForeground(Color.white);
        btnThoat.setBackground(new Color(135, 172, 203));
        btnThanhToan = new JButton("THANH TOÁN");
        btnThanhToan.setFont(fontText);
        btnThanhToan.setForeground(Color.white);
        btnThanhToan.setBackground(new Color(135, 172, 203));

        pnSlg.add(btnThanhToan);
        pnSlg.add(btnThoat);


        main.add(pnTenKhachHang);
        main.add(pnSDT);
        main.add(pnDiaChi);
        main.add(pnGioiTinh);
        main.add(pnNgaySinh);
        main.add(pnNhanVien);
        main.add(pnTongTienSP);


        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(main, BorderLayout.CENTER);
        getContentPane().add(pnSlg, BorderLayout.SOUTH);
        getContentPane().add(pnIntro, BorderLayout.NORTH);
    }

    public void action() {
        btnThoat.addActionListener(this);
        btnThanhToan.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj.equals(btnThoat)) {
            dispose();
        } else if (obj.equals(btnThanhToan)) {
            try {
                xuLyThemHoaDon();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }


    public void loadCbxTenNV() {
        cbxNhanVien.removeAllItems();
        try {
            for (NhanVien nhanVien : hoaDonDAO.loadCbxTenNV()) {
                String tenNV = nhanVien.getTenNV();
                modelNV.addElement(tenNV);
                cbxNhanVien.setModel(modelNV);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void xuLyThemHoaDon() throws Exception {
        StringBuilder errors = new StringBuilder();
        String gioiTinh;
        String maHD = rand.randomAlphaNumeric(6);
        String tenKH = tftenKH.getText().trim();
        String diaChi = tfDiaChi.getText().trim();
        String phone = tfSDT.getText().trim();
        if (rdoNam.isSelected()) {
            gioiTinh = "Nam";
        } else gioiTinh = "Nữ";
        java.sql.Date ngaySinhsql = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String ngaySinhtxt = dateFormat.format(birthday.getDate());
        java.util.Date ngaySinh = dateFormat.parse(ngaySinhtxt);
        ngaySinhsql = new java.sql.Date(ngaySinh.getTime());
        StringBuilder errorss = new StringBuilder();
        JDateChooser ngayLap = new JDateChooser();
        ngayLap.setDate(new Date(System.currentTimeMillis()));
        ngayLap.setDateFormatString("dd-MM-yyyy");
        java.sql.Date ngaySinhsql2 = null;
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        String ngaySinhtxt2 = dateFormat2.format(ngayLap.getDate());
        java.util.Date ngaySinh2 = dateFormat2.parse(ngaySinhtxt2);
        ngaySinhsql2 = new java.sql.Date(ngaySinh2.getTime());

        if (maHD.equalsIgnoreCase("")) {
            errorss.append("Mã Hóa đơn không được để trống\n");
        }
        if (tenKH.equalsIgnoreCase("")) {
            errorss.append("Họ tên không được để trống\n");
        } else if (tenKH.length() > 40) {
            errorss.append("Họ tên không hợp lệ!!\n");
        }
        if (diaChi.equalsIgnoreCase("")) {
            errorss.append("Địa chỉ không được để trống\n");
        }
        if (gioiTinh.equalsIgnoreCase("")) {
            errorss.append("Giới tính không được để trống\n");
        }
        if (phone.equalsIgnoreCase("")) {
            errorss.append("SDT không được để trống\n");
        } else if (!phone.matches("^0[0-9]{9}$")) {
            errorss.append("SDT không hợp lệ!\n");
        }
        int years = Integer.parseInt(ngaySinhtxt.substring(0, 4));

        if (LocalDate.now().getYear() - years > 120 || LocalDate.now().getYear() - years < 18) {
            errorss.append("Ngày sinh không hợp lệ!\n");
        }
        if (errorss.isEmpty()) {
            if (hoaDonDAO.addHoaDon(new HoaDon(maHD, ngaySinhsql2, Double.valueOf(tfTongTienSP.getText()),
                    hoaDonDAO.getMaNVbyTen(cbxNhanVien.getSelectedItem().toString())))) {
                if (hoaDonDAO.addKhachHang(new KhachHang(tenKH, phone, diaChi, maHD, gioiTinh, ngaySinhsql))) {
                    JOptionPane.showMessageDialog(this, "Thanh toán thành công");
                    int row = FrmBanHang.tbGioHang.getRowCount();
                    if (FrmBanHang.tbGioHang.getRowCount() > 0) {
                        for (int i = 0; i < row; i++) {
                            String tenSP = FrmBanHang.model.getValueAt(i, 1).toString();
                            String maSP = chiTietHDDAO.getMaSPbyTen(tenSP);
                            String dvt = sanPhamLKDAO.getDonViTinhByTenSP(tenSP);
                            Float donGia = Float.parseFloat(FrmBanHang.model.getValueAt(i, 2).toString());
                            int soLuong = Integer.parseInt(FrmBanHang.model.getValueAt(i, 3).toString());
                            ChiTietHD chiTietHD = new ChiTietHD(maHD, maSP, dvt, donGia, soLuong);
                            chiTietHDDAO.insertCTHD(chiTietHD);
                            dispose();
                        }
                    }
                    FrmXuatHD frmXuatHD=new FrmXuatHD();
                    frmXuatHD.setVisible(true);
                    String nguoiBan=modelNV.getSelectedItem().toString();
                    FrmXuatHD.lblLoaiHD1.setText(maHD);
                    FrmXuatHD.lblTenKH1.setText(tenKH);
                    FrmXuatHD.lblGTinh1.setText(gioiTinh);
                    FrmXuatHD.lblsdtkh1.setText(phone);
                    FrmXuatHD.lblNamSinh1.setText(String.valueOf(ngaySinhsql));
                    FrmXuatHD.lblNguoiBan.setText(nguoiBan);
                    FrmXuatHD.lblNguoiMuaHang1.setText(tenKH);
                    int slg=0;
                    Float tongTien= (float) 0;
                    int j=1;
                    if (FrmBanHang.tbGioHang.getRowCount() > 0) {
                        for (int i = 0; i < row; i++) {
                            String tenSP = FrmBanHang.model.getValueAt(i, 1).toString();
                            Float donGia = Float.parseFloat(FrmBanHang.model.getValueAt(i, 2).toString());
                            int soLuong = Integer.parseInt(FrmBanHang.model.getValueAt(i, 3).toString());
                            Float thanhTien= Float.parseFloat( FrmBanHang.model.getValueAt(i,4).toString());
                            String [] rows=new String[]{
                                    String.valueOf(j++),tenSP, String.valueOf(donGia), String.valueOf(soLuong),
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
                    lamRong();

                } else JOptionPane.showMessageDialog(this, "Thanh toán thành công");
            } else JOptionPane.showMessageDialog(this, "Thêm HD thất bại");
        } else JOptionPane.showMessageDialog(this, errorss.toString());
    }

    public void lamRong() {
        FrmBanHang.model.setRowCount(0);
        FrmBanHang.tfTongTien.setText("0.0");
    }

    public void xuLyXuatHD() throws Exception {


    }

}