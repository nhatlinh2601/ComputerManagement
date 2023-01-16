package Management.GUI;

import Management.DAO.DatabaseHelper;
import Management.DAO.SanPhamLKDAO;
import Management.DTO.NhanVien;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import static Management.GUI.MainForm.frmNhanVien;
import static Management.GUI.MainForm.frmSanPhamLK;

public class FrmSanPhamLK extends JPanel implements ActionListener {

    private ArrayList<SanPhamLK> sanPhamLKList;

    private DefaultTableModel spLKModel;

    private SanPhamLKDAO sanPhamLKDAO = new SanPhamLKDAO();
    private String[] header = new String[]{
            "Mã Sản Phẩm", "Tên Sản Phẩm", "Mô tả", "Giá", "Nhà Sản Xuất", "Số lượng",
            "Trạng Thái", "Đơn vị tính", "Ngày SX", "Số Đăng Ký"
    };

    private JRadioButton btnTimKiemByMaSP;
    private JRadioButton btnTimKiemBytenSP;
    private JRadioButton btnTimKiemByNSX;
    private JButton btnFindSP;
    private JTextField tfNhapTimKiem;
    private JButton btnNewFind;
    private JButton btnNew;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnAdd;
    private JButton btnClose;
    private JTextField tfId;
    private JTextField tfName;
    private JTextField tfPhanLoai;
    private JTextField tfPrice;
    private JTextField tfNSX;
    private JTextField tfDonViTinh;
    private JTextField tfSoDki;
    private JTextField tfTrangThai;
    private JTextArea tfMoTa;
    private JDateChooser dateSx;
    private JTable tblSanPhamLK;
    private JTextField tfSoLuong;


    public FrmSanPhamLK() {

        init();
        setVisible(true);
    }

