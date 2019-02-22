package fr.nico.ocprojet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Cette class a pour objectif de lancer un jeu avec les paramètres choisis ou configurés
 *
 * <ul>
 * <li>Elabore la liste de joueur (joueur et ordinateur)</li>
 * <li>choix du jeu : recherche +/- ou mastermind</li>
 * <li>choix du mode de jeu Challenger, Défenseur et Duel @see GameMode</li>
 * </ul>
 */
public class Launcher {
    private List<Player> players = new ArrayList<>();
    private Games game;
    private GameMode mode;


    public Launcher() {
        this.initPlayers();
    }

    /**
     * Crée une liste de joueurs
     *
     */
    private void initPlayers() {
        Player joueur = new Human();
        players.add(joueur);
        Player adversaire = new Bot();
        players.add(adversaire);
    }

    /**
     * Methode pour lancer un jeu
     */
    public void lanceLeJeu(){
        definirJeuALancer();
        if (game == Games.M) {
            System.out.println("Mastermind en projet");
            System.exit(0);
        }
        definirModeJeu();
        attributionDesRoles(mode);
        if (game == Games.R) {
            GamePlay partie = new Recherche(players, game);
            partie.go();
        } else {
            //new Mastermind(players, game);
        }
    }

    /**
     * Configure le jeu à lancer
     */
    private void definirJeuALancer(){
        this.setGames(players.get(0).choixDuJeu());
    }

    /**
     * Configure le mode jeu à lancer
     */
    private void definirModeJeu(){
        this.setMode(players.get(0).choixDuMode());
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

    private void setMode(GameMode mode) {
        this.mode = mode;
    }

    public Games getGames() {
        return game;
    }

    private void setGames(Games game) {
        this.game = game;
    }

}

