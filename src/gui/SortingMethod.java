package gui;

import org.constrains.Constrains;
import org.constrains.View;
import org.constrains.Weight;
import threads.Sorting;

import javax.swing.*;
import java.awt.*;

public class SortingMethod extends JPanel {
    private final Sorting sorting;
    private final JLabel time;
    private final PlayPause playPause;
    private final CanvasSorting canvas;

    {
        playPause = new PlayPause();
    }

    public SortingMethod(Methods method){
        super(new GridBagLayout());
        sorting = new Sorting(
                method,
                time = new JLabel("0 s"),
                true,
                canvas = new CanvasSorting()
        );
        init();
    }

    private void init() {
        JLabel name = new JLabel(sorting.getTypeSorting(), SwingConstants.CENTER);
        name.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
        time.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        time.setHorizontalAlignment(SwingConstants.RIGHT);
        Constrains.addCompX(
                new View(name, this),
                new Rectangle(0, 0, 2, 1),
                1,
                new Insets(5, 2, 3, 2),
                new Point(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL)
        );
        Constrains.addComp(
                new View(canvas, this),
                new Rectangle(0, 1, 2, 1),
                new Weight(1, 1),
                new Insets(2, 2, 2, 2),
                new Point(GridBagConstraints.CENTER, GridBagConstraints.NONE)
        );
        Constrains.addCompX(
                new View(playPause, this),
                new Rectangle(0, 2, 1, 1),
                1,
                new Insets(2, 2, 5, 5),
                new Point(GridBagConstraints.WEST, GridBagConstraints.NONE)
        );
        Constrains.addCompX(
                new View(time, this),
                new Rectangle(1, 2, 1, 1),
                1,
                new Insets(2, 5, 5, 2),
                new Point(GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL)
        );
    }

    public void run() {
        playPause.setPlayPause(true);
        playPause.action();
    }

    public void reset() {
        playPause.setPlayPause(false);
        playPause.action();
        time.setText("0 s");
        canvas.sorting(canvas.setPercentage(), 0);
    }

    public String getMethod() {
        return sorting.getTypeSorting();
    }

    public int getSteps() {
        return sorting.getSteps();
    }
}
