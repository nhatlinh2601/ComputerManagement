package Management.GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;

import javax.swing.table.DefaultTableModel;




public class FrmXuatHD extends JDialog implements ActionListener {


    private static JPanel contentPane;
    public static JTable table;
    public static JLabel lblQLT;
    public static JLabel lblDiaChi;
    public static JLabel lblDienThoai;
    public static JLabel lblMHDon ;
    public static JLabel lblNgayLap;
    public static JLabel lblHoTenKH ;
    public static JLabel lblNamSinh;
    public static JLabel lblSDTKH;
    public static JLabel lblGioiTinh ;
    public static JLabel lblTenKH1;
    public static JLabel lblLoaiHD1;
    public static JLabel lblGTinh1;
    public static JLabel lblNamSinh1 ;
    public static JLabel lblsdtkh1;
    public static JLabel lblSL;
    public static JLabel lblTongTThuoc;
    public static JLabel lblTongT ;
    public static JLabel lblNguoiBan ;
    public static JLabel lblNguoiMuaHang1 ;
    private static JPanel panel;
    public static DefaultTableModel tableModel ;
    public static JLabel lbNgayLap;

    public FrmXuatHD() {

        setTitle("HÓA ĐƠN QUẢN LÝ CỬA HÀNG");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize( 652, 775);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        contentPane.setLayout(null);


        panel = new JPanel();
        panel.setBounds(0, 10, 644, 725);

        panel.setBorder(new LineBorder(new Color(0, 0, 128), 1, true));
        contentPane.add(panel);
        panel.setLayout(null);

        Font fontIntro = new Font("Monospaced", Font.BOLD, 32);
        Font fontTbl = new Font("SF Mono", Font.PLAIN, 16);
        Font fontText = new Font("SF Mono", Font.BOLD, 18);
        Font fontTf = new Font("Lato", Font.PLAIN, 18);



        lblQLT = new JLabel("CỬA HÀNG LINH KIỆN MÁY TÍNH DREAM");
        lblQLT.setBounds(149, 11, 429, 27);
        lblQLT.setFont(fontText);
        lblQLT.setForeground(new Color(255, 0, 0));
        panel.add(lblQLT);

        lblDiaChi = new JLabel("Địa chỉ:");
        lblDiaChi.setBounds(33, 93, 119, 14);
        lblDiaChi.setForeground(Color.BLUE);
        lblDiaChi.setFont(fontText);
        panel.add(lblDiaChi);

        lblDienThoai = new JLabel("Điện thoại:");
        lblDienThoai.setBounds(33, 55, 168, 14);
        lblDienThoai.setForeground(Color.BLUE);
        lblDienThoai.setFont(fontText);
        panel.add(lblDienThoai);

        lblMHDon = new JLabel("Mã hóa đơn:");
        lblMHDon.setBounds(398, 55, 147, 14);
        lblMHDon.setForeground(Color.BLUE);
        lblMHDon.setFont(fontText);
        panel.add(lblMHDon);

        JPanel panel_1 = new JPanel();

        panel_1.setBounds(23, 176, 596, 84);
        panel_1.setBorder(new LineBorder(new Color(0, 0, 128), 1, true));
        panel.add(panel_1);
        panel_1.setLayout(null);

        lblNamSinh = new JLabel("Năm sinh:");
        lblNamSinh.setForeground(Color.BLUE);
        lblNamSinh.setFont(new Font("Dialog", Font.BOLD, 16));
        lblNamSinh.setBounds(318, 7, 129, 25);
        panel_1.add(lblNamSinh);

        lblSDTKH = new JLabel("Số điện thoại:");
        lblSDTKH.setForeground(Color.BLUE);
        lblSDTKH.setFont(new Font("Dialog", Font.BOLD, 16));
        lblSDTKH.setBounds(318, 41, 147, 27);
        panel_1.add(lblSDTKH);

        lblGioiTinh = new JLabel("Giới tính:");
        lblGioiTinh.setForeground(Color.BLUE);
        lblGioiTinh.setFont(new Font("Dialog", Font.BOLD, 16));
        lblGioiTinh.setBounds(10, 35, 129, 39);
        panel_1.add(lblGioiTinh);

        lblTenKH1 = new JLabel("Nhat Linh");
        lblTenKH1.setHorizontalAlignment(SwingConstants.CENTER);
        lblTenKH1.setFont(new Font("Dialog", Font.BOLD, 16));
        lblTenKH1.setBounds(108, -1, 147, 40);
        panel_1.add(lblTenKH1);

        lblNamSinh1 = new JLabel("17-02-2004");
        lblNamSinh1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNamSinh1.setFont(new Font("Dialog", Font.BOLD, 16));
        lblNamSinh1.setBounds(451, 9, 122, 20);
        panel_1.add(lblNamSinh1);

        lblsdtkh1 = new JLabel("0346242297");
        lblsdtkh1.setHorizontalAlignment(SwingConstants.CENTER);
        lblsdtkh1.setFont(new Font("Dialog", Font.BOLD, 16));
        lblsdtkh1.setBounds(464, 47, 122, 21);
        panel_1.add(lblsdtkh1);

        lblGTinh1 = new JLabel("Nu");
        lblGTinh1.setHorizontalAlignment(SwingConstants.CENTER);
        lblGTinh1.setFont(new Font("Dialog", Font.BOLD, 16));
        lblGTinh1.setBounds(108, 36, 129, 38);
        panel_1.add(lblGTinh1);

        lblHoTenKH = new JLabel("Họ tên KH:");
        lblHoTenKH.setBounds(10, -3, 112, 45);
        panel_1.add(lblHoTenKH);
        lblHoTenKH.setForeground(new Color(0, 0, 255));
        lblHoTenKH.setFont(new Font("Dialog", Font.BOLD, 16));

        JLabel lblDHTTThuoc = new JLabel("HÓA ĐƠN THANH TOÁN");
        lblDHTTThuoc.setBounds(178, 139, 258, 27);
        lblDHTTThuoc.setHorizontalAlignment(SwingConstants.CENTER);
        lblDHTTThuoc.setForeground(new Color(0, 0, 255));
        lblDHTTThuoc.setFont(fontText);
        panel.add(lblDHTTThuoc);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setViewportBorder(new LineBorder(new Color(0, 0, 128), 1, true));
        scrollPane_1.setBounds(23, 266, 596, 299);
        panel.add(scrollPane_1);


        String[] tb = new String[] {"STT","Tên Sản phẩm","ĐVT","Đơn Giá","Số Lượng","Thành Tiền"};

        tableModel = new DefaultTableModel(tb,0);
        table = new JTable(tableModel);
        table.setFont(fontTbl);


        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(104);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
        table.getColumnModel().getColumn(3).setPreferredWidth(63);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        scrollPane_1.setViewportView(table);

        JLabel lblNguoiMuaHang = new JLabel("Người mua hàng");
        lblNguoiMuaHang.setBounds(41, 650, 168, 27);
        lblNguoiMuaHang.setForeground(Color.BLUE);
        lblNguoiMuaHang.setFont(new Font("Dialog", Font.BOLD, 18));
        panel.add(lblNguoiMuaHang);

        JLabel lblNguoiBanHang = new JLabel("Người bán hàng");
        lblNguoiBanHang.setBounds(444, 650, 190, 27);
        lblNguoiBanHang.setForeground(Color.BLUE);
        lblNguoiBanHang.setFont(new Font("Dialog", Font.BOLD, 18));
        panel.add(lblNguoiBanHang);

        JPanel panel_2 = new JPanel();

        panel_2.setBounds(23, 572, 596, 35);
        panel_2.setBorder(new LineBorder(new Color(0, 0, 128), 1, true));
        panel.add(panel_2);
        panel_2.setLayout(null);

        JLabel lblTongTienTh = new JLabel("Tổng tiền linh kiện:");
        lblTongTienTh.setForeground(Color.BLUE);
        lblTongTienTh.setFont(new Font("Dialog", Font.BOLD, 18));
        lblTongTienTh.setBounds(256, 8, 216, 24);
        panel_2.add(lblTongTienTh);

        JLabel lblSLuong = new JLabel("Số lượng:");
        lblSLuong.setForeground(Color.BLUE);
        lblSLuong.setFont(new Font("Dialog", Font.BOLD, 18));
        lblSLuong.setBounds(10, 11, 121, 19);
        panel_2.add(lblSLuong);

        lblSL = new JLabel("");
        lblSL.setFont(new Font("Dialog", Font.BOLD, 18));
        lblSL.setHorizontalAlignment(SwingConstants.LEFT);
        lblSL.setBounds(106, 12, 121, 20);
        panel_2.add(lblSL);

        lblTongTThuoc = new JLabel("50000");
        lblTongTThuoc.setFont(new Font("Dialog", Font.BOLD, 18));
        lblTongTThuoc.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTongTThuoc.setBounds(423, 9, 99, 22);
        panel_2.add(lblTongTThuoc);

        JLabel label_3 = new JLabel("(VND)");
        label_3.setForeground(Color.BLUE);
        label_3.setFont(fontText);
        label_3.setBounds(537, 11, 121, 14);
        panel_2.add(label_3);

        JPanel panel_3 = new JPanel();
        panel_3.setLayout(null);
        panel_3.setBorder(new LineBorder(new Color(0, 0, 128)));
        panel_3.setBounds(23, 603, 596, 35);
        panel.add(panel_3);

        JLabel lblTongThanhToan = new JLabel("Tổng tiền thanh toán:");
        lblTongThanhToan.setBounds(256, 12, 214, 23);
        panel_3.add(lblTongThanhToan);
        lblTongThanhToan.setForeground(Color.BLUE);
        lblTongThanhToan.setFont(new Font("Dialog", Font.BOLD, 18));

        lblTongT = new JLabel("5000");
        lblTongT.setFont(new Font("Dialog", Font.BOLD, 18));
        lblTongT.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTongT.setBounds(418, 12, 111, 23);
        panel_3.add(lblTongT);

        JLabel lblvnd = new JLabel("(VND)");
        lblvnd.setForeground(Color.BLUE);
        lblvnd.setFont(fontText);
        lblvnd.setBounds(539, 12, 87, 14);
        panel_3.add(lblvnd);

        lbNgayLap = new JLabel("");
        lbNgayLap.setFont(new Font("Dialog", Font.BOLD, 16));
        lbNgayLap.setHorizontalAlignment(SwingConstants.CENTER);
        lbNgayLap.setBounds(83, 9, 163, 25);
        panel_3.add(lbNgayLap);

        lblNgayLap = new JLabel("Ngày lập:");
        lblNgayLap.setBounds(10, 10, 98, 20);
        panel_3.add(lblNgayLap);
        lblNgayLap.setForeground(Color.BLUE);
        lblNgayLap.setFont(new Font("Dialog", Font.PLAIN, 18));

        JLabel lblSDT1 = new JLabel("0346242297");
        lblSDT1.setFont(fontText);
        lblSDT1.setBounds(159, 55, 199, 14);
        panel.add(lblSDT1);

        JLabel lblDiaChi1 = new JLabel("470 Trần Đại Nghĩa, Hòa Quý, Đà Nẵng ");
        lblDiaChi1.setFont(fontText);
        lblDiaChi1.setBounds(135, 87, 397, 27);
        panel.add(lblDiaChi1);

        lblLoaiHD1 = new JLabel("100kh");
        lblLoaiHD1.setFont(new Font("Dialog", Font.BOLD, 16));
        lblLoaiHD1.setBounds(524, 55, 120, 14);
        panel.add(lblLoaiHD1);

        lblNguoiBan = new JLabel("");
        lblNguoiBan.setHorizontalAlignment(SwingConstants.CENTER);
        lblNguoiBan.setFont(new Font("Dialog", Font.PLAIN, 16));
        lblNguoiBan.setBounds(410, 675, 196, 27);
        panel.add(lblNguoiBan);

        lblNguoiMuaHang1 = new JLabel("");
        lblNguoiMuaHang1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNguoiMuaHang1.setFont(new Font("Dialog", Font.PLAIN, 18));
        lblNguoiMuaHang1.setBounds(18, 675, 191, 30);
        panel.add(lblNguoiMuaHang1);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }


}

