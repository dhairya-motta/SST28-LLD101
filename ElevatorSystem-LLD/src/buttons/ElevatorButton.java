package buttons;

public class ElevatorButton extends Button {
    private final int targetFloor;
    private final int elevatorId;

    public ElevatorButton(int targetFloor, int elevatorId) {
        super();
        this.targetFloor = targetFloor;
        this.elevatorId = elevatorId;
    }

    @Override
    public void pressed() {
        setPressed(true);
        System.out.println("Elevator " + elevatorId + ": Inside button pressed for floor " + targetFloor);
    }

    public int getTargetFloor() {
        return targetFloor;
    }

    public int getElevatorId() {
        return elevatorId;
    }
}
