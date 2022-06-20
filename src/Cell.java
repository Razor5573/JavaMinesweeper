public class Cell {
    private final boolean mine;
    private boolean isOpen = false;
    private GameBoard.FlagStatus flag = GameBoard.FlagStatus.UNMARKED;
    private int numMinesNearby;
    private final int X;
    private final int Y;

    public Cell(boolean isWithMine, int X, int Y, int numMinesNearby) {
        mine = isWithMine;
        this.X = X;
        this.Y = Y;
        this.numMinesNearby = numMinesNearby;
    }

    public int getNumMinesNearby() {
        return numMinesNearby;
    }

    public void open() {
        isOpen = true;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setUnmarked() {
        flag = GameBoard.FlagStatus.UNMARKED;
    }

    public void setQuest() {
        flag = GameBoard.FlagStatus.QUESTION;
    }

    public void setFlag() {
        flag = GameBoard.FlagStatus.FLAG;
    }

    public boolean isWithFlag() {
        if (flag == GameBoard.FlagStatus.UNMARKED) {
            return false;
        }
        return true;
    }

    public GameBoard.FlagStatus getFlagStatus() {
        return flag;
    }

    public boolean isWithMine() {
        return mine;
    }

    public boolean inNothingNear() {
        return numMinesNearby == 0;
    }

    public void incNearMines() {
        this.numMinesNearby++;
    }

}
