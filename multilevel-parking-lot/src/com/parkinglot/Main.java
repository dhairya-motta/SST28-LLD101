package com.parkinglot;

public class Main {
    public static void main(String[] args) {
        System.out.println("====== MULTILEVEL PARKING LOT SYSTEM ======\n");

        // Initialize parking lot: 3 levels, 5 small, 8 medium, 4 large slots each
        ParkingLot parkingLot = new ParkingLot(3, 5, 8, 4);

        // Show initial status
        parkingLot.status();

        // Test Case 1: Park a bike (2-wheeler)
        System.out.println("TEST CASE 1: Parking a Bike");
        Vehicle bike1 = new Vehicle("MH-02-AB-1234", VehicleType.TWO_WHEELER, "Raj");
        long time1 = System.currentTimeMillis();
        ParkingTicket ticket1 = parkingLot.park(bike1, time1, 1);

        // Test Case 2: Park a car (4-wheeler)
        System.out.println("\nTEST CASE 2: Parking a Car");
        Vehicle car1 = new Vehicle("MH-02-CD-5678", VehicleType.FOUR_WHEELER, "Priya");
        long time2 = System.currentTimeMillis();
        ParkingTicket ticket2 = parkingLot.park(car1, time2, 1);

        // Test Case 3: Park a bus
        System.out.println("\nTEST CASE 3: Parking a Bus");
        Vehicle bus1 = new Vehicle("MH-02-EF-9999", VehicleType.BUS, "Transport Co");
        long time3 = System.currentTimeMillis();
        ParkingTicket ticket3 = parkingLot.park(bus1, time3, 2);

        // Show status after parking
        parkingLot.status();

        // Test Case 4: Park another bike in a larger slot (upgrade scenario)
        System.out.println("TEST CASE 4: Parking Bike in Medium Slot (Upgrade)");
        Vehicle bike2 = new Vehicle("MH-02-GH-2222", VehicleType.TWO_WHEELER, "Anuj");
        long time4 = System.currentTimeMillis();
        ParkingTicket ticket4 = parkingLot.park(bike2, time4, 3);

        // Show status
        parkingLot.status();

        // Simulate exit after 2 hours
        System.out.println("\n========== VEHICLE EXITS ==========");
        System.out.println("\nTEST CASE 5: Bike exits after 2 hours");
        long exitTime1 = time1 + (2 * 3600000); // 2 hours later
        Bill bill1 = parkingLot.exit(ticket1, exitTime1);

        System.out.println("\nTEST CASE 6: Car exits after 3 hours");
        long exitTime2 = time2 + (3 * 3600000); // 3 hours later
        Bill bill2 = parkingLot.exit(ticket2, exitTime2);

        // Show final status
        parkingLot.status();

        System.out.println("====== SIMULATION COMPLETE ======");
    }
}
