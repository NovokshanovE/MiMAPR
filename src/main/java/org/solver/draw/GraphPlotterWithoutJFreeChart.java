package org.solver.draw;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GraphPlotterWithoutJFreeChart extends JFrame {

    private List<Point> readDataFromFile(String filename) {
        List<Point> points = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] coordinates = line.split(" ");
                double x = Double.parseDouble(coordinates[0]);
                double y = Double.parseDouble(coordinates[1]);
                points.add(new Point(x, y));
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ошибка при чтении файла", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }

        return points;
    }

    public GraphPlotterWithoutJFreeChart(String title) {
        super(title);

        List<Point> points = readDataFromFile("integral_1.txt");
        GraphPanel graphPanel = new GraphPanel(points);
        getContentPane().add(graphPanel);

        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GraphPlotterWithoutJFreeChart example = new GraphPlotterWithoutJFreeChart("График по точкам");
            example.setVisible(true);
        });
    }
}
