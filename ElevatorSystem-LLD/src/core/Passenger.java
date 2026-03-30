package core;

public class Passenger {
    private static int idCounter = 1;
    private final int passengerId;
    private final double weight;
    private final int sourceFloor;
    private int destinationFloor;

    public Passenger(double weight, int sourceFloor, int destinationFloor) {
        this.passengerId = idCounter++;
        this.weight = weight;
        this.sourceFloor = sourceFloor;
        this.destinationFloor = destinationFloor;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public double getWeight() {
        return weight;
    }

    public int getSourceFloor() {
        return sourceFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    @Override
    public String toString() {
        return "Passenger{id=" + passengerId + ", weight=" + weight + "kg, from=" + sourceFloor + ", to=" + destinationFloor + "}";
    }
}
