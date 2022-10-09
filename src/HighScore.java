import java.io.Serializable;

public class HighScore {
    static void displayAll(Entry[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.println(i + 1 + " - " + list[i].name + "\t\t\t" + list[i].score);
        }
    }

    static boolean placedOnBoard(Entry[] list, int guesses) {
        boolean placed = false;
        for (Entry entry : list) {
            if (guesses < entry.score) {
                placed = true;
                break;
            }
        }
        return placed;
    }

    static Entry[] addEntry(Entry[] list, Entry entry) {
        Entry[] result = new Entry[10];
        for (int i = 0; i < list.length; i++) {
            if (entry.score < list[i].score) {
                System.arraycopy(list, 0, result, 0, i);
                result[i] = entry;
                System.arraycopy(list, i, result, i + 1, list.length - 1);
                return result;
            }
        }

        return result;
    }


    static class Entry implements Serializable {
        String name;
        int score;

        Entry(String name, int score) {
            this.name = name;
            this.score = score;
        }

        Entry() {
            this.name = "Ada Lovelace";
            this.score = 99999;
        }
    }
}


