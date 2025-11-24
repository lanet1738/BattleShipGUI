package Model;

public class HumanPlayer implements Player, ShotDelegate {
    private String name;
    private OceanGrid oceanGrid;
    private TargetGrid targetGrid;
    private ShipFactory shipFactory;
    private ShotDelegate shotDelegate;

    public HumanPlayer(String name, ShipFactory shipFactory, ShotDelegate delegate){
        this.name = name;
        this.shipFactory = shipFactory;
        this.shotDelegate = delegate;
        this.oceanGrid = new OceanGrid();
        this.targetGrid = new TargetGrid(delegate);
    }

    public void placeShips(){
        oceanGrid.placeShips(shipFactory.getShips());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void takeShot() {
        // wait for player to click the target grid
    }

    @Override
    public ShotResult receiveShot(Coordinate shot) {
        return oceanGrid.receiveShot(shot);
    }

    @Override
    public void receiveShotResult(ShotResult shotResult) {
        targetGrid.receiveShotResult(shotResult);
    }

    @Override
    public boolean shipsAreSunk() {
        return oceanGrid.shipsAreSunk();
    }

    public TargetGrid getTargetGrid(){
        return targetGrid;
    }
    
    public OceanGrid getOceanGrid(){
        return oceanGrid;
    }

    @Override
    public void handleShot(Coordinate shot, Object sender) {
        // forward to game delegate
        shotDelegate.handleShot(shot, this);
    }
}
