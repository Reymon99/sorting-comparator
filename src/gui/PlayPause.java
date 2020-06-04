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
    private Consumer<MouseEvent> eventPlay;
    private Consumer<MouseEvent> eventPause;

    {
        play = "/resources/image/play.png";
        pause = "/resources/image/pause.png";
    }

    /**
     * Botón Play and Pause
     */
    public PlayPause() {
        playPause = true;
        eventPause = e -> {};
        eventPlay = e -> {};
        playPause();
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setHorizontalAlignment(CENTER);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                action();
            }
        });
    }

    /**
     * Modifica el icono del boton según su estado
     */
    private void playPause() {
        try {
            setIcon(new ImageIcon(
                    ImageIO.read(PlayPause.class.getResource(
                            playPause ? play : pause
                    )).getScaledInstance(
                            32,
                            32,
                            Image.SCALE_DEFAULT
                    )
            ));
        } catch (IOException e) { // None
        }
    }

    /**
     * Fija el evento Play del botón
     * @param eventPlay evento Mouse
     */
    public void setEventPlay(Consumer<MouseEvent> eventPlay) {
        this.eventPlay = eventPlay;
    }

    /**
     * Fija el evento Pause del botón
     * @param eventPause evento Mouse
     */
    public void setEventPause(Consumer<MouseEvent> eventPause) {
        this.eventPause = eventPause;
    }

    public void action() {
        if (playPause) eventPlay.accept(null);
        else eventPause.accept(null);
    }

    public void setPlayPause(boolean playPause) {
        this.playPause = playPause;
        playPause();
    }
}
