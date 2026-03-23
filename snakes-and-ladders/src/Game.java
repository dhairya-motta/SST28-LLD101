import java.util.*;

public class Game {
    private Board board;
    private List<Player> players;
    private Dice dice;
    private int currentPlayerIndex;
    private boolean gameOver;
    private Player winner;

    public Game(int n, int x, String difficulty) {
        this.board = new Board(n);
        this.players = new ArrayList<>();
        for (int i = 1; i <= x; i++) {
            players.add(new Player("Player " + i));
        }
        this.dice = new Dice();
        this.currentPlayerIndex = 0;
        this.gameOver = false;
        this.winner = null;
    }

    public void start() {
        System.out.println("Starting Snakes and Ladders game!");
        System.out.println("Board size: " + board.getSize() + "x" + board.getSize());
        System.out.println("Number of players: " + players.size());
        System.out.println("Snakes:");
        for (Snake snake : board.getSnakes()) {
            System.out.println("Head: " + snake.getHead() + " -> Tail: " + snake.getTail());
        }
        System.out.println("Ladders:");
        for (Ladder ladder : board.getLadders()) {
            System.out.println("Start: " + ladder.getStart() + " -> End: " + ladder.getEnd());
        }
        System.out.println();

        while (!gameOver && players.size() >= 2) {
            playTurn();
        }

        if (winner != null) {
            System.out.println(winner.getName() + " wins!");
        } else {
            System.out.println("Game ended without a winner.");
        }
    }

    private void playTurn() {
        Player currentPlayer = players.get(currentPlayerIndex);
        System.out.println(currentPlayer.getName() + "'s turn. Current position: " + currentPlayer.getPosition());

        int roll = dice.roll();
        System.out.println("Rolled: " + roll);

        currentPlayer.move(roll, board.getSize());
        int newPosition = currentPlayer.getPosition();
        System.out.println("Moved to: " + newPosition);

        // Check for snake or ladder
        int finalPosition = board.checkPosition(newPosition);
        if (finalPosition != newPosition) {
            if (finalPosition < newPosition) {
                System.out.println("Oops! Snake! Down to: " + finalPosition);
            } else {
                System.out.println("Yay! Ladder! Up to: " + finalPosition);
            }
            currentPlayer.setPosition(finalPosition);
        }

        // Check win
        if (currentPlayer.getPosition() == board.getSize() * board.getSize()) {
            winner = currentPlayer;
            gameOver = true;
            return;
        }

        // Next player
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }
}