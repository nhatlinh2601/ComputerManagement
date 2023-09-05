package Management.GUI;

import Management.BUS.SanPhamLKBUS;
import Management.DAO.DatabaseHelper;
import Management.DAO.SanPhamLKDAO;
import Management.DTO.SanPhamLK;
import com.toedter.calendar.JDateChooser;
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
import java.util.ArrayList;
import java.util.Date;
import static Management.GUI.MainForm.frmSanPhamLK;

public class FrmSanPhamLK extends JPanel implements ActionListener, MouseListener {

    private ArrayList<SanPhamLK> sanPhamLKList;
    public static FrmThongTin frmThongTin = new FrmThongTin();

    public static DefaultTableModel spLKModel;

    private SanPhamLKDAO sanPhamLKDAO = new SanPhamLKDAO();
    private SanPhamLKBUS sanPhamLKBUS=new SanPhamLKBUS();
    private String[] header = new String[]{"Mã Sản Phẩm", "Tên Sản Phẩm", "Mô tả", "Giá bán", "Giá nhập", "Nhà Sản Xuất",
            "Số lượng nhập", "Trạng Thái", "Đơn vị tính", "Ngày SX", "Số Đăng Ký"};

    public static DefaultComboBoxModel sortModel = new DefaultComboBoxModel();

    public static JRadioButton btnTimKiemByMaSP;
    public static JRadioButton btnTimKiemBytenSP;
    public static JRadioButton btnTimKiemByNSX;
    private JButton btnFindSP;
    public static JTextField tfNhapTimKiem;
    private JButton btnNewFind;
    private JButton btnNew;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnAdd;
    private JButton btnClose;
    public static JTextField tfId;
    public static JTextField tfName;
    public static JTextField tfPrice;
    public static JTextField tfNSX;
    public static JTextField tfDonViTinh;
    public static JTextField tfSoDki;
    public static JTextField tfTrangThai;
    public static JTextArea tfMoTa;
    public static JDateChooser dateSx;
    public static JTable tblSanPhamLK;
    public static JTextField tfSoLuong;
    public static JTextField tfGiaNhap;
    private JButton btnSort;


    public FrmSanPhamLK() {

        initGUI();
        setVisible(true);
        action();
    }

