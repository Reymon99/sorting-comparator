package gui;

import org.constrains.Constrains;
import org.constrains.View;
import org.constrains.Weight;

import javax.swing.*;
import java.awt.*;

public class Comparator extends JPanel {

    public Comparator() {
        super(new GridBagLayout());
        init();
    }

    private void init() {
        Constrains.addComp(
                new View(new TableComparator(), this),
                new Rectangle(0, 0, 1, 1),
                new Weight(1, 1),
                new Insets(10, 10, 10, 10),
                new Point(GridBagConstraints.CENTER, GridBagConstraints.BOTH)
        );
    }
}
