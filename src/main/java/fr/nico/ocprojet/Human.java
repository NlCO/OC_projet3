package fr.nico.ocprojet;


import org.apache.logging.log4j.Level;

import java.util.List;
import java.util.Scanner;

/**
 * La classe Human décrit la classe {@link Player Player} pour un joueur humain et gére ses interaction avec la machine
 */
public class Human extends Player {
    private String name;
    private Display screen;

    public Human() {
        App.logger.log(Level.TRACE, "Création joueur humain");
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
    public String genereUneCombinaison(Games jeu, int tailleCombinaison, List<String> setDeValeurs) {
        if (jeu == Games.R) {
            screen.invitePropositionCombinaisonRecherche(tailleCombinaison);
        } else {

        }
        return saisieClavier().toUpperCase();
    }

    @Override
    public String proposeUneCombinaison(Games jeu, int tailleCombinaison, List<String> setDeValeurs, List<String[]> historique) {
        if (historique.size() > 0) {
            screen.displayHistoriqueTour(historique);
        }
        return genereUneCombinaison(jeu, tailleCombinaison, setDeValeurs);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean demandeRejouerPartie() {
        String rejoue;
        do {
            screen.inviteRejouer();
            rejoue = saisieClavier().toUpperCase();
            if (!rejoue.matches("R|N|Q")) {
                App.logger.log(Level.WARN, "Erreur de saisie : " + rejoue + " ne fait pas partie des choix possibles");
                System.out.println("Erreur de saisie : " + rejoue + " ne fait pas partie des choix possibles");
            }
        } while (!rejoue.matches("R|N|Q"));
        if (rejoue.matches("Q")) {
            App.logger.log(Level.TRACE, "sortie de l'application");
            System.out.println("Bye Bye !!!");
            System.exit(0);
        }
        return rejoue.matches("R");
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
