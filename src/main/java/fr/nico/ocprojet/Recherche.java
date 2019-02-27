package fr.nico.ocprojet;

import org.apache.logging.log4j.Level;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Cette classe contient les paticularités du jeu Recherhce +/-
 */
public class Recherche extends GamePlay {

    public Recherche(List<Player> players, int tailleCombinaison, int nombreEssai ) {
        App.logger.log(Level.TRACE, "Lancement d'une partie Recherche +/-");
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
    public boolean combinaisonIsConforme(String combinaison) {
        String pattern = "\\d{" + tailleCombinaison + "}";
        return combinaison.matches(pattern);
    }

    @Override
    public String evaluerProposition(Player joueur, String proposition) {
        StringBuilder resultat = new StringBuilder();
        String[] combiATrouver = combinaisons.get(joueur).split("");
        String[] propositionArray = proposition.split("");
        for (int i = 0; i < tailleCombinaison; i++) {
            if (Integer.parseInt(propositionArray[i]) == Integer.parseInt(combiATrouver[i])) {
                resultat.append("=");
            } else {
                resultat.append((Integer.parseInt(propositionArray[i]) > Integer.parseInt(combiATrouver[i])) ? "-" : "+");
            }
        }
        System.out.println(joueur.getName() + " proposition : " + proposition + " -> Résultat : " + resultat);
        return resultat.toString();
    }

    @Override
    protected void CombinaisonTrouvee(Player joueur) {
        String dernierResultat = playersPropostions.get(joueur).get(playersPropostions.get(joueur).size() - 1)[1];
        joueur.setWinner(dernierResultat.matches("=+"));
    }

}
