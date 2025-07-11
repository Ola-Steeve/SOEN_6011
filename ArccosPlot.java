import org.knowm.xchart.*;
import java.util.*;

public class ArccosPlot {

    public static void main(String[] args) {
        List<Double> xValues = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();

        for (double x = -1.0; x <= 1.0; x += 0.01) {
            xValues.add(x);
            yValues.add(Math.acos(x));
        }

        XYChart chart = new XYChartBuilder()
                .width(800).height(600)
                .title("arccos(x)")
                .xAxisTitle("x")
                .yAxisTitle("arccos(x) in radians")
                .build();

        chart.addSeries("arccos(x)", xValues, yValues);
        new SwingWrapper<>(chart).displayChart();
    }
}
