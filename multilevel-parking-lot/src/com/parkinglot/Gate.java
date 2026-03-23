package com.parkinglot;

public class Gate {
    private int gateId;
    private String location; // Entry/Exit gate location

    public Gate(int gateId, String location) {
        this.gateId = gateId;
        this.location = location;
    }

    public int getGateId() {
        return gateId;
    }

    public String getLocation() {
        return location;
    }
}
