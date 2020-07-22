package gui;

import org.constrains.Constrains;
import org.constrains.View;
import org.constrains.Weight;
import threads.Sorting;

import javax.swing.*;
import java.awt.*;

public class SortingMethod extends JPanel {
    private final Sorting sorting;
    private final JLabel steps;
    private final JLabel swaps;
    private final PlayPause playPause;
    private final CanvasSorting canvas;
    private final int pointers;
    private boolean sorted;

    {
        playPause = new PlayPause();
        steps = new JLabel("st: 0");
        swaps = new JLabel("sw: 0");
    }

    public SortingMethod(Methods method){
        super(new GridBagLayout());
        sorted = false;
        pointers = method.getPointers();
        canvas = new CanvasSorting(pointers);
        sorting = new Sorting(
                method,
                this,
                true
        );
        setToolTipText("Number of " + method.toString() + " pointers: " + pointers);
        init();
    }

    private void init() {
        JLabel name = new JLabel(sorting.getTypeSorting(), SwingConstants.CENTER);
        name.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
        steps.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        steps.setHorizontalAlignment(SwingConstants.RIGHT);
        steps.setToolTipText("Number of steps");
        swaps.setFont(steps.getFont());
        swaps.setHorizontalAlignment(steps.getHorizontalAlignment());
        swaps.setToolTipText("Number of swaps");
        playPause.setEventPlay(e -> run());
        playPause.setEventPause(e -> {});
        Constrains.addCompX(
                new View(name, this),
                new Rectangle(0, 0, 3, 1),
                1,
                new Insets(5, 2, 3, 2),
                new Point(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL)
        );
        Constrains.addComp(
                new View(canvas, this),
                new Rectangle(0, 1, 3, 1),
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
                new View(steps, this),
                new Rectangle(1, 2, 1, 1),
                1,
                new Insets(2, 5, 5, 1),
                new Point(GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL)
        );
        Constrains.addCompX(
                new View(swaps, this),
                new Rectangle(2, 2, 1, 1),
                1,
                new Insets(2, 0, 5, 12),
                new Point(GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL)
        );
    }

    public void run() {
        playPause.setPlayPause(false);
        sorting.start();
    }

    public void reset() {
        playPause.setPlayPause(false);
        playPause.setEnabled(true);
        playPause.action();
        steps.setText("st: 0");
        swaps.setText("sw: 0");
        canvas.setData(canvas.setPercentage());
        canvas.sorting(new int[pointers]);
    }

    public String getMethod() {
        return sorting.getTypeSorting();
    }

    public void setSteps(int steps) {
        this.steps.setText("st: " + steps);
    }

    public void setSwaps(int swaps) {
        this.swaps.setText("sw: " + swaps);
    }

    public JLabel getSteps() {
        return steps;
    }

    public JLabel getSwaps() {
        return swaps;
    }

    public CanvasSorting getCanvas() {
        return canvas;
    }

    public PlayPause getPlayPause() {
        return playPause;
    }

    public boolean isSorted() {
        return sorted;
    }

    public void setSorted(boolean sorted) {
        this.sorted = sorted;
    }
}
