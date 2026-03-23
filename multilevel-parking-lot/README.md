# Multilevel Parking Lot System - LLD Assignment

## Problem Statement

Design a multilevel parking lot system that:
- Supports three types of parking slots: Small (2-wheelers), Medium (cars), Large (buses)
- Manages multiple entry gates
- Assigns nearest available compatible slots based on entry gate
- Handles parking ticket generation on entry
- Calculates billing on exit based on allocated slot type, not vehicle type
- Allows smaller vehicles to park in larger slots when needed

## Functional Requirements

### Slot Types
- **Small Slot** (₹50/hour) - for 2-wheelers
- **Medium Slot** (₹100/hour) - for cars
- **Large Slot** (₹200/hour) - for buses

### Vehicle Compatibility
- 2-wheeler can park in: SMALL, MEDIUM, or LARGE slot
- 4-wheeler (car) can park in: MEDIUM or LARGE slot
- Bus can park only in: LARGE slot

### Key Operations
- **park()** - Assign slot, generate ticket
- **exit()** - Calculate duration, generate bill
- **status()** - Show current availability

## Assumptions

1. Each level has the same number and type of slots
2. Parking duration calculated in hours (minimum 1 hour charge)
3. Billing is based on allocated slot type, not vehicle type
4. System finds the first available compatible slot across all levels
5. Multiple entry/exit gates available
6. Vehicles cannot be double-parked

## Design Explanation

### Key Classes

#### 1. **ParkingLot** (Main Coordinator)
- Manages all levels and gates
- Coordinates parking entry/exit
- Generates tickets and bills
- Provides system status

#### 2. **Level** (Floor)
- Represents one level/floor of parking lot
- Contains slots of different types
- Finds available compatible slots for vehicles
- Tracks slot availability per type

#### 3. **ParkingSlot** (Individual Slot)
- Represents a physical slot
- Stores: slot number, type, level, vehicle parked, entry time
- Can be occupied or available
- Tracks vehicle and entry time

#### 4. **Vehicle** (Vehicle Details)
- Stores: license plate, vehicle type, owner name
- Three types: TWO_WHEELER, FOUR_WHEELER, BUS

#### 5. **ParkingTicket** (Entry Document)
- Generated when vehicle enters
- Contains: vehicle details, slot number, slot type, entry time
- Unique ticket ID for tracking
- Used to identify parked vehicle

#### 6. **Bill** (Exit Document)
- Generated when vehicle exits
- Calculates duration based on entry and exit times
- Computes amount based on slot type's hourly rate
- Unique bill ID

#### 7. **Gate** (Entry/Exit Points)
- Multiple gates for system flexibility
- Gate ID and location tracking

#### 8. **Enums**
- **VehicleType**: TWO_WHEELER, FOUR_WHEELER, BUS
- **SlotType**: SMALL, MEDIUM, LARGE (with hourly rates)

## Class Diagram

