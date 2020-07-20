package threads;

import gui.Comparator;
import gui.Data;
import gui.Methods;
import gui.SortingMethod;

import java.util.ArrayList;

public class Sorting extends Thread {
    private final Methods typeSorting;
    private final SortingMethod method;
    private boolean playPause;
    private int steps;
    private int swaps;

    public Sorting(Methods typeSorting, SortingMethod method, boolean playPause) {
        this.typeSorting = typeSorting;
        this.method = method;
        this.playPause = playPause;
        steps = 0;
        swaps = 0;
    }

    @Override
    public void run() {
        ArrayList<Data> data = method.getCanvas().getData();
        switch (typeSorting) {
            case BUBBLE:
            case IMPROVED_BUBBLE:
            case OPTIMIZED_BUBBLE:
                break;
            case QUICKSORT:
                quickSort(data, 0, data.size() - 1);
                break;
            case SHELLSORT:
                shellSort(data);
                break;
            case RADIXSORT:
            case SELECTION:
            case INSERTION:
            case MERGESORT:
                break;
        }
        method.updateUI();
        method.getPlayPause().setPlayPause(true);
    }

    private void swap(ArrayList<Data> data, int i, int j) {
        method.setSwaps(++swaps);
        Data dataTemp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, dataTemp);
    }

    private void selection(int... pointers) {
        method.getCanvas().sorting(pointers);
        try {
            Thread.sleep(Comparator.sortSpeed);
        } catch (InterruptedException e) {  // None
        }
    }

    private void increaseSteps() {
        method.setSteps(++steps);
    }

    private void quickSort(ArrayList<Data> data, int first, int last) {
        int i = first;
        int j = last;
        int pivote = data.get((first + last) / 2).getData();
        do {
            while (data.get(i).getData() < pivote) i++;
            while (data.get(j).getData() > pivote) j--;
            selection(i, j);
            if (i <= j) {
                if (i != j) swap(data, i, j);
                i++;
                j--;
            }
            increaseSteps();
        } while (i <= j);
        if (first < j) quickSort(data, first, j);
        if (i < last) quickSort(data, i, last);
    }

    private void shellSort(ArrayList<Data> data) {
        int j;
        int k;
        int skip = data.size() / 2;
        while (skip > 0) {
            for (int i = skip; i < data.size(); i++) {
                j = i - skip;
                while (j >= 0) {
                    k = j + skip;
                    selection(j, k);
                    if (data.get(j).getData() <= data.get(k).getData()) j = -1;
                    else {
                        swap(data, j, k);
                        j -= skip;
                    }
                    increaseSteps();
                }
            }
            skip /= 2;
        }
    }

    public boolean isPlayPause() {
        return playPause;
    }

    public void setPlayPause(boolean playPause) {
        this.playPause = playPause;
    }

    public String getTypeSorting() {
        return typeSorting.toString();
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }
}
