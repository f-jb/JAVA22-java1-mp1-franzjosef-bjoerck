import java.util.Random;

public class NimGameLogic {

    static Nim.GameMove ComputerPlayer(Nim.Board board, int difficulty) {
        int[] board2 = board.board.clone();
        int[][] choice = new int[4][2];
        int nimsum = 0;
        int amtToDecrease;

// find nimsum
        for (int j : board2) {
            nimsum = nimsum ^ j;
        }
        // check if nimsum == 0 or difficulty warrants a random move.
        if (nimsum ==0 || new Random().nextInt(101) > difficulty*20){
            int row = 0;
            boolean findingRow = true;
            while (findingRow){
                row = new Random().nextInt(board2.length);
                if(board2[row] >0) {
                    findingRow = false;
                }
            }
            int amountToDecrease = new Random().nextInt(board2[row])+1;

            return new Nim.GameMove(row,amountToDecrease);
        }

// find valid row
        for (int i = 0; i < board2.length; i++) {
            if ((nimsum & board2[i]) != 0) {
                choice[i][0] = 1;
            }
        }

// get the amount to decrease
        if (Integer.toBinaryString(nimsum).length() == 3) {
            if (nimsum == 4) {
                amtToDecrease = 4;
            } else {
                amtToDecrease = nimsum - 4;
            }
        } else {
            amtToDecrease = nimsum;
        }
// final check, compiles into an array
        for (int i = 0; i < choice.length; i++) {
            if (board2[i] - amtToDecrease >= 0) {
                board2[i] -= amtToDecrease;
                if (choice[i][1] + amtToDecrease <= nimsum) {
                    choice[i][1] += amtToDecrease;
                }
            }
        }
        // Chooses random result

        Nim.GameMove gameMove = new Nim.GameMove(0,0);
       boolean searchMove = true;
       int i;
        while (searchMove){
            i = new Random().nextInt(choice.length);
            gameMove = new Nim.GameMove(i,choice[i][1]);
            if(choice[i][0] != 0 && choice[i][1] !=0){
                searchMove = false;
            }
        }
        return gameMove;
    }

}