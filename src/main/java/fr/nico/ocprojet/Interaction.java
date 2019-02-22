package fr.nico.ocprojet;

import java.util.List;
import java.util.Scanner;

/**
 * Cette class a pour objectif de gérer les interactions en entre le joueur humain et la machine
 */
public class Interaction {
    private Display screen = new Display();

    public Interaction() {
        //this.joueur = joueur;
    }

    /**
     * Method pour récupérer les saisies clavier du joueur
     *
     * @return la saisie clavier sous forme texte
     */
    public String saisieClavier() {
        Scanner sc = new Scanner(System.in);
        String saisie = sc.nextLine();
        return saisie;
    }

    /**
     * Method pour récupérer le choix du jeu
     *
     * @return le jeu choisi
     */
    public Games selectionJeu() {
        Games choix;
         do {
                screen.demandeChoixJeu();
                choix = Games.valueOf(saisieClavier().toUpperCase());
                if (choix == null) {
                    screen.erreurSaisie();
                }
        } while (choix == null);
        return choix;
    }

    /**
     * Method pour récupérer le mode du jeu
     *
     * @return le mode choisi
     */
    public GameMode selectionMode() {
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

    /**
     * Methode pour récupérer la combinaison saisie au clavier
     *
     * @param tailleCombinaison taille de la combinaison attendues
     * @return la combinaison saisie
     */
    public String proposeCombinaison(int tailleCombinaison) {
            screen.invitePropositionCombinaison(tailleCombinaison);
        return saisieClavier().toUpperCase();
    }

    public void afficheHistoriqueTour(List<String[]> historique) {
        screen.displayHistoriqueTour(historique);
    }
}
