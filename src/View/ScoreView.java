package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ScoreView {
    public static void showScoreWindow() {
        Pane scoreWindow = null;
        try {
            scoreWindow = FXMLLoader.load(ScoreView.class.getResource("ScoreWindow.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.initModality(Modality.NONE);

        ArrayList<String> score = HighScore.getScore();
        String[] labelNames = {"first", "second", "third", "fourth", "fifth"};
        for (int i = 0; i < 5; i++) {
            Label label = (Label) scoreWindow.lookup("#" + labelNames[i]);
            if (score.size() > i) {
                String[] splitData = score.get(i).split(",");
                label.setText(splitData[0] + "  " + splitData[1] + "  " + splitData[2]);
            } else {
                label.setText("");
            }
        }
        stage.setScene(new Scene(scoreWindow));
        stage.getIcons().add(new Image(ScoreView.class.getResourceAsStream("../res/mine.png")));
        stage.show();
    }

    public static void saveNameWindow(Stage parentStage) {
        Pane saveNameWindow = null;
        try {
            saveNameWindow = FXMLLoader.load(ScoreView.class.getResource("SaveNameWindow.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(parentStage.getScene().getWindow());
        stage.setScene(new Scene(saveNameWindow));
        stage.getIcons().add(new Image(ScoreView.class.getResourceAsStream("../res/mine.png")));
        stage.show();
    }
}
