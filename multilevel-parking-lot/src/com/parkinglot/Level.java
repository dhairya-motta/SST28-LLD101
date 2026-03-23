package com.parkinglot;

import java.util.*;

public class Level {
    private int levelNumber;
    private Map<SlotType, List<ParkingSlot>> slotsByType;

    public Level(int levelNumber, int smallSlots, int mediumSlots, int largeSlots) {
        this.levelNumber = levelNumber;
        this.slotsByType = new HashMap<>();
        initializeSlots(smallSlots, mediumSlots, largeSlots);
    }

    private void initializeSlots(int smallSlots, int mediumSlots, int largeSlots) {
        slotsByType.put(SlotType.SMALL, new ArrayList<>());
        slotsByType.put(SlotType.MEDIUM, new ArrayList<>());
        slotsByType.put(SlotType.LARGE, new ArrayList<>());

        int slotCounter = 1;

        // Create small slots
        for (int i = 0; i < smallSlots; i++) {
            slotsByType.get(SlotType.SMALL).add(
                new ParkingSlot(slotCounter++, SlotType.SMALL, levelNumber)
            );
        }

        // Create medium slots
        for (int i = 0; i < mediumSlots; i++) {
            slotsByType.get(SlotType.MEDIUM).add(
                new ParkingSlot(slotCounter++, SlotType.MEDIUM, levelNumber)
            );
        }

        // Create large slots
        for (int i = 0; i < largeSlots; i++) {
            slotsByType.get(SlotType.LARGE).add(
                new ParkingSlot(slotCounter++, SlotType.LARGE, levelNumber)
            );
        }
    }

    public ParkingSlot findAvailableSlot(VehicleType vehicleType) {
        // Determine compatible slot types based on vehicle type
        List<SlotType> compatibleSlots = new ArrayList<>();
        
        if (vehicleType == VehicleType.TWO_WHEELER) {
            compatibleSlots.add(SlotType.SMALL);
            compatibleSlots.add(SlotType.MEDIUM);
            compatibleSlots.add(SlotType.LARGE);
        } else if (vehicleType == VehicleType.FOUR_WHEELER) {
            compatibleSlots.add(SlotType.MEDIUM);
            compatibleSlots.add(SlotType.LARGE);
        } else if (vehicleType == VehicleType.BUS) {
            compatibleSlots.add(SlotType.LARGE);
        }

        // Find first available slot
        for (SlotType slotType : compatibleSlots) {
            for (ParkingSlot slot : slotsByType.get(slotType)) {
                if (slot.isAvailable()) {
                    return slot;
                }
            }
        }

        return null; // No available slot
    }

    public int getAvailableSlotCount(SlotType type) {
        int count = 0;
        for (ParkingSlot slot : slotsByType.get(type)) {
            if (slot.isAvailable()) {
                count++;
            }
        }
        return count;
    }

    public int getTotalSlotCount(SlotType type) {
        return slotsByType.get(type).size();
    }

    public int getLevelNumber() {
        return levelNumber;
    }
}
