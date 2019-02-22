package fr.nico.ocprojet;


import java.util.List;

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

    @Override
    public String proposeUneCombinaison(int tailleCombinaison, List<String[]> historique) {
        ihm.afficheHistoriqueTour(historique);
        return genereUneCombinaison(tailleCombinaison);
    }

    @Override
    public String getName() {
        return name;
    }

}
