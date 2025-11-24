package Util;

import java.util.Map;

public class Constants {
    public final static Map<String, Integer> shipSpecifications = Map.of("Carrier", 5, "Battleship", 4, "Destroyer", 3, "Submarine", 3, "Patrol Boat", 2);

    public class Dimensions {
        public final static int CELL_SIZE = 32;
        public final static int GRID_SIZE = 10 * CELL_SIZE + 1;
        public final static int STATUS_AREA_HEIGHT = 36;
        public final static int FOOTER_AREA_HEIGHT = 30;
        public final static int WINDOW_WIDTH = GRID_SIZE;
        public final static int WINDOW_HEIGHT = 2 * GRID_SIZE + STATUS_AREA_HEIGHT + FOOTER_AREA_HEIGHT - 60;
        public final static int PEG_DIAMETER = 8;
    }
    
	private Constants(){} 
}
