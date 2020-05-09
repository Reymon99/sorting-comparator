package gui;

import org.constrains.Constrains;
import org.constrains.View;
import org.constrains.Weight;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class Comparator extends JPanel {
    private final ArrayList<SortingMethod> methods;

    {
        methods = new ArrayList<>();
        for (Methods method: Methods.values()) methods.add(new SortingMethod(method));
    }

    public Comparator() {
        super(new GridBagLayout());
        init();
    }

    private void init() {
        Constrains.addComp(
                new View(methods(), this),
                new Rectangle(0, 0, 1, 2),
                new Weight(1, 1),
                new Insets(10, 20, 10, 5),
                new Point(GridBagConstraints.CENTER, GridBagConstraints.BOTH)
        );
        Constrains.addCompX(
                new View(buttons(), this),
                new Rectangle(2, 0, 1, 1),
                1,
                new Insets(10, 20, 10, 20),
                new Point(GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL)
        );
        Constrains.addComp(
                new View(comparator(), this),
                new Rectangle(2, 1, 1, 1),
                new Weight(1, 1),
                new Insets(10, 20, 10, 20),
                new Point(GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL)
        );
    }

    private JPanel comparator() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Comparator"));

        JComboBox<Methods> comparatorOne = new JComboBox<>(new DefaultComboBoxModel<>(Methods.values()));
        JComboBox<Methods> comparatorTwo = new JComboBox<>(new DefaultComboBoxModel<>(Methods.values()));
        comparatorOne.setSelectedIndex(-1);
        comparatorTwo.setSelectedIndex(-1);
        comparatorOne.addActionListener(e -> {
            if (Objects.equals(
                    comparatorOne.getSelectedItem(),
                    Optional.ofNullable(comparatorTwo.getSelectedItem()).orElse("")
            ))
                comparatorOne.setSelectedIndex(-1);
        });
        comparatorTwo.addActionListener(e -> {
            if (Objects.equals(
                    comparatorTwo.getSelectedItem(),
                    Optional.ofNullable(comparatorOne.getSelectedItem()).orElse("")
            ))
                comparatorTwo.setSelectedIndex(-1);
        });
        JButton button = new JButton("Comparator");
        button.addActionListener(e -> {});

        addComponents(panel, comparatorOne, comparatorTwo, button);
        return panel;
    }

    private JPanel buttons() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Control Panel"));
        JButton runAll = new JButton("Run All");
        JButton restart = new JButton("Restart");
        JButton setValues = new JButton("Set Values");

        setValues.addActionListener(e -> new Values((JPanel) getComponent(0)).setVisible(true));

        addComponents(panel, runAll, restart, setValues);
        return panel;
    }

    private JPanel methods() {
        JPanel panel = new JPanel(new GridLayout(3, 3, 10, 10));
        methods.forEach(panel::add);
        return panel;
    }

    private void addComponents(JPanel panel, JComponent... components) {
        Constrains.addCompX(
                new View(components[0], panel),
                new Rectangle(0, 0, 1, 1),
                1,
                new Insets(10, 10, 5, 5),
                new Point(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL)
        );
        Constrains.addCompX(
                new View(components[1], panel),
                new Rectangle(0, 1, 1, 1),
                1,
                new Insets(5, 10, 5, 5),
                new Point(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL)
        );
        Constrains.addCompX(
                new View(components[2], panel),
                new Rectangle(0, 2, 1, 1),
                1,
                new Insets(5, 10, 5, 5),
                new Point(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL)
        );
    }
}
