package fr.nico.ocprojet;

import java.util.*;

/**
 * GamePlay est la classe contenant le gameplay commun au jeu
 *
 * Un jeu a besoin d'une liste de joueur ayant été paramétrée @see Launcher @see App
 */
public abstract class GamePlay {
    protected List<Player> players;
    protected Games game;
    protected int tailleCombinaison = 4;
    protected int nombreEssai = 10;
    protected Map<Player, String> combinaisons;
    protected Map<Player, List<String>> playersPropostions;


    /**
     * Permet d'initiliaser la Partie
     */
    protected void initialiserLaPartie(){
        combinaisons = new Hashtable<>();
        playersPropostions = new Hashtable<>();
        for (Player joueur: players) {
            if (joueur.isCodeur()) {
                combinaisons.put(getAdversaire(joueur),demandeDeCombinaison(joueur));
                System.out.println("(" + joueur.getName() + " a choisi la combinaison " + combinaisons.get(getAdversaire(joueur)) + ")");
            }
            if (joueur.isDecodeur()) {

                List<String> propositions = new ArrayList<>();
                playersPropostions.put(joueur, propositions);
            }
        }
    }

    /**
     * Contient le déroulement du jeu
     */
    protected void jouerLaPartie(){
        Boolean trouve = false;
        int tour = 0;
        do {
            ++tour;
            for (Player joueur: players) {
                if (joueur.isDecodeur()){
                    faireUneTentative(joueur);
                }
            }
        } while (!trouve && tour < nombreEssai);
    }



    /**
     * Methode pour la demande de proposition de combinaison
     * @param joueur joueur qui soumets la combinaison
     * @return la combinaison
     */
    private String demandeDeCombinaison(Player joueur) {
        String proposition;
        do{
            proposition = joueur.genereUneCombinaison(tailleCombinaison);
            if (!combinaisonIsConforme(proposition)) {
                System.out.println("saisie non conforme");
            }
        } while (!combinaisonIsConforme(proposition));
        return  proposition;
    }

    /**
     * Methode pour checker la conformité de la saisie de la combinaison
     *
     * @param combinaison combinaison à tester
     * @return vrai ou faux
     */
    public abstract boolean combinaisonIsConforme(String combinaison);

    /**
     * Methode pour obtenir l'adversaire d'un joueur (fonctionne seulement pour un jeu 2 joueurs)
     * se base sur la liste de l'intance
     *
     * @param joueur le joueur
     * @return l'adversaire
     */
    private Player getAdversaire(Player joueur) {
        return players.get(Math.abs(players.indexOf(joueur) - 1));
    }

    /**
     * Permet la demande d'une tentative de décodage et d'annalyser son resulats
     *
     * @param joueur joueur dont c'est le tour de jeu
     */
    private void faireUneTentative(Player joueur){
        List<String> propositions = playersPropostions.get(joueur);
        String proposition = demandeDeCombinaison(joueur);
        System.out.println(playersPropostions.get(joueur));
        String resultat = evaluerProposition(joueur, proposition);
        System.out.println(resultat);
        propositions.add(proposition);
        playersPropostions.put(joueur, propositions);

    }

    /**
     * Methode pour l'evaluation de la proposition
     *
     * @param joueur
     * @param proposition
     * @return
     */
    protected abstract String evaluerProposition(Player joueur, String proposition);

}
