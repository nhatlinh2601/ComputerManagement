package Management.GUI;

import javax.swing.JFrame;

import Management.DAO.TKTongQuatDAO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * @author TVD
 */
public class TestBarChart {
    private static TKTongQuatDAO tkTongQuatDAO = new TKTongQuatDAO();

    public static JFreeChart createChart(int nam) {
        JFreeChart barChart = ChartFactory.createBarChart(
                "BIỂU ĐỒ DOANH THU CỬA HÀNG",
                "Tháng", "Lợi nhuận",
                createDataset(nam), PlotOrientation.VERTICAL, false, false, false);
        return barChart;
    }

    public static CategoryDataset createDataset(int nam) {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(TKTongQuatDAO.tongTienBanDuoc(1, nam) - tkTongQuatDAO.tongTienNhap(1, nam),
                "Lợi nhuận", "1");
        dataset.addValue(TKTongQuatDAO.tongTienBanDuoc(2, nam) - tkTongQuatDAO.tongTienNhap(2, nam),
                "Lợi nhuận", "2");
        dataset.addValue(TKTongQuatDAO.tongTienBanDuoc(3, nam) - tkTongQuatDAO.tongTienNhap(3, nam),
                "Lợi nhuận", "3");
        dataset.addValue(TKTongQuatDAO.tongTienBanDuoc(4, nam) - tkTongQuatDAO.tongTienNhap(4, nam),
                "Lợi nhuận", "4");
        dataset.addValue(TKTongQuatDAO.tongTienBanDuoc(5, nam) - tkTongQuatDAO.tongTienNhap(5, nam),
                "Lợi nhuận", "5");
        dataset.addValue(TKTongQuatDAO.tongTienBanDuoc(6, nam) - tkTongQuatDAO.tongTienNhap(6, nam),
                "Lợi nhuận", "6");
        dataset.addValue(TKTongQuatDAO.tongTienBanDuoc(7, nam) - tkTongQuatDAO.tongTienNhap(7, nam),
                "Lợi nhuận", "7");
        dataset.addValue(TKTongQuatDAO.tongTienBanDuoc(8, nam) - tkTongQuatDAO.tongTienNhap(8, nam),
                "Lợi nhuận", "8");
        dataset.addValue(TKTongQuatDAO.tongTienBanDuoc(9, nam) - tkTongQuatDAO.tongTienNhap(9, nam),
                "Lợi nhuận", "9");
        dataset.addValue(TKTongQuatDAO.tongTienBanDuoc(10, nam) - tkTongQuatDAO.tongTienNhap(10, nam),
                "Lợi nhuận", "10");
        dataset.addValue(TKTongQuatDAO.tongTienBanDuoc(11, nam) - tkTongQuatDAO.tongTienNhap(11, nam),
                "Lợi nhuận", "11");
        dataset.addValue(TKTongQuatDAO.tongTienBanDuoc(12, nam) - tkTongQuatDAO.tongTienNhap(12, nam),
                "Lợi nhuận", "12");
        return dataset;
    }

//    public static void main(String[] args) {
//        ChartPanel chartPanel = new ChartPanel(createChart());
//        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
//        JFrame frame = new JFrame();
//        frame.add(chartPanel);
//        frame.setTitle("Biểu đồ JFreeChart trong Java Swing");
//        frame.setSize(600, 400);
//        frame.setLocationRelativeTo(null);
//        frame.setResizable(false);
//        frame.setVisible(true);
//    }

}