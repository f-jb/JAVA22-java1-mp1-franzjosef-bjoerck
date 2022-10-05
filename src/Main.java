import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean mainLoop = true;
        while (mainLoop) {
            boolean loop = true;
            System.out.println("Hello, welcome to fjbs game corner!");
            System.out.println("Please choose a game.");
            System.out.println("1. Guess the number");
            System.out.println("2. Nim");
            System.out.println("9. Quit");
            while (loop) {
                int choice = getNumber();
                switch (choice) {
                    case 1 -> {
                        loop=false;
                        GuessNumber.main();
                    }
                    case 2 -> {
                        loop=false;
                        Nim.main();
                    }
                    case 9 -> {
                        loop = false;
                        mainLoop= false;
                        System.out.println("Bye!");
                    }
                    default -> System.out.println("Please choose a valid number.");
                }

            }
        }

    }

    static int getNumber() {
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNextInt()) {
            System.out.println("Please, only numbers.");
            sc.nextLine();
        }
        return sc.nextInt();
    }
}