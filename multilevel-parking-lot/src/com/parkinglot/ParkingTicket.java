package com.parkinglot;

public class ParkingTicket {
    private String ticketId;
    private Vehicle vehicle;
    private int levelNumber;
    private int slotNumber;
    private SlotType allocatedSlotType;
    private long entryTime;

    public ParkingTicket(String ticketId, Vehicle vehicle, int levelNumber,
                        int slotNumber, SlotType slotType, long entryTime) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.levelNumber = levelNumber;
        this.slotNumber = slotNumber;
        this.allocatedSlotType = slotType;
        this.entryTime = entryTime;
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public SlotType getAllocatedSlotType() {
        return allocatedSlotType;
    }

    public long getEntryTime() {
        return entryTime;
    }

    @Override
    public String toString() {
        return "Ticket " + ticketId + "\n" +
               "Vehicle: " + vehicle + "\n" +
               "Level: " + levelNumber + ", Slot: " + slotNumber + "\n" +
               "Slot Type: " + allocatedSlotType + "\n" +
               "Entry Time: " + entryTime;
    }
}
