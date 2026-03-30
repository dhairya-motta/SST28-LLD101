package core;

import display.ExternalDisplay;
import display.InternalDisplay;
import enums.Direction;
import enums.ElevatorState;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class ElevatorCar {
    private final int elevatorId;
    private int currentFloor;
    private Direction direction;
    private ElevatorState state;
    private final Door door;
    private final List<Passenger> passengers;
    private final TreeSet<Integer> upQueue;
    private final TreeSet<Integer> downQueue;
    private final InternalDisplay internalDisplay;
    private final ExternalDisplay externalDisplay;
    private InsidePanel insidePanel;

    private static final int MAX_PASSENGER_COUNT = 8;
    private static final double MAX_WEIGHT_KG = 680.0;
    private double currentWeight;

    public ElevatorCar(int elevatorId) {
        this.elevatorId = elevatorId;
        this.currentFloor = 1;
        this.direction = Direction.IDLE;
        this.state = ElevatorState.IDLE;
        this.door = new Door(elevatorId);
        this.passengers = new ArrayList<>();
        this.upQueue = new TreeSet<>();
        this.downQueue = new TreeSet<>();
        this.internalDisplay = new InternalDisplay(elevatorId);
        this.externalDisplay = new ExternalDisplay(elevatorId);
        this.currentWeight = 0.0;
    }

    public void setInsidePanel(InsidePanel insidePanel) {
        this.insidePanel = insidePanel;
    }

    public void addDestinationFloor(int floor) {
        if (floor == currentFloor) return;
        if (floor > currentFloor) {
            upQueue.add(floor);
        } else {
            downQueue.add(floor);
        }
        updateDirection();
    }

    private void updateDirection() {
        if (!upQueue.isEmpty() && direction != Direction.DOWN) {
            direction = Direction.UP;
        } else if (!downQueue.isEmpty()) {
            direction = Direction.DOWN;
        } else if (!upQueue.isEmpty()) {
            direction = Direction.UP;
        } else {
            direction = Direction.IDLE;
        }
        refreshDisplays();
    }

    public void moveStep() {
        if (state == ElevatorState.DOOR_OPEN) {
            System.out.println("Elevator " + elevatorId + ": Waiting — door is open.");
            return;
        }
        if (upQueue.isEmpty() && downQueue.isEmpty()) {
            state = ElevatorState.IDLE;
            direction = Direction.IDLE;
            refreshDisplays();
            System.out.println("Elevator " + elevatorId + ": Idle at floor " + currentFloor);
            return;
        }

        state = ElevatorState.MOVING;

        if (direction == Direction.UP && !upQueue.isEmpty()) {
            currentFloor++;
            if (upQueue.contains(currentFloor)) {
                upQueue.remove(currentFloor);
                arriveAtFloor();
            }
        } else if (direction == Direction.DOWN && !downQueue.isEmpty()) {
            currentFloor--;
            if (downQueue.contains(currentFloor)) {
                downQueue.remove(currentFloor);
                arriveAtFloor();
            }
        } else {
            updateDirection();
        }

        refreshDisplays();
    }

    private void arriveAtFloor() {
        state = ElevatorState.IDLE;
        System.out.println("Elevator " + elevatorId + ": Arrived at floor " + currentFloor);
        openDoor();
        unloadPassengers();
        refreshDisplays();
        closeDoor();
        updateDirection();
    }

    public void openDoor() {
        door.open(state);
        if (door.isOpen()) {
            state = ElevatorState.DOOR_OPEN;
        }
        refreshDisplays();
    }

    public void closeDoor() {
        door.close();
        state = ElevatorState.IDLE;
        refreshDisplays();
    }

    public boolean boardPassenger(Passenger passenger) {
        if (passengers.size() >= MAX_PASSENGER_COUNT) {
            System.out.println("Elevator " + elevatorId + ": At maximum passenger capacity.");
            return false;
        }
        if (currentWeight + passenger.getWeight() > MAX_WEIGHT_KG) {
            System.out.println("Elevator " + elevatorId + ": Exceeds weight limit of " + MAX_WEIGHT_KG + " kg.");
            return false;
        }
        passengers.add(passenger);
        currentWeight += passenger.getWeight();
        addDestinationFloor(passenger.getDestinationFloor());
        System.out.println("Elevator " + elevatorId + ": " + passenger + " boarded.");
        refreshDisplays();
        return true;
    }

    private void unloadPassengers() {
        List<Passenger> toRemove = new ArrayList<>();
        for (Passenger p : passengers) {
            if (p.getDestinationFloor() == currentFloor) {
                toRemove.add(p);
                System.out.println("Elevator " + elevatorId + ": " + p + " exited at floor " + currentFloor);
            }
        }
        for (Passenger p : toRemove) {
            passengers.remove(p);
            currentWeight -= p.getWeight();
        }
        refreshDisplays();
    }

    private void refreshDisplays() {
        internalDisplay.setCurrentFloor(currentFloor);
        internalDisplay.setDirection(direction);
        internalDisplay.setCurrentPassengerCount(passengers.size());
        externalDisplay.setCurrentFloor(currentFloor);
        externalDisplay.setDirection(direction);
    }

    public void showInternalDisplay() {
        internalDisplay.show();
    }

    public void showExternalDisplay() {
        externalDisplay.show();
    }

    public int getElevatorId() {
        return elevatorId;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public ElevatorState getState() {
        return state;
    }

    public Door getDoor() {
        return door;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public int getPassengerCount() {
        return passengers.size();
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public TreeSet<Integer> getUpQueue() {
        return upQueue;
    }

    public TreeSet<Integer> getDownQueue() {
        return downQueue;
    }

    public InternalDisplay getInternalDisplay() {
        return internalDisplay;
    }

    public ExternalDisplay getExternalDisplay() {
        return externalDisplay;
    }

    public InsidePanel getInsidePanel() {
        return insidePanel;
    }

    public boolean isFull() {
        return passengers.size() >= MAX_PASSENGER_COUNT;
    }
}
