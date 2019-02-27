package fr.nico.ocprojet;

import org.apache.logging.log4j.Level;

import java.util.ArrayList;
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
    }

    @Override
    public boolean combinaisonIsConforme(String combinaison) {
        return false;
    }

    @Override
    public String evaluerProposition(Player joueur, String proposition) {
        return null;
    }

    @Override
    protected void CombinaisonTrouvee(Player joueur) {

    }
}