    public void init() {


        Font fontIntro = new Font("Monospaced", Font.BOLD, 27);
        JLabel lbIntro = new JLabel("THÔNG TIN LINH KIỆN MÁY TÍNH", SwingConstants.CENTER);
        lbIntro.setFont(fontIntro);

        JPanel pnCenter = new JPanel(new FlowLayout());
//
//        JPanel pnInfo0=new JPanel(new BorderLayout());
//        ImageIcon icon=new ImageIcon("Image/Office-Girl-icon3.png");
//        lbImage=new JLabel(new ImageIcon("Image/Office-Girl-icon2.png"));
//        lbImage.setHorizontalAlignment(SwingConstants.CENTER);
//        btnMoHinh=new JButton("Open",icon);
//        pnInfo0.add(lbImage,BorderLayout.CENTER);
//        pnInfo0.add(btnMoHinh,BorderLayout.SOUTH);


        JPanel pnInputInfo = new JPanel(new GridLayout(3, 1));

        JPanel pnInfo1 = new JPanel(new FlowLayout());

        JLabel lbId = new JLabel("Mã SP:");
        tfId = new JTextField(15);
        JLabel lbName = new JLabel("Tên SP: ");
        tfName = new JTextField(15);
        JLabel lbSoLuong = new JLabel("Số lượng:");
        tfSoLuong = new JTextField(15);
        JLabel lbDonViTinh = new JLabel("Đơn vị tính:");
        tfDonViTinh = new JTextField(15);

        pnInfo1.add(lbId);
        pnInfo1.add(tfId);
        pnInfo1.add(lbName);
        pnInfo1.add(tfName);
        pnInfo1.add(lbSoLuong);
        pnInfo1.add(tfSoLuong);
        pnInfo1.add(lbDonViTinh);
        pnInfo1.add(tfDonViTinh);

        JPanel pnInfo2 = new JPanel(new FlowLayout());
        // gia,nsx,sodki,trang thai

        JLabel lbPrice = new JLabel("Price: ");
        tfPrice = new JTextField(15);
        JLabel lbNSX = new JLabel("Nhà SX: ");
        tfNSX = new JTextField(15);
        JLabel lbSoDki = new JLabel("Số Đăng Ký: ");
        tfSoDki = new JTextField(15);
        JLabel lbTrangThai = new JLabel("Trạng Thái:");
        tfTrangThai = new JTextField(15);

        pnInfo2.add(lbPrice);
        pnInfo2.add(tfPrice);
        pnInfo2.add(lbNSX);
        pnInfo2.add(tfNSX);
        pnInfo2.add(lbSoDki);
        pnInfo2.add(tfSoDki);
        pnInfo2.add(lbTrangThai);
        pnInfo2.add(tfTrangThai);

        JPanel pnInfo4 = new JPanel(new FlowLayout());
        JLabel lbMoTa = new JLabel("Mô tả:");
        tfMoTa = new JTextArea(4, 30);
        tfMoTa.setBorder(BorderFactory.createEtchedBorder());
        JLabel lbNgaySX = new JLabel("Ngày SX:");
        dateSx = new JDateChooser();
        dateSx.setDate(new Date(System.currentTimeMillis()));
        dateSx.setDateFormatString("dd-MM-yyyy");

        pnInfo4.add(lbNgaySX);
        pnInfo4.add(dateSx);
        pnInfo4.add(lbMoTa);
        pnInfo4.add(tfMoTa);

        pnInputInfo.add(pnInfo1);
        pnInputInfo.add(pnInfo2);
        pnInputInfo.add(pnInfo4);

        JPanel pnButton = new JPanel(new GridLayout(5, 1, 20, 20));
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

        pnCenter.add(pnInputInfo);
        pnCenter.add(pnButton);
        pnCenter.setBorder(BorderFactory.createEtchedBorder());

        JPanel pnFooterTimKiem = new JPanel(new GridLayout(2, 1, 10, 10));
        pnFooterTimKiem.setBorder(BorderFactory.createTitledBorder("Tìm kiếm Linh Kiện"));

        JPanel pnMain = new JPanel(new BorderLayout());
        pnMain.add(lbIntro, BorderLayout.NORTH);
        pnMain.add(pnCenter, BorderLayout.CENTER);
//        pnMain.add(pnFooterTimKiem,BorderLayout.SOUTH);
//        pnMain.add(pnInfo0,BorderLayout.WEST);


        JPanel nhapThongTinTimKiem = new JPanel(new FlowLayout());
        JLabel lbMaSP = new JLabel("Nhập thông tin tìm kiếm:");
        tfNhapTimKiem = new JTextField(20);
        ImageIcon imageIcons = new ImageIcon(getClass().getResource("/Image/Zoom-icon.png"));
        btnFindSP = new JButton("Tìm Kiếm", imageIcons);
        ImageIcon iconNewFind = new ImageIcon("/Image/new-icon.png");
        btnNewFind = new JButton("Làm Mới");


        nhapThongTinTimKiem.add(lbMaSP);
        nhapThongTinTimKiem.add(tfNhapTimKiem);
        nhapThongTinTimKiem.add(btnFindSP);
        nhapThongTinTimKiem.add(btnNewFind);


        JPanel timKiemBy = new JPanel(new FlowLayout());

        JLabel lbBy = new JLabel("Tim theo:");
        btnTimKiemByMaSP = new JRadioButton("Mã SP");
        btnTimKiemBytenSP = new JRadioButton("Tên SP");
        btnTimKiemByNSX = new JRadioButton("NSX");


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
        pnMainFooter.setBorder(BorderFactory.createEtchedBorder());
         tblSanPhamLK = new JTable();
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
        loadDataSanPhamTable();

        btnClose.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnNew.addActionListener(this);
        btnDelete.addActionListener(this);
        btnAdd.addActionListener(this);
        btnFindSP.addActionListener(this);
        btnNewFind.addActionListener(this);

        tblSanPhamLK.addMouseListener(new MouseListener() {
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
        });

    }

