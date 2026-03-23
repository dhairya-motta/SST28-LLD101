# Design a Pen - LLD Assignment

## Problem Statement
Design a system to model a Pen with the following functionalities:
- `start()` - Open the pen for use
- `write()` - Write text with the pen
- `close()` - Close the pen
- `refill()` - Refill the pen's ink

## Assumptions
1. A pen has a brand, type (Ball, Gel, Fountain), and state (New, Open, Closed, Empty).
2. Each pen has an ink level (0-100%) that decreases when writing.
3. Not all pens may be refillable, so we use an interface `Refillable`.
4. A pen can only write when it's in the OPEN state and has ink.
5. When ink runs out, the pen automatically transitions to EMPTY state.
6. Refilling resets the pen to NEW state and restores full ink level.

## Design Explanation

### Class Diagram

```
                        ┌─────────┐
                        │Refillable│
                        └────┬────┘
                             │
                    (implements)
                    ┌─────────┴───────────┬──────────────┐
                    │                     │              │
              ┌─────────┐           ┌──────────┐   ┌──────────┐
              │ BallPen │           │  GelPen  │   │FountainPen│
              └────┬────┘           └────┬─────┘   └────┬─────┘
                   │                     │              │
                   └─────────┬───────────┴──────────────┘
                        (extends)
                             │
                        ┌────▼────┐
                        │   Pen   │ (abstract)
                        ├─────────┤
                        │-brand   │
                        │-type    │
                        │-state   │
                        │-inkLevel│
                        ├─────────┤
                        │+start()  │
                        │+write()  │
                        │+close()  │
                        └─────────┘
                             △
                             │
                    ┌────────┴───────────┐
                    │ PenType (Enum)    │
                    ├───────────────────┤
                    │ BALL_PEN          │
                    │ GEL_PEN           │
                    │ FOUNTAIN_PEN      │
                    └───────────────────┘

                    ┌────────────────────┐
                    │ PenState (Enum)   │
                    ├────────────────────┤
                    │ NEW                │
                    │ OPEN               │
                    │ CLOSED             │
                    │ EMPTY              │
                    └────────────────────┘
```

### Design Explanation

1. **Pen (Abstract Class)**
   - Base class for all pen types
   - Manages brand, type, state, and ink level
   - Implements core methods: `start()`, `write()`, `close()`

2. **PenType (Enum)**
   - Defines different types of pens
   - Types: BALL_PEN, GEL_PEN, FOUNTAIN_PEN

3. **PenState (Enum)**
   - Represents the current state of a pen
   - States: NEW, OPEN, CLOSED, EMPTY

4. **Refillable (Interface)**
   - Contract for pens that can be refilled
   - Implemented by BallPen, GelPen, FountainPen

5. **BallPen, GelPen, FountainPen (Concrete Classes)**
   - Extend Pen and implement Refillable
   - Each has its own `refill()` implementation

## Project Structure

```
design-a-pen/
├── src/
│   └── com/
│       └── pen/
│           ├── PenType.java
│           ├── PenState.java
│           ├── Refillable.java
│           ├── Pen.java
│           ├── BallPen.java
│           ├── GelPen.java
│           ├── FountainPen.java
│           └── Main.java
└── README.md
```

## How to Run

### Compile
```bash
cd design-a-pen/src
javac com/pen/*.java
```

### Run
```bash
java com.pen.Main
```

### Expected Output
```
========== Ball Pen Demo ==========
Parker pen opened.
Writing: Hello World
Ink level: 90%
Writing: This is a ball pen
Ink level: 80%
...
```

## SOLID Principles Used

1. **Single Responsibility Principle**: Each class has a single responsibility.
2. **Open/Closed Principle**: New pen types can be added without modifying existing classes.
3. **Interface Segregation**: Refillable interface is small and focused.

## Key Features

- ✅ Simple and clean design
- ✅ Easy to extend with new pen types
- ✅ Natural flow of pen operations
- ✅ Interview-friendly explanation
- ✅ No over-engineering
