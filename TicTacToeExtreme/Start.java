import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Start {
    static boolean debug;
    public static void main(String[] args) throws Exception {
        System.out.println("Anmelden mit Token " + args[0] + "...");
        JDA jda = JDABuilder.createDefault(args[0]).build();
        System.out.println("Erfolgreich angemeldet\nErstelle EventListener");
        jda.addEventListener(new MyEventListener());
        System.out.println("EventListener erfolgreich erstellt");
        if (args.length >= 2) {
            if (args[1] == "true") {
                boolean debug = true;
                System.out.println("Debug-Modus eingeschaltet");
            } else {
                boolean debug = false;
            }
        }
        new Spielstatus();
    }
}