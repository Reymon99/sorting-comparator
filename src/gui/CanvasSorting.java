package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class CanvasSorting extends JLabel {
    private ArrayList<Data> data;

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
}
