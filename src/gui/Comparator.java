package gui;

import org.constrains.Constrains;
import org.constrains.View;
import org.constrains.Weight;

import javax.swing.*;
import java.awt.*;

public class Comparator extends JPanel {
    private DefaultListModel listSorting;

    public enum methods {
        BURBUJA("Burbuja Simple"),
        BURBUJA_MEJORADA("Burbuja Mejorada"),
        BURBUJA_OPTIMIZADA("Burbuja Optimizada"),
        QUICKSORT("QuickSort"),
        SHELLSORT("ShellSort"),
        RADIXSORT("RadixSort"),
        SELECCION("Selección"),
        INSERCION("Inserción"),
        MERGESORT("MergeSort");
        private final String name;

        methods(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

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
