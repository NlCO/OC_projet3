package fr.nico.ocprojet;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import static fr.nico.ocprojet.App.logger;

/**
 * La classe GamePlay est une représentation abstraite du concept des jeux.
 * Elle porte les propriétés communes au jeux :
 * <ul>
 * <li>players : liste de joueur </li>
 * <li>jeu : jeu instancié</li>
 * <li>tailleCombinaison : la longeur de la combinaison</li>
 * <li>nombreEssaie : le nombre de tentatives</li>
 * <li>combinaisons : une Map ayant pour clé le joueur et pour valeur la combinaison qu'il doit trouver</li>
 * <li>playersPropositions : une Map ayant pour clé le joueur et pour valeur une liste des proposition et leur résultat sous forme d'un tableau</li>
 * <li>panelCouleur : nombre de chiffres/couleur disponibles pour une combinasion</li>
 * </ul>
 */
public abstract class GamePlay {
    protected List<Player> players;
    protected Games jeu;
    protected Integer tailleCombinaison;
    protected Integer nombreEssai;
    protected Map<Player, String> combinaisons;
    protected Map<Player, List<String[]>> playersPropostions;
    protected List<String> setDeValeurs;


    /**
     * Méthode de déroulement de jeu
     */
    public void deroulement() {
        initialiserLaPartie();
        jouerLaPartie();
        bilanDeLaPartie();
    }

    /**
     * Methode pour initialiser la Partie en fonction des roles des joueurs
     */
    public void initialiserLaPartie() {
        combinaisons = new Hashtable<>();
        playersPropostions = new Hashtable<>();
        for (Player joueur : players) {
            joueur.setWinner(false);
            if (joueur.isCodeur()) {
                creerCombiniaison(joueur);
                if (App.DEVMODE) {
                    System.out.println("(" + joueur.getName() + " a choisi la combinaison " + combinaisons.get(getAdversaire(joueur)) + ")");
                }
            }
            if (joueur.isDecodeur()) {
                List<String[]> propositions = new ArrayList<>();
                playersPropostions.put(joueur, propositions);
            }
        }
    }

    /**
     * Methode visant à créer une combinaison à trouver pour son adversaire
     *
     * @param joueur Joueur qui crée la combinaison
     */
    public void creerCombiniaison(Player joueur) {
        String combinaison;
        do {
            combinaison = joueur.genereUneCombinaison(tailleCombinaison, setDeValeurs);
            if (!combinaisonEstConforme(combinaison)) {
                combinaison = null;
                logger.warn("Combinaison non conforme");
                System.out.println("combinaison saisie non conforme");
            }
        } while (combinaison == null);
        combinaisons.put(getAdversaire(joueur), combinaison);
        System.out.println(joueur.getName() + " a créé une combinaison secrète");
    }

    /**
     * Methode pour gerer les tours d'une partie
     */
    private void jouerLaPartie() {
        int tour = 0;
        do {
            ++tour;
            for (Player joueur : players) {
                if (joueur.isDecodeur()) {
                    faireUneTentative(joueur);
                    combinaisonTrouvee(joueur);
                }
            }
        } while (!(players.get(0).isWinner() || players.get(1).isWinner()) && tour < nombreEssai);
    }

    /**
     * Methode gerant un tour de jeu
     *
     * @param joueur joueur dont c'est le tour de jeu
     */
    public void faireUneTentative(Player joueur) {
        String proposition = demandeDeCombinaison(joueur);
        String resultat = evaluerProposition(combinaisons.get(joueur), proposition);
        String[] resultatTour = {proposition, resultat};
        afficheResultat(joueur, resultatTour);
        List<String[]> propositions = playersPropostions.get(joueur);
        propositions.add(resultatTour);
        playersPropostions.put(joueur, propositions);
    }


    /**
     * Methode pour la demande de proposition de combinaison lors d'un tour
     *
     * @param joueur joueur qui soumets la proposition
     * @return la combinaison
     */
    public String demandeDeCombinaison(Player joueur) {
        String proposition;
        do {
            proposition = joueur.proposeUneCombinaison(jeu, tailleCombinaison, setDeValeurs, playersPropostions.get(joueur));
            if (!combinaisonEstConforme(proposition)) {
                proposition = null;
                logger.warn("Combinaison non conforme");
                System.out.println("combinaison saisie non conforme");
            }
        } while (proposition == null);
        return proposition;
    }

    /**
     * Methode pour checker la conformité de la saisie de la combinaison
     *
     * @param combinaison combinaison à tester
     * @return vrai ou faux
     */
    public abstract boolean combinaisonEstConforme(String combinaison);

    /**
     * Retourne au joueur le resultat de sa proposition
     *
     * @param joueur       joueur qui a proposé une combinaison
     * @param resultatTour le resultat du tour sous forme d'un tableau avec la proposition et son evaluation
     */
    protected abstract void afficheResultat(Player joueur, String[] resultatTour);


    /**
     * Methode de cloture de partie
     */
    private void bilanDeLaPartie() {
        if (players.get(0).isWinner() && players.get(1).isWinner()) {
            System.out.println("Match nul - vous avez trouvé tous les 2 en " + playersPropostions.get(players.get(0)).size() + " coups.");
        } else {
            for (Player joueur : players) {
                if (joueur.isDecodeur() && joueur.isWinner()) {
                    System.out.println("Bravo " + joueur.getName() + " :) !!!!  - vous avez décodé la combinaison en " + playersPropostions.get(joueur).size() + " coups.");
                } else if (joueur.isDecodeur() && !joueur.isWinner()) {
                    System.out.println("Perdu " + joueur.getName() + " :( !!!!  - vous n'êtes pas parvenu à décoder la combinaison " + combinaisons.get(joueur) + " en moins de " + nombreEssai + " coups.");
                }
            }
        }
    }

    /**
     * Methode pour obtenir l'adversaire d'un joueur (fonctionne seulement pour un jeu 2 joueurs)
     * se base sur la liste de l'intance
     *
     * @param joueur le joueur
     * @return l'adversaire
     */
    public Player getAdversaire(Player joueur) {
        return players.get(Math.abs(players.indexOf(joueur) - 1));
    }

    /**
     * Methode pour l'evaluation de la proposition
     *
     * @param code        combinaison à trouver
     * @param proposition proposition à évaluer
     * @return retourne son evaluation
     */
    public abstract String evaluerProposition(String code, String proposition);

    /**
     * Test si la dernière proposition du joueur est gagante pour mettre à jour le parametre winner de player
     *
     * @param joueur joueur qui vient de joueur
     */
    protected abstract void combinaisonTrouvee(Player joueur);

    public Map<Player, String> getCombinaisons() {
        return combinaisons;
    }

    public int getTailleCombinaison() {
        return tailleCombinaison;
    }

    public Map<Player, List<String[]>> getPlayersPropostions() {
        return playersPropostions;
    }
}
