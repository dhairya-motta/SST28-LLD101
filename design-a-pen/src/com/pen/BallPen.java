package com.pen;

public class BallPen extends Pen implements Refillable {
    public BallPen(String brand) {
        super(brand, PenType.BALL_PEN);
    }

    @Override
    public void refill() {
        inkLevel = 100;
        state = PenState.NEW;
        System.out.println(brand + " ball pen refilled to full capacity.");
    }
}
