package Management.GUI;

import Management.DAO.ChiTietHDDAO;
import Management.DAO.DatabaseHelper;
import Management.DAO.HoaDonDAO;
import Management.DTO.*;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import static Management.GUI.MainForm.frmLapHoaDon;
import static Management.GUI.MainForm.frmNhanVien;

public class FrmLapHoaDon extends JPanel  {

    private JTextField tfIDHoaDon;
    private JTextField tfNgay;
    private JTextField tfNhanVien;
    private JTextField tfTenKH;
    private JTextField tfDiaChi;
    private JTextField tfSDT;
    private JRadioButton rdoNam;
    private JRadioButton rdoNu;
    ButtonGroup gender;
    private JComboBox<Object> cbTenSP;
    private JButton btnThem;
    private JButton btnThemHDMoi;
    private JButton btnThoat;
    private DefaultComboBoxModel cbxModel=new DefaultComboBoxModel<>();

    private HoaDonDAO hoaDonDAO=new HoaDonDAO();
    private String [] header=new String[]{
            "Mã SP"," Tên sản phẩm ","Nhà Sản Xuất","Đơn Vị tính","Đơn Giá","Số lượng","Thành Tiền"
    };
    private DefaultTableModel tbModel=new DefaultTableModel();
    private JButton btnXoa;
    private JDateChooser birthday;
    private JComboBox<Object> cbxNhanVien;
    private DefaultComboBoxModel modelNV=new DefaultComboBoxModel<>();
    private LocalDate localDate;
    private Date date;
    private JTable tbHoaDon;
    private JTextField tfTongTienSP;
    private JTextField tfTienNhan;
    private JTextField tfTienThoi;
    private JButton btnLamMoi;
    private JTextField tfSoLuong;
    private ChiTietHDDAO chiTietHDDAO=new ChiTietHDDAO();

    public FrmLapHoaDon(){

        init();
        setVisible(true);
    }

