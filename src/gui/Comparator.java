package gui;

import org.constrains.Constrains;
import org.constrains.View;
import org.constrains.Weight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class Comparator extends JPanel {
    public final static List<String> methodsStr;
    public final static HashMap<String, SortingMethod> methods;
    public final static JButton runAll;
    public final static JButton reset;
    public final static JButton setValues;
    public final static JButton comparator;
    public static int sortSpeed;

    static {
        sortSpeed = 200;
        runAll = new JButton("Run All");
        reset = new JButton("Reset");
        setValues = new JButton("Set Values");
        comparator = new JButton("Comparator");
        methods = new HashMap<>();
        methodsStr = Arrays.stream(Methods.values())
                .map(String::valueOf)
                .collect(Collectors.toList());
        methodsStr.forEach(e -> {
            String method = e.toUpperCase().replace(' ', '_');
            methods.put(e, new SortingMethod(Methods.valueOf(method)));
        });
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
                new Rectangle(0, 0, 1, 4),
                new Weight(1, 1),
                new Insets(10, 20, 10, 5),
                new Point(GridBagConstraints.CENTER, GridBagConstraints.BOTH)
        );
        Constrains.addCompX(
                new View(buttons(), this),
                new Rectangle(2, 0, 1, 1),
                1,
                new Insets(10, 20, 5, 20),
                new Point(GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL)
        );
        Constrains.addComp(
                new View(comparator(), this),
                new Rectangle(2, 1, 1, 1),
                new Weight(1, 1),
                new Insets(5, 20, 5, 20),
                new Point(GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL)
        );
        Constrains.addComp(
                new View(sortSpeed(), this),
                new Rectangle(2, 2, 1, 1),
                new Weight(1, 1),
                new Insets(5, 20, 230, 20),
                new Point(GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL)
        );
        Constrains.addCompX(
                new View(about, this),
                new Rectangle(2, 3, 1, 1),
                1,
                new Insets(30, 20, 10, 20),
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
        comparator.addActionListener(e -> {
            if (Optional.ofNullable(comparatorOne.getSelectedItem()).isPresent() &&
                    Optional.ofNullable(comparatorTwo.getSelectedItem()).isPresent()) {
                methods.get(comparatorOne.getSelectedItem().toString()).run();
                methods.get(comparatorTwo.getSelectedItem().toString()).run();
                runAll.setEnabled(false);
                reset.setEnabled(false);
                setValues.setEnabled(false);
                comparator.setEnabled(false);
                comparatorOne.setEnabled(false);
                comparatorTwo.setEnabled(false);
            }
        });
        addComponents(panel, comparatorOne, comparatorTwo, comparator);
        return panel;
    }

    private JPanel buttons() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Control Panel"));

        reset.setEnabled(true);

        runAll.addActionListener(e -> {
            methods.forEach((k, v) -> v.run());
            runAll.setEnabled(false);
            setValues.setEnabled(false);
            comparator.setEnabled(false);
        });
        reset.addActionListener(e -> reset().setVisible(true));
        setValues.addActionListener(e -> new Values((JPanel) getComponent(0)).setVisible(true));

        addComponents(panel, runAll, reset, setValues);
        return panel;
    }

    private JPanel methods() {
        JPanel panel = new JPanel(new GridLayout(3, 3, 10, 10));
        methodsStr.forEach(e -> panel.add(methods.get(e)));
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
                        "Repository: github.com/smaje99/sorting-comparator",
                6, 30
        );
        area.setOpaque(false);
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        Constrains.addComp(
                new View(new CanvasSorting(1), dialog.getContentPane()),
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

    private JPanel sortSpeed() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Sort Speed"));
        JLabel numberOfSpeed = new JLabel(String.valueOf(sortSpeed), SwingConstants.CENTER);
        numberOfSpeed.setFont(new Font(Font.MONOSPACED, Font.BOLD, 23));
        JSlider speed = new JSlider(JSlider.HORIZONTAL, 200, 600, sortSpeed);
        speed.setToolTipText("Delay time in milliseconds");
        speed.setMinorTickSpacing(200);
        speed.setMajorTickSpacing(100);
        speed.setPaintTicks(true);
        speed.setPaintLabels(true);
        speed.addChangeListener(e -> {
            sortSpeed = speed.getValue();
            numberOfSpeed.setText(String.valueOf(sortSpeed));
        });
        Constrains.addCompX(
                new View(numberOfSpeed, panel),
                new Rectangle(0, 0, 1, 1),
                1,
                new Insets(2, 10, 5, 5),
                new Point(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL)
        );
        Constrains.addCompX(
                new View(speed, panel),
                new Rectangle(0, 1, 1, 1),
                1,
                new Insets(5, 10, 5, 5),
                new Point(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL)
        );
        return panel;
    }

    private JDialog reset() {
        final JDialog dialog = new JDialog();
        dialog.setTitle("Reset Sorting Methods");
        dialog.setLayout(new GridLayout(3, 3, 10, 10));
        Container container = dialog.getContentPane();
        ArrayList<JRadioButton> buttons = new ArrayList<>(12);
        methodsStr.forEach(e -> {
            JRadioButton button = new JRadioButton(e, false);
            button.setEnabled(methods.get(e).isSorted());
            buttons.add(button);
            container.add(button);
        });
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                AtomicBoolean enable = new AtomicBoolean(false);
                buttons.stream()
                        .filter(JRadioButton::isEnabled)
                        .forEach(button -> {
                            if (button.isSelected()) {
                                methods.get(button.getText()).reset();
                                button.setEnabled(false);
                            } else enable.set(true);
                        });
                reset.setEnabled(enable.get());
                dialog.dispose();
                System.gc();
            }
        });
        dialog.pack();
        dialog.setLocationRelativeTo(this.getComponent(0));
        dialog.setResizable(false);
        return dialog;
    }
}