    public void loadDataSanPhamTable() {
        try {
            Connection conn = DatabaseHelper.getConnection();
            String sql = "select * from linhkienmt";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            sanPhamLKList = new ArrayList<>();
            spLKModel.setRowCount(0);
            while (rs.next()) {
                String[] row = new String[]{
                        String.valueOf(rs.getString("masp")), rs.getString("tensp"),
                        rs.getString("mota"), String.valueOf(rs.getFloat("gia")),
                        rs.getString("nsx"), String.valueOf(rs.getInt("soluong")),
                        rs.getString("trangthai"), rs.getString("donvitinh"),
                        String.valueOf(rs.getDate("ngaysx")), rs.getString("sodk")
                };
                spLKModel.addRow(row);
            }
            spLKModel.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj.equals(btnClose)) {
            if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thoát !") == JOptionPane.YES_OPTION) {
                MainForm.tpMain.remove(frmSanPhamLK);
            }
        } else if (obj.equals(btnNew)) {
            xoaRong();
        } else if (obj.equals(btnAdd)) {
            xuLyThemSP();
            loadDataSanPhamTable();
        } else if (obj.equals(btnDelete)) {
            try {
                xuLyXoaSP();
                loadDataSanPhamTable();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            loadDataSanPhamTable();

        } else if (obj.equals(btnUpdate)) {
            xuLyUpdateSP();
            loadDataSanPhamTable();
        } else if (obj.equals(btnFindSP)) {
            try {
                timKiemSP();
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

    public void timKiemSP() {
        StringBuilder errors = new StringBuilder();
        if (btnTimKiemByMaSP.isSelected() == false && btnTimKiemBytenSP.isSelected() == false && btnTimKiemByNSX.isSelected()
                 == false && tfNhapTimKiem.getText().trim().equals("")  ) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin và chọn loại tìm kiếm !", "Thông báo",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon("Image/warning-icon.png"));
        } else if (tfNhapTimKiem.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin tìm kiếm !", "Thông báo",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon("Image/warning-icon.png"));
        } else if (btnTimKiemByMaSP.isSelected() == false && btnTimKiemBytenSP.isSelected() == false &&
                btnTimKiemByNSX.isSelected()==false) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn loại tìm kiếm !", "Thông báo",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon("Image/warning-icon.png"));
        } else {
            if (btnTimKiemByMaSP.isSelected()) {
                getTimKiemNhanVienByMaSP(tfNhapTimKiem.getText());
            } else if (btnTimKiemBytenSP.isSelected()) {
                getTimKiemNhanVienByTenSP(tfNhapTimKiem.getText());
            } else if (btnTimKiemByNSX.isSelected()) {
                getTimKiemNhanVienByNSX(tfNhapTimKiem.getText());
            }
        }
    }


    public void getTimKiemNhanVienByMaSP(String maSP) {
        ArrayList<SanPhamLK> list = null;
        try {
            list = (ArrayList<SanPhamLK>) sanPhamLKDAO.findByMaSP(maSP);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        spLKModel = (DefaultTableModel) tblSanPhamLK.getModel();
        spLKModel.setRowCount(0);
        for (SanPhamLK sanPhamLK : list) {
            spLKModel.addRow(sanPhamLK.toVector());
        }
    }
    public void getTimKiemNhanVienByTenSP(String tenSP) {
        ArrayList<SanPhamLK> list = null;
        try {
            list = (ArrayList<SanPhamLK>) sanPhamLKDAO.findByTenSP(tenSP);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        spLKModel = (DefaultTableModel) tblSanPhamLK.getModel();
        spLKModel.setRowCount(0);
        for (SanPhamLK sanPhamLK : list) {
            spLKModel.addRow(sanPhamLK.toVector());
        }
    }

    public void getTimKiemNhanVienByNSX(String nsx) {
        ArrayList<SanPhamLK> list = null;
        try {
            list = (ArrayList<SanPhamLK>) sanPhamLKDAO.findByNSX(nsx);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        spLKModel = (DefaultTableModel) tblSanPhamLK.getModel();
        spLKModel.setRowCount(0);
        for (SanPhamLK sanPhamLK : list) {
            spLKModel.addRow(sanPhamLK.toVector());
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
        dateSx.setDate(null);
    }



    public void xuLyUpdateSP() {
        int row = tblSanPhamLK.getSelectedRow();
        try {
            if (row != -1) {

                String maSP = tfId.getText().trim();
                String tenSP = tfName.getText().trim();
                int soLuong = Integer.parseInt(tfSoLuong.getText().trim());
                String trangThai = tfTrangThai.getText().trim();
                String nsx = tfNSX.getText().trim();
                String donViTinh = tfDonViTinh.getText().trim();
                String moTa = tfMoTa.getText().trim();
                String soDangKi = tfSoDki.getText().trim();
                Float price = Float.valueOf(tfPrice.getText().trim());

                java.sql.Date ngaySX = null;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String ngaySXtxt = dateFormat.format(dateSx.getDate());
                java.util.Date ngaySinh = dateFormat.parse(ngaySXtxt);
                ngaySX = new java.sql.Date(ngaySinh.getTime());

                StringBuilder errorss = new StringBuilder();
                if (maSP.equalsIgnoreCase("")) {
                    errorss.append("masp không được để trống\n");
                }
                if (tenSP.equalsIgnoreCase("")) {
                    errorss.append("tensp không được để trống\n");
                }
                if (price.toString().equalsIgnoreCase("")) {
                    errorss.append("gia không được để trống\n");
                }

                if (errorss.isEmpty()) {
                    if (sanPhamLKDAO.updateSanPhamLK(new SanPhamLK(maSP, tenSP, moTa, nsx, soLuong, trangThai, donViTinh, soDangKi, price, ngaySX))) {
                        JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                    } else JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
                } else JOptionPane.showMessageDialog(this, errorss.toString());
            } else JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để cập nhật !!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void xuLyXoaSP() throws SQLException {
        int row = tblSanPhamLK.getSelectedRow();
        if (row != -1) {
            if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa?") == JOptionPane.YES_OPTION) {
                if (sanPhamLKDAO.xoaSanPhamLK(tfId.getText())){
                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
                    xoaRong();
                }
                else JOptionPane.showMessageDialog(this, "Xóa thất bại!");
            }
        }
        else JOptionPane.showMessageDialog(this,"Dữ liệu không được để trống. Vui lòng chọn dòng để xóa!!");
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


            java.sql.Date ngaySX = null;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String ngaySXtxt = dateFormat.format(dateSx.getDate());
            java.util.Date ngaySinh = dateFormat.parse(ngaySXtxt);
            ngaySX = new java.sql.Date(ngaySinh.getTime());

            StringBuilder errorss = new StringBuilder();
            if (maSP.equalsIgnoreCase("")) {
                errorss.append("masp không được để trống\n");
            }
            if (tenSP.equalsIgnoreCase("")) {
                errorss.append("tensp không được để trống\n");
            }
            if (tfPrice.getText().toString().equalsIgnoreCase("")) {
                errorss.append("gia không được để trống\n");
            }
            if (errorss.isEmpty()) {
                if (!checkTrungMaSP()&&!checkTrungTenSP()){
                    if (sanPhamLKDAO.themSanPhamLK(new SanPhamLK(maSP, tenSP, moTa, nsx,
                            Integer.parseInt(tfSoLuong.getText()), trangThai, donViTinh,
                            soDangKi,Float.parseFloat(tfPrice.getText()), ngaySX))) {
                        JOptionPane.showMessageDialog(this, "Thêm thành công");
                    } else JOptionPane.showMessageDialog(this, "Thêm thất bại");
                } else JOptionPane.showMessageDialog(this,"Sản phẩm đã tồn tại");
            } else JOptionPane.showMessageDialog(this, errorss.toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public boolean checkTrungMaSP() throws Exception {
        String maSP=tfId.getText().trim();
        boolean flag=false;
        for (SanPhamLK sanPhamLK:sanPhamLKDAO.getMaSP()) {
            if (sanPhamLK.getMaSP().equalsIgnoreCase(maSP)){
                flag=true;
            }
        }
        return flag;
    }

    public boolean checkTrungTenSP() throws Exception {
        String tenSP=tfName.getText().trim();
        boolean flag=false;
        for (SanPhamLK sanPhamLK:sanPhamLKDAO.getTenSP()) {
            if (sanPhamLK.getTenSP().equalsIgnoreCase(tenSP)){
                flag=true;
            }
        }
        return flag;
    }
}
