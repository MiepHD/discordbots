import java.util.ArrayList;

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
    private ArrayList<ArrayList> feldgruppen;
    private ArrayList<ArrayList> map_feldgruppen;
    private ArrayList<String> bigboard;
    public Board() {
        ArrayGenerator generate = new ArrayGenerator();
        zahlen = new ArrayList<String>();
        zahlen = generate.zahlen();
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
    }
    public String zeichneBoard() {
        int i = 0;
        int row = 2;
        String spielfeld_text = ":black_large_square::regional_indicator_a::regional_indicator_b::regional_indicator_c::regional_indicator_d::regional_indicator_e::regional_indicator_f::regional_indicator_g::regional_indicator_h::regional_indicator_i:\n:one:";
        for (String x : board) {
            if (mathe.istTeilerVon(i + 1, 9)) {
                spielfeld_text = spielfeld_text + x + "\n" + zahlen.get(row);
                row = row + 1;
            } else {
                spielfeld_text = spielfeld_text + x;
            }
            i++;
        }
        return spielfeld_text;
    }
    public void generiereBoard() {
        for (int i = 0; i <= 80; i++) {
             board.add(green);
             egal = true;
        }
    }
    public String place(int number) {
        String antwort = null;
        if (board.get(number) == green) {
            int target = 0;
            for (int i = 1; i <= 9; i++) {
                if (feldgruppen.get(i).contains(number)) {
                    target = i;
                }
            }
            int spalte;
            int zeile;
            int startfeld;
            spalte = target % 3;
            zeile = (target - spalte) / 3;
            startfeld = zeile * 27 + spalte * 3;
            if (bigboard.get(target) == "p0") {
                for (int i = 0; i <= 81; i++) {
                    if (board.get(i) == green) {
                        board.set(i, white);
                    }
                }
                for (int x = 0; x <= 3; x++) {
                    for (int y = 0; y <= 3; y++) {
                        int zielfeld = startfeld + x + y * 9;
                        if (board.get(zielfeld) == white) {
                            board.set(zielfeld, green);
                        }
                    }
                }
            } else {
                for (int i = 0; i <= 81; i++) {
                    if (board.get(i) == white) {
                        board.set(i, green);
                    }
                }
            }
            if (Spielstatus.turn == "p1") {
                board.set(number, kreis);
                Spielstatus.turn = "p2";
                symbol = kreis;
            } else {
                board.set(number, kreuz);
                Spielstatus.turn = "p1";
                symbol = kreuz;
            }
            int new_target = 0;
            for (int i = 0; i <= 9; i++) {
                if (map_feldgruppen.contains(number)) {
                    new_target = i;
                }
            }
        }
        return antwort;
    }
}
