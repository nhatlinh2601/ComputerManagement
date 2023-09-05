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

import Management.BUS.NhanVienBUS;
import Management.DAO.DatabaseHelper;
import Management.DAO.NhanVienDAO;
import Management.DTO.NhanVien;
import Management.DTO.TaiKhoan;
import com.toedter.calendar.JDateChooser;
import static Management.GUI.MainForm.frmNhanVien;

public class FrmNhanVien extends JPanel implements ActionListener, MouseListener {

    private ArrayList<NhanVien> nhanVienList;
    public static FrmThongTin frmThongTin = new FrmThongTin();
    public static JTextField tfNhapTimKiem;
    private JButton btnFindNV;
    public static JRadioButton btnTimKiemByName;
    public static JRadioButton btnTimKiemBySDT;
    public static JRadioButton btnTimKiemByMaNV;
    public static JRadioButton btnTimKiemByCMND;
    private JButton btnNewFind;
    private JButton btnClose;
    public static JTextField tfId;
    public static JTextField tfName;
    public static JTextField tfPhone;
    public static JTextField tfAddress;
    public static JTextField tfCMND;
    private JComboBox cbxCaLam;
    private JRadioButton rdoNam;
    private JRadioButton rdoNu;
    private ButtonGroup gender;
    public static JTable tblNhanVien;
    private JDateChooser date;
    public static DefaultTableModel NVmodel;
    public static DefaultComboBoxModel cbxModel = new DefaultComboBoxModel<>();
    private String[] header = {"Mã Nhân Viên", "Tên Nhân Viên", "CMND", "Số Điện Thoại", "Giới Tính", "Địa Chỉ", "Ngày Sinh", "Ca Làm Việc", "Username"};
    private NhanVienDAO nhanVienDAO = new NhanVienDAO();
    private JButton btnNew;
    private JButton btnUpdate;
    private JButton btnDelete;
    protected JButton btnAdd;
    public static JComboBox<Object> cbxUsername;
    private JButton btnSort;
    public static JRadioButton rdoCaLamViec;
    public static JRadioButton rdoTen;
    public static JRadioButton rdoMa;
    public static NhanVienBUS nhanVienBUS=new NhanVienBUS();


    public FrmNhanVien() {

        initGUI();
        action();
        setVisible(true);
    }

