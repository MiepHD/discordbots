import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class TicTacToe
{
    private String antwort;
    public TicTacToe() {
        antwort = null;
    }
    public String ticTacToe(String message, MessageReceivedEvent event) {
        if (Spielstatus.player1 == null) {
            Spielstatus.player1 = event.getAuthor().getId();
            return "Player eins ist <@" + event.getAuthor().getId() + ">";
        } else if (Spielstatus.player2 == null) {
            Spielstatus.player2 = event.getAuthor().getId();
            antwort = "Player zwei ist <@" + event.getAuthor().getId() + ">";
            Versenden.sendMessage(event, antwort);
            Spielstatus.begonnen = true;
            Spielstatus.board.generiereBoard();
            Spielstatus.turn = "p1";
            antwort = Spielstatus.board.zeichneBoard();
            return antwort;
        } else {
            return "Es sind bereits alle Plätze besetzt";
        }
    }
}
