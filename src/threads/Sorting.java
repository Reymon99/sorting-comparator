package threads;

import gui.CanvasSorting;
import gui.Methods;

import javax.swing.*;

public class Sorting extends Thread {
    private final Methods typeSorting;
    private final Time time;
    private boolean playPause;
    private final CanvasSorting canvas;

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
}
