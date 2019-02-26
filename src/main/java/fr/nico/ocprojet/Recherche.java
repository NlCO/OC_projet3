package fr.nico.ocprojet;

import org.apache.logging.log4j.Level;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Cette classe contient les paticularités du jeu Recherhce +/-
 */
public class Recherche extends GamePlay {

    public Recherche(List<Player> players) {
        App.logger.log(Level.TRACE, "Lancement d'une partie Recherche +/-");
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
        System.out.println(joueur.getName() + " proposition : " + proposition + " -> Résultat : " + resultat);
        return resultat.toString();
    }

    @Override
    protected void CombinaisonTrouvee(Player joueur) {
        String dernierResultat = playersPropostions.get(joueur).get(playersPropostions.get(joueur).size() - 1)[1];
        joueur.setWinner(dernierResultat.matches("=+"));
    }

    @Override
    protected void chargementFichierConfig() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/config.properties"));
        } catch (IOException e) {
            System.out.println("Veuillez vous assurez qu'un fichier config.properties soit présent ");
        }
        try {
            tailleCombinaison = Integer.parseInt(properties.getProperty("tailleCombinaison"));
        } catch (NumberFormatException e) {
            System.out.println("Valeur dans le fichier de configuration incorrecte : " + properties.getProperty("tailleCombinaison"));
            System.out.println("Valeur par défaut prise en compte : " + tailleCombinaison);
        }
        try {
            nombreEssai = Integer.parseInt(properties.getProperty("nombreEssais"));
        } catch (NumberFormatException e) {
            System.out.println("Valeur dans le fichier de configuration incorrecte : " + properties.getProperty("nombreEssais"));
            System.out.println("Valeur par défaut prise en compte : " + nombreEssai);
        }
    }
}
