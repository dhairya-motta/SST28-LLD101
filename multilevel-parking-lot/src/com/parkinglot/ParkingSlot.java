package com.parkinglot;

public class ParkingSlot {
    private int slotNumber;
    private SlotType slotType;
    private int level;
    private boolean isAvailable;
    private Vehicle parkedVehicle;
    private long parkingStartTime;

    public ParkingSlot(int slotNumber, SlotType slotType, int level) {
        this.slotNumber = slotNumber;
        this.slotType = slotType;
        this.level = level;
        this.isAvailable = true;
        this.parkedVehicle = null;
        this.parkingStartTime = 0;
    }

    public void parkVehicle(Vehicle vehicle, long startTime) {
        this.parkedVehicle = vehicle;
        this.isAvailable = false;
        this.parkingStartTime = startTime;
    }

    public void unparkVehicle() {
        this.parkedVehicle = null;
        this.isAvailable = true;
        this.parkingStartTime = 0;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public SlotType getSlotType() {
        return slotType;
    }

    public int getLevel() {
        return level;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public long getParkingStartTime() {
        return parkingStartTime;
    }
}
