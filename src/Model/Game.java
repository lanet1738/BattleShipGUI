package Model;

import java.util.ArrayList;
import java.util.List;

public class Game implements ShotDelegate {
    private HumanPlayer humanPlayer;
    private Player computerPlayer;
    private Player currentPlayer;
    private Player otherPlayer;
    private List<StatusListener> listeners = new ArrayList<>();

    public Game(){
        // set up
        humanPlayer = new HumanPlayer("Human", new AutomaticShipFactory(), this);
        computerPlayer = new EasyAIPlayer(new AutomaticShipFactory(), this);
    }

    public void start(){
        //place fleets
        humanPlayer.placeShips();
        computerPlayer.placeShips();

        //and go!
        currentPlayer = humanPlayer;
        otherPlayer = computerPlayer;
        currentPlayer.takeShot();
    }

    public TargetGrid getHumanTargetGrid(){
        return humanPlayer.getTargetGrid();
    }
    
    public OceanGrid getHumanOceanGrid(){
        return humanPlayer.getOceanGrid();
    }
    
    public HumanPlayer getHumanPlayer(){
        return humanPlayer;
    }

    public OceanGrid getComputerOceanGrid(){
        return ((EasyAIPlayer)computerPlayer).getOceanGrid();
    }

    public void addListener(StatusListener toAdd){
        listeners.add(toAdd);
    }

    @Override
    public void handleShot(Coordinate shot, Object sender){
        // must be this player's turn
        if(sender != currentPlayer){
            notifyListeners(String.format("Whoa there! Not your turn...%n"));
            return;
        }

        // prevent duplicate shots for human
        if(currentPlayer == humanPlayer){
            if(!humanPlayer.getTargetGrid().isValidShot(shot)){
                notifyListeners(String.format("You have already shot at %s%n", shot.toString()));
                return;
            }
        }

        // process the shot
        ShotResult result = otherPlayer.receiveShot(shot);
        currentPlayer.receiveShotResult(result);
        notifyListeners(String.format("%s fires at %s -> %s", currentPlayer.getName(), shot.toString(), result.name()));

        // check for end of game
        if(otherPlayer.shipsAreSunk()){
            notifyListeners(String.format("GAME OVER: The winner is --> %s", currentPlayer.getName()));
            return;
        }

        // swap turns
        Player temp = currentPlayer;
        currentPlayer = otherPlayer;
        otherPlayer = temp;

        // prompt current player to shoot
        currentPlayer.takeShot();
    }

    private void notifyListeners(String message){
        for(StatusListener listener : listeners){
            listener.statusMessage(message);
        }
    }
}
