package Management.GUI;

import Management.DAO.DatabaseHelper;
import Management.DTO.TaiKhoan;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import static Management.GUI.MainForm.btnLinhKien;
import static Management.GUI.MainForm.btnThongKe;
import static Management.GUI.MainForm.btnNhanVien;


public class LoginDialog extends JFrame implements ActionListener, KeyListener {

    public static JTextField username;
    public static JPasswordField password;

    private JButton submit;
    private JButton register;
    private JButton close;
    private JButton muaHang;
    private JComboBox cbxPhanQuyen;
    private DefaultComboBoxModel cbxModel = new DefaultComboBoxModel<>();
    public static boolean TrangThaiDangNhapNhanVien = false;
    public static boolean TrangThaiDangNhapQuanLy = false;
    private String tenTaiKhoanAdmin = "ADMIN";
    private String matKhauAdmin = "ADMIN";
    private PreparedStatement pstmt;


    public LoginDialog() {
        init();
        setVisible(true);


//        setVisible(true);


    }

    public void init() {
        this.getContentPane().setBackground(new Color(211, 225, 237));
        this.setBackground(new Color(39, 124, 134));
        this.setTitle("Đăng nhập hệ thống ");
        this.setSize(500, 220);
        this.setResizable(false);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon(getClass().getResource("/Image/logout-icon32.png"));
        this.setIconImage(icon.getImage());
        Font fontTitle = new Font("Monospaced", Font.BOLD, 30);
        Font fontText = new Font("SF Mono", Font.PLAIN, 15);

        JLabel title = new JLabel("ADMIN");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(fontTitle);
        title.setIcon(new ImageIcon(getClass().getResource("/Image/Admin-icon.png")));

        JPanel jPanelUsername = new JPanel(new FlowLayout());
        jPanelUsername.setBackground(new Color(211, 225, 237));
        JLabel jLabelUsername = new JLabel(" Username: ", JLabel.CENTER);
        jLabelUsername.setFont(fontText);
        this.username = new JTextField(25);
        this.username.setFont(fontText);
        jPanelUsername.add(jLabelUsername);
        jPanelUsername.add(this.username);

        JPanel jPanelPassword = new JPanel(new FlowLayout());
        jPanelPassword.setBackground(new Color(211, 225, 237));
        JLabel jLabelPassword = new JLabel("Password:  ", JLabel.CENTER);
        jLabelPassword.setFont(fontText);
        this.password = new JPasswordField(25);
        this.password.setFont(fontText);
        jPanelPassword.add(jLabelPassword);
        jPanelPassword.add(this.password);


        submit = new JButton("Đăng nhập");
        register = new JButton("Đăng ký");
        close = new JButton("Thoát");
        muaHang = new JButton("Mua hàng");
        submit.setBackground(new Color(38, 102, 157));
        submit.setForeground(new Color(238, 238, 238));
        submit.setBorderPainted(false);
        submit.setFont(fontText);
        register.setBorderPainted(false);
        register.setBackground(new Color(38, 102, 157));
        register.setForeground(new Color(238, 238, 238));
        register.setFont(fontText);
        close.setBorderPainted(false);
        close.setBackground(new Color(38, 102, 157));
        close.setForeground(new Color(238, 238, 238));
        close.setFont(fontText);
        muaHang.setBorderPainted(false);
        muaHang.setBackground(new Color(38, 102, 157));
        muaHang.setForeground(new Color(238, 238, 238));
        muaHang.setFont(fontText);

        JPanel footerLog = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerLog.setBackground(new Color(211, 225, 237));

        footerLog.add(register);
        footerLog.add(submit);
        footerLog.add(muaHang);
        footerLog.add(close);

        JPanel formLog = new JPanel();
        formLog.setBackground(new Color(211, 225, 237));
        formLog.setLayout(new BoxLayout(formLog, BoxLayout.Y_AXIS));
        formLog.add(jPanelUsername);
        formLog.add(jPanelPassword);


        this.setLayout(new BorderLayout());
        this.add(title, BorderLayout.NORTH);
        this.add(formLog, BorderLayout.CENTER);
        this.add(footerLog, BorderLayout.SOUTH);
        close.addActionListener(this);
        submit.addActionListener(this);
        this.addKeyListener(this);
        register.addActionListener(this);
        muaHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrmBanHang();
                dispose();
            }
        });

    }

    public boolean KiemTraDuLieu() {
        String tenUser = username.getText();
        // ten dang nhap phai la chu hoac so va khong co ki tu dac biet co toi da tu 5-20 ki tu
        boolean match = tenUser.matches("[a-zA-z0-9 ]{3,20}");
        if (match != true) {
            JOptionPane.showMessageDialog(null, "Lỗi: Tên đăng Nhập(Không Chứa Ký Tự  Biệt,Tối Thiểu 3-20 Ký Tự)");
            return false;
        } else
            return true;
    }

    public TaiKhoan loadTaiKhoan(String tenDangNhap, String matKhau) {
        TaiKhoan taiKhoan = null;
        try {
            Connection con = DatabaseHelper.getConnection();
            PreparedStatement stmt = null;
            String sql = "select * from taikhoan where username=? and password=?";

            stmt = con.prepareStatement(sql);
            stmt.setString(1, tenDangNhap);
            stmt.setString(2, matKhau);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String ten = rs.getString(1).trim();
                String mk = rs.getString(2).trim();
                String loaiTk = rs.getString(3).trim();
                taiKhoan = new TaiKhoan(ten, mk, loaiTk);
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        return taiKhoan;
    }

    public boolean kiemTraDangNhap(String tenDangNhap, String matKhau) {

        TaiKhoan taiKhoan = loadTaiKhoan(tenDangNhap, matKhau);

        if (tenDangNhap.equalsIgnoreCase(tenTaiKhoanAdmin) && matKhau.equalsIgnoreCase(matKhauAdmin)) {
            TrangThaiDangNhapNhanVien = true;
            TrangThaiDangNhapQuanLy = true;
            return true;
        } else if (taiKhoan.getUsername().equalsIgnoreCase(tenDangNhap) && taiKhoan.getPassword().equalsIgnoreCase(matKhau)
                && taiKhoan.getLoaiTK().equalsIgnoreCase("employee")) {
            TrangThaiDangNhapNhanVien = true;
            return true;
        } else if (taiKhoan.getUsername().equalsIgnoreCase(tenDangNhap) && taiKhoan.getPassword().equalsIgnoreCase(matKhau)
                && taiKhoan.getLoaiTK().equalsIgnoreCase("manager")) {
            TrangThaiDangNhapQuanLy = true;
            return true;
        }
        return false;
    }


    public void logIn() {
        try {
            if (KiemTraDuLieu()) {
                String tenDN = username.getText().trim();
                String matKhau = password.getText().trim();

                if (kiemTraDangNhap(tenDN, matKhau) && TrangThaiDangNhapNhanVien == true && TrangThaiDangNhapQuanLy == true) {

                    MainForm mainForm = new MainForm();
                    mainForm.setVisible(true);
                    this.setVisible(false);
                } else if (kiemTraDangNhap(tenDN, matKhau) && TrangThaiDangNhapNhanVien == true) {

                    MainForm mainForm = new MainForm();
                    btnNhanVien.setEnabled(false);
                    btnLinhKien.setEnabled(false);
                    btnThongKe.setEnabled(false);
                    mainForm.setVisible(true);


                    this.setVisible(false);
                } else if (kiemTraDangNhap(tenDN, matKhau) && TrangThaiDangNhapQuanLy == true) {

                    MainForm mainForm = new MainForm();

                    mainForm.setVisible(true);
                    this.setVisible(false);
                } else
                    JOptionPane.showMessageDialog(this, "HI");
            }
        } catch (Exception e2) {
            // TODO: handle exception
            e2.printStackTrace();
            JOptionPane.showMessageDialog(this, "Tên Đăng Nhập, Hoặc Mật Khẩu Sai.");
        }
    }

    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj.equals(close)) {
            System.exit(0);
        } else if (obj.equals(submit)) {
            logIn();
        } else if (obj.equals(register)) {
            if (KiemTraDuLieu()) {
                if (!password.getText().equals("")) {
                    try {
                        if (!checkTrungUsername()) {
                            ThemTKDialog themTKDialog = new ThemTKDialog();
                            dispose();
                        } else JOptionPane.showMessageDialog(null, "Username đã tồn tại!");
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                } else JOptionPane.showMessageDialog(this, "Mật khẩu không được để trống !");
            }
        }
    }

    public boolean checkTrungUsername() throws Exception {
        String username = LoginDialog.username.getText().trim();
        boolean flag = false;
        for (TaiKhoan tk : getTenTK()) {
            if (tk.getUsername().equalsIgnoreCase(username)) {
                flag = true;
            }
        }
        return flag;
    }

    public ArrayList<TaiKhoan> getTenTK() {
        ArrayList<TaiKhoan> taiKhoans = new ArrayList<>();
        try {
            Connection conn = DatabaseHelper.getConnection();
            String sql = "select username from taikhoan";
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery(sql);
            while (rs.next()) {
                TaiKhoan taiKhoan = new TaiKhoan();
                taiKhoan.setUsername(rs.getString("username"));
                taiKhoans.add(taiKhoan);
            }
            return taiKhoans;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            logIn();
        }
    }

    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            logIn();
        }
    }
}
