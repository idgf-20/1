package algoVersuch3_Hashing;

public class Zelle {
    String inhalt;
    Zelle next;

  
    Zelle (String el) {
        inhalt = el;
        next = null;
    }
    Zelle (String el, Zelle z) {
        inhalt = el;
        next = z;
    }
}