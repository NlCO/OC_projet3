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
 * A l'initiailisation, de la classe :
 * <ul>
 *     <li>un lanceur de type {@link Human humain} est crée pour configurer le {@link GameMode mode} de la {@link GamePlay partie} d'un {@link Games jeu} </li>
 *     <li>les paramètres de jeu sont chargés à partir du fichier de config</li>
 *     <li>une lists de 2 joueurs, composée d'un {@link Human humain} et d'un {@link Bot robot}, est créée</li>
 * </ul>
 *
 */
public class Launcher {
    private Human lanceur;
    private GamePlay partie;
    private Games game;
    private GameMode mode;
    private int tailleCombinaison;
    private int nombreEssai;
    private int panelCouleur;
    private List<Player> players = new ArrayList<>();

    public Launcher() {
        App.logger.log(Level.TRACE, "Init launcher");
        lanceur = new Human();
        chargementFichierConfig();
        this.initPlayers();
    }

    /**
     * Methode d'initialisation d'une liste de {@link Player joueurs} : un {@link Human humain} et un {@link Bot robot}
     */
    private void initPlayers() {
        Player joueur = new Human();
        players.add(joueur);
        Player adversaire = new Bot();
        players.add(adversaire);
    }

    /**
     * Methode comprenant le déroulement du programme
     */
    public void lanceLeJeu() {
        do {
            definirJeuALancer();
            definirModeJeu();
            creerPartie();
            do {
                partie.go();
            } while (rejouerPartie());
        } while (true);
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
        this.setGames(lanceur.choixDuJeu());
    }

    /**
     * Methode pour selectionner le mode jeu à lancer parmi les 3 {@link GameMode modes} et attribuer au joeur les roles correspondants
     */
    private void definirModeJeu() {
        this.setMode(lanceur.choixDuMode());
        attributionDesRoles(mode);
    }

    /**
     * Methode pour creer une partie
     */
    private void creerPartie() {
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
        players.get(1).setRoles(mode.getRoleadversaire());
    }

    /**
     * Methode pour demander si le joueur souhaite rejouer une partie avec les même parametres
     *
     * @return vrai ou faux
     */
    public boolean rejouerPartie() {
        //boolean rejoue = false;
        return lanceur.demandeRejouerPartie();
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

}

