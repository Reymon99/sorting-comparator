package gui;

public enum Methods {
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

    Methods(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