    public void init(){
        Font fontIntro = new Font("Monospaced", Font.BOLD, 27);
        JLabel lbIntro = new JLabel("LẬP HÓA ĐƠN", SwingConstants.CENTER);
        lbIntro.setFont(fontIntro);

        JPanel pnInfo1 = new JPanel(new FlowLayout());

        JLabel lbIDHoaDon = new JLabel("Mã Hóa Đơn:");
        tfIDHoaDon = new JTextField(10);
        JLabel lbNgay = new JLabel("Ngày: ");
        tfNgay = new JTextField(18);
         date=new java.util.Date();
        tfNgay.setText(date.toString());
        JLabel lbNhanVien= new JLabel("Nhân Viên:");
        cbxNhanVien=new JComboBox<>();
        loadCbxTenNV();
        JLabel lbTenKH = new JLabel("Tên Khách Hàng:");
        tfTenKH = new JTextField(18);

        pnInfo1.add(lbIDHoaDon);
        pnInfo1.add(tfIDHoaDon);
        pnInfo1.add(lbNgay);
        pnInfo1.add(tfNgay);
        pnInfo1.add(lbNhanVien);
        pnInfo1.add(cbxNhanVien);
        pnInfo1.add(lbTenKH);
        pnInfo1.add(tfTenKH);

        JPanel pnInfo2 = new JPanel(new FlowLayout());

        JLabel lbDiaChi = new JLabel("Địa Chỉ:");
        tfDiaChi = new JTextField(20);
        JLabel lbSDT = new JLabel("Số Điện Thoại: ");
        tfSDT = new JTextField(20);
        gender=new ButtonGroup();
        rdoNam=new JRadioButton("Nam");
        rdoNu=new JRadioButton("Nữ");
        gender.add(rdoNam);
        gender.add(rdoNu);
        JLabel lbGender=new JLabel("Giới tính:");
        JLabel lbBirthday=new JLabel("Ngày sinh:");

         birthday=new JDateChooser();
        birthday.setDate(new Date(System.currentTimeMillis()));
        birthday.setDateFormatString("dd-MM-yyyy");

        pnInfo2.add(lbDiaChi);
        pnInfo2.add(tfDiaChi);
        pnInfo2.add(lbSDT);
        pnInfo2.add(tfSDT);
        pnInfo2.add(lbGender);
        pnInfo2.add(rdoNam);
        pnInfo2.add(rdoNu);
        pnInfo2.add(lbBirthday);
        pnInfo2.add(birthday);

        JPanel pnInfo3 = new JPanel(new FlowLayout());
        JLabel lbTenSP=new JLabel("Tên Sản Phẩm:");
         cbTenSP=new JComboBox<>();
         loadCbxTenSP();
         JLabel lbSoLuong=new JLabel("Số lượng");
          tfSoLuong=new JTextField(5);
         btnThem=new JButton("Thêm vào danh sách");
        btnXoa=new JButton("Xóa khỏi danh sách");
        pnInfo3.add(lbTenSP);
        pnInfo3.add(cbTenSP);
        pnInfo3.add(lbSoLuong);
        pnInfo3.add(tfSoLuong);
        pnInfo3.add(btnThem);
        pnInfo3.add(btnXoa);

        JPanel pnInfo0 = new JPanel(new GridLayout(3,1,10,10));
        pnInfo0.add(pnInfo1);
        pnInfo0.add(pnInfo2);
        pnInfo0.add(pnInfo3);

        JPanel pnInfo=new JPanel(new BorderLayout(20,20));
        pnInfo.add(lbIntro,BorderLayout.NORTH);
        pnInfo.add(pnInfo0,BorderLayout.CENTER);
        pnInfo.setBorder(BorderFactory.createEtchedBorder());

         tbHoaDon=new JTable();
        JScrollPane scrollPane=new JScrollPane(tbHoaDon);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Thông tin Hóa Đơn"));
        tbModel.setColumnIdentifiers(header);
        tbHoaDon.setModel(tbModel);


        JLabel lbTongTienSP=new JLabel("Tổng tiền sản phẩm:");
         tfTongTienSP=new JTextField(15);
         tfTongTienSP.setText("0");
        JLabel lbTienNhan=new JLabel("Tiền nhận:");
         tfTienNhan=new JTextField(15);
        JButton btnTienThoi=new JButton("Tiền Thối:");
         tfTienThoi=new JTextField(15);

         btnThemHDMoi=new JButton("Thêm Hóa Đơn ");
         btnThoat=new JButton("Thoát");
         btnLamMoi=new JButton("Làm mới");


        JPanel pn1=new JPanel(new FlowLayout());
        pn1.add(lbTongTienSP);
        pn1.add(tfTongTienSP);
        pn1.add(lbTienNhan);
        pn1.add(tfTienNhan);

        JPanel pn2=new JPanel(new FlowLayout());
        pn2.add(btnTienThoi);
        pn2.add(tfTienThoi);

        JPanel pn3=new JPanel(new FlowLayout());
        pn3.add(btnThemHDMoi);
        pn3.add(btnLamMoi);
        pn3.add(btnThoat);

        JPanel pnThongTinChiTiet=new JPanel(new GridLayout(3,1,20,20));
        pnThongTinChiTiet.add(pn1);
        pnThongTinChiTiet.add(pn2);
        pnThongTinChiTiet.add(pn3);

        this.setLayout(new GridLayout(3,1,20,20));
        this.add(pnInfo);
        this.add(scrollPane);
        this.add(pnThongTinChiTiet);


        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder errors = new StringBuilder();
                if (tfSoLuong.getText().equals("")) {
                    errors.append("Số lượng không được để trống");
                }
                if (errors.isEmpty()) {
                    if (tfSoLuong.getText().matches("[0-9]")) {
                        String tenSP = String.valueOf(cbxModel.getSelectedItem());
                        loadDataSPvaoHoaDon(tenSP, Integer.parseInt(tfSoLuong.getText()));
                    }
                    else JOptionPane.showMessageDialog(btnThem,"Số lượng không hợp lệ. Mời nhập số lượng hợp lý!!");

                } else {
                    JOptionPane.showMessageDialog(btnThem,errors.toString());
                }
                String tongTien= String.valueOf(tinhTienHD());
                tfTongTienSP.setText(tongTien);

            }
        });

        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tbHoaDon.getRowCount()<=0){
                    JOptionPane.showMessageDialog(btnXoa,"Đơn hàng đang trống.");
                } else {
                    if (tbHoaDon.getSelectedRow() != -1) {

                        if (JOptionPane.showConfirmDialog(btnXoa, "Bạn chắc chắn muốn xóa sản phẩm?") == JOptionPane.YES_OPTION) {
                                tbModel.removeRow(tbHoaDon.getSelectedRow());
                                tbModel.fireTableDataChanged();
                        }
                    } else JOptionPane.showMessageDialog(btnXoa,"Dữ liệu không được để trống.Chọn dòng sản phẩm muốn xóa!!");

                    }
                String tongTien= String.valueOf(tinhTienHD());
                tfTongTienSP.setText(tongTien);
                }


        });

        btnThemHDMoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    xuLyThemKHHoaDon();
                    xuLyThemCTHD();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        btnTienThoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double tienThoi= 0.0;
                StringBuilder errors=new StringBuilder();
                if (tfTienNhan.getText().equals("")){
                    JOptionPane.showMessageDialog(btnTienThoi,"Tiền nhận rỗng");
                } else {
                    Double tienNhan= Double.valueOf(tfTienNhan.getText());
                    Double tongTien= Double.valueOf(tfTongTienSP.getText());
                    if (tienNhan<tongTien){
                        errors.append("Số tiền không đủ!!");
                    }
                    if (errors.isEmpty()){
                        tienThoi=tienNhan-tongTien;
                    }else {
                        JOptionPane.showMessageDialog(null,errors.toString());
                    }
                    tfTienThoi.setText(String.valueOf(tienThoi));
                }

            }
        });

        btnLamMoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xoaRong();
            }
        });

        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(btnThoat, "Bạn có chắc muốn thoát !") == JOptionPane.YES_OPTION) {
                    MainForm.tpMain.remove(frmLapHoaDon);
                }
            }
        });

    }


    public void loadCbxTenSP(){
        cbTenSP.removeAllItems();
        try {
            for (SanPhamLK sanPhamLK: hoaDonDAO.loadCbxTenSP()) {
                String tenSP=sanPhamLK.getTenSP();
                cbxModel.addElement(tenSP);
                cbTenSP.setModel(cbxModel);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void loadCbxTenNV(){
        cbxNhanVien.removeAllItems();
        try {
            for (NhanVien nhanVien: hoaDonDAO.loadCbxTenNV()) {
                String tenNV=nhanVien.getTenNV();
                modelNV.addElement(tenNV);
                cbxNhanVien.setModel(modelNV);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void loadDataSPvaoHoaDon(String tenSP,int soLuong) {
        try {
            Connection conn = DatabaseHelper.getConnection();
            String sql = "select * from linhkienmt where tensp=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,tenSP);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Float tongTien=soLuong*rs.getFloat("gia");
                String[] row = new String[]{
                        rs.getString("masp"),rs.getString("tensp"), rs.getString("nsx"),
                        rs.getString("donvitinh"), String.valueOf(rs.getFloat("gia")),
                        String.valueOf(soLuong), String.valueOf(tongTien)
                };
                tbModel.addRow(row);
            }
            tbModel.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void xuLyThemKHHoaDon() throws Exception {

        StringBuilder errors=new StringBuilder();
        String ngayLap=tfNgay.getText().trim();
        String gioiTinh;
        String maHD=tfIDHoaDon.getText().trim();
        String tenKH = tfTenKH.getText().trim();
        String diaChi = tfDiaChi.getText().trim();
        String phone = tfSDT.getText().trim();
        if (rdoNam.isSelected()) {
            gioiTinh = "Nam";
        } else gioiTinh = "Nữ";
        java.sql.Date ngaySinhsql = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String ngaySinhtxt = dateFormat.format(birthday.getDate());
        java.util.Date ngaySinh = dateFormat.parse(ngaySinhtxt);
        ngaySinhsql = new java.sql.Date(ngaySinh.getTime());
        StringBuilder errorss = new StringBuilder();

        if (maHD.equalsIgnoreCase("")) {
            errorss.append("Mã Hóa đơn không được để trống\n");
        }
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
            if (!checkTrungMaHD()){
                if (tbHoaDon.getRowCount()>0){
                    if (hoaDonDAO.addHoaDon(new HoaDon(maHD,ngayLap,Double.valueOf(tfTongTienSP.getText()),
                            hoaDonDAO.getMaNVbyTen(cbxNhanVien.getSelectedItem().toString())))) {
                        if (hoaDonDAO.addKhachHang(new KhachHang(tenKH,phone,diaChi,maHD,gioiTinh,ngaySinhsql))) {
                            JOptionPane.showMessageDialog(this, "Thêm HĐ thành công");
                        } else JOptionPane.showMessageDialog(this, "Thêm HĐ thất bại");
                    } else JOptionPane.showMessageDialog(this, "Thêm HD thất bại");
                } else JOptionPane.showMessageDialog(this,"Giỏ hàng đang trống, thêm sp trước khi lưu");
            } else JOptionPane.showMessageDialog(this,"Hóa đơn đã tồn tại");
        } else JOptionPane.showMessageDialog(this, errorss.toString());
    }

    public void xuLyThemCTHD() throws SQLException {
        int row=tbHoaDon.getRowCount();
        if (tbHoaDon.getRowCount()>0){
            for (int i = 0; i <row ; i++) {
                String maSP=tbModel.getValueAt(i,0).toString();
                String maHD=tfIDHoaDon.getText();
                String dvt=tbModel.getValueAt(i,3).toString();
                Float donGia= Float.parseFloat(tbModel.getValueAt(i,4).toString());
                int soLuong= Integer.parseInt(tbModel.getValueAt(i,5).toString());
                ChiTietHD chiTietHD=new ChiTietHD(maHD,maSP,dvt,donGia,soLuong);
                chiTietHDDAO.insertCTHD(chiTietHD);
            }
        }
    }

    public Double tinhTienHD(){
        Double tongTien= 0.0;
        int row= tbHoaDon.getRowCount();
        for (int i = 0; i < row; i++) {
            tongTien+=Double.parseDouble((String) tbHoaDon.getValueAt(i,6));
        }
        return tongTien;
    }

    public boolean checkTrungMaHD() throws Exception {
        String maHD=tfIDHoaDon.getText().trim();
        boolean flag=false;
        for (HoaDon hoaDon:hoaDonDAO.getMaHD()) {
            if (hoaDon.getMa().equalsIgnoreCase(maHD)){
                flag=true;
            }
        }
        return flag;
    }

    public void xoaRong(){

        tfIDHoaDon.setText("");
        tfTenKH.setText("");
        tfDiaChi.setText("");
        tfSDT.setText("");
        gender.clearSelection();
        tfSoLuong.setText("");
        tfTienNhan.setText("");
        tfTienThoi.setText("");
        tbModel.setRowCount(0);


    }




}



