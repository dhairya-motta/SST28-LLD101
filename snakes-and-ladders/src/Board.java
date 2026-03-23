import java.util.*;

public class Board {
    private int size;
    private List<Snake> snakes;
    private List<Ladder> ladders;
    private Map<Integer, Snake> snakeMap;
    private Map<Integer, Ladder> ladderMap;

    public Board(int n) {
        this.size = n;
        this.snakes = new ArrayList<>();
        this.ladders = new ArrayList<>();
        this.snakeMap = new HashMap<>();
        this.ladderMap = new HashMap<>();
        generateSnakesAndLadders();
    }

    private void generateSnakesAndLadders() {
        Random random = new Random();
        Set<Integer> occupied = new HashSet<>();
        occupied.add(1); // start
        occupied.add(size * size); // end

        // Generate snakes
        for (int i = 0; i < size; i++) {
            int head, tail;
            do {
                head = random.nextInt(size * size - 2) + 2; // 2 to n^2-1
                tail = random.nextInt(head - 1) + 1; // 1 to head-1
            } while (occupied.contains(head) || occupied.contains(tail));
            occupied.add(head);
            occupied.add(tail);
            Snake snake = new Snake(head, tail);
            snakes.add(snake);
            snakeMap.put(head, snake);
        }

        // Generate ladders
        for (int i = 0; i < size; i++) {
            int start, end;
            do {
                start = random.nextInt(size * size - 2) + 2; // 2 to n^2-1
                end = random.nextInt(size * size - start) + start + 1; // start+1 to n^2
            } while (occupied.contains(start) || occupied.contains(end));
            occupied.add(start);
            occupied.add(end);
            Ladder ladder = new Ladder(start, end);
            ladders.add(ladder);
            ladderMap.put(start, ladder);
        }
    }

    public int getSize() {
        return size;
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

    public List<Ladder> getLadders() {
        return ladders;
    }

    public int checkPosition(int position) {
        if (snakeMap.containsKey(position)) {
            return snakeMap.get(position).getTail();
        } else if (ladderMap.containsKey(position)) {
            return ladderMap.get(position).getEnd();
        }
        return position;
    }
}