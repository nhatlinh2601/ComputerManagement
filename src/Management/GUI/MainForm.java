package Management.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame implements ActionListener {

    public static FrmKhachHang frmKhachHang;
    public static FrmNhanVien frmNhanVien;
    public static FrmSanPhamLK frmSanPhamLK;
    public static FrmLapHoaDon frmLapHoaDon;
    public static FrmXemHoaDon frmXemHoaDon;
    public static FrmThongTin frmThongTin;
    public static FrmThongTin frmThongTinn;
    public static FrmThongKe frmThongKe;

    public static JButton btnKhachHang;
    public static JButton btnLinhKien;
    public static JButton btnNhanVien;
    public static JButton btnHoaDon;
    public static JButton btnAboutUs;
    public static JButton btnThongKe;
    public static JButton btnDangXuat;
    public static JTabbedPane tpMain;
    private JButton btnXemHD;


    public MainForm() {
        initGUI();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        action();

    }

    public void action() {
        btnKhachHang.addActionListener(this);
        btnNhanVien.addActionListener(this);
        btnLinhKien.addActionListener(this);

        btnHoaDon.addActionListener(this);
        btnXemHD.addActionListener(this);
        btnDangXuat.addActionListener(this);
        btnThongKe.addActionListener(this);
    }


    public void initGUI() {

        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Quản lý bán hàng linh kiện máy tính");
        ImageIcon icon = new ImageIcon(getClass().getResource("/Image/Admin-icon.png"));
        this.setIconImage(icon.getImage());


        JPanel footerBar = new JPanel();
        footerBar.setBackground(new Color(211, 225, 237));
        footerBar.setBorder(BorderFactory.createTitledBorder("MENU"));
        JToolBar tbar = new JToolBar();
        Font fontTextTbar = new Font("Fira Code", Font.PLAIN, 20);
        tbar.setBackground(new Color(119, 163, 168));


        ImageIcon iconKhachHang = new ImageIcon(getClass().getResource("/Image/Users-icon.png"));
        btnKhachHang = new JButton("QL Khách Hàng", iconKhachHang);
        btnKhachHang.setFont(fontTextTbar);
        btnKhachHang.setBackground(Color.DARK_GRAY);
        btnKhachHang.setForeground(Color.white);

        ImageIcon iconLinhKien = new ImageIcon(getClass().getResource("/Image/computer-lock-icon.png"));
        btnLinhKien = new JButton("QL Linh Kiện", iconLinhKien);
        btnLinhKien.setFont(fontTextTbar);
        btnLinhKien.setBackground(Color.DARK_GRAY);
        btnLinhKien.setForeground(Color.white);

        ImageIcon iconNhanVien = new ImageIcon(getClass().getResource("/Image/Preppy-icon.png"));
        btnNhanVien = new JButton("QL Nhân Viên", iconNhanVien);
        btnNhanVien.setFont(fontTextTbar);
        btnNhanVien.setBackground(Color.DARK_GRAY);
        btnNhanVien.setForeground(Color.white);


        ImageIcon iconHoaDon = new ImageIcon(getClass().getResource("/Image/receipt-icon.png"));
        btnHoaDon = new JButton("Lập Hóa Đơn", iconHoaDon);
        btnHoaDon.setFont(fontTextTbar);
        btnHoaDon.setBackground(Color.DARK_GRAY);
        btnHoaDon.setForeground(Color.white);

        ImageIcon iconXemHoaDon = new ImageIcon(getClass().getResource("/Image/invoice-icon.png"));
        btnXemHD = new JButton("QL đơn hàng", iconXemHoaDon);
        btnXemHD.setFont(fontTextTbar);
        btnXemHD.setBackground(Color.DARK_GRAY);
        btnXemHD.setForeground(Color.white);


        ImageIcon iconThongKe = new ImageIcon(getClass().getResource("/Image/Cash-register-icon.png"));
        btnThongKe = new JButton("Thống Kê", iconThongKe);
        btnThongKe.setFont(fontTextTbar);
        btnThongKe.setBackground(Color.DARK_GRAY);
        btnThongKe.setForeground(Color.white);

        ImageIcon iconDangXuat = new ImageIcon(getClass().getResource("/Image/logout-icon.png"));
        btnDangXuat = new JButton("Đăng Xuất", iconDangXuat);
        btnDangXuat.setFont(fontTextTbar);
        btnDangXuat.setBackground(Color.DARK_GRAY);
        btnDangXuat.setForeground(Color.white);

        tbar.add(btnNhanVien);
        tbar.add(btnKhachHang);
        tbar.add(btnLinhKien);
        tbar.add(btnHoaDon);
        tbar.add(btnXemHD);
        tbar.add(btnThongKe);
        tbar.add(btnDangXuat);
        tbar.addSeparator();

        footerBar.add(tbar);

        tpMain = new JTabbedPane();
        frmThongTin = new FrmThongTin();
        frmThongTinn = new FrmThongTin();
        tpMain.addTab("Giới Thiệu", null, frmThongTin);
        tpMain.setSelectedComponent(frmThongTin);


        this.add(footerBar, BorderLayout.NORTH);
        this.add(tpMain, BorderLayout.CENTER);
        this.getContentPane().setBackground(new Color(233, 233, 233));


    }


    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj.equals(btnKhachHang)) {

            tpMain.remove(tpMain.getSelectedComponent());
            frmKhachHang = new FrmKhachHang();
            ImageIcon iconKhachHang2 = new ImageIcon(getClass().getResource("/Image/Users-icon.png"));
            tpMain.addTab("Quản lý Khách Hàng", iconKhachHang2, frmKhachHang);
            tpMain.setSelectedComponent(frmKhachHang);

        } else if (obj.equals(btnNhanVien)) {

            tpMain.remove(tpMain.getSelectedComponent());
            frmNhanVien = new FrmNhanVien();
            ImageIcon iconNV = new ImageIcon(getClass().getResource("/Image/Preppy-icon.png"));
            tpMain.addTab("Quản lý Nhân Viên", iconNV, frmNhanVien);
            tpMain.setSelectedComponent(frmNhanVien);


        } else if (obj.equals(btnLinhKien)) {

            tpMain.remove(tpMain.getSelectedComponent());
            frmSanPhamLK = new FrmSanPhamLK();
            ImageIcon iconNV = new ImageIcon(getClass().getResource("/Image/computer-lock-icon.png"));
            tpMain.addTab("Quản lý SP", iconNV, frmSanPhamLK);
            tpMain.setSelectedComponent(frmSanPhamLK);

        } else if (obj.equals(btnHoaDon)) {

            tpMain.remove(tpMain.getSelectedComponent());
            frmLapHoaDon = new FrmLapHoaDon();
            ImageIcon iconHD = new ImageIcon(getClass().getResource("/Image/receipt-icon.png"));
            tpMain.addTab("Lập Hóa Đơn", iconHD, frmLapHoaDon);
            tpMain.setSelectedComponent(frmLapHoaDon);

        } else if (obj.equals(btnXemHD)) {

            tpMain.remove(tpMain.getSelectedComponent());
            frmXemHoaDon = new FrmXemHoaDon();
            ImageIcon iconXemHD = new ImageIcon(getClass().getResource("/Image/invoice-icon.png"));
            tpMain.addTab("Xem Hóa Đơn", iconXemHD, frmXemHoaDon);
            tpMain.setSelectedComponent(frmXemHoaDon);

        } else if (obj.equals(btnThongKe)) {

            tpMain.remove(tpMain.getSelectedComponent());
            frmThongKe = new FrmThongKe();
            ImageIcon iconTK = new ImageIcon(getClass().getResource("/Image/Cash-register-icon.png"));
            tpMain.addTab("Thống kê ", iconTK, frmThongKe);
            tpMain.setSelectedComponent(frmThongKe);

        } else if (obj.equals(btnDangXuat)) {
            this.dispose();
            LoginDialog loginDialog = new LoginDialog();


        }
    }


    public static void main(String[] args) {
//        LoginDialog loginDialog=new LoginDialog();
        new MainForm();

    }


}
