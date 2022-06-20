package GameController;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Minesweeper;

public class StartupWindowController {
    @FXML
    private Label gameStatus;
    @FXML
    private Button newGame;
    @FXML
    private Button highScore;
    @FXML
    private Button exit;
    @FXML
    private AnchorPane dialogAnchorPane;
    @FXML
    TextField inName;

    @FXML
    public void startNewGame(Event event) {
        Minesweeper.startGame();
        Stage stage = (Stage) ((Node)event.getTarget()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void exitAction(Event event) {
        Minesweeper.exit();
        Stage stage = (Stage) ((Node)event.getTarget()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void exitApplication(ActionEvent event) {
        ((Stage)dialogAnchorPane.getScene().getWindow()).close();
    }
}
