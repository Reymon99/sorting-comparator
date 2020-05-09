package gui;

import org.constrains.Constrains;
import org.constrains.View;
import org.constrains.Weight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Values extends JDialog implements KeyListener, ActionListener {
    public static ArrayList<Integer> data;
    private final JTextArea areaData;
    private String dataStr;

    {
        areaData = new JTextArea("No Values", 4, 40);
    }

    static {
        data = new ArrayList<>();
        Random random = new Random();
        ArrayList<Integer> range = new ArrayList<>();
        for (int i = 10; i < 100; i++) range.add(i);
        for (int i = 0; i < 10; i++) data.add(range.remove(random.nextInt(range.size())));
        range.clear();
        System.gc();
    }

    public Values(JPanel panel) {
        dataStr = "";
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
                2,
                40
        );
        info.setOpaque(false);
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        info.setEditable(false);

        areaData.setText(dataBuilder());
        areaData.setWrapStyleWord(true);
        areaData.setLineWrap(true);
        areaData.setBorder(BorderFactory.createTitledBorder("Values"));

        JButton set = new JButton("Set Values");
        JButton cancel = new JButton("Cancel");

        areaData.addKeyListener(this);
        set.addActionListener(this);
        cancel.addActionListener(e -> dispose());

        Constrains.addCompX(
                new View(info, getContentPane()),
                new Rectangle(0, 0, 2, 1),
                1,
                new Insets(5, 5, 5, 5),
                new Point(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL)
        );
        Constrains.addComp(
                new View(areaData, getContentPane()),
                new Rectangle(0, 1, 2, 1),
                new Weight(1,1),
                new Insets(5, 5, 10, 5),
                new Point(GridBagConstraints.CENTER, GridBagConstraints.BOTH)
        );
        Constrains.addCompX(
                new View(set, getContentPane()),
                new Rectangle(0, 2, 1, 1),
                1,
                new Insets(5, 5, 5, 5),
                new Point(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL)
        );
        Constrains.addCompX(
                new View(cancel, getContentPane()),
                new Rectangle(1, 2, 1, 1),
                1,
                new Insets(5, 5, 5, 5),
                new Point(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL)
        );
    }

    private String dataBuilder() {
        if (!Objects.equals(dataStr, areaData.getText())) {
            StringBuilder builder = new StringBuilder();
            Values.data.forEach(data -> builder.append(data).append(", "));
            dataStr = builder.substring(0, builder.length() - 2);
        }
        return dataStr;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void dispose() {
        areaData.setText(dataBuilder());
        super.dispose();
    }
}
