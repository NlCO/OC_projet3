package fr.nico.ocprojet;

import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/**
 * La class Launcher permet de configurer la partie et de la lancer
 */
public class Launcher {
    private List<Player> players = new ArrayList<>();
    private Games game;
    private GameMode mode;
    private GamePlay partie;


    public Launcher() {
        App.logger.log(Level.TRACE, "Init launcher");
        this.initPlayers();
    }

    /**
     * Crée la liste de joueurs un {@link Human humain} et {@link Bot un non-humain}
     */
    private void initPlayers() {
        Player joueur = new Human();
        players.add(joueur);
        Player adversaire = new Bot();
        players.add(adversaire);
    }

    /**
     * Methode pour lancer une partie
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
     * Methode pour selectionner le jeu à lancer parmi une liste de {@link Games jeux} disponible
     */
    private void definirJeuALancer() {
        this.setGames(players.get(0).choixDuJeu());
    }

    /**
     * Methode pour selectionner le mode jeu à lancer parmi les 3 {@link GameMode modes} et attribuer au joeur les roles correspondants
     */
    private void definirModeJeu() {
        this.setMode(players.get(0).choixDuMode());
        attributionDesRoles(mode);
    }

    /**
     * Methode pour creer une partie
     */
    private void creerPartie() {
        if (game == Games.R) {
            partie = new Recherche(players);
        } else {
            System.out.println("Mastermind en projet");
            System.exit(0);
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
        return players.get(0).demandeRejouerPartie();
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

