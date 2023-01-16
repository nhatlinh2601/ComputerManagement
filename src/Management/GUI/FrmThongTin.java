package Management.GUI;

import javax.swing.*;
import java.awt.*;


import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class FrmThongTin extends JPanel {

    /**
     * Create the panel.
     */
    public FrmThongTin() {
        setLayout(null);

        JLabel lblNewLabel = new JLabel("CHÀO MỪNG ĐẾN VỚI HTQL BÁN HÀNG LINH KIỆN MÁY TÍNH");
        lblNewLabel.setFont(new Font("Monospaced", Font.BOLD, 27));
        lblNewLabel.setBounds(298, 10, 872, 37);
        add(lblNewLabel);

        JButton btnNewButton = new JButton("Quản lý Nhân Viên");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnNewButton.setForeground(Color.black);
        btnNewButton.setBackground(Color.LIGHT_GRAY);
        btnNewButton.setBounds(93, 344, 187, 43);
        add(btnNewButton);

        JButton btnQunLLinh = new JButton("Quản lý Linh Kiện");
        btnQunLLinh.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnQunLLinh.setForeground(Color.black);
        btnQunLLinh.setBackground(Color.LIGHT_GRAY);
        btnQunLLinh.setBounds(93, 471, 187, 43);
        add(btnQunLLinh);

        JButton btnLpHan = new JButton("Lập Hóa Đơn");
        btnLpHan.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnLpHan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnLpHan.setForeground(Color.black);
        btnLpHan.setBackground(Color.LIGHT_GRAY);
        btnLpHan.setBounds(488, 201, 187, 43);
        add(btnLpHan);

        JButton btnXemHan = new JButton("Xem Hóa Đơn");
        btnXemHan.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnXemHan.setForeground(Color.black);
        btnXemHan.setBackground(Color.LIGHT_GRAY);
        btnXemHan.setBounds(488, 344, 187, 43);
        add(btnXemHan);

        JButton btnThngK = new JButton("Thống Kê");
        btnThngK.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnThngK.setForeground(Color.black);
        btnThngK.setBackground(Color.LIGHT_GRAY);
        btnThngK.setBounds(488, 471, 187, 43);
        add(btnThngK);

        JButton btnQunLKhch = new JButton("QL Khách Hàng");
        btnQunLKhch.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnQunLKhch.setForeground(Color.black);
        btnQunLKhch.setBackground(Color.LIGHT_GRAY);
        btnQunLKhch.setBounds(93, 201, 187, 43);
        add(btnQunLKhch);

        JLabel lblNewLabel_1 = new JLabel("Các nút chức năng cơ bản");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_1.setBounds(273, 74, 282, 29);
        add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Thêm, sửa, xóa và hiển thị thông tin ");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_2.setBounds(223, 118, 366, 29);
        add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("PROJECT INTRODUCTION");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_3.setBounds(1085, 64, 228, 48);
        add(lblNewLabel_3);

        JLabel lblNewLabel_3_1 = new JLabel("Subject: object-oriented programming");
        lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_3_1.setBounds(1048, 123, 390, 48);
        add(lblNewLabel_3_1);

        JLabel lblNewLabel_4 = new JLabel("");
        lblNewLabel_4.setIcon(new ImageIcon(FrmThongTin.class.getResource("/Image/computer-lock-icon (1).png")));
        lblNewLabel_4.setBounds(1124, 201, 374, 321);
        add(lblNewLabel_4);

    }
}

