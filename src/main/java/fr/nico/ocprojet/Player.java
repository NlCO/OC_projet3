package fr.nico.ocprojet;

import java.util.List;

/**
 * Player est une représentation abstraite d'un joueur soit humain @see Human soit machine @see Bot
 *
 * un joueur est caractrisé par 2 statuts (vrai ou faux):
 * <ul>
 *     <li>"codeur" : joueur générant le code à découvrir</li>
 *     <li>"décodeur" : joueur charchan a découvrir le code</li>
 * </ul>
 */
public abstract class Player {
    private boolean decodeur = false;
    private boolean codeur = false;
    private boolean winner = false;

    /**
     * Permet d'affecter les statut codeur/décodeur en fonction du mode de jeu
     *
     * @param mode mode de jeu @see GameMode
     */
    public void setRoles(GameMode mode) {
        decodeur = mode.decodeur;
        codeur = mode.codeur;
    }

    public abstract Games choixDuJeu();

    public abstract GameMode choixDuMode();

    public abstract String genereUneCombinaison(int tailleCombinaison);

    public abstract String proposeUneCombinaison(int tailleCombinaison, List<String[]> historique);

    public boolean isDecodeur() {
        return decodeur;
    }

    public boolean isCodeur() {
        return codeur;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public abstract String getName();

}
