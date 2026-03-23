package com.pen;

public class Main {
    public static void main(String[] args) {
        // Create different types of pens
        Pen ballPen = new BallPen("Parker");
        Pen gelPen = new GelPen("ReD");
        Pen fountainPen = new FountainPen("Montblanc");

        System.out.println("========== Ball Pen Demo ==========");
        ballPen.start();
        ballPen.write("Hello World");
        ballPen.write("This is a ball pen");
        ballPen.write("Almost empty");
        ballPen.write("Cannot write anymore");
        ballPen.close();

        // Refill and use again
        System.out.println("\n========== Refilling Ball Pen ==========");
        ((Refillable) ballPen).refill();
        ballPen.start();
        ballPen.write("Writing after refill");
        ballPen.close();

        System.out.println("\n========== Gel Pen Demo ==========");
        gelPen.start();
        gelPen.write("This is a gel pen");
        gelPen.close();

        System.out.println("\n========== Fountain Pen Demo ==========");
        fountainPen.start();
        fountainPen.write("Premium fountain pen");
        fountainPen.close();

        // Refill fountain pen
        System.out.println("\n========== Refilling Fountain Pen ==========");
        ((Refillable) fountainPen).refill();
        fountainPen.start();
        fountainPen.write("Writing with refilled fountain pen");
        fountainPen.close();
    }
}
