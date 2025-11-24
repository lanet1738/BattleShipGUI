package Controller;

import Model.Coordinate;
import Model.GridListener;
import Model.GridRep;
import Model.Player;
import Model.TargetGrid;
import View.GridPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Util.Constants;

public class TargetGridController {
    private GridPanel view;
    private TargetGrid model;
    private GridPanelListener viewListener;
    private GridListener modelListener;
    private Player shooter;

    public TargetGridController(GridPanel view, TargetGrid model, Player shooter){
        this.view = view;
        this.model = model;
        this.shooter = shooter;

        // listen for clicks on the target panel
        viewListener = new GridPanelListener();
        view.addMouseListener(viewListener);

        // listen for updates from the model
        modelListener = new TargetGridListener();
        model.addListener(modelListener);

        // seed view with current model
        view.setGridRep(new GridRep(model));
    }

    private class GridPanelListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();

            // model coordinates (row and column)
            int modelX = x / Constants.Dimensions.CELL_SIZE;
            int modelY = y / Constants.Dimensions.CELL_SIZE;
            // System.out.println(String.format("Click: %d, %d", modelX, modelY));

            // create a shot
            if(modelX >= 0 && modelX < 10 && modelY >= 0 && modelY < 10){
                try {
                    Coordinate shot = new Coordinate(modelY, modelX);
                    model.handleShot(shot, shooter);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    private class TargetGridListener implements GridListener {
        @Override
        public void gridChanged(GridRep gridRep) {
            view.setGridRep(gridRep);
        }
    }
}
