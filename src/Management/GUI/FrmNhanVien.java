package Management.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import Management.DAO.DatabaseHelper;
import Management.DAO.NhanVienDAO;
import Management.DTO.KhachHang;
import Management.DTO.NhanVien;
import Management.DTO.TaiKhoan;
import com.toedter.calendar.JDateChooser;

import static Management.GUI.MainForm.frmKhachHang;
import static Management.GUI.MainForm.frmNhanVien;

public class FrmNhanVien extends JPanel implements ActionListener {
    private ArrayList<NhanVien> nhanVienList;
    private JTextField tfNhapTimKiem;
    private JButton btnFindNV;
    private JRadioButton btnTimKiemByName;
    private JRadioButton btnTimKiemBySDT;
    private JRadioButton btnTimKiemByMaNV;
    private JRadioButton btnTimKiemByCMND;
    private JButton btnNewFind;
    private JButton btnClose;
    private JLabel lbImage;
    private JButton btnMoHinh;
    private JTextField tfId;
    private JTextField tfName;
    private JTextField tfPhone;
    private JTextField tfAddress;
    private JTextField tfCMND;
    private JTextField tfUsername;
    private JComboBox cbxCaLam;
    private JRadioButton rdoNam;
    private JRadioButton rdoNu;
    private ButtonGroup gender;
    private JTable tblNhanVien;
    private JDateChooser date;
    private DefaultTableModel NVmodel;
    private DefaultComboBoxModel cbxModel = new DefaultComboBoxModel<>();
    private String[] header = {"Mã Nhân Viên", "Tên Nhân Viên", "CMND", "Số Điện Thoại",
            "Giới Tính", "Địa Chỉ", "Ngày Sinh", "Ca Làm Việc", "Username"};
    private NhanVienDAO nhanVienDAO = new NhanVienDAO();
    private JButton btnNew;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnAdd;
    private JComboBox<Object> cbxUsername;


    public FrmNhanVien() {

        init();
        setVisible(true);
    }

