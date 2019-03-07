package fr.nico.ocprojet;

import java.util.ArrayList;
import java.util.List;

import static fr.nico.ocprojet.App.logger;

/**
 * Cette classe contient les paticularités du jeu Recherhce +/-
 */
public class Recherche extends GamePlay {

    public Recherche(List<Player> players, int tailleCombinaison, int nombreEssai) {
        logger.trace("Lancement d'une partie Recherche +/-");
        super.players = players;
        super.jeu = Games.R;
        super.tailleCombinaison = tailleCombinaison;
        super.nombreEssai = nombreEssai;
        super.setDeValeurs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            setDeValeurs.add("" + i + "");
        }
    }

    @Override
    public boolean combinaisonEstConforme(String combinaison) {
        String pattern = "\\d{" + tailleCombinaison + "}";
        return combinaison.matches(pattern);
    }

    @Override
    public String evaluerProposition(String code, String proposition) {
        StringBuilder resultat = new StringBuilder();
        String[] combiATrouver = code.split("");
        String[] propositionArray = proposition.split("");
        for (int i = 0; i < tailleCombinaison; i++) {
            if (Integer.parseInt(propositionArray[i]) == Integer.parseInt(combiATrouver[i])) {
                resultat.append("=");
            } else {
                resultat.append((Integer.parseInt(propositionArray[i]) > Integer.parseInt(combiATrouver[i])) ? "-" : "+");
            }
        }
        return resultat.toString();
    }

    @Override
    protected void afficheResultat(Player joueur, String[] resultatTour) {
        System.out.println(" proposition de " + joueur.getName() + " : " + resultatTour[0] + " -> Résultat : " + resultatTour[1]);
    }

    @Override
    protected void combinaisonTrouvee(Player joueur) {
        String dernierResultat = playersPropostions.get(joueur).get(playersPropostions.get(joueur).size() - 1)[1];
        joueur.setWinner(dernierResultat.matches("=+"));
    }

}
