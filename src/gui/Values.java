package gui;

import org.constrains.Constrains;
import org.constrains.View;
import org.constrains.Weight;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Values extends JDialog implements KeyListener, ActionListener {
    public static ArrayList<Integer> data;
    private final DefaultTableModel dataModel;
    private final JButton set;
    private final JButton cancel;
    private final JSpinner digits;

    {
        dataModel = dataModel();
        set = new JButton("Set Values");
        cancel = new JButton("Cancel");
        digits = new JSpinner(new SpinnerNumberModel(
                String.valueOf(data.get(0)).length(),
                2, 5, 1
        ));
    }

    static {
        data = new ArrayList<>();
        Random random = new Random();
        List<Integer> range = IntStream.range(10, 100).boxed().collect(Collectors.toList());
        for (int i = 0; i < 10; i++) data.add(range.remove(random.nextInt(range.size())));
        range.clear();
        System.gc();
    }

    public Values(JPanel panel) {
        setTitle("Set Values");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());
        init();
        pack();
        setLocationRelativeTo(panel);
        setResizable(false);
    }

    private void init() {
        JTextArea info = new JTextArea(
                "Only ten non-repeating integer values of the same number of digits are allowed, " +
                        "separated by commas.",
                2, 40
        );
        info.setOpaque(false);
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        info.setEditable(false);

        dataModel.setColumnIdentifiers(new Integer[]{1, 2, 3, 4, 5});
        dataModel.addRow(data.subList(0, 5).toArray());
        dataModel.addRow(data.subList(5, 10).toArray());

        JTable dataTable = dataTable(dataModel);

        dataTable.addKeyListener(this);
        set.addActionListener(this);
        cancel.addActionListener(e -> dispose());
        digits.addChangeListener(e -> dataTable.updateUI());

        set.setEnabled(false);

        Constrains.addCompX(
                new View(info, getContentPane()),
                new Rectangle(0, 0, 2, 1),
                1,
                new Insets(5, 5, 5, 5),
                new Point(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL)
        );
        Constrains.addCompX(
                new View(new JLabel("Number of digits are", SwingConstants.RIGHT), getContentPane()),
                new Rectangle(0, 1, 1, 1),
                1,
                new Insets(5, 5, 5, 1),
                new Point(GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL)
        );
        Constrains.addCompX(
                new View(digits, getContentPane()),
                new Rectangle(1, 1, 1, 1),
                1,
                new Insets(5, 1, 5, 5),
                new Point(GridBagConstraints.WEST, GridBagConstraints.NONE)
        );
        Constrains.addComp(
                new View(dataTable, getContentPane()),
                new Rectangle(0, 2, 2, 1),
                new Weight(1,1),
                new Insets(5, 5, 10, 5),
                new Point(GridBagConstraints.CENTER, GridBagConstraints.BOTH)
        );
        Constrains.addCompX(
                new View(set, getContentPane()),
                new Rectangle(0, 3, 1, 1),
                1,
                new Insets(5, 5, 5, 5),
                new Point(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL)
        );
        Constrains.addCompX(
                new View(cancel, getContentPane()),
                new Rectangle(1, 3, 1, 1),
                1,
                new Insets(5, 5, 5, 5),
                new Point(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL)
        );
    }

    private DefaultTableModel dataModel() {
        return new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return Integer.class;
            }
        };
    }

    private JTable dataTable(DefaultTableModel model) {
        return new JTable(model) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                Color color;
                boolean enabled;
                String number = getValueAt(row, column).toString();
                if (nDigits(number) && !isNegative(number) && !repeatedNumber(number)) {
                    color = Color.WHITE;
                    enabled = true;
                } else {
                    color = Color.RED;
                    enabled = false;
                }
                digits.setEnabled(enabled);
                set.setEnabled(enabled && !changedData());
                component.setBackground(color);
                return component;
            }
        };
    }

    private boolean nDigits(String number) {
        return number.length() == Integer.parseInt(digits.getValue().toString());
    }

    private boolean isNegative(String number) {
        return number.contains("-");
    }

    private boolean changedData() {
        var i = new AtomicInteger();
        return dataTable().stream().allMatch(e -> e.equals(data.get(i.getAndIncrement())));
    }

    private boolean repeatedNumber(String number) {
        int n = Integer.parseInt(number);
        return dataTable().stream().filter(e -> e == n).count() > 1;
    }

    private ArrayList<Integer> dataTable() {
        return (ArrayList) dataModel
                .getDataVector()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Values.data = dataTable();
        dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (!Character.isDigit(e.getKeyChar())) e.consume();
    }

    @Override
    public void keyPressed(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void dispose() {
        dataModel.removeRow(0);
        dataModel.removeRow(0);
        dataModel.addRow(data.subList(0, 5).toArray());
        dataModel.addRow(data.subList(5, 10).toArray());
        super.dispose();
    }
}
