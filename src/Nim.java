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
            int[] arr = board.clone();
            Arrays.sort(arr);
            return arr[arr.length - 1] == 0;
        }
    }

    static class GameMove{
        int row;
        int amountToDecrease;
        GameMove getMove(){
            System.out.println("Choose a row.");
            this.row = UserInput.getInt() - 1;
            System.out.println("How much to remove?");
            this.amountToDecrease = UserInput.getInt();
            return this;
        }
    }


    static void main() {
        System.out.println("Rules: Take turns choosing a row and the amount to remove. The players that removes the last item wins.");
        Board board = new Board();
        boolean playerOne = true;
        boolean play = true;
        while (play) {
            System.out.println("Player " + (playerOne ? "1" : "2") + " turn.");
            board.printBoard();
            boolean legalMove = false;
            while (!legalMove) {
                GameMove playerMove = new GameMove().getMove();
                legalMove = board.checkMove(playerMove);
                if (legalMove) {
                    board.decrease(playerMove);
                    if (board.checkWin()) {
                        System.out.println("Congratulations to player " + (playerOne ? "1" : "2") + "! You win!");
                        play = UserInput.continuePlay();
                        if (play){
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
