package Management.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


import Management.BUS.KhachHangBUS;
import Management.DAO.HoaDonDAO;
import Management.DAO.KhachHangDAO;
import Management.DTO.KhachHang;
import com.toedter.calendar.JDateChooser;

import static Management.GUI.MainForm.frmKhachHang;

public class FrmKhachHang extends JPanel implements ActionListener, MouseListener {
    private KhachHangDAO khachHangDAO = new KhachHangDAO();
    private KhachHangBUS khachHangBUS=new KhachHangBUS();

    public static DefaultTableModel customerTbModel;
    public static DefaultTableModel hoaDonModel = new DefaultTableModel();
    public static JTable tbCustomer;


    private String[] header = {"Mã Khách Hàng", "Tên Khách Hàng", "Địa Chỉ", "Số Điện Thoại", "Ngày Sinh", "Giới Tính"};
    private String[] headerHoaDon = {"Mã Hoá Đơn", "Ngày lập", "Tổng tiền", "Nhân Viên"};
    public static JTextField tfName;
    public static JTextField tfPhone;
    public static JTextField tfAddress;
    public static JRadioButton rdoNam;
    public static JRadioButton rdoNu;
    public static JDateChooser date;
    public static JTextField tfId;


    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClose;
    public static JRadioButton btnTimKiemByMaKH;
    public static JRadioButton btnTimKiemByName;
    public static JRadioButton btnTimKiemBySDT;
    private JButton btnFindKH;
    private JButton btnNew;
    public static JTextField tfNhapTimKiem;
    private ButtonGroup gender;
    public static JTable tbHoaDonKH;
    public static FrmThongTin frmThongTin = new FrmThongTin();
    private JButton btnSort;
    private ArrayList<KhachHang> khachHangList;

    public FrmKhachHang() {
        initGUI();
        action();
    }

