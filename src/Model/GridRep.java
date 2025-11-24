package Model;

public class GridRep {
    private Grid grid;

    public GridRep(Grid grid){
        this.grid = grid;
    }

    public Grid getGrid(){
        return grid;
    }

    public CellState getStateAt(int row, int column){
        return grid.cells[row][column].getState();
    }
}
