package fr.nico.ocprojet;




/**
 * Human étend la classe @see Player et représente un joueur humain
 */
public class Human extends Player {
    private Interaction ihm;
    private Display screen = new Display();

    public Human() {
        ihm = new Interaction();
        screen = new Display();
    }

    @Override
    public Games choixDuJeu() {
        return ihm.selectionJeu();
    }

    @Override
    public GameMode choixDuMode() {
        return ihm.selectionMode();
    }

    @Override
    public String genereUneCombinaison() {
        return ihm.genereCombinaison();
    }
}
