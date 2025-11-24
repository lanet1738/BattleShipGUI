package Controller;

import Model.GridListener;
import Model.GridRep;
import Model.OceanGrid;
import View.GridPanel;

public class OceanGridController {
    private GridPanel view;
    private OceanGrid model;
    private GridListener modelListener;

    public OceanGridController(GridPanel view, OceanGrid model){
        this.view = view;
        this.model = model;

        modelListener = new OceanGridListener();
        this.model.addListener(modelListener);

        // initial paint with current grid state (ships)
        view.setGridRep(new GridRep(model));
    }

    private class OceanGridListener implements GridListener {
        @Override
        public void gridChanged(GridRep gridRep) {
            view.setGridRep(gridRep);
        }
    }
}
