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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


import Management.DAO.DatabaseHelper;
import Management.DAO.HoaDonDAO;
import Management.DAO.KhachHangDAO;
import Management.DAO.NhanVienDAO;
import Management.DTO.HoaDon;
import Management.DTO.KhachHang;
import com.toedter.calendar.JDateChooser;

import static Management.GUI.MainForm.frmKhachHang;

public class FrmKhachHang extends JPanel implements ActionListener {
    private KhachHangDAO khachHangDAO=new KhachHangDAO();
    private HoaDonDAO hoaDonDAO=new HoaDonDAO();


    private DefaultTableModel customerTbModel;
    private DefaultTableModel hoaDonModel=new DefaultTableModel();
    private JTable tbCustomer;


    private String[] header = {"Mã Khách Hàng", "Tên Khách Hàng", "Địa Chỉ", "Số Điện Thoại", "Ngày Sinh", "Giới Tính"};
    private String[] headerHoaDon = {"Mã Hoá Đơn", "Ngày lập", "Tổng tiền", "Nhân Viên"};
    private ArrayList<KhachHang> customerList;
    private JTextField tfName;
    private JTextField tfPhone;
    private JTextField tfAddress;
    private JRadioButton rdoNam;
    private JRadioButton rdoNu;
    private JDateChooser date;
    private JTextField tfId;



    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClose;
    private JRadioButton btnTimKiemByMaKH;
    private JRadioButton btnTimKiemByName;
    private JRadioButton btnTimKiemBySDT;
    private JButton btnFindKH;
    private JButton btnNew;
    private JTextField tfNhapTimKiem;
    private ButtonGroup gender;
    private JTable tbHoaDonKH;

    public FrmKhachHang() {
        init();
    }

