import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    // God damn, motherfucking reservoir sampling. Jag tror att jag fattar det. Kill meh.
    // copied from https://kebomix.wordpress.com/2011/01/09/reservoir-sampling-java/
    // modified to work for this. Sometimes. The comments in the code are from original author.

    public static String reservoirSampling(String wordlistFile) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(wordlistFile))) {   // creating buffered reader object to read the file contains our data
            String currentLine;
            int reservoirSize = 10;
            List<String> reservoirList = new ArrayList<String>(reservoirSize);   //reservoirList is where our selected lines stored
            int count = 0;                           // we will use this counter to count the current line numebr while iterating
            Random ra = new Random();

            int randomNumber;
            while ((currentLine = br.readLine()) != null)                             // here we will iterate through the file till it ends
            {
                count++;                                         // increase the line number
                if (count <= 10) {
                    reservoirList.add(currentLine);
                } else {
                    randomNumber = ra.nextInt(count);
                    if (randomNumber < reservoirSize) {
                        reservoirList.set(randomNumber, currentLine);
                    }
                }
            }
            return reservoirList.get(new Random().nextInt(11));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return wordlistFile;
    }
}
