package Management.GUI;

import Management.BUS.LapHoaDonBUS;
import Management.DAO.ChiTietHDDAO;
import Management.DAO.DatabaseHelper;
import Management.DAO.HoaDonDAO;
import Management.DAO.SanPhamLKDAO;
import Management.DTO.*;
import Management.TEST.RandomStringExmple;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static Management.GUI.MainForm.frmLapHoaDon;
import static Management.GUI.MainForm.frmNhanVien;

public class FrmLapHoaDon extends JPanel implements ActionListener {
    public static FrmThongTin frmThongTin = new FrmThongTin();
    private SanPhamLKDAO sanPhamLKDAO=new SanPhamLKDAO();
    private LapHoaDonBUS lapHoaDonBUS=new LapHoaDonBUS();
    public static JTextField tfIDHoaDon;

    private JTextField tfNhanVien;
    public static JTextField tfTenKH;
    public static JTextField tfDiaChi;
    public static JTextField tfSDT;
    public static JRadioButton rdoNam;
    public static JRadioButton rdoNu;
    private JComboBox cbxSoLuong;
    ButtonGroup gender;
    public static JComboBox<Object> cbTenSP;
    private JButton btnThem;
    private JButton btnThemHDMoi;
    private JButton btnThoat;
    public static DefaultComboBoxModel cbxModel = new DefaultComboBoxModel<>();

    private HoaDonDAO hoaDonDAO = new HoaDonDAO();
    private String[] header = new String[]{
            "Mã SP", " Tên sản phẩm ", "Nhà Sản Xuất", "Đơn Vị tính", "Đơn Giá", "Số lượng", "Thành Tiền"
    };
    public static DefaultTableModel tbModel = new DefaultTableModel();
    private JButton btnXoa;
    public static JDateChooser birthday;
    public static JComboBox<Object> cbxNhanVien;
    public static DefaultComboBoxModel modelNV = new DefaultComboBoxModel<>();
    public static DefaultComboBoxModel modelSL = new DefaultComboBoxModel<>();
    private LocalDate localDate;
    private Date date;
    public static JTable tbHoaDon;
    public static JTextField tfTongTienSP;
    public static JTextField tfTienNhan;
    public static JTextField tfTienThoi;
    private JButton btnLamMoi;
    private JButton btnTienThoi;
    public static JDateChooser dchooseNgaylap;

    private ChiTietHDDAO chiTietHDDAO = new ChiTietHDDAO();
    private RandomStringExmple rand;




