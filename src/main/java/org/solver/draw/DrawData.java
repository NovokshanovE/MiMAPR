package org.solver.draw;



import javax.swing.*;
        import java.awt.*;
        import java.io.BufferedReader;
        import java.io.FileReader;
        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.List;

class Point {
    double x, y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

class GraphPanel extends JPanel {

    private List<Point> points;

    GraphPanel(List<Point> points) {
        this.points = points;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);

        // Рисуем оси координат
        g2d.drawLine(20, getHeight() / 2, getWidth(), getHeight() / 2); // Ось X
        g2d.drawLine(20, 0, 20, getHeight()); // Ось Y

        // Рисуем штрихи на оси X
        double maxX = points.stream().mapToDouble(point -> point.x).max().orElse(1.0);
        int tickSizeX = (int) (0.1 * getWidth());
        for (int x = 20; x < getWidth(); x += tickSizeX) {
            g2d.drawLine(x, getHeight() / 2 - 5, x, getHeight() / 2 + 5);
            double percentage = (double) (x - 20) / (getWidth() - 20) * maxX;
            g2d.drawString(String.format("%.1f", percentage), x - 15, getHeight() / 2 + 20);
        }

        // Рисуем штрихи на оси Y
        double maxY = points.stream().mapToDouble(point -> point.y).max().orElse(1.0);
        int tickSizeY = (int) (0.1 * getHeight());
        for (int y = 0; y < getHeight(); y += tickSizeY) {
            g2d.drawLine(15, y, 25, y);
            double coordinate = maxY - (double) y / getHeight() * maxY;
            g2d.drawString(String.format("%.1f", coordinate), 30, y + 5);
        }

        // Определяем смещение для центрирования графика
        int xOffset = 20;
        int yOffset = getHeight() / 2;

        g2d.setColor(Color.RED);
        for (Point point : points) {
            int x = (int) (point.x / maxX * (getWidth() - 20)) + xOffset;
            int y = (int) -((point.y / maxY) * getHeight()) + yOffset;

            g2d.fillOval(x - 3, y - 3, 6, 6); // Отрисовываем точки
        }

        g2d.setColor(Color.GREEN);
        for (int i = 1; i < points.size(); i++) {
            int x1 = (int) (points.get(i - 1).x / maxX * (getWidth() - 20)) + xOffset;
            int y1 = (int) -((points.get(i - 1).y / maxY) * getHeight()) + yOffset;
            int x2 = (int) (points.get(i).x / maxX * (getWidth() - 20)) + xOffset;
            int y2 = (int) -((points.get(i).y / maxY) * getHeight()) + yOffset;

            g2d.drawLine(x1, y1, x2, y2); // Отрисовываем линии между точками
        }
    }
}
