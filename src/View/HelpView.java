package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HelpView {
    public static void showHelp() {
        Pane helpWindow = null;
        try {
            helpWindow = FXMLLoader.load(ScoreView.class.getResource("HelpWindow.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.initModality(Modality.NONE);
        stage.getIcons().add(new Image(ScoreView.class.getResourceAsStream("../res/question.png")));
        stage.setScene(new Scene(helpWindow));
        stage.show();
    }
}
