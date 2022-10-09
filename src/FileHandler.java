import java.io.*;

public class FileHandler {
    public static boolean doesFileExist(String highScoreFile) {
        return new File(highScoreFile).exists();
    }

    public static void writeFile(HighScore.Entry[] list, String highScoreFile) {
        try (FileOutputStream fos = new FileOutputStream(highScoreFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(list);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static HighScore.Entry[] readFile(String highScoreFile) {
        try (FileInputStream fis = new FileInputStream(highScoreFile);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (HighScore.Entry[]) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return new HighScore.Entry[0];
    }

}