    public void init() {


        Font fontIntro = new Font("Monospaced", Font.BOLD, 27);
        JLabel lbIntro = new JLabel("THÔNG TIN NHÂN VIÊN", SwingConstants.CENTER);
        lbIntro.setFont(fontIntro);

        JPanel pnCenter = new JPanel(new FlowLayout());
        pnCenter.setBorder(BorderFactory.createEtchedBorder());
        JPanel pnInputInfo = new JPanel(new GridLayout(3, 1, 40, 40));

        JPanel pnInfo0 = new JPanel(new GridLayout(2, 1));

//        ImageIcon icon=new ImageIcon("Image/Office-Girl-icon3.png");
//
//        lbImage=new JLabel(new ImageIcon("Image/Office-Girl-icon2.png"));
//        btnMoHinh=new JButton("Open",icon);
//        pnInfo0.add(lbImage);
//        pnInfo0.add(btnMoHinh);

        JPanel pnInfo1 = new JPanel(new FlowLayout());
        JLabel lbId = new JLabel("Mã NV:");
        tfId = new JTextField(30);
        JLabel lbName = new JLabel("Tên NV:");
        tfName = new JTextField(30);
        pnInfo1.add(lbId);
        pnInfo1.add(tfId);
        pnInfo1.add(lbName);
        pnInfo1.add(tfName);


        JPanel pnInfo2 = new JPanel(new FlowLayout());
        JLabel lbPhone = new JLabel("Số ĐT:");
        tfPhone = new JTextField(20);
        JLabel lbAddress = new JLabel("Địa chỉ:");
        tfAddress = new JTextField(20);
        JLabel lbUsername = new JLabel("Username:");
        cbxUsername = new JComboBox<>();
        pnInfo2.add(lbPhone);
        pnInfo2.add(tfPhone);
        pnInfo2.add(lbAddress);
        pnInfo2.add(tfAddress);
        pnInfo2.add(lbUsername);
        pnInfo2.add(cbxUsername);

        JPanel pnInfo3 = new JPanel(new FlowLayout());
        JLabel lbCMND = new JLabel("CMND:");
        tfCMND = new JTextField(15);
        gender = new ButtonGroup();
        rdoNam = new JRadioButton("Nam");
        rdoNu = new JRadioButton("Nữ");
        gender.add(rdoNam);
        gender.add(rdoNu);
        JLabel lbGender = new JLabel("Giới tính:");
        JLabel lbDate = new JLabel("Ngày sinh:");
        date = new JDateChooser();
        date.setSize(25, 5);
        date.setDate(date.getDate());
        date.setDate(new Date(System.currentTimeMillis()));
        date.setDateFormatString("dd-MM-yyyy");
//        date.setDate();

        JLabel lbCaLamViec = new JLabel("Ca Làm Việc:");
        String[] string = new String[]{
                "Ca 1 (7h30-11h30)", "Ca 2 (13h00-17h00)", "Ca 3 (17h00-21h00)", "Ca 4 (Fulltime)"
        };
        cbxCaLam = new JComboBox<>();
        cbxCaLam.setModel(new DefaultComboBoxModel(string));

        pnInfo3.add(lbCMND);
        pnInfo3.add(tfCMND);
        pnInfo3.add(lbGender);
        pnInfo3.add(rdoNam);
        pnInfo3.add(rdoNu);
        pnInfo3.add(lbDate);
        pnInfo3.add(date);
        pnInfo3.add(lbCaLamViec);
        pnInfo3.add(cbxCaLam);


        pnInputInfo.add(pnInfo1);
        pnInputInfo.add(pnInfo2);
        pnInputInfo.add(pnInfo3);


        JPanel pnButton = new JPanel(new GridLayout(4, 1, 20, 20));
        ImageIcon iconNew = new ImageIcon(getClass().getResource("/Image/Actions-contact-new-icon.png"));
        btnNew = new JButton("Làm mới", iconNew);
        ImageIcon iconUpdate = new ImageIcon(getClass().getResource("/Image/Text-Edit-icon.png"));
        btnUpdate = new JButton("Cập nhật", iconUpdate);
        ImageIcon iconDelete = new ImageIcon(getClass().getResource("/Image/Button-Close-icon.png"));
        btnDelete = new JButton("Xóa", iconDelete);
        ImageIcon iconAdd = new ImageIcon(getClass().getResource("/Image/Actions-insert-table-icon.png"));
        btnAdd = new JButton("Lưu", iconAdd);
        ImageIcon iconClsoe = new ImageIcon(getClass().getResource("/Image/Close-icon.png"));
        btnClose = new JButton("Thoát", iconClsoe);

        pnButton.add(btnNew);
        pnButton.add(btnAdd);
        pnButton.add(btnUpdate);
        pnButton.add(btnDelete);
        pnButton.add(btnClose);

        pnCenter.add(pnInfo0);
        pnCenter.add(pnInputInfo);
        pnCenter.add(pnButton);

        JPanel pnFooterTimKiem = new JPanel(new GridLayout(2, 1, 20, 20));
        pnFooterTimKiem.setBorder(BorderFactory.createTitledBorder("Tìm kiếm Nhân Viên"));


        JPanel nhapThongTinTimKiem = new JPanel(new FlowLayout());
        JLabel lbMaKH = new JLabel("Nhập thông tin tìm kiếm:");
        tfNhapTimKiem = new JTextField(20);
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Image/Zoom-icon.png"));
        btnFindNV = new JButton("Tìm Kiếm", imageIcon);
        ImageIcon iconNewFind = new ImageIcon("/Image/new-icon.png");
        btnNewFind = new JButton("Làm Mới");


        nhapThongTinTimKiem.add(lbMaKH);
        nhapThongTinTimKiem.add(tfNhapTimKiem);
        nhapThongTinTimKiem.add(btnFindNV);
        nhapThongTinTimKiem.add(btnNewFind);


        JPanel timKiemBy = new JPanel(new FlowLayout());

        JLabel lbBy = new JLabel("Tim theo:");
        btnTimKiemByMaNV = new JRadioButton("Mã NV");
        btnTimKiemByName = new JRadioButton("Họ tên");
        btnTimKiemBySDT = new JRadioButton("SDT");
        btnTimKiemByCMND = new JRadioButton("CMND");


        ButtonGroup timKiem = new ButtonGroup();
        timKiem.add(btnTimKiemByMaNV);
        timKiem.add(btnTimKiemByName);
        timKiem.add(btnTimKiemBySDT);
        timKiem.add(btnTimKiemByCMND);

        timKiemBy.add(lbBy);
        timKiemBy.add(btnTimKiemByMaNV);
        timKiemBy.add(btnTimKiemByName);
        timKiemBy.add(btnTimKiemBySDT);
        timKiemBy.add(btnTimKiemByCMND);


        pnFooterTimKiem.add(nhapThongTinTimKiem);
        pnFooterTimKiem.add(timKiemBy);

        JPanel pnMain = new JPanel(new BorderLayout());
        pnMain.add(lbIntro, BorderLayout.NORTH);
        pnMain.add(pnCenter, BorderLayout.CENTER);



        tblNhanVien = new JTable();
        JScrollPane scrollPane = new JScrollPane(tblNhanVien);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách Nhân Viên"));
        JPanel pnMainFooter=new JPanel(new GridLayout(2,1,30,30));
        pnMainFooter.add(pnFooterTimKiem);
        pnMainFooter.add(scrollPane);
        this.setLayout(new GridLayout(2, 1));
        this.add(pnMain);
        this.add(pnMainFooter);
        loadCbxUsername();

        NVmodel = new DefaultTableModel();
        NVmodel.setColumnIdentifiers(header);
        tblNhanVien.setModel(NVmodel);
        loadDataNhanVienTable();

        btnClose.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnNew.addActionListener(this);
        btnDelete.addActionListener(this);
        btnAdd.addActionListener(this);
        btnFindNV.addActionListener(this);
        btnNewFind.addActionListener(this);


        tblNhanVien.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                int row = tblNhanVien.getSelectedRow();
                String maNV = NVmodel.getValueAt(row, 0).toString();

                NhanVien nhanVien = nhanVienDAO.layThongTinNhanVien(maNV);
                if (nhanVien != null) {
                    tfId.setText(nhanVien.getMaNV());
                    tfName.setText(nhanVien.getTenNV());
                    tfAddress.setText(nhanVien.getDiaChi());
                    tfPhone.setText(nhanVien.getSoDT());
                    date.setDate(nhanVien.getNgaySinh());
                    String gioiTinh = nhanVien.getGioiTinh().toString().trim();
                    if (gioiTinh.equalsIgnoreCase("Nam")) {
                        rdoNam.setSelected(true);
                        rdoNu.setSelected(false);
                    } else {
                        rdoNu.setSelected(true);
                        rdoNam.setSelected(false);
                    }
                    tfCMND.setText(nhanVien.getCMND());
                    cbxCaLam.setSelectedItem(nhanVien.getCaLamViec());
                    cbxUsername.setSelectedItem(nhanVien.getUsername());
                } else JOptionPane.showMessageDialog(tblNhanVien, "Loi");
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

    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj.equals(btnClose)) {
            if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thoát !") == JOptionPane.YES_OPTION) {
                MainForm.tpMain.remove(frmNhanVien);
            }
        } else if (obj.equals(btnNew)) {
            xoaRong();
        } else if (obj.equals(btnAdd)) {
            try {
                xuLyThemNV();
                loadDataNhanVienTable();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (obj.equals(btnDelete)) {
            try {
                xuLyXoaNV();
                loadDataNhanVienTable();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        } else if (obj.equals(btnUpdate)) {
            try {
                xuLyUpdateNV();
                loadDataNhanVienTable();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        } else if (obj.equals(btnFindNV)) {
            try {
                timKiemNV();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } else if (obj.equals(btnNewFind)) {
            try {
                tfNhapTimKiem.setText("");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void loadDataNhanVienTable() {
        try {
            Connection conn = DatabaseHelper.getConnection();
            String sql = "select * from nhanvien";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            nhanVienList = new ArrayList<>();
            NVmodel.setRowCount(0);
            while (rs.next()) {
                String[] row = new String[]{
                        String.valueOf(rs.getString("manv")), rs.getString("tennv"),
                        rs.getString("cmnd"), rs.getString("sodt"),
                        rs.getString("gioitinh"), rs.getString("diachi"),
                        String.valueOf(rs.getDate("ngaysinh")), rs.getString("calamviec"),
                        rs.getString("username")
                };
                NVmodel.addRow(row);
            }
            NVmodel.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void xuLyThemNV() throws SQLException {
        try {
            String gioiTinh;
            String maNV = tfId.getText().trim();
            String tenNV = tfName.getText().trim();
            String diaChi = tfAddress.getText().trim();
            String phone = tfPhone.getText().trim();
            String CMND = tfCMND.getText().trim();
            String caLamViec = cbxCaLam.getSelectedItem().toString();
            if (rdoNam.isSelected()) {
                gioiTinh = "Nam";
            } else gioiTinh = "Nữ";
            java.sql.Date ngaySinhsql = null;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String ngaySinhtxt = dateFormat.format(date.getDate());
            java.util.Date ngaySinh = dateFormat.parse(ngaySinhtxt);
            ngaySinhsql = new java.sql.Date(ngaySinh.getTime());
            String username = cbxUsername.getSelectedItem().toString();

            StringBuilder errorss = new StringBuilder();
            if (tenNV.equalsIgnoreCase("")) {
                errorss.append("Họ tên không được để trống\n");
            } else if (tenNV.length() > 40) {
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
            if (CMND.equalsIgnoreCase("")) {
                errorss.append("CMND không được để trống\n");
            } else if (!CMND.matches("^[0-9]{12}$")) {
                errorss.append("CMND không hợp lệ(CMND có 12 số)!\n");
            }
            if (caLamViec.equals("")) {
                errorss.append("Ca làm việc không được để trống!!");
            }
            if (username.equals("")) {
                errorss.append("Username không được để trống!!");
            }
            int years = Integer.parseInt(ngaySinhtxt.substring(0, 4));

            if (LocalDate.now().getYear() - years > 120 || LocalDate.now().getYear() - years < 18) {
                errorss.append("Ngày sinh không hợp lệ!\n");
            }
            if (errorss.isEmpty()) {
                if (!checkTrungMaNV()) {
                    if (nhanVienDAO.themNhanVien(new NhanVien(maNV, tenNV, phone, gioiTinh, diaChi, CMND, caLamViec, ngaySinhsql, username))) {
                        JOptionPane.showMessageDialog(this, "Thêm thành công");
                    } else JOptionPane.showMessageDialog(this, "Thêm thất bại");
                } else JOptionPane.showMessageDialog(this,"NV đã tồn tại!!");
            }
            else JOptionPane.showMessageDialog(this, errorss.toString());
            } catch(ParseException e){
                throw new RuntimeException(e);
            } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void xuLyUpdateNV() throws SQLException, ParseException {
        int row = tblNhanVien.getSelectedRow();
        try {
            if (row != -1) {

                String gioiTinh;
                String maNV = tfId.getText().trim();
                String tenNV = tfName.getText().trim();
                String diaChi = tfAddress.getText().trim();
                String phone = tfPhone.getText().trim();
                String CMND=tfCMND.getText().trim();
                String caLamViec= cbxCaLam.getSelectedItem().toString();
                if (rdoNam.isSelected()) {
                    gioiTinh = "Nam";
                } else gioiTinh = "Nữ";
                java.sql.Date ngaySinhsql = null;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String ngaySinhtxt = dateFormat.format(date.getDate());
                java.util.Date ngaySinh = dateFormat.parse(ngaySinhtxt);
                ngaySinhsql = new java.sql.Date(ngaySinh.getTime());
                StringBuilder errorss = new StringBuilder();
                if (tenNV.equalsIgnoreCase("")) {
                    errorss.append("Họ tên không được để trống\n");
                } else if (tenNV.length() > 40) {
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
                if (CMND.equalsIgnoreCase("")) {
                    errorss.append("CMND không được để trống\n");
                } else if (!CMND.matches("^[0-9]{12}$")) {
                    errorss.append("CMND không hợp lệ(CMND có 12 số)!\n");
                }
                if (caLamViec.equals("")){
                    errorss.append("Ca làm việc không được để trống!!");
                }
                int years = Integer.parseInt(ngaySinhtxt.substring(0, 4));

                if (LocalDate.now().getYear() - years > 120 || LocalDate.now().getYear() - years < 18) {
                    errorss.append("Ngày sinh không hợp lệ!\n");
                }

                if (errorss.isEmpty()) {
                    if ( nhanVienDAO.updateNhanVien(new NhanVien(maNV, tenNV,phone,gioiTinh,diaChi,CMND,caLamViec,ngaySinhsql,null))) {
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

    public void xuLyXoaNV() throws SQLException {
        int row = tblNhanVien.getSelectedRow();
        if (row != -1) {
            if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa?") == JOptionPane.YES_OPTION) {
                if (nhanVienDAO.xoaNhanVien(tfId.getText())){
                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
                    xoaRong();
                }
                else JOptionPane.showMessageDialog(this, "Xóa thất bại!");
            }
        }
        else JOptionPane.showMessageDialog(this,"Dữ liệu không được để trống. Vui lòng chọn dòng để xóa!!");
    }

    public void xoaRong() {
        tfId.setText("");
        tfName.setText("");
        tfPhone.setText("");
        tfAddress.setText("");
        tfCMND.setText("");
        date.setDate(null);

    }

    public void timKiemNV() {

        StringBuilder errors = new StringBuilder();
        if (btnTimKiemBySDT.isSelected() == false && btnTimKiemByMaNV.isSelected() == false && btnTimKiemByName.isSelected()
                == false &&btnTimKiemByCMND.isSelected() == false && tfNhapTimKiem.getText().trim().equals("")  ) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin và chọn loại tìm kiếm !", "Thông báo",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon("Image/warning-icon.png"));
        } else if (tfNhapTimKiem.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin tìm kiếm !", "Thông báo",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon("Image/warning-icon.png"));
        } else if (btnTimKiemBySDT.isSelected() == false && btnTimKiemByMaNV.isSelected() == false &&
                btnTimKiemByName.isSelected() == false&& btnTimKiemByCMND.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn loại tìm kiếm !", "Thông báo",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon("Image/warning-icon.png"));
        } else {
            if (btnTimKiemByMaNV.isSelected()) {
                getTimKiemNhanVienByMaNV(tfNhapTimKiem.getText());
            } else if (btnTimKiemByName.isSelected()) {
                getTimKiemNhanVienByTen(tfNhapTimKiem.getText());
            } else if (btnTimKiemBySDT.isSelected()) {
                getTimKiemNhanVienBySoDT(tfNhapTimKiem.getText());
            } else if (btnTimKiemByCMND.isSelected()) {
                getTimKiemNhanVienByCMND(tfNhapTimKiem.getText());
            }
        }
    }
    public void getTimKiemNhanVienByTen(String ten) {
        ArrayList<NhanVien> list = null;
        try {
            list = (ArrayList<NhanVien>) nhanVienDAO.findByTenNV(ten);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        NVmodel = (DefaultTableModel) tblNhanVien.getModel();
        NVmodel.setRowCount(0);
        for (NhanVien nhanVien : list) {
            NVmodel.addRow(nhanVien.toVector());
        }
    }
    public void getTimKiemNhanVienByMaNV(String maNV) {
        ArrayList<NhanVien> list = null;
        try {
            list = (ArrayList<NhanVien>) nhanVienDAO.findByMaNV(maNV);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        NVmodel = (DefaultTableModel) tblNhanVien.getModel();
        NVmodel.setRowCount(0);
        for (NhanVien nhanVien : list) {
            NVmodel.addRow(nhanVien.toVector());
        }
    }
    public void getTimKiemNhanVienBySoDT(String soDT) {
        ArrayList<NhanVien> list = null;
        try {
            list = (ArrayList<NhanVien>) nhanVienDAO.findBySDT(soDT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        NVmodel = (DefaultTableModel) tblNhanVien.getModel();
        NVmodel.setRowCount(0);
        for (NhanVien nhanVien : list) {
            NVmodel.addRow(nhanVien.toVector());
        }
    }

    public void getTimKiemNhanVienByCMND(String cmnd) {
        ArrayList<NhanVien> list = null;
        try {
            list = (ArrayList<NhanVien>) nhanVienDAO.findByCMND(cmnd);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        NVmodel = (DefaultTableModel) tblNhanVien.getModel();
        NVmodel.setRowCount(0);
        for (NhanVien nhanVien : list) {
            NVmodel.addRow(nhanVien.toVector());
        }
    }

    public void loadCbxUsername(){
        cbxUsername.removeAllItems();
        try {
            for (TaiKhoan taiKhoan:nhanVienDAO.loadCbxUsername()) {
                String username=taiKhoan.getUsername();
                cbxModel.addElement(username);
                cbxUsername.setModel(cbxModel);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public boolean checkTrungMaNV() throws Exception {
        String maNV=tfId.getText().trim();
        boolean flag=false;
        for (NhanVien nhanVien:nhanVienDAO.getMaNV()) {
            if (nhanVien.getMaNV().equalsIgnoreCase(maNV)){
                flag=true;
            }
        }
        return flag;
    }
}




