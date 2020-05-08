package gui;

import org.constrains.Constrains;
import org.constrains.View;
import org.constrains.Weight;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Optional;

public class Comparator extends JPanel {

    public Comparator() {
        super(new GridBagLayout());
        init();
    }

    private void init() {
        ComparatorMethods methods = new ComparatorMethods();
        Constrains.addComp(
                new View(methods, this),
                new Rectangle(0, 0, 1, 1),
                new Weight(1, 1),
                new Insets(30, 10, 10, 10),
                new Point(GridBagConstraints.CENTER, GridBagConstraints.BOTH)
        );
        Constrains.addComp(
                new View(comparator(), this),
                new Rectangle(0, 1, 1, 1),
                new Weight(1, 1),
                new Insets(30, 10, 10, 20),
                new Point(GridBagConstraints.CENTER, GridBagConstraints.NONE)
        );
    }

    private JPanel comparator() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Comparator"));
        panel.setOpaque(false);
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
        panel.add(comparatorOne);
        panel.add(comparatorTwo);
        panel.add(button);
        return panel;
    }
}
