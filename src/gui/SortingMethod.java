package gui;

import org.constrains.Constrains;
import org.constrains.View;
import threads.Sorting;

import javax.swing.*;
import java.awt.*;

public class SortingMethod extends JPanel {
    private final Sorting sorting;
    private final JLabel time;

    public SortingMethod(Methods method, int[] data){
        super(new GridBagLayout());
        sorting = new Sorting(
                method,
                data,
                time = new JLabel("0 ms"),
                true
        );
        init();
    }

    private void init() {
        JLabel name = new JLabel(sorting.getTypeSorting(), SwingConstants.CENTER);
        name.setFont(new Font(Font.MONOSPACED, Font.BOLD, 19));
        time.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        time.setHorizontalAlignment(SwingConstants.RIGHT);
        PlayPause playPause = new PlayPause();
        Constrains.addCompX(
                new View(name, this),
                new Rectangle(0, 0, 2, 1),
                1,
                new Insets(5, 2, 3, 2),
                new Point(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL)
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
}
