public class Main {
    public static void main(String[] args) {
        boolean mainLoop = true;
        while (mainLoop) {
            boolean menuLoop = true;
            System.out.println("""
                                Hello, welcome to fjbs game corner!
                                Please choose a game.
                                1. Guess the number
                                2. Nim
                                9. Quit
                                """);
            while (menuLoop) {
                int menuChoice = UserInput.getInt();
                switch (menuChoice) {
                    case 1 -> {
                        menuLoop = false;
                        GuessNumber.main();
                    }
                    case 2 -> {
                        menuLoop = false;
                        Nim.main();
                    }
                    case 9 -> {
                        menuLoop = false;
                        mainLoop = false;
                        System.out.println("Bye!");
                    }
                    default -> System.out.println("Please choose a valid number.");
                }

            }
        }
    }
}