    public void init() {
        Font fontIntro = new Font("Monospaced", Font.BOLD, 27);
        JLabel lbIntro = new JLabel("THÔNG TIN KHÁCH HÀNG ", SwingConstants.CENTER);
        lbIntro.setFont(fontIntro);

        JPanel pnCenter = new JPanel(new FlowLayout());
        JPanel pnInputInfo = new JPanel(new GridLayout(3, 1, 20, 20));
        JPanel pnInfo1 = new JPanel(new FlowLayout());
        JLabel lbId = new JLabel("Mã KH:");
        tfId = new JTextField(30);
        JLabel lbName = new JLabel("Tên KH:");
        tfName = new JTextField(30);
        pnInfo1.add(lbId);
        pnInfo1.add(tfId);
        pnInfo1.add(lbName);
        pnInfo1.add(tfName);

        JPanel pnInfo2 = new JPanel(new FlowLayout());
        JLabel lbPhone = new JLabel("Số ĐT:");
        tfPhone = new JTextField(30);
        JLabel lbAddress = new JLabel("Địa chỉ:");
        tfAddress = new JTextField(30);
        pnInfo2.add(lbPhone);
        pnInfo2.add(tfPhone);
        pnInfo2.add(lbAddress);
        pnInfo2.add(tfAddress);

        JPanel pnInfo3 = new JPanel(new FlowLayout());
        gender = new ButtonGroup();
        rdoNam = new JRadioButton("Nam");
        rdoNu = new JRadioButton("Nữ");
        gender.add(rdoNam);
        gender.add(rdoNu);
        JLabel lbGender = new JLabel("Giới tính:");
        JLabel lbDate = new JLabel("Ngày sinh:");
        date = new JDateChooser();
        date.getCalendarButton().setEnabled(false);
        date.setLocale(Locale.forLanguageTag("vi-VN"));
        date.setDateFormatString("dd-MM-yyyy");
        date.getCalendarButton().setEnabled(true);
        date.setDate(new Date(System.currentTimeMillis()));

        pnInfo3.add(lbGender);
        pnInfo3.add(rdoNam);
        pnInfo3.add(rdoNu);
        pnInfo3.add(lbDate);
        pnInfo3.add(date);

        pnInputInfo.add(pnInfo1);
        pnInputInfo.add(pnInfo2);
        pnInputInfo.add(pnInfo3);


        JPanel pnButton = new JPanel(new GridLayout(3, 1, 20, 20));

        ImageIcon iconUpdate = new ImageIcon(getClass().getResource("/Image/Text-Edit-icon.png"));
        btnUpdate = new JButton("Cập nhật", iconUpdate);
        ImageIcon iconDelete = new ImageIcon(getClass().getResource("/Image/Button-Close-icon.png"));
        btnDelete = new JButton("Xóa", iconDelete);
        ImageIcon iconClsoe = new ImageIcon(getClass().getResource("/Image/Close-icon.png"));
        btnClose = new JButton("Thoát", iconClsoe);


        pnButton.add(btnUpdate);
        pnButton.add(btnDelete);
        pnButton.add(btnClose);


        pnCenter.add(pnInputInfo);
        pnCenter.add(pnButton);

        JPanel pnFooterTimKiem = new JPanel(new GridLayout(2, 1, 20, 20));
        pnFooterTimKiem.setBorder(BorderFactory.createTitledBorder("Tìm kiếm Khách Hàng"));


        JPanel nhapThongTinTimKiem = new JPanel(new FlowLayout());
        JLabel lbMaKH = new JLabel("Nhập thông tin tìm kiếm:");
         tfNhapTimKiem = new JTextField(20);
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Image/Zoom-icon.png"));
         btnFindKH = new JButton("Tìm Kiếm", imageIcon);
        ImageIcon iconNew = new ImageIcon("/Image/new-icon.png");
         btnNew = new JButton("Làm Mới");


        nhapThongTinTimKiem.add(lbMaKH);
        nhapThongTinTimKiem.add(tfNhapTimKiem);
        nhapThongTinTimKiem.add(btnFindKH);
        nhapThongTinTimKiem.add(btnNew);


        JPanel timKiemBy = new JPanel(new FlowLayout());

        JLabel lbBy = new JLabel("Tim theo:");
         btnTimKiemByMaKH = new JRadioButton("Mã KH");
         btnTimKiemByName = new JRadioButton("Họ tên");
         btnTimKiemBySDT = new JRadioButton("SDT");


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
        pnMain.add(lbIntro, BorderLayout.NORTH);
        pnMain.add(pnCenter, BorderLayout.CENTER);
        pnMain.add(pnFooterTimKiem, BorderLayout.SOUTH);

        JPanel table=new JPanel(new GridLayout(1,2));

        tbCustomer = new JTable();
        tbHoaDonKH=new JTable();

        JScrollPane scrollPane1 = new JScrollPane(tbCustomer);
        scrollPane1.setBorder(BorderFactory.createTitledBorder("Danh sách Khách Hàng"));
        JScrollPane scrollPane2 = new JScrollPane(tbHoaDonKH);
        scrollPane2.setBorder(BorderFactory.createTitledBorder("Thông tin hóa đơn"));

        table.add(scrollPane1);
        table.add(scrollPane2);
        this.setLayout(new GridLayout(2, 1, 30, 30));
        this.add(pnMain);
        this.add(table);

        customerTbModel = new DefaultTableModel();
        customerTbModel.setColumnIdentifiers(header);
        tbCustomer.setModel(customerTbModel);
        loadDataKhachHangTable();

        hoaDonModel.setColumnIdentifiers(headerHoaDon);
        tbHoaDonKH.setModel(hoaDonModel);

        setVisible(true);


        btnClose.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnFindKH.addActionListener(this);
        btnNew.addActionListener(this);

        // xử lý kích chuột vào 1 dòng trong bảng
        tbCustomer.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
                int row=tbCustomer.getSelectedRow();
                String maKH=customerTbModel.getValueAt(row, 0).toString();

                KhachHang khachHang=khachHangDAO.layThongTinKhachHang(maKH);
                if (khachHang!=null){
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
                }
                else JOptionPane.showMessageDialog(tbCustomer,"Loi");
                loadHoaDonCuaKH();
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


    public void actionPerformed(ActionEvent e){
        Object obj=e.getSource();
        if (obj.equals(btnClose)){
            if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thoát !") == JOptionPane.YES_OPTION){
                MainForm.tpMain.remove(frmKhachHang);
            }
        }
        else if (obj.equals(btnUpdate)){
            try {
                xuLyUpdateKH();
                loadDataKhachHangTable();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }

        }
        else if (obj.equals(btnDelete)){
            try {
                xuLyXoaKH();
                loadDataKhachHangTable();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        } else if (obj.equals(btnFindKH)) {
            try {
                timKiemKH();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        } else if (obj.equals(btnNew)) {
            try {
                tfNhapTimKiem.setText("");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        }

    }

    // load dữ liệu từ csdl vào bảng
    public void loadDataKhachHangTable() {
        try {
            Connection conn = DatabaseHelper.getConnection();
            String sql = "select * from khachhang";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            customerList = new ArrayList<>();
            customerTbModel.setRowCount(0);
            while (rs.next()) {
                String[] row = new String[]{
                        String.valueOf(rs.getString("makh")), rs.getString("tenkh"), rs.getString("diachi")
                        , rs.getString("sodt"), String.valueOf(rs.getDate("ngaysinh")), rs.getString("gioitinh")
                };
                customerTbModel.addRow(row);
            }
            customerTbModel.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void xuLyUpdateKH() throws SQLException, ParseException {
        int row = tbCustomer.getSelectedRow();
        try {
            if (row != -1) {
                
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
                StringBuilder errorss = new StringBuilder();
                if (tenKH.equalsIgnoreCase("")) {
                    errorss.append("Họ tên không được để trống\n");
                } else if (tenKH.length() > 40) {
                    errorss.append("Họ tên không hợp lệ!!\n");
                }
                if (diaChi.equalsIgnoreCase("")) {
                    errorss.append("Địa chỉ không được để trống\n");
                }
                if (gioiTinh.equalsIgnoreCase("")) {
                    errorss.append("Giới tính không được để trống\n");
                }
                if (phone.equalsIgnoreCase("")) {
                    errorss.append("SDT không được để trống\n");
                } else if (!phone.matches("^0[0-9]{9}$")) {
                    errorss.append("SDT không hợp lệ!\n");
                }
                int years = Integer.parseInt(ngaySinhtxt.substring(0, 4));

                if (LocalDate.now().getYear() - years > 120 || LocalDate.now().getYear() - years < 18) {
                    errorss.append("Ngày sinh không hợp lệ!\n");
                }

                if (errorss.isEmpty()) {
                    if (khachHangDAO.updateKhachHang(new KhachHang(maKH, tenKH, phone, diaChi, ngaySinhsql, gioiTinh))) {
                        JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                    } else JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
                } else JOptionPane.showMessageDialog(this, errorss.toString());
            }
            else JOptionPane.showMessageDialog(this,"Vui lòng chọn dòng để cập nhật !!");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (HeadlessException e) {
            throw new RuntimeException(e);
        }
    }
    public void xoaRong() {
        tfId.setText("");
        tfName.setText("");
        tfPhone.setText("");
        tfAddress.setText("");
        date.setDate(null);
        gender.clearSelection();
    }
    public void xuLyXoaKH() throws SQLException {
        int row = tbCustomer.getSelectedRow();
        if (row != -1) {
            if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa?") == JOptionPane.YES_OPTION) {
                if (khachHangDAO.xoaKhachHang(tfId.getText())){
                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
                    xoaRong();
                }
                else JOptionPane.showMessageDialog(this, "Xóa thất bại!");
            }
        }
        else JOptionPane.showMessageDialog(this,"Dữ liệu không được để trống. Vui lòng chọn dòng để xóa!!");
    }
    public void timKiemKH() {

        StringBuilder errors = new StringBuilder();
        if (btnTimKiemBySDT.isSelected() == false && btnTimKiemByMaKH.isSelected() == false && btnTimKiemByName.isSelected() == false
                && tfNhapTimKiem.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin và chọn loại tìm kiếm !", "Thông báo",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon("Image/warning-icon.png"));
        } else if (tfNhapTimKiem.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin tìm kiếm !", "Thông báo",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon("Image/warning-icon.png"));
        } else if (btnTimKiemBySDT.isSelected() == false && btnTimKiemByMaKH.isSelected() == false && btnTimKiemByName.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn loại tìm kiếm !", "Thông báo",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon("Image/warning-icon.png"));
        } else {
            if (btnTimKiemByMaKH.isSelected()) {
                getTimKiemKhachHangByMaKH(tfNhapTimKiem.getText());
            } else if (btnTimKiemByName.isSelected()) {
                getTimKiemKhachHangByTen(tfNhapTimKiem.getText());
            } else if (btnTimKiemBySDT.isSelected()) {
                getTimKiemKhachHangBySoDT(tfNhapTimKiem.getText());
            }
        }
    }
    public void getTimKiemKhachHangByTen(String ten) {
        ArrayList<KhachHang> list = null;
        try {
            list = (ArrayList<KhachHang>) khachHangDAO.findByTenKH(ten);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        customerTbModel = (DefaultTableModel) tbCustomer.getModel();
        customerTbModel.setRowCount(0);
        for (KhachHang khachhang : list) {
            customerTbModel.addRow(khachhang.toVector());
        }
    }
    public void getTimKiemKhachHangByMaKH(String maKH) {
        ArrayList<KhachHang> list = null;
        try {
            list = (ArrayList<KhachHang>) khachHangDAO.findByMaKH(maKH);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        customerTbModel = (DefaultTableModel) tbCustomer.getModel();
        customerTbModel.setRowCount(0);
        for (KhachHang khachhang : list) {
            customerTbModel.addRow(khachhang.toVector());
        }
    }
    public void getTimKiemKhachHangBySoDT(String soDT) {
        ArrayList<KhachHang> list = null;
        try {
            list = (ArrayList<KhachHang>) khachHangDAO.findBySDT(soDT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        customerTbModel = (DefaultTableModel) tbCustomer.getModel();
        customerTbModel.setRowCount(0);
        for (KhachHang khachhang : list) {
            customerTbModel.addRow(khachhang.toVector());
        }
    }

    public void loadHoaDonCuaKH(){
        int row=tbCustomer.getSelectedRow();
        String maKH= tbCustomer.getValueAt(row,0).toString();
        String maHD=khachHangDAO.getMaHDbyMaKH(maKH);
        HoaDon hoaDon=hoaDonDAO.layThongTinByMaHD(maHD);
        String tenNV=hoaDonDAO.getTenNVbyMa(hoaDon.getNhanVien());

        String [] info=new String[]{
                hoaDon.getMa(),hoaDon.getNgayLap(), String.valueOf(hoaDon.getTongTien()),tenNV
        };
        hoaDonModel.setRowCount(0);
        hoaDonModel.addRow(info);
        hoaDonModel.fireTableDataChanged();
    }
}



