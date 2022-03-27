import net.dv8tion.jda.api.entities.*;
import java.util.ArrayList;

public class Spielstatus {
    static String turn;
    static String player1;
    static String player2;
    static boolean begonnen;
    static Board board;
    static ArrayList<Integer> data;
    public Spielstatus() {
        data = null;
        turn = "";
        board = new Board();
        ArrayGenerator generate = new ArrayGenerator();
    }
}
