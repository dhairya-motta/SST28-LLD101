package com.pen;

public abstract class Pen {
    protected String brand;
    protected PenType type;
    protected PenState state;
    protected int inkLevel;

    public Pen(String brand, PenType type) {
        this.brand = brand;
        this.type = type;
        this.state = PenState.NEW;
        this.inkLevel = 100; // 0-100
    }

    public void start() {
        if (state == PenState.NEW || state == PenState.CLOSED) {
            state = PenState.OPEN;
            System.out.println(brand + " pen opened.");
        }
    }

    public void write(String text) {
        if (state != PenState.OPEN) {
            System.out.println("Cannot write. Pen is " + state);
            return;
        }

        if (inkLevel <= 0) {
            state = PenState.EMPTY;
            System.out.println("Pen is empty. Cannot write.");
            return;
        }

        System.out.println("Writing: " + text);
        inkLevel -= 10; // Each write uses 10 units of ink
        System.out.println("Ink level: " + inkLevel + "%");
    }

    public void close() {
        if (state == PenState.OPEN) {
            state = PenState.CLOSED;
            System.out.println(brand + " pen closed.");
        }
    }

    public String getBrand() {
        return brand;
    }

    public PenType getType() {
        return type;
    }

    public PenState getState() {
        return state;
    }

    public int getInkLevel() {
        return inkLevel;
    }
}
