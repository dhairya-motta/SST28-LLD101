package controller;

import core.ElevatorCar;
import core.Floor;
import core.InsidePanel;
import core.Passenger;
import core.Request;
import dispatch.DispatchStrategy;
import dispatch.SmartDispatchStrategy;
import enums.Direction;
import enums.RequestType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ElevatorController {
    private static ElevatorController instance;
    private final List<ElevatorCar> elevators;
    private final List<Floor> floors;
    private final DispatchStrategy dispatchStrategy;
    private final Queue<Request> pendingRequests;

    private static final int MAX_ELEVATORS = 3;
    private static final int TOTAL_FLOORS = 15;

    private ElevatorController() {
        this.elevators = new ArrayList<>();
        this.floors = new ArrayList<>();
        this.dispatchStrategy = new SmartDispatchStrategy();
        this.pendingRequests = new LinkedList<>();
        initializeElevators();
        initializeFloors();
    }

    public static ElevatorController getInstance() {
        if (instance == null) {
            synchronized (ElevatorController.class) {
                if (instance == null) {
                    instance = new ElevatorController();
                }
            }
        }
        return instance;
    }

    private void initializeElevators() {
        for (int i = 1; i <= MAX_ELEVATORS; i++) {
            ElevatorCar elevator = new ElevatorCar(i);
            InsidePanel insidePanel = new InsidePanel(elevator);
            elevator.setInsidePanel(insidePanel);
            elevators.add(elevator);
        }
        System.out.println("Initialized " + MAX_ELEVATORS + " elevators.");
    }

    private void initializeFloors() {
        for (int i = 1; i <= TOTAL_FLOORS; i++) {
            floors.add(new Floor(i, elevators));
        }
        System.out.println("Initialized " + TOTAL_FLOORS + " floors.");
    }

    public void handleExternalRequest(int floorNumber, Direction direction) {
        if (floorNumber < 1 || floorNumber > TOTAL_FLOORS) {
            System.out.println("Invalid floor number: " + floorNumber);
            return;
        }

        Floor floor = floors.get(floorNumber - 1);
        if (direction == Direction.UP) {
            floor.getOutsidePanel().pressUp();
        } else {
            floor.getOutsidePanel().pressDown();
        }

        Request request = new Request(floorNumber, direction, RequestType.EXTERNAL);
        System.out.println("External request received: " + request);

        ElevatorCar assigned = dispatchStrategy.assignElevator(request, elevators);
        if (assigned != null) {
            System.out.println("Dispatching Elevator " + assigned.getElevatorId() + " to floor " + floorNumber);
            assigned.addDestinationFloor(floorNumber);
        } else {
            System.out.println("No available elevator. Request queued: " + request);
            pendingRequests.add(request);
        }
    }

    public void handleInternalRequest(int elevatorId, int destinationFloor) {
        if (elevatorId < 1 || elevatorId > MAX_ELEVATORS) {
            System.out.println("Invalid elevator ID: " + elevatorId);
            return;
        }
        if (destinationFloor < 1 || destinationFloor > TOTAL_FLOORS) {
            System.out.println("Invalid floor number: " + destinationFloor);
            return;
        }

        ElevatorCar elevator = elevators.get(elevatorId - 1);
        elevator.getInsidePanel().pressFloorButton(destinationFloor);
        System.out.println("Internal request: Elevator " + elevatorId + " -> Floor " + destinationFloor);
    }

    public void boardPassenger(int elevatorId, Passenger passenger) {
        if (elevatorId < 1 || elevatorId > MAX_ELEVATORS) {
            System.out.println("Invalid elevator ID: " + elevatorId);
            return;
        }
        ElevatorCar elevator = elevators.get(elevatorId - 1);
        elevator.boardPassenger(passenger);
    }

    public void step() {
        for (ElevatorCar elevator : elevators) {
            elevator.moveStep();
        }
        processPendingRequests();
    }

    private void processPendingRequests() {
        Queue<Request> stillPending = new LinkedList<>();
        while (!pendingRequests.isEmpty()) {
            Request request = pendingRequests.poll();
            ElevatorCar assigned = dispatchStrategy.assignElevator(request, elevators);
            if (assigned != null) {
                System.out.println("Retrying: Dispatching Elevator " + assigned.getElevatorId() + " to floor " + request.getFloor());
                assigned.addDestinationFloor(request.getFloor());
            } else {
                stillPending.add(request);
            }
        }
        pendingRequests.addAll(stillPending);
    }

    public void showAllDisplays() {
        System.out.println("\n========== SYSTEM STATUS ==========");
        for (ElevatorCar elevator : elevators) {
            elevator.showExternalDisplay();
            elevator.showInternalDisplay();
        }
        System.out.println("====================================\n");
    }

    public void showFloorStatus(int floorNumber) {
        if (floorNumber < 1 || floorNumber > TOTAL_FLOORS) {
            System.out.println("Invalid floor number: " + floorNumber);
            return;
        }
        floors.get(floorNumber - 1).showFloorDisplay();
    }

    public List<ElevatorCar> getElevators() {
        return elevators;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public int getTotalFloors() {
        return TOTAL_FLOORS;
    }

    public int getMaxElevators() {
        return MAX_ELEVATORS;
    }
}