```
┌────────────────────────────────────────────────────────────────┐
│                    ParkingLot                                  │
├────────────────────────────────────────────────────────────────┤
│ - levels: List<Level>                                          │
│ - gates: List<Gate>                                            │
│ - activeTickets: Map<String, ParkingTicket>                   │
│ - ticketCounter: int                                           │
│ - billCounter: int                                             │
├────────────────────────────────────────────────────────────────┤
│ + park(vehicle, entryTime, gateID): ParkingTicket             │
│ + exit(ticket, exitTime): Bill                                 │
│ + status(): void                                               │
│ + getTotalAvailableSlots(): int                               │
└────────────────────────────────────────────────────────────────┘
          │                    │                       │
          │ 1..n               │ 1..n                 │ 1..n
          ▼                    ▼                       ▼
    ┌──────────────┐    ┌──────────────┐    ┌──────────────┐
    │    Level     │    │     Gate     │    │   ParkingTicket │
    ├──────────────┤    ├──────────────┤    ├──────────────┐
    │ - levelNum   │    │ - gateId     │    │ - ticketId   │
    │ - slots      │    │ - location   │    │ - vehicle    │
    ├──────────────┤    └──────────────┘    │ - level      │
    │ + findAvail()│                        │ - slot       │
    │ + getStatus()│                        │ - slotType   │
    └──────────────┘                        │ - entryTime  │
          │                                 └──────────────┘
          │ 1..n
          ▼
    ┌──────────────┐    ┌──────────────┐
    │ ParkingSlot  │    │    Vehicle   │
    ├──────────────┤    ├──────────────┤
    │ - slotNum    │    │ - licensePlate│
    │ - type       │    │ - type       │ ◄─ VehicleType*
    │ - level      │    │ - ownerName  │    (TWO_WHEELER,
    │ - available  │    └──────────────┘     FOUR_WHEELER,
    │ - vehicle    │                         BUS)
    │ - startTime  │
    ├──────────────┤
    │ + park()     │
    │ + unpark()   │
    └──────────────┘

    ┌──────────────────┐    ┌──────────────────┐
    │    Bill          │    │    SlotType*     │
    ├──────────────────┤    ├──────────────────┤
    │ - billId         │    │ SMALL (50/hr)    │
    │ - ticket         │    │ MEDIUM (100/hr)  │
    │ - exitTime       │    │ LARGE (200/hr)   │
    │ - duration       │    └──────────────────┘
    │ - totalAmount    │
    ├──────────────────┤
    │ + getTotalAmount()│
    │ + getDuration()   │
    └──────────────────┘

    * Enums
```

## Design Approach

### Slot Assignment Algorithm
1. Vehicle enters through a gate
2. System checks vehicle type to determine compatible slot types
3. Searches all levels sequentially for first available compatible slot
4. Prefers smaller slots over larger ones (optimal space utilization)
5. If no slot available, parking denied

### Billing Calculation
- **Formula**: Duration (hours) × Hourly Rate of Allocated Slot Type
- **Duration**: Calculated from entry and exit times
- **Minimum Charge**: 1 hour (even if duration < 1 hour)
- **Rate**: Based on slot type where vehicle is parked (not vehicle type)

### Key Features
- Simple state management (available/occupied)
- Efficient slot finding for common cases
- Clean separation of concerns
- Extensible design for new features

## Project Structure

```
multilevel-parking-lot/
├── README.md
└── src/
    └── com/
        └── parkinglot/
            ├── VehicleType.java
            ├── SlotType.java
            ├── Vehicle.java
            ├── ParkingSlot.java
            ├── ParkingTicket.java
            ├── Bill.java
            ├── Gate.java
            ├── Level.java
            ├── ParkingLot.java
            └── Main.java
```

## How to Run

### Compile
```bash
cd multilevel-parking-lot/src
javac com/parkinglot/*.java
```

### Run
```bash
java com.parkinglot.Main
```

### Expected Output
Shows:
- Parking lot initialization
- Vehicle entry with ticket generation
- Parking lot status (availability by type)
- Vehicle exit with bill calculation
- Final parking lot status

## SOLID Principles Applied

1. **Single Responsibility**: Each class has one reason to change
2. **Open/Closed**: Easy to add new vehicle/slot types
3. **Liskov Substitution**: Level manages different slot types uniformly
4. **Interface Segregation**: Clean, focused methods
5. **Dependency Inversion**: ParkingLot depends on abstractions (Level, Slot)

## Viva Questions & Answers

**Q: How do you handle conflicts when multiple vehicles arrive simultaneously?**
A: The system finds the first available slot in level order. In a real system, we'd use a queue and timestamp-based ordering.

**Q: What happens if a vehicle tries to park when no slots are available?**
A: The park() method returns null after checking all levels, and an error message is displayed.

**Q: Why is billing based on slot type, not vehicle type?**
A: This incentivizes people to use appropriate slots and makes the system fair - if you occupy a premium slot, you pay premium rates.

**Q: Can a bike park in a large slot?**
A: Yes, but it pays the large slot rate (₹200/hour). This encourages choosing the right slot size.

**Q: How do you calculate the parking duration?**
A: Simple: (exitTime - entryTime) / 3600000 milliseconds per hour. Minimum 1 hour charge.
