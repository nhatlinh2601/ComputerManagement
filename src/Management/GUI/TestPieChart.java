package Management.GUI;


import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class TestPieChart {

    public static JFreeChart createChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                "CƠ CẤU DOANH THU THEO THÁNG", dataset, true, true, true);
        return chart;
    }

    public static PieDataset createDataset(Float a, Float b) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Tổng tiền nhập SP", a);
        dataset.setValue("Lợi nhuận", b);
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
