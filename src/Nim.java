import java.util.Arrays;

public class Nim {
    static class Board {
        public int[] board = {1, 3, 5, 7};

        void decrease(GameMove gameMove) {
            board[gameMove.row] -= gameMove.amountToDecrease;
        }

        void reset() {
            board = new int[]{1, 3, 5, 7};
        }

        void printBoard() {
            for (int i = 0; i < board.length; i++) {
                System.out.println(i + 1 + " (" + board[i] + "): " + "#".repeat(board[i]));
            }
        }

        boolean checkMove(GameMove gameMove) {
            if (gameMove.row + 1 > board.length) {
                System.out.println("Illegal move: Please choose a legal row.");
                return false;
            } else if (gameMove.amountToDecrease > board[gameMove.row]) {
                System.out.println("Illegal move: Can't go into negative.");
                return false;
            } else {
                return true;
            }
        }

        boolean checkWin() {
            int[] tempBoard = board.clone();
            Arrays.sort(tempBoard);
            return tempBoard[tempBoard.length - 1] == 0;
        }
    }

    static class GameMove {
        int row;
        int amountToDecrease;

        GameMove(int row, int amountToDecrease) {
            this.row = row;
            this.amountToDecrease = amountToDecrease;
        }

        GameMove getMove() {
            System.out.println("Choose a row:");
            this.row = UserInput.getInt() - 1;
            System.out.println("Choose how much to remove:");
            this.amountToDecrease = UserInput.getInt();
            return this;
        }
    }


    static void main() {
        int difficulty = 3;
        boolean playAgainstComputer = false;

        System.out.println("""
                1. Play
                2. Settings
                9. Quit
                """);

        boolean menuLoop = true;
        while (menuLoop) {
            int menuChoice = UserInput.getInt();
            switch (menuChoice) {
                case 1 -> menuLoop = false;
                case 2 -> {
                    menuLoop = false;
                    boolean settingsLoop = true;
                    while (settingsLoop) {
                        System.out.println("""
                                1. Play against""" + (playAgainstComputer ? " human?" : " computer?") + "\n" +
                                """
                                2. Difficulty
                                9. Back""");
                        int settingsChoice = UserInput.getInt();
                        switch (settingsChoice) {
                            case 1 -> {
                                playAgainstComputer = !playAgainstComputer;
                            }
                            case 2 -> {
                                boolean difficultyLoop = true;
                                while (difficultyLoop) {
                                    System.out.println("""
                                            Select difficulty:
                                            1. Super easy
                                            2. Easy
                                            3. Normal
                                            4. Hard
                                            5. Impossible
                                            9. Back
                                            """);
                                    int difficultyChoice = UserInput.getInt();
                                    if (difficultyChoice >= 1 && difficultyChoice <= 5) {
                                        difficulty = difficultyChoice - 1;
                                    } else if (difficultyChoice == 9) {
                                        difficultyLoop = false;

                                    } else {
                                        System.out.println("Please choose a valid number.");
                                    }
                                }
                            }
                            case 9 -> settingsLoop = false;
                            default -> System.out.println("Please choose a valid number.");
                        }
                    }
                }
                case 9 -> {
                    menuLoop = false;
                    System.out.println("Bye!");
                }
                default -> System.out.println("Please choose a valid number.");
            }
        }

        System.out.println("""
                Rules:
                1. Take turns choosing a row and the amount to remove.
                2. The players that removes the last item wins.
                3. The winning player starts the next round.
                """);
        Board board = new Board();
        boolean playerOne = true;
        boolean play = true;
        while (play) {
            System.out.println("Player " + (playerOne ? "1" : "2") + " turn.");
            board.printBoard();
            boolean legalMove = false;
            while (!legalMove) {
                GameMove playerMove = new GameMove(0, 0).getMove();
                legalMove = board.checkMove(playerMove);
                if (legalMove) {
                    board.decrease(playerMove);
                    if (board.checkWin()) {
                        System.out.println("Congratulations to player " + (playerOne ? "1" : "2") + "! You win!");
                        play = UserInput.continuePlay();
                        if (play) {
                            board.reset();
                        }

                    } else {
                        playerOne = !playerOne;
                    }
                }
            }

        }
        System.out.println("Bye!");
    }
}