    public void initGUI() {
        this.setBackground(new Color(233, 233, 233));
        Font fontIntro = new Font("Monospaced", Font.BOLD, 32);
        Font fontTbl = new Font("SF Mono", Font.PLAIN, 16);
        Font fontText = new Font("SF Mono", Font.BOLD, 18);
        Font fontTf = new Font("Lato", Font.PLAIN, 18);
        JPanel pnLbIntro = new JPanel();
        pnLbIntro.setBackground(new Color(135, 172, 203));
        JLabel lbIntro = new JLabel("THÔNG TIN KHÁCH HÀNG ", SwingConstants.CENTER);
        lbIntro.setBackground(new Color(135, 172, 203));
        lbIntro.setFont(fontIntro);
        lbIntro.setForeground(Color.white);
        pnLbIntro.add(lbIntro);

        JPanel pnCenter = new JPanel(new FlowLayout());
        pnCenter.setBackground(new Color(211, 225, 237));
        JPanel pnInputInfo = new JPanel(new GridLayout(3, 1, 20, 20));
        pnInputInfo.setBackground(new Color(211, 225, 237));
        JPanel pnInfo1 = new JPanel(new FlowLayout());
        pnInfo1.setBackground(new Color(211, 225, 237));
        JLabel lbId = new JLabel("Mã KH:");
        lbId.setFont(fontText);
        tfId = new JTextField(18);
        tfId.setFont(fontTf);
        tfId.setEditable(false);
        JLabel lbName = new JLabel("Tên KH:");
        lbName.setFont(fontText);
        tfName = new JTextField(18);
        tfName.setFont(fontTf);
        gender = new ButtonGroup();
        rdoNam = new JRadioButton("Nam");
        rdoNam.setFont(fontText);
        rdoNam.setBackground(new Color(135, 172, 203));
        rdoNam.setForeground(new Color(238, 238, 238));
        rdoNu = new JRadioButton("Nữ");
        rdoNu.setFont(fontText);
        rdoNu.setBackground(new Color(135, 172, 203));
        rdoNu.setForeground(new Color(238, 238, 238));
        gender.add(rdoNam);
        gender.add(rdoNu);
        JLabel lbGender = new JLabel("Giới tính:");
        lbGender.setFont(fontText);
        pnInfo1.add(lbId);
        pnInfo1.add(tfId);
        pnInfo1.add(lbName);
        pnInfo1.add(tfName);
        pnInfo1.add(lbGender);
        pnInfo1.add(rdoNam);
        pnInfo1.add(rdoNu);

        JPanel pnInfo2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnInfo2.setBackground(new Color(211, 225, 237));
        JLabel lbPhone = new JLabel("Số ĐT:");
        lbPhone.setFont(fontText);
        tfPhone = new JTextField(20);
        tfPhone.setFont(fontTf);
        JLabel lbAddress = new JLabel("Địa chỉ:");
        lbAddress.setFont(fontText);
        tfAddress = new JTextField(20);
        tfAddress.setFont(fontTf);
        JLabel lbDate = new JLabel("Ngày sinh:");
        lbDate.setFont(fontText);
        date = new JDateChooser();
//        date.getCalendarButton().setEnabled(false);
        date.setLocale(Locale.forLanguageTag("vi-VN"));
        date.setDateFormatString("dd-MM-yyyy");
//        date.getCalendarButton().setEnabled(true);
        date.setDate(new Date(System.currentTimeMillis()));
        date.setPreferredSize(new Dimension(100, 30));
        date.setFont(fontTf);
        pnInfo2.add(lbPhone);
        pnInfo2.add(tfPhone);
        pnInfo2.add(lbAddress);
        pnInfo2.add(tfAddress);
        pnInfo2.add(lbDate);
        pnInfo2.add(date);


        pnInputInfo.add(pnInfo1);
        pnInputInfo.add(pnInfo2);


        JPanel pnButton = new JPanel(new GridLayout(3, 1, 20, 20));
        pnButton.setBackground(new Color(211, 225, 237));

        ImageIcon iconUpdate = new ImageIcon(getClass().getResource("/Image/Text-Edit-icon.png"));
        btnUpdate = new JButton("Cập nhật", iconUpdate);
        btnUpdate.setFont(fontText);
        btnUpdate.setBackground(new Color(135, 172, 203));
        btnUpdate.setForeground(new Color(238, 238, 238));
        ImageIcon iconDelete = new ImageIcon(getClass().getResource("/Image/Button-Close-icon.png"));
        btnDelete = new JButton("Xóa", iconDelete);
        btnDelete.setFont(fontText);
        btnDelete.setBackground(new Color(135, 172, 203));
        btnDelete.setForeground(new Color(238, 238, 238));
        ImageIcon iconClsoe = new ImageIcon(getClass().getResource("/Image/Close-icon.png"));
        btnClose = new JButton("Thoát", iconClsoe);
        btnClose.setFont(fontText);
        btnClose.setBackground(new Color(135, 172, 203));
        btnClose.setForeground(new Color(238, 238, 238));


        pnButton.add(btnUpdate);
//        pnButton.add(btnDelete);


        pnCenter.add(pnInputInfo);
        pnCenter.add(pnButton);

        JPanel pnFooterTimKiem = new JPanel(new GridLayout(2, 1, 20, 20));
        pnFooterTimKiem.setBackground(new Color(211, 225, 237));
        pnFooterTimKiem.setBorder(BorderFactory.createTitledBorder("Tìm kiếm Khách Hàng"));


        JPanel nhapThongTinTimKiem = new JPanel(new FlowLayout());
        nhapThongTinTimKiem.setBackground(new Color(211, 225, 237));
        JLabel lbMaKH = new JLabel("Nhập thông tin tìm kiếm:");
        lbMaKH.setFont(fontText);
        tfNhapTimKiem = new JTextField(30);
        tfNhapTimKiem.setFont(fontTf);
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Image/Zoom-icon.png"));
        btnFindKH = new JButton("Tìm Kiếm", imageIcon);
        btnFindKH.setFont(fontText);
        btnFindKH.setBackground(new Color(135, 172, 203));
        btnFindKH.setForeground(new Color(238, 238, 238));
        ImageIcon iconNew = new ImageIcon("/Image/new-icon.png");
        btnNew = new JButton("Làm Mới");
        btnNew.setFont(fontText);
        btnNew.setBackground(new Color(135, 172, 203));
        btnNew.setForeground(new Color(238, 238, 238));

        btnSort = new JButton("Sắp xếp");
        btnSort.setFont(fontText);
        btnSort.setBackground(new Color(135, 172, 203));
        btnSort.setForeground(new Color(238, 238, 238));


        nhapThongTinTimKiem.add(lbMaKH);
        nhapThongTinTimKiem.add(tfNhapTimKiem);
        nhapThongTinTimKiem.add(btnFindKH);
        nhapThongTinTimKiem.add(btnNew);
        nhapThongTinTimKiem.add(btnSort);
        nhapThongTinTimKiem.add(btnClose);


        JPanel timKiemBy = new JPanel(new FlowLayout());
        timKiemBy.setBackground(new Color(211, 225, 237));

        JLabel lbBy = new JLabel("Tim theo:");
        lbBy.setFont(fontText);
        btnTimKiemByMaKH = new JRadioButton("Mã KH");
        btnTimKiemByMaKH.setFont(fontText);
        btnTimKiemByMaKH.setBackground(new Color(135, 172, 203));
        btnTimKiemByMaKH.setForeground(new Color(238, 238, 238));
        btnTimKiemByName = new JRadioButton("Họ tên");
        btnTimKiemByName.setFont(fontText);
        btnTimKiemByName.setBackground(new Color(135, 172, 203));
        btnTimKiemByName.setForeground(new Color(238, 238, 238));
        btnTimKiemBySDT = new JRadioButton("SDT");
        btnTimKiemBySDT.setFont(fontText);
        btnTimKiemBySDT.setBackground(new Color(135, 172, 203));
        btnTimKiemBySDT.setForeground(new Color(238, 238, 238));


        ButtonGroup timKiem = new ButtonGroup();
        timKiem.add(btnTimKiemByMaKH);
        timKiem.add(btnTimKiemByName);
        timKiem.add(btnTimKiemBySDT);

        timKiemBy.add(lbBy);
        timKiemBy.add(btnTimKiemByMaKH);
        timKiemBy.add(btnTimKiemByName);
        timKiemBy.add(btnTimKiemBySDT);


        pnFooterTimKiem.add(nhapThongTinTimKiem);
        pnFooterTimKiem.add(timKiemBy);


        JPanel pnMain = new JPanel(new BorderLayout());
        pnMain.setBackground(new Color(211, 225, 237));
        pnMain.add(pnLbIntro, BorderLayout.NORTH);
        pnMain.add(pnCenter, BorderLayout.CENTER);
        pnMain.add(pnFooterTimKiem, BorderLayout.SOUTH);

        JPanel table = new JPanel(new GridLayout(1, 2));
        table.setBackground(new Color(211, 225, 237));

        tbCustomer = new JTable();
        tbCustomer.setBackground(new Color(211, 225, 237));
        tbHoaDonKH = new JTable();
        tbHoaDonKH.setBackground(new Color(211, 225, 237));
        tbCustomer.setFont(fontTbl);
        tbHoaDonKH.setFont(fontTbl);

        JScrollPane scrollPane1 = new JScrollPane(tbCustomer);
        scrollPane1.setBackground(new Color(211, 225, 237));
        scrollPane1.setBorder(BorderFactory.createTitledBorder("Danh sách Khách Hàng"));
        JScrollPane scrollPane2 = new JScrollPane(tbHoaDonKH);
        scrollPane2.setBackground(new Color(211, 225, 237));
        scrollPane2.setBorder(BorderFactory.createTitledBorder("Thông tin hóa đơn"));

        table.add(scrollPane1);
        table.add(scrollPane2);
        this.setLayout(new GridLayout(2, 1, 30, 30));
        this.add(pnMain);
        this.add(table);

        customerTbModel = new DefaultTableModel();
        customerTbModel.setColumnIdentifiers(header);
        tbCustomer.setModel(customerTbModel);
        khachHangBUS.loadDataKhachHangTable();

        hoaDonModel.setColumnIdentifiers(headerHoaDon);
        tbHoaDonKH.setModel(hoaDonModel);
        setVisible(true);
    }

