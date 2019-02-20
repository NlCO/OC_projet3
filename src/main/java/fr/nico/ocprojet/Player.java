package fr.nico.ocprojet;

/**
 * Player est une représentation abstraite d'un joueur soit humain @see Human soit machine @see Bot
 *
 * un joueur est caractrisé par 2 statuts (vrai ou faux):
 * <ul>
 *     <li>"decodeur"</li>
 *     <li>"codeur"</li>
 * </ul>
 */
public abstract class Player {
    private boolean decodeur = false;
    private boolean codeur = false;

    public void setRoles(GameMode mode) {
        decodeur = mode.decodeur;
        codeur = mode.codeur;
    }

    public boolean isDecodeur() {
        return decodeur;
    }

    public boolean isCodeur() {
        return codeur;
    }


    public Game choixDuJeu(){
        return null;
    }

    public GameMode choixDuMode(){
        return null;
    }
}
