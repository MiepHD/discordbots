import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * Beschreiben Sie hier die Klasse Versenden.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Versenden
{
    static MessageReceivedEvent old_event;
    public static void sendTemporaryMessage(MessageReceivedEvent event, String antwort) {
        if (old_event != null) {
            old_event.getMessage().delete().queue();
        }
        old_event = event;
        event.getChannel().sendMessage(antwort).queue();
    }
    public static void sendMessage(MessageReceivedEvent event, String antwort) {
        event.getChannel().sendMessage(antwort).queue();
    }
}