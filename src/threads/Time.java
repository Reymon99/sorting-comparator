package threads;

import javax.swing.*;

public class Time extends Thread {
    private final JLabel time;
    private boolean life;

    public Time(JLabel time) {
        this.time = time;
        life = true;
    }

    @Override
    public void run() {
        int millisecond = 0;
        while (life) {
            time.setText(format(millisecond++));
            time.updateUI();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {//None
            }
        }
    }

    private String format(int millisecond) {
        return millisecond + " ms";
    }

    public void setLife(boolean life) {
        this.life = life;
    }
}
