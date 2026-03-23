package com.pen;

public class FountainPen extends Pen implements Refillable {
    public FountainPen(String brand) {
        super(brand, PenType.FOUNTAIN_PEN);
    }

    @Override
    public void refill() {
        inkLevel = 100;
        state = PenState.NEW;
        System.out.println(brand + " fountain pen refilled to full capacity.");
    }
}
