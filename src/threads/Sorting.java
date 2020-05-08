package threads;

import gui.CanvasSorting;
import gui.Methods;

import javax.swing.*;

public class Sorting extends Thread {
    private final Methods typeSorting;
    private final Time time;
    private boolean playPause;
    private CanvasSorting canvas;

    public Sorting(Methods typeSorting, JLabel time, boolean playPause) {
        this.typeSorting = typeSorting;
        this.time = new Time(time);
        this.playPause = playPause;
        canvas = new CanvasSorting(typeSorting);
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
