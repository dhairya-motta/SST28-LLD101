package com.parkinglot;

public enum SlotType {
    SMALL(50),      // For 2-wheelers, hourly rate: 50
    MEDIUM(100),    // For cars, hourly rate: 100
    LARGE(200);     // For buses, hourly rate: 200

    private int hourlyRate;

    SlotType(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }
}
