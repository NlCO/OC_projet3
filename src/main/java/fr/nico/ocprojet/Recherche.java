package fr.nico.ocprojet;

import java.util.List;

/**
 * Cette classe contient les paticularités du jeu Recherhce +/-
 */
public class Recherche extends GamePlay {

    public Recherche(List<Player> players) {
        super.players = players;
        //super.game = game;
    }

    @Override
    public boolean combinaisonIsConforme(String combinaison) {
        String pattern = "\\d{" + tailleCombinaison + "}";
        return combinaison.matches(pattern);
    }

    @Override
    protected String evaluerProposition(Player joueur, String proposition) {
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
        return resultat.toString();
    }

    @Override
    protected void CombinaisonTrouvee(Player joueur) {
        String dernierResultat = playersPropostions.get(joueur).get(playersPropostions.get(joueur).size() - 1)[1];
        joueur.setWinner(dernierResultat.matches("=+"));
    }
}
