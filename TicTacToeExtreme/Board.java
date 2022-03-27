import java.util.ArrayList;
import java.util.List;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Board
{
    private ArrayList<String> board;
    private boolean egal;
    private String green;
    private String white;
    private String kreis;
    private String kreuz;
    private String symbol;
    private Mathe mathe;
    private ArrayList<String> zahlen;
    private ArrayList<ArrayList<Integer>> feldgruppen;
    private ArrayList<ArrayList<Integer>> map_feldgruppen;
    private ArrayList<ArrayList<Integer>> winningconditions;
    private ArrayList<String> bigboard;
    public Board() {
        ArrayGenerator generate = new ArrayGenerator();
        zahlen = new ArrayList<String>(
            List.of("", "", ":two:", ":three:", ":four:", ":five:", ":six:", ":seven:", ":eight:", ":nine:", "")
        );
        mathe = new Mathe();
        egal = false;
        board = new ArrayList<String>();
        green = ":green_square:";
        white = ":white_large_square:";
        kreis = ":o2:";
        kreuz = ":regional_indicator_x:";
        symbol = "";
        bigboard = generate.bigboard();
        feldgruppen = generate.feldgruppen();
        map_feldgruppen = generate.map_feldgruppen();
        winningconditions = generate.winningConditions();
    }
    public String zeichneBoard() {
        System.out.println("Board: " + board);
        int i = 0;
        int row = 2;
        String spielfeld_text = ":black_large_square::regional_indicator_a::regional_indicator_b::regional_indicator_c::regional_indicator_d::regional_indicator_e::regional_indicator_f::regional_indicator_g::regional_indicator_h::regional_indicator_i:\n:one:";
        System.out.println("Zeichne Board");
        for (String x : board) {
            if (mathe.istTeilerVon(i + 1, 9)) {
                System.out.println("Erstelle Zeilenumbruch");
                spielfeld_text = spielfeld_text + x + "\n" + zahlen.get(row);
                row++;
            } else {
                spielfeld_text = spielfeld_text + x;
            }
            i++;
        }
        if (Start.debug == true) System.out.println("Board erstellt\nBoard ist: " + spielfeld_text);
        return spielfeld_text;
    }
    public void generiereBoard() {
        for (int i = 0; i <= 80; i++) {
             board.add(green);
             egal = true;
        }
    }
    public String place(int number, MessageReceivedEvent event) {
        String antwort = null;
        if (board.get(number) == green) {
            int target = findeFeld(number, feldgruppen);
            int startfeld = berechneStartfeld(target);
            zeichneGreen(target, startfeld);
            symbol = placeSymbol(number);
            int new_target = findeFeld(number, map_feldgruppen);
            startfeld = berechneStartfeld(new_target);
            boolean kleinfeldgewonnen = false;
            boolean spielgewonnen = false;
            if (hatKleinfeldGewonnen(startfeld)) {
                if (Spielstatus.turn == "p1") {
                    bigboard.set(new_target, "p2");
                    symbol = "p2";
                } else {
                    bigboard.set(new_target, "p1");
                    symbol = "p1";
                }
                if (target == new_target) {
                    for (int i = 0; i <= 81; i++) {
                        if (board.get(i) == white) {
                            board.set(i, green);
                        }
                    }
                }
                for (int x = 0; x <= 2; x++) {
                    for (int y = 0; y <= 2; y++) {
                        int zielfeld = startfeld + x + y * 9;
                        if (board.get(zielfeld) == white || board.get(zielfeld) == green) {
                            if (Spielstatus.turn == "p2") {
                                board.set(zielfeld, ":red_square:");
                            } else {
                                board.set(zielfeld, ":blue_square:");
                            }
                        }
                    }
                }
                String spielfeld_text = "";
                if (hatSpielGewonnen()) {
                    int p1_punkte;
                    int p2_punkte;
                    int p1_punkte_gesamt = 0;
                    int p2_punkte_gesamt = 0;
                    spielfeld_text = zeichneBoard();
                    event.getChannel().sendMessage(spielfeld_text).queue();
                    if (symbol == "p1") {
                        event.getChannel().sendMessage("<@" + Spielstatus.player1.getId() + "> hat gewonnen").queue();
                        p1_punkte = 6;
                        p2_punkte = 0;
                    } else {
                        event.getChannel().sendMessage("<@" + Spielstatus.player1.getId() + "> hat gewonnen").queue();
                        p2_punkte = 6;
                        p1_punkte = 0;
                    }
                    if (Spielstatus.player1 != Spielstatus.player2) {
                        for (String i: bigboard) {
                            if (i == "p1") {
                                p1_punkte = p1_punkte + 1;
                            } else if (i == "p2") {
                                p2_punkte = p2_punkte + 1;
                            }
                        }
                        if (!Spielstatus.data.contains(Spielstatus.player1)) {
                            Spielstatus.data.add(Integer.parseInt(Spielstatus.player1.getId()));
                            Spielstatus.data.add(0);
                        }
                        if (!Spielstatus.data.contains(Spielstatus.player2)) {
                            Spielstatus.data.add(Integer.parseInt(Spielstatus.player1.getId()));
                            Spielstatus.data.add(0);
                        }
                        for (int i = 0; i <= Spielstatus.data.size(); i++) {
                            if (Integer.parseInt(Spielstatus.player1.getId()) == Spielstatus.data.get(i)) {
                                Spielstatus.data.set(i + 1, Spielstatus.data.get(i + 1) + p1_punkte);
                                p1_punkte_gesamt = Spielstatus.data.get(i + 1);
                            }
                            if (Integer.parseInt(Spielstatus.player2.getId()) == Spielstatus.data.get(i)) {
                                Spielstatus.data.set(i + 1, Spielstatus.data.get(i + 1) + p2_punkte);
                                p2_punkte_gesamt = Spielstatus.data.get(i + 1);
                            }
                        }
                        event.getChannel().sendMessage("Player eins hat " + p1_punkte + " Punkte erspielt und hat damit jetzt insgesamt " + p1_punkte_gesamt).queue();
                        event.getChannel().sendMessage("Player zwei hat " + p2_punkte + " Punkte erspielt und hat damit jetzt insgesamt " + p2_punkte_gesamt).queue();
                    }
                }
            }
            int besetzt = 0;
            if (!spielgewonnen) {
                antwort = zeichneBoard();
                for (String element : bigboard) {
                    if (element != "p0") {
                        besetzt++;
                    }
                }
                if (besetzt == 9) {
                    event.getChannel().sendMessage("Unentschieden!").queue();
                    antwort = zeichneBoard();
                    return antwort;
                }
            }
            besetzt = 0;
            if (!kleinfeldgewonnen) {
                for (int x = 0; x <= 2; x++) {
                    for (int y = 0; y <= 2; y++) {
                        int zielfeld = startfeld + x + y * 9;
                        if (board.get(zielfeld) == kreis || board.get(zielfeld) == kreuz) {
                            besetzt++;
                        }
                    }
                }
                if (besetzt == 9) {
                    bigboard.set(new_target, "/");
                }
            }
        } else {
            return "Feld "+ number +" nicht auswählbar. \n Board: " + board.get(number);
        }
        return antwort;
    }
    public boolean hatSpielGewonnen() {
        for (int i = 0; i <= 7; i++) {
            if (bigboard.get(winningconditions.get(i).get(0)) == symbol) {
                if (bigboard.get(winningconditions.get(i).get(1)) == symbol) {
                    if (bigboard.get(winningconditions.get(i).get(2)) == symbol) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean hatKleinfeldGewonnen(int startfeld) {
        for (int i = 0; i <= 7; i++) {
            if (board.get(startfeld + map_feldgruppen.get(0).get(winningconditions.get(i).get(0))) == symbol) {
                if (board.get(startfeld + map_feldgruppen.get(0).get(winningconditions.get(i).get(1))) == symbol) {
                    if (board.get(startfeld + map_feldgruppen.get(0).get(winningconditions.get(i).get(2))) == symbol) {
                        System.out.println("Kleines Feld gewonnen");
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public int findeFeld(int number, ArrayList<ArrayList<Integer>> gruppe) {
        System.out.println ("Möglichkeiten: " + gruppe);
        for (int i = 0; i <= 8; i++) {
            if (gruppe.get(i).contains(number)) {
                return i;
            }
        }
        System.out.println("Keine Übereinstimmung gefunden");
        return -1;
    }
    public int berechneStartfeld(int target) {
        int spalte;
        int zeile;
        int startfeld;
        spalte = target % 3;
        System.out.println(target + " ist in Spalte " + spalte);
        zeile = (target - spalte) / 3;
        System.out.println(target + " ist in Zeile " + zeile);
        startfeld = zeile * 27 + spalte * 3;
        System.out.println("Das Startfeld ist: " + startfeld);
        return startfeld;
    }
    public String placeSymbol(int number) {
        if (Spielstatus.turn == "p1") {
            board.set(number, kreis);
            Spielstatus.turn = "p2";
            return kreis;
        } else {
            board.set(number, kreuz);
            Spielstatus.turn = "p1";
            return kreuz;
        }
    }
    public void zeichneGreen(int target, int startfeld) {
        if (bigboard.get(target) == "p0") {
            for (int i = 0; i <= 80; i++) {
                if (board.get(i) == green) {
                    board.set(i, white);
                }
            }
            for (int x = 0; x <= 2; x++) {
                for (int y = 0; y <= 2; y++) {
                    int zielfeld = startfeld + x + y * 9;
                    if (board.get(zielfeld) == white) {
                        board.set(zielfeld, green);
                    }
                }
            }
        } else {
            for (int i = 0; i <= 80; i++) {
                if (board.get(i) == white) {
                    board.set(i, green);
                }
            }
        }
    }
}
