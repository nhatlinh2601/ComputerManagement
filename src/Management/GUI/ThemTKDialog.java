package Management.GUI;

import Management.DAO.DatabaseHelper;
import Management.DTO.NhanVien;
import Management.DTO.SanPhamLK;
import Management.DTO.TaiKhoan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class ThemTKDialog extends JDialog {
    private PreparedStatement pstmt;
    private JComboBox cbxQuyen;
    private DefaultComboBoxModel modelQuyen = new DefaultComboBoxModel<>();


    private JPasswordField tfpass;
    private JButton register;
    private JButton close;

    public ThemTKDialog() {
        init();
        setVisible(true);
    }

    public void init() {
        this.setBackground(new Color(39, 124, 134));
        this.setTitle("Xác nhận Đăng Ký ");
        this.setSize(400, 200);
        this.setResizable(false);
        this.setLocationRelativeTo(null);


        Font fontTitle = new Font("Monospaced", Font.BOLD, 30);
        Font fontText = new Font("SF Mono", Font.PLAIN, 15);

        JLabel title = new JLabel("ADMIN");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(fontTitle);
        title.setIcon(new ImageIcon(getClass().getResource("/Image/Admin-icon.png")));

        cbxQuyen = new JComboBox<>();
        modelQuyen.addElement("employee");
        modelQuyen.addElement("manager");
        cbxQuyen.setModel(modelQuyen);


        JPanel jPanelPassword = new JPanel(new FlowLayout());
        JLabel jLabelPassword = new JLabel(" Password: ", JLabel.CENTER);
        jLabelPassword.setFont(fontText);
        this.tfpass = new JPasswordField(15);
        this.tfpass.setFont(fontText);
        jPanelPassword.add(jLabelPassword);
        jPanelPassword.add(this.tfpass);
        jPanelPassword.add(cbxQuyen);


        JPanel pnReader = new JPanel(new GridLayout(2, 1));
        JLabel lb1 = new JLabel("Nhập password tài khoản quyền quản lý");
        lb1.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel lb2 = new JLabel("để thêm tài khoản mới");
        lb2.setHorizontalAlignment(SwingConstants.CENTER);
        pnReader.add(lb1);
        pnReader.add(lb2);

        JPanel mainPn = new JPanel(new GridLayout(2, 1));
        mainPn.add(pnReader);
        mainPn.add(jPanelPassword);


        JPanel pnButton = new JPanel(new FlowLayout());

        register = new JButton("Đăng ký");
        register.setBorderPainted(false);
        register.setBackground(new Color(135, 172, 203));
        register.setFont(fontText);
        close = new JButton("Thoát");
        close.setBorderPainted(false);
        close.setBackground(new Color(135, 172, 203));
        close.setFont(fontText);

        pnButton.add(register);
        pnButton.add(close);

        this.setLayout(new BorderLayout());
        this.add(title, BorderLayout.NORTH);
        this.add(mainPn, BorderLayout.CENTER);
        this.add(pnButton, BorderLayout.SOUTH);

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(close, "Bạn có chắc muốn thoát") == JOptionPane.YES_OPTION) {
                    LoginDialog loginDialog = new LoginDialog();
                    dispose();
                }
            }
        });

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag = false;
                try {
                    for (TaiKhoan taikhoan : getPassword()) {
                        if (taikhoan.getPassword().equals(tfpass.getText())) {
                            flag = true;
                        }
                    }
                    if (flag) {
                        String username = LoginDialog.username.getText().trim();
                        String password = LoginDialog.password.getText().trim();
                        String loaiTK = modelQuyen.getSelectedItem().toString();


                        TaiKhoan taiKhoan = new TaiKhoan(username, password, loaiTK);
                        if (themTK(taiKhoan)) {
                            JOptionPane.showMessageDialog(register, "Thêm TK thành công");
                            dispose();
                            LoginDialog loginDialog = new LoginDialog();
                        } else JOptionPane.showMessageDialog(register, "Thêm TK thất bại");
                    } else JOptionPane.showMessageDialog(register, "Mật khẩu quyển quản lý không đúng!!");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }

    public ArrayList<TaiKhoan> getPassword() throws Exception {

        ArrayList<TaiKhoan> taiKhoans = new ArrayList<>();
        try {
            Connection conn = DatabaseHelper.getConnection();
            String sql = "select password from taikhoan where loaiTK = 'manager'";
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery(sql);
            while (rs.next()) {
                TaiKhoan taiKhoan = new TaiKhoan();
                taiKhoan.setPassword(rs.getString("password"));
                taiKhoans.add(taiKhoan);
            }
            return taiKhoans;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public boolean themTK(TaiKhoan taiKhoan) throws SQLException {
        try {

            Connection conn = DatabaseHelper.getConnection();
            String sql = "insert into taikhoan(username,password,loaitk) values(?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, taiKhoan.getUsername());
            pstmt.setString(2, taiKhoan.getPassword());
            pstmt.setString(3, taiKhoan.getLoaiTK());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return pstmt.executeUpdate() > 0;
    }
}
