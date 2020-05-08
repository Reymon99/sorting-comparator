package gui;

public enum Methods {
    BUBBLE("Simple Bubble"),
    IMPROVED_BUBBLE("Improved Bubble"),
    OPTIMIZED_BUBBLE("Optimized Bubble"),
    QUICKSORT("QuickSort"),
    SHELLSORT("ShellSort"),
    RADIXSORT("RadixSort"),
    SELECTION("Selection"),
    INSERTION("Insertion"),
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
