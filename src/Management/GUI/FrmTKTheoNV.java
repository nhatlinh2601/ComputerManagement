package Management.GUI;

import Management.BUS.TKTheoNVBUS;
import Management.DAO.HoaDonDAO;
import Management.DAO.TKTheoNVDAO;
import Management.DTO.NhanVien;
import Management.DTO.TKTheoNV;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FrmTKTheoNV extends JPanel implements ActionListener {


    private String[] header = new String[]{
            "STT", "Mã Hóa Đơn", "Tên Nhân Viên", "Ca làm việc", "Ngày lập", "Tổng Tiền", "Số lượng"
    };
    private HoaDonDAO hoaDonDAO = new HoaDonDAO();
    private TKTheoNVBUS tkTheoNVBUS=new TKTheoNVBUS();
    private TKTheoNVDAO tkTheoNVDAO = new TKTheoNVDAO();
    public static DefaultComboBoxModel cbxModel = new DefaultComboBoxModel<>();
    public static DefaultTableModel model = new DefaultTableModel();
    public static JTextField tfTongSlgSPDaBan;
    public static JTextField tfTongTienSPDaBan;
    public static JTextField tfTongSlgHoaDon;
    public static JTable tbDSTK;
    public static JDateChooser date;
    private JButton btnXem;
    private JButton btnDoanhThu;
    private JComboBox cbxTenNV;

    public FrmTKTheoNV() {
        initGUI();
        action();
    }

    public void action() {
        btnXem.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj.equals(btnXem)) {
            tkTheoNVBUS.xuLyXem();
        }
    }

    public void initGUI() {

        Font fontIntro = new Font("Monospaced", Font.BOLD, 32);
        Font fontTbl = new Font("SF Mono", Font.PLAIN, 16);
        Font fontText = new Font("SF Mono", Font.BOLD, 18);
        Font fontdate = new Font("SF Mono", Font.BOLD, 24);
        Font fontTf = new Font("Lato", Font.PLAIN, 18);
        setBackground(new Color(211, 225, 237));
        JPanel pnLbIntro = new JPanel();
        pnLbIntro.setBackground(new Color(135, 172, 203));
        JLabel lbIntro = new JLabel("THỐNG KÊ NHÂN VIÊN LẬP HÓA ĐƠN THEO NGÀY");
        lbIntro.setHorizontalAlignment(SwingConstants.CENTER);
        lbIntro.setFont(fontIntro);
        lbIntro.setForeground(Color.white);
        pnLbIntro.add(lbIntro);

        JPanel pnThongTinChung = new JPanel(new GridLayout(3, 1, 30, 30));
        pnThongTinChung.setBackground(new Color(211, 225, 237));
        pnThongTinChung.setBorder(BorderFactory.createTitledBorder("Thông tin chung"));

        JPanel pn1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pn1.setBackground(new Color(211, 225, 237));
        JLabel lbNgay = new JLabel("Ngày lập hóa đơn  ");
        lbNgay.setFont(fontText);
        date = new JDateChooser();
        date.setDateFormatString("dd-MM-yyyy");
        date.setFont(fontdate);
        date.setDate(new Date(System.currentTimeMillis()));
        date.setPreferredSize(new Dimension(200, 30));
        pn1.add(lbNgay);
        pn1.add(date);

        JPanel pn2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pn2.setBackground(new Color(211, 225, 237));
        JLabel lbTenNV = new JLabel("Nhân Viên  :");
        lbTenNV.setFont(fontText);
        cbxTenNV = new JComboBox<>();
        cbxTenNV.setModel(cbxModel);
        loadCbxTenNV();
        cbxTenNV.setFont(fontText);
        pn2.add(lbTenNV);
        pn2.add(cbxTenNV);

        JPanel pnXem = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnXem = new JButton("Xem");
        btnXem.setFont(fontText);
        btnXem.setBackground(new Color(135, 172, 203));
        btnDoanhThu = new JButton("Doanh thu NV");
        btnDoanhThu.setFont(fontText);
        btnDoanhThu.setBackground(new Color(135, 172, 203));
        pnXem.add(btnXem);
        pnXem.setBackground(new Color(211, 225, 237));


        pnThongTinChung.add(pn1);
        pnThongTinChung.add(pn2);
        pnThongTinChung.add(pnXem);


        JPanel pnThongKeCT = new JPanel(new GridLayout(3, 1, 30, 30));
        pnThongKeCT.setBackground(new Color(211, 225, 237));

        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p1.setBackground(new Color(211, 225, 237));

        JLabel lbTongSlgSPDaBan = new JLabel("Tổng số lượng sản phẩm đã bán:");
        lbTongSlgSPDaBan.setFont(fontText);
        tfTongSlgSPDaBan = new JTextField(30);
        tfTongSlgSPDaBan.setFont(fontTf);
        tfTongSlgSPDaBan.setEditable(false);
        p1.add(lbTongSlgSPDaBan);
        p1.add(tfTongSlgSPDaBan);

        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p2.setBackground(new Color(211, 225, 237));
        JLabel lbTongTienDaBan = new JLabel("Tổng tiền đã bán                             :");
        lbTongTienDaBan.setFont(fontText);
        tfTongTienSPDaBan = new JTextField(30);
        tfTongTienSPDaBan.setFont(fontTf);
        tfTongTienSPDaBan.setEditable(false);
        p2.add(lbTongTienDaBan);
        p2.add(tfTongTienSPDaBan);

        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p3.setBackground(new Color(211, 225, 237));
        JLabel lbTongSlgHoaDon = new JLabel("Tổng số lượng hóa đơn                :");
        lbTongSlgHoaDon.setFont(fontText);
        tfTongSlgHoaDon = new JTextField(30);
        tfTongSlgHoaDon.setFont(fontTf);
        tfTongSlgHoaDon.setEditable(false);
        p3.add(lbTongSlgHoaDon);
        p3.add(tfTongSlgHoaDon);

        pnThongKeCT.add(p1);
        pnThongKeCT.add(p2);
        pnThongKeCT.add(p3);

        pnThongKeCT.setBorder(BorderFactory.createTitledBorder("Thống kê chi tiết"));


        JPanel pnThongTin = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnThongTin.setBackground(new Color(211, 225, 237));
        pnThongTin.add(pnThongTinChung);
        pnThongTin.add(pnThongKeCT);

        tbDSTK = new JTable();
        model.setColumnIdentifiers(header);
        tbDSTK.setModel(model);
        tbDSTK.setFont(fontTbl);
        tbDSTK.setBackground(new Color(211, 225, 237));
        JScrollPane scrollPane = new JScrollPane(tbDSTK);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách hóa đơn"));

        JPanel pnMain = new JPanel(new GridLayout(2, 1));
        pnMain.setBackground(new Color(211, 225, 237));
        pnMain.add(pnThongTin);
        pnMain.add(scrollPane);

        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);

        this.setLayout(new BorderLayout());
        this.add(pnLbIntro, BorderLayout.NORTH);
        this.add(pnMain, BorderLayout.CENTER);
        model.setRowCount(0);
    }

    public void loadCbxTenNV() {
        cbxModel.removeAllElements();
        for (NhanVien nhanVien : tkTheoNVDAO.loadCbxTenNV()) {
            String tenNv = nhanVien.getTenNV();
            cbxModel.addElement(tenNv);
        }
        cbxModel.addElement("Tất cả");
    }


}