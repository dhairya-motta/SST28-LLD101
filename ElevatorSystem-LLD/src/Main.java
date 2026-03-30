import core.Passenger;
import enums.Direction;
import system.ElevatorSystem;

public class Main {

    public static void main(String[] args) {

        ElevatorSystem elevatorSystem = new ElevatorSystem();

        System.out.println("\n========== SCENARIO 1: Multiple Passengers, Multiple Directions ==========");

        elevatorSystem.callElevator(1, Direction.UP);
        elevatorSystem.callElevator(5, Direction.UP);
        elevatorSystem.callElevator(10, Direction.DOWN);

        elevatorSystem.showStatus();

        System.out.println("\n========== SCENARIO 2: Passengers Boarding Different Elevators ==========");

        Passenger p1 = new Passenger(70.0, 1, 8);
        Passenger p2 = new Passenger(65.0, 1, 12);
        Passenger p3 = new Passenger(80.0, 5, 2);

        elevatorSystem.boardPassenger(1, p1);
        elevatorSystem.boardPassenger(1, p2);
        elevatorSystem.boardPassenger(2, p3);

        elevatorSystem.showStatus();

        System.out.println("\n========== SCENARIO 3: Inside Panel Floor Selections ==========");

        elevatorSystem.selectFloor(1, 8);
        elevatorSystem.selectFloor(1, 12);
        elevatorSystem.selectFloor(2, 2);
        elevatorSystem.selectFloor(3, 15);

        elevatorSystem.showStatus();

        System.out.println("\n========== SCENARIO 4: Smart Dispatch — Calling from Floor 7 UP ==========");

        elevatorSystem.callElevator(7, Direction.UP);

        elevatorSystem.showStatus();

        System.out.println("\n========== SCENARIO 5: Simulating 5 Movement Steps ==========");

        elevatorSystem.runSteps(5);

        elevatorSystem.showStatus();

        System.out.println("\n========== SCENARIO 6: Floor Status Display at Floor 1 ==========");

        elevatorSystem.showFloorStatus(1);

        System.out.println("\n========== SCENARIO 7: Capacity Check — Overloading Elevator 1 ==========");

        for (int i = 0; i < 10; i++) {
            Passenger extra = new Passenger(70.0, 1, 3 + i);
            elevatorSystem.boardPassenger(1, extra);
        }

        System.out.println("\n========== SCENARIO 8: Multiple Concurrent Calls From Various Floors ==========");

        elevatorSystem.callElevator(3, Direction.UP);
        elevatorSystem.callElevator(13, Direction.DOWN);
        elevatorSystem.callElevator(6, Direction.DOWN);
        elevatorSystem.callElevator(9, Direction.UP);

        elevatorSystem.showStatus();

        System.out.println("\n========== SCENARIO 9: Full Simulation — 10 Steps ==========");

        elevatorSystem.runSteps(10);

        elevatorSystem.showStatus();

        System.out.println("\n========== SCENARIO 10: Floor Status All Elevators at Floor 5 ==========");

        elevatorSystem.showFloorStatus(5);
    }
}
