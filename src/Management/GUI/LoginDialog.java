package Management.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LoginDialog extends JDialog {

    private JTextField username;
    private JPasswordField password;
    private JButton submit;
    private JButton close;

    public LoginDialog(){
        init();
        setVisible(true);
        xuLyDangNhap();


//        setVisible(true);



    }
    public void init(){
        this.setTitle("Đăng nhập hệ thống ");
        this.setSize(400,200);
        this.setResizable(false);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
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

    }

//    public static void main(String[] args) {
//        new LoginDialog();
//    }

//    public void showWindow() {
//        Image icon = Toolkit.getDefaultToolkit().getImage("image/ManagerUI/icon-app.png");
//        this.setIconImage(icon);
//        this.setVisible(true);
//    }



    public void xuLyDangNhap(){
        submit.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                MainForm mainForm=new MainForm();



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
        });
    }


}
