# Elevator System — Low-Level Design (LLD)

## Overview

A fully object-oriented Low-Level Design for an Elevator System servicing **15 floors** with **3 elevator cars**, built in Java. This implementation applies core OOP principles and industry-standard design patterns.

---

## System Requirements Covered

| Requirement | Implemented |
|---|---|
| 3 Elevator Cars | ✅ |
| 15 Floors | ✅ |
| Move UP / DOWN / IDLE | ✅ |
| Door opens only when idle | ✅ |
| Each elevator stops at every floor | ✅ |
| Outside panel (UP / DOWN buttons per floor) | ✅ |
| Inside panel (floor buttons + door open/close) | ✅ |
| External display (floor + direction) | ✅ |
| Internal display (floor + direction + capacity) | ✅ |
| Floor panel with elevator status displays | ✅ |
| Multiple passengers, different floors/directions | ✅ |
| Smart dispatch (location + trajectory based) | ✅ |
| Max capacity: 8 people / 680 kg | ✅ |

---

## Design Patterns Used

| Pattern | Applied To |
|---|---|
| **Singleton** | `ElevatorController` — single system-wide controller |
| **Strategy** | `DispatchStrategy` / `SmartDispatchStrategy` — pluggable dispatch algorithm |
| **Template Method** | `Button` abstract class — pressed() hook |
| **Facade** | `ElevatorSystem` — simplified interface for the controller |
| **Observer** | `Display` interface — `InternalDisplay`, `ExternalDisplay`, `FloorDisplay` |

---

## Class Structure

```
src/
├── Main.java                          # Entry point with 10 simulation scenarios
├── enums/
│   ├── Direction.java                 # UP, DOWN, IDLE
│   ├── ElevatorState.java             # MOVING, IDLE, DOOR_OPEN, MAINTENANCE
│   ├── DoorState.java                 # OPEN, CLOSED
│   └── RequestType.java               # EXTERNAL, INTERNAL
├── buttons/
│   ├── Button.java                    # Abstract base button
│   ├── FloorButton.java               # Outside floor call button
│   ├── ElevatorButton.java            # Inside elevator floor button
│   └── DoorButton.java                # Door open/close button
├── display/
│   ├── Display.java                   # Display interface
│   ├── ExternalDisplay.java           # Outside elevator display
│   ├── InternalDisplay.java           # Inside elevator display
│   └── FloorDisplay.java              # Floor panel elevator status board
├── core/
│   ├── Door.java                      # Door with open/close safety enforcement
│   ├── Passenger.java                 # Passenger entity (weight, floors)
│   ├── Request.java                   # Elevator request (floor, direction, type)
│   ├── InsidePanel.java               # Inside control panel
│   ├── OutsidePanel.java              # Outside call panel per floor
│   ├── ElevatorCar.java               # Main elevator class (SCAN algorithm)
│   └── Floor.java                     # Floor with panel and display
├── dispatch/
│   ├── DispatchStrategy.java          # Strategy interface
│   └── SmartDispatchStrategy.java     # Scoring-based smart dispatch
├── controller/
│   └── ElevatorController.java        # Singleton system controller
└── system/
    └── ElevatorSystem.java            # Facade entry point
```

---

## Dispatch Algorithm

The `SmartDispatchStrategy` scores each elevator using:

- **Distance** from request floor (lower = better)
- **Direction alignment**: Same direction en-route → lowest penalty
- **Direction alignment but not en-route** → medium penalty (+10)
- **Opposite direction** → highest penalty (+20)
- **Idle elevators** scored by pure distance

This mimics real-world elevator dispatch systems.

---

## Movement Algorithm

`ElevatorCar` uses a **SCAN (Elevator) Algorithm**:
- Maintains two sorted queues: `upQueue` (TreeSet) and `downQueue` (TreeSet)
- Services all floors in the current direction first before reversing
- Automatically unloads passengers and updates direction on arrival

---

## How to Compile and Run

```bash
cd ElevatorSystem-LLD/src

# Compile all Java files
javac -d ../out enums/*.java buttons/*.java display/*.java core/*.java dispatch/*.java controller/*.java system/*.java Main.java

# Run
java -cp ../out Main
```

---

## Key Constraints Enforced

- **Door Safety**: `Door.open()` only succeeds when `ElevatorState` is `IDLE` or `DOOR_OPEN`
- **Weight Limit**: Boarding rejected if total weight exceeds 680 kg
- **Passenger Limit**: Max 8 passengers per elevator
- **Pending Queue**: Unserviceable requests are queued and retried each system step
