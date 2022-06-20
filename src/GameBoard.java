import View.View;

import java.util.Random;

public class GameBoard {
    private Cell[][] map;
    private final int cellsCountX;
    private final int cellsCountY;
    private final int percentMine;
    private int countFlags;
    private int totalMinesNum;
    private int openedCellsNum;
    private int unmarkedMines;

    public enum FlagStatus {
        FLAG,
        UNMARKED,
        QUESTION

    }

    public GameBoard(int cellsCountX, int cellsCountY, int percentMine) {
        this.cellsCountX = cellsCountX;
        this.cellsCountY = cellsCountY;
        this.percentMine = percentMine;
    }

    public void generate() {
        openedCellsNum = 0;
        map = new Cell[cellsCountX][cellsCountY];
        int[][] count = new int[cellsCountX][cellsCountY];
        Random rnd = new Random();
        totalMinesNum = 0;
        for (int x = 0; x < cellsCountX; x++) {
            for (int y = 0; y < cellsCountY; y++) {
                boolean isMine = rnd.nextInt(100) < percentMine;
                map[x][y] = new Cell(isMine, x, y, count[x][y]);
                if (isMine) {
                    totalMinesNum++;
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            try {
                                if (map[x + i][y + j] == null) { //if there is no cell, save info into table
                                    count[x + i][y + j]++;
                                } else { //else save info into cell
                                    map[x + i][y + j].incNearMines();
                                }
                            } catch (java.lang.ArrayIndexOutOfBoundsException ignored) {}
                        }
                    }
                }
            }
        }
        unmarkedMines = totalMinesNum;
        System.out.println("unmarked mines: " + unmarkedMines);
    }

    public Minesweeper.ExitStatus changeFlag(int X, int Y, View view) {
        Cell thatCell = map[X][Y];
        if (!thatCell.isOpen()) {
            switch (thatCell.getFlagStatus()){
                case FLAG:
                    countFlags--;
                    thatCell.setQuest();
                    if (thatCell.isWithMine()) {
                        unmarkedMines++;
                        System.out.println("unmarkedMines: " + unmarkedMines);
                    }
                    break;
                case QUESTION:
                    thatCell.setUnmarked();
                    break;
                case UNMARKED:
                    thatCell.setFlag();
                    countFlags++;
                    if (thatCell.isWithMine()) {
                        unmarkedMines--;
                        System.out.println("unmarkedMines: " + unmarkedMines);
                    }
            }
            view.updateFlagsNum(countFlags);
            view.changeCellsFlagStatus(X, Y, thatCell.getFlagStatus());
        }
        return Minesweeper.ExitStatus.CONTINUE;
    }

    public Minesweeper.ExitStatus checkCell(int X, int Y, View view) {
        Cell thatCell = map[X][Y];
        if (thatCell.isWithFlag() || thatCell.isOpen()) {
            return Minesweeper.ExitStatus.CONTINUE;
        }
        thatCell.open();
        openedCellsNum++;
        if (thatCell.isWithMine()) {
            view.showLoseMap(map);
            return Minesweeper.ExitStatus.FAIL;
        } else if (thatCell.inNothingNear()) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (X + i < cellsCountX && X + i > -1 && Y + j < cellsCountY && Y + j > -1) {
                        checkCell(X + i, Y + j, view);
                    }
                }
            }
        }
        view.openCell(X, Y, thatCell.getNumMinesNearby());
        if (openedCellsNum == cellsCountX*cellsCountY - totalMinesNum){
            return Minesweeper.ExitStatus.WIN;
        }
        return Minesweeper.ExitStatus.CONTINUE;
    }

    public int getTotalMinesNum() {
        return totalMinesNum;
    }
}
