import Model.Game;
import Controller.TargetGridController;
import Controller.StatusController;
import Controller.OceanGridController;
import Controller.WindowController;
import View.GameWindow;
import Model.GridRep;

public class App {
    public static void main(String[] args) throws Exception {
        // 1. Build out the view layer...
        GameWindow gameWindow = new GameWindow("Battleship");
        Game game = new Game();
        new WindowController(gameWindow, game);

        gameWindow.setVisible(true);
        gameWindow.pack();

        // 4. start game
        game.start();

        // seed views with current grids after placement
        gameWindow.getTargetPanel().setGridRep(new GridRep(game.getHumanTargetGrid()));
        gameWindow.getOceanPanel().setGridRep(new GridRep(game.getHumanOceanGrid()));
    }
}
