package dispatch;

import core.ElevatorCar;
import core.Request;
import enums.Direction;
import enums.ElevatorState;

import java.util.List;

public class SmartDispatchStrategy implements DispatchStrategy {

    @Override
    public ElevatorCar assignElevator(Request request, List<ElevatorCar> elevators) {
        ElevatorCar bestElevator = null;
        int bestScore = Integer.MAX_VALUE;

        for (ElevatorCar elevator : elevators) {
            if (elevator.isFull()) continue;
            if (elevator.getState() == ElevatorState.MAINTENANCE) continue;

            int score = calculateScore(elevator, request);
            if (score < bestScore) {
                bestScore = score;
                bestElevator = elevator;
            }
        }

        return bestElevator;
    }

    private int calculateScore(ElevatorCar elevator, Request request) {
        int distance = Math.abs(elevator.getCurrentFloor() - request.getFloor());

        if (elevator.getDirection() == Direction.IDLE) {
            return distance;
        }

        boolean sameDirection = elevator.getDirection() == request.getDirection();
        boolean enRoute = isElevatorEnRoute(elevator, request.getFloor());

        if (sameDirection && enRoute) {
            return distance;
        }

        if (sameDirection && !enRoute) {
            return distance + 10;
        }

        return distance + 20;
    }

    private boolean isElevatorEnRoute(ElevatorCar elevator, int requestFloor) {
        if (elevator.getDirection() == Direction.UP) {
            return requestFloor >= elevator.getCurrentFloor();
        }
        if (elevator.getDirection() == Direction.DOWN) {
            return requestFloor <= elevator.getCurrentFloor();
        }
        return false;
    }
}
