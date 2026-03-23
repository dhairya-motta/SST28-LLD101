package com.parkinglot;

import java.util.*;

public class ParkingLot {
    private List<Level> levels;
    private List<Gate> gates;
    private Map<String, ParkingTicket> activeTickets; // ticketId -> ticket
    private int ticketCounter;
    private int billCounter;

    public ParkingLot(int numLevels, int smallSlots, int mediumSlots, int largeSlots) {
        this.levels = new ArrayList<>();
        this.gates = new ArrayList<>();
        this.activeTickets = new HashMap<>();
        this.ticketCounter = 1000;
        this.billCounter = 5000;

        // Initialize levels
        for (int i = 1; i <= numLevels; i++) {
            levels.add(new Level(i, smallSlots, mediumSlots, largeSlots));
        }

        // Initialize gates
        gates.add(new Gate(1, "Gate-North"));
        gates.add(new Gate(2, "Gate-South"));
        gates.add(new Gate(3, "Gate-East"));
    }

    public ParkingTicket park(Vehicle vehicle, long entryTime, int entryGateID) {
        // Find nearest available compatible slot
        ParkingSlot availableSlot = null;
        
        for (Level level : levels) {
            availableSlot = level.findAvailableSlot(vehicle.getType());
            if (availableSlot != null) {
                break;
            }
        }

        if (availableSlot == null) {
            System.out.println("No available slot for " + vehicle.getType());
            return null;
        }

        // Park the vehicle
        availableSlot.parkVehicle(vehicle, entryTime);

        // Generate ticket
        String ticketId = "TICKET-" + (ticketCounter++);
        ParkingTicket ticket = new ParkingTicket(
            ticketId,
            vehicle,
            availableSlot.getLevel(),
            availableSlot.getSlotNumber(),
            availableSlot.getSlotType(),
            entryTime
        );

        activeTickets.put(ticketId, ticket);
        System.out.println("Vehicle parked successfully!");
        System.out.println(ticket);
        return ticket;
    }

    public Bill exit(ParkingTicket ticket, long exitTime) {
        if (!activeTickets.containsKey(ticket.getTicketId())) {
            System.out.println("Invalid ticket!");
            return null;
        }

        // Find and unpark the vehicle
        for (Level level : levels) {
            // Search through all slots to find the parked vehicle
            // This is a simplified approach - in real scenario we'd maintain a map
        }

        // Generate bill
        String billId = "BILL-" + (billCounter++);
        Bill bill = new Bill(billId, ticket, exitTime);

        activeTickets.remove(ticket.getTicketId());
        System.out.println("\nVehicle exited successfully!");
        System.out.println(bill);
        return bill;
    }

    public void status() {
        System.out.println("\n========== PARKING LOT STATUS ==========");
        for (Level level : levels) {
            System.out.println("\nLevel " + level.getLevelNumber() + ":");
            for (SlotType type : SlotType.values()) {
                int available = level.getAvailableSlotCount(type);
                int total = level.getTotalSlotCount(type);
                System.out.println("  " + type + ": " + available + "/" + total + " available");
            }
        }
        System.out.println("========================================\n");
    }

    public int getTotalAvailableSlots() {
        int total = 0;
        for (Level level : levels) {
            total += level.getAvailableSlotCount(SlotType.SMALL);
            total += level.getAvailableSlotCount(SlotType.MEDIUM);
            total += level.getAvailableSlotCount(SlotType.LARGE);
        }
        return total;
    }
}
