package Management.GUI;


import Management.DAO.TKTongQuatDAO;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.jfree.chart.JFreeChart;

public class FrmTKTongQuat extends JPanel implements ActionListener {
    public static DefaultComboBoxModel modelThang = new DefaultComboBoxModel<>();
    public static DefaultComboBoxModel modelNam = new DefaultComboBoxModel<>();
    private JTextField tfTTBanDuoc;
    private JTextField tfTTNhap;

    private JTextField tfLoiNhuan;
    private TKTongQuatDAO tkTongQuatDAO = new TKTongQuatDAO();
    private JFreeChart pieChart;
    private ChartPanel chartPanel2;
    private JButton btnNam;
    private JButton btnXem;
    private JTabbedPane tp;
    private JButton btnDoanhThuNv;

    public FrmTKTongQuat() {
        initGUI();
        setVisible(true);
        action();
    }

    public void initGUI() {
        Font fontIntro = new Font("Monospaced", Font.BOLD, 32);
        Font fontTbl = new Font("SF Mono", Font.PLAIN, 16);
        Font fontText = new Font("SF Mono", Font.BOLD, 18);
        Font fontTf = new Font("Lato", Font.PLAIN, 18);
        setBackground(new Color(211, 225, 237));
        modelThang.removeAllElements();
        for (int i = 1; i <= 12; i++) {
            modelThang.addElement(i);
        }
        modelNam.removeAllElements();

        for (int i = 2020; i <= 2040; i++) {
            modelNam.addElement(i);
        }
        setLayout(null);

        tp = new JTabbedPane();
        tp.setBounds(417, 10, 1113, 625);
        add(tp);
        JPanel pn1 = new JPanel();
        pn1.setBackground(new Color(211, 225, 237));
        pn1.setBounds(10, 10, 497, 335);
        add(pn1);
        pn1.setLayout(null);

        JLabel lbThang = new JLabel("THÁNG");
        lbThang.setFont(fontText);
        lbThang.setBounds(36, 15, 114, 57);
        pn1.add(lbThang);

        JComboBox cbxThang = new JComboBox();
        cbxThang.setFont(fontText);
        cbxThang.setBounds(147, 24, 139, 39);
        cbxThang.setModel(modelThang);
        pn1.add(cbxThang);

        JComboBox cbxNam = new JComboBox();
        cbxNam.setFont(fontText);
        cbxNam.setBounds(147, 91, 139, 39);
        cbxNam.setModel(modelNam);
        pn1.add(cbxNam);

        JLabel lbNam = new JLabel("NĂM");
        lbNam.setFont(fontText);
        lbNam.setBounds(36, 78, 114, 57);
        pn1.add(lbNam);

        btnXem = new JButton("XEM BÁO CÁO");
        btnXem.setFont(fontText);
        btnXem.setBackground(new Color(135, 172, 203));
        btnXem.setBounds(76, 195, 232, 40);
        pn1.add(btnXem);


        btnNam = new JButton("DOANH THU NĂM");
        btnNam.setFont(fontText);
        btnNam.setBackground(new Color(135, 172, 203));
        btnNam.setBounds(76, 245, 232, 40);
        pn1.add(btnNam);

        btnDoanhThuNv = new JButton("DOANH THU NV");
        btnDoanhThuNv.setFont(new Font("Dialog", Font.BOLD, 18));
        btnDoanhThuNv.setBackground(new Color(135, 172, 203));
        btnDoanhThuNv.setBounds(76, 295, 232, 40);
        pn1.add(btnDoanhThuNv);


        JPanel pn2 = new JPanel();
        pn2.setBackground(new Color(211, 225, 237));
        pn2.setBounds(10, 355, 397, 273);
        add(pn2);
        pn2.setLayout(null);

        tfTTBanDuoc = new JTextField();
        tfTTBanDuoc.setText("");
        tfTTBanDuoc.setEditable(false);
        tfTTBanDuoc.setFont(fontText);
        tfTTBanDuoc.setBounds(197, 41, 175, 47);
        pn2.add(tfTTBanDuoc);
        tfTTBanDuoc.setColumns(10);

        tfTTNhap = new JTextField();
        tfTTNhap.setText("");
        tfTTNhap.setEditable(false);
        tfTTNhap.setFont(fontText);
        tfTTNhap.setColumns(10);
        tfTTNhap.setBounds(197, 111, 175, 47);
        pn2.add(tfTTNhap);

        tfLoiNhuan = new JTextField();
        tfLoiNhuan.setText("");
        tfLoiNhuan.setEditable(false);
        tfLoiNhuan.setFont(fontText);
        tfLoiNhuan.setColumns(10);
        tfLoiNhuan.setBounds(197, 182, 175, 47);
        pn2.add(tfLoiNhuan);

        JLabel lbTTBanDuoc = new JLabel("Tổng tiền bán được");
        lbTTBanDuoc.setFont(fontText);
        lbTTBanDuoc.setBounds(10, 43, 201, 34);
        pn2.add(lbTTBanDuoc);

        JLabel lbTTNhap = new JLabel("Tổng tiền nhập");
        lbTTNhap.setFont(fontText);
        lbTTNhap.setBounds(10, 111, 201, 34);
        pn2.add(lbTTNhap);

        JLabel lbLoiNhuan = new JLabel("Lợi nhuận");
        lbLoiNhuan.setFont(fontText);
        lbLoiNhuan.setBounds(10, 183, 201, 34);
        pn2.add(lbLoiNhuan);


    }

