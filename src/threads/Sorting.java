package threads;

import gui.CanvasSorting;
import gui.Methods;
import gui.SortingMethod;

public class Sorting extends Thread {
    private final Methods typeSorting;
    private final SortingMethod method;
    private boolean playPause;
    private int steps;

    public Sorting(Methods typeSorting, SortingMethod method, boolean playPause) {
        this.typeSorting = typeSorting;
        this.method = method;
        this.playPause = playPause;
    }

    @Override
    public void run() {
        super.run();
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
