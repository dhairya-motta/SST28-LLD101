import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter board size n (nxn): ");
        int n = scanner.nextInt();

        System.out.print("Enter number of players x: ");
        int x = scanner.nextInt();

        System.out.print("Enter difficulty level (easy/hard): ");
        String difficulty = scanner.next();

        Game game = new Game(n, x, difficulty);
        game.start();

        scanner.close();
    }
}