package buttons;

public class DoorButton extends Button {
    private final boolean isOpenButton;
    private final int elevatorId;

    public DoorButton(boolean isOpenButton, int elevatorId) {
        super();
        this.isOpenButton = isOpenButton;
        this.elevatorId = elevatorId;
    }

    @Override
    public void pressed() {
        setPressed(true);
        if (isOpenButton) {
            System.out.println("Elevator " + elevatorId + ": Door Open button pressed");
        } else {
            System.out.println("Elevator " + elevatorId + ": Door Close button pressed");
        }
    }

    public boolean isOpenButton() {
        return isOpenButton;
    }

    public int getElevatorId() {
        return elevatorId;
    }
}
