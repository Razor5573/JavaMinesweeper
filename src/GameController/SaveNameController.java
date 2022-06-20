package GameController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Minesweeper;

public class SaveNameController {
    @FXML
    TextField inName;
    public void onClick(ActionEvent event) {
        if(!inName.getText().equals("")) {
            Minesweeper.saveScore(inName.getText());
            Stage stage = (Stage) ((Node)event.getTarget()).getScene().getWindow();
            stage.close();
        }
    }
}
