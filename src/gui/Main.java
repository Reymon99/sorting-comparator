package gui;

import javax.swing.*;

public class Main extends JFrame {
    private Main() {
        super("Sorting Comparator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(new Comparator());
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new Main().setVisible(true);
    }
}
