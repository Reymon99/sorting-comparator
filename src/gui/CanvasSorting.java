package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class CanvasSorting extends JLabel {
    private ArrayList<Data> data;
    private final HashMap<Integer, Point> lines;
    private final ArrayList<Integer> numberOfPointers;

    {
        numberOfPointers = new ArrayList<>();
        lines = new HashMap<>();
        for (int i = 0; i < 10; i++) lines.put(i, new Point(30, i == 0 ? 8 : 14 * i + 8));
    }

    public CanvasSorting(int numberOfPointers) {
        Dimension dimension = new Dimension(140, 150);
        setMinimumSize(dimension);
        setPreferredSize(dimension);
        setMaximumSize(dimension);
        setOpaque(true);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createRaisedBevelBorder());
        data = setPercentage();
        for (int pointer = 0; pointer < numberOfPointers; pointer++) this.numberOfPointers.add(pointer);
    }

    public void sorting(int... pointers) {
        if (pointers.length != numberOfPointers.size())
            throw new IndexOutOfBoundsException("pointer.length !=" + numberOfPointers.size());
        for (int i : pointers) if (i >= data.size())
            throw new IllegalArgumentException("pointer >= " + data.size());
        for (int pointer = 0; pointer < pointers.length; pointer++)
            numberOfPointers.set(pointer, pointers[pointer]);
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

    public void setData(ArrayList<Data> data) {
        this.data = data;
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
        numberOfPointers.forEach(e -> {
            int y = lines.get(e).y;
            g2.fill(new Polygon(
                    new int[]{x, x + 12, x, x + 4},
                    new int[]{y - 2, y + 3, y + 10, y + 3},
                    4
            ));
        });
    }
}
