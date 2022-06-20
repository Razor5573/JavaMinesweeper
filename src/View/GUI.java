package View;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Cell;
import GameBoard;
import Minesweeper;


import java.io.IOException;
import java.io.InputStream;

public class GUI implements View {
    private Stage stage;
    private Scene mainScene;
    private Pane gameWindow;
    private Pane startupWindow;
    private int cellsCountX;
    private int cellsCountY;
    private Timer timer;

    @FXML
    private GridPane map;

    public GUI(Stage stage, Pane gameWindow, Pane startupWindow, int cellsCountX, int cellsCountY) {
        this.stage = stage;
        this.cellsCountX = cellsCountX;
        this.cellsCountY = cellsCountY;
        this.timer = new Timer();
        this.gameWindow = gameWindow;
        this.startupWindow = startupWindow;
    }

    @Override
    public void generate() {
        Label label = (Label) gameWindow.lookup("#timeSpent");
        timer.runTimer(label);
        map = new GridPane();
        InputStream input = getClass().getResourceAsStream("../res/unmarked.png");
        Image image = new Image(input);
        for (int x = 0; x < cellsCountX; x++) {
            for (int y = 0; y < cellsCountY; y++) {
                ImageView imageView = new ImageView(image);
                map.add(imageView, x, y);
            }
        }
        final int GRID_OFFSET = 52;
        map.setLayoutY(GRID_OFFSET);
        gameWindow.getChildren().add(map);
        final int CELL_SIZE = 26;
        if (mainScene == null) {
            mainScene = new Scene(gameWindow, cellsCountX * CELL_SIZE, cellsCountY * CELL_SIZE + GRID_OFFSET);
        } else {
            mainScene.setRoot(gameWindow);
        }
        updateFlagsNum(0);
        stage.setScene(mainScene);
        stage.setWidth(cellsCountX * CELL_SIZE + 15);
        stage.setHeight(cellsCountY * CELL_SIZE + GRID_OFFSET + 39);
        stage.setTitle("Minesweeper");
        stage.setResizable(false);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../res/mine.png")));
        stage.show();
    }

    @Override
    public void changeSize(int cellsCountX, int cellsCountY) {
        this.cellsCountX = cellsCountX;
        this.cellsCountY = cellsCountY;
        this.timer = new Timer();
        generate();
    }


    @Override
    public void showEndgameWindow(Minesweeper.ExitStatus exitStatus, int score) {
        timer.stopTimer();
        Pane startupWindow = null;
        try {
            startupWindow = FXMLLoader.load(getClass().getResource("StartupWindow.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.stage.getScene().getWindow());
        Label gameStatus = (Label) startupWindow.lookup("#gameStatus");
        gameStatus.setText(String.valueOf(exitStatus));
        stage.setScene(new Scene(startupWindow));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../res/mine.png")));
        stage.setOnCloseRequest(event -> {
            System.out.println("Stage is closing");
            Minesweeper.startGame();
        });
        stage.show();
        if (exitStatus == Minesweeper.ExitStatus.WIN) {
            ScoreView.saveNameWindow(stage);
        }
    }

    public static void showHighScore() {
        ScoreView.showScoreWindow();
    }

    @Override
    public void showLoseMap(Cell[][] map) {
        String imgName;
        for (int x = 0; x < cellsCountX; x++) {
            for (int y = 0; y < cellsCountY; y++) {
                if (map[x][y].isOpen() && map[x][y].isWithMine()) {
                    imgName = "hit.png";
                    setImage(x, y, imgName);
                } else if (map[x][y].isWithFlag() && !(map[x][y].isWithMine())) {
                    imgName = "notmine.png";
                    setImage(x, y, imgName);
                } else if (!(map[x][y].isWithFlag()) && map[x][y].isWithMine()) {
                    imgName = "mine.png";
                    setImage(x, y, imgName);
                }
            }
        }
    }

    public void showWinMap(Cell[][] map) {
        for (int x = 0; x < cellsCountX; x++) {
            for (int y = 0; y < cellsCountY; y++) {
                if (!(map[x][y].isWithFlag())) {
                    openCell(x, y, map[x][y].getNumMinesNearby());
                }
            }
        }
    }

    @Override
    public void openCell(int X, int Y, int numMinesNear) {
        String imgName;
        if (numMinesNear == 0) {
            imgName = "tilebase.png";
        } else {
            imgName = "mine" + numMinesNear + ".png";
        }
        setImage(X, Y, imgName);
    }

    @Override
    public void changeCellsFlagStatus(int X, int Y, GameBoard.FlagStatus flagStatus) {
        String imgName = null;
        switch (flagStatus){
            case UNMARKED:
                imgName = "unmarked.png";
                break;
            case QUESTION:
                imgName = "question.png";
                break;
            case FLAG:
                imgName = "flag.png";
                break;
        }
        setImage(X, Y, imgName);
    }

    @Override
    public void updateFlagsNum(int countFlags) {
        Label flagsRemainingLabel = (Label) gameWindow.lookup("#flagsRemainingLabel");
        flagsRemainingLabel.setText(String.valueOf(countFlags));
    }

    @Override
    public long getTime() {
        return Long.parseLong(((Label) gameWindow.lookup("#timeSpent")).getText());
    }

    private void setImage(int X, int Y, String imgName) {
        InputStream inputImage = getClass().getResourceAsStream("../res/" + imgName);
        ImageView cellView = (ImageView) GridPaneUtil.getNodeFromGridPane(map, X, Y);
        cellView.setImage(new Image(inputImage));
        stage.show();
    }

    @Override
    public void exit() {
        stage.close();
    }

    @Override
    public void showHelp() {
        HelpView.showHelp();
    }


}
