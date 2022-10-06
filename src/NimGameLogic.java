public class NimGameLogic {
    static Nim.GameMove main(Nim.Board board) {
        int[] board2 = board.board.clone();
        int[][] choice = new int[4][2];
        int nimsum;
        int amtToDecrease;

        nimsum = getNimsum(board2);
        markValidRows(board2, nimsum, choice);
        amtToDecrease = calcAmountToRemove(nimsum);
        finalCheck(board2, choice, amtToDecrease);

        for (int i = 0; i < choice.length; i++) {
            System.out.println(choice[i][0] + " " + choice[i][1]);
        }

        return new Nim.GameMove(1, 3);
    }

    static int getNimsum(int[] board) {// gets the nimsum of the board
        int nimsum = 0;
        for (int i = 0; i < board.length; i++) {
            nimsum = nimsum ^ board[i];
        }
        return nimsum;
    }

    static void markValidRows(int[] board, int nimsum, int[][] choice) {// Marks which rows are uneven in nimsum
        for (int i = 0; i < board.length; i++) {
            if ((nimsum & board[i]) != 0) {
                choice[i][0] = 1;
            }
        }
    }

    // Calculates if it is a four or more and the amount to remove.
    static int calcAmountToRemove(int nimsum) {
        int amtToRemove = 0;
        System.out.println("nimsum is: " + nimsum + " which in binary is " + Integer.toBinaryString(nimsum) + " with the length of: " + Integer.toBinaryString(nimsum).length());
        if (Integer.toBinaryString(nimsum).length() == 3) {
            if (nimsum == 4) {
                amtToRemove = 4;
            } else {
                amtToRemove = nimsum - 4;
            }
        } else {
            amtToRemove = nimsum;
        }
        System.out.println("the amt to remove is: " + amtToRemove);
        return amtToRemove;
    }

    static void finalCheck(int[] board, int[][] choice, int nimsum) {
        // check which row can be removed from
        int amtToRemove = 0;
        int[] board2 = board.clone();
        for (int i = 0; i < choice.length; i++) {
            if (board2[i] - amtToRemove >= 0) {
                board2[i] -= amtToRemove;
                if (choice[i][1] + amtToRemove <= nimsum) {
                    choice[i][1] += amtToRemove;
                }
            }
        }
    }
}