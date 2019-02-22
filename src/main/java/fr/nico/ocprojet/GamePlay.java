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
    protected Map<Player, List<String[]>> playersPropostions;
    protected String[] tourResultat;


    /**
     * Lancement
     *
     */
    public void go() {
        initialiserLaPartie();
        jouerLaPartie();
        BilanDeLaPartie();
    }

     /**
     * Permet d'initiliaser la Partie
     */
    protected void initialiserLaPartie(){
        combinaisons = new Hashtable<>();
        playersPropostions = new Hashtable<>();
        for (Player joueur: players) {
            if (joueur.isCodeur()) {
                combinaisons.put(getAdversaire(joueur),demandeDeCombinaison(joueur));
                //todo sortir le display et voir le mode dev
                System.out.println("(" + joueur.getName() + " a choisi la combinaison " + combinaisons.get(getAdversaire(joueur)) + ")");
            }
            if (joueur.isDecodeur()) {
                List<String[]> propositions = new ArrayList<>();
                playersPropostions.put(joueur, propositions);
            }
        }
    }

    /**
     * Contient le déroulement du jeu
     */
    protected void jouerLaPartie(){
        int tour = 0;
        do {
            ++tour;
            for (Player joueur: players) {
                if (joueur.isDecodeur()){
                    faireUneTentative(joueur);
                    CombinaisonTrouvee(joueur);
                }
            }
        } while (!(players.get(0).isWinner() || players.get(1).isWinner()) && tour < nombreEssai);
    }

    /**
     * Resolution de la partie
     */
    protected void BilanDeLaPartie(){
        if (players.get(0).isWinner() && players.get(1).isWinner()){
            System.out.println("Match nul - vous avez trouvé tous les 2 en " + playersPropostions.get(players.get(0)).size() + "coups.");
        } else {
            for (Player joueur: players) {
                if (joueur.isDecodeur() && joueur.isWinner()) {
                    System.out.println("Bravo " + joueur.getName() + " :) !!!!  - vous avez décodé la combinaison en " + playersPropostions.get(joueur).size() + " coups.");
                } else if (joueur.isDecodeur() && !joueur.isWinner()) {
                    System.out.println("Perdu " + joueur.getName() + " :( !!!!  - vous n'êtes pas parvenu à décoder la combinaison " + combinaisons.get(joueur) + " en " + nombreEssai + " coups.");
                }
            }
        }
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
                //todo sortir le display
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
        List<String[]> propositions = playersPropostions.get(joueur);
        String proposition = demandeDeCombinaison(joueur);
        String resultat = evaluerProposition(joueur, proposition);
        System.out.println(resultat);
        String[] resultatTour = {proposition,resultat};
        propositions.add(resultatTour);
        playersPropostions.put(joueur, propositions);

        for (String[] tr: playersPropostions.get(joueur)) {
            System.out.println("proposition : " + tr[0] + "  -> resultat : " + tr[1]);

        }
        //System.out.println(playersPropostions.get(joueur));

    }

    /**
     * Methode pour l'evaluation de la proposition
     *
     * @param joueur joueur qui soumets la proposition
     * @param proposition proposition à évaluer
     * @return retourne son evaluation
     */
    protected abstract String evaluerProposition(Player joueur, String proposition);

    /**
     * Test si la dernière proposition du joueur est gagante pour mettre à jour le parametre winner de player
     *
     * @param joueur joueur qui vient de joueur
     */
    protected abstract void CombinaisonTrouvee(Player joueur);
}
