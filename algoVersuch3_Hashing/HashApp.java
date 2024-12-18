package algoVersuch3_Hashing;

import javax.swing.*;
import java.util.NoSuchElementException;

public class HashApp {
	
	
    public static void main (String args []) {

        final int B = 5;
        String  menue = "Eingabe\n" ;
        menue += " 1) Element einfuegen\n";
        menue += " 2) Element suchen\n"; //TODO
        menue += " 3) Zelle loeschen\n"; //TODO
        menue += " 4) Laenge der Liste von Korb k\n";
        menue += " 5) Liste von Korb k ansehen\n"; //TODO
        menue += " 6) Laenge der Listen von allen Koerben\n"; //TODO
        menue += " 7) Liste zufaellig fuellen\n";
        String ausgabe="";
        Liste hashTabelle[];
      
        hashTabelle = new Liste [B];
      

        for (int i = 0; i < B;i++)
            hashTabelle[i] = new Liste();

        while (true) {

            String menueeingabe = JOptionPane.showInputDialog(menue);
            if (menueeingabe == null)
                break;
            String dialogEingabe;

            

            if (menueeingabe.equals("1")) {
                dialogEingabe = JOptionPane.showInputDialog("Datenstring?");
               
                int h = hashTabelle[0].hashFunktion(dialogEingabe, B); //Welcher Korb?
                boolean existsInHashtable = false;
                for (int j = 1; j <= hashTabelle[h].laenge(); j++) {
                    if (hashTabelle[h].inhalt(j).equals(dialogEingabe)){
                        ausgabe = "Element in Korb " + h + " gefunden, daher nicht eingefuegt";
                        existsInHashtable = true;
                        break;
                    }

                }
                if (!existsInHashtable) {
                    hashTabelle[h].einsetzenAnfang(dialogEingabe);
                    ausgabe = "Eingabe eingefuegt in bucket " + h;
                }
                JOptionPane.showMessageDialog(null, ausgabe);
            }

            if (menueeingabe.equals("2")) {
                dialogEingabe = JOptionPane.showInputDialog("Welches Element soll gesucht werden?");
              
                int h = hashTabelle[0].hashFunktion(dialogEingabe, B);
              

                
                int position = hashTabelle[h].suche(dialogEingabe);
                
                if(position != -1){
              
                    ausgabe = "Element gefunden in Bucket " + h + ", die Nummer des Korbes : " + position;
                }else{
                    ausgabe = "Element nicht gefunden";
                }
                JOptionPane.showMessageDialog(null, ausgabe);
            }

            if (menueeingabe.equals("3")) {

                dialogEingabe = JOptionPane.showInputDialog("Welches Element soll geloescht werden?");

                int h = hashTabelle[0].hashFunktion(dialogEingabe, B);
              
                int position = hashTabelle[h].suche(dialogEingabe);
               
                if(hashTabelle[h].laenge() == 0){
                    throw new IllegalArgumentException("Bucket ist leer!!");
                }
                if(position == -1){
                    throw new NoSuchElementException("Element existiert nicht");
                }

                String loeschendeElement = hashTabelle[h].inhalt(position);
               
                hashTabelle[h].loescheElem(dialogEingabe);
                ausgabe = "Element in Bucket "+ h + " --> " + loeschendeElement + " gelöscht!";
                JOptionPane.showMessageDialog(null, ausgabe);
              
            }

            if (menueeingabe.equals("4")) {
                dialogEingabe = JOptionPane.showInputDialog("Von welchem Korb soll die Laenge angezeigt werden?");
                //ausgabe = "";
                int k = Integer.parseInt(dialogEingabe);
                int l = hashTabelle[k].laenge();
                ausgabe = "Liste hat Laenge " + l;
                JOptionPane.showMessageDialog(null, ausgabe);
            }

            if (menueeingabe.equals("5")) {
                //...
                dialogEingabe = JOptionPane.showInputDialog("Welcher Bucket soll gezeigt werden?");
                int bucket = Integer.parseInt(dialogEingabe);
            
                if(bucket < 0 || bucket > B){
                    throw new IllegalArgumentException("In der Hashtabelle stehen nur Buckets von 0 bis 4!!");
                }
             
                if(hashTabelle[bucket].laenge() == 0){
                    throw new IllegalArgumentException("Bucket ist leer!!");
                }
              
                ausgabe = hashTabelle[bucket].zeigeAlleElementeInBucket(bucket);
                JOptionPane.showMessageDialog(null, ausgabe);
            }

            if (menueeingabe.equals("6")) {
            

                ausgabe = "Anzahl der Elemente in jedem Bucket in der Hashtabelle:\n";
          
                for(int i = 0; i < B; i++){
                    ausgabe += hashTabelle[i].listenLaengen(i);
                }

                JOptionPane.showMessageDialog(null, ausgabe);
            }

            if (menueeingabe.equals("7")) {
                // Eingabeaufforderung für die Anzahl der zufällig zu generierenden Elemente
                dialogEingabe = JOptionPane.showInputDialog("Anzahl?");
                ausgabe = ""; // Initialisierung der Ausgabestring
                int zahl = Integer.parseInt(dialogEingabe); // Konvertierung der Eingabe in einen Integer-Wert
                
                // Schleife zur Erstellung der geforderten Anzahl zufälliger Strings
                for (int j = 1; j <= zahl; j++) {
                    String s = ""; // Initialisierung des zufälligen Strings
                    
                    // Generierung einer zufälligen Länge für den String zwischen 3 und 10
                    int laenge = (int) ((Math.random() * 8) + 3);
                    
                    // Schleife zur Erstellung eines zufälligen Strings aus Großbuchstaben
                    for (int k = 1; k <= laenge; k++) {
                        // Zufälliger ASCII-Wert zwischen 65 ('A') und 90 ('Z')
                        char zufall = (char) ((Math.random() * 26) + 65);
                        s += zufall; // Anhängen des zufälligen Buchstabens an den String
                    }
                    
                    // Bestimmen des Buckets, in den der String eingefügt werden soll
                    int h = hashTabelle[0].hashFunktion(s, B); 
                    
                    // Einfügen des Strings am Anfang der Liste des entsprechenden Buckets
                    hashTabelle[h].einsetzenAnfang(s);
                }
            }
            
            /*Die Menüoption `7` ermöglicht das automatische Befüllen der Hashtabelle mit einer vom Benutzer vorgegebenen Anzahl zufällig generierter Strings. 

			1. **Eingabe der Anzahl**: Der Benutzer gibt an, wie viele zufällige Strings erstellt werden sollen. Diese Zahl wird verarbeitet.
			2. **String-Erzeugung**:
   				- Jeder String erhält eine zufällige Länge zwischen 3 und 10 Zeichen.
   				- Die Zeichen bestehen aus Großbuchstaben (A-Z), die durch Zufallsgenerierung erzeugt werden.
			3. **Hashwert berechnen**: Für jeden erzeugten String wird die Hashfunktion aufgerufen, um den passenden Bucket (Index in der Hashtabelle) zu ermitteln.
			4. **Einfügen in die Hashtabelle**: Der String wird in den Anfang der Liste im entsprechenden Bucket eingefügt.
			Diese Funktion erleichtert das Testen der Hashtabelle, da die Struktur automatisch mit zufälligen Daten befüllt wird, ohne dass der Benutzer jeden String einzeln eingeben muss.*/


        }
    }
}