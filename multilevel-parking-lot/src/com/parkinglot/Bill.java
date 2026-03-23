package com.parkinglot;

public class Bill {
    private String billId;
    private ParkingTicket ticket;
    private long exitTime;
    private long parkingDuration; // in hours
    private int totalAmount;

    public Bill(String billId, ParkingTicket ticket, long exitTime) {
        this.billId = billId;
        this.ticket = ticket;
        this.exitTime = exitTime;
        this.parkingDuration = (exitTime - ticket.getEntryTime()) / 3600000; // Convert ms to hours
        if (parkingDuration == 0) {
            parkingDuration = 1; // Minimum 1 hour charge
        }
        this.totalAmount = (int) (parkingDuration * ticket.getAllocatedSlotType().getHourlyRate());
    }

    public String getBillId() {
        return billId;
    }

    public ParkingTicket getTicket() {
        return ticket;
    }

    public long getParkingDuration() {
        return parkingDuration;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "Bill " + billId + "\n" +
               "Vehicle: " + ticket.getVehicle().getLicensePlate() + "\n" +
               "Slot Type: " + ticket.getAllocatedSlotType() + "\n" +
               "Duration: " + parkingDuration + " hour(s)\n" +
               "Rate: " + ticket.getAllocatedSlotType().getHourlyRate() + " per hour\n" +
               "Total Amount: Rs. " + totalAmount;
    }
}
