package com.parkinglot;

public class Vehicle {
    private String licensePlate;
    private VehicleType type;
    private String ownerName;

    public Vehicle(String licensePlate, VehicleType type, String ownerName) {
        this.licensePlate = licensePlate;
        this.type = type;
        this.ownerName = ownerName;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public VehicleType getType() {
        return type;
    }

    public String getOwnerName() {
        return ownerName;
    }

    @Override
    public String toString() {
        return ownerName + " (" + licensePlate + ") - " + type;
    }
}
