package fr.nico.ocprojet;


import org.apache.logging.log4j.Level;

import java.util.List;

/**
 * La classe Display contient les méthodes pour l'affichaga à l'écran
 */
public class Display {

    public Display() {
        App.logger.log(Level.TRACE, "Init Display");
    }

    public void afficheGameList() {
        for (Games games : Games.values()) {
            System.out.println(games.getAbbreviation() + " -> " + games.toString());
        }
    }

    public void demandeChoixJeu() {
        afficheGameList();
        System.out.println("Veuillez choisir un jeu (R ou M) : ");
    }

    public void erreurSaisie() {
        System.out.println("la valeur saisie ne correspond pas aux valeurs ou formats attendus");
    }

    public void afficheModeList() {
        for (GameMode mode : GameMode.values()) {
            System.out.println(mode.getCode() + " -> " + mode.toString());
        }
    }

    public void demandeChoixMode() {
        afficheModeList();
        System.out.println("Veuillez choisir un mode (C, D ou VS) : ");
    }

    public void invitePropositionCombinaison(int tailleCominaison) {
        System.out.println("Veuillez saisir un combinaison de " + tailleCominaison + " chiffres");
    }

    public void displayHistoriqueTour(List<String[]> historique) {
        App.logger.log(Level.DEBUG, "Ci-dessous L'historique de vos propositions avec leur résultat : ");
        int essai = 0;
        for (String[] resultat : historique) {
            App.logger.log(Level.DEBUG, "proposition " + essai + " : " + resultat[0] + "  -> resultat : " + resultat[1]);
            essai++;
        }
    }

    public void inviteRejouer() {
        System.out.println("Voulez vous :");
        System.out.println("R -> Rejouer la partie avec les mêmes paramètres");
        System.out.println("N -> Faire une nouvelle partie avec d'autres choix");
        System.out.println("Q -> Quitter le jeu");
    }
}
