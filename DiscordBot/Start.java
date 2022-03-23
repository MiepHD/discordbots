import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Start {
    public static void main(String[] args) throws Exception {
        JDA jda = JDABuilder.createDefault("token").build();
        jda.addEventListener(new MyEventListener());
        new Spielstatus();
    }
}