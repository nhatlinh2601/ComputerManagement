package Management.GUI;

import Management.DAO.DatabaseHelper;
import Management.DTO.TaiKhoan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import static Management.GUI.MainForm.btnLinhKien;
import static Management.GUI.MainForm.btnThongKe;
import static Management.GUI.MainForm.btnNhanVien;
import static Management.GUI.MainForm.btnLinhKien;
import static Management.GUI.MainForm.btnHoaDon;

public class LoginDialog extends JFrame implements ActionListener, KeyListener {

    private JTextField username;
    private JPasswordField password;

    private JButton submit;
    private JButton close;
    private JComboBox cbxPhanQuyen;
    private DefaultComboBoxModel cbxModel=new DefaultComboBoxModel<>();
    public static boolean TrangThaiDangNhapNhanVien = false;
    public static boolean TrangThaiDangNhapQuanLy = false;
    private String tenTaiKhoanAdmin = "ADMIN";
    private String matKhauAdmin = "ADMIN";


    public LoginDialog(){
        init();
        setVisible(true);



//        setVisible(true);



    }
    public void init(){
        this.setTitle("Đăng nhập hệ thống ");
        this.setSize(400,200);
        this.setResizable(false);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon =new ImageIcon(getClass().getResource("/Image/logout-icon32.png"));
        this.setIconImage(icon.getImage());
        Font fontTitle = new Font("Monospaced",Font.BOLD,30);
        Font fontText = new Font("SF Mono", Font.PLAIN,15);

        JLabel title = new JLabel("ADMIN");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(fontTitle);
        title.setIcon(new ImageIcon(getClass().getResource("/Image/Admin-icon.png")));

        JPanel jPanelUsername = new JPanel(new FlowLayout());
        JLabel jLabelUsername = new JLabel(" Username: ",JLabel.CENTER);
        jLabelUsername.setFont(fontText);
        this.username = new JTextField(15);
        this.username.setFont(fontText);
        jPanelUsername.add(jLabelUsername);
        jPanelUsername.add(this.username);

        JPanel jPanelPassword = new JPanel(new FlowLayout());
        JLabel jLabelPassword = new JLabel("Password:  ",JLabel.CENTER);
        jLabelPassword.setFont(fontText);
        this.password = new JPasswordField(15);
        this.password.setFont(fontText);
        jPanelPassword.add(jLabelPassword);
        jPanelPassword.add(this.password);






        submit = new JButton("Đăng nhập");
         close = new JButton("Thoát");
        submit.setForeground(Color.WHITE);
        submit.setBorderPainted(false);
        submit.setBackground(Color.blue);
        submit.setFont(fontText);
        close.setForeground(Color.WHITE);
        close.setBorderPainted(false);
        close.setBackground(Color.blue);
        close.setFont(fontText);

        JPanel footerLog=new JPanel(new FlowLayout());
        footerLog.add(submit);
        footerLog.add(close);


        JPanel formLog = new JPanel();
        formLog.setLayout(new BoxLayout(formLog,BoxLayout.Y_AXIS));
        formLog.add(jPanelUsername);
        formLog.add(jPanelPassword);


        this.setLayout(new BorderLayout());
        this.add(title,BorderLayout.NORTH);
        this.add(formLog,BorderLayout.CENTER);
        this.add(footerLog,BorderLayout.SOUTH);
        close.addActionListener(this);
        submit.addActionListener(this);
        this.addKeyListener(this);

    }

    public boolean KiemTraDuLieu() {
        String tenUser = username.getText();
        // ten dang nhap phai la chu hoac so va khong co ki tu dac biet co toi da tu 5-20 ki tu
        boolean match = tenUser.matches("[a-zA-z0-9 ]{3,20}");
        if(match!=true) {
            JOptionPane.showMessageDialog(null,"Lỗi: Tên đăng Nhập(Không Chứa Ký Tự  Biệt,Tối Thiểu 3-20 Ký Tự)");
            return false;
        }
        else
            return true;
    }

