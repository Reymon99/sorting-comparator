package threads;

import gui.CanvasSorting;
import gui.Methods;

import javax.swing.*;

public class Sorting extends Thread {
    private final Methods typeSorting;
    private final Time time;
    private final CanvasSorting canvas;
    private boolean playPause;
    private int steps;

    public Sorting(Methods typeSorting, JLabel time, boolean playPause, CanvasSorting canvas) {
        this.typeSorting = typeSorting;
        this.time = new Time(time);
        this.playPause = playPause;
        this.canvas = canvas;
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
