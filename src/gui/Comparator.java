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
    public final static ArrayList<SortingMethod> methods;
    public final static JButton runAll;
    public final static JButton reset;
    public final static JButton setValues;
    public final static JButton comparator;

    static {
        runAll = new JButton("Run All");
        reset = new JButton("Reset");
        setValues = new JButton("SetValues");
        comparator = new JButton("Comparator");
        methods = new ArrayList<>();
        for (Methods method: Methods.values()) methods.add(new SortingMethod(method));
    }

    public Comparator() {
        super(new GridBagLayout());
        init();
    }

    private void init() {
        JButton about = new JButton("About");
        about.addActionListener(e -> about().setVisible(true));
        Constrains.addComp(
                new View(methods(), this),
                new Rectangle(0, 0, 1, 3),
                new Weight(1, 1),
                new Insets(10, 20, 10, 5),
                new Point(GridBagConstraints.CENTER, GridBagConstraints.BOTH)
        );
        Constrains.addCompX(
                new View(buttons(), this),
                new Rectangle(2, 0, 1, 1),
                1,
                new Insets(10, 20, 10, 20),
                new Point(GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL)
        );
        Constrains.addComp(
                new View(comparator(), this),
                new Rectangle(2, 1, 1, 1),
                new Weight(1, 1),
                new Insets(10, 20, 10, 20),
                new Point(GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL)
        );
        Constrains.addCompX(
                new View(about, this),
                new Rectangle(2, 2, 1, 1),
                1,
                new Insets(100, 20, 10, 20),
                new Point(GridBagConstraints.LINE_END, GridBagConstraints.HORIZONTAL)
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

        comparator.addActionListener(e -> {});

        addComponents(panel, comparatorOne, comparatorTwo, comparator);
        return panel;
    }

    private JPanel buttons() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Control Panel"));

        reset.setEnabled(false);

        runAll.addActionListener(e -> {
            methods.forEach(SortingMethod::run);
            runAll.setEnabled(false);
            setValues.setEnabled(false);
            comparator.setEnabled(false);
        });
        reset.addActionListener(e -> methods.forEach(SortingMethod::reset));
        setValues.addActionListener(e -> new Values((JPanel) getComponent(0)).setVisible(true));

        addComponents(panel, runAll, reset, setValues);
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

    private JDialog about() {
        final JDialog dialog = new JDialog();
        dialog.setTitle("About");
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setLayout(new GridBagLayout());
        JLabel sorting_comparator = new JLabel("Sorting Comparator", SwingConstants.CENTER);
        sorting_comparator.setFont(new Font(Font.MONOSPACED, Font.BOLD, 28));
        JTextArea area = new JTextArea(
                "Animated comparison of internal sorting methods.\n\n\n" +
                        "Developed by: Sergio Majé\n" +
                        "Repository: github.com/Reymon99/sorting-comparator",
                6, 30
        );
        area.setOpaque(false);
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        Constrains.addComp(
                new View(new CanvasSorting(), dialog.getContentPane()),
                new Rectangle(0, 0, 1, 1),
                new Weight(1, 1),
                new Insets(30, 30, 20, 30),
                new Point(GridBagConstraints.CENTER, GridBagConstraints.NONE)
        );
        Constrains.addCompX(
                new View(sorting_comparator, dialog.getContentPane()),
                new Rectangle(0, 1, 1, 1),
                1,
                new Insets(10, 10, 10, 10),
                new Point(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL)
        );
        Constrains.addComp(
                new View(area, dialog.getContentPane()),
                new Rectangle(0, 2, 1, 1),
                new Weight(1, 1),
                new Insets(10, 10, 10, 10),
                new Point(GridBagConstraints.CENTER, GridBagConstraints.BOTH)
        );
        dialog.pack();
        dialog.setLocationRelativeTo(this.getComponent(0));
        dialog.setResizable(false);
        return dialog;
    }
}
