import java.util.ArrayList;
import java.util.List;

/**
 * Beschreiben Sie hier die Klasse Feldgruppen.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class ArrayGenerator {
    public ArrayList map_feldgruppen() {
        ArrayList<Integer> felgrup1 = new ArrayList<Integer>(
            List.of(0, 1, 2, 9, 10, 11, 18, 19, 20)
        );
        ArrayList<Integer> felgrup2 = new ArrayList<Integer>(
            List.of(3, 4, 5, 12, 13, 14, 21, 22, 23)
        );
        ArrayList<Integer> felgrup3 = new ArrayList<Integer>(
            List.of(6, 7, 8, 15, 16, 17, 24, 25, 26)
        );
        ArrayList<Integer> felgrup4 = new ArrayList<Integer>(
            List.of(27, 28, 29, 36, 37, 38, 45, 46, 47)
        );
        ArrayList<Integer> felgrup5 = new ArrayList<Integer>(
            List.of(30, 31, 32, 39, 40, 41, 48, 49, 50)
        );
        ArrayList<Integer> felgrup6 = new ArrayList<Integer>(
            List.of(33, 34, 35, 42, 43, 44, 51, 52, 53)
        );
        ArrayList<Integer> felgrup7 = new ArrayList<Integer>(
            List.of(54, 55, 56, 63, 64, 65, 72, 73, 74)
        );
        ArrayList<Integer> felgrup8 = new ArrayList<Integer>(
            List.of(57, 58, 59, 66, 67, 68, 75, 76, 77)
        );
        ArrayList<Integer> felgrup9 = new ArrayList<Integer>(
            List.of(60, 61, 62, 69, 70, 71, 78, 79, 80)
        );
        ArrayList<ArrayList<Integer>> feldgruppen = new ArrayList<ArrayList<Integer>>(
            List.of(felgrup1, felgrup2, felgrup3, felgrup4, felgrup5, felgrup6, felgrup7, felgrup8, felgrup9)
        );
        return feldgruppen;
    }
    public ArrayList bigboard() {
        ArrayList<String> bigboard = new ArrayList<String>();
        for (int i = 1; i <=9; i++) {
            bigboard.add("p0");
        }
        return bigboard;
    }
    public ArrayList winningConditions() {
        ArrayList<Integer> wincon1 = new ArrayList<Integer>(
            List.of(0, 1, 2)
        );
        ArrayList<Integer> wincon2 = new ArrayList<Integer>(
            List.of(3, 4, 5)
        );
        ArrayList<Integer> wincon3 = new ArrayList<Integer>(
            List.of(6, 7, 8)
        );
        ArrayList<Integer> wincon4 = new ArrayList<Integer>(
            List.of(0, 3, 6)
        );
        ArrayList<Integer> wincon5 = new ArrayList<Integer>(
            List.of(1, 4, 7)
        );
        ArrayList<Integer> wincon6 = new ArrayList<Integer>(
            List.of(2, 5, 8)
        );
        ArrayList<Integer> wincon7 = new ArrayList<Integer>(
            List.of(0, 4, 8)
        );
        ArrayList<Integer> wincon8 = new ArrayList<Integer>(
            List.of(2, 4, 6)
        );
        ArrayList<ArrayList<Integer>> winningconditions = new ArrayList<ArrayList<Integer>>(
            List.of(wincon1, wincon2, wincon3, wincon4, wincon5, wincon6, wincon7, wincon8)
        );
        return winningconditions;
    }
    public ArrayList feldgruppen() {
        ArrayList<ArrayList> feldgruppen;
        ArrayList<Integer> felgrup1 = new ArrayList<Integer>(
            List.of(0, 3, 6, 27, 30, 33, 54, 57, 60)
        );
        ArrayList<Integer> felgrup2 = new ArrayList<Integer>(
            List.of(1, 4, 7, 28, 31, 34, 55, 58, 61)
        );
        ArrayList<Integer> felgrup3 = new ArrayList<Integer>(
            List.of(2, 5, 8, 29, 32, 35, 56, 59, 62)
        );
        ArrayList<Integer> felgrup4 = new ArrayList<Integer>(
            List.of(9, 12, 15, 36, 39, 42, 63, 66, 69)
        );
        ArrayList<Integer> felgrup5 = new ArrayList<Integer>(
            List.of(10, 13, 16, 37, 40, 43, 64, 67, 70)
        );
        ArrayList<Integer> felgrup6 = new ArrayList<Integer>(
            List.of(11, 14, 17, 38, 41, 44, 65, 68, 71)
        );
        ArrayList<Integer> felgrup7 = new ArrayList<Integer>(
            List.of(18, 21, 24, 45, 48, 51, 72, 75, 78)
        );
        ArrayList<Integer> felgrup8 = new ArrayList<Integer>(
            List.of(19, 22, 25, 46, 49, 52, 73, 76, 79)
        );
        ArrayList<Integer> felgrup9 = new ArrayList<Integer>(
            List.of(20, 23, 26, 47, 50, 53, 74, 77, 80)
        );
        feldgruppen = new ArrayList<ArrayList>(
            List.of(felgrup1, felgrup2, felgrup3, felgrup4, felgrup5, felgrup6, felgrup7, felgrup8, felgrup9)
        );
        System.out.println("Feldgruppen: " + feldgruppen);
        return feldgruppen;
    }
}
