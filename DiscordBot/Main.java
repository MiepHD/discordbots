import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.entities.*;
/**
 * Beschreiben Sie hier die Klasse Main.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Main
{
    public String onMessage(String message, MessageReceivedEvent event) {
        String antwort = null;
        if (message.startsWith("*exit")) {
            System.exit(0);
        }
        if (message.startsWith("*tictactoe") || message.startsWith("*ttt")) {
            TicTacToe tictactoe = new TicTacToe();
            antwort = tictactoe.ticTacToe(message, event);
            return antwort;
        }
        if (message.startsWith("*p")) {
            Place place = new Place();
            antwort = place.place(message, event);
            return antwort;
        }
        return antwort;
    }
}