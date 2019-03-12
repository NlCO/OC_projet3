package fr.nico.ocprojet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static fr.nico.ocprojet.App.logger;

/**
 * La classe Bot décrit la classe {@link Player Player} pour un joueur non-humain et gére son "IA"
 */
public class Bot extends Player {
    private Random random;
    private List<String> combinaisonsPossibles;
    private StringBuilder borneMin;
    private StringBuilder borneMax;

    public Bot() {
        logger.trace("Création joueur non-humain");
        super.setName("l'ordinateur");
        random = new Random();
    }

    @Override
    public String genereUneCombinaison(int tailleCombinaison, List<String> setDeValeurs) {
        StringBuilder combinaison = new StringBuilder();
        for (int i = 0; i < tailleCombinaison; i++) {
            combinaison.append(setDeValeurs.get(random.nextInt(setDeValeurs.size())));
        }
        logger.debug("Combinaison générée : " + combinaison.toString());
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

    /**
     * Methode retournant un proposition pour le jeu Recherche +/- en fonction de l'historique des proposition
     *
     * @param tailleCombinaison longueur de la combinaison attendue
     * @param historique        liste des précedentes proposition
     * @return une combinaison
     */
    private String propositionRecherche(int tailleCombinaison, List<String[]> historique) {
        if (historique.size() > 0) {
            miseAJourBorneRecherche(historique.get(historique.size() - 1));
        } else {
            initialisationBorneRecherche(tailleCombinaison);
        }
        logger.debug("Tentative : " + historique.size() + "- Etat des borne Min  : " + borneMin.toString() + " / Max : " + borneMax.toString());
        StringBuilder proposition = new StringBuilder();
        for (int i = 0; i < tailleCombinaison; i++) {
            int biais = ((borneMax.toString().charAt(i) - '0') > 5) ? 1 : 0;
            int moyenne = ((borneMax.toString().charAt(i) - '0') + (borneMin.toString().charAt(i) - '0') + biais) / 2;
            proposition.append(moyenne);
        }
        logger.debug("proposition : " + proposition.toString());
        return proposition.toString();
    }

    /**
     * Methode permettant l'initialisation des bornes Min et Max pour le jeu Recherche +/-
     *
     * @param tailleCombinaison longueur de la combinaison atendue
     */
    private void initialisationBorneRecherche(int tailleCombinaison) {
        borneMin = new StringBuilder();
        borneMax = new StringBuilder();
        for (int i = 0; i < tailleCombinaison; i++) {
            borneMin.append(0);
            borneMax.append(9);
        }
    }

    /**
     * Methode permettant la mise à jour des bornes Min et Max pou le jeu Recherche +/-
     *
     * @param dernierTentative tableau contenant la dernière proposition et son évaluation
     */
    private void miseAJourBorneRecherche(String[] dernierTentative) {
        int position = 0;
        for (String c : dernierTentative[1].split("")) {
            switch (c) {
                case "=":
                    borneMin.setCharAt(position, dernierTentative[0].charAt(position));
                    borneMax.setCharAt(position, dernierTentative[0].charAt(position));
                    break;
                case "-":
                    borneMax.setCharAt(position, dernierTentative[0].charAt(position));
                    break;
                case "+":
                    borneMin.setCharAt(position, dernierTentative[0].charAt(position));
                    break;
            }
            logger.debug("ajustement des bornes Min/Max : " + borneMin.toString() + "/" + borneMax.toString());
            position++;
        }
    }

    /**
     * Methode retournant un proposition pour le jeu Mastermind en fonction de l'historique des proposition
     *
     * @param tailleCombinaison longueur de la combinaison attendue
     * @param symboles          liste des symboles permis
     * @param historique        liste des précedentes proposition
     * @return une combinaison
     */
    private String propositionMastermind(int tailleCombinaison, List<String> symboles, List<String[]> historique) {
        String proposition;
        if (historique.size() > 0) {
            long startTime = System.currentTimeMillis();
            constitutionListeCombinaisonPossible(tailleCombinaison, symboles, historique);
            long endTime = System.currentTimeMillis();
            System.out.println("Temps de calcul en ms : " + (endTime - startTime));
            proposition = combinaisonsPossibles.get(random.nextInt(combinaisonsPossibles.size()));
        } else {
            proposition = genereUneCombinaison(tailleCombinaison, symboles);
            logger.debug("Bot - Mastermind - première proposition : " + proposition);
        }
        return proposition;
    }

    /**
     * Methode permettant la mise en place d'un liste de combinaison ppossibles à jour
     *
     * @param tailleCombinaison longueur de la combinaison attendue
     * @param symboles          liste des symboles permis
     * @param historique        listes des précédentes propositions
     */
    public void constitutionListeCombinaisonPossible(int tailleCombinaison, List<String> symboles, List<String[]> historique) {
        if (historique.size() > 1) {
            miseAJourCombinaisonsPossibles(historique.get(historique.size() - 1));
        } else {
            combinaisonsPossibles = new ArrayList<>();
            StringBuilder motif = new StringBuilder();
            alimentationListeCombinaisons(motif, tailleCombinaison, symboles, historique.get(0));
        }
        logger.debug("Nombre de combinaisons possibles : " + combinaisonsPossibles.size());
    }

    /**
     * Methode recursive permettant la constitution des combinaisons possibles à partir d'un motif
     *
     * @param motif             bout de combinaison
     * @param tailleCombinaison nombre de caractères attendus dans la combinaisons conditionnant la sortie de la boucle
     * @param symboles          elements pouvants être présents dans la combinaison
     */
    public void alimentationListeCombinaisons(StringBuilder motif, int tailleCombinaison, List<String> symboles, String[] dernierResultat) {
        if (motif.length() == tailleCombinaison) {
            if (combinaisonEstPossible(dernierResultat, motif.toString())) {
                combinaisonsPossibles.add(motif.toString());
            }
            logger.debug("Nombre de combinaisons présentes dans la liste : " + combinaisonsPossibles.size());
        } else {
            for (String symbole : symboles) {
                motif.append(symbole);
                alimentationListeCombinaisons(motif, tailleCombinaison, symboles, dernierResultat);
                motif.deleteCharAt(motif.length() - 1);
                logger.debug("Motif suite appel recursif : " + motif);
            }
        }

    }

    /**
     * Methode permettant de valider que le motif calculé retourne le même resultat d'evaluation que le resultat d'evaluation pour la dernière proposition
     *
     * @param derniereResultat tableau contenant la dernière proposition et son résultat
     * @param motifCalcule     motif issu de la boucle recurssive de liste de l'ensemble des combinaisons
     * @return vrai si le resultat de l'evaluation de combinaison calculée vis à vis de la dernière proposition est egal au resultat de celle-ci avec la combinaison à trouve
     */
    public boolean combinaisonEstPossible(String[] derniereResultat, String motifCalcule) {
        List<String> combinaisonAEstimee = new ArrayList<>(Arrays.asList(motifCalcule.split("")));
        List<String> combinaisonReference = new ArrayList<>(Arrays.asList(derniereResultat[0].split("")));
        int correct = nombreSymboleCorrect(combinaisonAEstimee, combinaisonReference);
        int present = nombreSymbolePresent(combinaisonAEstimee, combinaisonReference);
        return derniereResultat[1].equals(String.format("%d,%d", correct, present));
    }

    /**
     * Methode pour evaluer le nombre de symbole à la bonne place
     *
     * @param combinaisonAEstimee  combinaison à estimer
     * @param combinaisonReference combinaison de référence
     * @return le nombre de symbole à la bonne place
     */
    public int nombreSymboleCorrect(List<String> combinaisonAEstimee, List<String> combinaisonReference) {
        int correct = 0;
        int rang = 0;
        int tailleCombinaison = combinaisonAEstimee.size();
        for (int i = 0; i < tailleCombinaison; i++) {
            if (combinaisonReference.get(rang).equals(combinaisonAEstimee.get(rang))) {
                combinaisonReference.remove(rang);
                combinaisonAEstimee.remove(rang);
                correct++;
            } else {
                rang++;
            }
        }
        return correct;
    }

    /**
     * Methode pour evaluer le nombre de symbole présent mais mal placé
     *
     * @param combinaisonAEstimee  combinaison à évaluer
     * @param combinaisonReference combinaison de référence
     * @return nombre de symbole présent mal placé
     */
    public int nombreSymbolePresent(List<String> combinaisonAEstimee, List<String> combinaisonReference) {
        int present = 0;
        for (String s : combinaisonAEstimee) {
            if (combinaisonReference.contains(s)) {
                combinaisonReference.remove(s);
                present++;
            }
        }
        return present;
    }

    /**
     * Methode permettant de retirer les combinaisons invalides de la liste des combinaisons restantes à partir du dernier résultat
     *
     * @param dernierResultat dernier resultat obtenu
     */
    private void miseAJourCombinaisonsPossibles(String[] dernierResultat) {
        List<String> combinaisonsRestante = new ArrayList<>();
        System.out.println("nombre de combinaison à analyser " + combinaisonsPossibles.size());
        for (String combinaison : combinaisonsPossibles) {
            if (combinaisonEstPossible(dernierResultat, combinaison)) {
                combinaisonsRestante.add(combinaison);
            }
        }
        combinaisonsPossibles = combinaisonsRestante;
    }

    public List<String> getCombinaisonsPossibles() {
        return combinaisonsPossibles;
    }

}
