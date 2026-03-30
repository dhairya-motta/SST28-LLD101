package system;

import controller.ElevatorController;
import core.Passenger;
import enums.Direction;

public class ElevatorSystem {
    private final ElevatorController controller;

    public ElevatorSystem() {
        this.controller = ElevatorController.getInstance();
    }

    public void callElevator(int floor, Direction direction) {
        controller.handleExternalRequest(floor, direction);
    }

    public void selectFloor(int elevatorId, int floor) {
        controller.handleInternalRequest(elevatorId, floor);
    }

    public void boardPassenger(int elevatorId, Passenger passenger) {
        controller.boardPassenger(elevatorId, passenger);
    }

    public void runStep() {
        controller.step();
    }

    public void runSteps(int count) {
        for (int i = 0; i < count; i++) {
            System.out.println("\n--- Step " + (i + 1) + " ---");
            controller.step();
        }
    }

    public void showStatus() {
        controller.showAllDisplays();
    }

    public void showFloorStatus(int floor) {
        controller.showFloorStatus(floor);
    }
}
