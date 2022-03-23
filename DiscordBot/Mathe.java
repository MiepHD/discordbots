import java.util.ArrayList;

/**
 * Berechnen von Teilern und Primzahlen
 * 
 * @author Laila
 * @version 07.02.2022
 */
public class Mathe {
    // Berechnet den Rest einer Division
    public boolean istTeilerVon(int zahl1, int zahl2) {
        int rest;
        rest = zahl1 % zahl2; //modulo - Gibt den Rest einer Division zurück
        if (rest == 0) {
            return true;
        } else {
            return false;
        }
    }
}
