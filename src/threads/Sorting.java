package threads;

import gui.Methods;

import javax.swing.*;

public class Sorting extends Thread {
    private final Methods typeSorting;
    private final Time time;
    private boolean playPause;

    public Sorting(Methods typeSorting, JLabel time, boolean playPause) {
        this.typeSorting = typeSorting;
        this.time = new Time(time);
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
}
