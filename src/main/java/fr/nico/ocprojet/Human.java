package fr.nico.ocprojet;




/**
 * Human étend la classe @see Player et représente un joueur humain
 */
public class Human extends Player {
    private String name;
    private Interaction ihm;
    private Display screen;

    public Human() {
        name = "Humain";
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
    public String genereUneCombinaison(int tailleCombinaison) {
        return ihm.proposeCombinaison(tailleCombinaison);
    }

    public String getName() {
        return name;
    }

}
