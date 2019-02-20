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
    private Game game;
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
        definirModeJeu();
    }

    /**
     * Configure le jeu à lancer
     */
    private void definirJeuALancer(){
        this.setGame(players.get(0).choixDuJeu());
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
     * @param players liste des joueurs
     * @param mode mode de jeu
     */
    private void attributionDesRoles(List<Player> players, GameMode mode) {
        players.get(0).setRoles(mode);
        players.get(1).setRoles(mode.valueOf(mode.getRoleadversaire()));
    }
    /**
     * Retourne la liste des jeux
     *
     * @return Liste de jeux
     */
    public List<Game> jeuxDispos() {
        return Arrays.asList(Game.values());
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

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

}

