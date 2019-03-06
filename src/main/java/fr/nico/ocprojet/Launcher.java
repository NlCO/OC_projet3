package fr.nico.ocprojet;

import org.apache.logging.log4j.Level;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;


/**
 * La class Launcher permet de configurer la partie afin de la lancer.
 * A l'initiailisation, de la classe : les paramètres de jeu sont chargés à partir du fichier de config
 */
public class Launcher {
    private GamePlay partie;
    private Games game;
    private GameMode mode;
    private int tailleCombinaison;
    private int nombreEssai;
    private int panelCouleur;
    private List<Player> players;
    private Human joueur;

    public Launcher() {
        App.logger.log(Level.TRACE, "Init launcher");
        chargementFichierConfig();
    }

    /**
     * Methode d'initialisation d'une liste de {@link Player joueurs} : {@link Human l'utilisatur} et un adversaire {@link Bot machine}
     */
    public void initPlayers() {
        players = new ArrayList<>();
        joueur = new Human();
        players.add(joueur);
        Bot adversaire = new Bot();
        players.add(adversaire);
    }

    /**
     * Methode comprenant le déroulement du jeu
     */
    public void lanceLeJeu() {
        initPlayers();
        String rejouer;
        do {
            definirJeuALancer();
            definirModeJeu();
            attributionDesRoles(mode);
            creerPartie();
            do {
                partie.deroulement();
                rejouer = rejouerPartie();
            } while (rejouer.equals("R"));
        } while (rejouer.equals("N"));
    }

    /**
     * Methode pour récupérer les paramètres du fichier de configuration.properties
     */
    protected void chargementFichierConfig() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/config.properties"));
            tailleCombinaison = Integer.parseInt(properties.getProperty("tailleCombinaison"));
            nombreEssai = Integer.parseInt(properties.getProperty("nombreEssais"));
            panelCouleur = Integer.parseInt(properties.getProperty("panelCouleur"));
            if (panelCouleur < 4 || panelCouleur > 10) {
                throw new java.lang.NumberFormatException("out of range");
            }
        } catch (IOException e) {
            System.out.println("Veuillez vous assurez qu'un fichier config.properties soit présent ");
            System.exit(2);
        } catch (NumberFormatException e) {
            App.logger.log(Level.FATAL, "Le fichier config.properties contient des valeurs corrompues");
            System.out.println("Valeur dans le fichier de configuration incorrecte : merci de vérifier son contenu");
            System.exit(1);
        }
    }

    /**
     * Methode pour selectionner le jeu à lancer parmi une liste de {@link Games jeux} disponible
     */
    private void definirJeuALancer() {
        for (Games games : Games.values()) {
            System.out.println(games.getAbbreviation() + " -> " + games.toString());
        }
        this.setGames(joueur.choixDuJeu());
    }

    /**
     * Methode pour selectionner le mode jeu à lancer parmi les 3 {@link GameMode modes} et attribuer au joeur les roles correspondants
     */
    private void definirModeJeu() {
        for (GameMode mode : GameMode.values()) {
            System.out.println(mode.getCode() + " -> " + mode.toString());
        }
        this.setMode(joueur.choixDuMode());

    }

    /**
     * Methode pour creer une partie
     */
    public void creerPartie() {
        if (game == Games.R) {
            partie = new Recherche(players, tailleCombinaison, nombreEssai);
        } else {
            partie = new Mastermind(players, tailleCombinaison, nombreEssai, panelCouleur);
        }
    }

    /**
     * Attribue aux joueurs leurs roles respectifs en fonction du mode de jeu
     *
     * @param mode mode de jeu
     */
    public void attributionDesRoles(GameMode mode) {
        players.get(0).setRoles(mode);
        players.get(1).setRoles(mode.getRoleAdversaire());

    }

    /**
     * Methode pour demander si le joueur souhaite rejouer une partie avec les même parametres
     *
     * @return vrai ou faux
     */
    public String rejouerPartie() {
        System.out.println("Voulez vous :");
        System.out.println("R -> Rejouer la partie avec les mêmes paramètres");
        System.out.println("N -> Faire une nouvelle partie avec d'autres choix");
        System.out.println("Q -> Quitter le jeu");
        return joueur.demandeRejouerPartie();
    }


    /**
     * Retourne la liste des jeux
     *
     * @return Liste de jeux
     */
    public List<Games> jeuxDispos() {
        return Arrays.asList(Games.values());
    }

    /**
     * Retourne la liste des modes de jeux
     *
     * @return liste des modes de jeux @see GameMode
     */
    public List<GameMode> gameModes() {
        return Arrays.asList(GameMode.values());
    }

    public List<Player> getPlayers() {
        return players;
    }

    public GameMode getMode() {
        return mode;
    }

    public void setMode(GameMode mode) {
        this.mode = mode;
    }

    public Games getGames() {
        return game;
    }

    public void setGames(Games game) {
        this.game = game;
    }

    public GamePlay getPartie() {
        return partie;
    }
}

