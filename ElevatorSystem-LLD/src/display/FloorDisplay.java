package display;

import core.ElevatorCar;
import java.util.List;

public class FloorDisplay implements Display {
    private final int floorNumber;
    private List<ElevatorCar> elevators;

    public FloorDisplay(int floorNumber, List<ElevatorCar> elevators) {
        this.floorNumber = floorNumber;
        this.elevators = elevators;
    }

    public void setElevators(List<ElevatorCar> elevators) {
        this.elevators = elevators;
    }

    @Override
    public void update() {
        show();
    }

    @Override
    public void show() {
        System.out.println("=== Floor " + floorNumber + " Elevator Status Board ===");
        for (ElevatorCar elevator : elevators) {
            System.out.println("  Elevator " + elevator.getElevatorId()
                    + " -> Floor: " + elevator.getCurrentFloor()
                    + " | Direction: " + elevator.getDirection()
                    + " | State: " + elevator.getState());
        }
    }
}
