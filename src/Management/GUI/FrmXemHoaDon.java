package Management.GUI;

import Management.DAO.ChiTietHDDAO;
import Management.DAO.DatabaseHelper;
import Management.DAO.HoaDonDAO;
import Management.DAO.KhachHangDAO;
import Management.DTO.ChiTietHD;
import Management.DTO.HoaDon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FrmXemHoaDon extends JPanel {

    private JTextField tfNhapTimKiem;
    private JButton btnFindHD;
    private JButton btnNewFind;
    private JRadioButton btnTimKiemByMaHD;
    private JRadioButton btnTimKiemByNgayLap;
    private JRadioButton btnTimKiemBytenKH;
    private JRadioButton btnTimKiemBytenNV;
    private JButton btnClose;
    private JTextField tfNhanVien;
    private JTextField tfMaHD;
    private JTextField tfNgayLap;
    private JTextField tfKhachHang;
    private JTextField tfTongTien;
    private ChiTietHDDAO chiTietHDDAO=new ChiTietHDDAO();


    private String[] headerDanhSachSHD=new String[]{
            "STT","Mã Hóa Đơn","Ngày lập","Tổng tiền","Tên Khách Hàng","Tên Nhân Viên"
    };

    private String[] headerChiTietHD=new String[]{
            "STT","Tên sản phẩm","Đơn vị tính","Đơn giá","Số lượng","Thành tiền"
    };
    private DefaultTableModel modelChiTiet=new DefaultTableModel();


    private DefaultTableModel modelDanhSach=new DefaultTableModel();
    private JTable tbChiTietHD;
    private JTable tbDanhSachHD;
    private ArrayList<HoaDon> hoaDonList;
    private KhachHangDAO khachHangDAO=new KhachHangDAO();
    private HoaDonDAO hoaDonDAO=new HoaDonDAO();


    public FrmXemHoaDon(){
        init();
        setVisible(true);
    }

    public void init(){

        Font fontIntro = new Font("Monospaced", Font.BOLD, 27);
        JLabel lbIntro = new JLabel("XEM HÓA ĐƠN", SwingConstants.CENTER);
        lbIntro.setFont(fontIntro);

        JPanel pnThongTinHoaDon=new JPanel(new GridLayout(5,1,10,10));

        JPanel pn1=new JPanel(new FlowLayout());
        JLabel lbMaHD=new JLabel("Mã Hóa Đơn:");
         tfMaHD=new JTextField(40);
        pn1.add(lbMaHD);
        pn1.add(tfMaHD);

        JPanel pn2=new JPanel(new FlowLayout());
        JLabel lbNgayLap=new JLabel("Ngày Lập     :");
         tfNgayLap=new JTextField(40);
        pn2.add(lbNgayLap);
        pn2.add(tfNgayLap);

        JPanel pn3=new JPanel(new FlowLayout());
        JLabel lbNhanVien=new JLabel("Nhân Viên    :");
         tfNhanVien=new JTextField(40);
        pn3.add(lbNhanVien);
        pn3.add(tfNhanVien);

        JPanel pn4=new JPanel(new FlowLayout());
        JLabel lbKhachHang=new JLabel("Khách hàng :");
         tfKhachHang=new JTextField(40);
        pn4.add(lbKhachHang);
        pn4.add(tfKhachHang);

        JPanel pn5=new JPanel(new FlowLayout());
        JLabel lbTongTien=new JLabel("Tổng tiền     :");
         tfTongTien=new JTextField(40);
        pn5.add(lbTongTien);
        pn5.add(tfTongTien);

        pnThongTinHoaDon.add(pn1);
        pnThongTinHoaDon.add(pn2);
        pnThongTinHoaDon.add(pn3);
        pnThongTinHoaDon.add(pn4);
        pnThongTinHoaDon.add(pn5);

        pnThongTinHoaDon.setBorder(BorderFactory.createTitledBorder("Thông tin hóa đơn"));

         tbChiTietHD=new JTable();
        JScrollPane scrollPane=new JScrollPane(tbChiTietHD);
        modelChiTiet.setColumnIdentifiers(headerChiTietHD);
        tbChiTietHD.setModel(modelChiTiet);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Thông tin chi tiết hóa đơn"));

        JPanel headerCenterpn=new JPanel(new GridLayout(1,2,10,10));
        headerCenterpn.add(pnThongTinHoaDon);
        headerCenterpn.add(scrollPane);
        headerCenterpn.setBorder(BorderFactory.createEtchedBorder());

        JPanel pnMainHeader=new JPanel(new BorderLayout(20,20));
        pnMainHeader.add(lbIntro,BorderLayout.NORTH);
        pnMainHeader.add(headerCenterpn,BorderLayout.CENTER);


         tbDanhSachHD=new JTable();
        JScrollPane scp=new JScrollPane(tbDanhSachHD);
        scp.setBorder(BorderFactory.createTitledBorder("Danh sách hóa đơn"));
        modelDanhSach.setColumnIdentifiers(headerDanhSachSHD);
        tbDanhSachHD.setModel(modelDanhSach);
        loadDataDanhSachHoaDonTable();

        JPanel pnFooterTimKiem = new JPanel(new GridLayout(2, 1, 20, 20));
        pnFooterTimKiem.setBorder(BorderFactory.createTitledBorder("Tìm kiếm Hóa đơn"));


        JPanel nhapThongTinTimKiem = new JPanel(new FlowLayout());
        JLabel lbMaKH = new JLabel("Nhập thông tin tìm kiếm:");
        tfNhapTimKiem = new JTextField(60);
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Image/Zoom-icon.png"));
        btnFindHD = new JButton("Tìm Kiếm", imageIcon);
        btnNewFind = new JButton("Làm Mới");
        btnClose=new JButton("Thoát");


        nhapThongTinTimKiem.add(lbMaKH);
        nhapThongTinTimKiem.add(tfNhapTimKiem);
        nhapThongTinTimKiem.add(btnFindHD);
        nhapThongTinTimKiem.add(btnNewFind);
        nhapThongTinTimKiem.add(btnClose);


        JPanel timKiemBy = new JPanel(new FlowLayout());

        JLabel lbBy = new JLabel("Tim theo:");
        btnTimKiemByMaHD = new JRadioButton("Mã Hóa Đơn");
        btnTimKiemBytenKH = new JRadioButton("Tên Khách hàng");
        btnTimKiemBytenNV = new JRadioButton("Tên Nhân viên");
        btnTimKiemByNgayLap = new JRadioButton("Ngày ");


        ButtonGroup timKiem = new ButtonGroup();
        timKiem.add(btnTimKiemByMaHD);
        timKiem.add(btnTimKiemBytenKH);
        timKiem.add(btnTimKiemBytenNV);
        timKiem.add(btnTimKiemByNgayLap);

        timKiemBy.add(lbBy);
        timKiemBy.add(btnTimKiemByMaHD);
        timKiemBy.add(btnTimKiemBytenKH);
        timKiemBy.add(btnTimKiemBytenNV);
        timKiemBy.add(btnTimKiemByNgayLap);


        pnFooterTimKiem.add(nhapThongTinTimKiem);
        pnFooterTimKiem.add(timKiemBy);

        JPanel footerCenterpn=new JPanel(new GridLayout(2,1,10,10));
        footerCenterpn.add(scp);
        footerCenterpn.add(pnFooterTimKiem);

        this.setLayout(new GridLayout(2,1,20,20));
        this.add(pnMainHeader);
        this.add(footerCenterpn);

        tbDanhSachHD.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row=tbDanhSachHD.getSelectedRow();
                String maHD=modelDanhSach.getValueAt(row,1).toString();
                String ngayLap=modelDanhSach.getValueAt(row,2).toString();
                String tongTien=modelDanhSach.getValueAt(row,3).toString();
                String khachHang= modelDanhSach.getValueAt(row,4).toString();
                String nhanVien= modelDanhSach.getValueAt(row,5).toString();

                tfMaHD.setText(maHD);
                tfNgayLap.setText(ngayLap);
                tfTongTien.setText(tongTien);
                tfNhanVien.setText(nhanVien);
                tfKhachHang.setText(khachHang);

                List<ChiTietHD> list=chiTietHDDAO.getChiTietHD(maHD);
                int i=1;
                modelChiTiet.setRowCount(0);
                for (ChiTietHD ct:list) {
                    modelChiTiet.addRow(new Object[]{
                            String.valueOf(i++),chiTietHDDAO.getTenSPbyMa(ct.getMaSP()),ct.getDonViTinh(), String.valueOf(ct.getDonGia()),
                            String.valueOf(ct.getSoLuong()), String.valueOf((ct.getDonGia()*ct.getSoLuong()))
                    });
                }
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

    public void loadDataDanhSachHoaDonTable() {
        try {
            Connection conn = DatabaseHelper.getConnection();
            String sql = "select * from hoadon";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            hoaDonList = new ArrayList<>();
            modelDanhSach.setRowCount(0);

            int i=1;
            while (rs.next()) {
                String[] row = new String[]{
                        String.valueOf(i++),rs.getString("mahd"),rs.getString("ngaylap"),
                        rs.getString("tongtien"), khachHangDAO.getTenKHbyMaHD(rs.getString("mahd")),
                        hoaDonDAO.getTenNVbyMa(rs.getString("manv"))
                };
                modelDanhSach.addRow(row);
            }
            modelDanhSach.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
