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
        super.panelCouleur = panelCouleur;
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
    public boolean combinaisonIsConforme(String combinaison) {
        String pattern = "([" + setDeValeurs.get(0) + "-" + setDeValeurs.get(setDeValeurs.size() - 1) + "]){" + tailleCombinaison + "}";
        App.logger.log(Level.DEBUG, "pattern du regex : " + pattern + " généré à partir de la liste : " + setDeValeurs + " et de longueur " + tailleCombinaison);
        return combinaison.matches(pattern);
    }

    @Override
    public String evaluerProposition(String code, String proposition) {
        List<String> combinaisonProposee = new ArrayList<>(Arrays.asList(proposition.split("")));
        List<String> combinaisonATrouver = new ArrayList<>(Arrays.asList(code.split("")));
        App.logger.log(Level.DEBUG, "Evaluations MM : proposée : " + combinaisonProposee + " - A trouver : " + combinaisonATrouver);
        int correct = nombreBienPlace(combinaisonProposee, combinaisonATrouver, tailleCombinaison);
        int present = nombrePresent(combinaisonProposee, combinaisonATrouver);
        int[] bilanNb = {correct, present};
        String bilan = "";
        if (bilanNb[0] == 0 && bilanNb[1] == 0) {
            bilan = "aucuns symboles présents";
        } else {
            if (bilanNb[1] > 0) {
                bilan = String.format("%d présent%s", bilanNb[1], (bilanNb[1] > 1) ? "s" : "");
            }
            if (bilanNb[0] > 0) {
                bilan += String.format("%s%d bien placé%s", (bilanNb[1] > 0) ? ", " : "", bilanNb[0], (bilanNb[0] > 1) ? "s" : "");
            }

        }
        //System.out.println(joueur.getName() + " proposition : " + proposition + " -> Résultat : " + bilan);
        System.out.println(" proposition : " + proposition + " -> Résultat : " + bilan);
        return String.format("%d,%d", bilanNb[0], bilanNb[1]);
    }

    public static int nombreBienPlace(List<String> combinaisonProposee, List<String> combinaisonATrouver, int tailleCombinaison) {
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

    public static int nombrePresent(List<String> combinaisonProposee, List<String> combinaisonATrouver) {
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
    protected void combinaisonTrouvee(Player joueur) {
        String dernierResultat = playersPropostions.get(joueur).get(playersPropostions.get(joueur).size() - 1)[1];
        joueur.setWinner(dernierResultat.split(",")[0].equals(tailleCombinaison.toString()));
    }
}
