package fr.nico.ocprojet;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * App est le point d'entrée du programme
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

        Launcher start = new Launcher();
        start.lanceLeJeu();
    }

}
