package core;

import enums.DoorState;
import enums.ElevatorState;

public class Door {
    private DoorState doorState;
    private final int elevatorId;

    public Door(int elevatorId) {
        this.elevatorId = elevatorId;
        this.doorState = DoorState.CLOSED;
    }

    public void open(ElevatorState elevatorState) {
        if (elevatorState == ElevatorState.IDLE || elevatorState == ElevatorState.DOOR_OPEN) {
            this.doorState = DoorState.OPEN;
            System.out.println("Elevator " + elevatorId + ": Door OPENED");
        } else {
            System.out.println("Elevator " + elevatorId + ": Cannot open door — elevator is moving!");
        }
    }

    public void close() {
        this.doorState = DoorState.CLOSED;
        System.out.println("Elevator " + elevatorId + ": Door CLOSED");
    }

    public DoorState getDoorState() {
        return doorState;
    }

    public boolean isOpen() {
        return doorState == DoorState.OPEN;
    }
}
