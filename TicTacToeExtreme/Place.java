import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * Beschreiben Sie hier die Klasse Place.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Place
{
    public String place(String message, MessageReceivedEvent event) {
        String antwort;
        String eingabe;
        char buchstabe;
        int buchstabenziffer;
        int ziffer;
        int number;
        antwort = null;
        if (!((Spielstatus.turn == "p1" && event.getAuthor() == Spielstatus.player1)  || (Spielstatus.turn == "p2" && event.getAuthor() == Spielstatus.player2))) {
            antwort = "Du bist nicht an der Reihe";    
            return antwort;
        }
        eingabe = message.split(" ", 2)[1];
        buchstabe = eingabe.charAt(0);
        buchstabenziffer = buchstabe - 65;
        System.out.println("Zahl für Buchstabe " + buchstabe + ": " + buchstabenziffer);
        ziffer = eingabe.charAt(1) - 49;
        System.out.println("Zahl ist: " + ziffer);
        number = ziffer * 9 + buchstabenziffer;
        System.out.println("Extrahierte Zahl: " + number);
        antwort = Spielstatus.board.place(number, event);
        return antwort;
    }
}
