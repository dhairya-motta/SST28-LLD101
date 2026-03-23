# Snakes and Ladders Game

## Class Diagram

```
+----------------+     +-----------------+
|     Board      |     |     Snake       |
+----------------+     +-----------------+
| - size: int    |     | - head: int     |
| - snakes: List |     | - tail: int     |
| - ladders: List|     +-----------------+
| - snakeMap: Map|     | + getHead()     |
| - ladderMap: Map|     | + getTail()     |
+----------------+     +-----------------+
| + Board(n)     |           ^
| + getSize()    |           |
| + getSnakes()  |           |
| + getLadders() |           |
| + checkPosition|           |
+----------------+           |
          |                  |
          | 1                |
          v                  |
+----------------+     +-----------------+
|     Game       |     |     Ladder      |
+----------------+     +-----------------+
| - board: Board |     | - start: int    |
| - players: List|     | - end: int      |
| - dice: Dice   |     +-----------------+
| - currentIndex |     | + getStart()    |
| - gameOver     |     | + getEnd()      |
| - winner       |     +-----------------+
+----------------+           ^
| + Game(n,x,diff)|           |
| + start()       |           |
+----------------+           |
          |                  |
          | *                |
          v                  |
+----------------+     +-----------------+
|    Player      |     |      Dice       |
+----------------+     +-----------------+
| - name: String |     | - random: Random|
| - position: int|     +-----------------+
+----------------+     | + roll(): int   |
| + Player(name)  |     +-----------------+
| + getName()     |
| + getPosition() |
| + setPosition() |
| + move()        |
+----------------+
```

## Description

This is a Java implementation of the Snakes and Ladders game.

### Features

- Configurable board size (n x n)
- Variable number of players
- Random placement of snakes and ladders
- Turn-based gameplay
- Dice rolling simulation

### How to Run

1. Compile: `javac *.java`
2. Run: `java Main`
3. Enter n, x, difficulty when prompted

### Rules

- Board from 1 to n²
- Players start at 0
- Roll dice (1-6) to move
- Land on snake head -> go to tail
- Land on ladder start -> go to end
- First to reach n² wins
- Cannot move beyond n²