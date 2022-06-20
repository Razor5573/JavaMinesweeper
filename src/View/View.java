package View;

import Cell;
import GameBoard;
import Minesweeper;

public interface View {
    void generate();
    void changeSize(int cellsCountX, int cellsCountY);
    void showEndgameWindow(Minesweeper.ExitStatus exitStatus, int score);
    void showLoseMap(Cell[][] map);
    void showWinMap(Cell[][] mpa);
    void openCell(int X, int Y, int numMinesNear);
    void changeCellsFlagStatus(int X, int Y, GameBoard.FlagStatus flagStatus);
    void updateFlagsNum(int countFlags);
    long getTime();

    void exit();

    void showHelp();

}
