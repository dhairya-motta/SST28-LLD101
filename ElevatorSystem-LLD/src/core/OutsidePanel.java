package core;

import buttons.FloorButton;
import enums.Direction;

public class OutsidePanel {
    private final FloorButton upButton;
    private final FloorButton downButton;
    private final int floorNumber;

    public OutsidePanel(int floorNumber) {
        this.floorNumber = floorNumber;
        this.upButton = new FloorButton(floorNumber, Direction.UP);
        this.downButton = new FloorButton(floorNumber, Direction.DOWN);
    }

    public FloorButton getUpButton() {
        return upButton;
    }

    public FloorButton getDownButton() {
        return downButton;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void pressUp() {
        upButton.pressed();
    }

    public void pressDown() {
        downButton.pressed();
    }
}
