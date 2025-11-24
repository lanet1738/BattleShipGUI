import Model.Game;
import Controller.TargetGridController;
import Controller.StatusController;
import Controller.OceanGridController;
import View.GameWindow;
import Model.GridRep;

public class App {
    public static void main(String[] args) throws Exception {
        // 1. Build out the view layer...
        GameWindow gameWindow = new GameWindow("Battleship");

        // 2. Build out the Model layer...
        Game game = new Game();

        // 3. Connect Models and Views with Controllers
        TargetGridController tgc = new TargetGridController(gameWindow.getTargetPanel(), game.getHumanTargetGrid(), game.getHumanPlayer());
        StatusController sc = new StatusController(gameWindow.getStatusPane(), game);
        OceanGridController ogc = new OceanGridController(gameWindow.getOceanPanel(), game.getHumanOceanGrid());

        gameWindow.setVisible(true);
        gameWindow.pack();

        // 4. start game
        game.start();

        // seed views with current grids after placement
        gameWindow.getTargetPanel().setGridRep(new GridRep(game.getHumanTargetGrid()));
        gameWindow.getOceanPanel().setGridRep(new GridRep(game.getHumanOceanGrid()));
    }
}
