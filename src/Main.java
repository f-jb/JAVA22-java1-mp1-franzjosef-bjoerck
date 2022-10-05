import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean play = true;
        while (play) {
            int totalGuesses = 0;
            int number = generateNumber();
            System.out.println("I am thinking of a number between 0 - 100. Guess!");
            boolean won;
            do {
                won = checkNumber(getNumber(), number);
                totalGuesses++;
            } while (!won);
            System.out.println("It took you " + totalGuesses + (totalGuesses == 1 ? " guess." : " guesses."));
            play = continuePlay();
        }
        System.out.println("Bye!");
    }

    static int generateNumber() {
        return new Random().nextInt(101);
    }

    static boolean checkNumber(int guess, int number) {
        if (guess == number) {
            return true;
        } else if (guess > number) {
            System.out.println("Too high!");
        } else {
            System.out.println("Too low!");
        }
        return false;

    }

    static int getNumber() {
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNextInt()) {
            System.out.println("Please, only numbers.");
            sc.nextLine();
        }
        return sc.nextInt();
    }

    static boolean continuePlay() {
        Scanner sc = new Scanner(System.in);
        String s;
        do {
            System.out.println("Continue? y/n");
            s = sc.nextLine();
            if (s.equalsIgnoreCase("y")) {
                return true;
            } else if (s.equalsIgnoreCase("n")) {
                return false;
            }
        } while (!s.equals("y") && !s.equals("n"));
        return true;
    }
}