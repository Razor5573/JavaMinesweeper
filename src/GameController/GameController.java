package GameController;


import javafx.event.Event;
import javafx.scene.input.MouseEvent;

public interface GameController {
    void actOnCell(MouseEvent event);
    void showHighScore();

    void startEasy();

    void startMedium();

    void startHard();

    void showHelp();

    void clearTable();

    void exit(Event event);
}
