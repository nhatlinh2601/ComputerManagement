package Management.GUI;


import Management.BUS.TKTinhTrangSPBUS;
import Management.DAO.TKTheoNVDAO;
import Management.DAO.TKTinhTrangSPDAO;
import Management.DTO.TKTinhTrangSP;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FrmTKTinhTrangSP extends JPanel implements ActionListener {
    private String[] header = new String[]{
            "STT", "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Nhà Sản Xuất", "Đơn giá"
    };

    private TKTinhTrangSPDAO tkTinhTrangSPDAO = new TKTinhTrangSPDAO();
    private TKTheoNVDAO tkTheoNVDAO = new TKTheoNVDAO();
    private TKTinhTrangSPBUS tkTinhTrangSPBUS = new TKTinhTrangSPBUS();
    public static DefaultComboBoxModel cbxModel = new DefaultComboBoxModel<>();
    public static DefaultTableModel model = new DefaultTableModel();
    public static JTextField tfTongSlgSP;
    private JButton btnXemSPConLai;
    private JButton btnXemSPDaBan;
    public static JRadioButton rdoNgay;
    public static JRadioButton rdoNam;
    public static JRadioButton rdoThang;
    private JButton btnXemSPBanChay;
    public static JDateChooser date;


    public FrmTKTinhTrangSP() {
        init();
        action();
    }

    public void init() {

        Font fontIntro = new Font("Monospaced", Font.BOLD, 32);
        Font fontTbl = new Font("SF Mono", Font.PLAIN, 16);
        Font fontText = new Font("SF Mono", Font.BOLD, 18);
        Font fontText2 = new Font("SF Mono", Font.BOLD, 22);
        Font fontTf = new Font("Lato", Font.PLAIN, 18);
        setBackground(new Color(211, 225, 237));
        JPanel pnLbIntro = new JPanel();
        pnLbIntro.setBackground(new Color(135, 172, 203));
        JLabel lbIntro = new JLabel("THỐNG KÊ TÌNH TRẠNG SẢN PHẨM LINH KIỆN");
        lbIntro.setHorizontalAlignment(SwingConstants.CENTER);
        lbIntro.setFont(fontIntro);
        lbIntro.setForeground(Color.white);
        pnLbIntro.add(lbIntro);

        JPanel pnThongTinChung = new JPanel(new GridLayout(3, 1, 30, 30));
        pnThongTinChung.setBackground(new Color(211, 225, 237));
        pnThongTinChung.setBorder(BorderFactory.createTitledBorder("Hình thức thống kê"));

        JPanel pn1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pn1.setBackground(new Color(211, 225, 237));
        JLabel lbNgay = new JLabel("Ngày  ");
        lbNgay.setFont(fontText2);
        date = new JDateChooser();
        date.setDateFormatString("dd-MM-yyyy");
        date.setFont(fontText2);
        date.setDate(new Date(System.currentTimeMillis()));
        date.setSize(20, 50);
        date.setPreferredSize(new Dimension(100, 30));
        pn1.add(lbNgay);
        pn1.add(date);

        JPanel pnButton = new JPanel(new GridLayout(1, 3, 20, 20));
        pnButton.setBackground(new Color(211, 225, 237));

        btnXemSPDaBan = new JButton("Xem sản phẩm đã bán");
        btnXemSPDaBan.setFont(fontText);
        btnXemSPDaBan.setBackground(new Color(135, 172, 203));
        btnXemSPConLai = new JButton("Xem sản phẩm còn lại trong kho");
        btnXemSPConLai.setFont(fontText);
        btnXemSPConLai.setBackground(new Color(135, 172, 203));
        btnXemSPBanChay = new JButton("Xem sản phẩm bán chạy nhất");
        btnXemSPBanChay.setFont(fontText);
        btnXemSPBanChay.setBackground(new Color(135, 172, 203));
        pnButton.add(btnXemSPDaBan);
        pnButton.add(btnXemSPConLai);
        pnButton.add(btnXemSPBanChay);

        JPanel pnLuaChon = new JPanel(new FlowLayout());
        pnLuaChon.setBackground(new Color(211, 225, 237));
        rdoNgay = new JRadioButton("Ngày");
        rdoNgay.setFont(fontText);
        rdoNgay.setBackground(new Color(135, 172, 203));
        rdoThang = new JRadioButton("Tháng");
        rdoThang.setFont(fontText);
        rdoThang.setBackground(new Color(135, 172, 203));
        rdoNam = new JRadioButton("Năm");
        rdoNam.setFont(fontText);
        rdoNam.setBackground(new Color(135, 172, 203));
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rdoNgay);
        buttonGroup.add(rdoThang);
        buttonGroup.add(rdoNam);
        pnLuaChon.add(rdoNgay);
        pnLuaChon.add(rdoThang);
        pnLuaChon.add(rdoNam);

        pnThongTinChung.add(pn1);
        pnThongTinChung.add(pnLuaChon);
        pnThongTinChung.add(pnButton);


        JPanel pnThongKeCT = new JPanel();
        pnThongKeCT.setBackground(new Color(211, 225, 237));

        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p1.setBackground(new Color(211, 225, 237));

        JLabel lbTongSlgSP = new JLabel("Tổng số lượng sản phẩm :");
        lbTongSlgSP.setFont(fontText);
        tfTongSlgSP = new JTextField(10);
        tfTongSlgSP.setFont(fontTf);
        tfTongSlgSP.setEditable(false);
        p1.add(lbTongSlgSP);
        p1.add(tfTongSlgSP);
        pnThongKeCT.add(p1);


        pnThongKeCT.setBorder(BorderFactory.createTitledBorder("Báo cáo sau thống kê"));


        JPanel pnThongTin = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnThongTin.setBackground(new Color(211, 225, 237));
        pnThongTin.add(pnThongTinChung);
        pnThongTin.add(pnThongKeCT);

        JTable tbDSTK = new JTable();
        model.setColumnIdentifiers(header);
        tbDSTK.setModel(model);
        tbDSTK.setFont(fontTbl);
        tbDSTK.setBackground(new Color(211, 225, 237));
        JScrollPane scrollPane = new JScrollPane(tbDSTK);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách sản phẩm"));

        JPanel pnMain = new JPanel(new GridLayout(2, 1));
        pnMain.setBackground(new Color(211, 225, 237));
        pnMain.add(pnThongTin);
        pnMain.add(scrollPane);

        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);

        this.setLayout(new BorderLayout());
        this.add(pnLbIntro, BorderLayout.NORTH);
        this.add(pnMain, BorderLayout.CENTER);

    }

    public void action() {
        btnXemSPBanChay.addActionListener(this);
        btnXemSPDaBan.addActionListener(this);
        btnXemSPConLai.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj.equals(btnXemSPDaBan)) {
            tkTinhTrangSPBUS.xemSPDaBan();
        } else if (obj.equals(btnXemSPBanChay)) {
            tkTinhTrangSPBUS.xemSPBanChay();
        } else if (obj.equals(btnXemSPConLai)) {
            tkTinhTrangSPBUS.xemSPConLai();
        }
    }
}
