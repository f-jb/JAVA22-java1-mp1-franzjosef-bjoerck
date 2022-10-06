import java.util.Random;

public class GuessNumber {
    public static void main() {
        boolean play = true;
        while (play) {
            int totalGuesses = 0;
            int number = generateNumber();
            System.out.println("I am thinking of a number between 0 - 100. Guess!");
            boolean won;
            do {
                won = checkNumber(UserInput.getInt(), number);
                totalGuesses++;
            } while (!won);
            System.out.println("It took you " + totalGuesses + (totalGuesses == 1 ? " guess." : " guesses."));
            play = UserInput.continuePlay();
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
}