    public void initGUI() {

        Font fontIntro = new Font("Monospaced", Font.BOLD, 32);
        Font fontTbl = new Font("SF Mono", Font.PLAIN, 16);
        JLabel lbIntro = new JLabel("THÔNG TIN NHÂN VIÊN", SwingConstants.CENTER);
        lbIntro.setFont(fontIntro);
        lbIntro.setBackground(new Color(211, 225, 237));
        lbIntro.setForeground(Color.white);

        JPanel pnCenter = new JPanel(new FlowLayout());
        pnCenter.setBorder(BorderFactory.createEtchedBorder());
        JPanel pnInputInfo = new JPanel(new GridLayout(3, 1, 40, 40));
        pnInputInfo.setBackground(new Color(211, 225, 237));

        JPanel pnInfo0 = new JPanel(new GridLayout(2, 1));

        Font fontText = new Font("SF Mono", Font.BOLD, 18);
        Font fontTf = new Font("Lato", Font.PLAIN, 18);
        JPanel pnInfo1 = new JPanel(new FlowLayout());
        pnInfo1.setBackground(new Color(211, 225, 237));
        JLabel lbId = new JLabel("Mã NV:");
        lbId.setFont(fontText);
        tfId = new JTextField(12);
        tfId.setFont(fontTf);


        JLabel lbName = new JLabel("Tên NV:");
        lbName.setFont(fontText);
        tfName = new JTextField(15);
        tfName.setFont(fontTf);

        JLabel lbCMND = new JLabel("CMND:");
        lbCMND.setFont(fontText);
        tfCMND = new JTextField(12);
        tfCMND.setFont(fontTf);

        pnInfo1.add(lbId);
        pnInfo1.add(tfId);
        pnInfo1.add(lbName);
        pnInfo1.add(tfName);
        pnInfo1.add(lbCMND);
        pnInfo1.add(tfCMND);


        JPanel pnInfo2 = new JPanel(new FlowLayout());
        pnInfo2.setBackground(new Color(211, 225, 237));
        JLabel lbPhone = new JLabel("Số ĐT:");
        lbPhone.setFont(fontText);
        tfPhone = new JTextField(15);
        tfPhone.setFont(fontTf);
        JLabel lbAddress = new JLabel("Địa chỉ:");
        lbAddress.setFont(fontText);
        tfAddress = new JTextField(15);
        tfAddress.setFont(fontTf);
        JLabel lbUsername = new JLabel("Username:");
        lbUsername.setFont(fontText);
        cbxUsername = new JComboBox<>();
        cbxUsername.setFont(fontTf);
        pnInfo2.add(lbPhone);
        pnInfo2.add(tfPhone);
        pnInfo2.add(lbAddress);
        pnInfo2.add(tfAddress);
        pnInfo2.add(lbUsername);
        pnInfo2.add(cbxUsername);

        JPanel pnInfo3 = new JPanel(new FlowLayout());
        pnInfo3.setBackground(new Color(211, 225, 237));

        gender = new ButtonGroup();
        rdoNam = new JRadioButton("Nam");
        rdoNam.setFont(fontText);
        rdoNam.setForeground(new Color(238, 238, 238));
        rdoNam.setBackground(new Color(135, 172, 203));
        rdoNu = new JRadioButton("Nữ");
        rdoNu.setForeground(new Color(238, 238, 238));
        rdoNu.setFont(fontText);
        rdoNu.setBackground(new Color(135, 172, 203));
        gender.add(rdoNam);
        gender.add(rdoNu);
        JLabel lbGender = new JLabel("Giới tính:");
        lbGender.setFont(fontText);
        JLabel lbDate = new JLabel("Ngày sinh:");
        lbDate.setFont(fontText);
        date = new JDateChooser();
        date.setSize(25, 5);
        date.setDate(date.getDate());
        date.setDate(new Date(System.currentTimeMillis()));
        date.setDateFormatString("dd-MM-yyyy");
        date.setFont(fontText);
        date.setPreferredSize(new Dimension(100, 30));
//        date.setDate();

        JLabel lbCaLamViec = new JLabel("Ca Làm Việc:");
        lbCaLamViec.setFont(fontText);
        String[] string = new String[]{"Ca 1 (7h30-11h30)", "Ca 2 (13h00-17h00)", "Ca 3 (17h00-21h00)", "Ca 4 (Fulltime)"};

        cbxCaLam = new JComboBox<>();
        cbxCaLam.setModel(new DefaultComboBoxModel(string));
        cbxCaLam.setFont(fontTf);

        btnSort = new JButton("Sắp xếp");
        btnSort.setFont(fontText);
        btnSort.setBackground(new Color(135, 172, 203));
        btnSort.setForeground(new Color(238, 238, 238));

        rdoMa = new JRadioButton("Mã");
        rdoMa.setFont(fontText);
        rdoMa.setBackground(new Color(135, 172, 203));
        rdoMa.setForeground(new Color(238, 238, 238));
        rdoTen = new JRadioButton("Tên");
        rdoTen.setFont(fontText);
        rdoTen.setBackground(new Color(135, 172, 203));
        rdoTen.setForeground(new Color(238, 238, 238));
        rdoCaLamViec = new JRadioButton("Ca làm việc");
        rdoCaLamViec.setFont(fontText);
        rdoCaLamViec.setBackground(new Color(135, 172, 203));
        rdoCaLamViec.setForeground(new Color(238, 238, 238));

        JPanel pnSort = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnSort.setBackground(new Color(211, 225, 237));
        pnSort.add(rdoMa);
        pnSort.add(rdoTen);
        pnSort.add(rdoCaLamViec);

        ButtonGroup sort = new ButtonGroup();
        sort.add(rdoMa);
        sort.add(rdoTen);
        sort.add(rdoCaLamViec);


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
        pnButton.setBackground(new Color(211, 225, 237));
        ImageIcon iconNew = new ImageIcon(getClass().getResource("/Image/Actions-contact-new-icon.png"));
        btnNew = new JButton("Làm mới", iconNew);
        btnNew.setFont(fontText);
        btnNew.setBackground(new Color(135, 172, 203));
        btnNew.setForeground(new Color(238, 238, 238));

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

        ImageIcon iconAdd = new ImageIcon(getClass().getResource("/Image/Actions-insert-table-icon.png"));
        btnAdd = new JButton("Lưu", iconAdd);
        btnAdd.setFont(fontText);
        btnAdd.setBackground(new Color(135, 172, 203));
        btnAdd.setForeground(new Color(238, 238, 238));

        ImageIcon iconClsoe = new ImageIcon(getClass().getResource("/Image/Close-icon.png"));
        btnClose = new JButton("Thoát", iconClsoe);
        btnClose.setFont(fontText);
        btnClose.setBackground(new Color(135, 172, 203));
        btnClose.setForeground(new Color(238, 238, 238));


        pnButton.add(btnNew);
        pnButton.add(btnAdd);
        pnButton.add(btnUpdate);
        pnButton.add(btnDelete);
        pnButton.add(btnSort);
        pnButton.add(btnClose);

        JPanel pnButton2 = new JPanel(new BorderLayout());
        pnButton2.add(pnButton, BorderLayout.CENTER);
        pnButton2.add(pnSort, BorderLayout.SOUTH);

        pnCenter.add(pnInfo0);
        pnCenter.add(pnInputInfo);
        pnCenter.add(pnButton2);

        JPanel pnFooterTimKiem = new JPanel(new GridLayout(2, 1, 20, 20));
        pnFooterTimKiem.setBorder(BorderFactory.createTitledBorder("Tìm kiếm Nhân Viên"));
        pnFooterTimKiem.setFont(fontText);
        pnFooterTimKiem.setBackground(new Color(211, 225, 237));


        JPanel nhapThongTinTimKiem = new JPanel(new FlowLayout());
        nhapThongTinTimKiem.setBackground(new Color(211, 225, 237));
        JLabel lbMaKH = new JLabel("Nhập thông tin tìm kiếm:");
        lbMaKH.setFont(fontText);
        tfNhapTimKiem = new JTextField(20);
        tfNhapTimKiem.setFont(fontTf);
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Image/Zoom-icon.png"));
        btnFindNV = new JButton("Tìm Kiếm", imageIcon);
        btnFindNV.setFont(fontText);
        btnFindNV.setForeground(new Color(238, 238, 238));
        btnFindNV.setBackground(new Color(135, 172, 203));
        ImageIcon iconNewFind = new ImageIcon("/Image/new-icon.png");
        btnNewFind = new JButton("Làm Mới");
        btnNewFind.setFont(fontText);
        btnNewFind.setForeground(new Color(238, 238, 238));
        btnNewFind.setBackground(new Color(135, 172, 203));


        nhapThongTinTimKiem.add(lbMaKH);
        nhapThongTinTimKiem.add(tfNhapTimKiem);
        nhapThongTinTimKiem.add(btnFindNV);
        nhapThongTinTimKiem.add(btnNewFind);


        JPanel timKiemBy = new JPanel(new FlowLayout());
        timKiemBy.setBackground(new Color(211, 225, 237));

        JLabel lbBy = new JLabel("Tim theo:");
        lbBy.setFont(fontText);
        btnTimKiemByMaNV = new JRadioButton("Mã NV");
        btnTimKiemByMaNV.setFont(fontText);
        btnTimKiemByMaNV.setForeground(new Color(238, 238, 238));
        btnTimKiemByMaNV.setBackground(new Color(135, 172, 203));
        btnTimKiemByName = new JRadioButton("Họ tên");
        btnTimKiemByName.setFont(fontText);
        btnTimKiemByName.setForeground(new Color(238, 238, 238));
        btnTimKiemByName.setBackground(new Color(135, 172, 203));
        btnTimKiemBySDT = new JRadioButton("SDT");
        btnTimKiemBySDT.setFont(fontText);
        btnTimKiemBySDT.setForeground(new Color(238, 238, 238));
        btnTimKiemBySDT.setBackground(new Color(135, 172, 203));
        btnTimKiemByCMND = new JRadioButton("CMND");
        btnTimKiemByCMND.setFont(fontText);
        btnTimKiemByCMND.setForeground(new Color(238, 238, 238));
        btnTimKiemByCMND.setBackground(new Color(135, 172, 203));

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
        tblNhanVien.setFont(fontTbl);
        tblNhanVien.setBackground(new Color(211, 225, 237));
        JScrollPane scrollPane = new JScrollPane(tblNhanVien);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách Nhân Viên"));
        scrollPane.setBackground(new Color(211, 225, 237));
        JPanel pnMainFooter = new JPanel(new GridLayout(2, 1, 30, 30));
        pnMainFooter.add(scrollPane);
        pnMainFooter.add(pnFooterTimKiem);

        this.setLayout(new GridLayout(2, 1));
        this.add(pnMain);
        this.add(pnMainFooter);
        pnMain.setBackground(SystemColor.inactiveCaption);
        pnMainFooter.setBackground(SystemColor.inactiveCaption);
        pnCenter.setBackground(SystemColor.inactiveCaption);

        pnMain.setBackground(new Color(135, 172, 203));
        pnMainFooter.setBackground(new Color(211, 225, 237));
        pnCenter.setBackground(new Color(211, 225, 237));

        loadCbxUsername();

        NVmodel = new DefaultTableModel();
        NVmodel.setColumnIdentifiers(header);

        tblNhanVien.setModel(NVmodel);
        nhanVienBUS.loadDataNhanVienTable();

    }


