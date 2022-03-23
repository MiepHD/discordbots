import java.util.ArrayList;

/**
 * Beschreiben Sie hier die Klasse Feldgruppen.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class ArrayGenerator {
    public ArrayList map_feldgruppen() {
        ArrayList<Integer> infeldgruppen;
        ArrayList<ArrayList> feldgruppen;
        infeldgruppen = new ArrayList<Integer>();
        feldgruppen = new ArrayList<ArrayList>();
        infeldgruppen.add(0);
        infeldgruppen.add(1);
        infeldgruppen.add(2);
        infeldgruppen.add(9);
        infeldgruppen.add(10);
        infeldgruppen.add(11);
        infeldgruppen.add(18);
        infeldgruppen.add(19);
        infeldgruppen.add(20);
        feldgruppen.add(infeldgruppen);
        return feldgruppen;
    }
    public ArrayList bigboard() {
        ArrayList<String> bigboard = new ArrayList<String>();
        for (int i = 1; i <=9; i++) {
            bigboard.add("p0");
        }
        return bigboard;
    }
    public ArrayList zahlen() {
        ArrayList<String> zahlen = new ArrayList<String>();
        zahlen.add("");
        zahlen.add("");
        zahlen.add(":two:");
        zahlen.add(":three:");
        zahlen.add(":four:");
        zahlen.add(":five:");
        zahlen.add(":six:");
        zahlen.add(":seven:");
        zahlen.add(":eight:");
        zahlen.add(":nine:");
        zahlen.add("");
        return zahlen;
    }
    public ArrayList feldgruppen() {
        ArrayList<Integer> infeldgruppen;
        ArrayList<ArrayList> feldgruppen;
        infeldgruppen = new ArrayList<Integer>();
        feldgruppen = new ArrayList<ArrayList>();
        infeldgruppen.add(0);
        infeldgruppen.add(3);
        infeldgruppen.add(6);
        infeldgruppen.add(27);
        infeldgruppen.add(30);
        infeldgruppen.add(33);
        infeldgruppen.add(54);
        infeldgruppen.add(57);
        infeldgruppen.add(60);
        feldgruppen.add(infeldgruppen);
        infeldgruppen.clear();
        infeldgruppen.add(1);
        infeldgruppen.add(4);
        infeldgruppen.add(7);
        infeldgruppen.add(28);
        infeldgruppen.add(31);
        infeldgruppen.add(34);
        infeldgruppen.add(55);
        infeldgruppen.add(58);
        infeldgruppen.add(61);
        feldgruppen.add(infeldgruppen);
        infeldgruppen.clear();
        infeldgruppen.add(2);
        infeldgruppen.add(5);
        infeldgruppen.add(8);
        infeldgruppen.add(29);
        infeldgruppen.add(32);
        infeldgruppen.add(35);
        infeldgruppen.add(56);
        infeldgruppen.add(59);
        infeldgruppen.add(62);
        feldgruppen.add(infeldgruppen);
        infeldgruppen.clear();
        infeldgruppen.add(9);
        infeldgruppen.add(12);
        infeldgruppen.add(15);
        infeldgruppen.add(36);
        infeldgruppen.add(39);
        infeldgruppen.add(42);
        infeldgruppen.add(63);
        infeldgruppen.add(66);
        infeldgruppen.add(69);
        feldgruppen.add(infeldgruppen);
        infeldgruppen.clear();
        infeldgruppen.add(10);
        infeldgruppen.add(13);
        infeldgruppen.add(16);
        infeldgruppen.add(37);
        infeldgruppen.add(40);
        infeldgruppen.add(43);
        infeldgruppen.add(64);
        infeldgruppen.add(67);
        infeldgruppen.add(70);
        feldgruppen.add(infeldgruppen);
        infeldgruppen.clear();
        infeldgruppen.add(11);
        infeldgruppen.add(14);
        infeldgruppen.add(17);
        infeldgruppen.add(38);
        infeldgruppen.add(41);
        infeldgruppen.add(44);
        infeldgruppen.add(65);
        infeldgruppen.add(68);
        infeldgruppen.add(71);
        feldgruppen.add(infeldgruppen);
        infeldgruppen.clear();
        infeldgruppen.add(18);
        infeldgruppen.add(21);
        infeldgruppen.add(24);
        infeldgruppen.add(45);
        infeldgruppen.add(48);
        infeldgruppen.add(51);
        infeldgruppen.add(72);
        infeldgruppen.add(75);
        infeldgruppen.add(78);
        feldgruppen.add(infeldgruppen);
        infeldgruppen.clear();
        infeldgruppen.add(19);
        infeldgruppen.add(22);
        infeldgruppen.add(25);
        infeldgruppen.add(46);
        infeldgruppen.add(49);
        infeldgruppen.add(52);
        infeldgruppen.add(73);
        infeldgruppen.add(76);
        infeldgruppen.add(79);
        feldgruppen.add(infeldgruppen);
        infeldgruppen.clear();
        infeldgruppen.add(20);
        infeldgruppen.add(23);
        infeldgruppen.add(26);
        infeldgruppen.add(47);
        infeldgruppen.add(50);
        infeldgruppen.add(53);
        infeldgruppen.add(74);
        infeldgruppen.add(77);
        infeldgruppen.add(80);
        feldgruppen.add(infeldgruppen);
        infeldgruppen.clear();
        System.out.println("Feldgruppen: " + feldgruppen);
        return feldgruppen;
    }
}
