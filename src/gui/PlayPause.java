package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.function.Consumer;

public class PlayPause extends JLabel {
    private final String play;
    private final String pause;
    private boolean playPause;
    private Consumer<MouseEvent> event;

    {
        play = "/resources/play.png";
        pause = "/resources/pause.png";
    }

    public PlayPause() {
        playPause = false;
        playPause();
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setHorizontalAlignment(CENTER);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playPause();
                event.accept(e);
            }
        });
    }

    private void playPause() {
        try {
            setIcon(new ImageIcon(
                    ImageIO.read(PlayPause.class.getResource(
                            (playPause = !playPause) ? play : pause
                    )).getScaledInstance(
                            48,
                            48,
                            Image.SCALE_DEFAULT
                    )
            ));
        } catch (IOException e) { // None
        }
    }

    public void setEvent(Consumer<MouseEvent> event) {
        this.event = event;
    }
}
