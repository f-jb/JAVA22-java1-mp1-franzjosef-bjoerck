import java.io.IOException;
import java.util.Arrays;

public class HangMan {
    public static void main() {
        boolean menuLoop = true;
        while (menuLoop) {
            System.out.println("""
                    1. Play
                    9. Quit""");
            int menuChoice = UserInput.getInt();
            switch (menuChoice) {
                case 1 -> menuLoop = false;
                case 9 -> {
                    System.out.println("Bye!");
                    return;
                }
                default -> System.out.println("Please choose a valid number.");
            }
        }
        boolean play = true;
        while (play) {
            String wordlistFile = "wordlist.txt";
            char[] answer = new char[0];
            try {
                answer = FileHandler.reservoirSampling(wordlistFile).toCharArray();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            char[] guess = new char[answer.length];
            Arrays.fill(guess, '_');
            int lives = 6;
            boolean won = false;
            boolean lost = false;
            do {
                printGubbe(lives);
                System.out.println("The word is:");
                System.out.println(String.valueOf(guess));
                System.out.println("The amount of lives is " + lives);
                char guessedLetter = UserInput.getChar();
                if (!containsLetter(guessedLetter, answer)) {
                    System.out.println("It didn't contain the letter");
                    lives--;
                } else {
                    guess = checkGuessedLetter(guessedLetter, guess, answer);
                }

                won = checkString(guess, answer);
                if (lives == 0) {
                    lost = true;
                    printGubbe(lives);
                }
            } while (!won && !lost);
            if(won){
                System.out.println("Congratulations for the win!");
            } else {
                System.out.println("The word was " + String.valueOf(answer));
                System.out.println("Sorry, you have lost.");
            }

            play = UserInput.continuePlay();
        }
        System.out.println("Bye!");
    }

    static boolean containsLetter(char guessedLetter, char[] answer) {
        for (char c : answer) {
            if (guessedLetter == c) {
                return true;
            }
        }
        return false;
    }

    static char[] checkGuessedLetter(char guessedLetter, char[] guess, char[] answer) {
        for (char c : answer) {
            if (c == guessedLetter) {
                for (int i = 0; i < answer.length; i++) {
                    if (guessedLetter == answer[i]) {
                        guess[i] = guessedLetter;
                    }
                }
            }
        }

        return guess;
    }

    private static boolean checkString(char[] guess, char[] answer) {
        return Arrays.equals(guess, answer);
    }

    private static void printGubbe(int lives) {
        switch (lives) {
            case 6 -> System.out.println("""
                      ______
                      |    |
                      |
                      |
                      |
                      |
                    __|__
                    """);
            case 5 -> System.out.println("""
                      ______
                      |    |
                      |    o
                      |
                      |
                      |
                    __|__
                    """);
            case 4 -> System.out.println("""
                      ______
                      |    |
                      |    o
                      |    |
                      |
                      |
                    __|__
                    """);
            case 3 -> System.out.println("""
                      ______
                      |    |
                      |    o
                      |   /|
                      |
                      |
                    __|__
                    """);
            case 2 -> System.out.println("""
                      ______
                      |    |
                      |    o
                      |   /|\\
                      |
                      |
                    __|__
                    """);
            case 1 -> System.out.println("""
                      ______
                      |    |
                      |    o
                      |   /|\\
                      |   /
                      |
                    __|__
                    """);
            case 0 -> System.out.println("""
                      ______
                      |    |
                      |    o
                      |   /|\\
                      |   / \\
                      |
                    __|__
                    """);
        }
    }
}

