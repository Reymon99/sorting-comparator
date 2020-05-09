package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class CanvasSorting extends JLabel {
    private ArrayList<Data> data;
    private int index;
    private final HashMap<Integer, Point> lines;

    {
        lines = new HashMap<>();
        for (int i = 0; i < 10; i++) lines.put(i, new Point(30, i == 0 ? 8 : 14 * i + 8));
    }

    public CanvasSorting() {
        Dimension dimension = new Dimension(140, 150);
        setMinimumSize(dimension);
        setPreferredSize(dimension);
        setMaximumSize(dimension);
        setOpaque(true);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createRaisedBevelBorder());
        data = setPercentage();
    }

    public void sorting(ArrayList<Data> data, int index) {
        if (index >= 10) throw new IllegalArgumentException("index >= 10");
        this.data = data;
        this.index = index;
        repaint();
    }

    public ArrayList<Data> setPercentage() {
        ArrayList<Data> data = new ArrayList<>();
        Values.data.forEach(dta -> data.add(new Data(dta)));
        int max = data.stream().max(
                Comparator.comparing(Data::getData)
        ).orElse(new Data(0)).getData();
        data.forEach(e -> e.setPercentage(max));
        return data;
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
        paintLines(g2);
    }

    private void paintLines(Graphics2D g2) {
        g2.setPaint(new Color(121, 114, 114));
        lines.forEach((line, point) -> g2.fill(new RoundRectangle2D.Double(
                point.x,
                point.y,
                100 * data.get(line).getPercentage(),
                8, 8, 8
        )));
    }

    private void paintArrow(Graphics2D g2) {
        g2.setPaint(new Color(238, 70, 55));
        int x = 10;
        int y = lines.get(index).y;
        g2.fill(new Polygon(
                new int[]{x, x + 12, x, x + 4},
                new int[]{y - 2, y + 3, y + 10, y + 3},
                4
        ));
    }
}