    public void initGUI() {
        Font fontIntro = new Font("Monospaced", Font.BOLD, 32);
        Font fontTbl = new Font("SF Mono", Font.PLAIN, 16);
        Font fontText = new Font("SF Mono", Font.BOLD, 18);
        Font fontTf = new Font("Lato", Font.PLAIN, 18);
        JPanel pnLbIntro = new JPanel();
        pnLbIntro.setBackground(new Color(135, 172, 203));
        JLabel lbIntro = new JLabel("THÔNG TIN LINH KIỆN MÁY TÍNH", SwingConstants.CENTER);
        lbIntro.setFont(fontIntro);
        lbIntro.setBackground(new Color(135, 172, 203));
        lbIntro.setForeground(Color.white);
        pnLbIntro.add(lbIntro);

        JPanel pnCenter = new JPanel(new FlowLayout());
        pnCenter.setBackground(new Color(211, 225, 237));

        JPanel pnInputInfo = new JPanel(new GridLayout(3, 1));
        pnInputInfo.setBackground(new Color(211, 225, 237));

        JPanel pnInfo1 = new JPanel(new FlowLayout());
        pnInfo1.setBackground(new Color(211, 225, 237));

        JLabel lbId = new JLabel("Mã SP:");
        lbId.setFont(fontText);
        tfId = new JTextField(15);
        tfId.setFont(fontTf);
        JLabel lbName = new JLabel("Tên SP: ");
        lbName.setFont(fontText);
        tfName = new JTextField(15);
        tfName.setFont(fontTf);
        JLabel lbSoLuong = new JLabel("Số lượng:");
        lbSoLuong.setFont(fontText);
        tfSoLuong = new JTextField(15);
        tfSoLuong.setFont(fontTf);
        JLabel lbDonViTinh = new JLabel("Đơn vị tính:");
        lbDonViTinh.setFont(fontText);
        tfDonViTinh = new JTextField(15);
        tfDonViTinh.setFont(fontTf);

        pnInfo1.add(lbId);
        pnInfo1.add(tfId);
        pnInfo1.add(lbName);
        pnInfo1.add(tfName);
        pnInfo1.add(lbSoLuong);
        pnInfo1.add(tfSoLuong);
        pnInfo1.add(lbDonViTinh);
        pnInfo1.add(tfDonViTinh);

        JPanel pnInfo2 = new JPanel(new FlowLayout());
        pnInfo2.setBackground(new Color(211, 225, 237));
        // gia,nsx,sodki,trang thai

        JLabel lbPrice = new JLabel("Giá bán:");
        lbPrice.setFont(fontText);
        tfPrice = new JTextField(15);
        tfPrice.setFont(fontTf);
        JLabel lbNSX = new JLabel("Nhà SX: ");
        lbNSX.setFont(fontText);
        tfNSX = new JTextField(15);
        tfNSX.setFont(fontTf);
        JLabel lbSoDki = new JLabel("Đăng Ký  : ");
        lbSoDki.setFont(fontText);
        tfSoDki = new JTextField(15);
        tfSoDki.setFont(fontTf);
        tfSoLuong.setFont(fontTf);
        JLabel lbTrangThai = new JLabel("Trạng Thái:");
        lbTrangThai.setFont(fontText);
        tfTrangThai = new JTextField(15);
        tfTrangThai.setFont(fontTf);

        pnInfo2.add(lbPrice);
        pnInfo2.add(tfPrice);
        pnInfo2.add(lbNSX);
        pnInfo2.add(tfNSX);
        pnInfo2.add(lbSoDki);
        pnInfo2.add(tfSoDki);
        pnInfo2.add(lbTrangThai);
        pnInfo2.add(tfTrangThai);

        JPanel pnInfo4 = new JPanel(new FlowLayout());
        pnInfo4.setBackground(new Color(211, 225, 237));
        JLabel lbMoTa = new JLabel("Mô tả:");
        lbMoTa.setFont(fontText);
        tfMoTa = new JTextArea(4, 30);
        tfMoTa.setBorder(BorderFactory.createEtchedBorder());
        JLabel lbGiaNhap = new JLabel("Giá nhập:");
        lbGiaNhap.setFont(fontText);
        tfGiaNhap = new JTextField(10);
        tfGiaNhap.setFont(fontText);
        JLabel lbNgaySX = new JLabel("Ngày SX:");
        lbNgaySX.setFont(fontText);
        dateSx = new JDateChooser();
        dateSx.setDate(new Date(System.currentTimeMillis()));
        dateSx.setDateFormatString("dd-MM-yyyy");
        dateSx.setFont(fontText);
        dateSx.setPreferredSize(new Dimension(200, 30));

        btnSort = new JButton("Sắp xếp");
        btnSort.setFont(fontText);
        btnSort.setForeground(new Color(238, 238, 238));
        btnSort.setBackground(new Color(135, 172, 203));

        JComboBox sort = new JComboBox<>();
        sort.setModel(sortModel);
        sort.setFont(fontText);
        sortModel.addElement("Giá bán");
        sortModel.addElement("Giá nhập");
        sortModel.addElement("Mã sản phẩm");
        sortModel.addElement("Tên sản phẩm");


        pnInfo4.add(lbNgaySX);
        pnInfo4.add(dateSx);
        pnInfo4.add(lbGiaNhap);
        pnInfo4.add(tfGiaNhap);
        pnInfo4.add(lbMoTa);
        pnInfo4.add(tfMoTa);
        pnInfo4.add(sort);
        pnInfo4.add(btnSort);

        pnInputInfo.add(pnInfo1);
        pnInputInfo.add(pnInfo2);
        pnInputInfo.add(pnInfo4);

        JPanel pnButton = new JPanel(new GridLayout(5, 1, 20, 20));
        pnButton.setBackground(new Color(211, 225, 237));
        ImageIcon iconNew = new ImageIcon(getClass().getResource("/Image/Actions-contact-new-icon.png"));
        btnNew = new JButton("Làm mới", iconNew);
        btnNew.setFont(fontText);
        btnNew.setForeground(new Color(238, 238, 238));
        btnNew.setBackground(new Color(135, 172, 203));
        ImageIcon iconUpdate = new ImageIcon(getClass().getResource("/Image/Text-Edit-icon.png"));
        btnUpdate = new JButton("Cập nhật", iconUpdate);
        btnUpdate.setFont(fontText);
        btnUpdate.setForeground(new Color(238, 238, 238));
        btnUpdate.setBackground(new Color(135, 172, 203));
        ImageIcon iconDelete = new ImageIcon(getClass().getResource("/Image/Button-Close-icon.png"));
        btnDelete = new JButton("Xóa", iconDelete);
        btnDelete.setFont(fontText);
        btnDelete.setForeground(new Color(238, 238, 238));
        btnDelete.setBackground(new Color(135, 172, 203));
        ImageIcon iconAdd = new ImageIcon(getClass().getResource("/Image/Actions-insert-table-icon.png"));
        btnAdd = new JButton("Lưu", iconAdd);
        btnAdd.setFont(fontText);
        btnAdd.setForeground(new Color(238, 238, 238));
        btnAdd.setBackground(new Color(135, 172, 203));
        ImageIcon iconClsoe = new ImageIcon(getClass().getResource("/Image/Close-icon.png"));
        btnClose = new JButton("Thoát", iconClsoe);
        btnClose.setFont(fontText);
        btnClose.setForeground(new Color(238, 238, 238));
        btnClose.setBackground(new Color(135, 172, 203));

        pnButton.add(btnNew);
        pnButton.add(btnAdd);
        pnButton.add(btnUpdate);
        pnButton.add(btnDelete);
        pnButton.add(btnClose);

        pnCenter.add(pnInputInfo);
        pnCenter.add(pnButton);
        pnCenter.setBorder(BorderFactory.createEtchedBorder());

        JPanel pnFooterTimKiem = new JPanel(new GridLayout(2, 1, 10, 10));
        pnFooterTimKiem.setBackground(new Color(211, 225, 237));
        pnFooterTimKiem.setBorder(BorderFactory.createTitledBorder("Tìm kiếm Linh Kiện"));

        JPanel pnMain = new JPanel(new BorderLayout());
        pnMain.setBackground(new Color(211, 225, 237));
        pnMain.add(pnLbIntro, BorderLayout.NORTH);
        pnMain.add(pnCenter, BorderLayout.CENTER);


        JPanel nhapThongTinTimKiem = new JPanel(new FlowLayout());
        nhapThongTinTimKiem.setBackground(new Color(211, 225, 237));
        JLabel lbMaSP = new JLabel("Nhập thông tin tìm kiếm:");
        lbMaSP.setFont(fontText);
        tfNhapTimKiem = new JTextField(20);
        tfNhapTimKiem.setFont(fontTf);
        ImageIcon imageIcons = new ImageIcon(getClass().getResource("/Image/Zoom-icon.png"));
        btnFindSP = new JButton("Tìm Kiếm", imageIcons);
        btnFindSP.setFont(fontText);
        btnFindSP.setForeground(new Color(238, 238, 238));
        btnFindSP.setBackground(new Color(135, 172, 203));
        ImageIcon iconNewFind = new ImageIcon("/Image/new-icon.png");
        btnNewFind = new JButton("Làm Mới");
        btnNewFind.setFont(fontText);
        btnNewFind.setForeground(new Color(238, 238, 238));
        btnNewFind.setBackground(new Color(135, 172, 203));


        nhapThongTinTimKiem.add(lbMaSP);
        nhapThongTinTimKiem.add(tfNhapTimKiem);
        nhapThongTinTimKiem.add(btnFindSP);
        nhapThongTinTimKiem.add(btnNewFind);


        JPanel timKiemBy = new JPanel(new FlowLayout());
        timKiemBy.setBackground(new Color(211, 225, 237));

        JLabel lbBy = new JLabel("Tim theo:");
        lbBy.setFont(fontText);
        btnTimKiemByMaSP = new JRadioButton("Mã SP");
        btnTimKiemByMaSP.setFont(fontText);
        btnTimKiemByMaSP.setForeground(new Color(238, 238, 238));
        btnTimKiemByMaSP.setBackground(new Color(135, 172, 203));
        btnTimKiemBytenSP = new JRadioButton("Tên SP");
        btnTimKiemBytenSP.setFont(fontText);
        btnTimKiemBytenSP.setForeground(new Color(238, 238, 238));
        btnTimKiemBytenSP.setBackground(new Color(135, 172, 203));
        btnTimKiemByNSX = new JRadioButton("NSX");
        btnTimKiemByNSX.setFont(fontText);
        btnTimKiemByNSX.setForeground(new Color(238, 238, 238));
        btnTimKiemByNSX.setBackground(new Color(135, 172, 203));


        ButtonGroup timKiem = new ButtonGroup();
        timKiem.add(btnTimKiemByMaSP);
        timKiem.add(btnTimKiemBytenSP);
        timKiem.add(btnTimKiemByNSX);


        timKiemBy.add(lbBy);
        timKiemBy.add(btnTimKiemByMaSP);
        timKiemBy.add(btnTimKiemBytenSP);
        timKiemBy.add(btnTimKiemByNSX);


        pnFooterTimKiem.add(nhapThongTinTimKiem);
        pnFooterTimKiem.add(timKiemBy);

        JPanel pnMainFooter = new JPanel(new BorderLayout(10, 10));
        pnMainFooter.setBackground(new Color(211, 225, 237));
        pnMainFooter.setBorder(BorderFactory.createEtchedBorder());
        tblSanPhamLK = new JTable();
        tblSanPhamLK.setFont(fontTbl);
        tblSanPhamLK.setBackground(new Color(211, 225, 237));
        JScrollPane scrollPane = new JScrollPane(tblSanPhamLK);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách Linh Kiện"));
        pnMainFooter.add(pnFooterTimKiem, BorderLayout.SOUTH);
        pnMainFooter.add(scrollPane, BorderLayout.CENTER);

        this.setLayout(new GridLayout(2, 1, 10, 10));
        this.add(pnMain);
        this.add(pnMainFooter);

        spLKModel = new DefaultTableModel();
        spLKModel.setColumnIdentifiers(header);
        tblSanPhamLK.setModel(spLKModel);
        sanPhamLKBUS.loadDataSanPhamTable();


    }

