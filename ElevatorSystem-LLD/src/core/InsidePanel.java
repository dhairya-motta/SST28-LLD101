package core;

import buttons.DoorButton;
import buttons.ElevatorButton;
import core.ElevatorCar;

public class InsidePanel {
    private final ElevatorButton[] floorButtons;
    private final DoorButton doorOpenButton;
    private final DoorButton doorCloseButton;
    private final ElevatorCar elevatorCar;
    private static final int TOTAL_FLOORS = 15;

    public InsidePanel(ElevatorCar elevatorCar) {
        this.elevatorCar = elevatorCar;
        this.floorButtons = new ElevatorButton[TOTAL_FLOORS];
        for (int i = 0; i < TOTAL_FLOORS; i++) {
            floorButtons[i] = new ElevatorButton(i + 1, elevatorCar.getElevatorId());
        }
        this.doorOpenButton = new DoorButton(true, elevatorCar.getElevatorId());
        this.doorCloseButton = new DoorButton(false, elevatorCar.getElevatorId());
    }

    public void pressFloorButton(int floor) {
        if (floor < 1 || floor > TOTAL_FLOORS) {
            System.out.println("Invalid floor: " + floor);
            return;
        }
        ElevatorButton btn = floorButtons[floor - 1];
        btn.pressed();
        elevatorCar.addDestinationFloor(floor);
    }

    public void pressDoorOpen() {
        doorOpenButton.pressed();
        elevatorCar.openDoor();
    }

    public void pressDoorClose() {
        doorCloseButton.pressed();
        elevatorCar.closeDoor();
    }

    public ElevatorButton[] getFloorButtons() {
        return floorButtons;
    }

    public DoorButton getDoorOpenButton() {
        return doorOpenButton;
    }

    public DoorButton getDoorCloseButton() {
        return doorCloseButton;
    }
}
