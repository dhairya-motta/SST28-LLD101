package com.pen;

public class GelPen extends Pen implements Refillable {
    public GelPen(String brand) {
        super(brand, PenType.GEL_PEN);
    }

    @Override
    public void refill() {
        inkLevel = 100;
        state = PenState.NEW;
        System.out.println(brand + " gel pen refilled to full capacity.");
    }
}
