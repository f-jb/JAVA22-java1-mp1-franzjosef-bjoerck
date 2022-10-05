import java.util.Arrays;
import java.util.Scanner;

public class Nim {
    public static void main(String[] args) {
        boolean playerOne = true;
        boolean play = true;
        int[] board = {1, 3, 5, 7};
        while (play) {
            System.out.println("Player " + (playerOne ? "1" : "2") + " turn.");
            for (int i = 0; i < board.length; i++) {
                System.out.println(i + 1 + " (" + board[i] + "): " + "#".repeat(board[i]));
            }
            int row;
            int amountToDecrease;
            boolean legalMove = false;
            while (!legalMove) {
                System.out.println("Choose a row.");
                row = getNumber() - 1;
                System.out.println("How much to remove?");
                amountToDecrease = getNumber();
                legalMove = checkMove(board, row, amountToDecrease);
                if (legalMove) {
                    decrease(board, row, amountToDecrease);
                    if (checkWin(board)) {
                        System.out.println("Congratulations to player " + (playerOne ? "1" : "2") + "!");
                        play = continuePlay();

                    } else {
                        playerOne = !playerOne;
                    }
                }
            }

        }
    }

    static void decrease(int[] board, int row, int amountToDecrease) {
        board[row] -= amountToDecrease;
    }

    static int getNumber() {
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNextInt()) {
            System.out.println("Please, only numbers.");
            sc.nextLine();
        }
        return sc.nextInt();
    }

    static boolean checkWin(int[] board) {
        int[] arr = board.clone();
        Arrays.sort(arr);
        return arr[arr.length - 1] == 0;
    }

    static boolean checkMove(int[] board, int row, int amountToDecrease) {
        if (row + 1 > board.length) {
            System.out.println("Illegal move: Please choose a legal row.");
            return false;
        } else if (amountToDecrease > board[row]) {
            System.out.println("Illegal move: Can't go into negative.");
            return false;
        } else {
            return true;
        }
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
