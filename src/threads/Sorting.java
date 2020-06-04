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
        switch (typeSorting) {
            case BUBBLE:
            case IMPROVED_BUBBLE:
            case OPTIMIZED_BUBBLE:
            case QUICKSORT:
                break;
            case SHELLSORT:
                shellSort(method.getCanvas().getData());
                break;
            case RADIXSORT:
            case SELECTION:
            case INSERTION:
            case MERGESORT:
                break;
        }
        method.getPlayPause().setPlayPause(true);
    }

    private void swap(ArrayList<Data> data, int i, int j) {
        method.setSwaps(++swaps);
        Data dataTemp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, dataTemp);
        method.getCanvas().sorting(i);
        try {
            Thread.sleep(Comparator.sortSpeed);
        } catch (InterruptedException e) {  // None
        }
    }

    public void shellSort(ArrayList<Data> data) {
        int j;
        int k;
        int skip = data.size() / 2;
        while (skip > 0) {
            for (int i = skip; i < data.size(); i++) {
                j = i - skip;
                while (j >= 0) {
                    k = j + skip;
                    if (data.get(j).getData() <= data.get(k).getData()) j = -1;
                    else {
                        swap(data, j, k);
                        j -= skip;
                    }
                    method.setSteps(++steps);
                }
            }
            skip /= 2;
        }
        method.updateUI();
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
