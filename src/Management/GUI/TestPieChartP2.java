package Management.GUI;


import javax.swing.JFrame;

import Management.DAO.DatabaseHelper;
import Management.DTO.SanPhamLK;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestPieChartP2 {

    private static PreparedStatement pstmt;
    private static ResultSet rs;

    public static JFreeChart createChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                "CƠ CẤU DOANH THU NHÂN VIÊN ", dataset, true, true, true);
        return chart;
    }

    public static PieDataset createDataset(int thang,int nam) {
        Connection conn = null;
        DefaultPieDataset dataset = new DefaultPieDataset();
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "SELECT nhanvien.tennv, sum(tongtien) FROM hoadon JOIN nhanvien ON hoadon.manv=nhanvien.manv " +
                    "WHERE month(hoadon.ngaylap)= ? and year(hoadon.ngaylap)= ? GROUP BY hoadon.manv ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, thang);
            pstmt.setInt(2, nam);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                dataset.setValue(rs.getString(1),rs.getDouble(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) try {
                conn.close();
            } catch (SQLException ignore) {
            }
        }
        return dataset;
    }
//
//    public static void main(String[] args) {
//        JFreeChart pieChart = createChart(createDataset());
//        ChartPanel chartPanel = new ChartPanel(pieChart);
//        JFrame frame = new JFrame();
//        frame.add(chartPanel);
//        frame.setTitle("Biểu đồ JFreeChart trong Java Swing");
//        frame.setSize(600, 400);
//        frame.setLocationRelativeTo(null);
//        frame.setResizable(false);
//        frame.setVisible(true);
//    }

}