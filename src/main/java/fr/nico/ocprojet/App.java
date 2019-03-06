package fr.nico.ocprojet;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * App est le point d'entrée du programme :
 * prise en charge la configuration du logger (log4J)
 * récupération des paramatres de lancement pour l'option dévellopement (DevMode)
 */
public class App {
    static final Logger logger = LogManager.getLogger(App.class.getName());
    static boolean DEVMODE = false;

    public static void main(String[] args) {
        logger.log(Level.TRACE, "Lancement du main");
        if (args.length > 0) {
            if (args[0].equals("-DevMode")) {
                DEVMODE = true;
            }
        }
        Launcher lanceur = new Launcher();
        lanceur.lanceLeJeu();
    }

}
