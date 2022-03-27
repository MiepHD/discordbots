import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class TicTacToe
{
    private String antwort;
    public TicTacToe() {
        antwort = null;
    }
    public String ticTacToe(String message, MessageReceivedEvent event) {
        if (Spielstatus.player1 == null) {
            Spielstatus.player1 = event.getAuthor();
            antwort = "Player eins ist <@" + event.getAuthor().getId() + ">";
            return antwort;
        } else if (Spielstatus.player2 == null) {
            Spielstatus.player2 = event.getAuthor();
            antwort = "Player zwei ist <@" + event.getAuthor().getId() + ">";
            event.getChannel().sendMessage(antwort).queue();
            Spielstatus.begonnen = true;
            Spielstatus.board.generiereBoard();
            Spielstatus.turn = "p1";
            antwort = Spielstatus.board.zeichneBoard();
            return antwort;
        } else {
            antwort = "Es sind bereits alle Plätze besetzt";
            return antwort;
        }
    }
}
