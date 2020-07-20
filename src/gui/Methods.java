package gui;

public enum Methods {
    BUBBLE("Simple Bubble", 1),
    IMPROVED_BUBBLE("Improved Bubble", 1),
    OPTIMIZED_BUBBLE("Optimized Bubble", 1),
    QUICKSORT("QuickSort", 2),
    SHELLSORT("ShellSort", 2),
    RADIXSORT("RadixSort", 1),
    SELECTION("Selection", 1),
    INSERTION("Insertion", 1),
    MERGESORT("MergeSort", 1);
    private final String name;
    private final int pointers;

    Methods(String name, int pointers) {
        this.name = name;
        this.pointers = pointers;
    }

    public int getPointers() {
        return pointers;
    }

    @Override
    public String toString() {
        return name;
    }
}
