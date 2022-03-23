import net.dv8tion.jda.api.entities.*;

public class Spielstatus {
    static String turn;
    static User player1;
    static User player2;
    static boolean begonnen;
    static Board board;
    public Spielstatus() {
        turn = "";
        board = new Board();
    }
}