    public void action() {

        btnClose.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnNew.addActionListener(this);
        btnDelete.addActionListener(this);
        btnAdd.addActionListener(this);
        btnFindNV.addActionListener(this);
        btnNewFind.addActionListener(this);
        btnSort.addActionListener(this);
        tblNhanVien.addMouseListener(this);

    }


    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj.equals(btnClose)) {
            if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thoát !") == JOptionPane.YES_OPTION) {
                MainForm.tpMain.remove(frmNhanVien);
                MainForm.tpMain.addTab("Giới Thiệu", null, frmThongTin);
                MainForm.tpMain.setSelectedComponent(frmThongTin);
            }
        } else if (obj.equals(btnNew)) {
            nhanVienBUS.xoaRong();
        } else if (obj.equals(btnSort)) {
            nhanVienBUS.xuLySapXep();
        } else if (obj.equals(btnAdd)) {
            try {
                xuLyThemNV();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (obj.equals(btnDelete)) {
            try {
                nhanVienBUS.xuLyXoaNV(tfId.getText());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        } else if (obj.equals(btnUpdate)) {
            try {
                xuLyUpdateNV();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        } else if (obj.equals(btnFindNV)) {
            try {
               nhanVienBUS.xuLyTimKiemNV();
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
            NhanVien nhanVien = new NhanVien(maNV, tenNV, phone, gioiTinh, diaChi, CMND, caLamViec, ngaySinhsql, username);
            nhanVienBUS.xuLyThemNV(nhanVien);
            nhanVienBUS.loadDataNhanVienTable();


        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }



        public void xuLyUpdateNV() throws SQLException, ParseException {
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
                NhanVien nhanVien = new NhanVien(maNV, tenNV, phone, gioiTinh, diaChi, CMND, caLamViec, ngaySinhsql, username);
                nhanVienBUS.xuLyUpdateNV(nhanVien);
                nhanVienBUS.loadDataNhanVienTable();


            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
    }

    public void loadCbxUsername() {
        cbxUsername.removeAllItems();
        try {
            for (TaiKhoan taiKhoan : nhanVienDAO.loadCbxUsername()) {
                String username = taiKhoan.getUsername();
                cbxModel.addElement(username);
                cbxUsername.setModel(cbxModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

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
}

