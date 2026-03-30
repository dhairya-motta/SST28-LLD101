package display;

import enums.Direction;

public class ExternalDisplay implements Display {
    private int currentFloor;
    private Direction direction;
    private final int elevatorId;

    public ExternalDisplay(int elevatorId) {
        this.elevatorId = elevatorId;
        this.currentFloor = 1;
        this.direction = Direction.IDLE;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void update() {
        show();
    }

    @Override
    public void show() {
        System.out.println("[External Display | Elevator " + elevatorId + "] Floor: " + currentFloor + " | Direction: " + direction);
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }
}
