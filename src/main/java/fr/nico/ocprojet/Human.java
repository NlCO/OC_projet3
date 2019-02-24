package fr.nico.ocprojet;


import java.util.List;
import java.util.Scanner;

/**
 * La classe Human décrit la classe {@link Player Player} pour un joueur humain et gére ses interaction avec la machine
 */
public class Human extends Player {
    private String name;
    private Display screen;

    public Human() {
        name = "X l'humain";
        screen = new Display();
    }

    @Override
    public Games choixDuJeu() {
        Games choix = null;
        do {
            screen.demandeChoixJeu();
            try {
                choix = Games.valueOf(saisieClavier().toUpperCase());
            } catch (IllegalArgumentException e) {
                screen.erreurSaisie();
            }

            //if (choix == null) {
            //    screen.erreurSaisie();
            //}
        } while (choix == null);
        return choix;
    }

    @Override
    public GameMode choixDuMode() {
        GameMode choix;
        do {
            screen.demandeChoixMode();
            choix = GameMode.modeFromCode(saisieClavier().toUpperCase());
            if (choix == null) {
                screen.erreurSaisie();
            }
        } while (choix == null);
        return choix;
    }

    @Override
    public String genereUneCombinaison(int tailleCombinaison) {
        screen.invitePropositionCombinaison(tailleCombinaison);
        return saisieClavier().toUpperCase();
    }

    @Override
    public String proposeUneCombinaison(int tailleCombinaison, List<String[]> historique) {
        if (historique.size() > 0){
            screen.displayHistoriqueTour(historique);
        }
        return genereUneCombinaison(tailleCombinaison);
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * Method pour récupérer les saisies clavier du joueur
     *
     * @return la saisie clavier sous forme texte
     */
    public String saisieClavier() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

}