    public void action() {
        btnClose.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnNew.addActionListener(this);
        btnDelete.addActionListener(this);
        btnAdd.addActionListener(this);
        btnFindSP.addActionListener(this);
        btnNewFind.addActionListener(this);
        tblSanPhamLK.addMouseListener(this);
        btnSort.addActionListener(this);
    }






    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj.equals(btnClose)) {
            if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thoát !") == JOptionPane.YES_OPTION) {
                MainForm.tpMain.remove(frmSanPhamLK);
                MainForm.tpMain.addTab("Giới Thiệu", null, frmThongTin);
                MainForm.tpMain.setSelectedComponent(frmThongTin);
            }
        } else if (obj.equals(btnNew)) {
            sanPhamLKBUS.xoaRong();
        } else if (obj.equals(btnAdd)) {
            xuLyThemSP();
            sanPhamLKBUS.loadDataSanPhamTable();
        } else if (obj.equals(btnDelete)) {
            try {
                sanPhamLKBUS.xuLyXoaSP(tfId.getText());
                sanPhamLKBUS.loadDataSanPhamTable();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            sanPhamLKBUS.loadDataSanPhamTable();

        } else if (obj.equals(btnUpdate)) {
            xuLyUpdateSP();
            sanPhamLKBUS.loadDataSanPhamTable();
        } else if (obj.equals(btnFindSP)) {
            try {
                sanPhamLKBUS.timKiemSP(tfNhapTimKiem.getText());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } else if (obj.equals(btnNewFind)) {
            try {
                tfNhapTimKiem.setText("");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } else if (obj.equals(btnSort)) {
            sanPhamLKBUS.xuLySapXep();
        }
    }



    public void xoaRong() {
        tfId.setText("");
        tfName.setText("");
        tfNSX.setText("");
        tfPrice.setText("");
        tfDonViTinh.setText("");
        tfMoTa.setText("");
        tfSoDki.setText("");
        tfTrangThai.setText("");
        tfSoLuong.setText("");
        tfGiaNhap.setText("");

    }


    public void xuLyUpdateSP() {
        int row = tblSanPhamLK.getSelectedRow();
        try {

            String maSP = tfId.getText().trim();
            String tenSP = tfName.getText().trim();
            String trangThai = tfTrangThai.getText().trim();
            String nsx = tfNSX.getText().trim();
            String donViTinh = tfDonViTinh.getText().trim();
            String moTa = tfMoTa.getText().trim();
            String soDangKi = tfSoDki.getText().trim();
            Float price = Float.valueOf(tfPrice.getText().trim());
            Float giaNhap = Float.valueOf(tfGiaNhap.getText().trim());
            int sl = Integer.parseInt(tfSoLuong.getText().trim());
            java.sql.Date ngaySX = null;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String ngaySXtxt = dateFormat.format(dateSx.getDate());
            java.util.Date ngaySinh = dateFormat.parse(ngaySXtxt);
            ngaySX = new java.sql.Date(ngaySinh.getTime());

            SanPhamLK sanPhamLK = new SanPhamLK(maSP, tenSP, moTa, nsx, sl, trangThai, donViTinh, soDangKi, price, ngaySX, giaNhap);
            sanPhamLKBUS.xuLyUpdateSP(sanPhamLK);


        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }



    public void xuLyThemSP() {
        try {

            String maSP = tfId.getText().trim();
            String tenSP = tfName.getText().trim();
            String trangThai = tfTrangThai.getText().trim();
            String nsx = tfNSX.getText().trim();
            String donViTinh = tfDonViTinh.getText().trim();
            String moTa = tfMoTa.getText().trim();
            String soDangKi = tfSoDki.getText().trim();
            Float giaNhap;
            int sl;
            if (tfGiaNhap.getText().trim().equalsIgnoreCase("")){
                 giaNhap= (float) 0;
            } else {
                 giaNhap = Float.valueOf(tfGiaNhap.getText().trim());
            }
            if (tfSoLuong.getText().trim().equalsIgnoreCase("")){
                 sl=0;
            } else {
                 sl = Integer.parseInt(tfSoLuong.getText().trim());
            }
            java.sql.Date ngaySX = null;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String ngaySXtxt = dateFormat.format(dateSx.getDate());
            java.util.Date ngaySinh = dateFormat.parse(ngaySXtxt);
            ngaySX = new java.sql.Date(ngaySinh.getTime());

            SanPhamLK sanPhamLK = new SanPhamLK(maSP, tenSP, moTa, nsx, sl, trangThai, donViTinh, soDangKi,
                    Float.valueOf(tfPrice.getText().trim()), ngaySX, giaNhap);
            sanPhamLKBUS.xuLyThemSP(sanPhamLK);


        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


        @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int row = tblSanPhamLK.getSelectedRow();
        String maSP = spLKModel.getValueAt(row, 0).toString();
        SanPhamLK sanPhamLK = sanPhamLKDAO.layThongTinSanPham(maSP);
        if (sanPhamLK != null) {
            tfId.setText(sanPhamLK.getMaSP());
            tfName.setText(sanPhamLK.getTenSP());
            tfNSX.setText(sanPhamLK.getNsx());
            tfPrice.setText(String.valueOf(sanPhamLK.getPrice()));
            dateSx.setDate(sanPhamLK.getNgaySX());
            tfTrangThai.setText(sanPhamLK.getTrangThai());
            tfSoDki.setText(sanPhamLK.getSoDK());
            tfSoLuong.setText(String.valueOf(sanPhamLK.getSoLuong()));
            tfMoTa.setText(sanPhamLK.getMoTa());
            tfDonViTinh.setText(sanPhamLK.getDonViTinh());
            tfGiaNhap.setText(String.valueOf(sanPhamLK.getGiaNhap()));
        } else JOptionPane.showMessageDialog(tblSanPhamLK, "Loi");
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