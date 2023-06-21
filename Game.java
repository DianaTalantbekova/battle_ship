import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Game {
    private Board board;
    private List<Ship> ships;
    private int remainingShips;
    private long startTime;
    private List<Long> results;

    public Game(Board board) {
        this.board = board;
        this.ships = new ArrayList<>();
        this.remainingShips = 0;
        this.startTime = 0;
        this.results = new ArrayList<>();
    }

    public void play() {
        board = new Board();
        ships.clear();
        remainingShips = 0;

        System.out.println("\n=== Новая игра ===");

        createShips();
        placeShips();
        remainingShips = ships.size();
        startTime = System.currentTimeMillis();

        Scanner scanner = new Scanner(System.in);

        boolean gameOver = false;

        while (!gameOver) {
            System.out.println("\nТекущее поле:");
            board.printBoard();
            System.out.print("Куда стреляем (например, A2): ");

            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Слишком долго! Вы проиграли.");
                return;
            }

            if (input.length() < 2 || input.length() > 3) {
                System.out.println("Некорректный ввод. Попробуйте ещё раз.");
                continue;
            }

            char colChar = Character.toUpperCase(input.charAt(0));
            int row;

            try {
                row = Integer.parseInt(input.substring(1));
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод. Попробуйте ещё раз.");
                continue;
            }

            int col = colChar - 'A';

            if (row < 1 || row > 8 || col < 0 || col >= Board.SIZE) {
                System.out.println("Некорректные координаты. Попробуйте ещё раз.");
                continue;
            }

            boolean hit = false;

            for (Ship ship : ships) {
                if (ship.checkHit(String.valueOf(colChar) + row)) {
                    hit = true;

                    if (ship.isSunk()) {
                        System.out.println("Корабль потоплен!");
                        board.markSunk(row - 1, col);
                        remainingShips--;
                    } else {
                        System.out.println("Попадание!");
                        board.markHit(row - 1, col);
                    }

                    break;
                }
            }

            if (!hit) {
                System.out.println("Промах!");
                board.markMiss(row - 1, col);
            }

            if (remainingShips == 0) {
                long endTime = System.currentTimeMillis();
                long duration = (endTime - startTime) / 1000;
                results.add(duration);

                System.out.println("\nПоздравляем! Вы победили!");
                System.out.println("Время: " + duration + " сек");
                gameOver = true;
            }
        }

        scanner.close();
    }

    public void displayResults() {
        System.out.println("\n=== Результаты ===");

        if (results.isEmpty()) {
            System.out.println("Нет доступных результатов.");
        } else {
            System.out.println("Топ 3 самых быстрых игр:");

            List<Long> sortedResults = new ArrayList<>(results);
            Collections.sort(sortedResults);

            int limit = Math.min(3, sortedResults.size());

            for (int i = 0; i < limit; i++) {
                System.out.println((i + 1) + ". " + sortedResults.get(i) + " сек");
            }
        }
    }

    private void createShips() {
        Ship ship1 = new Ship(1);
        Ship ship2 = new Ship(2);
        Ship ship3 = new Ship(2);

        ships.add(ship1);
        ships.add(ship2);
        ships.add(ship3);
    }

    private void placeShips() {
        List<String> ship1Positions = Arrays.asList("B2");
        List<String> ship2Positions = Arrays.asList("D4", "D5");
        List<String> ship3Positions = Arrays.asList("F7", "G7");

        ships.get(0).placeShip(ship1Positions);
        ships.get(1).placeShip(ship2Positions);
        ships.get(2).placeShip(ship3Positions);
    }
}