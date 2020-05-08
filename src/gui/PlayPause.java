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
        play = "/resources/image/play.png";
        pause = "/resources/image/pause.png";
    }

    /**
     * Botón Play and Pause
     */
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

    /**
     * Modifica el icono del boton según su estado
     */
    private void playPause() {
        try {
            setIcon(new ImageIcon(
                    ImageIO.read(PlayPause.class.getResource(
                            (playPause = !playPause) ? play : pause
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
     * Fija el evento del botón
     * @param event evento Mouse
     */
    public void setEvent(Consumer<MouseEvent> event) {
        this.event = event;
    }
}