    public FrmLapHoaDon() {

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
        JLabel lbIntro = new JLabel("LẬP HÓA ĐƠN", SwingConstants.CENTER);
        lbIntro.setForeground(Color.white);
        lbIntro.setFont(fontIntro);
        pnLbIntro.add(lbIntro);

        JPanel pnInfo1 = new JPanel(new FlowLayout());
        pnInfo1.setBackground(new Color(211, 225, 237));

        JLabel lbIDHoaDon = new JLabel("Mã Hóa Đơn:");
        lbIDHoaDon.setFont(fontText);
        tfIDHoaDon = new JTextField(15);
        tfIDHoaDon.setFont(fontTf);
        // sinh mahd ngau nhien
        rand = new RandomStringExmple();
        String maHD = rand.randomAlphaNumeric(6);
        tfIDHoaDon.setText(maHD);
        tfIDHoaDon.setEditable(false);
        JLabel lbNgay = new JLabel("Ngày: ");
        lbNgay.setFont(fontText);
        dchooseNgaylap = new JDateChooser();
        dchooseNgaylap.setFont(fontText);
        dchooseNgaylap.setDate(new Date(System.currentTimeMillis()));
        dchooseNgaylap.setDateFormatString("dd-MM-yyyy");
        JLabel lbNhanVien = new JLabel("Nhân Viên:");
        lbNhanVien.setFont(fontText);
        cbxNhanVien = new JComboBox<>();
        cbxNhanVien.setFont(fontText);
        loadCbxTenNV();
        JLabel lbTenKH = new JLabel("Tên KH:");
        lbTenKH.setFont(fontText);
        tfTenKH = new JTextField(20);
        tfTenKH.setFont(fontTf);

        pnInfo1.add(lbIDHoaDon);
        pnInfo1.add(tfIDHoaDon);
        pnInfo1.add(lbNgay);
        pnInfo1.add(dchooseNgaylap);
        pnInfo1.add(lbNhanVien);
        pnInfo1.add(cbxNhanVien);
        pnInfo1.add(lbTenKH);
        pnInfo1.add(tfTenKH);

        JPanel pnInfo2 = new JPanel(new FlowLayout());
        pnInfo2.setBackground(new Color(211, 225, 237));

        JLabel lbDiaChi = new JLabel("Địa Chỉ:");
        lbDiaChi.setFont(fontText);
        tfDiaChi = new JTextField(20);
        tfDiaChi.setFont(fontTf);
        JLabel lbSDT = new JLabel("Số Điện Thoại: ");
        lbSDT.setFont(fontText);
        tfSDT = new JTextField(20);
        tfSDT.setFont(fontTf);
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
        JLabel lbGender = new JLabel("Giới tính:");
        lbGender.setFont(fontText);
        JLabel lbBirthday = new JLabel("Ngày sinh:");
        lbBirthday.setFont(fontText);

        birthday = new JDateChooser();
        birthday.setFont(fontText);
        birthday.setDate(new Date(System.currentTimeMillis()));
        birthday.setDateFormatString("dd-MM-yyyy");
        birthday.setPreferredSize(new Dimension(100, 30));

        pnInfo2.add(lbDiaChi);
        pnInfo2.add(tfDiaChi);
        pnInfo2.add(lbSDT);
        pnInfo2.add(tfSDT);
        pnInfo2.add(lbGender);
        pnInfo2.add(rdoNam);
        pnInfo2.add(rdoNu);
        pnInfo2.add(lbBirthday);
        pnInfo2.add(birthday);

        JPanel pnInfo3 = new JPanel(new FlowLayout());
        pnInfo3.setBackground(new Color(211, 225, 237));
        JLabel lbTenSP = new JLabel("Tên Sản Phẩm:");
        lbTenSP.setFont(fontText);
        cbTenSP = new JComboBox<>();
        cbTenSP.setFont(fontText);
        loadCbxTenSP();
        JLabel lbSoLuong = new JLabel("Số lượng");
        lbSoLuong.setFont(fontText);
        cbxSoLuong = new JComboBox<>();
        cbxSoLuong.setFont(fontText);
        for (int i = 1; i <= 100; i++) {
            modelSL.addElement(i);
        }
        cbxSoLuong.setModel(modelSL);
        btnThem = new JButton("Thêm vào danh sách");
        btnThem.setFont(fontText);
        btnThem.setBackground(new Color(135, 172, 203));
        btnThem.setForeground(new Color(238, 238, 238));
        btnXoa = new JButton("Xóa khỏi danh sách");
        btnXoa.setFont(fontText);
        btnXoa.setBackground(new Color(135, 172, 203));
        btnXoa.setForeground(new Color(238, 238, 238));
        pnInfo3.add(lbTenSP);
        pnInfo3.add(cbTenSP);
        pnInfo3.add(lbSoLuong);
        pnInfo3.add(cbxSoLuong);
        pnInfo3.add(btnThem);
        pnInfo3.add(btnXoa);

        JPanel pnInfo0 = new JPanel(new GridLayout(3, 1, 10, 10));
        pnInfo0.setBackground(new Color(211, 225, 237));
        pnInfo0.add(pnInfo1);
        pnInfo0.add(pnInfo2);
        pnInfo0.add(pnInfo3);

        JPanel pnInfo = new JPanel(new BorderLayout(20, 20));
        pnInfo.setBackground(new Color(211, 225, 237));
        pnInfo.add(pnLbIntro, BorderLayout.NORTH);
        pnInfo.add(pnInfo0, BorderLayout.CENTER);
        pnInfo.setBorder(BorderFactory.createEtchedBorder());

        tbHoaDon = new JTable();
        tbHoaDon.setFont(fontTbl);
        tbHoaDon.setBackground(new Color(211, 225, 237));
        JScrollPane scrollPane = new JScrollPane(tbHoaDon);
        scrollPane.setBackground(new Color(211, 225, 237));
        scrollPane.setBorder(BorderFactory.createTitledBorder("Thông tin Hóa Đơn"));
        tbModel.setColumnIdentifiers(header);
        tbHoaDon.setModel(tbModel);
        tbModel.setRowCount(0);


        JLabel lbTongTienSP = new JLabel("Tổng tiền sản phẩm:");
        lbTongTienSP.setFont(fontText);
        tfTongTienSP = new JTextField(15);
        tfTongTienSP.setFont(fontTf);
        tfTongTienSP.setText("0");
        tfTongTienSP.setEditable(false);
        JLabel lbTienNhan = new JLabel("Tiền nhận:");
        lbTienNhan.setFont(fontText);
        tfTienNhan = new JTextField(15);
        tfTienNhan.setFont(fontTf);
        btnTienThoi = new JButton("Tiền Thối:");
        btnTienThoi.setFont(fontText);
        btnTienThoi.setBackground(new Color(135, 172, 203));
        btnTienThoi.setForeground(new Color(238, 238, 238));
        tfTienThoi = new JTextField(15);
        tfTienThoi.setFont(fontText);
        tfTienThoi.setEditable(false);

        btnThemHDMoi = new JButton("Thêm Hóa Đơn ");
        btnThemHDMoi.setFont(fontText);
        btnThemHDMoi.setBackground(new Color(135, 172, 203));
        btnThemHDMoi.setForeground(new Color(238, 238, 238));

        btnThoat = new JButton("Thoát");
        btnThoat.setBackground(new Color(135, 172, 203));
        btnThoat.setForeground(new Color(238, 238, 238));
        btnThoat.setFont(fontText);
        btnLamMoi = new JButton("Làm mới");
        btnLamMoi.setBackground(new Color(135, 172, 203));
        btnLamMoi.setForeground(new Color(238, 238, 238));
        btnLamMoi.setFont(fontText);


        JPanel pn1 = new JPanel(new FlowLayout());
        pn1.setBackground(new Color(211, 225, 237));
        pn1.add(lbTongTienSP);
        pn1.add(tfTongTienSP);
        pn1.add(lbTienNhan);
        pn1.add(tfTienNhan);

        JPanel pn2 = new JPanel(new FlowLayout());
        pn2.setBackground(new Color(211, 225, 237));
        pn2.add(btnTienThoi);
        pn2.add(tfTienThoi);

        JPanel pn3 = new JPanel(new FlowLayout());
        pn3.setBackground(new Color(211, 225, 237));
        pn3.add(btnThemHDMoi);
        pn3.add(btnLamMoi);
        pn3.add(btnThoat);


        JPanel pnThongTinChiTiet = new JPanel(new GridLayout(3, 1, 20, 20));
        pnThongTinChiTiet.setBackground(new Color(211, 225, 237));
        pnThongTinChiTiet.add(pn1);
        pnThongTinChiTiet.add(pn2);
        pnThongTinChiTiet.add(pn3);

        this.setLayout(new GridLayout(3, 1, 20, 20));
        this.add(pnInfo);
        this.add(scrollPane);
        this.add(pnThongTinChiTiet);

    }

    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj.equals(btnThoat)) {
            if (JOptionPane.showConfirmDialog(btnThoat, "Bạn có chắc muốn thoát !") == JOptionPane.YES_OPTION) {
                MainForm.tpMain.remove(frmLapHoaDon);
                MainForm.tpMain.addTab("Giới Thiệu", null, frmThongTin);
                MainForm.tpMain.setSelectedComponent(frmThongTin);
            }
        } else if (obj.equals(btnThem)) {
            xuLyThemSPVaoGio();
        } else if (obj.equals(btnXoa)) {
            lapHoaDonBUS.xuLyXoa();
        } else if (obj.equals(btnTienThoi)) {
            lapHoaDonBUS.xuLyTienThoi();
        } else if (obj.equals(btnThemHDMoi)) {
            try {
                xuLyThemHoaDonMoi();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } else if (obj.equals(btnLamMoi)) {
            lapHoaDonBUS.xoaRong();
        }

    }

    public void action() {
        btnThoat.addActionListener(this);
        btnThem.addActionListener(this);
        btnXoa.addActionListener(this);
        btnTienThoi.addActionListener(this);
        btnThemHDMoi.addActionListener(this);
        btnLamMoi.addActionListener(this);

    }


    public void xuLyThemSPVaoGio() {

        String tenSP = String.valueOf(cbxModel.getSelectedItem());
        Integer sl = Integer.valueOf(modelSL.getSelectedItem().toString());
        String maSP = chiTietHDDAO.getMaSPbyTen(cbxModel.getSelectedItem().toString());
        lapHoaDonBUS.xuLyThemSPVaoGio(tenSP,sl,maSP);

    }

    public void xuLyThemHoaDonMoi() throws Exception {
        xuLyThemKHHoaDon();
    }

    public void loadCbxTenSP() {
        cbTenSP.removeAllItems();
        try {
            for (SanPhamLK sanPhamLK : hoaDonDAO.loadCbxTenSP()) {
                String tenSP = sanPhamLK.getTenSP();
                cbxModel.addElement(tenSP);
                cbTenSP.setModel(cbxModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public void xuLyThemKHHoaDon() throws Exception {


        java.sql.Date ngaySinhsql2 = null;
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        String ngaySinhtxt2 = dateFormat2.format(dchooseNgaylap.getDate());
        java.util.Date ngaySinh2 = dateFormat2.parse(ngaySinhtxt2);
        ngaySinhsql2 = new java.sql.Date(ngaySinh2.getTime());
        String gioiTinh;
        if (rdoNam.isSelected()) {
            gioiTinh = "Nam";
        } else gioiTinh = "Nữ";
        String maHD = tfIDHoaDon.getText().trim();
        String tenKH = tfTenKH.getText().trim();
        String diaChi = tfDiaChi.getText().trim();
        String phone = tfSDT.getText().trim();

        java.sql.Date ngaySinhsql = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String ngaySinhtxt = dateFormat.format(birthday.getDate());
        java.util.Date ngaySinh = dateFormat.parse(ngaySinhtxt);
        ngaySinhsql = new java.sql.Date(ngaySinh.getTime());
        HoaDon hoaDon=new HoaDon(maHD,ngaySinhsql2,Double.valueOf(tfTongTienSP.getText()),
                hoaDonDAO.getMaNVbyTen(cbxNhanVien.getSelectedItem().toString()));
        KhachHang khachHang=new KhachHang(tenKH, phone, diaChi, maHD, gioiTinh, ngaySinhsql);
        lapHoaDonBUS.xuLyThemKHHoaDon(hoaDon,khachHang);

    }


}