    public void action() {
        btnClose.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnSort.addActionListener(this);
        btnFindKH.addActionListener(this);
        btnNew.addActionListener(this);
        tbCustomer.addMouseListener(this);
    }





    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj.equals(btnClose)) {
            if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thoát !") == JOptionPane.YES_OPTION) {
                MainForm.tpMain.remove(frmKhachHang);
                MainForm.tpMain.addTab("Giới Thiệu", null, frmThongTin);
                MainForm.tpMain.setSelectedComponent(frmThongTin);
            }
        } else if (obj.equals(btnUpdate)) {
            try {
                xuLyUpdateKH();

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }

        } else if (obj.equals(btnFindKH)) {
            try {
                khachHangBUS.timKiemKH();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        } else if (obj.equals(btnNew)) {
            try {
                tfNhapTimKiem.setText("");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        } else if (obj.equals(btnSort)) {
            khachHangBUS.xuLySapXep();
        }

    }



    // load dữ liệu từ csdl vào bảng

    public void xuLyUpdateKH() throws SQLException, ParseException {

        try {
            String gioiTinh;
            String maKH = tfId.getText().trim();
            String tenKH = tfName.getText().trim();
            String diaChi = tfAddress.getText().trim();
            String phone = tfPhone.getText().trim();
            if (rdoNam.isSelected()) {
                gioiTinh = "Nam";
            } else gioiTinh = "Nữ";
            java.sql.Date ngaySinhsql = null;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String ngaySinhtxt = dateFormat.format(date.getDate());
            java.util.Date ngaySinh = dateFormat.parse(ngaySinhtxt);
            ngaySinhsql = new java.sql.Date(ngaySinh.getTime());
            KhachHang khachHang = new KhachHang(maKH, tenKH, phone, diaChi, ngaySinhsql, gioiTinh);
            khachHangBUS.xuLyUpdateKH(khachHang);
            khachHangBUS.loadDataKhachHangTable();

        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }






    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int row = tbCustomer.getSelectedRow();
        String maKH = customerTbModel.getValueAt(row, 0).toString();

        KhachHang khachHang = khachHangDAO.layThongTinKhachHang(maKH);
        if (khachHang != null) {
            tfId.setText(khachHang.getMaKH());
            tfName.setText(khachHang.getTenKH());
            tfAddress.setText(khachHang.getDiaChi());
            tfPhone.setText(khachHang.getSoDT());
            date.setDate(khachHang.getNgaySinh());
            String gioiTinh = khachHang.getGioiTinh().toString().trim();
            if (gioiTinh.equalsIgnoreCase("Nam")) {
                rdoNam.setSelected(true);
                rdoNu.setSelected(false);
            } else {
                rdoNu.setSelected(true);
                rdoNam.setSelected(false);
            }
        } else JOptionPane.showMessageDialog(tbCustomer, "Loi");
        khachHangBUS.loadHoaDonCuaKH();

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
}


