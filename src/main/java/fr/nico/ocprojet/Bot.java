package fr.nico.ocprojet;

import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * La classe Bot décrit la classe {@link Player Player} pour un joueur non-humain et gére son "IA"
 */
public class Bot extends Player {
    private Random random;
    private List<String> combinaisonsPossibles;

    public Bot() {
        App.logger.log(Level.TRACE, "Création joueur non-humain");
        super.setName("Bot le bot");
        random = new Random();
    }

    @Override
    public String genereUneCombinaison(int tailleCombinaison, List<String> setDeValeurs) {
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
            proposition = propositionMastermind(tailleCombinaison, colors, historique);
        }
        return proposition;
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

    private String propositionMastermind(int tailleCombinaison, List<String> colors, List<String[]> historique) {
        String proposition;
        if (historique.size() > 0) {
            constitutionListeCombinaisonPossible(tailleCombinaison, colors, historique);
            proposition = combinaisonsPossibles.get(random.nextInt(combinaisonsPossibles.size()));
        } else {
            proposition = genereUneCombinaison(tailleCombinaison, colors);
            App.logger.log(Level.DEBUG, "Bot - Mastermind - première proposition : " + proposition);
        }
        return proposition;
    }

    public void constitutionListeCombinaisonPossible(int tailleCombinaison, List<String> colors, List<String[]> historique) {
        if (historique.size() > 1) {
            miseAJourCombinaisonsPossibles(historique.get(historique.size() - 1), tailleCombinaison);
        } else {
            combinaisonsPossibles = new ArrayList<>();
            StringBuilder motif = new StringBuilder();
            alimentationListeCombinaisons(motif, tailleCombinaison, colors, historique.get(0));
        }
        App.logger.log(Level.DEBUG, "Nombre de combinaisons possibles : " + combinaisonsPossibles.size());
    }

    /**
     * Methode recursive permettant la constitution des combinaisons possibles à partir d'un motif
     *
     * @param motif             bout de combinaison
     * @param tailleCombinaison nombre de caractères attendus dans la combinaisons conditionnant la sortie de la boucle
     * @param colors            elements pouvants être présents dans la combinaison
     */
    public void alimentationListeCombinaisons(StringBuilder motif, int tailleCombinaison, List<String> colors, String[] dernierResultat) {
        if (motif.length() == tailleCombinaison) {
            if (validationCombinaison(dernierResultat[0], motif.toString(), tailleCombinaison).equals(dernierResultat[1])) {
                combinaisonsPossibles.add(motif.toString());
            }
            App.logger.log(Level.DEBUG, "Nombre de combinaisons présentes dans la liste : " + combinaisonsPossibles.size());
            return;
        } else {
            for (String symbole : colors) {
                motif.append(symbole);
                alimentationListeCombinaisons(motif, tailleCombinaison, colors, dernierResultat);
                motif.deleteCharAt(motif.length() - 1);
                App.logger.log(Level.DEBUG, "Motif suite appel recursif : " + motif);
            }
        }

    }

    public String validationCombinaison(String derniereCombinaison, String motifCalcule, int tailleCombinaison) {
        List<String> combinaisonAEstimee = new ArrayList<>(Arrays.asList(motifCalcule.split("")));
        List<String> combinaisonReference = new ArrayList<>(Arrays.asList(derniereCombinaison.split("")));
        int correct = nombreBienPlace(combinaisonAEstimee, combinaisonReference, tailleCombinaison);
        int present = nombrePresent(combinaisonAEstimee, combinaisonReference);
        return String.format("%d,%d", correct, present);
    }

    public int nombreBienPlace(List<String> combinaisonProposee, List<String> combinaisonATrouver, int tailleCombinaison) {
        int correct = 0;
        int rang = 0;
        for (int i = 0; i < tailleCombinaison; i++) {
            if (combinaisonATrouver.get(rang).equals(combinaisonProposee.get(rang))) {
                combinaisonATrouver.remove(rang);
                combinaisonProposee.remove(rang);
                correct++;
            } else {
                rang++;
            }
        }
        return correct;
    }

    public int nombrePresent(List<String> combinaisonProposee, List<String> combinaisonATrouver) {
        int present = 0;
        for (String s : combinaisonProposee) {
            if (combinaisonATrouver.contains(s)) {
                combinaisonATrouver.remove(s);
                present++;
            }
        }
        return present;
    }

    private void miseAJourCombinaisonsPossibles(String[] dernierResultat, int tailleCombinaison) {
        List<String> combinaisonsARetirer = new ArrayList<>();
        for (String combinaison : combinaisonsPossibles) {
            if (!validationCombinaison(dernierResultat[0], combinaison, tailleCombinaison).equals(dernierResultat[1])) {
                combinaisonsARetirer.add(combinaison);
            }
        }
        for (String invalide : combinaisonsARetirer) {
            combinaisonsPossibles.remove(invalide);
        }
    }
}
