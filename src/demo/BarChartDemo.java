package demo;

import dao.impl.OtherDaoImpl;
import java.awt.Font;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChartDemo {
    public BarChartDemo() {
    }

    public static void main(String[] args) {
        setChart("成绩直方图", "成绩分布", "人数");
    }

    private static void setChart(String title, String xName, String yName) {
        JFreeChart chart = ChartFactory.createBarChart(title, xName, yName, getChartDataSet());
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setLabelFont(new Font("黑体", 1, 14));
        domainAxis.setTickLabelFont(new Font("宋体", 1, 12));
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setLabelFont(new Font("黑体", 1, 15));
        chart.getLegend().setItemFont(new Font("黑体", 1, 15));
        chart.getTitle().setFont(new Font("宋体", 1, 20));
        ChartPanel chartPanel = new ChartPanel(chart, true);
        JFrame frame = new JFrame("成绩分布图");
        frame.add(chartPanel);
        frame.setBounds(50, 50, 900, 600);
        frame.setVisible(true);
    }

    private static CategoryDataset getChartDataSet() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int score_0_60 = (new OtherDaoImpl()).analysis(172102, 3, 0, 60);
        int score_60_75 = (new OtherDaoImpl()).analysis(172102, 3, 60, 75);
        int score_75_90 = (new OtherDaoImpl()).analysis(172102, 3, 75, 90);
        int score_90_100 = (new OtherDaoImpl()).analysis(172102, 3, 90, 100);
        int score_100 = (new OtherDaoImpl()).analysis(172102, 3, 100, 101);
        dataset.addValue((double)score_75_90, "aaaa", "数据结构");
        return dataset;
    }
}
