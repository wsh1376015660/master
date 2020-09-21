package demo;

import java.util.Arrays;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;

public class BarChartExample extends Application {
    public BarChartExample() {
    }

    public void start(Stage stage) {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.observableArrayList(Arrays.asList("Speed", "User rating", "Milage", "Safety")));
        xAxis.setLabel("category");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("score");
        BarChart<String, Number> barChart = new BarChart(xAxis, yAxis);
        barChart.setTitle("Comparison between various cars");
        Series<String, Number> series1 = new Series();
        series1.setName("Fiat");
        series1.getData().add(new Data("Speed", 1.0D));
        series1.getData().add(new Data("User rating", 3.0D));
        series1.getData().add(new Data("Milage", 5.0D));
        series1.getData().add(new Data("Safety", 5.0D));
        Series<String, Number> series2 = new Series();
        series2.setName("Audi");
        series2.getData().add(new Data("Speed", 5.0D));
        series2.getData().add(new Data("User rating", 6.0D));
        series2.getData().add(new Data("Milage", 10.0D));
        series2.getData().add(new Data("Safety", 4.0D));
        Series<String, Number> series3 = new Series();
        series3.setName("Ford");
        series3.getData().add(new Data("Speed", 4.0D));
        series3.getData().add(new Data("User rating", 2.0D));
        series3.getData().add(new Data("Milage", 3.0D));
        series3.getData().add(new Data("Safety", 6.0D));
        barChart.getData().addAll(new Series[]{series1, series2, series3});
        Group root = new Group(new Node[]{barChart});
        Scene scene = new Scene(root, 600.0D, 400.0D);
        stage.setTitle("Bar Chart");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}