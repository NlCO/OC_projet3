package fr.nico.ocprojet;

import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Cette classe contient les paticularités du jeu Mastermind
 */
public class Mastermind extends GamePlay {


    public Mastermind(List<Player> players, int tailleCombinaison, int nombreEssai, int panelCouleur) {
        App.logger.log(Level.TRACE, "Lancement d'une partie Mastermind");
        super.players = players;
        super.jeu = Games.M;
        super.tailleCombinaison = tailleCombinaison;
        super.nombreEssai = nombreEssai;
        super.setDeValeurs = creerListeValeurs(panelCouleur);
    }

    /**
     * Methode pour générer la liste des symboles possibles d'une combinaison
     *
     * @param panelCouleur nombre de symboles possibles
     * @return la liste des symboles
     */
    private List<String> creerListeValeurs(int panelCouleur) {
        List<MastermindSymbole> allValeurs = Arrays.asList(MastermindSymbole.values());
        List<String> panelSet = new ArrayList<>();
        for (int i = 0; i < panelCouleur; i++) {
            panelSet.add(allValeurs.get(i).toString());
        }
        return panelSet;
    }

    @Override
    public boolean combinaisonEstConforme(String combinaison) {
        String pattern = "([" + setDeValeurs.get(0) + "-" + setDeValeurs.get(setDeValeurs.size() - 1) + "]){" + tailleCombinaison + "}";
        App.logger.log(Level.DEBUG, "pattern du regex : " + pattern + " généré à partir de la liste : " + setDeValeurs + " et de longueur " + tailleCombinaison);
        return combinaison.matches(pattern);
    }

    @Override
    public String evaluerProposition(String code, String proposition) {
        List<String> combinaisonProposee = new ArrayList<>(Arrays.asList(proposition.split("")));
        List<String> combinaisonATrouver = new ArrayList<>(Arrays.asList(code.split("")));
        App.logger.log(Level.DEBUG, "Evaluation de combinaison MM : proposée : " + combinaisonProposee + " - A trouver : " + combinaisonATrouver);
        int symboleCorrect = nombreBienPlace(combinaisonProposee, combinaisonATrouver, tailleCombinaison);
        int symbolePresent = nombrePresent(combinaisonProposee, combinaisonATrouver);
        return String.format("%d,%d", symboleCorrect, symbolePresent);

    }


    /**
     * Methode pour evaluer le nombre de symbole à la bonne place
     * @param combinaisonProposee combinaison à estimer
     * @param combinaisonATrouver combinaison à trouver
     * @param tailleCombinaison longueur de la combinaison
     * @return le nombre de symbole à la bonne place
     */
    private int nombreBienPlace(List<String> combinaisonProposee, List<String> combinaisonATrouver, int tailleCombinaison) {
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

    /**
     * Methode pour evaluer le nombre de symbole présent mais mal placé
     * @param combinaisonProposee combinaison à évaluer
     * @param combinaisonATrouver combinaison à trouver
     * @return nombre de symbole présent mal placé
     */
    private int nombrePresent(List<String> combinaisonProposee, List<String> combinaisonATrouver) {
        int present = 0;
        for (String s : combinaisonProposee) {
            if (combinaisonATrouver.contains(s)) {
                combinaisonATrouver.remove(s);
                present++;
            }
        }
        return present;
    }

    @Override
    protected void afficheResultat(Player joueur, String[] resultatTour) {
        String[] resultatSymbole = resultatTour[1].split(",");
        String bilanResultat = bilanEvaluation(Integer.parseInt(resultatSymbole[0]),Integer.parseInt(resultatSymbole[1]));
        System.out.println(" proposition de " + joueur.getName() + " : " + resultatTour[0] + " -> Résultat : " + bilanResultat);
    }

    /**
     * Methode permettant de retourner le resultat d'un proposition sous forme de texte
     * @param symboleCorrect nombre de symboles biens placés
     * @param symbolePresent nombre de symboles présents
     * @return le resultat sous forme de texte
     */
    private String bilanEvaluation(int symboleCorrect, int symbolePresent){
        String bilan = "";
        if (symboleCorrect == 0 && symbolePresent == 0) {
            bilan = "aucun symbole présent";
        } else {
            if (symbolePresent > 0) {
                bilan = String.format("%d présent%s", symbolePresent, (symbolePresent > 1) ? "s" : "");
            }
            if (symboleCorrect > 0) {
                bilan += String.format("%s%d bien%s placé%s", (symbolePresent > 0) ? ", " : "", symboleCorrect, (symboleCorrect > 1) ? "s" : "",(symboleCorrect > 1) ? "s" : "");
            }
        }
        return bilan;
    }


    @Override
    protected void combinaisonTrouvee(Player joueur) {
        String dernierResultat = playersPropostions.get(joueur).get(playersPropostions.get(joueur).size() - 1)[1];
        joueur.setWinner(dernierResultat.split(",")[0].equals(tailleCombinaison.toString()));
    }
}
