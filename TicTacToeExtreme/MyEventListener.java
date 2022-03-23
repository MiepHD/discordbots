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
        if (event.getAuthor().isBot()) return;
        
        Message message = event.getMessage();
        String content = message.getContentRaw();
        MessageChannel channel = event.getChannel();
        String antwort = main.onMessage(content, event);
        if (antwort != null) {
            event.getChannel().sendMessage(antwort).queue();
        }
    }
}
