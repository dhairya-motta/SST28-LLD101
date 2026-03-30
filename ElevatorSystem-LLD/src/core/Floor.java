package core;

import display.FloorDisplay;
import java.util.List;

public class Floor {
    private final int floorNumber;
    private final OutsidePanel outsidePanel;
    private final FloorDisplay floorDisplay;

    public Floor(int floorNumber, List<ElevatorCar> elevators) {
        this.floorNumber = floorNumber;
        this.outsidePanel = new OutsidePanel(floorNumber);
        this.floorDisplay = new FloorDisplay(floorNumber, elevators);
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public OutsidePanel getOutsidePanel() {
        return outsidePanel;
    }

    public FloorDisplay getFloorDisplay() {
        return floorDisplay;
    }

    public void showFloorDisplay() {
        floorDisplay.show();
    }
}
