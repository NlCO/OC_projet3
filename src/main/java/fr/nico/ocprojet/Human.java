package fr.nico.ocprojet;




/**
 * Human étend la classe @see Player et représente un joueur humain
 */
public class Human extends Player {
    private Interaction ihm;

    public Human() {
        ihm = new Interaction();
    }

    public Game choixDuJeu() {
        Game gameChoisi = ihm.selectionJeu();
        return gameChoisi;
    }

    public GameMode choixDuMode() {
        GameMode modeChoisi = ihm.selectionMode();
        return modeChoisi;
    }

}
