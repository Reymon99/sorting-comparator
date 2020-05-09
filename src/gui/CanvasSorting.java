package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class CanvasSorting extends JLabel {
    private ArrayList<Data> data;
    private HashMap<Integer, Point> lines;

    public CanvasSorting() {
        Dimension dimension = new Dimension(140, 150);
        setMinimumSize(dimension);
        setPreferredSize(dimension);
        setMaximumSize(dimension);
        setOpaque(true);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createRaisedBevelBorder());
        setPercentage();
    }

    public void sorting(ArrayList<Data> data) {
        this.data = data;
        repaint();
    }

    private void setPercentage() {
        data = new ArrayList<>();
        Values.data.forEach(data -> this.data.add(new Data(data)));
        int max = data.stream().max(
                Comparator.comparing(Data::getData)
        ).orElse(new Data(0)).getData();
        data.forEach(e -> e.setPercentage(max));
    }

    public ArrayList<Data> getData() {
        return data;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        paintArrow(g2);
    }

    private void paintLines(Graphics2D g2) {

    }

    private void paintArrow(Graphics2D g2) {
        g2.setPaint(new Color(238, 70, 55));
        g2.fill(new Polygon(
                new int[]{20, 120, 20, 50},
                new int[]{20, 60, 120, 60},
                4
        ));
        g2.setPaint(new Color(229, 221, 221));
        g2.setStroke(new BasicStroke(2));
        g2.draw(new Line2D.Double(new Point(35, 105), new Point(105, 63)));
    }
}
