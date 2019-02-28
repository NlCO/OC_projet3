package fr.nico.ocprojet;

import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * La classe Bot décrit la classe {@link Player Player} pour un joueur non-humain et gére son "IA"
 */
public class Bot extends Player {
    private String name;
    private Random random;
    List<String> combinaisonsPossibles;

    public Bot() {
        App.logger.log(Level.TRACE, "Création joueur non-humain");
        name = "Bot le Bot";
        random = new Random();
    }

    @Override
    public Games choixDuJeu() {
        App.logger.log(Level.ERROR, "Methode non disponible pour un Bot");
        return null;
    }

    @Override
    public GameMode choixDuMode() {
        App.logger.log(Level.ERROR, "Methode non disponible pour un Bot");
        return null;
    }

    @Override
    public String genereUneCombinaison(Games jeu, int tailleCombinaison, List<String> setDeValeurs) {
        StringBuilder combinaison = new StringBuilder();
        for (int i = 0; i < tailleCombinaison; i++) {
            combinaison.append(setDeValeurs.get(random.nextInt(setDeValeurs.size())));
        }
        App.logger.log(Level.DEBUG, "Combinaison générée : " + combinaison.toString());
        return combinaison.toString();
    }

    @Override
    public String proposeUneCombinaison(Games jeu, int tailleCombinaison, List<String> colors, List<String[]> historique) {
        String proposition;
        if (jeu == Games.R) {
            proposition = propositionRecherche(tailleCombinaison, historique);
        } else {
            //proposition = genereUneCombinaison(jeu, tailleCombinaison, colors);
            propositionMastermind(tailleCombinaison,colors);
            System.exit(0);
            proposition = "";
        }
        return proposition;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean demandeRejouerPartie() {
        App.logger.log(Level.ERROR, "Methode non disponible pour un Bot");
        System.exit(0);
        return false;
    }

    private String propositionRecherche(int tailleCombinaison, List<String[]> historique) {
        StringBuilder proposition = new StringBuilder();
        StringBuilder borneMin = new StringBuilder();
        StringBuilder borneMax = new StringBuilder();
        for (int i = 0; i < tailleCombinaison; i++) {
            borneMin.append(0);
            borneMax.append(9);
        }
        App.logger.log(Level.DEBUG, "Tentative : " + historique.size());
        App.logger.log(Level.DEBUG, "Bot - Recherche +/- - Etat initiale des borne Min  : " + borneMin.toString() + " / Max : " + borneMax.toString());

        if (historique.size() == 0) {
            for (int i = 0; i < tailleCombinaison; i++) {
                proposition.append(5);
            }
            App.logger.log(Level.DEBUG, "Bot - Recherche +/- - première proposition : " + proposition.toString());
        } else {
            for (String[] resultat : historique) {
                int position = 0;
                for (String c : resultat[1].split("")) {
                    switch (c) {
                        case "=":
                            borneMin.setCharAt(position, resultat[0].charAt(position));
                            borneMax.setCharAt(position, resultat[0].charAt(position));
                            break;
                        case "-":
                            borneMax.setCharAt(position, resultat[0].charAt(position));
                            break;
                        case "+":
                            borneMin.setCharAt(position, resultat[0].charAt(position));
                            break;
                    }
                    App.logger.log(Level.DEBUG, "Bot - Recherche +- - proposition " + resultat[0] + " ajustement des bornes Min/Max : " + borneMin.toString() + "/" + borneMax.toString());
                    if ((historique.indexOf(resultat) + 1) == historique.size()) {
                        int biais = ((borneMax.toString().charAt(position) - '0') > 5) ? 1 : 0;
                        int alea = ((borneMax.toString().charAt(position) - '0') + (borneMin.toString().charAt(position) - '0') + biais) / 2;
                        proposition.append(alea);
                    }
                    position++;
                }
                App.logger.log(Level.DEBUG, "Bot - Recherche +- - proposition :" + proposition.toString());
            }
        }
        return proposition.toString();

    }
    private String propositionMastermind(int tailleCombinaison, List<String> colors){
        combinaisonsPossibles = new ArrayList<>();
        StringBuilder motif = new StringBuilder();
        alimentationListeCombinaisons(motif, tailleCombinaison, colors);
        System.out.println("taille list final : " + combinaisonsPossibles.size());
        return null;
    }

    private void alimentationListeCombinaisons(StringBuilder motif, int tailleCombinaison, List<String> colors) {
        if (motif.length() == tailleCombinaison) {
            combinaisonsPossibles.add(motif.toString());
            System.out.println("taille liste : " + combinaisonsPossibles.size());

        } else {
            for (String symbole: colors) {
                motif.append(symbole);
                alimentationListeCombinaisons(motif, tailleCombinaison, colors);
            }
        }
    }


}