    public TaiKhoan loadTaiKhoan(String tenDangNhap,String matKhau) {
        TaiKhoan taiKhoan = null;
        try {
            Connection con = DatabaseHelper.getConnection();
            PreparedStatement stmt = null;
            String sql  = "select * from taikhoan where username=? and password=?";

            stmt = con.prepareStatement(sql);
            stmt.setString(1,tenDangNhap);
            stmt.setString(2, matKhau);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                String ten = rs.getString(1).trim();
                String mk = rs.getString(2).trim();
                String loaiTk = rs.getString(3).trim();
                 taiKhoan=new TaiKhoan(ten,mk,loaiTk);
            }
            
        } catch (Exception e) {
            // TODO: handle exception
        } return taiKhoan;
    }

    public boolean kiemTraDangNhap(String tenDangNhap,String matKhau) {
        
        TaiKhoan taiKhoan=loadTaiKhoan(tenDangNhap,matKhau);
        
        if(tenDangNhap.equalsIgnoreCase(tenTaiKhoanAdmin) && matKhau.equalsIgnoreCase(matKhauAdmin)) {
            TrangThaiDangNhapNhanVien = true ;
            TrangThaiDangNhapQuanLy = true;
            return true;
        }
        else if(taiKhoan.getUsername().equalsIgnoreCase(tenDangNhap) && taiKhoan.getPassword().equalsIgnoreCase(matKhau)
                && taiKhoan.getLoaiTK().equalsIgnoreCase("employee") ) {
            TrangThaiDangNhapNhanVien =true;
            return true;
        }
        else if(taiKhoan.getUsername().equalsIgnoreCase(tenDangNhap) && taiKhoan.getPassword().equalsIgnoreCase(matKhau)
                && taiKhoan.getLoaiTK().equalsIgnoreCase("manager")) {
            TrangThaiDangNhapQuanLy =true;
            return true;
        }
        return false;
    }

    public boolean kiemTraDangNhapAdmin(String tenDangNhap,String matKhau) {
        if(tenDangNhap.equalsIgnoreCase(tenTaiKhoanAdmin) && matKhau.equalsIgnoreCase(matKhauAdmin)) {
            TrangThaiDangNhapNhanVien = true ;
            TrangThaiDangNhapQuanLy = true;
            return true;
        }
        return false;
    }
    public void logIn() {
        try {
            if(KiemTraDuLieu()) {
                String tenDN = username.getText().trim();
                String matKhau = password.getText().trim();
                
                if(kiemTraDangNhap(tenDN, matKhau) && TrangThaiDangNhapNhanVien==true && TrangThaiDangNhapQuanLy==true) {

                    MainForm mainForm=new MainForm();
                    mainForm.setVisible(true);
                    this.setVisible(false);
                }
                else if(kiemTraDangNhap(tenDN, matKhau) && TrangThaiDangNhapNhanVien==true) {

                    MainForm mainForm=new MainForm();
                    btnNhanVien.setEnabled(false);
                    btnLinhKien.setEnabled(false);
                    btnThongKe.setEnabled(false);
                    mainForm.setVisible(true);


                    // form thong ke false

                    this.setVisible(false);
                }
                else if(kiemTraDangNhap(tenDN, matKhau) && TrangThaiDangNhapQuanLy==true) {

                    MainForm mainForm=new MainForm();

                    mainForm.setVisible(true);
                    this.setVisible(false);
                }

                else
                    JOptionPane.showMessageDialog(this,"HI");
            }
        } catch (Exception e2) {
            // TODO: handle exception
            e2.printStackTrace();
            JOptionPane.showMessageDialog(this,"Tên Đăng Nhập, Hoặc Mật Khẩu Sai.");
        }
    }

    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        Object obj = e.getSource();
        if(obj.equals(close)) {
            System.exit(0);
        }
        else if(obj.equals(submit)) {
            logIn();
        }
    }

    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        if(e.getKeyCode()==KeyEvent.VK_ENTER) {
            logIn();
        }
    }

    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        if(e.getKeyCode()==KeyEvent.VK_ENTER) {
            logIn();
        }
    }











}
