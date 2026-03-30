package buttons;

import enums.Direction;

public class FloorButton extends Button {
    private final int floorNumber;
    private final Direction direction;

    public FloorButton(int floorNumber, Direction direction) {
        super();
        this.floorNumber = floorNumber;
        this.direction = direction;
    }

    @Override
    public void pressed() {
        setPressed(true);
        System.out.println("Floor button pressed at floor " + floorNumber + " for direction " + direction);
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public Direction getDirection() {
        return direction;
    }
}
