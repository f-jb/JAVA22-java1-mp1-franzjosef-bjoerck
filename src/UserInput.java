import java.util.Scanner;

public class UserInput {
    static int getInt() {
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNextInt()) {
            System.out.println("Please, only numbers.");
            sc.nextLine();
        }
        return sc.nextInt();
    }
    static String getString(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();

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
