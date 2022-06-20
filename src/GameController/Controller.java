package GameController;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Minesweeper;
import View.GUI;
import View.HighScore;


public class Controller implements GameController {

    @FXML
    private Label timeSpent;
    @FXML
    private MenuItem highScore;
    @FXML
    private Label flagsRemainingLabel;


    @Override
    public void actOnCell(MouseEvent mouseEvent) {
        int x = (int) (mouseEvent.getX() / 26);
        int y = (int) (mouseEvent.getY() / 26 - 2);

        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            Minesweeper.openCell(x, y);
        } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
            Minesweeper.changeFlag(x, y);
        }
        System.out.println("Cell (" + x + ", " + y + ")");
    }

    @Override
    public void showHighScore() {
        GUI.showHighScore();
    }

    @Override
    public void startEasy() {
        Minesweeper.startGame(9, 9, 15);
    }

    @Override
    public void startMedium() {
        Minesweeper.startGame(16, 16, 25);
    }

    @Override
    public void startHard() {
        Minesweeper.startGame(30, 16, 35);
    }

    @Override
    public void showHelp() {
        Minesweeper.showHelp();
    }

    @Override
    public void clearTable() {
        HighScore.dropTable();
    }

    @Override
    public void exit(Event event) {
        Minesweeper.exit();
//        Stage stage = (Stage) ((Node)event.getTarget()).getScene().getWindow();
//        stage.close();
    }


}
