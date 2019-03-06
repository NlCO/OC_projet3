package fr.nico.ocprojet;


import org.apache.logging.log4j.Level;

import java.util.List;
import java.util.Scanner;

/**
 * La classe Human décrit la classe {@link Player Player} pour un joueur humain et gére ses interaction avec la machine
 */
public class Human extends Player {

    public Human() {
        App.logger.log(Level.TRACE, "Création joueur humain");
        super.setName("l'utilisateur");
    }

    /**
     * Retourne parmi une {@link Games liste de jeux} celui le choisi par le joueur
     *
     * @return jeu choisi
     */
    public Games choixDuJeu() {
        Games choix = null;
        do {
            System.out.println("Veuillez choisir un jeu (R ou M) : ");
            try {
                choix = Games.valueOf(saisieClavier().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("la valeur saisie ne correspond pas aux valeurs ou formats attendus");
            }
        } while (choix == null);
        return choix;
    }

    /**
     * Retourne parmi une {@link GameMode liste de mode de jeu} celui choisi par le joueur
     *
     * @return mode choisi
     */
    public GameMode choixDuMode() {
        GameMode choix;
        do {
            System.out.println("Veuillez choisir un mode (C, D ou VS) : ");
            choix = GameMode.modeFromCode(saisieClavier().toUpperCase());
            if (choix == null) {
                System.out.println("la valeur saisie ne correspond pas aux valeurs ou formats attendus");
            }
        } while (choix == null);
        return choix;
    }

    @Override
    public String genereUneCombinaison(int tailleCombinaison, List<String> setDeValeurs) {
        System.out.println("Veuillez créer une combinaison de " + tailleCombinaison + " elements parmi " + setDeValeurs + " :");
        return saisieClavier().toUpperCase();
    }

    @Override
    public String proposeUneCombinaison(Games jeu, int tailleCombinaison, List<String> setDeValeurs, List<String[]> historique) {
        if (historique.size() > 0) {
            debugHistoriqueTour(historique);
        }
        System.out.println("Veuillez faire une proposition (" + tailleCombinaison + " elements parmi " + setDeValeurs + ") :");
        return saisieClavier().toUpperCase();
    }

    /**
     * Methode qui permet de demander à un joueur s'il veut rejouer
     *
     * @return R pour rejouer la parie, N pour définir une nouvelle partie ou Q pour quitter
     */
    public String demandeRejouerPartie() {
        String rejoue;
        do {
            rejoue = saisieClavier().toUpperCase();
            if (!rejoue.matches("R|N|Q")) {
                App.logger.log(Level.WARN, "Erreur de saisie : " + rejoue + " ne fait pas partie des choix possibles");
                System.out.println("Erreur de saisie : " + rejoue + " ne fait pas partie des choix possibles");
            }
        } while (!rejoue.matches("R|N|Q"));
        return rejoue;
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


    private void debugHistoriqueTour(List<String[]> historique) {
        App.logger.log(Level.DEBUG, "Ci-dessous L'historique de vos propositions avec leur résultat : ");
        int essai = 0;
        for (String[] resultat : historique) {
            App.logger.log(Level.DEBUG, "proposition " + essai + " : " + resultat[0] + "  -> resultat : " + resultat[1]);
            essai++;
        }
    }

}
