package Management.GUI;

import Management.DAO.BanHangDAO;
import Management.DTO.SanPhamLK;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmBanHang extends JFrame implements ActionListener {

    private DefaultComboBoxModel cbxModel = new DefaultComboBoxModel<>();
    private JButton btnChon;
    public static JTextField tfTongTien;
    private BanHangDAO banHangDAO = new BanHangDAO();
    private String[] header = new String[]{
            "STT", "TÊN SẢN PHẨM", "ĐƠN GIÁ", "SỐ LƯỢNG", "THÀNH TIỀN"
    };
    public static DefaultTableModel model = new DefaultTableModel();
    public static JTable tbGioHang;
    private JButton btnMuaHang;
    private JComboBox cbx;

    private JButton btnlamMoi;
    private JButton btnThoat;
    private JButton btnChuot;
    private JButton btnCPU;
    private JButton btnRAM;
    private JButton btnOCung;
    private JButton btnBoNguon;
    private JButton btnCard;
    private JButton btnMainboard;
    private JButton btnBanPhim;
    private JButton btnXoa;


    public FrmBanHang() {
        initGUI();
        action();
    }

    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj.equals(btnThoat)) {
            if (JOptionPane.showConfirmDialog(btnThoat, "Bạn có chắc muốn thoát") == JOptionPane.YES_OPTION) {
                dispose();
                new LoginDialog();
            }
        } else if (obj.equals(btnCPU)) {
            cbxModel.removeAllElements();
            for (SanPhamLK sp : banHangDAO.loadSanPhamCPU()) {
                cbxModel.addElement(sp.getTenSP());
            }
            cbx.setModel(cbxModel);
        } else if (obj.equals(btnChuot)) {
            cbxModel.removeAllElements();
            for (SanPhamLK sp : banHangDAO.loadSanPhamChuot()) {
                cbxModel.addElement(sp.getTenSP());
            }
            cbx.setModel(cbxModel);
        } else if (obj.equals(btnBanPhim)) {
            cbxModel.removeAllElements();
            for (SanPhamLK sp : banHangDAO.loadSanPhamBanPhim()) {
                cbxModel.addElement(sp.getTenSP());
            }
            cbx.setModel(cbxModel);
        } else if (obj.equals(btnBoNguon)) {
            cbxModel.removeAllElements();
            for (SanPhamLK sp : banHangDAO.loadSanPhamBoNguon()) {
                cbxModel.addElement(sp.getTenSP());
            }
            cbx.setModel(cbxModel);
        } else if (obj.equals(btnCard)) {
            cbxModel.removeAllElements();
            for (SanPhamLK sp : banHangDAO.loadSanPhamCard()) {
                cbxModel.addElement(sp.getTenSP());
            }
            cbx.setModel(cbxModel);
        } else if (obj.equals(btnMainboard)) {
            cbxModel.removeAllElements();
            for (SanPhamLK sp : banHangDAO.loadSanPhamMainBoard()) {
                cbxModel.addElement(sp.getTenSP());
            }
            cbx.setModel(cbxModel);
        } else if (obj.equals(btnOCung)) {
            cbxModel.removeAllElements();
            for (SanPhamLK sp : banHangDAO.loadSanPhamOCung()) {
                cbxModel.addElement(sp.getTenSP());
            }
            cbx.setModel(cbxModel);
        } else if (obj.equals(btnRAM)) {
            cbxModel.removeAllElements();
            for (SanPhamLK sp : banHangDAO.loadSanPhamRAM()) {
                cbxModel.addElement(sp.getTenSP());
            }
            cbx.setModel(cbxModel);
        } else if (obj.equals(btnChon)) {
            xuLyChon();
            ;
        } else if (obj.equals(btnXoa)) {
            xuLyXoa();
        } else if (obj.equals(btnMuaHang)) {
            xuLyMuaHang();
        } else if (obj.equals(btnlamMoi)) {
            model.setRowCount(0);
            tfTongTien.setText("0.0");
        }

    }

    public void action() {
        btnChon.addActionListener(this);
        btnlamMoi.addActionListener(this);
        btnRAM.addActionListener(this);
        btnXoa.addActionListener(this);
        btnThoat.addActionListener(this);
        btnBanPhim.addActionListener(this);
        btnBoNguon.addActionListener(this);
        btnCard.addActionListener(this);
        btnChuot.addActionListener(this);
        btnOCung.addActionListener(this);
        btnMuaHang.addActionListener(this);
        btnCPU.addActionListener(this);
        btnMainboard.addActionListener(this);
    }

    public void initGUI() {
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Bán hàng linh kiện máy tính");
        ImageIcon icon = new ImageIcon(getClass().getResource("/Image/Admin-icon.png"));
        this.setIconImage(icon.getImage());

        Font fontText = new Font("Monospaced", Font.BOLD, 28);
        Font fontBtn = new Font("SF Mono", Font.BOLD, 32);
        Font fontBtn2 = new Font("SF Mono", Font.BOLD, 28);


        JPanel pnButtonSP = new JPanel(new GridLayout(10, 1, 5, 5));
        JButton btnSP = new JButton("Sản Phẩm");
        btnSP.setFont(fontText);
        btnSP.setForeground(Color.white);
        btnSP.setBackground(new Color(135, 172, 203));
        btnCPU = new JButton("CPU");
        btnCPU.setFont(fontBtn);
        btnCPU.setForeground(Color.white);
        btnCPU.setBackground(new Color(135, 172, 203));
        btnRAM = new JButton("RAM");
        btnRAM.setFont(fontBtn);
        btnRAM.setForeground(Color.white);
        btnRAM.setBackground(new Color(135, 172, 203));
        btnOCung = new JButton("Ổ cứng");
        btnOCung.setFont(fontBtn);
        btnOCung.setForeground(Color.white);
        btnOCung.setBackground(new Color(135, 172, 203));
        btnBoNguon = new JButton("Bộ nguồn");
        btnBoNguon.setFont(fontBtn);
        btnBoNguon.setForeground(Color.white);
        btnBoNguon.setBackground(new Color(135, 172, 203));
        btnCard = new JButton("Card đồ họa");
        btnCard.setFont(fontBtn);
        btnCard.setForeground(Color.white);
        btnCard.setBackground(new Color(135, 172, 203));
        btnMainboard = new JButton("Mainboard");
        btnMainboard.setFont(fontBtn);
        btnMainboard.setForeground(Color.white);
        btnMainboard.setBackground(new Color(135, 172, 203));
        btnBanPhim = new JButton("Bàn Phím");
        btnBanPhim.setFont(fontBtn);
        btnBanPhim.setForeground(Color.white);
        btnBanPhim.setBackground(new Color(135, 172, 203));
        btnChuot = new JButton("Chuột");
        btnChuot.setFont(fontBtn);
        btnChuot.setForeground(Color.white);
        btnChuot.setBackground(new Color(135, 172, 203));


        pnButtonSP.add(btnCPU);
        pnButtonSP.add(btnRAM);
        pnButtonSP.add(btnOCung);
        pnButtonSP.add(btnBoNguon);
        pnButtonSP.add(btnCard);
        pnButtonSP.add(btnMainboard);
        pnButtonSP.add(btnBanPhim);
        pnButtonSP.add(btnChuot);


        JTabbedPane tpMain = new JTabbedPane();

        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(new Color(211, 225, 237));
        Font fontIntro = new Font("Monospaced", Font.BOLD, 40);
        Font fontTbl = new Font("SF Mono", Font.PLAIN, 16);
        Font fontTf = new Font("Lato", Font.PLAIN, 18);
        JPanel pnLbIntro = new JPanel();
        pnLbIntro.setBackground(new Color(135, 172, 203));
        JLabel lbIntro = new JLabel("HỆ THỐNG BÁN HÀNG LINH KIỆN MÁY TÍNH XIN CHÀO");
        lbIntro.setHorizontalAlignment(SwingConstants.CENTER);
        lbIntro.setFont(fontIntro);
        lbIntro.setForeground(Color.white);
        pnLbIntro.add(lbIntro);

        JPanel pnCbxSP = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnCbxSP.setBackground(new Color(211, 225, 237));
        cbx = new JComboBox<>();
        cbxModel.removeAllElements();
        for (SanPhamLK sp : banHangDAO.loadSanPham()) {
            cbxModel.addElement(sp.getTenSP());
        }
        cbx.setModel(cbxModel);
        cbx.setFont(fontBtn2);
        btnChon = new JButton("CHỌN");
        btnChon.setBackground(new Color(135, 172, 203));
        btnChon.setForeground(Color.white);
        btnChon.setFont(fontBtn2);
        btnThoat = new JButton("THOÁT");
        btnThoat.setBackground(new Color(135, 172, 203));
        btnThoat.setForeground(Color.white);
        btnThoat.setFont(fontBtn2);
        btnXoa = new JButton("XÓA");
        btnXoa.setFont(fontBtn2);
        btnXoa.setForeground(Color.white);
        btnXoa.setBackground(new Color(135, 172, 203));
        btnlamMoi = new JButton("LÀM MỚI");
        btnlamMoi.setFont(fontBtn2);
        btnlamMoi.setForeground(Color.white);
        btnlamMoi.setBackground(new Color(135, 172, 203));
        pnCbxSP.add(cbx);
        pnCbxSP.add(btnChon);
        pnCbxSP.add(btnlamMoi);
        pnCbxSP.add(btnXoa);
        pnCbxSP.add(btnThoat);

        tbGioHang = new JTable();
        model.setColumnIdentifiers(header);
        tbGioHang.setModel(model);
        tbGioHang.setBackground(new Color(211, 225, 237));
        tbGioHang.setFont(fontTbl);
        JScrollPane scrollPane = new JScrollPane(tbGioHang);
        scrollPane.setBorder(BorderFactory.createTitledBorder("GIỎ HÀNG CỦA BẠN"));

        JPanel pnTongtien = new JPanel(new FlowLayout());
        pnTongtien.setBackground(new Color(211, 225, 237));
        JLabel lbTongTien = new JLabel("TỔNG TIỀN");
        lbTongTien.setFont(fontBtn2);
        tfTongTien = new JTextField(10);
        tfTongTien.setHorizontalAlignment(SwingConstants.CENTER);
        tfTongTien.setFont(fontBtn2);
        tfTongTien.setEditable(false);
        btnMuaHang = new JButton("MUA HÀNG");
        btnMuaHang.setFont(fontBtn2);
        btnMuaHang.setForeground(Color.white);
        btnMuaHang.setBackground(new Color(135, 172, 203));

        pnTongtien.add(lbTongTien);
        pnTongtien.add(tfTongTien);
        pnTongtien.add(btnMuaHang);

        JPanel pn1 = new JPanel(new GridLayout(2, 1));
        pn1.setBackground(new Color(211, 225, 237));
        pn1.add(pnCbxSP);
        pn1.add(pnTongtien);

        JPanel pn2 = new JPanel(new GridLayout(2, 1));
        pn2.setBackground(new Color(211, 225, 237));
        pn2.add(scrollPane);
        pn2.add(pn1);

        JPanel pnMain = new JPanel(new BorderLayout());
        pnMain.setBackground(new Color(211, 225, 237));
        pnMain.add(pn2, BorderLayout.CENTER);

        main.add(pnLbIntro, BorderLayout.NORTH);
        main.add(pnMain, BorderLayout.CENTER);

        tpMain.addTab(null, main);
        tpMain.setSelectedComponent(main);


        this.setLayout(new BorderLayout());
        add(pnButtonSP, BorderLayout.WEST);
        add(tpMain, BorderLayout.CENTER);
        this.setVisible(true);


    }

    public void xuLyMuaHang() {

        int rowss = FrmBanHang.model.getRowCount();
        if (rowss <= 0) {
            JOptionPane.showMessageDialog(btnXoa, "Giỏ hàng đang trống.Hãy thêm sản phẩm mới!");
        } else {
            FrmMuaHang frmMuaHang = new FrmMuaHang();
            FrmMuaHang.tfTongTienSP.setText(String.valueOf(MoTaSPDialog.tinhTongTien()));
        }

    }


    public void xuLyXoa() {
        int rows = FrmBanHang.tbGioHang.getSelectedRow();
        int rowss = FrmBanHang.model.getRowCount();
        if (rowss <= 0) {
            JOptionPane.showMessageDialog(btnXoa, "Giỏ hàng đang trống.Hãy thêm sản phẩm mới!");
        } else {
            if (rows != -1) {
                if (JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc muốn xóa sản phẩm khỏi giỏ hàng?") == JOptionPane.YES_OPTION) {
                    FrmBanHang.model.removeRow(rows);
                    FrmBanHang.model.fireTableDataChanged();
                    tfTongTien.setText(String.valueOf(MoTaSPDialog.tinhTongTien()));
                }
            } else
                JOptionPane.showMessageDialog(btnXoa, "Dữ liệu đang trống.Vui lòng chọn sản phẩm trước khi xóa!");
        }
    }

    public void xuLyChon() {
        String tenSP = cbxModel.getSelectedItem().toString();
        SanPhamLK sp = banHangDAO.layThongTinSanPham(tenSP);
        MoTaSPDialog moTaSPDialog = new MoTaSPDialog();
        MoTaSPDialog.tfMaSP.setText(sp.getMaSP());
        MoTaSPDialog.tfTenSP.setText(sp.getTenSP());
        MoTaSPDialog.tfMoTa.setText(sp.getMoTa());
        MoTaSPDialog.tfGia.setText(String.valueOf(sp.getPrice()));
        MoTaSPDialog.tfNSX.setText(sp.getNsx());
        MoTaSPDialog.tfNgaySX.setText(String.valueOf(sp.getNgaySX()));
        moTaSPDialog.setVisible(true);
    }


}