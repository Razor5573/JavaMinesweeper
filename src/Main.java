import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        final int cellsCountX = 15, cellsCountY = 10;
        Pane gameWindow = FXMLLoader.load(getClass().getResource("View/GameWindow.fxml"));
        Pane startupWindow = FXMLLoader.load(getClass().getResource("View/StartupWindow.fxml"));
        Minesweeper.init(cellsCountX, cellsCountY, primaryStage, gameWindow, startupWindow);
        Minesweeper.startGame();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
