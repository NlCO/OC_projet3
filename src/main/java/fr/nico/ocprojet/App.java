package fr.nico.ocprojet;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * App est le point d'entrée du programme
 */
public class App {
    public static void main(String[] args) {
        final Logger logger = Logger.getLogger("App");
        logger.log(Level.WARNING, "youyou");
        Launcher test = new Launcher();
        test.lanceLeJeu();

    }
   // public static void logger(){
   //     final Logger logger = Logger.getLogger("App");
   // }

}
