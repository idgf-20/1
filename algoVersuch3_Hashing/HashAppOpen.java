package algoVersuch3_Hashing;

import javax.swing.*;
import java.util.Arrays;

public class HashAppOpen {
    public static void main(String[] args) {
        final int B = 5; // Größe der Hashtabelle
        String menue = "Eingabe\n";
        menue += " 1) Element einfuegen\n";
        menue += " 2) Element suchen\n";
        menue += " 3) Zelle loeschen\n";
        menue += " 4) Hashtabelle ansehen\n";
        String ausgabe = "";
        String[] hashTable = new String[B];
        Arrays.fill(hashTable, null);

        while (true) {
            String menueeingabe = JOptionPane.showInputDialog(menue);
            if (menueeingabe == null)
                break;

            String dialogEingabe;

            if (menueeingabe.equals("1")) { // Element einfügen
                dialogEingabe = JOptionPane.showInputDialog("Datenstring?");
                int h = hashFunction(dialogEingabe, B);
                boolean inserted = false;

                for (int i = 0; i < B; i++) {
                    int index = (h + i) % B; // lineare Sondierung
                    if (hashTable[index] == null || hashTable[index].equals("\0")) {
                        hashTable[index] = dialogEingabe;
                        ausgabe = "Eingabe eingefuegt in Position " + index;
                        inserted = true;
                        break;
                    } else if (hashTable[index].equals(dialogEingabe)) {
                        ausgabe = "Element bereits vorhanden in Position " + index;
                        inserted = true;
                        break;
                    }
                }

                if (!inserted) {
                    ausgabe = "Hashtabelle ist voll!";
                }

                JOptionPane.showMessageDialog(null, ausgabe);
            }

            if (menueeingabe.equals("2")) { // Element suchen
                dialogEingabe = JOptionPane.showInputDialog("Welches Element soll gesucht werden?");
                int h = hashFunction(dialogEingabe, B);
                boolean found = false;

                for (int i = 0; i < B; i++) {
                    int index = (h + i) % B;
                    if (hashTable[index] == null) {
                        break;
                    }
                    if (hashTable[index].equals(dialogEingabe)) {
                        ausgabe = "Element gefunden an Position " + index;
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    ausgabe = "Element nicht gefunden.";
                }

                JOptionPane.showMessageDialog(null, ausgabe);
            }

            if (menueeingabe.equals("3")) { // Element löschen
                dialogEingabe = JOptionPane.showInputDialog("Welches Element soll geloescht werden?");
                int h = hashFunction(dialogEingabe, B);
                boolean deleted = false;

                for (int i = 0; i < B; i++) {
                    int index = (h + i) % B;
                    if (hashTable[index] == null) {
                        break;
                    }
                    if (hashTable[index].equals(dialogEingabe)) {
                        hashTable[index] = "\0"; // Markiere als gelöscht
                        ausgabe = "Element geloescht an Position " + index;
                        deleted = true;
                        break;
                    }
                }

                if (!deleted) {
                    ausgabe = "Element nicht gefunden.";
                }

                JOptionPane.showMessageDialog(null, ausgabe);
            }

            if (menueeingabe.equals("4")) { // Hashtabelle anzeigen
                ausgabe = "Aktuelle Hashtabelle:\n";
                for (int i = 0; i < B; i++) {
                    ausgabe += "Position " + i + ": " + (hashTable[i] == null ? "(leer)" : hashTable[i]) + "\n";
                }
                JOptionPane.showMessageDialog(null, ausgabe);
            }
        }
    }

    // Hash-Funktion
    public static int hashFunction(String x, int b) {
        int hash = 0;
        for (int i = 0; i < x.length(); i++) {
            hash = (31 * hash + x.charAt(i)) % b;
        }
        return hash;
    }
}
