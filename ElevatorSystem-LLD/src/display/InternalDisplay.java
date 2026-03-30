package display;

import enums.Direction;

public class InternalDisplay implements Display {
    private int currentFloor;
    private Direction direction;
    private int currentPassengerCount;
    private final int elevatorId;
    private static final int MAX_CAPACITY = 8;

    public InternalDisplay(int elevatorId) {
        this.elevatorId = elevatorId;
        this.currentFloor = 1;
        this.direction = Direction.IDLE;
        this.currentPassengerCount = 0;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setCurrentPassengerCount(int count) {
        this.currentPassengerCount = count;
    }

    @Override
    public void update() {
        show();
    }

    @Override
    public void show() {
        System.out.println("[Internal Display | Elevator " + elevatorId + "] Floor: " + currentFloor
                + " | Direction: " + direction
                + " | Capacity: " + currentPassengerCount + "/" + MAX_CAPACITY);
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getCurrentPassengerCount() {
        return currentPassengerCount;
    }
}
