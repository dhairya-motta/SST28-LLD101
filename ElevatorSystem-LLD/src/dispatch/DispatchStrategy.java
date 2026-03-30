package dispatch;

import core.ElevatorCar;
import core.Request;
import java.util.List;

public interface DispatchStrategy {
    ElevatorCar assignElevator(Request request, List<ElevatorCar> elevators);
}