    public void action() {
        btnXem.addActionListener(this);
        btnDoanhThuNv.addActionListener(this);
        btnNam.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj.equals(btnXem)) {
            xuLyXem();
        }
        if (obj.equals(btnDoanhThuNv)) {
            xuLyDoanhThuNV();
        }
        if (obj.equals(btnNam)) {
            xuLyDoanhThuNam();
        }
    }

    public void xuLyXem() {
        int thang = (int) modelThang.getSelectedItem();
        int nam = (int) modelNam.getSelectedItem();
        Float tongTienBanDuoc = tkTongQuatDAO.tongTienBanDuoc(thang, nam);
        tfTTBanDuoc.setText(String.valueOf(tongTienBanDuoc));
        Float tongTienNhap = tkTongQuatDAO.tongTienNhap(thang, nam);
        tfTTNhap.setText(String.valueOf(tongTienNhap));
        Float loiNhuan = tongTienBanDuoc - tongTienNhap;
        tfLoiNhuan.setText(String.valueOf(loiNhuan));
        pieChart = TestPieChart.createChart(TestPieChart.createDataset(tongTienNhap, loiNhuan));
        tp.removeAll();
        chartPanel2 = new ChartPanel(pieChart);
        tp.addTab("Doanh thu tháng", chartPanel2);
        tp.setSelectedComponent(chartPanel2);
    }

    public void xuLyDoanhThuNV() {
        int thang = (int) modelThang.getSelectedItem();
        int nam = (int) modelNam.getSelectedItem();
        Float tongTienBanDuoc = tkTongQuatDAO.tongTienBanDuoc(thang, nam);
        tfTTBanDuoc.setText(String.valueOf(tongTienBanDuoc));
        Float tongTienNhap = tkTongQuatDAO.tongTienNhap(thang, nam);
        tfTTNhap.setText(String.valueOf(tongTienNhap));
        Float loiNhuan = tongTienBanDuoc - tongTienNhap;
        tfLoiNhuan.setText(String.valueOf(loiNhuan));
        pieChart = TestPieChartP2.createChart(TestPieChartP2.createDataset(thang, nam));
        tp.removeAll();
        chartPanel2 = new ChartPanel(pieChart);
        tp.addTab("Doanh thu NV", chartPanel2);
        tp.setSelectedComponent(chartPanel2);
    }

    public void xuLyDoanhThuNam() {
        tp.removeAll();
        ChartPanel chartPanel = new ChartPanel(TestBarChart.createChart((Integer) modelNam.getSelectedItem()));
        chartPanel.setBackground(new Color(211, 225, 237));
        chartPanel.setPreferredSize(new Dimension(560, 367));
        chartPanel.setBounds(417, 10, 1113, 625);
        tp.addTab("Doanh thu năm", chartPanel);
        tp.setSelectedComponent(chartPanel);
        int nam = (int) modelNam.getSelectedItem();
        Float tongTienBanDuoc = tkTongQuatDAO.tongTienBanDuoc(nam);
        tfTTBanDuoc.setText(String.valueOf(tongTienBanDuoc));
        Float tongTienNhap = tkTongQuatDAO.tongTienNhap(nam);
        tfTTNhap.setText(String.valueOf(tongTienNhap));
        Float loiNhuan = tongTienBanDuoc - tongTienNhap;
        tfLoiNhuan.setText(String.valueOf(loiNhuan));

    }


    public Color getThisBackground() {
        return getBackground();
    }

    public void setThisBackground(Color background) {
        setBackground(background);
    }
}

