import java.util.Random;

public class GuessNumber {
    public static void main() {
        HighScore.Entry[] highScore;
        String highScoreFile = "NimHighScore.dat";
        if (FileHandler.doesFileExist(highScoreFile)) {
            highScore = FileHandler.readFile(highScoreFile);
        } else {
            highScore = new HighScore.Entry[10];
            for (int i = 0; i < highScore.length; i++) {
                highScore[i] = new HighScore.Entry();
            }
        }
        boolean menuLoop = true;
        while (menuLoop) {
            System.out.println("""
                    1. Play
                    2. Highscores
                    9. Quit""");
            int menuChoice = UserInput.getInt();
            switch (menuChoice) {
                case 1 -> menuLoop = false;
                case 2 -> HighScore.displayAll(highScore);
                case 9 -> {
                    System.out.println("Bye!");
                    return;
                }
                default -> System.out.println("Please choose a valid number.");
            }
        }
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
            if (HighScore.placedOnBoard(highScore, totalGuesses)) {
                highScore = HighScore.addEntry(highScore, new HighScore.Entry(UserInput.getName(), totalGuesses));
                FileHandler.writeFile(highScore, highScoreFile);
                HighScore.displayAll(highScore);
            }
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