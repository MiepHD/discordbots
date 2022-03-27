import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class MyEventListener extends ListenerAdapter {
    private Main main;
    public MyEventListener() {
         main = new Main();
    }
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            if (Start.debug == true) System.out.println("Nachricht eines Bots");
            return;
        }
        if (Start.debug == true) System.out.println("Werte Nachricht aus");
        Message message = event.getMessage();
        String content = message.getContentRaw();
        MessageChannel channel = event.getChannel();
        if (Start.debug == true) System.out.println("Nachricht ausgewertet");
        String antwort = main.onMessage(content, event);
        if (antwort != null) {
            Versenden.sendMessage(event, antwort);
        } else {
            System.out.println("Leere Antwort zurückgekommen. Ist ein Fehler aufgetreten?");
        }
    }
}
