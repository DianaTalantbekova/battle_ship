import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        Game game = new Game(board);
        
        System.out.println("=== МОРСКОЙ БОЙ ===");
        
        boolean exit = false;
        
        while (!exit) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Новая игра");
            System.out.println("2. Результаты");
            System.out.println("3. Выход");
            System.out.print("Ваш выбор: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    game.play();
                    break;
                case 2:
                    game.displayResults();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Некорректный выбор. Попробуйте ещё раз.");
                    break;
            }
        }
        
        scanner.close();
    }
}
